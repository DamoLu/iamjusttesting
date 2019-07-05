package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.plcsbatchrequest.PlctBatchRequest;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.common.utils.RandomUtil;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdAllocateHstDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdAllocateHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分案历史Service
 * @author tanweijian
 * @version 2019-06-11
 */
@Service
@Transactional
public class WsxdAllocateHstService extends CrudService<WsxdAllocateHstDao, WsxdAllocateHst> {

	@Autowired
	private WsxdAllocateHstDao wsxdAllocateHstDao;
	@Autowired
	private WsxdCaseService wsxdCaseService;
	@Autowired
	private PlctBatchRequest plctBatchRequest;

	public WsxdAllocateHst get(String id) {
		return super.get(id);
	}
	
	public List<WsxdAllocateHst> findList(WsxdAllocateHst wsxdAllocateHst) {
		return super.findList(wsxdAllocateHst);
	}
	
	public Page<WsxdAllocateHst> findPage(Page<WsxdAllocateHst> page, WsxdAllocateHst wsxdAllocateHst) {
		return super.findPage(page, wsxdAllocateHst);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdAllocateHst wsxdAllocateHst) {
		super.save(wsxdAllocateHst);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdAllocateHst wsxdAllocateHst) {
		super.delete(wsxdAllocateHst);
	}

	// 获取处理人员列表
	public List<OdvOV> findOdvList() {
		return wsxdAllocateHstDao.selectOdvList();
	}

	// 获取事业部列表
	public List<DepartmentVO> findDepartmentList() {
		return wsxdAllocateHstDao.selectDepartmentList();
	}

	// 获取放款机构列表
	public List<String> findLoanOrginList() {
		return wsxdAllocateHstDao.selectLoanOrginList();
	}

	public int insertAllocateHst(WsxdCase wsxdCase) {
		List<WsxdCase> wsxdCaseList = new ArrayList<>();
		if(Boolean.valueOf(wsxdCase.getSelectAllFlag())){
			// 取出待分配的处理人员名
			String odvName = wsxdCase.getOdvName();
			// 如果是全选的话，则先查询所有的逾期案件，然后进行分配
			wsxdCase.setOdvName(wsxdCase.getCsy());
			wsxdCaseList = wsxdCaseService.findList(wsxdCase);
			wsxdCase.setOdvName(odvName);
			return doDistributeCSY(wsxdCase, wsxdCaseList);
		}else {
			// 否则，先根据复选框的wsxd_case_id查询到对应的逾期案件，然后进行分配
			if (wsxdCase != null) {
				if (StringUtils.isNotBlank(wsxdCase.getCheckID())) {
					String[] idArr=wsxdCase.getCheckID().split(",");
					for (String id : idArr) {
						wsxdCaseList.add(wsxdCaseService.get(id));
					}
					return doDistributeCSY(wsxdCase, wsxdCaseList);
				}
			}
		}
		return 1;
	}

