package com.plcs.web.wsxd.businessoperation.businessoperation.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plcs.web.common.config.Global;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdRepayHst;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdRepayHstService;

/**
 * 还款记录Controller
 * @author tanweijian
 * @version 2019-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/businessoperation/wsxdRepayHst")
public class WsxdRepayHstController extends BaseController {

	@Autowired
	private WsxdRepayHstService wsxdRepayHstService;
	
	@ModelAttribute
	public WsxdRepayHst get(@RequestParam(required=false) String id) {
		WsxdRepayHst entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdRepayHstService.get(id);
		}
		if (entity == null){
			entity = new WsxdRepayHst();
		}
		return entity;
	}
	
	@RequiresPermissions("businessoperation:wsxdRepayHst:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsxdRepayHst wsxdRepayHst, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdRepayHst> page = wsxdRepayHstService.findPage(new Page<WsxdRepayHst>(request, response), wsxdRepayHst); 
		model.addAttribute("page", page);
		return "businessoperation/businessoperation/wsxdRepayHstList";
	}

	@RequiresPermissions("businessoperation:wsxdRepayHst:view")
	@RequestMapping(value = "form")
	public String form(WsxdRepayHst wsxdRepayHst, Model model) {
		model.addAttribute("wsxdRepayHst", wsxdRepayHst);
		return "businessoperation/businessoperation/wsxdRepayHstForm";
	}

	@RequiresPermissions("businessoperation:wsxdRepayHst:edit")
	@RequestMapping(value = "save")
	public String save(WsxdRepayHst wsxdRepayHst, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdRepayHst)){
			return form(wsxdRepayHst, model);
		}
		wsxdRepayHstService.save(wsxdRepayHst);
		addMessage(redirectAttributes, "保存案件催记成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdRepayHst/?repage";
	}
	
	@RequiresPermissions("businessoperation:wsxdRepayHst:edit")
	@RequestMapping(value = "delete")
	public String delete(WsxdRepayHst wsxdRepayHst, RedirectAttributes redirectAttributes) {
		wsxdRepayHstService.delete(wsxdRepayHst);
		addMessage(redirectAttributes, "删除案件催记成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdRepayHst/?repage";
	}

}