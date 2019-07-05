package com.plcs.web.wsxd.sysmgt.allocatecase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plcs.web.common.config.Global;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateRule;
import com.plcs.web.wsxd.sysmgt.allocatecase.service.WsxdAllocateRuleService;

import java.util.List;
import java.util.Optional;

/**
 * 分案规则排序Controller
 * @author luqihua
 * @version 2019-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/sysmgt/allocatecase/wsxdAllocateRule")
public class WsxdAllocateRuleController extends BaseController {

	@Autowired
	private WsxdAllocateRuleService wsxdAllocateRuleService;
	
	@ModelAttribute
	public WsxdAllocateRule get(@RequestParam(required=false) String id) {
		WsxdAllocateRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdAllocateRuleService.get(id);
		}
		if (entity == null){
			entity = new WsxdAllocateRule();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(WsxdAllocateRule wsxdAllocateRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdAllocateRule> page = wsxdAllocateRuleService.findPage(new Page<WsxdAllocateRule>(request, response), wsxdAllocateRule); 
		model.addAttribute("page", page);
		return "sysmgt/allocatecase/wsxdAllocateRuleList";
	}

	@RequestMapping(value = "form")
	public String form(WsxdAllocateRule wsxdAllocateRule, Model model) {
		model.addAttribute("wsxdAllocateRule", wsxdAllocateRule);
		return "sysmgt/allocatecase/wsxdAllocateRuleForm";
	}

	@RequestMapping(value = "save")
	public String save(WsxdAllocateRule wsxdAllocateRule, Model model, RedirectAttributes redirectAttributes) {
		wsxdAllocateRule.setNum(3);
		wsxdAllocateRule.setRuleDescribe("按照逾期贷款余额在待分件人员中平均循环分案");
		wsxdAllocateRule.setSortKey("3");
		wsxdAllocateRuleService.save(wsxdAllocateRule);
		addMessage(redirectAttributes, "保存分案规则排序成功");
		return "redirect:"+Global.getAdminPath()+"/sysmgt/allocatecase/wsxdAllocateRule/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(WsxdAllocateRule wsxdAllocateRule, RedirectAttributes redirectAttributes) {
		wsxdAllocateRuleService.delete(wsxdAllocateRule);
		addMessage(redirectAttributes, "删除分案规则排序成功");
		return "redirect:"+Global.getAdminPath()+"/allocatecase/wsxdAllocateRule/?repage";
	}

	@RequestMapping(value = "operation")
	@ResponseBody
	public JSONObject operation(String operation, Integer num) {
		JSONObject jsonObject = new JSONObject();
		WsxdAllocateRule wsxdAllocateRule = this.findByNum(num);
		switch (operation) {
			case "up":
				//页面做了限制,正常情况不会跑进if语句
				if (wsxdAllocateRule.getNum() == 1) {
					jsonObject.put("msg", "已经是第一行,不能往上移动");
					return jsonObject;
				}
				//找到前一个进行num值交换
				WsxdAllocateRule wsxdAllocateRule1 = this.findByNum(num - 1);
				wsxdAllocateRule1.setNum(num);
				wsxdAllocateRuleService.save(wsxdAllocateRule1);
				wsxdAllocateRule.setNum(num - 1);
				wsxdAllocateRuleService.save(wsxdAllocateRule);
				break;

			case "down":
				//页面做了限制,正常情况不会跑进if语句
				if (num == 3) {
					jsonObject.put("msg", "已经是最后一行,不能往下移动");
					return jsonObject;
				}
				//找到下一个进行num值交换
				WsxdAllocateRule wsxdAllocateRule2 = this.findByNum(num + 1);
				wsxdAllocateRule2.setNum(num);
				wsxdAllocateRuleService.save(wsxdAllocateRule2);
				wsxdAllocateRule.setNum(num + 1);
				wsxdAllocateRuleService.save(wsxdAllocateRule);
				break;
		}
		return jsonObject;
	}

	private WsxdAllocateRule findByNum(Integer num) {
		List<WsxdAllocateRule> wsxdAllocateRuleList = wsxdAllocateRuleService.findList(new WsxdAllocateRule());
		Optional<WsxdAllocateRule> wsxdAllocateRuleOptional= wsxdAllocateRuleList.stream().filter(wsxdAllocateRule -> num.equals(wsxdAllocateRule.getNum())).findAny();
		return wsxdAllocateRuleOptional.orElse(null);
	}

}