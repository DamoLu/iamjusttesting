package com.plcs.web.wsxd.sysmgt.allocatecase.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvGroupVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import com.plcs.web.wsxd.sysmgt.allocatecase.bo.WsxdCaseScopeBo;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分案处理组DAO接口
 * @author tanweijian
 * @version 2019-06-12
 */
@MyBatisDao
public interface WsxdAllocateGroupDao extends CrudDao<WsxdAllocateGroup> {
	List<OdvGroupVO> selectOdvGroupList();
	List<OdvOV> selectOdvList(String odvGroup);

	List<WsxdAllocateGroup> selectAllocateGroupListAndScope(WsxdAllocateGroup allocateGroup);

	List<WsxdAllocateGroup> selectEnabledGroupListAndScope(@Param("id") String id);

	List<WsxdCaseScopeBo> selectCaseScopeList();

	int insertGroup(WsxdAllocateGroup allocateGroup);

//	int updateGroup(WsxdAllocateGroup pojo);

}