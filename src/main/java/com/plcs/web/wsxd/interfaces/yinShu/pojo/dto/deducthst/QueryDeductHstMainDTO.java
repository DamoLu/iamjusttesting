package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuContainerDTO;

/**
 * @author luqihua
 * @title: QueryDeductHstServiceDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/9
 */
public class QueryDeductHstMainDTO extends YinShuContainerDTO {

    @JSONField(name = "SERVICE_HEADER")
    private QueryHeaderDeductHst header;
    @JSONField(name = "SERVICE_BODY")
    private QueryBodyDeductHstDTO body;

    public QueryHeaderDeductHst getHeader() {
        return header;
    }

    public void setHeader(QueryHeaderDeductHst header) {
        this.header = header;
    }

    public QueryBodyDeductHstDTO getBody() {
        return body;
    }

    public void setBody(QueryBodyDeductHstDTO body) {
        this.body = body;
    }
}
