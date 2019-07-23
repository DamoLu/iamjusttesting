package com.plcs.web.wsxd.interfaces.chuangxin.dto.loandetail;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.common.ResponseObject;

import java.math.BigDecimal;
import java.util.List;

public class LoanDetailRespObj extends ResponseObject {

    //    借据号
    private String loanSerialNo;

    //    应还款额
    private BigDecimal payAmt;

    //    是否支持部分扣款
    private String isPartialDeduction;

    public String getLoanSerialNo() {
        return loanSerialNo;
    }

    public void setLoanSerialNo(String loanSerialNo) {
        this.loanSerialNo = loanSerialNo;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public String getIsPartialDeduction() {
        return isPartialDeduction;
    }

    public void setIsPartialDeduction(String isPartialDeduction) {
        this.isPartialDeduction = isPartialDeduction;
    }
}
