package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WsxdRemindRecordBO {
	private String id;

	private String loanBillNo;

	private Date createTime;

	private String status;

	private Date promiseDate;

	private BigDecimal promiseAmt;

	private String phone;

	private String phoneStatus;

	private Date remindDate;

	private String isRemind;

	private String recording;

	private String odv;

	private String odvGroup;

	private Date createDate;

	private String createBy;

	private String updateBy;

	private Date updateDate;

	private String remarks;

	private String delFlag;

	private String measure;

	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo == null ? null : loanBillNo.trim();
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
		this.status = status == null ? null : status.trim();
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus == null ? null : phoneStatus.trim();
	}

	public Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}

	public String getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(String isRemind) {
		this.isRemind = isRemind == null ? null : isRemind.trim();
	}

	public String getRecording() {
		return recording;
	}

	public void setRecording(String recording) {
		this.recording = recording == null ? null : recording.trim();
	}

	public String getOdv() {
		return odv;
	}

	public void setOdv(String odv) {
		this.odv = odv == null ? null : odv.trim();
	}

	public String getOdvGroup() {
		return odvGroup;
	}

	public void setOdvGroup(String odvGroup) {
		this.odvGroup = odvGroup == null ? null : odvGroup.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure == null ? null : measure.trim();
	}

}