package com.plcs.web.wsxd.smsplatform.smssend.entity;
 
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class SmsContentPageDTO extends PageDTO {

	@ApiModelProperty(value = "发送状态")
	private Integer status;
	@ApiModelProperty(value = "客户手机号")
	private String phone;
	@ApiModelProperty(value = "客户联系人手机号")
	private List<String> phoneList;
	@ApiModelProperty(value = "开始日期(可空)")
	private Date startDate;
	@ApiModelProperty(value = "结束日期(可空)")
	private Date endDate;
	@ApiModelProperty(value = "客户借据号")
	private String loanBillNo;

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "SmsContentPageDTO [status=" + status + ", phone=" + phone + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

}
