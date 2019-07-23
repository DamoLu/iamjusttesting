package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuContainerDTO;

/**
 * @author luqihua
 * @title: QuerySettleHstContainerDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/11
 */
public class QuerySettleHstContainerDTO extends YinShuContainerDTO {

    @JSONField(name = "SERVICE")
    private QuerySettleHstMainDTO service;

    public QuerySettleHstMainDTO getService() {
        return service;
    }

    public void setService(QuerySettleHstMainDTO service) {
        this.service = service;
    }
}
