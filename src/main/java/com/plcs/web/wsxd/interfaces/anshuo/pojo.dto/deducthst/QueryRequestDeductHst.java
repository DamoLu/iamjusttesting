package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luqihua
 * @title: QueryRequestDeductHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/17
 */
public class QueryRequestDeductHst {
    @JSONField(name = "LoanSerialNo")
    private String loanSerialNo; //借据号
    @JSONField(name = "BeginDate")
    private String beginDate;  //起始日期
    @JSONField(name = "endDate")
    private String EndDate;  //截止日期
    @JSONField(name = "TransStatus")
    private String transStatus; //交易状态
    @JSONField(name = "PageNum")
    private Integer pageNum;
    @JSONField(name = "PageSize")
    private Integer pageSize;

    public String getLoanSerialNo() {
        return loanSerialNo;
    }

    public void setLoanSerialNo(String loanSerialNo) {
        this.loanSerialNo = loanSerialNo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
