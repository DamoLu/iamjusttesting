package com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luqihua
 * @title: QueryDeductHstContainerDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/23
 */
public class QueryDeductHstContainerDTO {
    @JSONField(name = "loanSerialNo")
    private String loanSerialNo;

    public String getLoanSerialNo() {
        return loanSerialNo;
    }

    public void setLoanSerialNo(String loanSerialNo) {
        this.loanSerialNo = loanSerialNo;
    }
}
