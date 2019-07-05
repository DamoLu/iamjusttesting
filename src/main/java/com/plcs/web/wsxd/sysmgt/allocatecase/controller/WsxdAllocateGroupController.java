package com.plcs.web.wsxd.sysmgt.allocatecase.controller;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.config.Global;
import com.plcs.web.common.constant.AllocateGroupConstants;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.plcsbatchrequest.PlctBatchRequest;
import com.plcs.web.common.utils.ResultVO;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.modules.sys.bo.UserBO;
import com.plcs.web.modules.sys.entity.Role;
import com.plcs.web.modules.sys.service.SystemService;
import com.plcs.web.wsxd.sysmgt.allocatecase.bo.WsxdCaseScopeBo;
import com.plcs.web.wsxd.sysmgt.allocatecase.dto.WsxdAllocateGroupDTO;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroup;
import com.plcs.web.wsxd.sysmgt.allocatecase.service.WsxdAllocateGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 分案处理组Controller
 * @author tanweijian
 * @version 2019-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sysmgt/allocatecase/wsxdAllocateGroup")
public class WsxdAllocateGroupController extends BaseController {

	@Autowired
	private WsxdAllocateGroupService wsxdAllocateGroupService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private PlctBatchRequest plctBatchRequest;

	@ModelAttribute
	public WsxdAllocateGroup get(@RequestParam(required=false) String id) {
		WsxdAllocateGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdAllocateGroupService.get(id);
		}
		if (entity == null){
			entity = new WsxdAllocateGroup();
		}
		return entity;
	}

//	@RequiresPermissions("sysmgt:allocatecase:editAllocateGroup:edit")
	@RequestMapping(value = "edit",method = RequestMethod.GET)
	public ModelAndView editAllocateGroup(@RequestParam(required = false, value = "groupId") String id) {
		ModelAndView modelAndView = new ModelAndView("sysmgt/allocatecase/editAllocateGroup");
		if(StringUtils.isNotBlank(id)){
			ResultVO<WsxdAllocateGroup> resultVO = wsxdAllocateGroupService.getGroupAndScope(id);
			modelAndView.addObject("allocateGroup",resultVO.getResult());
			modelAndView.addObject("WsxdAllocateGroupScopeList", JSONObject.toJSONString(resultVO.getResult().getWsxdAllocateGroupScopeList()));
		}
		return modelAndView;
	}

//	@RequiresPermissions("sysmgt:allocatecase:editAllocateGroup:edit")
	@ResponseBody
	@RequestMapping(value = "getRoleList",method = RequestMethod.GET)
	public JSONObject getRoleList() {
		ResultVO<List<Role>> resultVO = wsxdAllocateGroupService.getRoleList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result",resultVO.getResult());
		jsonObject.put("success",resultVO.getSuccess());
		jsonObject.put("message",resultVO.getMessage());
		return jsonObject;
	}

//	@RequiresPermissions("sysmgt:allocatecase:editAllocateGroup:edit")
	@ResponseBody
	@RequestMapping(value = "changeRole", method = RequestMethod.GET)
	public JSONObject changeRole(String role) {
        ResultVO<List<UserBO>> resultVO = systemService.getUserByRole(role);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userList",resultVO.getResult());
		jsonObject.put("success",resultVO.getSuccess());
		jsonObject.put("message",resultVO.getMessage());
		return jsonObject;

	}

//	@RequiresPermissions("sysmgt:allocatecase:editAllocateGroup:edit")
	@ResponseBody
	@RequestMapping(value = "/getAppOrgList")
	public JSONObject getAppOrgList(){
		JSONObject jsonObject = new JSONObject();
		ResultVO<List<WsxdCaseScopeBo>> resultVO = wsxdAllocateGroupService.getCaseScopeList();
		jsonObject.put("success",resultVO.getSuccess());
		jsonObject.put("list",resultVO.getResult());
		jsonObject.put("message",resultVO.getMessage());
		return jsonObject;
	}

