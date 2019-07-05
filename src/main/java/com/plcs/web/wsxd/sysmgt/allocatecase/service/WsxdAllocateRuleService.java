package com.plcs.web.wsxd.sysmgt.allocatecase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateRule;
import com.plcs.web.wsxd.sysmgt.allocatecase.dao.WsxdAllocateRuleDao;

/**
 * 分案规则排序Service
 * @author luqihua
 * @version 2019-06-25
 */
@Service
@Transactional(readOnly = true)
public class WsxdAllocateRuleService extends CrudService<WsxdAllocateRuleDao, WsxdAllocateRule> {

	public WsxdAllocateRule get(String id) {
		return super.get(id);
	}
	
	public List<WsxdAllocateRule> findList(WsxdAllocateRule wsxdAllocateRule) {
		return super.findList(wsxdAllocateRule);
	}
	
	public Page<WsxdAllocateRule> findPage(Page<WsxdAllocateRule> page, WsxdAllocateRule wsxdAllocateRule) {
		return super.findPage(page, wsxdAllocateRule);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdAllocateRule wsxdAllocateRule) {
		super.save(wsxdAllocateRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdAllocateRule wsxdAllocateRule) {
		super.delete(wsxdAllocateRule);
	}
	
}