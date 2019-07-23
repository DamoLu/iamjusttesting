package com.plcs.web.wsxd.interfaces.UFS.enitty;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ResponseData {

    private String appNo;
    private String custName;
    private String loanNo;

//    应还总额，当前客户应还总金额（元）
    private BigDecimal psTotalAmt;



//    网商待扣收收益，还未扣成功部分的收益(网商待扣金额)
    private BigDecimal wsPendBenifit;

//    扣收状态: (00 :未处理,01：扣收失败,02：扣收中,03：扣收成功)
//    如果状态不为扣收中，则可以发起扣收，扣收金额不超过上限以待扣收金额。如果待扣收金额为0，则已经全部扣收。
    private String status;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date tradeTime;


    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public BigDecimal getPsTotalAmt() {
        return psTotalAmt;
    }

    public void setPsTotalAmt(BigDecimal psTotalAmt) {
        this.psTotalAmt = psTotalAmt;
    }


    public BigDecimal getWsPendBenifit() {
        return wsPendBenifit;
    }

    public void setWsPendBenifit(BigDecimal wsPendBenifit) {
        this.wsPendBenifit = wsPendBenifit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }
}
