package com.plcs.web.wsxd.interfaces.UFS.enitty;

import java.math.BigDecimal;

public class DeductRequestData extends RequestData{

    //  申请件编号
    private  String applCde;

    //  扣款流水号，不为空且不重复，此流水号可用于查询扣款结果
    private  String deductId;

//    扣款金额
    private BigDecimal totalAmount;

    public String getApplCde() {
        return applCde;
    }

    public void setApplCde(String applCde) {
        this.applCde = applCde;
    }

    public String getDeductId() {
        return deductId;
    }

    public void setDeductId(String deductId) {
        this.deductId = deductId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
