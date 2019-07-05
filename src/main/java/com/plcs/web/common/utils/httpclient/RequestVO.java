package com.plcs.web.common.utils.httpclient;

import com.mysql.jdbc.StringUtils;

import java.util.Map;

public class RequestVO {
	private String txnSsn;
	private String txnTime;
	private String txnNum;
	private String sign;
	private String signType;
	private String version;
	private String reqSysId;
	private String desSysId;
	private String txnType;
	private String tradeId;
	private Map<String,String> data;

	public RequestVO() {
	}

	public RequestVO(Map<String, String> data) {
		this.data = data;
	}

	public String getTxnSsn() {
		return txnSsn;
	}

	public void setTxnSsn(String txnSsn) {
		this.txnSsn = txnSsn;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getTxnNum() {
		return txnNum;
	}

	public void setTxnNum(String txnNum) {
		this.txnNum = txnNum;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReqSysId() {
		return reqSysId;
	}

	public void setReqSysId(String reqSysId) {
		this.reqSysId = reqSysId;
	}

	public String getDesSysId() {
		return desSysId;
	}

	public void setDesSysId(String desSysId) {
		this.desSysId = desSysId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public void setHeader(Map<String, String> header){
		if(null != header){
			if(header.containsKey("txnSsn") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("txnSsn"))){
				this.txnSsn = header.get("txnSsn");
			}

			if(header.containsKey("txnTime") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("txnTime"))){
				this.txnTime = header.get("txnTime");
			}
			if(header.containsKey("txnNum") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("txnNum"))){
				this.txnNum = header.get("txnNum");
			}
			if(header.containsKey("sign") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("sign"))){
				this.sign = header.get("sign");
			}
			if(header.containsKey("signType") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("signType"))){
				this.signType = header.get("signType");
			}
			if(header.containsKey("version") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("version"))){
				this.version = header.get("version");
			}
			if(header.containsKey("reqSysId") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("reqSysId"))){
				this.reqSysId = header.get("reqSysId");
			}
			if(header.containsKey("desSysId") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("desSysId"))){
				this.desSysId = header.get("desSysId");
			}
			if(header.containsKey("txnType") &&
					!StringUtils.isEmptyOrWhitespaceOnly(header.get("txnType"))){
				this.txnType = header.get("txnType");
			}

		}
	}
}
