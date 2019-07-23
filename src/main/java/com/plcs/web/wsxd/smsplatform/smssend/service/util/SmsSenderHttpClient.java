package com.plcs.web.wsxd.smsplatform.smssend.service.util;

import com.alibaba.fastjson.JSONObject;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HttpClient发送GET、POST请求
 * 
 * @Author liufei
 * @CreateDate 20190618
 */
public class SmsSenderHttpClient {

	private static final Logger logger = LoggerFactory.getLogger(SmsSenderHttpClient.class);
	/**
	 * 返回成功状态码
	 */
	public static final int SUCCESS_CODE = 200;

	/**
	 * post请求传输json参数
	 * 
	 * @param url       url地址
	 * @param jsonParam 参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build());
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
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
					logger.error("Parsing SMS response body failed:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("Send SMS request failed:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

}
