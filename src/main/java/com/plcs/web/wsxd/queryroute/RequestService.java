package com.plcs.web.wsxd.queryroute;

import com.plcs.web.common.constant.LoanOrgConstants;
import com.plcs.web.common.enums.SourceCore;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.interfaces.enums.DeductStatusEnum;
import com.plcs.web.wsxd.queryroute.Strategy.RequestStrategy;
import com.plcs.web.wsxd.queryroute.Strategy.impl.UFSStrategy;
import com.plcs.web.wsxd.queryroute.Strategy.impl.YinShuStrategy;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class RequestService {


    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    private WsxdCaseService wsxdCaseService;

    @Autowired
    private UFSStrategy ufsStrategy;

    @Autowired
    private YinShuStrategy yinShuStrategy;

    /**
     * 贷款详情查询
     * @param loanBillNo  借据号
     */
    public LoanDetailResult excuteLoanDetailQuery(String loanBillNo) {
        LoanDetailResult result;
        WsxdCase wsxdCase=wsxdCaseService.getByLoanBillNo(loanBillNo);
        RequestStrategy requestStrategy=setTarget(wsxdCase.getSourceCore(),wsxdCase.getLoanOrgin());
        if(requestStrategy !=null){
            result= requestStrategy.loanDeatilQuery(loanBillNo);
        }else {
            result=new LoanDetailResult();
            result.setSupportManualDeduction(false);
            result.setRespMsg("此部分案件不支持实时扣款！");
        }
        logger.info("贷款详情查询结果：{}",result);
        return result;
    }


    /**
     * 发起扣款请求
     * @param loanBillNo  借据号
     */
    public DeductResult excuteDeduct(String loanBillNo, BigDecimal amount,BigDecimal maxDeductionAmount) {
        DeductResult result;
        WsxdCase wsxdCase=wsxdCaseService.getByLoanBillNo(loanBillNo);
        RequestStrategy requestStrategy=setTarget(wsxdCase.getSourceCore(),wsxdCase.getLoanOrgin());
        if(requestStrategy !=null){
            result= requestStrategy.LanuchDeduction(loanBillNo,amount,maxDeductionAmount);
        }else {
            result=new DeductResult();
            result.setDesc("暂时不支持点扣功能");
        }
        return result;
    }

    /**
     * 华安（30009）和新网（30020）走资金前置
     * 根据数据来源走对应的核心系统，目前不支持安硕、创新
     * @param dataSource
     * @param loanOrg
     * @throws Exception
     */
    private RequestStrategy setTarget(String dataSource,String loanOrg) {

            if(LoanOrgConstants.HUA_AN.equals(loanOrg)||LoanOrgConstants.XIN_WANG.equals(loanOrg)){
                return ufsStrategy;
            }else if(SourceCore.YS.getCode().equals(dataSource)){
                return yinShuStrategy;
            }else {
                logger.warn("暂时不支持 数据来源：{}，放款机构：{}的查询",dataSource,loanOrg);
                return null;
            }
    }



}
