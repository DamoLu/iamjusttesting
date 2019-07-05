package com.plcs.web.wsxd.businessoperation.businessRecord.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plcs.web.common.utils.IdGen;
import com.plcs.web.modules.sys.entity.User;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessRecord.dao.WsxdRemindRecordMapper;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.AddRemindRecordDTO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.GetAllCaseTypeVO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.GetAllPhoneStatusVO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.PADImportRemindRecordDTO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.PADImportRemindRecordResult;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.RemindRecordSourceType;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.SysDictBO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.WsxdRemindRecordBO;
import com.plcs.web.wsxd.smsplatform.smssend.entity.ResultVO;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroup;

@Service
public class RemindRecordService {
	
	@Autowired
	private WsxdRemindRecordMapper reminderRecordMapper;
	@Autowired
	private IdGen idGen;

	@Value("${envName}")
	private String envName;
	
	public GetAllCaseTypeVO getAllCaseType() {
		GetAllCaseTypeVO result = new GetAllCaseTypeVO();
		List<SysDictBO> caseTypeList = reminderRecordMapper.findAllCaseStatusFromSysDict();
		result.setCasyTypeList(caseTypeList);
		return result;
	}
	
	public GetAllPhoneStatusVO getAllPhoneStatus() {
		GetAllPhoneStatusVO result = new GetAllPhoneStatusVO();
		List<SysDictBO> caseTypeList = reminderRecordMapper.findAllPhoneStatusFromSysDict();
		result.setPhoneStatusList(caseTypeList);
		return result;
	}
	
	public ResultVO addRemindRecord(AddRemindRecordDTO dto) {
		User user = UserUtils.getUser();
		ResultVO result = new ResultVO();
		if(user == null) {
//			TODO dev
			if("dev".equals(envName)) {
				user = new User();
				user.setId("123");
				user.setName("testUser");
			} else {
//				TODO
			}
		}
		
		if(StringUtils.isBlank(dto.getCaseStatus())) {
			result.setMsg("案件状态不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getLoanBillNo())) {
			result.setMsg("借据号不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getPhone())) {
			result.setMsg("联系号不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getPhoneStatus())) {
			result.setMsg("联系号码状态不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getMeasure())) {
			result.setMsg("措施不能为空");
			return result;
		}
		if(dto.getRemindDate() == null) {
			dto.setRemindDate(new Date());
		}
		
		List<WsxdAllocateGroup> groupList = reminderRecordMapper.findGroupNameByUserName(user.getName());
		
		WsxdRemindRecordBO po = new WsxdRemindRecordBO();
		po.setCreateBy(user.getId());
		po.setCreateDate(new Date());
		po.setCreateTime(new Date());
		po.setDelFlag("0");
		po.setId(idGen.getNextId());
		po.setIsRemind("0");
		po.setLoanBillNo(dto.getLoanBillNo());
		po.setMeasure(dto.getMeasure());
		po.setOdv(user.getName());
		if(groupList == null || groupList.size() < 1) {
			po.setOdvGroup("小组不存在");
		} else {
			po.setOdvGroup(groupList.get(0).getGroupName());
		}
		po.setPhone(dto.getPhone());
		if(dto.getPromiseAmt() != null) {
			po.setPromiseAmt(dto.getPromiseAmt());
		}
		po.setPhoneStatus(dto.getPhoneStatus());
		po.setPromiseDate(dto.getPromiseDate());
		po.setRemindDate(dto.getRemindDate());
		po.setStatus(dto.getCaseStatus());
		po.setUpdateBy(user.getId());
		po.setUpdateDate(new Date());
		
		int count = reminderRecordMapper.insertSelective(po);
		if(count < 1) {
			result.setMsg("服务器繁忙");
			return result;
		}
		
		result.setSuccess(true);
		result.setCode(0);
		
		return result;
	}
	
	public PADImportRemindRecordResult padImportRemindRecord(PADImportRemindRecordDTO dto) {
		PADImportRemindRecordResult result = new PADImportRemindRecordResult();
		
		if(StringUtils.isBlank(dto.getData().getStatus())) {
			result.setRespMsg("案件状态不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getData().getLoanBillNo())) {
			result.setRespMsg("借据号不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getData().getPhone())) {
			result.setRespMsg("联系号不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getData().getPhoneStatus())) {
			result.setRespMsg("联系号码状态不能为空");
			return result;
		}
		if(StringUtils.isBlank(dto.getData().getMeasure())) {
			result.setRespMsg("措施不能为空");
			return result;
		}
		if(dto.getData().getRemindDate() == null) {
			dto.getData().setRemindDate(new Date());
		}
		
		WsxdRemindRecordBO po = new WsxdRemindRecordBO();
		po.setCreateBy("PAD导入");
		po.setCreateDate(new Date());
		po.setCreateTime(new Date());
		po.setDelFlag("0");
		po.setId(idGen.getNextId());
		po.setIsRemind("0");
		po.setLoanBillNo(dto.getData().getLoanBillNo());
		po.setMeasure(dto.getData().getMeasure());
		po.setOdv(dto.getData().getOdv());
		po.setOdvGroup("PAD导入");
		po.setPhone(dto.getData().getPhone());
		po.setPhoneStatus(dto.getData().getPhoneStatus());
		if(StringUtils.isBlank(dto.getData().getSource())) {
			po.setSource(RemindRecordSourceType.accountManagerPAD.getCode().toString());
		} else {
			po.setSource(dto.getData().getSource());
		}
		if(dto.getData().getPromiseAmt() != null) {
			try {
				po.setPromiseAmt(new BigDecimal(dto.getData().getPromiseAmt()));
			} catch (Exception e) {
			}
		}
		po.setPromiseDate(dto.getData().getPromiseDate());
		po.setRemindDate(dto.getData().getRemindDate());
		po.setStatus(dto.getData().getStatus());
		po.setUpdateBy("PAD导入");
		po.setUpdateDate(new Date());
		
		int count = reminderRecordMapper.insertSelective(po);
		if(count < 1) {
			result.setRespMsg("服务器繁忙");
			return result;
		}
		
		result.setSuccess();
		
		return result;
	}
}
