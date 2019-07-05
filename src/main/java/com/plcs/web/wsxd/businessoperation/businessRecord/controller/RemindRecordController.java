package com.plcs.web.wsxd.businessoperation.businessRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plcs.web.wsxd.businessoperation.businessRecord.entity.AddRemindRecordDTO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.GetAllCaseTypeVO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.GetAllPhoneStatusVO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.PADImportRemindRecordDTO;
import com.plcs.web.wsxd.businessoperation.businessRecord.entity.PADImportRemindRecordResult;
import com.plcs.web.wsxd.businessoperation.businessRecord.service.RemindRecordService;
import com.plcs.web.wsxd.smsplatform.smssend.entity.ResultVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/remindRecorder")
public class RemindRecordController {

	@Autowired
	private RemindRecordService remindRecordService;
	
	@ApiOperation(value = "获取所有案件状态")
	@RequestMapping(value = "/getAllCaseType", method = RequestMethod.POST)
	@ResponseBody
	public GetAllCaseTypeVO getAllCaseType() {
		GetAllCaseTypeVO result = remindRecordService.getAllCaseType();
		return result;
	}
	
	@ApiOperation(value = "获取所有电话状态")
	@RequestMapping(value = "/getAllPhoneStatus", method = RequestMethod.POST)
	@ResponseBody
	public GetAllPhoneStatusVO getAllPhoneStatus() {
		GetAllPhoneStatusVO result = remindRecordService.getAllPhoneStatus();
		return result;
	}
	
	@ApiOperation(value = "增加催收记录")
	@RequestMapping(value = "/addRemindRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO addRemindRecord(@RequestBody AddRemindRecordDTO dto) {
		ResultVO result = remindRecordService.addRemindRecord(dto);
		return result;
	}
	
	@ApiOperation(value = "pad端增加催收记录, 无需前端页面接口")
	@RequestMapping(value = "/padAddRemindRecord", method = RequestMethod.POST)
	@ResponseBody
	public PADImportRemindRecordResult addRemindRecord(@RequestBody PADImportRemindRecordDTO dto) {
		PADImportRemindRecordResult result = remindRecordService.padImportRemindRecord(dto);
		return result;
	}

}
