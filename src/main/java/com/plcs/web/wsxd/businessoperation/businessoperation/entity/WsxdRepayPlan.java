package com.plcs.web.wsxd.businessoperation.businessoperation.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.plcs.web.common.persistence.DataEntity;

/**
 * 还款计划Entity
 * @author moyue
 * @version 2019-06-18
 */
public class WsxdRepayPlan extends DataEntity<WsxdRepayPlan> {
	
	private static final long serialVersionUID = 1L;
	private Date loanPmtDueDate;		// 到期日
	private String currentTerm;		// 期数
	private String loanTermPrin;		// 本金
	private String loanTermInt;		// 利息
	private String loanTermFee;		// 手续费
	private String accountFee;		// 服务费
	private String overdueLateFee;		// 违约金
	private String overduePenalty;		// 罚息
	private String overdueCompound;		// 复利
	private String principal;		// 已还本金
	private String interest;		// 已还利息
	private String fee;		// 已还手续费
	private String payAccountFee;		// 已还服务费
	private String lateFee;		// 已还滞纳金
	private String penalty;		// 已还罚息
	private String compound;		// 已还复利
	private String appNo;		// 合同编号
	private String loanBillNo;		// 借据编号
	private Date presentDate;		// 当前日期
	
	public WsxdRepayPlan() {
		super();
	}

	public WsxdRepayPlan(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoanPmtDueDate() {
		return loanPmtDueDate;
	}

	public void setLoanPmtDueDate(Date loanPmtDueDate) {
		this.loanPmtDueDate = loanPmtDueDate;
	}
	
	@Length(min=0, max=11, message="期数长度必须介于 0 和 11 之间")
	public String getCurrentTerm() {
		return currentTerm;
	}

	public void setCurrentTerm(String currentTerm) {
		this.currentTerm = currentTerm;
	}
	
	public String getLoanTermPrin() {
		return loanTermPrin;
	}

	public void setLoanTermPrin(String loanTermPrin) {
		this.loanTermPrin = loanTermPrin;
	}
	
	public String getLoanTermInt() {
		return loanTermInt;
	}

	public void setLoanTermInt(String loanTermInt) {
		this.loanTermInt = loanTermInt;
	}
	
	public String getLoanTermFee() {
		return loanTermFee;
	}

	public void setLoanTermFee(String loanTermFee) {
		this.loanTermFee = loanTermFee;
	}
	
	public String getAccountFee() {
		return accountFee;
	}

	public void setAccountFee(String accountFee) {
		this.accountFee = accountFee;
	}
	
	public String getOverdueLateFee() {
		return overdueLateFee;
	}

	public void setOverdueLateFee(String overdueLateFee) {
		this.overdueLateFee = overdueLateFee;
	}
	
	public String getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(String overduePenalty) {
		this.overduePenalty = overduePenalty;
	}
	
	public String getOverdueCompound() {
		return overdueCompound;
	}

	public void setOverdueCompound(String overdueCompound) {
		this.overdueCompound = overdueCompound;
	}
	
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
	
	public String getPayAccountFee() {
		return payAccountFee;
	}

	public void setPayAccountFee(String payAccountFee) {
		this.payAccountFee = payAccountFee;
	}
	
	public String getLateFee() {
		return lateFee;
	}

	public void setLateFee(String lateFee) {
		this.lateFee = lateFee;
	}
	
	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
	
	public String getCompound() {
		return compound;
	}

	public void setCompound(String compound) {
		this.compound = compound;
	}
	
	@Length(min=1, max=64, message="合同编号长度必须介于 1 和 64 之间")
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	@Length(min=1, max=64, message="借据编号长度必须介于 1 和 64 之间")
	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPresentDate() {
		return presentDate;
	}

	public void setPresentDate(Date presentDate) {
		this.presentDate = presentDate;
	}
	
}