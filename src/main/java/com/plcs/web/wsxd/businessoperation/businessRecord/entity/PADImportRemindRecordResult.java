package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import com.alibaba.fastjson.JSONObject;

public class PADImportRemindRecordResult {

	private String respCode;
	private String respMsg;
	private JSONObject data;
	

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	@Override
	public String toString() {
		return "PADImportRemindRecordResult [respCode=" + respCode + ", respMsg=" + respMsg + ", data=" + data + "]";
	}

	public void setSuccess() {
		this.respCode = "0000000";
		this.respMsg = "成功";
	}
}
