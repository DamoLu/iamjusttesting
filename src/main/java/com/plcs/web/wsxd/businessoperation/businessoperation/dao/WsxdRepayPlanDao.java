package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRepayPlan;

/**
 * 还款计划DAO接口
 * @author moyue
 * @version 2019-06-18
 */
@MyBatisDao
public interface WsxdRepayPlanDao extends CrudDao<WsxdRepayPlan> {
	
}