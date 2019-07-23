package com.plcs.web.wsxd.deduct.web;

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
import com.plcs.web.wsxd.deduct.entity.WsxdRealTimeDeductionHistory;
import com.plcs.web.wsxd.deduct.service.WsxdRealTimeDeductionHistoryService;

/**
 * 保存所发送的扣款请求历史Controller
 * @author zhengjiangbo
 * @version 2019-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/deduct/wsxdRealTimeDeductionHistory")
public class WsxdRealTimeDeductionHistoryController extends BaseController {

	@Autowired
	private WsxdRealTimeDeductionHistoryService wsxdRealTimeDeductionHistoryService;
	
	@ModelAttribute
	public WsxdRealTimeDeductionHistory get(@RequestParam(required=false) String id) {
		WsxdRealTimeDeductionHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdRealTimeDeductionHistoryService.get(id);
		}
		if (entity == null){
			entity = new WsxdRealTimeDeductionHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("deduct:wsxdRealTimeDeductionHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdRealTimeDeductionHistory> page = wsxdRealTimeDeductionHistoryService.findPage(new Page<WsxdRealTimeDeductionHistory>(request, response), wsxdRealTimeDeductionHistory); 
		model.addAttribute("page", page);
		return "wsxd/deduct/wsxdRealTimeDeductionHistoryList";
	}

	@RequiresPermissions("deduct:wsxdRealTimeDeductionHistory:view")
	@RequestMapping(value = "form")
	public String form(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory, Model model) {
		model.addAttribute("wsxdRealTimeDeductionHistory", wsxdRealTimeDeductionHistory);
		return "wsxd/deduct/wsxdRealTimeDeductionHistoryForm";
	}

	@RequiresPermissions("deduct:wsxdRealTimeDeductionHistory:edit")
	@RequestMapping(value = "save")
	public String save(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdRealTimeDeductionHistory)){
			return form(wsxdRealTimeDeductionHistory, model);
		}
		wsxdRealTimeDeductionHistoryService.save(wsxdRealTimeDeductionHistory);
		addMessage(redirectAttributes, "保存保存扣款请求记录成功成功");
		return "redirect:"+Global.getAdminPath()+"/deduct/wsxdRealTimeDeductionHistory/?repage";
	}
	
	@RequiresPermissions("deduct:wsxdRealTimeDeductionHistory:edit")
	@RequestMapping(value = "delete")
	public String delete(WsxdRealTimeDeductionHistory wsxdRealTimeDeductionHistory, RedirectAttributes redirectAttributes) {
		wsxdRealTimeDeductionHistoryService.delete(wsxdRealTimeDeductionHistory);
		addMessage(redirectAttributes, "删除保存扣款请求记录成功成功");
		return "redirect:"+Global.getAdminPath()+"/deduct/wsxdRealTimeDeductionHistory/?repage";
	}

}