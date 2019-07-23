package com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.anshuo.pojo.dto.common.AnShuoMainResponse;

import java.util.List;

/**
 * @author luqihua
 * @title: DeductHstAResponse
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/17
 */
public class DeductHstAResponse extends AnShuoMainResponse {

    @JSONField(name = "TotalCount")
    private String totalCount; //总笔数
    @JSONField(name = "PageSize")
    private String pageSize; //每页条数
    @JSONField(name = "ACCT_TRANSACTION")
    private String acctTransaction;

    private List<DeductHstA> deductHstAList;

    public List<DeductHstA> getDeductHstAList() {
        return deductHstAList;
    }

    public void setDeductHstAList(List<DeductHstA> deductHstAList) {
        this.deductHstAList = deductHstAList;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getAcctTransaction() {
        return acctTransaction;
    }

    public void setAcctTransaction(String acctTransaction) {
        this.acctTransaction = acctTransaction;
    }
}
