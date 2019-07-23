package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.modules.sys.entity.Dict;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ManagerVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ProductNameVO;

import java.util.List;

/**
 * 业务操作DAO接口
 * @author tanweijian
 * @version 2019-06-10
 */
@MyBatisDao
public interface WsxdCaseDao extends CrudDao<WsxdCase> {
	List<DepartmentVO> selectDepartmentList();
	List<String> selectLoanOrginList();
	List<ManagerVO> selectManagerList();
	List<ProductNameVO> selectProductNameList();
	WsxdCase findBaseInfo(String loanBillNo);
	WsxdCase getByLoanBillNo(String loanBillNo);
    List<WsxdCase> findCardInfo(WsxdCase wsxdCase);
	List<WsxdCase> findAllList();
    WsxdCase findByLoanBillNo(String loanBillNo);
	List<WsxdCase> findRealList(WsxdCase wsxdCase);
	Dict findDictByTypeAndValue(Dict dict);
}