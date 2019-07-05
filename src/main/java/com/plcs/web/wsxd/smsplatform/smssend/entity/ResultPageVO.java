package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class ResultPageVO extends ResultVO {

	@ApiModelProperty(value = "总条数")
	private Long resultCount = 0L;

	public Long getResultCount() {
		return resultCount;
	}

	public void setResultCount(Long resultCount) {
		this.resultCount = resultCount;
	}

	@Override
	public String toString() {
		return "ResultPageVO [resultCount=" + resultCount + "]";
	}

}
