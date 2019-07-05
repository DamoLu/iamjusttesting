package com.plcs.web.wsxd.businessoperation.businessoperation.enums;

public enum ContactDataSourceEnum {

    DW_IMPORT("0","数仓导入"),
    MANUAL_CREATE("1","人工新建");


    private  String code;
    private  String codeName;

    ContactDataSourceEnum(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
