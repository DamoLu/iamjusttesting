package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import java.util.List;

import com.plcs.web.wsxd.smsplatform.smssend.entity.ResultVO;

import io.swagger.annotations.ApiModelProperty;

public class GetAllCaseTypeVO extends ResultVO {

	@ApiModelProperty(value = "案件状态列表")
	private List<SysDictBO> casyTypeList;

	public List<SysDictBO> getCasyTypeList() {
		return casyTypeList;
	}

	public void setCasyTypeList(List<SysDictBO> casyTypeList) {
		this.casyTypeList = casyTypeList;
	}

	@Override
	public String toString() {
		return "GetAllCaseTypeVO [casyTypeList=" + casyTypeList + ", getCode()=" + getCode() + ", getMsg()=" + getMsg()
				+ ", isSuccess()=" + isSuccess() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
