package com.plcs.web.wsxd.businessoperation.businessoperation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.plcs.web.common.persistence.DataEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 案件催记Entity
 * @author tanweijian
 * @version 2019-06-11
 */
public class WsxdRemindRecord extends DataEntity<WsxdRemindRecord> {
	
	private static final long serialVersionUID = 1L;
	private String loanBillNo;		// 借据号
	private String phone;			// 电话号码
	private String status;			// 案件状态
	private String measure;			// 措施
	private Date remindDate;		// 提醒日期
	private String isRemind;		// 是否提醒 0:是 1:否
	private Date promiseDate;		// 承诺还款时间
	private String promiseAmt;		// 承诺还款金额
	private String odv;				// 操作员
	private Date createTime;		// 添加时间
	private String odvGroup;		// 催收组
	private String phoneStatus;		// 电话状态
	private String recording;		// 录音ID
	private String source;			// 来源
	
	public WsxdRemindRecord() {
		super();
	}

	public WsxdRemindRecord(String id){
		super(id);
	}

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getRecording() {
		return recording;
	}

	public void setRecording(String recording) {
		this.recording = recording;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Length(min=1, max=64, message="借据号长度必须介于 1 和 64 之间")
	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}
	
	@Length(min=1, max=16, message="电话号码长度必须介于 1 和 16 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=64, message="案件状态长度必须介于 1 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}
	
	@Length(min=1, max=1, message="是否提醒 0:是 1:否长度必须介于 1 和 1 之间")
	public String getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(String isRemind) {
		this.isRemind = isRemind;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	
	@Length(min=1, max=32, message="操作员长度必须介于 1 和 32 之间")
	public String getOdv() {
		return odv;
	}

	public void setOdv(String odv) {
		this.odv = odv;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=32, message="催收组长度必须介于 0 和 32 之间")
	public String getOdvGroup() {
		return odvGroup;
	}

	public void setOdvGroup(String odvGroup) {
		this.odvGroup = odvGroup;
	}
	
}