package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author luqihua
 * @title: SettleHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/10
 */
public class SettleHst {

    @JSONField(name = "AMOUNT")
    private String amount; //总金额
    @JSONField(name = "PREMIUM")
    private String premium; //保费
    @JSONField(name = "POUNDAGE")
    private String poundage; //手续费
    @JSONField(name = "INTEREST")
    private String interest; //利息
    @JSONField(name = "PRINCIPAL")
    private String principal; //本金
    @JsonIgnore
    private String date; //当前日期

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
