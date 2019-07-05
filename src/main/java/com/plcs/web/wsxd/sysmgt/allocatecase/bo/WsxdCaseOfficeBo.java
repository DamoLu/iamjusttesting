package com.plcs.web.wsxd.sysmgt.allocatecase.bo;

/**
 * @ProjectName plcs-web
 * @ClassName WsxdCaseOfficeBo
 * @Description 案件事业部
 * @Author AndyHuang
 * @DATE 2019/6/21 9:54
 */
public class WsxdCaseOfficeBo {
    /**
     * 案件事业部id
     */
    private String value;

    /**
     * 案件事业部名称
     */
    private String label;


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
}
