package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luqihua
 * @title: AnShuoMainResponse
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/17
 */
public abstract class AnShuoMainResponse {
    @JSONField(name = "ReturnCode")
    private String returnCode;
    @JSONField(name = "ReturnMessage")
    private String returnMessage;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
