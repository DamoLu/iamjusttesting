package com.plcs.web.wsxd.queryroute.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LoanDetailResult {

    //请求结果状态(成功/失败)
    private String requestStauts;

    //返回消息
    private String respMsg;

    //欠款总金额
    private BigDecimal overdueAmt;

    //是否支持点扣
    private boolean supportManualDeduction=false;

    //是否支持部分扣款
    private boolean supportPartDeduction=true;

    //可点扣金额
    private BigDecimal deductionAmount;

    public  enum statusEnum{
        SUCC("S"),
        FAIL("F");

        private final String value;

        statusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }


    /**
     * 创新是否支持部分扣款返回码值映射
     * @return
     */
    public static Map getCXCodeMap() {
        Map cxCodeMap=new HashMap();
        cxCodeMap.put("Y",true);
        cxCodeMap.put("N",false);
        return cxCodeMap;
    }

    public String getRequestStauts() {
        return requestStauts;
    }

    public void setRequestStauts(String requestStauts) {
        this.requestStauts = requestStauts;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public BigDecimal getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(BigDecimal overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public boolean isSupportManualDeduction() {
        return supportManualDeduction;
    }

    public void setSupportManualDeduction(boolean supportManualDeduction) {
        this.supportManualDeduction = supportManualDeduction;
    }

    public boolean isSupportPartDeduction() {
        return supportPartDeduction;
    }

    public void setSupportPartDeduction(boolean supportPartDeduction) {
        this.supportPartDeduction = supportPartDeduction;
    }

    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }


    @Override
    public String toString() {
        return "LoanDetailResult{" +
                "requestStauts='" + requestStauts + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", overdueAmt=" + overdueAmt +
                ", supportManualDeduction=" + supportManualDeduction +
                ", supportPartDeduction=" + supportPartDeduction +
                ", deductionAmount=" + deductionAmount +
                '}';
    }
}
