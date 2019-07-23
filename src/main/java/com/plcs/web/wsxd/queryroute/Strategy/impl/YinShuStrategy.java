package com.plcs.web.wsxd.queryroute.Strategy.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.deduct.entity.WsxdRealTimeDeductionHistory;
import com.plcs.web.wsxd.deduct.service.WsxdRealTimeDeductionHistoryService;
import com.plcs.web.wsxd.interfaces.UFS.enitty.ResponseData;
import com.plcs.web.wsxd.interfaces.enums.DeductStatusEnum;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.LoanDetailData;
import com.plcs.web.wsxd.queryroute.Strategy.RequestStrategy;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;
import com.plcs.web.wsxd.interfaces.yinShu.service.YinShuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Component
public class YinShuStrategy implements RequestStrategy {

    @Autowired
    private YinShuService yinShuService;

    @Autowired
    private WsxdCaseService wsxdCaseService;

    private static final Logger logger = LoggerFactory.getLogger(YinShuStrategy.class);


    @Override
    public LoanDetailResult loanDeatilQuery( String loanBillNo) {
        LoanDetailResult result = new LoanDetailResult();
        WsxdCase wsxdCase=wsxdCaseService.getByLoanBillNo(loanBillNo);
        JSONObject jsonObject = yinShuService.queryAfterLoan(wsxdCase.getManagerId(), loanBillNo);

        if (jsonObject != null) {
            logger.info("请求响应结果：{}", jsonObject.toJSONString());
            result.setRequestStauts(LoanDetailResult.statusEnum.SUCC.value());

            JSONObject serviceObject = jsonObject.getJSONObject("SERVICE");
            JSONObject serviceBodyObject = serviceObject.getJSONObject("SERVICE_BODY");
            JSONObject responseObject = serviceBodyObject.getJSONObject("RESPONSE");
            JSONArray loanArray = responseObject.getJSONArray("LOAN_LIST");
            if (loanArray == null) {
                String respMsg = serviceObject.getJSONObject("SERVICE_HEADER").getJSONObject("SERV_RESPONSE").getString("DESC");
                result.setRespMsg(respMsg);
                return result;
            }
            List<LoanDetailData> loanList=JSONObject.parseArray(loanArray.toJSONString(),LoanDetailData.class);
            LoanDetailData  loanObject=getLoanDetail(loanList,loanBillNo);
            if(loanObject==null){
                result.setRespMsg("未查询到该笔借据的贷款详情！");
                return result;
            }
            //当期应还欠款
            BigDecimal currDueAmt = loanObject.getCurrdueAmt();
            //逾期欠款总金额
            BigDecimal overdueAmt = loanObject.getOverdueAmt();
            result.setDeductionAmount(currDueAmt);
            result.setOverdueAmt(overdueAmt);
            result.setSupportManualDeduction(true);
        } else {
            result.setRequestStauts(LoanDetailResult.statusEnum.FAIL.value());
            result.setRespMsg("请求发送失败！");
        }
        return result;
    }

    @Override
    public DeductResult LanuchDeduction( String loanBillNo, BigDecimal amount, BigDecimal maxDeductionAmount) {

        DeductResult result = new DeductResult();
        WsxdCase wsxdCase=wsxdCaseService.getByLoanBillNo(loanBillNo);
        JSONObject jsonObject = yinShuService.lanuchDeduction(wsxdCase, amount,maxDeductionAmount);
        if (jsonObject != null) {
            logger.info("请求响应结果：{}", jsonObject.toJSONString());
            JSONObject serviceObject = jsonObject.getJSONObject("SERVICE");
            JSONObject headerObject = serviceObject.getJSONObject("SERVICE_HEADER");
            JSONObject responseObject = headerObject.getJSONObject("SERV_RESPONSE");
            String status = responseObject.getString("STATUS");
            String desc = responseObject.getString("DESC");
            if (DeductResult.SuccResponseEnum.YS_SUCC.getCode().equals(status)) {
                result.setDesc("扣款请求发送成功");
            } else {
                result.setDesc(desc);
            }
        } else {
            result.setDesc("扣款请求发送失败！");
        }
        logger.info("扣款请求结果:{}", result);
        return result;
    }


    public LoanDetailData getLoanDetail(List<LoanDetailData> loanList, String loanBillNo){
        LoanDetailData loanDetailData = null;
        for (LoanDetailData responseData : loanList) {
            if (loanBillNo.equals(responseData.getLoanBillNo())) {
                loanDetailData=responseData;
            }
        }
        return loanDetailData;
    }




}
