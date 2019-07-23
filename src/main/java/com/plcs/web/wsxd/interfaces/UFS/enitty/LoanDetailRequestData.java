package com.plcs.web.wsxd.interfaces.UFS.enitty;

public class LoanDetailRequestData extends RequestData{

//  客户经理ID，与申请件编号二选一查询，有限按客户经理ID查询
    private  String abuser;
//  申请件编号
    private  String appNo;

//    资金方编码
    private String fundId;

    private  String pageNo="1";

    private  String pageSize="10";

    public String getAbuser() {
        return abuser;
    }

    public void setAbuser(String abuser) {
        this.abuser = abuser;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
