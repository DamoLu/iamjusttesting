package com.plcs.web.wsxd.businessoperation.businessoperation.dto;

public class WsxdAllocateHstExportDTO {
    private String appOrgName;		// 案件范围(机构) 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app
    private String departmentName;		// 事业部名
    private String loanOrgin; // 放款机构
    private String customerName; // 客户姓名
    private String loanBillNo;		// 借据号
    private String overdueDays; // 逾期天数
    private String oldOdvName;		// 旧催收员姓名
    private String oldOdvGroupName; // 旧催收组名
    private String odvName;		// 催收员姓名
    private String odvGroupName;		// 催收员组名
    private String createByUser; //  操作人
    private String createDate; // 操作时间

    public String getAppOrgName() {
        return appOrgName;
    }

    public void setAppOrgName(String appOrgName) {
        this.appOrgName = appOrgName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLoanOrgin() {
        return loanOrgin;
    }

    public void setLoanOrgin(String loanOrgin) {
        this.loanOrgin = loanOrgin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLoanBillNo() {
        return loanBillNo;
    }

    public void setLoanBillNo(String loanBillNo) {
        this.loanBillNo = loanBillNo;
    }

    public String getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getOldOdvName() {
        return oldOdvName;
    }

    public void setOldOdvName(String oldOdvName) {
        this.oldOdvName = oldOdvName;
    }

    public String getOldOdvGroupName() {
        return oldOdvGroupName;
    }

    public void setOldOdvGroupName(String oldOdvGroupName) {
        this.oldOdvGroupName = oldOdvGroupName;
    }

    public String getOdvName() {
        return odvName;
    }

    public void setOdvName(String odvName) {
        this.odvName = odvName;
    }

    public String getOdvGroupName() {
        return odvGroupName;
    }

    public void setOdvGroupName(String odvGroupName) {
        this.odvGroupName = odvGroupName;
    }

    public String getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(String createByUser) {
        this.createByUser = createByUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
