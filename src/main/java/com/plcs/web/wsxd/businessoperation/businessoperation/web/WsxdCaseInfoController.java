package com.plcs.web.wsxd.businessoperation.businessoperation.web;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.modules.sys.utils.DictUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.*;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.*;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst.DeductHst;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.deducthst.QueryDeductHstResponse;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.settlehst.SettleHst;
import com.plcs.web.wsxd.queryroute.RequestService;
import com.plcs.web.wsxd.queryroute.entity.DeductResult;
import com.plcs.web.wsxd.queryroute.entity.LoanDetailResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * 业务操作查看页面Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/businessoperation/wsxdCaseInfo")
public class WsxdCaseInfoController extends BaseController {

    @Autowired
    private WsxdRemindRecordService remindRecordService;
    @Autowired
    private WsxdCaseInfoService caseInfoService;
    @Autowired
    private WsxdRepayPlanService repayPlanService;
    @Autowired
    private WsxdContactAddrService contactAddrService;
    @Autowired
    private WsxdContactPhoneService contactPhoneService;
    @Autowired
    private WsxdCaseService wsxdCaseService;
    @Autowired
    private WsxdRepayHstService wsxdRepayHstService;
    @Autowired
    private RequestService requestService;

    @Value("${pictures.upload.url}")
    private String picturesUploadURL;

    @RequestMapping(value = {"list"})
    public String list(WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
        String loanBillNo = wsxdCase.getLoanBillNo();
        // 总进展
        model.addAttribute("totalProgressPage", remindRecordService.findPage(new Page<WsxdRemindRecord>(request, response){{setPageSize(10);}}, new WsxdRemindRecord(){{setLoanBillNo(loanBillNo);}}));
        // 基本信息
        model.addAttribute("wsxdCase", caseInfoService.findBaseInfo(loanBillNo));
        // 账户信息
        model.addAttribute("cardInfoPage", caseInfoService.findCardInfo(new Page<WsxdCase>(request, response){{setPageSize(5);}}, wsxdCase));
        // 联系电话列表
        model.addAttribute("phoneInfoPage", caseInfoService.findContactPhones(new Page<WsxdContactPhone>(request, response){{setPageSize(5);}}, wsxdCase));
        // 联系地址列表
        model.addAttribute("addressInfoPage", caseInfoService.findContactAddrs(new Page<WsxdContactAddr>(request, response){{setPageSize(5);}}, wsxdCase));
        // 影像件地址ip
        model.addAttribute("picturesUploadURL", picturesUploadURL);


        model.addAttribute("wsxdRemindRecord", new WsxdRemindRecord());
        model.addAttribute("wsxdContactAddr", new WsxdContactAddr());
        model.addAttribute("wsxdContactPhone", new WsxdContactPhone());
        model.addAttribute("phoneStatusList", DictUtils.getDictListJson("phone_status"));
        model.addAttribute("remindStatusList", DictUtils.getDictListJson("remind_status"));
        model.addAttribute("caseSourceList", DictUtils.getDictListJson("case_source"));

        return "businessoperation/businessoperation/wsxdcaseinfo/wsxdCaseForm";
    }

    // 总进展（催记）列表
    @RequestMapping(value = {"findProgressPage"})
    @ResponseBody
    public Page<WsxdRemindRecord> findProgressPage(@RequestBody WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
        return remindRecordService.findPage(new Page<WsxdRemindRecord>(request, response){{setPageNo(wsxdCase.getPageNo());setPageSize(wsxdCase.getPageSize());}}, new WsxdRemindRecord(){{setLoanBillNo(wsxdCase.getLoanBillNo());}});
    }

    // 账户信息列表
    @RequestMapping(value = {"findCardInfoPage"})
    @ResponseBody
    public Page<WsxdCase> findCardInfoPage(@RequestBody WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
        return caseInfoService.findCardInfo(new Page<WsxdCase>(request, response){{setPageNo(wsxdCase.getPageNo());setPageSize(wsxdCase.getPageSize());}}, wsxdCase);
    }

    // 联系电话列表
    @RequestMapping(value = {"findContactPhonePage"})
    @ResponseBody
    public Page<WsxdContactPhone> findContactPhonePage(@RequestBody WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
        return caseInfoService.findContactPhones(new Page<WsxdContactPhone>(request, response){{setPageNo(wsxdCase.getPageNo());setPageSize(wsxdCase.getPageSize());}}, wsxdCase);
    }

