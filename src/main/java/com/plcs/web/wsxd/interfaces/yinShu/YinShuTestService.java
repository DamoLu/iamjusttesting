package com.plcs.web.wsxd.interfaces.yinShu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryAfterLoanContainerDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryAfterLoanMainDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryBodyAfterLoanDTO;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryHeaderAfterLoan;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan.QueryRequestAfterLoan;

@Service
public class YinShuTestService {
	
	/**
	 * 测试银数接口
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(YinShuTestService.class);

//	银数核心sit测试环境
	private static String testUrl = "http://192.168.91.9:8880/";
//	private static String testUrl = "http://10.193.16.109:20004/";
//	private static String testUrl = "http://10.41.3.109:20004/";
	
	public static void main(String[] args) {
		QueryRequestAfterLoan request = new QueryRequestAfterLoan();
		request.setAbuser("24210011");
		request.setDueBillNo("20190704160000055765");
		
		QueryBodyAfterLoanDTO body = new QueryBodyAfterLoanDTO();
		body.setRequest(request);
		
		QueryHeaderAfterLoan header = new QueryHeaderAfterLoan();
		header.setServiceId("TNQLoanAfterInfoQuery");
		
		QueryAfterLoanMainDTO service = new QueryAfterLoanMainDTO();
		service.setBody(body);
		service.setHeader(header);
		
		QueryAfterLoanContainerDTO dto = new QueryAfterLoanContainerDTO();
		dto.setService(service);
		
		String jsonStr = JSONObject.toJSONString(dto);
		System.out.println(jsonStr);
		JSONObject j = JSONObject.parseObject(jsonStr);
		System.out.println(j.toJSONString());
		JSONObject result = httpPost(testUrl, j);
		System.out.println(result);
	}
	
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build());
		try {
			if (null != jsonParam) {
				StringEntity entity = new StringEntity(jsonParam.toString(), StandardCharsets.UTF_8.toString());
				entity.setContentEncoding(StandardCharsets.UTF_8.toString());
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), StandardCharsets.UTF_8.toString());
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
//                    jsonResult = new JSONObject().
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}
}
