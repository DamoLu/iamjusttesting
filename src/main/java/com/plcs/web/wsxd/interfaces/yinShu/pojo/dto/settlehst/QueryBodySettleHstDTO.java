package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuServiceBodyDTO;

/**
 * @author luqihua
 * @title: QueryBodySettleHstDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/11
 */
public class QueryBodySettleHstDTO extends YinShuServiceBodyDTO {

    @JSONField(name = "REQUEST")
    private QueryRequestSettleHst request;

    @Override
    public QueryRequestSettleHst getRequest() {
        return request;
    }

    public void setRequest(QueryRequestSettleHst request) {
        this.request = request;
    }
}
