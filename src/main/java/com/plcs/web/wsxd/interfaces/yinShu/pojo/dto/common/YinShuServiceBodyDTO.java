package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common;

public abstract class YinShuServiceBodyDTO {

	protected YinShuMainDTO request;

	public YinShuMainDTO getRequest() {
		return request;
	}

	public void setRequest(YinShuMainDTO request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "YinShuServiceBodyCommon [request=" + request + "]";
	}

}
