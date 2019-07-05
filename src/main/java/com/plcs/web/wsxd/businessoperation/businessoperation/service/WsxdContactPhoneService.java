package com.plcs.web.wsxd.businessoperation.businessoperation.service;

import com.plcs.web.common.utils.RandomUtil;
import com.plcs.web.wsxd.businessoperation.businessoperation.dao.WsxdContactPhoneDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdContactPhone;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.ContactDataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 联系人地址Service
 *
 * @author zhengjiangbo
 * @version 2019-06-25
 */
@Service
@Transactional
public class WsxdContactPhoneService {

    @Autowired
    WsxdContactPhoneDao wsxdContactPhoneDao;

    @Autowired
    WsxdCaseService wsxdCaseService;


    public void save(WsxdContactPhone wsxdContactPhone) {
        WsxdCase queryModel=new WsxdCase();
        queryModel.setLoanBillNo(wsxdContactPhone.getLoanBillNo());
        List<WsxdCase> resultList=wsxdCaseService.findList(queryModel);
        if(resultList!=null && resultList.size()>0){
            String customerIdNo=resultList.get(0).getCustomerIdNo();
            wsxdContactPhone.setCustomerIdNo(customerIdNo);
            wsxdContactPhone.setId(RandomUtil.randomUUID());
            wsxdContactPhone.setDataSource(ContactDataSourceEnum.MANUAL_CREATE.getCode());
            wsxdContactPhone.setCreateDate(new Date());
            wsxdContactPhone.setUpdateDate(new Date());
            wsxdContactPhone.setDelFlag("0");
            wsxdContactPhoneDao.insert(wsxdContactPhone);
        }

    }
}
