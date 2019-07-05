package com.plcs.web.wsxd.smsplatform.smstemplatemgt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.smsplatform.smstemplatemgt.entity.WsxdSmsTemplate;
import com.plcs.web.wsxd.smsplatform.smstemplatemgt.dao.WsxdSmsTemplateDao;

/**
 * 短信模板Service
 * @author luqihua
 * @version 2019-06-11
 */
@Service
@Transactional(readOnly = true)
public class WsxdSmsTemplateService extends CrudService<WsxdSmsTemplateDao, WsxdSmsTemplate> {

	public WsxdSmsTemplate get(String id) {
		return super.get(id);
	}
	
	public List<WsxdSmsTemplate> findList(WsxdSmsTemplate wsxdSmsTemplate) {
		return super.findList(wsxdSmsTemplate);
	}
	
	public Page<WsxdSmsTemplate> findPage(Page<WsxdSmsTemplate> page, WsxdSmsTemplate wsxdSmsTemplate) {
		return super.findPage(page, wsxdSmsTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdSmsTemplate wsxdSmsTemplate) {
		super.save(wsxdSmsTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdSmsTemplate wsxdSmsTemplate) {
		super.delete(wsxdSmsTemplate);
	}
	
}