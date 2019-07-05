package com.plcs.web.wsxd.smsplatform.smsplatformconfig.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.smsplatform.smsplatformconfig.entity.WsxdSmsConfig;

/**
 * 短信平台配置DAO接口
 * @author luqihua
 * @version 2019-06-18
 */
@MyBatisDao
public interface WsxdSmsConfigDao extends CrudDao<WsxdSmsConfig> {
	
}