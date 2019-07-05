package com.plcs.web.wsxd.sysmgt.allocatecase.bo;

import java.util.List;

/**
 * @ProjectName plcs-web
 * @ClassName WsxdCaseScopeBo
 * @Description 案件范围
 * @Author AndyHuang
 * @DATE 2019/6/21 9:56
 */
public class WsxdCaseScopeBo {
    /**
     *  机构id
     */
    private String value;

    /**
     *  机构名
     */
    private String label;

    /**
     * 案件事业部列表
     */
    private List<WsxdCaseOfficeBo> list;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<WsxdCaseOfficeBo> getList() {
        return list;
    }

    public void setList(List<WsxdCaseOfficeBo> list) {
        this.list = list;
    }
}
