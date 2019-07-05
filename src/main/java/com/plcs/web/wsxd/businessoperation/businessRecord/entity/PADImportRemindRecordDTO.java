package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

public class PADImportRemindRecordDTO {

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
	private PADImportRemindRecordDataDTO data;

	public PADImportRemindRecordDataDTO getData() {
		return data;
	}

	public void setData(PADImportRemindRecordDataDTO data) {
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

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	@Override
	public String toString() {
		return "PADImportRemindRecordDTO [txnSsn=" + txnSsn + ", txnTime=" + txnTime + ", txnNum=" + txnNum + ", sign="
				+ sign + ", signType=" + signType + ", version=" + version + ", reqSysId=" + reqSysId + ", desSysId="
				+ desSysId + ", txnType=" + txnType + ", tradeId=" + tradeId + ", data=" + data + "]";
	}

}
