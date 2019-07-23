package com.plcs.web.wsxd.businessquery.realtimequery.controller;

import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.utils.excel.ExportExcel;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.modules.sys.entity.Role;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.DataScopeEnum;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.businessoperation.businessoperation.utils.CompareUtils;
import com.plcs.web.wsxd.businessquery.realtimequery.dto.WsxdRealTimeCaseExport;
import com.plcs.web.wsxd.sysmgt.allocatecase.service.WsxdAllocateGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		// 数据权限控制
		// 1. 根据登录账户获取对应的角色类型（可能有多个，取最高权限）
		// 2. 根据角色获取对应的数据范围权限（可能有多个，取最大范围），实际上1、2一起的
		List<Role> roleList =  UserUtils.getUser().getRoleList();
		List<String> dataScopes = new ArrayList<>();
		if (roleList != null && roleList.size() > 0) {
			for (Role role : roleList) {
				String dataScope = role.getDataScope();
				dataScopes.add(dataScope);
			}
		}
		// 3. 根据数据范围权限做限制
		String dataScope = CompareUtils.getMaxDataScope(dataScopes);
		if (dataScope.equals(DataScopeEnum.ALL_DATA.getCode())) {
			// 3.1 统管理员、业务管理员，可以看到所有案件（范围为1）

		} else if (dataScope.equals(DataScopeEnum.BELONG_DEPARTMENT.getCode())) {
			// 3.2 事业部负责人，看到关于自己所属机构的事业部的所有案件（范围为4）
			wsxdCase.setDepartmentId(UserUtils.getUser().getOffice().getCode());
		} else {
			// 3.3 其他角色看到自己的案件（范围为8）
			wsxdCase.setPermissionOdv(UserUtils.getUser().getLoginName());
		}
		Page<WsxdCase> page = wsxdCaseService.findRealListPage(new Page<WsxdCase>(request, response), wsxdCase);
		model.addAttribute("page", page);
		return "businessoperation/realtimequery/wsxdCaseRealTimeList";
	}


	@RequestMapping("/exportFile")
	public void exportFile(WsxdCase wsxdCase,String isAll,String checkIDArr, HttpServletResponse response) throws IOException {
		List<WsxdCase> caseList=new ArrayList<>();
		List<WsxdRealTimeCaseExport> exportList=new ArrayList<>();
		System.out.println(wsxdCase.toString());
		if(Boolean.valueOf(isAll)){
			// 数据权限控制
			// 1. 根据登录账户获取对应的角色类型（可能有多个，取最高权限）
			// 2. 根据角色获取对应的数据范围权限（可能有多个，取最大范围），实际上1、2一起的
			List<Role> roleList =  UserUtils.getUser().getRoleList();
			List<String> dataScopes = new ArrayList<>();
			if (roleList != null && roleList.size() > 0) {
				for (Role role : roleList) {
					String dataScope = role.getDataScope();
					dataScopes.add(dataScope);
				}
			}
			// 3. 根据数据范围权限做限制
			String dataScope = CompareUtils.getMaxDataScope(dataScopes);
			if (dataScope.equals(DataScopeEnum.ALL_DATA.getCode())) {
				// 3.1 统管理员、业务管理员，可以看到所有案件（范围为1）

			} else if (dataScope.equals(DataScopeEnum.BELONG_DEPARTMENT.getCode())) {
				// 3.2 事业部负责人，看到关于自己所属机构的事业部的所有案件（范围为4）
				wsxdCase.setDepartmentId(UserUtils.getUser().getOffice().getCode());
			} else {
				// 3.3 其他角色看到自己的案件（范围为8）
				wsxdCase.setPermissionOdv(UserUtils.getUser().getLoginName());
			}
			caseList= wsxdCaseService.findRealList(wsxdCase);
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