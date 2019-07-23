package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author luqihua
 * @title: QueryDeductHstResponse
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/10
 */
public class QueryDeductHstResponse {
    @JSONField(name = "CONTR_NBR")
    private String contrNbr;
    @JSONField(name = "PAGE_SIZE")
    private String pageSize;
    @JSONField(name = "TXN_COUNT")
    private String txnCount;
    @JSONField(name = "TXN_LIST")
    private List<DeductHst> txnList;

    public String getContrNbr() {
        return contrNbr;
    }

    public void setContrNbr(String contrNbr) {
        this.contrNbr = contrNbr;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTxnCount() {
        return txnCount;
    }

    public void setTxnCount(String txnCount) {
        this.txnCount = txnCount;
    }

    public List<DeductHst> getTxnList() {
        return txnList;
    }

    public void setTxnList(List<DeductHst> txnList) {
        this.txnList = txnList;
    }
}
