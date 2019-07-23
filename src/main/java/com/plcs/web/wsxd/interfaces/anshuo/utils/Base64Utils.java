package com.plcs.web.wsxd.interfaces.anshuo.utils;

import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author luqihua
 * @title: Base64Utils
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/16
 */
public class Base64Utils {

    /**
     * 将Base64码解码成字节数组
     * */
    public static byte[] decode(String base64) throws Exception {
        return Base64.getMimeDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将字节数组进行Base64编码
     * */
    public static String encode(byte[] bytes) throws Exception {
         return Base64.getMimeEncoder().encodeToString(bytes);
    }

    //解密
    public static JSONObject decodeMessageInfo(String messageInfo, String charset, String privateKey) throws Exception {
        byte[] decodedData = RSAUtils.decryptByPrivateKey(decode(messageInfo), privateKey);
        return JSONObject.parseObject(new String(decodedData, charset));
    }

    //加密
    public static String encodeMessageInfo(Object messageInfoBean, String publicKey) throws Exception{
        String messageInfo = JSONObject.toJSON(messageInfoBean).toString();
        //公钥加密响应报文
        byte[] encodedData = RSAUtils.encryptByPublicKey(messageInfo.getBytes(), publicKey);
        return encode(encodedData);
    }

}
