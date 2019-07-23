package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.google.common.collect.Maps;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.modules.sys.entity.Dict;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdCaseDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ManagerVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ProductNameVO;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst.DeductHstAResponse;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.settlehst.SettleHstA;
import com.plcs.web.wsxd.interfaces.anshuo.service.AnShuoService;
import com.plcs.web.wsxd.interfaces.anshuo.utils.DateFormatUtils;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst.DeductHstC;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst.DeductHstResponse;
import com.plcs.web.wsxd.interfaces.chuangxin.service.ChuangXinService;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst.DeductHst;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst.QueryDeductHstResponse;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst.SettleHst;
import com.plcs.web.wsxd.interfaces.yinShu.service.YinShuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 业务操作Service
 * @author tanweijian
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class WsxdCaseService extends CrudService<WsxdCaseDao, WsxdCase> {

	@Autowired
	WsxdCaseDao wsxdCaseDao;
	@Autowired
	YinShuService yinShuService;
	@Autowired
	AnShuoService anShuoService;
	@Autowired
	ChuangXinService chuangXinService;

	public WsxdCase get(WsxdCase wsxdCase) {
		return super.get(wsxdCase);
	}

	public WsxdCase getByLoanBillNo(String loanBillNo) {
		return wsxdCaseDao.getByLoanBillNo(loanBillNo);
	}
	
	public List<WsxdCase> findList(WsxdCase wsxdCase) {
		return super.findList(wsxdCase);
	}
	
	public Page<WsxdCase> findPage(Page<WsxdCase> page, WsxdCase wsxdCase) {
		Page<WsxdCase> result = super.findPage(page, wsxdCase);
		if (result != null && result.getList() != null && result.getList().size() > 0) {
			List<WsxdCase> wsxdCaseList = new LinkedList<>();
			Map<String, ProductNameVO> productNameVOMap = this.productBeanToMap();
			for (WsxdCase wsxdCase1 : result.getList()) {
				WsxdCase wsxdCase2 = new WsxdCase();
				// 根据产品编码来获取产品名称的规则如下
				// 1. description=进件通路，为线下产品，统一用线下产品
				// 2. description=产品编码，为线上产品，根据编码到字典匹配
				ProductNameVO productNameVO = productNameVOMap.get(wsxdCase1.getAppName());
				if (productNameVO != null) {
					if (productNameVO.getDescription().equals("进件通路")) {
						BeanUtils.copyProperties(wsxdCase1, wsxdCase2);
						wsxdCase2.setAppName("线下产品");
					} else if(productNameVO.getDescription().equals("产品编码")) {
						BeanUtils.copyProperties(wsxdCase1, wsxdCase2);
						wsxdCase2.setAppName(productNameVO.getProductName());
					} else {
						BeanUtils.copyProperties(wsxdCase1, wsxdCase2);
						wsxdCase2.setAppName(productNameVO.getProductCode());
					}
				}
				wsxdCaseList.add(wsxdCase2);
			}
			result.setList(wsxdCaseList);
		}
		return result;
	}

    public Page<WsxdCase> findRealListPage(Page<WsxdCase> page, WsxdCase wsxdCase) {
	    wsxdCase.setPage(page);
        page.setList(wsxdCaseDao.findRealList(wsxdCase));
        return page;
    }

	public List<WsxdCase> findRealList(WsxdCase wsxdCase) {
		return wsxdCaseDao.findRealList(wsxdCase);

	}

	public List<WsxdCase> findAllList() {
		return wsxdCaseDao.findAllList();
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdCase wsxdCase) {
		super.save(wsxdCase);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdCase wsxdCase) {
		super.delete(wsxdCase);
	}

	// 获取事业部列表
	public List<DepartmentVO> findDepartmentList() {
		return wsxdCaseDao.selectDepartmentList();
	}

	// 获取放款机构列表
	public List<String> findLoanOrginList() {
		return wsxdCaseDao.selectLoanOrginList();
	}

	// 获取客户经理列表
	public List<ManagerVO> findManagerList() {
		return wsxdCaseDao.selectManagerList();
	}

	// 获取产品名称列表
	public List<ProductNameVO> findProductNameList() {
		// 将多个产品编码对于同一产品名称的字典组装成如下形式
		// 101,102->我来贷
		// 103,104->自营
		// 线下产品统一组装成：offline_product->线下产品
		List<ProductNameVO> productNameVOList = wsxdCaseDao.selectProductNameList();
		List<ProductNameVO> productNameVOS = new ArrayList<>();
		boolean hasOffline = false;
		Map<String, Boolean> map = new ConcurrentHashMap<>();

		for (ProductNameVO productNameVO : productNameVOList) {
			ProductNameVO productName = new ProductNameVO();

			// 如果是线下产品，则产品编码统一用offline_product
			if (productNameVO.getDescription().equals("进件通路") || productNameVO.getDescription().equals("辅助字典")) {
				productName.setProductCode("offline_product");
				productName.setProductName("线下产品");
				if (!hasOffline) {
					productNameVOS.add(productName);
				}
				hasOffline = true;
				continue ;
			}

			List<String> productCodeList = new ArrayList<>();
			// 如果线上产品有多个产品编码，则需要合并
			for (ProductNameVO productNameVO1 : productNameVOList) {
				if (productNameVO.getProductName().equals(productNameVO1.getProductName())) {
					productCodeList.add(productNameVO1.getProductCode());
				}
			}

			if (productCodeList.size() > 1) {
				productName.setProductCode(StringUtils.join(productCodeList.toArray(), ","));
			} else {
				productName.setProductCode(StringUtils.join(productCodeList.toArray(), ""));
			}
			productName.setProductName(productNameVO.getProductName());
			// 如果该产品名称已经存在，则不再存储
			if (!map.containsKey(productNameVO.getProductName())) {
				productNameVOS.add(productName);
			}
			map.put(productNameVO.getProductName(), true);
		}

		return productNameVOS;
	}

	public WsxdCase findByLoanBillNo(String loanBillNo) {
		return wsxdCaseDao.findByLoanBillNo(loanBillNo);
	}

	public Page<DeductHst> findDeductHstList(WsxdCase wsxdCase, Page<DeductHst> page) {
		wsxdCase.setSourceCore("03");
		List<DeductHst> list = new ArrayList<>();
		//案件来源核心(01:银数、02:安硕、03:创新、C:c金所）
		if ("01".equals(wsxdCase.getSourceCore())) {
			QueryDeductHstResponse queryDeductHstResponse = yinShuService.findDeductHstList(wsxdCase, page);
			if (null != queryDeductHstResponse) {
				page.setList(queryDeductHstResponse.getTxnList());
				page.setCount(Integer.parseInt(queryDeductHstResponse.getTxnCount()));
				return page;
			}
        } else if ("02".equals(wsxdCase.getSourceCore())) {
			DeductHstAResponse deductHstAResponse = anShuoService.findDeductHstList(wsxdCase, page);
			if (null != deductHstAResponse) {
				page.setCount(Integer.parseInt(deductHstAResponse.getTotalCount()));
				deductHstAResponse.getDeductHstAList().forEach(deductHstA -> {
					DeductHst deductHst = new DeductHst();
					deductHst.setTxnTime(DateFormatUtils.parseToDate(deductHstA.getTxnTime()));
					deductHst.setTxnAmt(deductHstA.getActualPayAmt());
					list.add(deductHst);
				});
				page.setList(list);
				return page;
			}
		} else if ("03".equals(wsxdCase.getSourceCore())) {
			DeductHstResponse deductHstCResponse = chuangXinService.findDeductHstList(wsxdCase);
			List<DeductHstC> deductHstCList = deductHstCResponse.getDeductHstCList();
			if (null != deductHstCList) {
				deductHstCList.forEach(deductHstC ->{
					DeductHst deductHst = new DeductHst();
					deductHst.setTxnAmt(deductHstC.getPayAmt());
					deductHst.setTxnTime(deductHstC.getPayTime());
					list.add(deductHst);
				});
			}
			page.setCount(list.size());
			page.setList(list);
			return page;
		}
		return null;
	}

	public SettleHst querySettleHst(WsxdCase wsxdCase) {
		SettleHst settleHst = new SettleHst();
		//案件来源核心(01:银数、02:安硕、03:创新、C:c金所）
		if ("01".equals(wsxdCase.getSourceCore())) {
			return yinShuService.querySettleHst(wsxdCase);
		} else if ("02".equals(wsxdCase.getSourceCore())) {
			SettleHstA settleHstA = anShuoService.querySettleHst(wsxdCase);
			if ("OSFS110000".equals(settleHstA.getReturnCode())) {
				settleHst.setDate(settleHstA.getDate());
				settleHst.setAmount(settleHstA.getAmount());
				settleHst.setPrincipal(settleHstA.getPrincipal());
				settleHst.setInterest(settleHstA.getInterest());
				return settleHst;
			}
		}
		return null;
	}

	/**
	 * 判断搜索栏是否有值
	 * @param wsxdCase
	 * @return
	 */
	public boolean isDoSerach(WsxdCase wsxdCase) {
		if ((wsxdCase.getAppOrg() != null && !wsxdCase.getAppOrg().equals(""))
				|| (wsxdCase.getDepartmentId() != null && !wsxdCase.getDepartmentId().equals(""))
				|| (wsxdCase.getLoanOrgin() != null && !wsxdCase.getLoanOrgin().equals(""))
				|| (wsxdCase.getLoanBillNo() != null && !wsxdCase.getLoanBillNo().equals(""))
				|| (wsxdCase.getCustomerIdNo() != null && !wsxdCase.getCustomerIdNo().equals(""))
				|| (wsxdCase.getCustomerName() != null && !wsxdCase.getCustomerName().equals(""))
				|| (wsxdCase.getTelephoneNo() != null && !wsxdCase.getTelephoneNo().equals(""))
				|| (wsxdCase.getCsy() != null && !wsxdCase.getCsy().equals(""))
				|| (wsxdCase.getMinOverdueDays() != null && !wsxdCase.getMinOverdueDays().equals(""))
				|| (wsxdCase.getMaxOverdueDays() != null && !wsxdCase.getMaxOverdueDays().equals(""))
				|| (wsxdCase.getRemindStatus() != null && !wsxdCase.getRemindStatus().equals(""))
				|| (wsxdCase.getCaseStatus() != null && !wsxdCase.getCaseStatus().equals(""))
				|| (wsxdCase.getManagerId() != null && !wsxdCase.getManagerId().equals(""))
				|| (wsxdCase.getAppName() != null && !wsxdCase.getAppName().equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	public Dict getDictByTypeAndValue(WsxdCase wsxdCase) {
		Dict dict = new Dict();
		dict.setType(wsxdCase.getDictType());
		dict.setValue(wsxdCase.getDictValue());
		return wsxdCaseDao.findDictByTypeAndValue(dict);
	}

	/**
	 * 将产品对象列表装换为map
	 * @param
	 * @return
	 */
	public Map<String, ProductNameVO> productBeanToMap() {
		List<ProductNameVO> productNameVOList = wsxdCaseDao.selectProductNameList();
		Map<String, ProductNameVO> map = Maps.newHashMap();
		if (productNameVOList != null && productNameVOList.size() > 0) {
			for (ProductNameVO productNameVO : productNameVOList) {
				map.put(productNameVO.getProductCode(), productNameVO);
			}
		}
		return map;
	}
}