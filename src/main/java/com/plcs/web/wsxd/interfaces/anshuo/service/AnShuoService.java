package com.plcs.web.wsxd.interfaces.anshuo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.httpclient.HttpClientUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.common.AnshuoMainRequest;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst.DeductHstA;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst.DeductHstAResponse;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst.QueryDeductHstContainerDTO;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst.QueryRequestDeductHst;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.settlehst.QueryRequestSettleHst;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.settlehst.QuerySettleHstContainerDTO;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.settlehst.SettleHstA;
import com.plcs.web.wsxd.interfaces.anshuo.utils.Base64Utils;
import com.plcs.web.wsxd.interfaces.yinShu.service.YinShuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

/**
 * @author luqihua
 * @title: AnShuoService
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/16
 */
@Service
public class AnShuoService {
    private static final Logger logger = LoggerFactory.getLogger(YinShuService.class);
    @Value("${anshuo.privateKey}")
    private String privateKey;
    @Value("${anshuo.publicKey}")
    private String publicKey;
    private String testUrl = "http://192.168.91.35:8070/cfs_server/ws/";

    public SettleHstA querySettleHst(WsxdCase wsxdCase) {
        String interfaceNo = "CFS001020020";
        QueryRequestSettleHst messageInfo = new QueryRequestSettleHst();
        messageInfo.setLoanSerialNo(wsxdCase.getLoanBillNo());
        messageInfo.setTransFlag("02");
        messageInfo.setPrePayType("3");
        QuerySettleHstContainerDTO querySettleHstContainerDTO = new QuerySettleHstContainerDTO();
        querySettleHstContainerDTO.setInterfaceNo(interfaceNo);
        JSONObject jsonResponse = this.requestAndParse(querySettleHstContainerDTO, messageInfo);
        if (null!=jsonResponse){
            SettleHstA settleHstA = JSONObject.parseObject(jsonResponse.toJSONString(), SettleHstA.class);
            LocalDateTime localDateTime = LocalDateTime.now();
            settleHstA.setDate(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            return settleHstA;
        }

//            if ("OSFS110000".equals(settleHstA.getReturnCode())) {
//                logger.error("借据号[{}]"+ settleHstA.getReturnMessage(), wsxdCase.getLoanBillNo());
//            }

        return null;

    }

    public DeductHstAResponse findDeductHstList(WsxdCase wsxdCase, Page page) {
        String interfaceNo = "CFS003000230";
        QueryRequestDeductHst messageInfo = new QueryRequestDeductHst();
        messageInfo.setLoanSerialNo(wsxdCase.getLoanBillNo());//测试用
        messageInfo.setPageSize(page.getPageSize());
        messageInfo.setPageNum(page.getPageNo());
        QueryDeductHstContainerDTO request = new QueryDeductHstContainerDTO();
        request.setInterfaceNo(interfaceNo);
        JSONObject jsonResponse = this.requestAndParse(request, messageInfo);
        if (null != jsonResponse) {
            DeductHstAResponse deductHstAResponse = JSONObject.parseObject(jsonResponse.toJSONString(), DeductHstAResponse.class);
            if (!"OSFS110000".equals(deductHstAResponse.getReturnCode())) {
                logger.error(deductHstAResponse.getReturnMessage());
                return null;
            }
            JSONArray acctTransaction = jsonResponse.getJSONArray("ACCT_TRANSACTION");
            List<DeductHstA> deductHstAList = JSONObject.parseArray(acctTransaction.toJSONString(), DeductHstA.class);
            deductHstAResponse.setDeductHstAList(deductHstAList);
            return deductHstAResponse;
        }
        return null;
    }

    private JSONObject requestAndParse(AnshuoMainRequest request, Object messageInfo) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            String yyyyMMddHHss = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHss"));
            request.setBusinessSerialNo("CS" + yyyyMMddHHss);
            String encodeMessageInfo = Base64Utils.encodeMessageInfo(messageInfo, publicKey);
            request.setMessageInfo(encodeMessageInfo);
            JSONObject jsonParam = JSONObject.parseObject(JSONObject.toJSONString(request));
            JSONObject httpResponse = HttpClientUtils.httpPost(testUrl + request.getInterfaceNo(), jsonParam);
            Object responseMessageInfo = httpResponse.get("MessageInfo");
            JSONObject jsonObject = Base64Utils.decodeMessageInfo(responseMessageInfo.toString(), request.getCharset(), privateKey);
            return jsonObject;
        } catch (Exception e) {
            logger.error("请求安硕接口错误错误,interfaceNo[{}]", request.getInterfaceNo());
            System.out.println("请求安硕接口错误错误:" + e.getMessage());
        }
        return null;
    }
}
