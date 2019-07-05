package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import java.util.Date;

public class PADImportRemindRecordDataDTO {

	private String loanBillNo;
	private Date createTime;
	private String status;
	private Date promiseDate;
	private String promiseAmt;
	private String phone;
	private String phoneStatus;
	private Date remindDate;
	private String measure;
	private String odv;
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(Date promiseDate) {
		this.promiseDate = promiseDate;
	}

	public String getPromiseAmt() {
		return promiseAmt;
	}

	public void setPromiseAmt(String promiseAmt) {
		this.promiseAmt = promiseAmt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getOdv() {
		return odv;
	}

	public void setOdv(String odv) {
		this.odv = odv;
	}

	@Override
	public String toString() {
		return "PADImportRemindRecordDataDTO [loanBillNo=" + loanBillNo + ", createTime=" + createTime + ", status="
				+ status + ", promiseDate=" + promiseDate + ", promiseAmt=" + promiseAmt + ", phone=" + phone
				+ ", phoneStatus=" + phoneStatus + ", remindDate=" + remindDate + ", measure=" + measure + ", odv="
				+ odv + ", source=" + source + "]";
	}

}
