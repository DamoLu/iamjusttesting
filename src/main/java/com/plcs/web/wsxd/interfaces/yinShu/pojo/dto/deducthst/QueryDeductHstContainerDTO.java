package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuContainerDTO;
/**
 * @author luqihua
 * @title: QueryDeductHstDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/9
 */
public class QueryDeductHstContainerDTO extends YinShuContainerDTO {

    @JSONField(name = "SERVICE")
    private QueryDeductHstMainDTO service;

    public QueryDeductHstMainDTO getService() {
        return service;
    }

    public void setService(QueryDeductHstMainDTO service) {
        this.service = service;
    }
}
