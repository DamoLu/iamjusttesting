package com.plcs.web.wsxd.smsplatform.smssend.service.util;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsSendResultBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsSender {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${smsPlatformUrl}")
	private String smsPlatformUrl;
	private String smsContentPrefix = "【网商金融】";

    @Value("${envName}")
    private String env;
//	String test_dev = "127.0.0.1";
//	String test_host = "192.168.90.15";
//	String sit_host = "192.168.91.30";
//	String prd_host = "192.168.40.22";
//	String host = "";
//	int port = 9002;

//	public void send(String env, List<String> mobileList, String smsContent) {
//
//		if ("prd".equals(env)) {
//			host = prd_host;
//		} else if ("sit".equals(env)) {
//			host = sit_host;
//		} else if ("dev".equals(env)) {
//			host = test_dev;
//		} else {
//			host = test_host;
//		}
//
//		SendTextWSXD sender = new SendTextWSXD(host, port);
//
//		for (String mobile : mobileList) {
//			sender.put(mobile, smsContent);
//		}
//
//		sender.close();
//	}
//
//	public SmsSendResultBO send(String env, String mobile, String smsContent) {
//
//		if ("prd".equals(env)) {
//			host = prd_host;
//		} else if ("sit".equals(env)) {
//			host = sit_host;
//		} else if ("dev".equals(env)) {
//			host = test_dev;
//		} else {
//			host = test_host;
//		}
//
//		SendTextWSXD sender = new SendTextWSXD(host, port);
//
//		SmsSendResultBO s = sender.put(mobile, smsContent, 0);
//
//		sender.close();
//
//		return s;
//
//	}

	public String mobileListToString(List<String> mobileList) {
		StringBuffer sb = new StringBuffer(mobileList.toString());
		String result = sb.toString().replaceAll(", ", "|").replaceAll("\\[", "").replaceAll("\\]", "");
		return result;
	}

	public SmsSendResultBO send(String mobile, String smsContent,String loanBillNo) {
//		String url = null;
//
//		if("prd".equals(env)) {
//			url = "http://192.168.80.6:5000/sms/send.do";
//		} else {
//			url = "http://192.168.90.63:5000/sms/send.do";
//		}
		
		/**
		 * 参数值 username 系统名称 env 调用的短信系统 test 90网段， sit 91网段 ， prd 40生产网段 phone
		 * 手机号集合，通过|分割 content 发送的短信模板内容
		 */
		
		// 2019-07-02 短信中心改动, 短信内容前边需要加上短信签名
		if(!smsContent.startsWith(smsContentPrefix)) {
			smsContent = smsContentPrefix + smsContent;
		}
//        SmsRequestBody smsRequestBody = new SmsRequestBody();
//		smsRequestBody.setUsername("plcs-web");
//		smsRequestBody.setPhone(mobile);
//		smsRequestBody.setContent(smsContent);

		JSONObject param = new JSONObject();
		param.put("username", "plcs-web");
		param.put("env", env);
		param.put("phone", mobile);
		param.put("content", smsContent);
        String reqJsonStr = JSONObject.toJSONString(param);
		logger.info("发送短信-- 借据号: {} 发送对象 : {} 请求地址: {} 请求报文: {}", loanBillNo, mobile, smsPlatformUrl, reqJsonStr);

		/**
		 * 发送post
		 */
		JSONObject result = SmsSenderHttpClient.httpPost(smsPlatformUrl, param);
//		System.out.println("POST返回信息：" + result);
        logger.info("发送短信-- 借据号: {} 发送对象 : {} 响应报文: {}"
				, loanBillNo, mobile, result != null ? result.toJSONString() : "error");
		SmsSendResultBO s = new SmsSendResultBO();
		if(result != null && ("00".equals(result.getString("rspCode"))) ) {
			s.setFlag(true);
		} else {
			s.setFlag(false);
		}
		return s;
	}
	

}
