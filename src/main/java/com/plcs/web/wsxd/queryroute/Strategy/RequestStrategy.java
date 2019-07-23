package com.plcs.web.wsxd.queryroute.Strategy;

import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;

import java.math.BigDecimal;

public interface RequestStrategy {

    /**
     * 贷款详情查询
     */
    LoanDetailResult loanDeatilQuery(String loanBillNo);

    /**
     * 发送扣款请求
     */
    DeductResult LanuchDeduction( String loanBillNo, BigDecimal amount,BigDecimal maxDeductionAmount);
}
