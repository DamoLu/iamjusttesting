package com.plcs.web.wsxd.smsplatform.smssend.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.IdGen;
import com.plcs.web.modules.sys.entity.User;
import com.plcs.web.modules.sys.utils.DictUtils;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.smsplatform.smssend.dao.WsxdSmsContentMapper;
import com.plcs.web.wsxd.smsplatform.smssend.entity.*;
import com.plcs.web.wsxd.smsplatform.smssend.service.util.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SmsSendService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WsxdSmsContentMapper smsContentMapper;
	@Autowired
	private SmsSender smsSender;
	@Autowired
	private IdGen idGen;
	
	public ResultVO sendSmsByTemplate(SendSmsByTemplateDTO dto) {
		ResultVO vo = new ResultVO();
		User user = UserUtils.getUser();
		if(user == null) {
			vo.fail(-1, "获取发送人信息异常");
			return vo;
		}
		
		SmsTemplateBO template = smsContentMapper.getSmsTemplate(dto.getTemplateId());
		if(template == null) {
			logger.error("找不到此短信模板 {}", dto.getTemplateId());
			vo.fail(-1, "找不到此短信模板");
			return vo;
		}
		
		CustomerBO customer = new CustomerBO();
		customer.setPhone(dto.getCustomerPhone());
		
		List<CustomerBO> customerList = smsContentMapper.getCustomer(customer);
		customer = null;
		if(customerList != null && customerList.size() > 0) {
			customer = customerList.get(0);
		}
		if(customer == null) {
			logger.error("找不到此客户 {}", dto.getCustomerPhone());
			vo = vo.fail(-1, "找不到此客户");
			return vo;
		}
		
		List<String> mobiles = dto.getMobiles();
		Integer sendLimit = smsContentMapper.findSendLimit();
		if(sendLimit == null) {
			logger.error("查询配置发送短信的限制数量失败");
			vo = vo.fail(-1, "没有配置发送短信的限制数量");
			return vo;
		}

		//发送短信
		SmsSendResultBO result = null;
		String content = null;
		int successCount = 0, failedCount = 0;
		for(String m : mobiles) {
			content = buildSmsContent(customer, template, dto);
			if(canSend(m, sendLimit)) {
				result = smsSender.send( m, content,dto.getLoanBillNo());
				if(result.isFlag()) {
					insertNewRecord(m, dto.getLoanBillNo(), user, content, SmsSendStatusType.success.getCode(), template.getName());
					successCount++;
				} else {
					insertNewRecord(m, dto.getLoanBillNo(), user, content, SmsSendStatusType.fail.getCode(), template.getName());
					failedCount++;
				}
			} else {
				insertNewRecord(m, dto.getLoanBillNo(), user, content, SmsSendStatusType.limit.getCode(), template.getName());
				failedCount++;
			}
		}

		if(successCount == mobiles.size()) {
			vo = vo.success("发送成功");
		} else {
			logger.error("发送成功:  {}条  发送失败: {}条, \n借据号: {} 发送人: {}  发送内容:{} ", successCount, failedCount, dto.getLoanBillNo(), user.getName(), content);
			String errMsg = String.format("发送成功:  %s条\n发送失败:  %s条", successCount, failedCount);
			vo = vo.fail(-3, errMsg);
		}
		return vo;
	}
	
	public GetContactsVO getContacts(GetContactsDTO dto) {
		GetContactsVO v = new GetContactsVO();

		CustomerBO customer = new CustomerBO();
		customer.setPhone(dto.getCustomerPhone());

		List<CustomerBO> customerList = smsContentMapper.getCustomer(customer);
		customer = null;
		if(customerList != null && customerList.size() > 0) {
			customer = customerList.get(0);
		}
		if (customer == null) {
			v.setSuccess(false);
			v.setCode(-1);
			v.setMsg("找不到此客户");
			return v;
		}

		List<ContactBO> contacts = smsContentMapper.getContacts(customer.getCustomerIdNo());
		if (contacts != null && contacts.size() > 0) {
			for (ContactBO contact : contacts) {
				contact.setRelation(DictUtils.getDictLabel(contact.getRelation(), "relationship", contact.getRelation()));
			}
		}
		v.setContacts(contacts);

		return v;
	}

	private String buildSmsContent(CustomerBO customer, SmsTemplateBO t, SendSmsByTemplateDTO dto) {
		String result = t.getContent();
//		result = result.replaceAll("《客戶姓名》", customer.getCustomerName());
		result = result.replaceAll("《客户姓名》", customer.getCustomerName());
		if(GenderType.female.getCode().toString().equals(customer.getGender())) {
			result = result.replaceAll("《称谓》", GenderType.female.getName());
		} else {
			result = result.replaceAll("《称谓》", GenderType.male.getName());
		}
		result = result.replaceAll("《业务员号码》", dto.getBusinessPhone());
		
		return result;
	}

	private void insertNewRecord(String phone, String loanBillNo, User user, String content, Integer status, String templateName) {
		WsxdSmsContent c = new WsxdSmsContent();
		c.setId(idGen.getNextId());
		c.setLoanBillNo(loanBillNo);
		c.setTemplateName(templateName);
		c.setPhone(phone);
		c.setContent(content);
		c.setSenderId(String.valueOf(user.getId()));
		c.setSenderName(user.getName());
		c.setSendTime(new Date());
		c.setCreateBy(String.valueOf(user.getId()));
		c.setCreateDate(new Date());
		c.setUpdateBy(String.valueOf(user.getId()));
		c.setUpdateDate(new Date());
		c.setStatus(status);
		c.setDelFlag("0");

		try{
			smsContentMapper.insertSelective(c);
		} catch (Exception e){
			logger.error("短信内容表新增记录失败: ", e);
		}
	}

	@SuppressWarnings("deprecation")
	private boolean canSend(String phone, Integer limit) {
		Date startDate = new Date();
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		Integer sendCount = smsContentMapper.findSendCount(phone, startDate, new Date());
		if(sendCount == null || sendCount < limit) {
			return true;
		} else {
			logger.info("发送对象: {} 已达到今天的发送数量上限: {}条 ", phone, limit);
			return false;
		}
	}
	
	public GetTemplatesVO getTemplates() {
		List<SmsTemplateBO> templates = smsContentMapper.getSmsTemplates();
		GetTemplatesVO v = new GetTemplatesVO();
		v.setSuccess(true);
		v.setCode(0);
		v.setTemplates(templates);

		return v;
	}
	
	public GetTemplateVO getTemplate(GetTemplateDTO dto) {
		GetTemplateVO v = new GetTemplateVO();
		SmsTemplateBO template = smsContentMapper.getSmsTemplate(dto.getTemplateId());
		if(template == null) {
			v.setCode(-1);
			v.setMsg("没有此模板");
		} else {
			v.setCode(0);
			v.setTemplate(template);
		}
		return v;
		
	}
	
	public SmsContentPageVO getSmsContentPage(SmsContentPageDTO dto) {
		SmsContentPageVO v = new SmsContentPageVO();
		v.setSuccess(false);
//		if(StringUtils.isBlank(dto.getLoanBillNo())) {
//			v.setCode(-2);
//			v.setMsg("缺少客户借据号");
//		}
		
//		GetContactsDTO getContactsDTO = new GetContactsDTO();
//		getContactsDTO.setCustomerPhone(dto.getPhone());
//		GetContactsVO getContacts = getContacts(getContactsDTO);
//		dto.setPhoneList(getContacts.getContacts().stream().map(ContactBO::getPhone).collect(Collectors.toList()));
		Long count = smsContentMapper.smsContentPageCount(dto);
		
		v.setCode(0);
		v.setSuccess(true);
		v.setResultCount(count);

		SmsContentPageBO bo = new SmsContentPageBO();
		BeanUtils.copyProperties(dto, bo);
		PageParam p = setPageFromPageNo(dto.getPageNo(), dto.getPageSize());
		bo.setPageStart(p.getPageStart());
		bo.setPageEnd(p.getPageEnd());
		List<WsxdSmsContent> contentPage = smsContentMapper.smsContentPage(bo);
		List<SmsContentVO> voPage = new ArrayList<SmsContentVO>();
		SmsContentVO tmpVO = null;
		for(WsxdSmsContent i : contentPage) {
			tmpVO = new SmsContentVO();
			BeanUtils.copyProperties(i, tmpVO);
			SmsSendStatusType type = SmsSendStatusType.getType(i.getStatus());
			if(type != null) {
				tmpVO.setStatus(type.getName());
			}

			voPage.add(tmpVO);
		}
		v.setSmsContentList(voPage);
		
		return v;
	}
	
	public ModelAndView getSmsSubTable(SmsContentPageDTO dto) {
		ModelAndView v = new ModelAndView("/sms/smsSend/sendSMSSubTable");
		
//		v.addObject("customerPhone", dto.getPhone());
		
		SmsContentPageDTO smsContentPageDTO = new SmsContentPageDTO();
		smsContentPageDTO.setPageNo(dto.getPageNo());
		smsContentPageDTO.setPageSize(dto.getPageSize());
		smsContentPageDTO.setPhone(dto.getPhone());
		smsContentPageDTO.setLoanBillNo(dto.getLoanBillNo());
		if(dto.getStatus()!=null) {
			smsContentPageDTO.setStatus(dto.getStatus());
		}
		
		SmsContentPageVO smsContentPageResult = getSmsContentPage(smsContentPageDTO);
		Page<SmsContentVO> page = new Page<SmsContentVO>(
				smsContentPageDTO.getPageNo().intValue(),
				smsContentPageDTO.getPageSize().intValue(),
				smsContentPageResult.getResultCount(),
				smsContentPageResult.getSmsContentList()
				);
		page.setFuncName("page");
		
		v.addObject("page", page);
		List<Long> pageNumberList = new ArrayList<Long>();
		Long pageMax = smsContentPageResult.getResultCount() / smsContentPageDTO.getPageSize() + 1;
		for(Integer i = 1; i <= pageMax; i++) {
			pageNumberList.add(i.longValue());
		}
		v.addObject("pageNumberList", pageNumberList);
		v.addObject("smsContentList", smsContentPageResult.getSmsContentList());
		
		return v;
	}
	
	protected PageParam setPageFromPageNo(Long pageNo) {
		return setPageFromPageNo(pageNo, 10L);
	}

	protected PageParam setPageFromPageNo(Long pageNo, Long pageSize) {
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1L;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 1L;
		}
		if (pageSize > 10L) {
			pageSize = 10L;
		}
		PageParam pp = new PageParam();
		if (pageNo == 1) {
			pp.setPageStart(0L);
			pp.setPageEnd(pageSize);
		} else if (pageNo > 1) {
			pp.setPageStart(pageSize * (pageNo - 1));
			pp.setPageEnd(pp.getPageStart() + pageSize);
		}
		pp.setPageSize(pageSize);
		return pp;
	}
	
	public ModelAndView buildSendSmsView(String loanBillNo) {
		ModelAndView v = new ModelAndView("/sms/smsSend/sendSMS");
		
		CustomerBO customer = new CustomerBO();
		customer.setLoanBillNo(loanBillNo);

		List<CustomerBO> customerList = smsContentMapper.getCustomer(customer);
		customer = null;
		if(customerList != null && customerList.size() > 0) {
			customer = customerList.get(0);
		}
		if(customer == null) {
			v.addObject("errMsg", "找不到此客户");
			return v;
		}

		Integer sendLimit = smsContentMapper.findSendLimit();
		if(sendLimit == null) {
			v.addObject("errMsg","没有配置发送短信的限制数量");
			return v;
		}
		v.addObject("sendLimit",sendLimit);
		v.addObject("customerPhone", customer.getPhone());
		
		GetContactsDTO dto = new GetContactsDTO();
		dto.setCustomerPhone(customer.getPhone());
		GetContactsVO result = getContacts(dto);
		v.addObject("mobiles", result.getContacts());
		
		GetTemplatesVO templeates = getTemplates();
		v.addObject("smsTemplateList", templeates.getTemplates());
		
		SmsContentPageDTO smsContentPageDTO = new SmsContentPageDTO();
		smsContentPageDTO.setPageNo(1L);
		smsContentPageDTO.setPageSize(10L);
		smsContentPageDTO.setPhone(customer.getPhone());
		smsContentPageDTO.setLoanBillNo(loanBillNo);
		SmsContentPageVO smsContentPageResult = getSmsContentPage(smsContentPageDTO);
		Page<SmsContentVO> page = new Page<SmsContentVO>(
				smsContentPageDTO.getPageNo().intValue(),
				smsContentPageDTO.getPageSize().intValue(),
				smsContentPageResult.getResultCount(),
				smsContentPageResult.getSmsContentList()
				);
		page.setFuncName("page");
		
		v.addObject("page", page);
		List<Long> pageNumberList = new ArrayList<Long>();
		Long pageMax = smsContentPageResult.getResultCount() / smsContentPageDTO.getPageSize() + 1;
		for(Integer i = 1; i <= pageMax; i++) {
			pageNumberList.add(i.longValue());
		}
		v.addObject("pageNumberList", pageNumberList);
		v.addObject("smsContentList", smsContentPageResult.getSmsContentList());
		
		return v;
	}
	
}
