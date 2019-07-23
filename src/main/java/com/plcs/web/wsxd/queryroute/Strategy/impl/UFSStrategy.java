package com.plcs.web.wsxd.queryroute.Strategy.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.interfaces.UFS.enitty.ResponseData;
import com.plcs.web.wsxd.interfaces.UFS.service.UFSService;
import com.plcs.web.wsxd.queryroute.Strategy.RequestStrategy;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 资金前置
 * 用于查询资金方为华安和新网的案件
 */
@Component
public class UFSStrategy implements RequestStrategy {


    @Autowired
    private UFSService ufsService;

    @Autowired
    private WsxdCaseService wsxdCaseService;


    private static final Logger logger = LoggerFactory.getLogger(UFSStrategy.class);


    @Override
    public LoanDetailResult loanDeatilQuery(String loanBillNo) {
        WsxdCase wsxdCase = wsxdCaseService.getByLoanBillNo(loanBillNo);
        JSONObject jsonObject = ufsService.loanDetailQuery(wsxdCase);
        LoanDetailResult result = new LoanDetailResult();
        if (jsonObject != null) {
            logger.info("请求响应结果：{}", jsonObject.toJSONString());
            result.setRequestStauts(LoanDetailResult.statusEnum.SUCC.value());


            JSONObject jsonData = jsonObject.getJSONObject("data");
            if (jsonData == null) {
                result.setRespMsg("未查询到该笔借据的贷款详情！");
                return result;
            }
            JSONArray loanArray = jsonData.getJSONArray("loanList");
            if (loanArray == null) {
                String respMsg = jsonObject.getString("respMsg");
                result.setRespMsg(respMsg);
                return result;
            }
            List<ResponseData> loanList = JSONObject.parseArray(loanArray.toJSONString(), ResponseData.class);

            result.setSupportManualDeduction(true);
            ResponseData responseData = getLatestDetail(loanList, loanBillNo);
            if (responseData == null) {
                result.setRespMsg("未查询到该笔借据的贷款详情！");
                return result;
            }
            result.setOverdueAmt(responseData.getPsTotalAmt());
//          可点扣金额 = 网商待扣收收益，还未扣成功部分的收益(网商待扣金额)
            result.setDeductionAmount(responseData.getWsPendBenifit());
        } else {
            result.setRequestStauts(LoanDetailResult.statusEnum.FAIL.value());
            result.setRespMsg("请求发送失败！");
        }
        return result;
    }

    @Override
    public DeductResult LanuchDeduction(String loanBillNo, BigDecimal amount, BigDecimal maxDeductionAmount) {
        WsxdCase wsxdCase = wsxdCaseService.getByLoanBillNo(loanBillNo);
        JSONObject jsonObject = ufsService.LanuchDeduction(wsxdCase, amount, maxDeductionAmount);
        DeductResult result = new DeductResult();
        if (jsonObject != null) {
            logger.info("请求响应结果：{}", jsonObject.toJSONString());
            String respCode = jsonObject.getString("respCode");
            String respMsg = jsonObject.getString("respMsg");
            result.setCode(respCode);
            if (DeductResult.SuccResponseEnum.UFS_SUCC.getCode().equals(respCode)) {
                result.setDesc("扣款请求发送成功");
            } else {
                result.setDesc(respMsg);
            }
        } else {
            result.setDesc("扣款请求发送失败！");
        }
        logger.info("扣款请求结果:{}", result);
        return result;
    }


    /**
     * 查找借据号为loanBillNo的最新贷款详情
     * tradeTime作为比较字段
     */
    private ResponseData getLatestDetail(List<ResponseData> loanList, String loanBillNo) {
        ResponseData maxObject = null;
        for (ResponseData responseData : loanList) {
            if (loanBillNo.equals(responseData.getLoanNo())) {
                if (maxObject == null) {
                    maxObject = responseData;
                }
                if (responseData.getTradeTime().after(maxObject.getTradeTime())) {
                    maxObject = responseData;
                }
            }
        }
        return maxObject;
    }


}
