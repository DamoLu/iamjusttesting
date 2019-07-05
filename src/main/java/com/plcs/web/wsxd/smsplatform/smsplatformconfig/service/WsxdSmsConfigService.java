package com.plcs.web.wsxd.smsplatform.smsplatformconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.smsplatform.smsplatformconfig.entity.WsxdSmsConfig;
import com.plcs.web.wsxd.smsplatform.smsplatformconfig.dao.WsxdSmsConfigDao;

/**
 * 短信平台配置Service
 * @author luqihua
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class WsxdSmsConfigService extends CrudService<WsxdSmsConfigDao, WsxdSmsConfig> {

	public WsxdSmsConfig get(String id) {
		return super.get(id);
	}
	
	public List<WsxdSmsConfig> findList(WsxdSmsConfig wsxdSmsConfig) {
		return super.findList(wsxdSmsConfig);
	}
	
	public Page<WsxdSmsConfig> findPage(Page<WsxdSmsConfig> page, WsxdSmsConfig wsxdSmsConfig) {
		return super.findPage(page, wsxdSmsConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdSmsConfig wsxdSmsConfig) {
		super.save(wsxdSmsConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdSmsConfig wsxdSmsConfig) {
		super.delete(wsxdSmsConfig);
	}
	
}