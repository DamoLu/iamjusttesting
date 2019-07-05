package com.plcs.web.wsxd.sysmgt.allocatecase.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupScopeHst;

import java.util.List;

/**
 * 分案处理组案件范围历史DAO接口
 * @author andyhuang
 * @version 2019-06-24
 */
@MyBatisDao
public interface WsxdAllocateGroupScopeHstDao extends CrudDao<WsxdAllocateGroupScopeHst> {
    int batchInsertGroupScopeHst(List<WsxdAllocateGroupScopeHst> allocateGroupScopeHstList);
}