package com.plcs.web.wsxd.interfaces.yinShu.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.RandomUtil;
import com.plcs.web.common.utils.httpclient.HttpClientUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.deduct.entity.WsxdRealTimeDeductionHistory;
import com.plcs.web.wsxd.deduct.service.WsxdRealTimeDeductionHistoryService;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuServerResponse;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuServiceHeaderDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst.*;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction.DeductBody;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction.DeductContianer;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction.DeductRequestContent;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction.DeductService;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryAfterLoanContainerDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryAfterLoanMainDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryBodyAfterLoanDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryHeaderAfterLoan;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryRequestAfterLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YinShuService {
	
	private static final Logger logger = LoggerFactory.getLogger(YinShuService.class);

	@Autowired
	private WsxdRealTimeDeductionHistoryService realTimeDeductionHistoryService;

//	银数核心sit测试环境
	private static String testUrl = "http://192.168.91.9:8880/";
//	private static String testUrl = "http://10.193.16.109:20004/";
//	private static String testUrl = "http://10.41.3.109:20004/";
	
	public JSONObject queryAfterLoan(String managerId, String billNo) {
		QueryRequestAfterLoan request = new QueryRequestAfterLoan();
		request.setAbuser(managerId);
		request.setDueBillNo(billNo);
		
		QueryBodyAfterLoanDTO body = new QueryBodyAfterLoanDTO();
		body.setRequest(request);
		
		QueryHeaderAfterLoan header = new QueryHeaderAfterLoan();
		header.setServiceId("TNQLoanAfterInfoQuery");
		header.setServiceSN(RandomUtil.generateServiceSN(24));
		header.setRequestTime(DateUtils.getDate("yyyyMMddHHmmss"));
		
		QueryAfterLoanMainDTO service = new QueryAfterLoanMainDTO();
		service.setBody(body);
		service.setHeader(header);
		
		QueryAfterLoanContainerDTO dto = new QueryAfterLoanContainerDTO();
		dto.setService(service);
		
		String jsonStr = JSONObject.toJSONString(dto);
		JSONObject j = JSONObject.parseObject(jsonStr);
		logger.info("请求报文：{}",jsonStr);
		JSONObject result = HttpClientUtils.httpPost(testUrl, j);
		return result;
	}

	public QueryDeductHstResponse findDeductHstList(WsxdCase wsxdCase, Page page) {
		QueryRequestDeductHst request = new QueryRequestDeductHst();
		request.setContraNbr(wsxdCase.getContractNo());
		request.setCreditNo(wsxdCase.getLoanBillNo());
		request.setPageSize(String.valueOf(page.getPageSize()));
		request.setPagePosition(String.valueOf(page.getPageNo()));
		QueryBodyDeductHstDTO body = new QueryBodyDeductHstDTO();
		body.setRequest(request);
		QueryHeaderDeductHst header = new QueryHeaderDeductHst();
		header.setServiceId("TNQOrderInfo");
		QueryDeductHstMainDTO mainDTO = new QueryDeductHstMainDTO();
		mainDTO.setBody(body);
		mainDTO.setHeader(header);
		QueryDeductHstContainerDTO containerDTO = new QueryDeductHstContainerDTO();
		containerDTO.setService(mainDTO);
		JSONObject jsonParam = JSONObject.parseObject(JSONObject.toJSONString(containerDTO));
		JSONObject responseResult = HttpClientUtils.httpPost(testUrl, jsonParam);
		JSONObject serverResponse = responseResult.getJSONObject("SERVICE").getJSONObject("SERVICE_HEADER").getJSONObject("SERV_RESPONSE");
		YinShuServerResponse yinShuServerResponse = JSONObject.parseObject(serverResponse.toJSONString(), YinShuServerResponse.class);
		if (!"S".equals(yinShuServerResponse.getStatus())) {
			logger.error("银树扣款记录接口查询[借据号:{}]" + yinShuServerResponse.getDesc(), wsxdCase.getLoanBillNo());
		}
		JSONObject response = responseResult.getJSONObject("SERVICE").getJSONObject("SERVICE_BODY").getJSONObject("RESPONSE");
		return JSONObject.parseObject(response.toJSONString(), QueryDeductHstResponse.class);
	}

	public SettleHst querySettleHst(WsxdCase wsxdCase){
		QueryRequestSettleHst request = new QueryRequestSettleHst();
		request.setType("1"); // 1.提前还款试算  2.提前还款预约
		request.setContrNbr(wsxdCase.getContractNo());
        LocalDate localDate = LocalDate.now();
        request.setCaldate(localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		request.setCreditNo(wsxdCase.getLoanBillNo());
		QueryBodySettleHstDTO body = new QueryBodySettleHstDTO();
		body.setRequest(request);

		QueryHeaderSettleHst header = new QueryHeaderSettleHst();
		header.setServiceId("TNMLBooking");
		QuerySettleHstMainDTO mainDTO = new QuerySettleHstMainDTO();
		mainDTO.setBody(body);
		mainDTO.setHeader(header);

		QuerySettleHstContainerDTO containerDTO = new QuerySettleHstContainerDTO();
		containerDTO.setService(mainDTO);
		JSONObject jsonParam = JSONObject.parseObject(JSONObject.toJSONString(containerDTO));
		JSONObject responseResult = HttpClientUtils.httpPost(testUrl, jsonParam);
		JSONObject serverResponse = responseResult.getJSONObject("SERVICE").getJSONObject("SERVICE_HEADER").getJSONObject("SERV_RESPONSE");
		YinShuServerResponse yinShuServerResponse = JSONObject.parseObject(serverResponse.toJSONString(), YinShuServerResponse.class);
		if (!"S".equals(yinShuServerResponse.getStatus())) {
			logger.error("银树结清金额查询接口[借据号:{}]" + yinShuServerResponse.getDesc(), wsxdCase.getLoanBillNo());
		}
		JSONObject response = responseResult.getJSONObject("SERVICE").getJSONObject("SERVICE_BODY").getJSONObject("RESPONSE");
		SettleHst settleHst = JSONObject.parseObject(response.toJSONString(), SettleHst.class);
		if (StringUtils.isBlank(settleHst.getAmount())&& StringUtils.isBlank(settleHst.getInterest())&&StringUtils.isBlank(settleHst.getPoundage()) && StringUtils.isBlank(settleHst.getPremium()) && StringUtils.isBlank(settleHst.getPrincipal())) {
			return null;
		}
		settleHst.setDate(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		return settleHst;
	}


	/**
	 * 非循环贷款主动还款接口（循环贷不支持点扣）
	 * @param wsxdCase     案件详情
	 * @param amount      还款金额
	 * @return
	 */
	public JSONObject lanuchDeduction(WsxdCase wsxdCase, BigDecimal amount,BigDecimal maxDeductionAmount){
		DeductRequestContent request=new DeductRequestContent();
		request.setContractNo(wsxdCase.getContractNo());
		request.setAmount(amount);

		DeductBody body = new DeductBody();
		body.setRequest(request);

		YinShuServiceHeaderDTO header = new QueryHeaderAfterLoan();
		header.setServiceId("TFCRepay");
		header.setServiceSN(RandomUtil.generateServiceSN(24));
		header.setRequestTime(DateUtils.getDate("yyyyMMddHHmmss"));

		DeductService service = new DeductService();
		service.setBody(body);
		service.setHeader(header);

		DeductContianer contianer=new DeductContianer();
		contianer.setService(service);

		String jsonStr = JSONObject.toJSONString(contianer);
		JSONObject object=JSONObject.parseObject(jsonStr);
		logger.info("请求报文：{}",jsonStr);
		HttpClientUtils httpClient=new HttpClientUtils();
		JSONObject result=httpClient.postJsonRequest(object,testUrl);
		realTimeDeductionHistoryService.logDeductRequest(wsxdCase,header.getServiceSN(),result,amount,maxDeductionAmount);
		return  result;
	}




}
