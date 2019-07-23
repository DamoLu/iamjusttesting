package com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author luqihua
 * @title: DeductHstResponse
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/23
 */
public class DeductHstResponse {
    @JSONField(name = "respCode")
    private String respCode;
    @JSONField(name = "respMsg")
    private String respMsg;
    private List<DeductHstC> deductHstCList;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public List<DeductHstC> getDeductHstCList() {
        return deductHstCList;
    }

    public void setDeductHstCList(List<DeductHstC> deductHstCList) {
        this.deductHstCList = deductHstCList;
    }
}
