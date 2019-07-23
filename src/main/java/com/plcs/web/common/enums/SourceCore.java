package com.plcs.web.common.enums;

public enum SourceCore {

    YS("01","银数"),
    AS("02","安硕"),
    CX("03","创新");

    /**
     * 核心编码
     */
    private  String code;
    /**
     * 核心名称
     */
    private  String name;

    SourceCore(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
