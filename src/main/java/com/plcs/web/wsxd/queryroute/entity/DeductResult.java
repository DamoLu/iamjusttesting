package com.plcs.web.wsxd.queryroute.entity;

import com.plcs.web.wsxd.interfaces.enums.DeductStatusEnum;

import java.util.HashMap;
import java.util.Map;

public class DeductResult {

    private String code;

    private  String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public enum SuccResponseEnum {
        YS_SUCC("S"),
        UFS_SUCC("200"),
        AS_SUCC("OSFS110000");

        private  String  code;

        SuccResponseEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 安硕返回码值映射
     * @return
     */
    public Map getASCodeMap() {
        Map asCodeMap=new HashMap();
        asCodeMap.put("OSFS110000",DeductStatusEnum.SUCC.getStatus());
        asCodeMap.put("OSFS110001",DeductStatusEnum.FAIL.getStatus());
        asCodeMap.put("OSFS999999",DeductStatusEnum.FAIL.getStatus());
        asCodeMap.put("OSFS121012", DeductStatusEnum.DOING.getStatus());
        return asCodeMap;
    }

    /**
     * 银数返回码值映射
     * @return
     */
    public Map getYSCodeMap() {
        Map ysCodeMap=new HashMap();
        ysCodeMap.put("S",DeductStatusEnum.SUCC.getStatus());
        ysCodeMap.put("F",DeductStatusEnum.FAIL.getStatus());
        return ysCodeMap;
    }



    @Override
    public String toString() {
        return "DeductResult{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
