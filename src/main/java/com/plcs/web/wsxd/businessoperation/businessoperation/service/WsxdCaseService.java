package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdCaseDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务操作Service
 * @author tanweijian
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class WsxdCaseService extends CrudService<WsxdCaseDao, WsxdCase> {

	@Autowired
	WsxdCaseDao wsxdCaseDao;

	public WsxdCase get(String id) {
		return super.get(id);
	}
	
	public List<WsxdCase> findList(WsxdCase wsxdCase) {
		return super.findList(wsxdCase);
	}
	
	public Page<WsxdCase> findPage(Page<WsxdCase> page, WsxdCase wsxdCase) {
		return super.findPage(page, wsxdCase);
	}

	public List<WsxdCase> findAllList() {
		return wsxdCaseDao.findAllList();
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdCase wsxdCase) {
		super.save(wsxdCase);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdCase wsxdCase) {
		super.delete(wsxdCase);
	}

	// 获取事业部列表
	public List<DepartmentVO> findDepartmentList() {
		return wsxdCaseDao.selectDepartmentList();
	}

	// 获取放款机构列表
	public List<String> findLoanOrginList() {
		return wsxdCaseDao.selectLoanOrginList();
	}

	// 获取客户经理列表
	public List<ManagerVO> findManagerList() {
		return wsxdCaseDao.selectManagerList();
	}

	// 获取产品名称列表
	public List<String> findAppNameList() {
		return wsxdCaseDao.selectAppNameList();
	}
}