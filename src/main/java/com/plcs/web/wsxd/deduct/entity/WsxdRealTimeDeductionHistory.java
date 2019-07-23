package com.plcs.web.wsxd.deduct.entity;

import org.hibernate.validator.constraints.Length;

import com.plcs.web.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 保存所发送的扣款请求历史Entity
 * @author zhengjiangbo
 * @version 2019-07-18
 */
public class WsxdRealTimeDeductionHistory extends DataEntity<WsxdRealTimeDeductionHistory> {
	
	private static final long serialVersionUID = 1L;
	private String contractNo;		// 合同号
	private String loanBillNo;		// 借据号
	private String serialNum;		// 扣款请求流水号
	private BigDecimal payAmount;		// 核心返回最大可扣款金额
	private String customerName;		// 客户姓名
	private String customerIdNo;		// 客户证件号
	private BigDecimal deductAmt;		// 扣款金额
	private String requestStatus;		// 扣款请求状态
	private String deductStatus;		// 扣款状态
	private String respMsg;		// 响应内容
	private String isPartialDeduction;		// 核心返回 是否支持部分扣款
	private String sourceCore;		// 案件来源核心(银数、安硕、创新）
	private String isDelete;		// is_delete
	
	public WsxdRealTimeDeductionHistory() {
		super();
	}

	public WsxdRealTimeDeductionHistory(String id){
		super(id);
	}

	@Length(min=1, max=128, message="合同号长度必须介于 1 和 128 之间")
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Length(min=1, max=128, message="借据号长度必须介于 1 和 128 之间")
	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}
	
	@Length(min=1, max=64, message="扣款请求流水号长度必须介于 1 和 64 之间")
	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	@Length(min=1, max=40, message="客户姓名长度必须介于 1 和 40 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=1, max=50, message="客户证件号长度必须介于 1 和 50 之间")
	public String getCustomerIdNo() {
		return customerIdNo;
	}

	public void setCustomerIdNo(String customerIdNo) {
		this.customerIdNo = customerIdNo;
	}

	public BigDecimal getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(BigDecimal deductAmt) {
		this.deductAmt = deductAmt;
	}

	@Length(min=1, max=4, message="扣款请求状态长度必须介于 1 和 4 之间")
	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	@Length(min=1, max=4, message="扣款状态长度必须介于 1 和 4 之间")
	public String getDeductStatus() {
		return deductStatus;
	}

	public void setDeductStatus(String deductStatus) {
		this.deductStatus = deductStatus;
	}
	
	@Length(min=0, max=64, message="响应内容长度必须介于 0 和 64 之间")
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	@Length(min=0, max=1, message="核心返回 是否支持部分扣款长度必须介于 0 和 1 之间")
	public String getIsPartialDeduction() {
		return isPartialDeduction;
	}

	public void setIsPartialDeduction(String isPartialDeduction) {
		this.isPartialDeduction = isPartialDeduction;
	}
	
	@Length(min=0, max=64, message="案件来源核心(银数、安硕、创新）长度必须介于 0 和 64 之间")
	public String getSourceCore() {
		return sourceCore;
	}

	public void setSourceCore(String sourceCore) {
		this.sourceCore = sourceCore;
	}
	
	@Length(min=0, max=1, message="is_delete长度必须介于 0 和 1 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}