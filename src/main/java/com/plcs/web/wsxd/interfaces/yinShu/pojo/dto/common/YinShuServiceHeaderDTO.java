package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class YinShuServiceHeaderDTO {

	@JSONField(name = "SERVICE_ID")
	protected String serviceId;

	@JSONField(name = "ORG")
	protected String org = "9982";

	@JSONField(name = "CHANNEL_ID")
	protected String channelId = "BANK";

	@JSONField(name = "ACQ_ID")
	protected String acqId = "10000000";

	@JSONField(name = "SUB_TERMINAL_TYPE")
	protected String subTerminalType = "Android";

	@JSONField(name = "SERVICESN")
	protected String serviceSN = "APP20190626171607979775";

	@JSONField(name = "REQUEST_TIME")
	protected String requestTime = "20190626171607";

	@JSONField(name = "VERSION_ID")
	protected String versionId = "01";

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAcqId() {
		return acqId;
	}

	public void setAcqId(String acqId) {
		this.acqId = acqId;
	}

	public String getSubTerminalType() {
		return subTerminalType;
	}

	public void setSubTerminalType(String subTerminalType) {
		this.subTerminalType = subTerminalType;
	}

	public String getServiceSN() {
		return serviceSN;
	}

	public void setServiceSN(String serviceSN) {
		this.serviceSN = serviceSN;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	@Override
	public String toString() {
		return "YinShuServiceHeaderDTO [serviceId=" + serviceId + ", org=" + org + ", channelId=" + channelId
				+ ", acqId=" + acqId + ", subTerminalType=" + subTerminalType + ", serviceSN=" + serviceSN
				+ ", requestTime=" + requestTime + ", versionId=" + versionId + "]";
	}

}
