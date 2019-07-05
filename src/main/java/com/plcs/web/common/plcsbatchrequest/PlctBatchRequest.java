package com.plcs.web.common.plcsbatchrequest;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.utils.httpclient.HttpClientUtils;
import com.plcs.web.common.utils.httpclient.RequestVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdAllocateHst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 跑批项目http请求工具类
 */
@Component
public class PlctBatchRequest {

    @Value("${plcs.batch.url}")
    private String plcsBatchUrl;
    @Value("${plcs.batch.doallocateurl}")
    private String doAllocateUrl;
    @Value("${plcs.batch.stopallocatecaseurl}")
    private String stopAllocatecaseUrl;


    /**
     * 对案件进行停催
     * @param wsxdAllocateHst 包含 id/loanBillNo/odv
     * @return
     *  {"status":200,"msg":"案件停催成功","data":null}
     *  {"status":400,"msg":"案件停催失败","data":null}
     */
    public Map<String, Object> stopAllocateCase(final WsxdAllocateHst wsxdAllocateHst) {
        return JSONObject.parseObject(new HttpClientUtils().sendJsonRequest(plcsBatchUrl+ stopAllocatecaseUrl, new RequestVO(new HashMap(){{put("wsxdAllocateHst", JSONObject.toJSONString(wsxdAllocateHst));}})), Map.class);
    }

    /**
     * 执行自动分案
     * @return
     *  {"status":200,"msg":"自动分案执行成功","data":null}
     *  {"status":400,"msg":"自动分案执行失败","data":null}
     */
    public Map<String, Object> doAllocateCase() {
        return JSONObject.parseObject(new HttpClientUtils().sendJsonRequest(plcsBatchUrl+ doAllocateUrl, new RequestVO()), Map.class);
    }
}
