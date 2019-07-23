package com.plcs.web.wsxd.interfaces.UFS.enitty;

import com.alibaba.fastjson.annotation.JSONField;

public class ResponseContainer {


    //    返回码
    private String  respCode;

    //  返回信息
    private  String respMsg;


    @JSONField(name = "data")
    private ResponseData data;



}
