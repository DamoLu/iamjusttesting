package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuServiceBodyDTO;

/**
 * @author luqihua
 * @title: QueryBodyDeductHstDTO
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/9
 */
public class QueryBodyDeductHstDTO extends YinShuServiceBodyDTO {

    @JSONField(name = "REQUEST")
    private QueryRequestDeductHst request;

    @Override
    public QueryRequestDeductHst getRequest() {
        return request;
    }

    public void setRequest(QueryRequestDeductHst request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "QueryBodyDeductHstDTO{" +
                "request=" + request +
                '}';
    }
}
