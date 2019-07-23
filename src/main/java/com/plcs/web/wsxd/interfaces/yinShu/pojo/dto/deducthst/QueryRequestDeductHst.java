package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuMainDTO;

/**
 * @author luqihua
 * @title: QueryRequestDeductHst
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/9
 */
public class QueryRequestDeductHst extends YinShuMainDTO {

    @JSONField(name = "CONTRA_NBR")
    private String contraNbr;
    @JSONField(name = "CREDIT_NO")
    private String creditNo;
    @JSONField(name = "PAGE_SIZE")
    private String pageSize;
    @JSONField(name = "PAGE_POSITION")
    private String pagePosition;

    public String getContraNbr() {
        return contraNbr;
    }

    public void setContraNbr(String contraNbr) {
        this.contraNbr = contraNbr;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPagePosition() {
        return pagePosition;
    }

    public void setPagePosition(String pagePosition) {
        this.pagePosition = pagePosition;
    }

    @Override
    public String toString() {
        return "QueryRequestDeductHst{" +
                "contraNbr='" + contraNbr + '\'' +
                ", creditNo='" + creditNo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pagePosition='" + pagePosition + '\'' +
                '}';
    }
}
