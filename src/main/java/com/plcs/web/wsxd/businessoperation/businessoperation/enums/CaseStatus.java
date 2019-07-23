package com.plcs.web.wsxd.businessoperation.businessoperation.enums;

public enum CaseStatus {
    OVERDUE("01", "常规逾期"),
    REPAY("02", "正常还款"),
    SETTLED("03", "已结清");

    private String status;
    private String desc;

    CaseStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
