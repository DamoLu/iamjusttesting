package com.plcs.web.wsxd.businessoperation.businessoperation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plcs.web.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款记录Entity
 * @author tanweijian
 * @version 2019-06-27
 */
public class WsxdRepayHst extends DataEntity<WsxdRepayHst> {
	
	private static final long serialVersionUID = 1L;
	private String dwId;		// 数仓ID
	private String appNo;		// 申请编号
	private String loanBillNo;		// 借据号
	private Date repayDate;		// 还款日期
	private BigDecimal principal;		// 实还本金
	private BigDecimal interest;		// 实还利息
	private BigDecimal fee;		// 实还手续费
	private BigDecimal penalty;		// 实还罚息
	private BigDecimal compound;		// 实还复利
	private BigDecimal lateFee;		// 实还滞纳金
	private BigDecimal prepayFee;		// 实还提前还款手续费
	private BigDecimal payAccountFee;		// 实还账户管理费
	
	public WsxdRepayHst() {
		super();
	}

	public WsxdRepayHst(String id){
		super(id);
	}

	@Length(min=0, max=16, message="数仓ID长度必须介于 0 和 16 之间")
	public String getDwId() {
		return dwId;
	}

	public void setDwId(String dwId) {
		this.dwId = dwId;
	}
	
	@Length(min=0, max=64, message="申请编号长度必须介于 0 和 64 之间")
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	@Length(min=1, max=64, message="借据号长度必须介于 1 和 64 之间")
	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="还款日期不能为空")
	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	
	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	
	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}
	
	public BigDecimal getCompound() {
		return compound;
	}

	public void setCompound(BigDecimal compound) {
		this.compound = compound;
	}
	
	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}
	
	public BigDecimal getPrepayFee() {
		return prepayFee;
	}

	public void setPrepayFee(BigDecimal prepayFee) {
		this.prepayFee = prepayFee;
	}
	
	public BigDecimal getPayAccountFee() {
		return payAccountFee;
	}

	public void setPayAccountFee(BigDecimal payAccountFee) {
		this.payAccountFee = payAccountFee;
	}
	
}