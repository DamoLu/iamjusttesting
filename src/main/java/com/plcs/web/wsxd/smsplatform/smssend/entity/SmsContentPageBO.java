package com.plcs.web.wsxd.smsplatform.smssend.entity;

public class SmsContentPageBO extends SmsContentPageDTO {

	private Long pageStart;
	private Long pageEnd;

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

	@Override
	public String toString() {
		return "SmsContentPageBO [pageStart=" + pageStart + ", pageEnd=" + pageEnd + "]";
	}

}