	/**
	 * 开始分配
	 * @param wsxdCase
	 * @param wsxdCaseList
	 * @return
	 */
	public int doDistributeCSY(WsxdCase wsxdCase, List<WsxdCase> wsxdCaseList) {
		logger.info("开始进行手工分案...");

		try {
			if (wsxdCaseList != null && wsxdCaseList.size() > 0) {
				int count = 0;
				for (WsxdCase wsxdCase1 : wsxdCaseList) {
					WsxdAllocateHst wsxdAllocateHst = new WsxdAllocateHst();
					BeanUtils.copyProperties(wsxdCase1, wsxdAllocateHst);
					wsxdAllocateHst.setAppOrg(wsxdCase1.getAppOrgName());
					wsxdAllocateHst.setAppOrgName(wsxdCase1.getAppOrg());
					wsxdAllocateHst.setId(RandomUtil.randomUUID());
					if (StringUtils.contains(wsxdCase1.getCustomerName(), "公共池")) {
						wsxdAllocateHst.setHasCommonPool("0");
					} else {
						wsxdAllocateHst.setHasCommonPool("1");
					}
					wsxdAllocateHst.setOverdueDays(wsxdCase1.getOverdueDays());
					wsxdAllocateHst.setOdv(wsxdCase.getOdv());
					wsxdAllocateHst.setOdvName(wsxdCase.getOdvName());
					wsxdAllocateHst.setOdvGroup(wsxdCase.getOdvGroup());
					wsxdAllocateHst.setOdvGroupName(wsxdCase.getOdvGroupName());
					WsxdAllocateHst lastAllocate = wsxdAllocateHstDao.selectLastAllocate(wsxdCase1.getLoanBillNo());
					if (lastAllocate != null) {
						wsxdAllocateHst.setOldOdv(lastAllocate.getOdv());
						wsxdAllocateHst.setOldOdvName(lastAllocate.getOdvName());
						wsxdAllocateHst.setOldOdvGroup(lastAllocate.getOdvGroup());
						wsxdAllocateHst.setOldOdvGroupName(lastAllocate.getOdvGroupName());
					}
					wsxdAllocateHst.setStartDate(new Date());
					wsxdAllocateHst.setAllocateType("1");
					wsxdAllocateHst.setCreateDate(new Date());
					wsxdAllocateHst.setCreateByUser(UserUtils.getUser().getLoginName());
					wsxdAllocateHst.setUpdateDate(new Date());
					wsxdAllocateHst.setUpdateByUser(UserUtils.getUser().getLoginName());
					wsxdAllocateHst.setRemarks("手工分案");
					wsxdAllocateHst.setDelFlag("0");

					// 分配之前，先对当前的案件进行停催操作
					WsxdAllocateHst wsxdAllocateHst1 = new WsxdAllocateHst();
					wsxdAllocateHst1.setId(wsxdAllocateHst.getId());
					wsxdAllocateHst1.setLoanBillNo(wsxdAllocateHst.getLoanBillNo());
					wsxdAllocateHst1.setOdv(wsxdAllocateHst.getOdv());
					Map<String, Object> result = plctBatchRequest.stopAllocateCase(wsxdAllocateHst1);
					Integer status = (Integer) result.get("status");
					String msg = (String)result.get("msg");
					if (status == 200) {
						// 开始插入到分案历史表
						count = wsxdAllocateHstDao.insertSelective(wsxdAllocateHst);
						if (count > 0) {
							logger.info("手工分案成功，借据号：{}，原处理组编号：{}、原处理组名：{}、原处理人员编号：{}、原处理人名：{}，" +
											"成功分配给现处理组编号：{}、现处理组名：{}、现催收员编号：{}、现催收员姓名：{}",
									wsxdAllocateHst.getLoanBillNo(), wsxdAllocateHst.getOldOdvGroup(), wsxdAllocateHst.getOldOdvGroupName() ,
									wsxdAllocateHst.getOldOdv(), wsxdAllocateHst.getOldOdvName(), wsxdAllocateHst.getOdvGroup(), wsxdAllocateHst.getOdvGroupName(),
									wsxdAllocateHst.getOdv(), wsxdAllocateHst.getOdvName());
						} else {
							logger.info("手工分案失败，借据号：{}，原处理组编号：{}、原处理组名：{}、原处理人员编号：{}、原处理人名：{}，" +
											"失败分配给现处理组编号：{}、现处理组名：{}、现催收员编号：{}、现催收员姓名：{}",
									wsxdAllocateHst.getLoanBillNo(), wsxdAllocateHst.getOldOdvGroup(), wsxdAllocateHst.getOldOdvGroupName() ,
									wsxdAllocateHst.getOldOdv(), wsxdAllocateHst.getOldOdvName(), wsxdAllocateHst.getOdvGroup(), wsxdAllocateHst.getOdvGroupName(),
									wsxdAllocateHst.getOdv(), wsxdAllocateHst.getOdvName());
							logger.info("结束进行手工分案...");
							return 0;
						}
					} else {
						// 停催失败，停止手动分案
						logger.info("停催失败：{}，导致手工分案失败，借据号：{}，原处理组编号：{}、原处理组名：{}、原处理人员编号：{}、原处理人名：{}，" +
										"失败分配给现处理组编号：{}、现处理组名：{}、现催收员编号：{}、现催收员姓名：{}",
								msg, wsxdAllocateHst.getLoanBillNo(), wsxdAllocateHst.getOldOdvGroup(), wsxdAllocateHst.getOldOdvGroupName() ,
								wsxdAllocateHst.getOldOdv(), wsxdAllocateHst.getOldOdvName(), wsxdAllocateHst.getOdvGroup(), wsxdAllocateHst.getOdv(),
								wsxdAllocateHst.getOdvName());
						logger.info("结束进行手工分案...");
						return 0;
					}
				}
				logger.info("结束进行手工分案...");
				return 1;
			} else {
				logger.info("手工分案失败，没有需要分配的案件数据！");
				logger.info("结束进行手工分案...");
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("手工分案失败：{}", e.getMessage());
			return 0;
		}
	}
}