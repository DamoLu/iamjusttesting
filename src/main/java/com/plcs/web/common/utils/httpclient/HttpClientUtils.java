package com.plcs.web.common.utils.httpclient;

import com.alibaba.fastjson.JSON;
import com.plcs.web.common.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 创建https客户端
     *
     * @return
     */
    public CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 转发请求，并返回字符串
     *
     * @param redirectUrl
     * @param params
     * @return
     */
    public String sendRequest(String redirectUrl, Map<String, String> params) {

        CloseableHttpClient client = createSSLClientDefault();

        // 创建httppost
        HttpPost httppost = new HttpPost(redirectUrl);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(90000)
                .setConnectTimeout(90000).setConnectionRequestTimeout(90000).build();
        httppost.setConfig(defaultRequestConfig);
        //参数
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        //遍历并设置参数
        for (String key : params.keySet()) {
            BasicNameValuePair nameValue = new BasicNameValuePair(key, params.get(key));
            formparams.add(nameValue);
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            logger.info("executing request " + httppost.getURI());

            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    logger.info("--------------------------------------");
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    logger.info(responseString);
                    logger.info("--------------------------------------");

                    return responseString;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "error";
    }

    /**
     * 转发请求，并返回字符串
     *
     * @param redirectUrl
     * @param params
     * @return
     */
    public   String sendJsonRequest(String redirectUrl, RequestVO params) {

        CloseableHttpClient client = createSSLClientDefault();

        // 创建httppost
        HttpPost httppost = new HttpPost(redirectUrl);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(90000)
                .setConnectTimeout(90000).setConnectionRequestTimeout(90000).build();
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("Content-Type", "application/json");
        //参数
        StringEntity sentity = new StringEntity(JSON.toJSONString(params), "utf-8");
        sentity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        try {
            httppost.setEntity(sentity);
            logger.info("executing request " + httppost.getURI());

            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    logger.info("--------------------------------------");
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    logger.info(responseString);
                    logger.info("--------------------------------------");

                    return responseString;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        } finally {
        }

        return "error";
    }

    /**
     * 转发请求，并返回字符串
     *
     * @param params
     * @return
     */
    public String sendStrRequest(String redirectUrl, String params) {

        CloseableHttpClient client = createSSLClientDefault();

        // 创建httppost
        HttpPost httppost = new HttpPost(redirectUrl);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(90000)
                .setConnectTimeout(90000).setConnectionRequestTimeout(90000).build();
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("Content-Type", "application/json");
        //参数
        StringEntity sentity = new StringEntity(params, "utf-8");
        sentity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        try {
            httppost.setEntity(sentity);
            logger.info("executing request " + httppost.getURI());

            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    logger.info("--------------------------------------");
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    logger.info(responseString);
                    logger.info("--------------------------------------");

                    return responseString;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        } finally {
        }

        return "error";
    }


    public String sendToUpay(String redirectUrl, Map<String, String> paramMap) {

        CloseableHttpClient client = createSSLClientDefault();

        // 创建httppost
        HttpPost httppost = new HttpPost(redirectUrl);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(90000)
                .setConnectTimeout(90000).setConnectionRequestTimeout(90000).build();
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset");

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (String key : paramMap.keySet()) {
            if (StringUtils.isNotBlank(key)
                    && StringUtils.isNotBlank(paramMap.get(key))) {
                String value = paramMap.get(key);
                pairs.add(new BasicNameValuePair(key, value));
            }
        }
        //参数
        try {
            UrlEncodedFormEntity sentity = new UrlEncodedFormEntity(pairs, "utf-8");
            httppost.setEntity(sentity);
            logger.info("executing request " + httppost.getURI());

            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    logger.info("--------------------------------------");
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    //logger.info(responseString);
                    logger.info("--------------------------------------");

                    return responseString;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        } finally {
        }

        return "error";
    }

    public String yxSendStrRequest(String redirectUrl, String params, String yxMerid, String yxSecretkey) {

        CloseableHttpClient client = createSSLClientDefault();

        // 创建httppost
        HttpPost httppost = new HttpPost(redirectUrl);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(90000)
                .setConnectTimeout(90000).setConnectionRequestTimeout(90000).build();
        httppost.setConfig(defaultRequestConfig);
        //httppost.setHeader("Content-Type", "application/json");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        httppost.setHeader("Content-Encoding", "UTF-8");
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("MerId", yxMerid);
        httppost.setHeader("SecretKey", yxSecretkey);
        //参数
        StringEntity sentity = new StringEntity(params, "utf-8");
        //sentity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


        try {
            httppost.setEntity(sentity);
            logger.info("executing request " + httppost.getURI());
            logger.info(EntityUtils.toString(sentity, "UTF-8"));
            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    logger.info("--------------------------------------");
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    logger.info(responseString);
                    logger.info("--------------------------------------");

                    return responseString;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        } finally {
        }

        return "error";
    }

    public String yxDownLoadt(String redirectUrl, String params, String sign, String yxMerid, String yxSecretkey) {

        CloseableHttpClient client = createSSLClientDefault();

        // 创建httppost
        HttpPost httppost = new HttpPost(redirectUrl);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(90000)
                .setConnectTimeout(90000).setConnectionRequestTimeout(90000).build();
        httppost.setConfig(defaultRequestConfig);
        //httppost.setHeader("Content-Type", "application/json");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        httppost.setHeader("Content-Encoding", "UTF-8");
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("MerId", yxMerid);
        httppost.setHeader("SecretKey", yxSecretkey);
        httppost.setHeader("SignedMsg", sign);

        //参数
        StringEntity sentity = new StringEntity(params, "utf-8");
        //sentity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


        try {
            httppost.setEntity(sentity);
            logger.info("executing request " + httppost.getURI());

            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    logger.info("--------------------------------------");
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    logger.info(responseString);
                    logger.info("--------------------------------------");

                    return responseString;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        } finally {
        }

        return "error";
    }

}
