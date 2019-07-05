package com.plcs.web.wsxd.businessoperation.businessoperation.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RepayHstVO {
    private String loanBillNo;		// 借据号
    private Date repayDate;		// 还款日期
    private BigDecimal repayMoney; // 还款金额

    public String getLoanBillNo() {
        return loanBillNo;
    }

    public void setLoanBillNo(String loanBillNo) {
        this.loanBillNo = loanBillNo;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }
}
