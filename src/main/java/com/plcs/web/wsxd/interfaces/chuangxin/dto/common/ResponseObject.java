package com.plcs.web.wsxd.interfaces.chuangxin.dto.common;


import com.alibaba.fastjson.annotation.JSONField;

public  class ResponseObject {

    //    业务响应码
    @JSONField(name = "ReturnCode")
    private String  returnCode;

    //    业务响应消息
    @JSONField(name = "ReturnMessage")
    private String  returnMessage;

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