    // 联系地址列表
    @RequestMapping(value = {"findContactAddressPage"})
    @ResponseBody
    public Page<WsxdContactAddr> findContactAddressPage(@RequestBody WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
        return caseInfoService.findContactAddrs(new Page<WsxdContactAddr>(request, response){{setPageNo(wsxdCase.getPageNo());setPageSize(wsxdCase.getPageSize());}}, wsxdCase);
    }

    // 还款计划列表
    @RequestMapping(value = {"findRepayPlans"})
    @ResponseBody
    public List<WsxdRepayPlan> findRepayPlans(@RequestBody WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
        return repayPlanService.findList(new WsxdRepayPlan(){{setLoanBillNo(wsxdCase.getLoanBillNo());}});
    }

    @RequestMapping("deductHstList")
    public String deductHstList(String loanBillNo,HttpServletRequest request, HttpServletResponse response, Model model){
        WsxdCase wsxdCase = wsxdCaseService.findByLoanBillNo(loanBillNo);
        Page<DeductHst> page = new Page<>(request,response);
        page = wsxdCaseService.findDeductHstList(wsxdCase, page);
        model.addAttribute("page", page);
        return "businessoperation/businessoperation/wsxdcaseinfo/wsxdDeductHstList";
    }


    // 结清金额查询列表
    @RequestMapping(value = "querySettleHst")
    public String querySettleHst(String loanBillNo, Model model) {
        WsxdCase wsxdCase = wsxdCaseService.findByLoanBillNo(loanBillNo);
        Page<SettleHst> page = new Page<>();
       SettleHst settleHst = wsxdCaseService.querySettleHst(wsxdCase);
        List<SettleHst> list = new ArrayList<>();
        if (null != settleHst) {
            list.add(settleHst);
            page.setList(list);
            page.setCount(1);//只会返回一条记录
        }
        page.setPageNo(1);
        page.setPageSize(30);
        model.addAttribute("page", page);
        return "businessoperation/businessoperation/wsxdcaseinfo/wsxdSettleHstList";
    }

    // 贷款详情查询
    @RequestMapping(value = "loanDetailQuery")
    public String loanDetailQuery(String loanBillNo, Model model) {
        LoanDetailResult  result=requestService.excuteLoanDetailQuery(loanBillNo);
        model.addAttribute("loanBillNo",loanBillNo);
        model.addAttribute("result",result);
        return "businessoperation/businessoperation/wsxdcaseinfo/wsxdDeduct";
    }

    // 发送扣款请求
    @RequestMapping(value = "lanuchDeduct")
    @ResponseBody
    public Map lanuchDeduct(  String loanBillNo, String deductAmount, String maxDeductionAmount) {
        System.out.println(loanBillNo);
        System.out.println(deductAmount);
        System.out.println(maxDeductionAmount);
        BigDecimal amount=new BigDecimal(deductAmount);
        BigDecimal maxAmount=new BigDecimal(maxDeductionAmount);
        DeductResult deductResult=requestService.excuteDeduct(loanBillNo,amount,maxAmount);
        return new HashMap(){{put("msg",deductResult.getDesc());}};
    }


    @RequestMapping(value = "addProgress")
    @ResponseBody
    public Map addProgress(@RequestBody WsxdRemindRecord wsxdRemindRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!beanValidator(model, wsxdRemindRecord)){
            // 数据验证失败，需返回错误
            return new HashMap(){{put("msg",(String) model.asMap().get("message"));put("code","400");}};
        }
        remindRecordService.save(wsxdRemindRecord);
        return new HashMap(){{put("msg","保存案件催记成功");put("code","200");}};
    }

    @RequestMapping(value = "addContactAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map addContactAddress(@RequestBody WsxdContactAddr wsxdContactAddr) {
        contactAddrService.save(wsxdContactAddr);
        return new HashMap(){{put("msg","保存联系人地址成功");put("code","200");}};
    }

    @RequestMapping(value = "addContactPhone",method = RequestMethod.POST)
    @ResponseBody
    public Map addContactPhone(@RequestBody WsxdContactPhone wsxdContactPhone) {
        contactPhoneService.save(wsxdContactPhone);
        return new HashMap(){{put("msg","保存联系人电话成功");put("code","200");}};
    }
}
