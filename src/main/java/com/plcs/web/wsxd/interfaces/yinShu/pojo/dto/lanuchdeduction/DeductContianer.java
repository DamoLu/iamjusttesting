package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction;

import com.alibaba.fastjson.annotation.JSONField;

public class DeductContianer {

    @JSONField(name = "SERVICE")
    private DeductService service;

    public DeductService getService() {
        return service;
    }

    public void setService(DeductService service) {
        this.service = service;
    }
}
