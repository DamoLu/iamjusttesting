package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuMainDTO;

/**
 * @author luqihua
 * @title: QuerySettleHstMainDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/11
 */
public class QuerySettleHstMainDTO extends YinShuMainDTO {
    @JSONField(name = "SERVICE_HEADER")
    private QueryHeaderSettleHst header;
    @JSONField(name = "SERVICE_BODY")
    private QueryBodySettleHstDTO body;

    public QueryHeaderSettleHst getHeader() {
        return header;
    }

    public void setHeader(QueryHeaderSettleHst header) {
        this.header = header;
    }

    public QueryBodySettleHstDTO getBody() {
        return body;
    }

    public void setBody(QueryBodySettleHstDTO body) {
        this.body = body;
    }
}
