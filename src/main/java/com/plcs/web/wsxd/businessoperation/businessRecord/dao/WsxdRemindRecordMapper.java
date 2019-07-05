package com.plcs.web.wsxd.businessoperation.businessRecord.dao;

import java.util.List;

import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroup;
import org.apache.ibatis.annotations.Param;

import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.SysDictBO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.WsxdRemindRecordBO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.WsxdRemindRecordBOExample;

@MyBatisDao
public interface WsxdRemindRecordMapper {
	long countByExample(WsxdRemindRecordBOExample example);

    int deleteByExample(WsxdRemindRecordBOExample example);

    int insert(WsxdRemindRecordBO record);

    int insertSelective(WsxdRemindRecordBO record);

    List<WsxdRemindRecordBO> selectByExampleWithBLOBs(WsxdRemindRecordBOExample example);

    List<WsxdRemindRecordBO> selectByExample(WsxdRemindRecordBOExample example);

    int updateByExampleSelective(@Param("record") WsxdRemindRecordBO record, @Param("example") WsxdRemindRecordBOExample example);

    int updateByExampleWithBLOBs(@Param("record") WsxdRemindRecordBO record, @Param("example") WsxdRemindRecordBOExample example);

    int updateByExample(@Param("record") WsxdRemindRecordBO record, @Param("example") WsxdRemindRecordBOExample example);
    
    List<SysDictBO> findAllCaseStatusFromSysDict();
    
    List<SysDictBO> findAllPhoneStatusFromSysDict();
    
    List<WsxdAllocateGroup> findGroupNameByUserName(String userName);
}