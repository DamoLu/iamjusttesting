package com.plcs.web.wsxd.interfaces.UFS.enitty;

import com.alibaba.fastjson.annotation.JSONField;

public class RequestContainer {

//  签名
	private  String sign="null";

    //    加密方式
    private String  signType="null";

//    交易类型
    private String  txnType="0000";

//    请求流水号 不能为空且不重复
    private String  txnSsn="20180514162652049004";

    //    交易码
    private String  txnNum;

    //    请求系统标识 10001
    private String  reqSysId="10001";

//    目标系统标识( 华安（30009）,新网（30020）)
    private String  desSysId;

    private String  version="1.0";

    // 交易时间(yyyy-MM-dd HH:mm:ss) 以实际时间为准
    private String  txnTime;

    @JSONField(name = "data")
    private RequestData data;

    public  enum desSysIdEnum{
        HUA_AN("30009"),
        XIN_WANG("30020");

        private final String value;

        desSysIdEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public  enum txnNumEnum{
        DEDUCT("TX0014"),
        LOAN_DETAIL("TX055");

        private final String value;

        txnNumEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public  enum txnTypeEnum{
        MANUAL_DEDUCT("0005","手动点扣"),
        LOAN_DETAIL("0000","手动点扣");

        private final String code;
        private final String desc;

        txnTypeEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }
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

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSsn() {
        return txnSsn;
    }

    public void setTxnSsn(String txnSsn) {
        this.txnSsn = txnSsn;
    }

    public String getTxnNum() {
        return txnNum;
    }

    public void setTxnNum(String txnNum) {
        this.txnNum = txnNum;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public RequestData getData() {
        return data;
    }

    public void setData(RequestData data) {
        this.data = data;
    }
}
