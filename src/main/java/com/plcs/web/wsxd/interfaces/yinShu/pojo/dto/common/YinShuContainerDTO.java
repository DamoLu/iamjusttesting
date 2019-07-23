package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common;

public abstract class YinShuContainerDTO {

	protected YinShuServiceHeaderDTO serviceHeader;

	protected YinShuServiceBodyDTO serviceBody;

	public YinShuServiceHeaderDTO getServiceHeader() {
		return serviceHeader;
	}

	public void setServiceHeader(YinShuServiceHeaderDTO serviceHeader) {
		this.serviceHeader = serviceHeader;
	}

	public YinShuServiceBodyDTO getServiceBody() {
		return serviceBody;
	}

	public void setServiceBody(YinShuServiceBodyDTO serviceBody) {
		this.serviceBody = serviceBody;
	}

	@Override
	public String toString() {
		return "YinShuCommonDTO [serviceHeader=" + serviceHeader + ", serviceBody=" + serviceBody + "]";
	}

}
