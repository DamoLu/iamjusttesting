package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luqihua
 * @title: YinShuServerResponse
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/10
 */
public class YinShuServerResponse {

    @JSONField(name = "STATUS")
    private String status;
    @JSONField(name = "CODE")
    private String code;
    @JSONField(name = "DESC")
    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