//	@RequiresPermissions("sysmgt:allocatecase:editAllocateGroup:edit")
	@ResponseBody
	@RequestMapping(value = "saveGroup", method = RequestMethod.POST)
	public JSONObject saveGroup(@RequestBody WsxdAllocateGroupDTO group) {
		JSONObject jsonObject = new JSONObject();
        ResultVO<String> resultVO = new ResultVO<>();
        if(StringUtils.isBlank(group.getId())) {
			try {
				resultVO = wsxdAllocateGroupService.insertGroup(group);
			} catch (Exception e) {
				resultVO.setError(AllocateGroupConstants.INSERT_GROUP_FAILED,"处理组新增失败");
			}
		} else{
			try {
				resultVO = wsxdAllocateGroupService.updateGroup(group);
			} catch (Exception e) {
				resultVO.setError(AllocateGroupConstants.UPDATE_GROUP_FAILED,"更新处理组信息失败");
			}
		}
		jsonObject.put("success",resultVO.getSuccess());
		jsonObject.put("code",resultVO.getCode());
		jsonObject.put("message",resultVO.getMessage());
//		//处理组启用
//		if (group.getStatus().equals("1")) {
//			ResultVO<List<WsxdAllocateGroup>> result = wsxdAllocateGroupService.getEnabledAllocateGroupList(group.getId());
//			if (!result.getSuccess()) {
//				//获取启动的处理组出现异常
//				jsonObject.put("success", result.getSuccess());
//				jsonObject.put("code", "1");
//				return jsonObject;
//			}
//			ResultVO<String> checkResult = checkDuplicate(group, result.getResult());
//			if (!checkResult.getSuccess()) {
//				jsonObject.put("success", checkResult.getSuccess());
//				jsonObject.put("code", checkResult.getCode());
//				return jsonObject;
//			}
//		}

//		try {
//			wsxdAllocateGroupService.insertOrUpdateGroup(group);
//			jsonObject.put("success", true);
//		} catch (Exception e) {
//			logger.error(AllocateCaseController.class.getName(), "insertAction", e.getMessage(), e);
//			jsonObject.put("code","4");
//			jsonObject.put("success",false);
//		}
		return jsonObject;

	}




	@RequestMapping(value = {"list", ""})
	public String list(WsxdAllocateGroup wsxdAllocateGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdAllocateGroup> page = wsxdAllocateGroupService.findPage(new Page<WsxdAllocateGroup>(request, response), wsxdAllocateGroup); 
		model.addAttribute("page", page);
		return "sysmgt/allocatecase/wsxdAllocateGroupList";
	}

	@RequestMapping(value = "form")
	public String form(WsxdAllocateGroup wsxdAllocateGroup, Model model) {
		model.addAttribute("wsxdAllocateGroup", wsxdAllocateGroup);
		return "sysmgt/allocatecase/wsxdAllocateGroupForm";
	}

	@RequestMapping(value = "save")
	public String save(WsxdAllocateGroup wsxdAllocateGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdAllocateGroup)){
			return form(wsxdAllocateGroup, model);
		}
		wsxdAllocateGroupService.save(wsxdAllocateGroup);
		addMessage(redirectAttributes, "保存分案助理组成功");
		return "redirect:"+Global.getAdminPath()+"/sysmgt/allocatecase/wsxdAllocateGroup/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(WsxdAllocateGroup wsxdAllocateGroup, RedirectAttributes redirectAttributes) {
		wsxdAllocateGroupService.delete(wsxdAllocateGroup);
		addMessage(redirectAttributes, "删除分案助理组成功");
		return "redirect:"+Global.getAdminPath()+"/sysmgt/allocatecase/wsxdAllocateGroup/?repage";
	}



	@RequestMapping(value = "/detail")
	public String showDetail(Model model, WsxdAllocateGroup wsxdAllocateGroup) {
		model.addAttribute("wsxdAllocateGroup", wsxdAllocateGroup);
		return "sysmgt/allocatecase/wsxdAllocateGroupDetail";
	}

	@ResponseBody
	@RequestMapping(value = "stopOrRestart")
	public JSONObject stopOrRestart(@RequestBody WsxdAllocateGroupDTO wsxdAllocateGroupDTO) {
        ResultVO<String> resultVO = new ResultVO<>();
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isNotBlank(wsxdAllocateGroupDTO.getId())) {
			try {
				resultVO = wsxdAllocateGroupService.updateGroupStatus(wsxdAllocateGroupDTO);
			} catch (Exception e) {
				resultVO.setError(AllocateGroupConstants.UPDATE_GROUP_STATUS_FAILED, "更改处理组状态失败");
			}
		} else {
			resultVO.setError(AllocateGroupConstants.UPDATE_GROUP_STATUS_FAILED,"缺少处理部ID");
		}
		jsonObject.put("success",resultVO.getSuccess());
		jsonObject.put("code",resultVO.getCode());
		jsonObject.put("message",resultVO.getMessage());
		return jsonObject;
	}


	@RequestMapping(value = "caseScope")
	public String getCaseScope(WsxdAllocateGroup wsxdAllocateGroup, Model model) {
		model.addAttribute("wsxdAllocateGroup", wsxdAllocateGroup);
		return "sysmgt/allocatecase/caseScope";
	}


	@RequestMapping(value = "/reAllocateCase")
	@ResponseBody
	public JSONObject reAllocateCase() {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> data = plctBatchRequest.doAllocateCase();
		jsonObject.put("code", data.get("status"));
		return jsonObject;
	}

}