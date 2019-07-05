package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRepayPlan;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdRepayPlanDao;

/**
 * 还款计划Service
 * @author moyue
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class WsxdRepayPlanService extends CrudService<WsxdRepayPlanDao, WsxdRepayPlan> {

	public WsxdRepayPlan get(String id) {
		return super.get(id);
	}
	
	public List<WsxdRepayPlan> findList(WsxdRepayPlan wsxdRepayPlan) {
		return super.findList(wsxdRepayPlan);
	}
	
	public Page<WsxdRepayPlan> findPage(Page<WsxdRepayPlan> page, WsxdRepayPlan wsxdRepayPlan) {
		return super.findPage(page, wsxdRepayPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdRepayPlan wsxdRepayPlan) {
		super.save(wsxdRepayPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdRepayPlan wsxdRepayPlan) {
		super.delete(wsxdRepayPlan);
	}
	
}