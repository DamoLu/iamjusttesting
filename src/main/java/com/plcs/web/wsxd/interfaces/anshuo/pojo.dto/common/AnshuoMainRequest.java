package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luqihua
 * @title: AnshuoMainRequest
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/17
 */
public abstract class AnshuoMainRequest {
    @JSONField(name = "ResourceType")
    protected String resourceType = "12004"; //系统来源
    @JSONField(name = "Charset")
    protected String charset = "UTF-8";
    @JSONField(name = "InterfaceNo")
    protected String interfaceNo; //接口号
    @JSONField(name = "BusinessSerialNo")
    protected String businessSerialNo; //唯一申请编号
    @JSONField(name = "MessageInfo")
    protected String messageInfo;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getInterfaceNo() {
        return interfaceNo;
    }

    public void setInterfaceNo(String interfaceNo) {
        this.interfaceNo = interfaceNo;
    }

    public String getBusinessSerialNo() {
        return businessSerialNo;
    }

    public void setBusinessSerialNo(String businessSerialNo) {
        this.businessSerialNo = businessSerialNo;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }
}
