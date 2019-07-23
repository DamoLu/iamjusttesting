package com.plcs.web.wsxd.businessoperation.businessoperation.enums;

public enum DataScopeEnum {
    ALL_DATA("1","所有数据"),
    BELONG_DEPARTMENT("4","所在部门数据"),
    ALONE("8", "仅本人数据");


    private  String code;
    private  String codeName;

    DataScopeEnum(String code, String codeName) {
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
