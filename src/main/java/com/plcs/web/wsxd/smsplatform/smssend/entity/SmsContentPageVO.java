package com.plcs.web.wsxd.smsplatform.smssend.entity;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SmsContentPageVO extends ResultPageVO {

	@ApiModelProperty(value = "短信列表")
	private List<SmsContentVO> smsContentList;
	

	public List<SmsContentVO> getSmsContentList() {
		return smsContentList;
	}

	public void setSmsContentList(List<SmsContentVO> smsContentList) {
		this.smsContentList = smsContentList;
	}

	@Override
	public String toString() {
		return "SmsContentPageVO [smsContentList=" + smsContentList + ", getSmsContentList()=" + getSmsContentList()
				+ ", getResultCount()=" + getResultCount() + ", toString()=" + super.toString() + ", getCode()="
				+ getCode() + ", getMsg()=" + getMsg() + ", isSuccess()=" + isSuccess() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
}
