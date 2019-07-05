package com.plcs.web.wsxd.smsplatform.smstemplatemgt.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.smsplatform.smstemplatemgt.entity.WsxdSmsTemplate;

/**
 * 短信模板DAO接口
 * @author luqihua
 * @version 2019-06-11
 */
@MyBatisDao
public interface WsxdSmsTemplateDao extends CrudDao<WsxdSmsTemplate> {
	
}