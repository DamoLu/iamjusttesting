package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.plcsbatchrequest.PlctBatchRequest;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.common.utils.RandomUtil;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.modules.sys.entity.Role;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdAllocateHstDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdAllocateHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.CaseStatus;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.DataScopeEnum;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.DistributeResultStatus;
import com.plcs.web.wsxd.businessoperation.businessoperation.utils.CompareUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
			// 数据权限控制
			// 1. 根据登录账户获取对应的角色类型（可能有多个，取最高权限）
			// 2. 根据角色获取对应的数据范围权限（可能有多个，取最大范围），实际上1、2一起的
			List<Role> roleList =  UserUtils.getUser().getRoleList();
			List<String> dataScopes = new ArrayList<>();
			if (roleList != null && roleList.size() > 0) {
				for (Role role : roleList) {
					String dataScope = role.getDataScope();
					dataScopes.add(dataScope);
				}
			}
			// 3. 根据数据范围权限做限制
			String dataScope =  CompareUtils.getMaxDataScope(dataScopes);
			if (dataScope.equals(DataScopeEnum.ALL_DATA.getCode())) {
				// 3.1 统管理员、业务管理员，可以看到所有案件（范围为1）

			} else if (dataScope.equals(DataScopeEnum.BELONG_DEPARTMENT.getCode())) {
				// 3.2 事业部负责人，看到关于自己所属机构的事业部的所有案件（范围为4）
				wsxdCase.setDepartmentId(UserUtils.getUser().getOffice().getCode());
			} else {
				// 3.3 其他角色看到自己的案件（范围为8）
				wsxdCase.setPermissionOdv(UserUtils.getUser().getLoginName());
			}

			// 取出待分配的处理人员名
			String odvName = wsxdCase.getOdvName();
			// 如果是全选的话，则先查询所有的逾期案件，然后进行分配
			wsxdCase.setOdvName(wsxdCase.getCsy()); // 这个是搜索栏的值
			if (wsxdCaseService.isDoSerach(wsxdCase)) {
				wsxdCase.setSearch("yes");
			} else {
				wsxdCase.setSearch("no");
			}
			wsxdCase.setFind("no");

            if (wsxdCase.getAppName() != null && !wsxdCase.getAppName().equals("")) {
                wsxdCase.getAppName().split(",");
                wsxdCase.setAppNameList(Arrays.asList(wsxdCase.getAppName().split(",")));
            }

			wsxdCaseList = wsxdCaseService.findList(wsxdCase);
			wsxdCase.setOdvName(odvName);
			return doDistributeCSY(wsxdCase, wsxdCaseList);
		}else {
			// 否则，先根据复选框的wsxd_case_id查询到对应的逾期案件，然后进行分配
			if (wsxdCase != null) {
				if (StringUtils.isNotBlank(wsxdCase.getCheckID())) {
					String[] idArr=wsxdCase.getCheckID().split(",");
					for (String id : idArr) {
						wsxdCase.setId(id);
						wsxdCase.setFind("no");
						wsxdCaseList.add(wsxdCaseService.get(wsxdCase));
					}
					return doDistributeCSY(wsxdCase, wsxdCaseList);
				}
			}
		}
		return DistributeResultStatus.DISTRIBUTE_SUCCESS.getStatus();
	}

	/**
	 * 开始分配
	 * @param wsxdCase
	 * @param wsxdCaseList
	 * @return
	 */
	@Transactional(readOnly = false)
	public int doDistributeCSY(WsxdCase wsxdCase, List<WsxdCase> wsxdCaseList) {
		logger.info("开始进行手工分案...");

		try {
			if (wsxdCaseList != null && wsxdCaseList.size() > 0) {
				int count = 0;

				// 分配前拦截未逾期的案件
				for (WsxdCase wsxdCase2: wsxdCaseList) {
					if (wsxdCase2.getCaseStatus() != null && wsxdCase2.getCaseStatus() != "") {
						if (!wsxdCase2.getCaseStatus().equals(CaseStatus.OVERDUE.getDesc())) {
							return DistributeResultStatus.NOT_OVERDUE.getStatus();
						}
					} else {
						return DistributeResultStatus.NOT_OVERDUE.getStatus();
					}
				}

				for (WsxdCase wsxdCase1 : wsxdCaseList) {
					WsxdAllocateHst wsxdAllocateHst = new WsxdAllocateHst();
					WsxdAllocateHst wsxdAllocateHst1 = new WsxdAllocateHst();
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
					wsxdAllocateHst1.setLoanBillNo(wsxdAllocateHst.getLoanBillNo());
					wsxdAllocateHst1.setOdv(wsxdAllocateHst.getOdv());

					// 只有endDate is null代表当前案件在催状态，才进行停催动作
					if (lastAllocate != null && lastAllocate.getEndDate() == null) {
						// 将需要停催的分案历史id传给停催方法
						wsxdAllocateHst1.setId(lastAllocate.getId());
						Map<String, Object> result = plctBatchRequest.stopAllocateCase(wsxdAllocateHst1);
						Integer status = (Integer) result.get("status");
						String msg = (String)result.get("msg");

						if (status == 200) {
							// 开始插入到分案历史表
							count = wsxdAllocateHstDao.insertSelective(wsxdAllocateHst);
							if (count > 0) {
								printSuccessLog(wsxdAllocateHst);
							} else {
								printErrorLog(wsxdAllocateHst);
								logger.info("结束进行手工分案...");
								return DistributeResultStatus.DISTRIBUTE_FAILURE.getStatus();
							}
						} else {
							// 停催失败，停止手动分案
							logger.info("停催失败：{}，导致手工分案失败，借据号：{}，原处理组编号：{}、原处理组名：{}、原处理人员编号：{}、原处理人名：{}，" +
											"失败分配给现处理组编号：{}、现处理组名：{}、现催收员编号：{}、现催收员姓名：{}",
									msg, wsxdAllocateHst.getLoanBillNo(), wsxdAllocateHst.getOldOdvGroup(), wsxdAllocateHst.getOldOdvGroupName() ,
									wsxdAllocateHst.getOldOdv(), wsxdAllocateHst.getOldOdvName(), wsxdAllocateHst.getOdvGroup(), wsxdAllocateHst.getOdvGroupName(),
									wsxdAllocateHst.getOdv(), wsxdAllocateHst.getOdvName());
							logger.info("结束进行手工分案...");
							return DistributeResultStatus.DISTRIBUTE_FAILURE.getStatus();
						}
					} else {
						// 开始插入到分案历史表
						count = wsxdAllocateHstDao.insertSelective(wsxdAllocateHst);
						if (count > 0) {
							printSuccessLog(wsxdAllocateHst);
						} else {
							printErrorLog(wsxdAllocateHst);
							logger.info("结束进行手工分案...");
							return DistributeResultStatus.DISTRIBUTE_FAILURE.getStatus();
						}
					}
				}
				logger.info("结束进行手工分案...");
				return DistributeResultStatus.DISTRIBUTE_SUCCESS.getStatus();
			} else {
				logger.info("手工分案失败，没有需要分配的案件数据！");
				logger.info("结束进行手工分案...");
				return DistributeResultStatus.NOT_SELECTED.getStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("手工分案失败：{}", e.getMessage());
			return DistributeResultStatus.DISTRIBUTE_FAILURE.getStatus();
		}
	}

	private void printSuccessLog(WsxdAllocateHst wsxdAllocateHst) {
		logger.info("手工分案成功，借据号：{}，原处理组编号：{}、原处理组名：{}、原处理人员编号：{}、原处理人名：{}，" +
						"成功分配给现处理组编号：{}、现处理组名：{}、现催收员编号：{}、现催收员姓名：{}",
				wsxdAllocateHst.getLoanBillNo(), wsxdAllocateHst.getOldOdvGroup(), wsxdAllocateHst.getOldOdvGroupName() ,
				wsxdAllocateHst.getOldOdv(), wsxdAllocateHst.getOldOdvName(), wsxdAllocateHst.getOdvGroup(), wsxdAllocateHst.getOdvGroupName(),
				wsxdAllocateHst.getOdv(), wsxdAllocateHst.getOdvName());
	}

	private void printErrorLog(WsxdAllocateHst wsxdAllocateHst) {
		logger.info("手工分案失败，借据号：{}，原处理组编号：{}、原处理组名：{}、原处理人员编号：{}、原处理人名：{}，" +
						"失败分配给现处理组编号：{}、现处理组名：{}、现催收员编号：{}、现催收员姓名：{}",
				wsxdAllocateHst.getLoanBillNo(), wsxdAllocateHst.getOldOdvGroup(), wsxdAllocateHst.getOldOdvGroupName() ,
				wsxdAllocateHst.getOldOdv(), wsxdAllocateHst.getOldOdvName(), wsxdAllocateHst.getOdvGroup(), wsxdAllocateHst.getOdvGroupName(),
				wsxdAllocateHst.getOdv(), wsxdAllocateHst.getOdvName());
	}
}