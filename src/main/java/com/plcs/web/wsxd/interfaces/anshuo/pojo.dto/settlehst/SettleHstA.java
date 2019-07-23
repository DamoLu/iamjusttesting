package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.common.AnShuoMainResponse;

/**
 * @author luqihua
 * @title: SettleHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/16
 */
public class SettleHstA extends AnShuoMainResponse {
    @JSONField(name = "ActualPayAmt")
    private String amount; //总金额
    @JSONField(name = "PrePayInterestAmt")
    private String interest; //利息
    @JSONField(name = "PrePayPrincipalAmt")
    private String principal; //本金
    @JsonIgnore
    private String date; //当前日期

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
