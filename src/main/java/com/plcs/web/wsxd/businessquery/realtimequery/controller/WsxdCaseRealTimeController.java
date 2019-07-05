package com.plcs.web.wsxd.businessquery.realtimequery.controller;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.utils.excel.ExportExcel;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.sysmgt.allocatecase.service.WsxdAllocateGroupService;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.businessquery.realtimequery.dto.WsxdRealTimeCaseExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务操作Controller
 * @author zhengjianbo
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/businessoperation/realtimequery")
public class WsxdCaseRealTimeController extends BaseController {

	@Autowired
	private WsxdCaseService wsxdCaseService;

	@Autowired
	private WsxdAllocateGroupService wsxdAllocateGroupService;


	/**
	 *  将下拉列表数据返回给搜索栏
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public WsxdCase getDrapList(@RequestParam(required=false) String id) {
		WsxdCase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdCaseService.get(id);
		}
		if (entity == null){
			entity = new WsxdCase();
			// 合作机构在页面配置了，如有需要可在数据库中查询
			// 事业部
			entity.setDepartmentList(wsxdCaseService.findDepartmentList());
			// 放款机构，只有名字
			entity.setLoanOrginList(wsxdCaseService.findLoanOrginList());
		}
		return entity;
	}

	@RequiresPermissions("businessoperation:realtimequery:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdCase> page = wsxdCaseService.findPage(new Page<WsxdCase>(request, response), wsxdCase); 
		model.addAttribute("page", page);
		return "businessoperation/realtimequery/wsxdCaseRealTimeList";
	}


	@RequestMapping("/exportFile")
	public void exportFile(WsxdCase wsxdCase,String isAll,String checkIDArr, HttpServletResponse response) throws IOException {
		List<WsxdCase> caseList=new ArrayList<>();
		List<WsxdRealTimeCaseExport> exportList=new ArrayList<>();
		if(Boolean.valueOf(isAll)){
			caseList= wsxdCaseService.findList(wsxdCase);
		}else {
			String[] idArr=checkIDArr.split(",");
			for (int i = 0; i <idArr.length ; i++) {
				caseList.add(wsxdCaseService.get(idArr[i]));
			}
		}
		for (WsxdCase sourceCase:caseList){
			WsxdRealTimeCaseExport exportCase=new WsxdRealTimeCaseExport();
			BeanUtils.copyProperties(sourceCase,exportCase);
			exportList.add(exportCase);
		}
		String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		ExportExcel ee = new ExportExcel("用户数据", WsxdRealTimeCaseExport.class);
		ee.setDataList(exportList).write(response, fileName);
	}
}