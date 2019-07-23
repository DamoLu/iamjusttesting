package com.plcs.web.wsxd.businessoperation.businessoperation.enums;

public enum DistributeResultStatus {
    DISTRIBUTE_SUCCESS(201, "手工分案成功！"),
    DISTRIBUTE_FAILURE(202, "系统内部出错，手工分案失败！"),
    NOT_OVERDUE(203, "待分配案件非逾期状态，手工分案失败！"),
    NOT_SELECTED(204, "未选择待分配的逾期案件！");

    private int status;
    private String msg;

    DistributeResultStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
