package com.plcs.web.wsxd.interfaces.UFS.service;


import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.constant.LoanOrgConstants;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.RandomUtil;
import com.plcs.web.common.utils.httpclient.HttpClientUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.deduct.entity.WsxdRealTimeDeductionHistory;
import com.plcs.web.wsxd.deduct.service.WsxdRealTimeDeductionHistoryService;
import com.plcs.web.wsxd.interfaces.UFS.enitty.DeductRequestData;
import com.plcs.web.wsxd.interfaces.UFS.enitty.RequestContainer;
import com.plcs.web.wsxd.interfaces.UFS.enitty.LoanDetailRequestData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Date;

@Service
public class UFSService {
//
//    /**
//     * 旧资金前置sit地址  用于查询资金方为华安的案件
//     */
//    private final static String ufsUrl="http://192.168.91.18:21004/";

    /**
     * 新资金前置sit地址  用于查询资金方为华安和新网的案件
     */
    @Value( "${ufs-sit.url}" )
    private  String ufsUrl;



    @Autowired
    private WsxdRealTimeDeductionHistoryService realTimeDeductionHistoryService;

    private static final Logger logger = LoggerFactory.getLogger(UFSService.class);



    public JSONObject loanDetailQuery(WsxdCase wsxdCase){
        RequestContainer content=new RequestContainer();
        SetDesSysId(wsxdCase.getLoanOrgin(),content);
        content.setTxnNum(RequestContainer.txnNumEnum.LOAN_DETAIL.value());
        content.setTxnType(RequestContainer.txnTypeEnum.LOAN_DETAIL.getCode());
        LoanDetailRequestData data=new LoanDetailRequestData();
        data.setAppNo(wsxdCase.getAppNo());
        data.setFundId(wsxdCase.getLoanOrgin());
        content.setData(data);
        String jsonStr = JSONObject.toJSONString(content);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        logger.info("请求报文：{}",jsonStr);
        HttpClientUtils httpClient=new HttpClientUtils();
        JSONObject result= httpClient.postJsonRequest(jsonObject,ufsUrl);
        return result;
    }


    public JSONObject LanuchDeduction(WsxdCase wsxdCase, BigDecimal amount, BigDecimal maxDeductionAmount){
        RequestContainer content=new RequestContainer();
        SetDesSysId(wsxdCase.getLoanOrgin(),content);
        content.setTxnNum(RequestContainer.txnNumEnum.DEDUCT.value());
        content.setTxnType(RequestContainer.txnTypeEnum.MANUAL_DEDUCT.getCode());
        DeductRequestData data=new DeductRequestData();
        data.setDeductId(RandomUtil.generateSerialNum());
        data.setApplCde(wsxdCase.getAppNo());
        data.setTotalAmount(amount);
        content.setData(data);
        String jsonStr = JSONObject.toJSONString(content);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        logger.info("请求报文：{}",jsonStr);
        HttpClientUtils httpClient=new HttpClientUtils();
        JSONObject result= httpClient.postJsonRequest(jsonObject,ufsUrl);
        realTimeDeductionHistoryService.logDeductRequest(wsxdCase,data.getDeductId(),result,amount,maxDeductionAmount);
        return result;
    }


    private void SetDesSysId(String loanOrg,RequestContainer content){
        content.setTxnTime(DateUtils.formatDateTime(new Date()));
        if(LoanOrgConstants.HUA_AN.equals(loanOrg)){
            content.setDesSysId(RequestContainer.desSysIdEnum.HUA_AN.value());
        }else {
            content.setDesSysId(RequestContainer.desSysIdEnum.XIN_WANG.value());
        }
    }




}
