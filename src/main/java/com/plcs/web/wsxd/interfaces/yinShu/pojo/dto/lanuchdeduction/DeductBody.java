package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction;

import com.alibaba.fastjson.annotation.JSONField;

public class DeductBody {

    @JSONField(name = "REQUEST")
    private DeductRequestContent request;

    public DeductRequestContent getRequest() {
        return request;
    }

    public void setRequest(DeductRequestContent request) {
        this.request = request;
    }
}
