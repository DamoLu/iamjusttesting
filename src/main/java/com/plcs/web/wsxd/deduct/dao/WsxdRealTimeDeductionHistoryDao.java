package com.plcs.web.wsxd.deduct.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.deduct.entity.WsxdRealTimeDeductionHistory;

/**
 * 保存所发送的扣款请求历史DAO接口
 * @author zhengjiangbo
 * @version 2019-07-18
 */
@MyBatisDao
public interface WsxdRealTimeDeductionHistoryDao extends CrudDao<WsxdRealTimeDeductionHistory> {

}