package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdCaseDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdContactAddrDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdContactPhoneDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdContactAddr;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdContactPhone;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRemindRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务操作查看页面Controller
 */
@Service
@Transactional(readOnly = true)
public class WsxdCaseInfoService extends CrudService<WsxdCaseDao, WsxdCase> {
    @Autowired
    private WsxdCaseDao wsxdCaseDao;
    @Autowired
    private WsxdContactPhoneDao wsxdContactPhoneDao;
    @Autowired
    private WsxdContactAddrDao wsxdContactAddrDao;

    // 根据借据号查询借据的基本信息
    public WsxdCase findBaseInfo(String loanBillNo) {
        return this.wsxdCaseDao.findBaseInfo(loanBillNo);
    }

    // 根据借据号查询对应客户的所有账户信息
    public Page<WsxdCase> findCardInfo(Page<WsxdCase> page, WsxdCase wsxdCase) {
        wsxdCase.setPage(page);
        page.setList(wsxdCaseDao.findCardInfo(wsxdCase));
        return page;
    }

    // 根据借据号查询对应客户的所有联系电话
    public Page<WsxdContactPhone> findContactPhones(Page<WsxdContactPhone> page, WsxdCase wsxdCase) {
        wsxdCase.setPage(null);
        page.setCount(wsxdContactPhoneDao.findContactPhones(wsxdCase).size());
        wsxdCase.setPage(new Page<WsxdCase>(){{setPageNo(page.getPageNo());setPageSize(page.getPageSize());}});
        page.setList(wsxdContactPhoneDao.findContactPhones(wsxdCase));
        return page;
    }

    // 根据借据号查询对应客户的所有联系地址
    public Page<WsxdContactAddr> findContactAddrs(Page<WsxdContactAddr> page, WsxdCase wsxdCase) {
        wsxdCase.setPage(null);
        page.setCount(wsxdContactAddrDao.findContactAddrs(wsxdCase).size());
        wsxdCase.setPage(new Page<WsxdCase>(){{setPageNo(page.getPageNo());setPageSize(page.getPageSize());}});
        page.setList(wsxdContactAddrDao.findContactAddrs(wsxdCase));
        return page;
    }
}
