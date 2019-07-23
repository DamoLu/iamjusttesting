package com.plcs.web.wsxd.queryroute.Strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.common.ResponseObject;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.loandetail.LoanDetail;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.loandetail.LoanDetailRespData;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.loandetail.LoanDetailRespObj;
import com.plcs.web.wsxd.interfaces.chuangxin.service.ChuangXinService;
import com.plcs.web.wsxd.queryroute.Strategy.RequestStrategy;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CXStrategy implements RequestStrategy {

    @Autowired
    private ChuangXinService chuangXinService;

    @Override
    public LoanDetailResult loanDeatilQuery(String loanBillNo) {
        LoanDetailResult result=new LoanDetailResult();
        JSONObject jsonObject =chuangXinService.loanDetailQuery(loanBillNo);
        if(jsonObject==null){
            result.setRequestStauts(LoanDetailResult.statusEnum.FAIL.value());
            result.setRespMsg("请求发送失败！");
            return result;
        }
        LoanDetail loanDetail=JSONObject.parseObject(jsonObject.toJSONString(), LoanDetail.class);
        LoanDetailRespData respData=loanDetail.getData();
        if(respData==null){
            result.setRespMsg("未查询到该笔借据的贷款详情！");
            return result;
        }
        LoanDetailRespObj respObj=respData.getRespObj();
        if(respObj==null){
            result.setRespMsg("未查询到该笔借据的贷款详情！");
            return result;
        }
        result.setOverdueAmt(respObj.getPayAmt());
//          可点扣金额
        result.setDeductionAmount(respObj.getPayAmt());
        result.setSupportManualDeduction(true);
        Boolean supportPartDeduct=(Boolean)LoanDetailResult.getCXCodeMap().get(respObj.getIsPartialDeduction().toUpperCase());
        result.setSupportPartDeduction(supportPartDeduct);
        return result;
    }

    @Override
    public DeductResult LanuchDeduction(String loanBillNo, BigDecimal amount, BigDecimal maxDeductionAmount) {
        return null;
    }
}
