package com.plcs.web.wsxd.businessoperation.businessoperation.entity;

import java.util.Date;

public class WsxdContactPhone {

    private String id;                  // 物理主键
    private String loanBillNo;		    // 借据编号
    private String customerIdNo;        // 客户证件号
    private String name;                // 联系人姓名
    private String relation;            // 联系人关系
    private String phone;               // 联系人号码
    private String type;                // 电话类型 0:手机 1:座机
    private String dataSource;          // 数据来源 0:数仓导入 1:人工新建
    private String remark;              // 备注
    private String createBy;            // 创建者
    private Date createDate;            // 创建时间
    private String updateBy;            // 更新者
    private Date updateDate;            // 更新时间
    private String remarks;             // 备注信息
    private String delFlag;             // 删除标记 默认 0-正常 1-删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getLoanBillNo() {
        return loanBillNo;
    }

    public void setLoanBillNo(String loanBillNo) {
        this.loanBillNo = loanBillNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
