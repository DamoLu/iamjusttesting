package com.plcs.web.wsxd.smsplatform.smssend.entity;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class GetContactsVO extends ResultVO {
	
	@ApiModelProperty(value = "联系人列表")
	private List<ContactBO> contacts;

	public List<ContactBO> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactBO> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "GetContactsVO [contacts=" + contacts + "]";
	}

}
