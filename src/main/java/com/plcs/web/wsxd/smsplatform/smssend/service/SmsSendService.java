package com.plcs.web.wsxd.smsplatform.smssend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
 
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.IdGen;
import com.plcs.web.modules.sys.entity.User;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.smsplatform.smssend.dao.WsxdSmsContentMapper;
import com.plcs.web.wsxd.smsplatform.smssend.entity.ContactBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.CustomerBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GenderType;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetContactsDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetContactsVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetTemplateDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetTemplateVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.GetTemplatesVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.PageParam;
import com.plcs.web.wsxd.smsplatform.smssend.entity.ResultVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SendSmsByTemplateDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentVO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsSendResultBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsSendStatusType;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsTemplateBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.WsxdSmsContent;
import com.plcs.web.wsxd.smsplatform.smssend.service.util.SmsSender;

@Service
public class SmsSendService {

	@Autowired
	private WsxdSmsContentMapper smsContentMapper;
	@Autowired
	private SmsSender smsSender;
	@Autowired
	private IdGen idGen;

	@Value("${envName}")
	private String envName;
	
	public ResultVO sendSmsByTemplate(SendSmsByTemplateDTO dto) {
		ResultVO vo = new ResultVO();
		User user = UserUtils.getUser();
		if(user == null) {
//			TODO dev
			if("dev".equals(envName)) {
				user = new User();
				user.setId("123");
				user.setName("testUser");
			} else {
//				TODO
			}
		}
		
		SmsTemplateBO template = smsContentMapper.getSmsTemplate(dto.getTemplateId());
		if(template == null) {
			vo.fail(-1, "找不到此短信模板");
			return vo;
		}
		
		CustomerBO customer = new CustomerBO();
		customer.setPhone(dto.getCustomerPhone());
		
		customer = smsContentMapper.getCustomer(customer);
		if(customer == null) {
			vo = vo.fail(-1, "找不到此客户");
			return vo;
		}
		
		List<String> mobiles = dto.getMobiles();
		Integer sendLimit = smsContentMapper.findSendLimit();
		if(sendLimit == null) {
			sendLimit = 1;
		}

		SmsSendResultBO result = null;
		String content = null;
		for(String m : mobiles) {
			if(canSend(m, sendLimit)) {
				content = buildSmsContent(customer, template, dto);
				result = smsSender.send(envName, m, content);
				if(result.isFlag()) {
					insertNewRecord(m, user, content, SmsSendStatusType.success.getCode().toString(), template.getName());
				} else {
					insertNewRecord(m, user, content, SmsSendStatusType.fail.getCode().toString(), template.getName());
				}
			} else {
				insertNewRecord(m, user, content, SmsSendStatusType.limit.getCode().toString(), template.getName());
			}
		}
		
		if(result.isFlag()) {
			vo = vo.success("发送成功");
		} else {
			vo = vo.fail(-3, "发送失败");
		}
		return vo;
	}
	
	public GetContactsVO getContacts(GetContactsDTO dto) {
		GetContactsVO v = new GetContactsVO();

		CustomerBO customer = new CustomerBO();
		customer.setPhone(dto.getCustomerPhone());

		customer = smsContentMapper.getCustomer(customer);
		if (customer == null) {
			v.setSuccess(false);
			v.setCode(-1);
			v.setMsg("找不到此客户");
			return v;
		}

		List<ContactBO> contacts = smsContentMapper.getContacts(customer.getCustomerIdNo());
		v.setContacts(contacts);

		return v;
	}

	private String buildSmsContent(CustomerBO customer, SmsTemplateBO t, SendSmsByTemplateDTO dto) {
		String result = t.getContent();
		result = result.replaceAll("《客戶姓名》", customer.getCustomerName());
		if(GenderType.female.getCode().toString().equals(customer.getGender())) {
			result = result.replaceAll("《称谓》", GenderType.female.getName());
		} else {
			result = result.replaceAll("《称谓》", GenderType.male.getName());
		}
		result = result.replaceAll("《业务员号码》", dto.getBusinessPhone());
		
		return result.toString();
	}

	private void insertNewRecord(String phone, User user, String content, String status, String templateName) {
		WsxdSmsContent c = new WsxdSmsContent();
		c.setId(idGen.getNextId());
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
		
		smsContentMapper.insertSelective(c);
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
		if(StringUtils.isBlank(dto.getPhone())) {
			v.setCode(-2);
			v.setMsg("请输入客户手机号");
		}
		
		GetContactsDTO getContactsDTO = new GetContactsDTO();
		getContactsDTO.setCustomerPhone(dto.getPhone());
		GetContactsVO getContacts = getContacts(getContactsDTO);
		dto.setPhoneList(getContacts.getContacts().stream().map(ContactBO::getPhone).collect(Collectors.toList()));
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
			voPage.add(tmpVO);
		}
		v.setSmsContentList(voPage);
		
		return v;
	}
	
	public ModelAndView getSmsSubTable(SmsContentPageDTO dto) {
		ModelAndView v = new ModelAndView("/sms/smsSend/sendSMSSubTable");
		
		SmsContentPageDTO smsContentPageDTO = new SmsContentPageDTO();
		smsContentPageDTO.setPageNo(dto.getPageNo());
		smsContentPageDTO.setPageSize(dto.getPageSize());
		smsContentPageDTO.setPhone(dto.getPhone());
		smsContentPageDTO.setStatus(dto.getStatus());
		
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

		customer = smsContentMapper.getCustomer(customer);
		if(customer == null) {
			v.addObject("message", "找不到此客户");
			return v;
		}
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
