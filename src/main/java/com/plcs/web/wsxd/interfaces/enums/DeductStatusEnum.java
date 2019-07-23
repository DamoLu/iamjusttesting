package com.plcs.web.wsxd.interfaces.enums;


public enum DeductStatusEnum {

    //未处理
    UNDO("未处理",1),
    //处理中
    DOING("处理中",2),
    //失败
    SUCC("成功",3),
    //成功
    FAIL("失败",4);

    private String status;
    private Integer code;

    DeductStatusEnum(String status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public static DeductStatusEnum getType(String typeName) {
        for(DeductStatusEnum t : DeductStatusEnum.values()) {
            if(t.getStatus().equals(typeName)) {
                return t;
            }
        }
        return null;
    }

    public static DeductStatusEnum getType(Integer typeCode) {
        for(DeductStatusEnum t : DeductStatusEnum.values()) {
            if(t.getCode().equals(typeCode)) {
                return t;
            }
        }
        return null;
    }

}
