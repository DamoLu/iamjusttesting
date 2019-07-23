package com.plcs.web.wsxd.interfaces.chuangxin.dto.common;


public  class ResponseData {

    private  String sessionId;

    //    业务响应码
    private String  code;

    //    业务响应消息
    private String  message;

//    业务状态
    private String  success;



    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
