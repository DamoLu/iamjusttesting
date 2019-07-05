package com.plcs.web.wsxd.smsplatform.smssend.entity;

public class PageParam {

	private Long pageStart = 1L;
	private Long pageEnd = 1L;
	private Long pageSize = 1L;

	public Long getPageStart() {
		return pageStart;
	}

	public void setPageStart(Long pageStart) {
		this.pageStart = pageStart;
	}

	public Long getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(Long pageEnd) {
		this.pageEnd = pageEnd;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageParam [pageStart=" + pageStart + ", pageEnd=" + pageEnd + ", pageSize=" + pageSize + "]";
	}

}
