package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdAllocateHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;

import java.util.List;

/**
 * 分案历史DAO接口
 * @author tanweijian
 * @version 2019-06-11
 */
@MyBatisDao
public interface WsxdAllocateHstDao extends CrudDao<WsxdAllocateHst> {
	List<OdvOV> selectOdvList();
	List<DepartmentVO> selectDepartmentList();
	List<String> selectLoanOrginList();
	WsxdAllocateHst selectLastAllocate(String loanBillNo);
	int insertSelective(WsxdAllocateHst wsxdAllocateHst);
}