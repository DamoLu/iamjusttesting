package com.plcs.web.wsxd.businessoperation.businessoperation.web;

import com.plcs.web.common.config.Global;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.Encodes;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.modules.sys.entity.Role;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.dto.WsxdAllocateHstExportDTO;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdAllocateHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.DataScopeEnum;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdAllocateHstService;
import com.plcs.web.wsxd.businessoperation.businessoperation.utils.CompareUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.util.PoiElUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分案历史Controller
 * @author tanweijian
 * @version 2019-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/businessoperation/wsxdAllocateHst")
public class WsxdAllocateHstController extends BaseController {

	@Autowired
	private WsxdAllocateHstService wsxdAllocateHstService;

	/**
	 * 将下拉列表数据返回给搜索栏
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public WsxdAllocateHst get(@RequestParam(required=false) String id) {
		WsxdAllocateHst entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdAllocateHstService.get(id);
		}
		if (entity == null){
			entity = new WsxdAllocateHst();
			// 合作机构在页面配置了，如有需要可在数据库中查询
			// 事业部
			entity.setDepartmentList(wsxdAllocateHstService.findDepartmentList());
			// 放款机构，只有名字
			entity.setLoanOrginList(wsxdAllocateHstService.findLoanOrginList());
		}
		return entity;
	}
	
	@RequiresPermissions("businessoperation:wsxdAllocateHst:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsxdAllocateHst wsxdAllocateHst, HttpServletRequest request, HttpServletResponse response, Model model) {
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
			wsxdAllocateHst.setDepartmentId(UserUtils.getUser().getOffice().getCode());
		} else {
			// 3.3 其他角色看到自己的案件（范围为8）
			wsxdAllocateHst.setPermissionOdv(UserUtils.getUser().getLoginName());
		}

		Page<WsxdAllocateHst> page = wsxdAllocateHstService.findPage(new Page<WsxdAllocateHst>(request, response), wsxdAllocateHst);
		model.addAttribute("page", page);
		return "businessoperation/businessoperation/wsxdAllocateHstList";
	}

	@RequiresPermissions("businessoperation:wsxdAllocateHst:view")
	@RequestMapping(value = "form")
	public String form(WsxdAllocateHst wsxdAllocateHst, Model model) {
		model.addAttribute("wsxdAllocateHst", wsxdAllocateHst);
		return "businessoperation/businessoperation/wsxdAllocateHstForm";
	}

	@RequiresPermissions("businessoperation:wsxdAllocateHst:edit")
	@RequestMapping(value = "save")
	public String save(WsxdAllocateHst wsxdAllocateHst, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdAllocateHst)){
			return form(wsxdAllocateHst, model);
		}
		wsxdAllocateHstService.save(wsxdAllocateHst);
		addMessage(redirectAttributes, "保存案件催记成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdAllocateHst/?repage";
	}
	
	@RequiresPermissions("businessoperation:wsxdAllocateHst:edit")
	@RequestMapping(value = "delete")
	public String delete(WsxdAllocateHst wsxdAllocateHst, RedirectAttributes redirectAttributes) {
		wsxdAllocateHstService.delete(wsxdAllocateHst);
		addMessage(redirectAttributes, "删除案件催记成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdAllocateHst/?repage";
	}

	/**
	 * 导出Excel
	 * @param wsxdCase 搜索条件
	 * @param isAll 是否全选
	 * @param checkIDArr 复选框Id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportFile")
	public void exportFile(WsxdAllocateHst wsxdAllocateHst, String isAll, String checkIDArr, HttpServletResponse response) throws IOException {
		logger.info("开始导出手工分案日志数据表...");
		List<WsxdAllocateHst> wsxdAllocateHstList = new ArrayList<>();
		List<WsxdAllocateHstExportDTO> wsxdAllocateHstExportDTOList = new ArrayList<>();
		if(Boolean.valueOf(isAll)){
			// 如果是全选的话，直接根据搜索条件导出所有的即可
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
				wsxdAllocateHst.setDepartmentId(UserUtils.getUser().getOffice().getCode());
			} else {
				// 3.3 其他角色看到自己的案件（范围为8）
				wsxdAllocateHst.setPermissionOdv(UserUtils.getUser().getLoginName());
			}
			wsxdAllocateHstList= wsxdAllocateHstService.findList(wsxdAllocateHst);
		}else {
			// 根据复选框Id进行导出
			String[] idArr=checkIDArr.split(",");
			if (idArr != null && idArr.length > 0) {
				for (int i = 0; i <idArr.length ; i++) {
					wsxdAllocateHstList.add(wsxdAllocateHstService.get(idArr[i]));
				}
			}
		}
		for (WsxdAllocateHst sourceCase : wsxdAllocateHstList){
			WsxdAllocateHstExportDTO wsxdAllocateHstExportDTO=new WsxdAllocateHstExportDTO();
			BeanUtils.copyProperties(sourceCase,wsxdAllocateHstExportDTO);
			if (sourceCase.getCreateDate() != null) {
				wsxdAllocateHstExportDTO.setCreateDate(DateUtils.formatDateTime(sourceCase.getCreateDate()));
			}
			wsxdAllocateHstExportDTOList.add(wsxdAllocateHstExportDTO);
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			TemplateExportParams params = new TemplateExportParams("/template/手工分案日志数据表.xlsx","手工分案日志数据表");

			Map<String, Object> mapExcel = new HashMap<String, Object>();
			List<WsxdAllocateHstExportDTO> viewlist = wsxdAllocateHstExportDTOList;
			Map<String, Object> mapDate = new HashMap<String, Object>();
			mapExcel.put("date", PoiElUtil.eval("fd:(date;yyyyMMdd)", mapDate));
			mapExcel.put("viewlist", viewlist);

			String fileName = "手工分案日志数据表"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";;
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			// 告诉浏览器用什么软件可以打开此文件
			response.setHeader("content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(fileName));
			Workbook workbook = ExcelExportUtil.exportExcel(params, mapExcel);
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("手工分案日志数据表导出失败信息："+e.getMessage());
		}
		logger.info("结束导出手工分案日志数据表...");
	}
}