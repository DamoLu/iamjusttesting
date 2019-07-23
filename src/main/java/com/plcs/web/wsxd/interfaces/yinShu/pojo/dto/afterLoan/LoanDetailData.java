package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * 贷款业务详情
 */

public class LoanDetailData {

    //    合同号
    @JSONField(name = "CONTR_NBR")
    private String contractNo;

    //    借据号
    @JSONField(name = "DUE_BILL_NO")
    private String loanBillNo;

    //    当期应还欠款(最大可点扣金额)
    @JSONField(name = "CURR_DUE_AMT")
    private BigDecimal currdueAmt;

    //    客户名称
    @JSONField(name = "CUST_NAME")
    private String custName;

    //    逾期欠款总额
    @JSONField(name = "OVERDUE_AMT")
    private BigDecimal overdueAmt;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLoanBillNo() {
        return loanBillNo;
    }

    public void setLoanBillNo(String loanBillNo) {
        this.loanBillNo = loanBillNo;
    }

    public BigDecimal getCurrdueAmt() {
        return currdueAmt;
    }

    public void setCurrdueAmt(BigDecimal currdueAmt) {
        this.currdueAmt = currdueAmt;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public BigDecimal getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(BigDecimal overdueAmt) {
        this.overdueAmt = overdueAmt;
    }
}
