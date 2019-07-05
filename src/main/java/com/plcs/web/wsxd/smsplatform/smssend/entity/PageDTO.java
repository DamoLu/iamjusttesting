package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class PageDTO {

	@ApiModelProperty(value = "当前页码")
	private Long pageNo = 1L;
	@ApiModelProperty(value = "每页条数")
	private Long pageSize = 10L;

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageDTO [pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}

}
