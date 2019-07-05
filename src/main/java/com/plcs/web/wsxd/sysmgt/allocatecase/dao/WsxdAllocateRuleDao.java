package com.plcs.web.wsxd.sysmgt.allocatecase.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateRule;

/**
 * 分案规则排序DAO接口
 * @author luqihua
 * @version 2019-06-25
 */
@MyBatisDao
public interface WsxdAllocateRuleDao extends CrudDao<WsxdAllocateRule> {
	
}