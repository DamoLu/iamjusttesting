package com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author luqihua
 * @title: DeductHstC
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/23
 */
public class DeductHstC {
    @JSONField(name = "payNo")
    private String payNo;
    @JSONField(name = "payTime")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date payTime;
    @JSONField(name = "payAmt")
    private String payAmt;
    @JSONField(name = "loanSerialNo")
    private String loanSerialNo;
    @JSONField(name = "contractSerialNo")
    private String contractSerialNo;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(String payAmt) {
        this.payAmt = payAmt;
    }

    public String getLoanSerialNo() {
        return loanSerialNo;
    }

    public void setLoanSerialNo(String loanSerialNo) {
        this.loanSerialNo = loanSerialNo;
    }

    public String getContractSerialNo() {
        return contractSerialNo;
    }

    public void setContractSerialNo(String contractSerialNo) {
        this.contractSerialNo = contractSerialNo;
    }
}
