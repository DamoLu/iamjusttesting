package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class GetContactsDTO {

	@ApiModelProperty(value = "客户手机号")
	private String customerPhone;

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	@Override
	public String toString() {
		return "GetContactsDTO [customerPhone=" + customerPhone + "]";
	}

}
