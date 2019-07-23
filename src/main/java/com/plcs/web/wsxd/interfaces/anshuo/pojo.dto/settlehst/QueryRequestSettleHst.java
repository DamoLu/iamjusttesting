package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luqihua
 * @title: QueryRequestSettleHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/16
 */
public class QueryRequestSettleHst {

    @JSONField(name = "LoanSerialNo")
    private String loanSerialNo;   //借据号  Y
    @JSONField(name = "TransFlag")
    private String transFlag;  // 01:还款申请,02:还款试算； Y
    @JSONField(name = "TransDate")
    private String transDate; //提前还款试算日期
    @JSONField(name = "PrePayType")
    private String prePayType;  //1:部分提前还款-期限不变; 3:全部提前还款  Y
    @JSONField(name = "PrePayAmtFlag")
    private String prePayAmtFlag;  // 1:本金,2:本金+利息
    @JSONField(name = "PrePayAmt")
    private String prePayAmt; //提前还款金额
    @JSONField(name = "PayAccountNo")
    private String payAccountNo; //还款卡号
    @JSONField(name = "PrePaySerialNo")
    private String prePaySerialNo; //外围系统唯一交易流水号
    @JSONField(name = "Remark")
    private String remark;  //摘要

    public String getLoanSerialNo() {
        return loanSerialNo;
    }

    public void setLoanSerialNo(String loanSerialNo) {
        this.loanSerialNo = loanSerialNo;
    }

    public String getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(String transFlag) {
        this.transFlag = transFlag;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getPrePayType() {
        return prePayType;
    }

    public void setPrePayType(String prePayType) {
        this.prePayType = prePayType;
    }

    public String getPrePayAmtFlag() {
        return prePayAmtFlag;
    }

    public void setPrePayAmtFlag(String prePayAmtFlag) {
        this.prePayAmtFlag = prePayAmtFlag;
    }

    public String getPrePayAmt() {
        return prePayAmt;
    }

    public void setPrePayAmt(String prePayAmt) {
        this.prePayAmt = prePayAmt;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public void setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
    }

    public String getPrePaySerialNo() {
        return prePaySerialNo;
    }

    public void setPrePaySerialNo(String prePaySerialNo) {
        this.prePaySerialNo = prePaySerialNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
