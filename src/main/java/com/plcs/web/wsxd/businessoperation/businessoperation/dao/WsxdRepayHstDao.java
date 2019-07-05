package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRepayHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.RepayHstVO;

import java.util.List;

/**
 * 还款记录DAO接口
 * @author tanweijian
 * @version 2019-06-27
 */
@MyBatisDao
public interface WsxdRepayHstDao extends CrudDao<WsxdRepayHst> {
	List<RepayHstVO> selectByLoanBillNo(String loanBillNo);
}