package com.plcs.web.wsxd.smsplatform.smssend.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.smsplatform.smssend.entity.ContactBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.CustomerBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsContentPageDTO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsTemplateBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.WsxdSmsContent;
import com.plcs.web.wsxd.smsplatform.smssend.entity.WsxdSmsContentExample;

@MyBatisDao
public interface WsxdSmsContentMapper {
	long countByExample(WsxdSmsContentExample example);

	int deleteByExample(WsxdSmsContentExample example);

	int insert(WsxdSmsContent record);

	int insertSelective(WsxdSmsContent record);

	List<WsxdSmsContent> selectByExample(WsxdSmsContentExample example);

	int updateByExampleSelective(@Param("record") WsxdSmsContent record,
			@Param("example") WsxdSmsContentExample example);

	int updateByExample(@Param("record") WsxdSmsContent record, @Param("example") WsxdSmsContentExample example);

	SmsTemplateBO getSmsTemplate(String id);

	CustomerBO getCustomer(CustomerBO bo);

	List<ContactBO> getContacts(@Param("customerIdNo")String customerIdNo);

	Integer findSendLimit();

	Integer findSendCount(@Param("phone") String phone, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	Long smsContentPageCount(SmsContentPageDTO dto);

	List<WsxdSmsContent> smsContentPage(SmsContentPageBO bo);
	
	List<SmsTemplateBO> getSmsTemplates();
}