package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ManagerVO;

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
	List<String> selectAppNameList();
	WsxdCase findBaseInfo(String loanBillNo);
    List<WsxdCase> findCardInfo(WsxdCase wsxdCase);
	List<WsxdCase> findAllList();
}