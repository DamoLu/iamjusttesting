package com.plcs.web.wsxd.interfaces.chuangxin.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.utils.httpclient.HttpClientUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.deduct.service.WsxdRealTimeDeductionHistoryService;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst.DeductHstC;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst.DeductHstResponse;
import com.plcs.web.wsxd.interfaces.chuangxin.dto.deducthst.QueryDeductHstContainerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChuangXinService {

    @Value("cx-sit.loanDetail")
    private String loanDetailUrl;

    private static final Logger logger = LoggerFactory.getLogger(ChuangXinService.class);

    @Autowired
    private WsxdRealTimeDeductionHistoryService realTimeDeductionHistoryService;

    public JSONObject loanDetailQuery(String loanBillNO){
        Map requestMap=new HashMap();
        requestMap.put("loanSerialNo",loanBillNO);
        String jsonStr = JSONObject.toJSONString(requestMap);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        logger.info("请求报文：{}",jsonStr);
        HttpClientUtils httpClient=new HttpClientUtils();
        JSONObject result= httpClient.postJsonRequest(jsonObject,loanDetailUrl);
        return result;
    }

    public JSONObject LanuchDeduction(WsxdCase wsxdCase, BigDecimal amount, BigDecimal maxDeductionAmount){

        return null;
    }

    public DeductHstResponse findDeductHstList(WsxdCase wsxdCase) {
        String url = "https://dev.newlandfinance.com/dev/channel/capiCallBack/collection/CSHOU_QUERYORDERFLOW.do";
        QueryDeductHstContainerDTO request = new QueryDeductHstContainerDTO();
        request.setLoanSerialNo("20190711223344556610");
        JSONObject jsonParams = JSONObject.parseObject(JSON.toJSONString(request));
        JSONObject responseJson = HttpClientUtils.httpPost(url, jsonParams);
        DeductHstResponse deductHstResponse = JSONObject.parseObject(responseJson.toJSONString(), DeductHstResponse.class);
        if (!"200".equals(deductHstResponse.getRespCode())) {
            logger.error("创新扣款记录查询错误,借据号[{}]", wsxdCase.getLoanBillNo());
        } else {
            JSONArray deductHstListJson = responseJson.getJSONObject("data").getJSONObject("respObj").getJSONArray("list");
            List<DeductHstC> deductHstCSList = JSONObject.parseArray(deductHstListJson.toJSONString(), DeductHstC.class);
            deductHstResponse.setDeductHstCList(deductHstCSList);
        }
        return deductHstResponse;
    }
}
