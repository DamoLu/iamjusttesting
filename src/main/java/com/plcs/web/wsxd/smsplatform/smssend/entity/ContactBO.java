package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class ContactBO {

	@ApiModelProperty(value = "联系人姓名")
	private String name;
	@ApiModelProperty(value = "联系人关系")
	private String relation;
	@ApiModelProperty(value = "联系人手机号")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ContactBO [name=" + name + ", relation=" + relation + ", phone=" + phone + "]";
	}

}
