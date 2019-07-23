package com.plcs.web.test;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.wsxd.interfaces.UFS.service.UFSService;
import com.plcs.web.wsxd.queryroute.RequestService;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "classpath:spring-mvc.xml",
        "classpath:spring-context.xml",
        "classpath:spring-context-activiti.xml",
        "classpath:spring-context-jedis.xml",
        "classpath:spring-context-shiro.xml"
})
public class UFSServiceTest {

    @Autowired
    private RequestService requestService;

    public static void main(String args[]){

//        UFSService ufsService=new UFSService();
//        JSONObject jsonObject=ufsService.loanDetailQuery("20180828210000004766","24210011","30009");
//        System.out.println(jsonObject);

    }

    @Test
    public void UFStest(){
//        资金方为30009
//        LoanDetailResult result= requestService.excuteLoanDetailQuery("20180814150000037182");
//        System.out.println(result);
//        DeductResult deductResult=requestService.excuteDeduct("20181214110000048569",new BigDecimal(200));
//        System.out.println(deductResult);
    }

    @Test
    public void YStest(){
        LoanDetailResult result= requestService.excuteLoanDetailQuery("20180813115513375686");
        System.out.println(result);
    }
}
