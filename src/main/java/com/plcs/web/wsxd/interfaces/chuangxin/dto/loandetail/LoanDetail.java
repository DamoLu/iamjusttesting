package com.plcs.web.wsxd.interfaces.chuangxin.dto.loandetail;


import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.common.ResponseContainer;


public class LoanDetail  extends ResponseContainer {

    @JSONField(name = "data")
    private LoanDetailRespData data;

    public LoanDetailRespData getData() {
        return data;
    }

    public void setData(LoanDetailRespData data) {
        this.data = data;
    }
}
