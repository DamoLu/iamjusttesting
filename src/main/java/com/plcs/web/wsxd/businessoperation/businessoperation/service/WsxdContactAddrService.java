package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.utils.RandomUtil;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdContactAddrDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdContactAddr;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.ContactDataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 联系人地址Service
 *
 * @author zhengjiangbo
 * @version 2019-06-25
 */
@Service
@Transactional
public class WsxdContactAddrService  {

    @Autowired
    WsxdContactAddrDao wsxdContactAddrDao;

    @Autowired
    WsxdCaseService wsxdCaseService;


    public void save(WsxdContactAddr wsxdContactAddr) {
        WsxdCase queryModel=new WsxdCase();
        queryModel.setLoanBillNo(wsxdContactAddr.getLoanBillNo());
        List<WsxdCase> resultList=wsxdCaseService.findList(queryModel);
        if(resultList!=null && resultList.size()>0){
            String customerIdNo=resultList.get(0).getCustomerIdNo();
            wsxdContactAddr.setCustomerIdNo(customerIdNo);
            wsxdContactAddr.setId(RandomUtil.randomUUID());
            wsxdContactAddr.setDataSource(ContactDataSourceEnum.MANUAL_CREATE.getCode());
            wsxdContactAddrDao.insert(wsxdContactAddr);
        }

    }
}
