package com.plcs.web.wsxd.smsplatform.smssend.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plcs.web.wsxd.smsplatform.smssend.entity.GetContactsDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetContactsVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetTemplateDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetTemplateVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetTemplatesVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.ResultVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SendSmsByTemplateDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageVO;
import com.plcs.web.wsxd.smsplatform.smssend.service.SmsSendService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/sms")
public class SmsSendController {
	
	@Autowired
	private SmsSendService smsSendService;
	
	@ApiOperation(value = "以客户手机号获取其联系人")
	@RequestMapping(value = "/getContacts", method = RequestMethod.POST)
	@ResponseBody
	public GetContactsVO getContacts(@RequestBody GetContactsDTO dto) {
		GetContactsVO result = smsSendService.getContacts(dto);
		return result;
	}
	
	@ApiOperation(value = "获取当前所有模板")
	@RequestMapping(value = "/getTemplates", method = RequestMethod.POST)
	@ResponseBody
	public GetTemplatesVO getTemplates() {
		GetTemplatesVO result = smsSendService.getTemplates();
		return result;
	}
	
	@ApiOperation(value = "以id获取信息模板")
	@RequestMapping(value = "/getTemplate", method = RequestMethod.POST)
	@ResponseBody
	public GetTemplateVO getTemplate(@RequestBody GetTemplateDTO dto) {
		GetTemplateVO result = smsSendService.getTemplate(dto);
		return result;
	}
	
	@ApiOperation(value = "分页获取短信记录")
	@RequestMapping(value = "/getSmsContentPage", method = RequestMethod.POST)
	@ResponseBody
	public SmsContentPageVO getSmsContentPage(@RequestBody SmsContentPageDTO dto) {
		SmsContentPageVO result = smsSendService.getSmsContentPage(dto);
		return result;
	}
	
	@ApiOperation(value = "发送模板短信")
	@RequestMapping(value = "/sendSmsByTemplate", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO sendSmsByTemplate(@RequestBody SendSmsByTemplateDTO dto) {
		ResultVO result = smsSendService.sendSmsByTemplate(dto);
		return result;
	}
	
	@RequestMapping(value = "/sendSMSView", method = RequestMethod.GET)
	public ModelAndView sendSMSView(@RequestParam String loanBillNo) {
		ModelAndView v = smsSendService.buildSendSmsView(loanBillNo);
		
		return v;
	}
	
	@RequestMapping(value = "/getSmsSubTable", method = RequestMethod.POST)
	public ModelAndView getSmsSubTable(@RequestBody SmsContentPageDTO dto) {
		ModelAndView v = smsSendService.getSmsSubTable(dto);
		return v;
	}
	
	
}
