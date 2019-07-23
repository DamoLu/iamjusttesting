package com.plcs.web.wsxd.businessoperation.businessoperation.web;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.config.Global;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.Encodes;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.modules.sys.entity.Role;
import com.plcs.web.modules.sys.utils.UserUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.dto.WsxdCaseExportDTO;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.DataScopeEnum;
import com.plcs.web.wsxd.businessoperation.businessoperation.enums.DistributeResultStatus;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdAllocateHstService;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdRemindRecordService;
import com.plcs.web.wsxd.businessoperation.businessoperation.utils.CompareUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvGroupVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import com.plcs.web.wsxd.sysmgt.allocatecase.service.WsxdAllocateGroupService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.util.PoiElUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 业务操作Controller
 * @author tanweijian
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/businessoperation/wsxdCase")
public class WsxdCaseController extends BaseController {

	@Autowired
	private WsxdCaseService wsxdCaseService;
	@Autowired
	private WsxdRemindRecordService wsxdRemindRecordService;
	@Autowired
	private WsxdAllocateHstService wsxdAllocateHstService;
	@Autowired
	private WsxdAllocateGroupService wsxdAllocateGroupService;

    /**
     *  将下拉列表数据返回给搜索栏
     * @param id
     * @return
     */
	@ModelAttribute
	public WsxdCase get(@RequestParam(required=false) String id) {
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
			// entity.setLoanOrginList(wsxdCaseService.findLoanOrginList());
            // 案件状态，只有名字
            // entity.setRemindStatusList(wsxdRemindRecordService.findCaseStatusList());
            // 客户经理
            entity.setManagerList(wsxdCaseService.findManagerList());
            // 产品名称
            entity.setProductNameList(wsxdCaseService.findProductNameList());
		}
		return entity;
	}

    /**
     * 获取处理组列表
     */
    @ResponseBody
    @RequestMapping(value = "getOdvGroupList", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OdvGroupVO> getOdvGroupList(HttpServletRequest request, @RequestBody OdvGroupVO odvGroupVO) {
    	List<OdvGroupVO> odvGroupVOList = wsxdAllocateGroupService.findOdvGroupList();
    	if (odvGroupVOList != null && odvGroupVOList.size() > 0) {
    		for (OdvGroupVO vo : odvGroupVOList) {
				List<OdvOV> odvOVList = wsxdAllocateGroupService.findOdvList(vo.getOdvGroup());
				vo.setOdvOVList(odvOVList);
			}
		}
        return odvGroupVOList;
    }

    /**
     * 获取处理人员列表
     */
    @ResponseBody
    @RequestMapping(value = "getOdvList", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OdvOV> getOdvList(HttpServletRequest request, @RequestBody OdvOV odvOV) {
        return wsxdAllocateGroupService.findOdvList(odvOV.getId());
    }

	@RequiresPermissions("businessoperation:wsxdCase:view")
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

		if (wsxdCaseService.isDoSerach(wsxdCase)) {
			wsxdCase.setSearch("yes");
		} else {
			wsxdCase.setSearch("no");
		}

		if (wsxdCase.getAppName() != null && !wsxdCase.getAppName().equals("")) {
            wsxdCase.getAppName().split(",");
            wsxdCase.setAppNameList(Arrays.asList(wsxdCase.getAppName().split(",")));
        }

    	Page<WsxdCase> page = wsxdCaseService.findPage(new Page<WsxdCase>(request, response), wsxdCase);
		model.addAttribute("page", page);
		return "businessoperation/businessoperation/wsxdCaseList";
	}

	@RequiresPermissions("businessoperation:wsxdCase:view")
	@RequestMapping(value = "form")
	public String form(WsxdCase wsxdCase, Model model) {
		model.addAttribute("wsxdCase", wsxdCase);
		return "businessoperation/businessoperation/wsxdCaseForm";
	}

	@RequiresPermissions("businessoperation:wsxdCase:edit")
	@RequestMapping(value = "save")
	public String save(WsxdCase wsxdCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdCase)){
			return form(wsxdCase, model);
		}
		wsxdCaseService.save(wsxdCase);
		addMessage(redirectAttributes, "保存业务操作成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdCase/?repage";
	}
	
	@RequiresPermissions("businessoperation:wsxdCase:edit")
	@RequestMapping(value = "delete")
	public String delete(WsxdCase wsxdCase, RedirectAttributes redirectAttributes) {
		wsxdCaseService.delete(wsxdCase);
		addMessage(redirectAttributes, "删除业务操作成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdCase/?repage";
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
	public void exportFile(WsxdCase wsxdCase,String isAll,String checkIDArr, HttpServletResponse response) throws IOException {
		logger.info("开始导出催记案件数据表...");
		List<WsxdCase> caseList=new ArrayList<WsxdCase>();
		List<WsxdCaseExportDTO> wsxdCaseExportDTOList = new ArrayList<>();
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
			String dataScope =  CompareUtils.getMaxDataScope(dataScopes);
			if (dataScope.equals(DataScopeEnum.ALL_DATA.getCode())) {
				// 3.1 统管理员、业务管理员，可以看到所有案件（范围为1）

			} else if (dataScope.equals(DataScopeEnum.BELONG_DEPARTMENT.getCode())) {
				// 3.2 事业部负责人，看到关于自己所属机构的事业部的所有案件（范围为4）
				wsxdCase.setDepartmentId(UserUtils.getUser().getOffice().getCode());
			} else {
				// 3.3 其他角色看到自己的案件（范围为8）
				wsxdCase.setPermissionOdv(UserUtils.getUser().getLoginName());
			}
			if (wsxdCaseService.isDoSerach(wsxdCase)) {
				wsxdCase.setSearch("yes");
			} else {
				wsxdCase.setSearch("no");
			}
			wsxdCase.setFind("no");

            if (wsxdCase.getAppName() != null && !wsxdCase.getAppName().equals("")) {
                wsxdCase.getAppName().split(",");
                wsxdCase.setAppNameList(Arrays.asList(wsxdCase.getAppName().split(",")));
            }

			caseList= wsxdCaseService.findList(wsxdCase);
		}else {
			// 根据复选框Id进行导出
			String[] idArr=checkIDArr.split(",");
			if (idArr != null && idArr.length > 0) {
				for (int i = 0; i <idArr.length ; i++) {
					wsxdCase.setId(idArr[i]);
					wsxdCase.setFind("no");
					caseList.add(wsxdCaseService.get(wsxdCase));
				}
			}
		}
		for (WsxdCase sourceCase:caseList){
			WsxdCaseExportDTO exportCase=new WsxdCaseExportDTO();
			BeanUtils.copyProperties(sourceCase,exportCase);
			if (sourceCase.getCreateDate() != null) {
				exportCase.setCreateDate(DateUtils.formatDateTime(sourceCase.getCreateDate()));
			}
			wsxdCaseExportDTOList.add(exportCase);
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			TemplateExportParams params = new TemplateExportParams("/template/催记案件数据表.xlsx","催记案件数据表");

			Map<String, Object> mapExcel = new HashMap<String, Object>();
			List<WsxdCaseExportDTO> viewlist = wsxdCaseExportDTOList;
			Map<String, Object> mapDate = new HashMap<String, Object>();
			mapExcel.put("date", PoiElUtil.eval("fd:(date;yyyyMMdd)", mapDate));
			mapExcel.put("viewlist", viewlist);

			String fileName = "催记案件数据表"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			// 告诉浏览器用什么软件可以打开此文件
			response.setHeader("content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(fileName));
			Workbook workbook = ExcelExportUtil.exportExcel(params, mapExcel);
			workbook.write(response.getOutputStream());
			logger.info("结束导出催记案件数据表...");
		} catch (Exception e) {
			logger.error("催记案件数据表导出失败信息："+e.getMessage());
		}
	}

	/**
	 * 更新分配到人
	 */
	@ResponseBody
	@RequestMapping(value = "distributeCSY")
	public JSONObject distributeCSY(HttpServletRequest request, WsxdCase wsxdCase) throws IOException {
		JSONObject object = new JSONObject();

		// 将分案信息保存到分案历史表中
		int result = wsxdAllocateHstService.insertAllocateHst(wsxdCase);

		if (result == DistributeResultStatus.DISTRIBUTE_SUCCESS.getStatus()) {
			object.put("success", true);
		} else if (result == DistributeResultStatus.DISTRIBUTE_FAILURE.getStatus()) {
			object.put("success", false);
			object.put("message", DistributeResultStatus.DISTRIBUTE_FAILURE.getMsg());
		} else if (result == DistributeResultStatus.NOT_OVERDUE.getStatus()) {
			object.put("success", false);
			object.put("message", DistributeResultStatus.NOT_OVERDUE.getMsg());
		} else if (result == DistributeResultStatus.NOT_SELECTED.getStatus()) {
			object.put("success", false);
			object.put("message", DistributeResultStatus.NOT_SELECTED.getMsg());
		}
		return object;
	}
}