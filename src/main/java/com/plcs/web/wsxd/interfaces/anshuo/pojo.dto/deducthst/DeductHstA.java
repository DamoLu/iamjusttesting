package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author luqihua
 * @title: DeductHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/16
 */
public class DeductHstA{

    @JSONField(name = "ActualPayAmt")
    private String actualPayAmt; //实还总金额/交易金额
    @JSONField(name = "ActualPayPrincipalAmt")
    private String actualPayPrincipalAmt; //实还本金
    @JSONField(name = "ActualPayInterestAmt")
    private String actualPayInterestAmt; //实还利息
    @JSONField(name = "PayType")
    private String payType; //还款方式
    @JSONField(name = "PayTime")
    private String txnTime;

    public String getActualPayAmt() {
        return actualPayAmt;
    }

    public void setActualPayAmt(String actualPayAmt) {
        this.actualPayAmt = actualPayAmt;
    }

    public String getActualPayPrincipalAmt() {
        return actualPayPrincipalAmt;
    }

    public void setActualPayPrincipalAmt(String actualPayPrincipalAmt) {
        this.actualPayPrincipalAmt = actualPayPrincipalAmt;
    }

    public String getActualPayInterestAmt() {
        return actualPayInterestAmt;
    }

    public void setActualPayInterestAmt(String actualPayInterestAmt) {
        this.actualPayInterestAmt = actualPayInterestAmt;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }
}
