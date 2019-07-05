package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import java.util.List;

import com.plcs.web.wsxd.smsplatform.smssend.entity.ResultVO;

import io.swagger.annotations.ApiModelProperty;

public class GetAllPhoneStatusVO extends ResultVO {

	@ApiModelProperty(value = "电话状态列表")
	private List<SysDictBO> phoneStatusList;

	public List<SysDictBO> getPhoneStatusList() {
		return phoneStatusList;
	}

	public void setPhoneStatusList(List<SysDictBO> phoneStatusList) {
		this.phoneStatusList = phoneStatusList;
	}

	@Override
	public String toString() {
		return "GetAllCaseTypeVO [phoneStatusList=" + phoneStatusList + "]";
	}

}
