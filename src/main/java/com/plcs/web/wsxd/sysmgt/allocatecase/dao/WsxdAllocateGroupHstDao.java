package com.plcs.web.wsxd.sysmgt.allocatecase.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupHst;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupScopeHst;

/**
 * 分案处理组历史DAO接口
 *
 * @author andyhuang
 * @version 2019-06-17
 */
@MyBatisDao
public interface WsxdAllocateGroupHstDao extends CrudDao<WsxdAllocateGroupHst> {
    int insert(WsxdAllocateGroupScopeHst wsxdAllocateGroupScopeHst);
}