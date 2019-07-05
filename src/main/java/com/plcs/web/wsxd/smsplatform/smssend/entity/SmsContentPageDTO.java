package com.plcs.web.wsxd.smsplatform.smssend.entity;
 
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SmsContentPageDTO extends PageDTO {

	@ApiModelProperty(value = "发送状态")
	private String status;
	@ApiModelProperty(value = "客户手机号")
	private String phone;
	@ApiModelProperty(value = "客户联系人手机号")
	private List<String> phoneList;
	@ApiModelProperty(value = "开始日期(可空)")
	private Date startDate;
	@ApiModelProperty(value = "结束日期(可空)")
	private Date endDate;


	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
