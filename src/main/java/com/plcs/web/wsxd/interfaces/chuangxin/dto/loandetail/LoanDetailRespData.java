package com.plcs.web.wsxd.interfaces.chuangxin.dto.loandetail;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.common.ResponseData;

public class LoanDetailRespData extends ResponseData {

    @JSONField(name = "respObj")
    private LoanDetailRespObj respObj;


    public LoanDetailRespObj getRespObj() {
        return respObj;
    }

    public void setRespObj(LoanDetailRespObj respObj) {
        this.respObj = respObj;
    }
}
