package com.plcs.web.wsxd.deduct.service;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.deduct.entity.WsxdRealTimeDeductionHistory;
import com.plcs.web.wsxd.deduct.dao.WsxdRealTimeDeductionHistoryDao;

/**
 * 保存所发送的扣款请求历史Service
 * @author zhengjiangbo
 * @version 2019-07-18
 */
@Service
@Transactional
public class WsxdRealTimeDeductionHistoryService extends CrudService<WsxdRealTimeDeductionHistoryDao, WsxdRealTimeDeductionHistory> {

	@Autowired
	WsxdRealTimeDeductionHistoryDao historyDao;

	public WsxdRealTimeDeductionHistory get(String id) {
		return super.get(id);
	}
	
	public List<WsxdRealTimeDeductionHistory> findList(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory) {
		return super.findList(wsxdRealTimeDeductionHistory);
	}
	
	public Page<WsxdRealTimeDeductionHistory> findPage(Page<WsxdRealTimeDeductionHistory> page, WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory) {
		return super.findPage(page, wsxdRealTimeDeductionHistory);
	}

	public void save(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory) {
		super.save(wsxdRealTimeDeductionHistory);
	}

	public void delete(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory) {
		super.delete(wsxdRealTimeDeductionHistory);
	}


	public void logDeductRequest(WsxdCase wsxdCase, String serialNum, JSONObject result, BigDecimal deductionAmount, BigDecimal maxDeductionAmount) {
		WsxdRealTimeDeductionHistory entity=new WsxdRealTimeDeductionHistory();
		entity.setContractNo(wsxdCase.getContractNo());
		entity.setLoanBillNo(wsxdCase.getLoanBillNo());
		entity.setSerialNum(serialNum);
		entity.setDeductAmt(deductionAmount);
		entity.setPayAmount(maxDeductionAmount);
		entity.setCustomerName(wsxdCase.getCustomerName());
		entity.setCustomerIdNo(wsxdCase.getCustomerIdNo());
		if(result!=null){
			entity.setRequestStatus("S");
		}else {
			entity.setRequestStatus("F");
		}
		save(entity);
	}

}