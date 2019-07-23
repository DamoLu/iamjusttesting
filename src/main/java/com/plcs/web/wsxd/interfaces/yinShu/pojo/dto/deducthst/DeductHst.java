package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author luqihua
 * @title: DeductHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/10
 */
public class DeductHst {

    @JSONField(name = "TXN_AMT")
    private String txnAmt; //交易金额
    @JSONField(name = "TXN_STATUS")
    private String txnStatus; //交易状态
    @JSONField(name = "FAILURE_MESSAGE")
    private String failureMessage; //
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    @JSONField(name = "TXN_TIME")
    private Date txnTime;
    @JSONField(name = "LOAN_USAGE")
    private String loanUsage;
    @JSONField(name = "BANK_NAME")
    private String bankName;
    @JSONField(name = "BANK_CARD_NBR")
    private String bankCardNbr;
    @JSONField(name = "PURPOSE")
    private String purpose;

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public Date getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(Date txnTime) {
        this.txnTime = txnTime;
    }

    public String getLoanUsage() {
        return loanUsage;
    }

    public void setLoanUsage(String loanUsage) {
        this.loanUsage = loanUsage;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNbr() {
        return bankCardNbr;
    }

    public void setBankCardNbr(String bankCardNbr) {
        this.bankCardNbr = bankCardNbr;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
