package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class DeductRequestContent  {

//    合同号
    @JSONField(name="CONTR_NBR")
    private  String contractNo;

//    还款金额
    @JSONField(name="AMOUNT")
    private BigDecimal amount;

//  是否业务验证
    @JSONField(name="VALIBIZ")
    private  String valide="N";

//    手机号
    @JSONField(name="MOBILE")
    private  String mobile;

//    业务验证类型
//    None - 不启用该校验字段（默认值）
//    Overdue - 校验逾期
//    Amount - 校验金额
    @JSONField(name="VALIDATE_TYPE")
    private  String valideType;


    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getValideType() {
        return valideType;
    }

    public void setValideType(String valideType) {
        this.valideType = valideType;
    }
}
