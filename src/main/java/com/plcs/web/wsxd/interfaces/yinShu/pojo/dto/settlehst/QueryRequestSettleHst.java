package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuMainDTO;

/**
 * @author luqihua
 * @title: QueryRequestSettleHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/10
 */
public class QueryRequestSettleHst extends YinShuMainDTO {

    @JSONField(name = "GUARANTYID")
    private String guarantyId; //保单号
    @JSONField(name = "TYPE")
    private String type; //请求类型 1.提前还款试算  2.提前还款预约
    @JSONField(name = "CALDATE")
    private String caldate; //还款日期
    @JSONField(name = "CONTR_NBR")
    private String contrNbr; //合同编号
    @JSONField(name = "CREDIT_NO")
    private String creditNo; //借据号

    public String getGuarantyId() {
        return guarantyId;
    }

    public void setGuarantyId(String guarantyId) {
        this.guarantyId = guarantyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaldate() {
        return caldate;
    }

    public void setCaldate(String caldate) {
        this.caldate = caldate;
    }

    public String getContrNbr() {
        return contrNbr;
    }

    public void setContrNbr(String contrNbr) {
        this.contrNbr = contrNbr;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }
}
