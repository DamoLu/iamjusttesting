package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class AddRemindRecordDTO {

	@ApiModelProperty(value = "借据号 必填")
	private String loanBillNo;

	@ApiModelProperty(value = "案件状态 必填")
	private String caseStatus;

	@ApiModelProperty(value = "联系号码 必填")
	private String phone;

	@ApiModelProperty(value = "号码状态 必填  有效电话:1 停机:2 占线:3 不在服务区:4 拒接:5")
	/** {@link PhoneStatusType} */
	private String phoneStatus;

	@ApiModelProperty(value = "措施 必填")
	private String measure;

	@ApiModelProperty(value = "提醒日期 yyyy-MM-dd 若空 默认为今天")
	private Date remindDate;

	@ApiModelProperty(value = "承诺还款日期 yyyy-MM-dd")
	private Date promiseDate;

	@ApiModelProperty(value = "承诺还款金额")
	private BigDecimal promiseAmt;

	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}

	public Date getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(Date promiseDate) {
		this.promiseDate = promiseDate;
	}

	public BigDecimal getPromiseAmt() {
		return promiseAmt;
	}

	public void setPromiseAmt(BigDecimal promiseAmt) {
		this.promiseAmt = promiseAmt;
	}

	@Override
	public String toString() {
		return "AddRemindRecordDTO [loanBillNo=" + loanBillNo + ", caseStatus=" + caseStatus + ", phone=" + phone
				+ ", phoneStatus=" + phoneStatus + ", measure=" + measure + ", remindDate=" + remindDate
				+ ", promiseDate=" + promiseDate + ", promiseAmt=" + promiseAmt + "]";
	}

}
