package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.modules.sys.entity.User;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdRemindRecordDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRemindRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 案件催记Service
 * @author tanweijian
 * @version 2019-06-11
 */
@Service
@Transactional(readOnly = true)
public class WsxdRemindRecordService extends CrudService<WsxdRemindRecordDao, WsxdRemindRecord> {

	@Autowired
	WsxdRemindRecordDao wsxdRemindRecordDao;

	public WsxdRemindRecord get(String id) {
		return super.get(id);
	}
	
	public List<WsxdRemindRecord> findList(WsxdRemindRecord wsxdRemindRecord) {
		return super.findList(wsxdRemindRecord);
	}
	
	public Page<WsxdRemindRecord> findPage(Page<WsxdRemindRecord> page, WsxdRemindRecord wsxdRemindRecord) {
		return super.findPage(page, wsxdRemindRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdRemindRecord wsxdRemindRecord) {
		if(wsxdRemindRecord.getCreateTime()==null) {
			wsxdRemindRecord.setCreateTime(new Date());
		}
		User user = UserUtils.getUser();
		wsxdRemindRecord.setOdv(user.getName());
		super.save(wsxdRemindRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdRemindRecord wsxdRemindRecord) {
		super.delete(wsxdRemindRecord);
	}

	public List<String> findCaseStatusList() {
		return wsxdRemindRecordDao.selectCaseStatusList();
	}
}