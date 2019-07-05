package com.plcs.web.wsxd.sysmgt.allocatecase.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分案处理组案件范围DAO接口
 * @author andyhuang
 * @version 2019-06-17
 */
@MyBatisDao
public interface WsxdAllocateGroupScopeDao extends CrudDao<WsxdAllocateGroupScope> {
    int batchInsertAllocateGroupScope(List<WsxdAllocateGroupScope> allocateGroupScope);

    int deleteByGroupId(@Param("groupId") String groupId);
}