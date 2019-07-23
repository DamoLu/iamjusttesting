package com.plcs.web.wsxd.smsplatform.smsplatformconfig.controller;

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
import com.plcs.web.wsxd.smsplatform.smsplatformconfig.entity.WsxdSmsConfig;
import com.plcs.web.wsxd.smsplatform.smsplatformconfig.service.WsxdSmsConfigService;

import java.util.List;

/**
 * 短信平台配置Controller
 * @author luqihua
 * @version 2019-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/smsplatformconfig/wsxdSmsConfig")
public class WsxdSmsConfigController extends BaseController {

	@Autowired
	private WsxdSmsConfigService wsxdSmsConfigService;
	
	@ModelAttribute
	public WsxdSmsConfig get(@RequestParam(required=false) String id) {
		WsxdSmsConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdSmsConfigService.get(id);
		}
		if (entity == null){
			entity = new WsxdSmsConfig();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(WsxdSmsConfig wsxdSmsConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdSmsConfig> page = wsxdSmsConfigService.findPage(new Page<WsxdSmsConfig>(request, response), wsxdSmsConfig); 
		model.addAttribute("page", page);
		return "smsplatform/smsplatformconfig/wsxdSmsConfigList";
	}

	@RequestMapping(value = "form")
	public String form(WsxdSmsConfig wsxdSmsConfig, Model model) {
		List<WsxdSmsConfig> list = wsxdSmsConfigService.findList(wsxdSmsConfig);
		if (null != list && list.size() == 1) {
			wsxdSmsConfig = list.get(0);
		}
		model.addAttribute("wsxdSmsConfig", wsxdSmsConfig);
		return "smsplatform/smsplatformconfig/wsxdSmsConfigForm";
	}

	@RequestMapping(value = "save")
	public String save(WsxdSmsConfig wsxdSmsConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdSmsConfig)){
			return form(wsxdSmsConfig, model);
		}
		List<WsxdSmsConfig> list = wsxdSmsConfigService.findList(new WsxdSmsConfig());
		if (list.size() == 0) {
			wsxdSmsConfigService.save(wsxdSmsConfig);
			model.addAttribute("message", "保存短信平台配置成功");
			return form(wsxdSmsConfig, model);
		}else if (list.size()==1) {
			WsxdSmsConfig wsxdSmsConfig1 = list.get(0);
			wsxdSmsConfig1.setMsgCount(wsxdSmsConfig.getMsgCount());
			wsxdSmsConfig1.setReturnState(wsxdSmsConfig.getReturnState());
			wsxdSmsConfigService.save(wsxdSmsConfig1);
			model.addAttribute("message", "保存短信平台配置成功");
			return form(wsxdSmsConfig, model);
		}else {
		model.addAttribute("message", "数据有误,请联系管理员");
		return form(wsxdSmsConfig, model);
		}
	}
	
	@RequestMapping(value = "delete")
	public String delete(WsxdSmsConfig wsxdSmsConfig, RedirectAttributes redirectAttributes) {
		wsxdSmsConfigService.delete(wsxdSmsConfig);
		addMessage(redirectAttributes, "删除保存短信平台配置成功成功");
		return "redirect:"+Global.getAdminPath()+"/smsplatformconfig/wsxdSmsConfig/?repage";
	}

}