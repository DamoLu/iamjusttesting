package com.plcs.web.wsxd.smsplatform.smssend.entity;

public class SmsSendResultBO {

	private boolean flag = false;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "SmsSendResultBO [flag=" + flag + "]";
	}

}
