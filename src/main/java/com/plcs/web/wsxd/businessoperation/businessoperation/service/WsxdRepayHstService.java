package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdRepayHstDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRepayHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.RepayHstVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 还款记录Service
 * @author tanweijian
 * @version 2019-06-27
 */
@Service
@Transactional(readOnly = true)
public class WsxdRepayHstService extends CrudService<WsxdRepayHstDao, WsxdRepayHst> {

	@Autowired
	private WsxdRepayHstDao wsxdRepayHstDao;

	public WsxdRepayHst get(String id) {
		return super.get(id);
	}
	
	public List<WsxdRepayHst> findList(WsxdRepayHst wsxdRepayHst) {
		return super.findList(wsxdRepayHst);
	}
	
	public Page<WsxdRepayHst> findPage(Page<WsxdRepayHst> page, WsxdRepayHst wsxdRepayHst) {
		return super.findPage(page, wsxdRepayHst);
	}
	
	@Transactional(readOnly = false)
	public void save(WsxdRepayHst wsxdRepayHst) {
		super.save(wsxdRepayHst);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsxdRepayHst wsxdRepayHst) {
		super.delete(wsxdRepayHst);
	}

	public List<RepayHstVO> findByLoanBillNo(String loanBillNo) {
		return wsxdRepayHstDao.selectByLoanBillNo(loanBillNo);
	}
}