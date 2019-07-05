package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRemindRecord;

import java.util.List;

/**
 * 案件催记DAO接口
 * @author tanweijian
 * @version 2019-06-11
 */
@MyBatisDao
public interface WsxdRemindRecordDao extends CrudDao<WsxdRemindRecord> {
	List<String> selectCaseStatusList();
}