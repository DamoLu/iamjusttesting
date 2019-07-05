package com.plcs.web.wsxd.smsplatform.smstemplatemgt.controller;

import com.plcs.web.common.config.Global;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.wsxd.smsplatform.smstemplatemgt.entity.WsxdSmsTemplate;
import com.plcs.web.wsxd.smsplatform.smstemplatemgt.service.WsxdSmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 短信模板Controller
 *
 * @author luqihua
 * @version 2019-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/smstemplatemgt/wsxdSmsTemplate")
public class WsxdSmsTemplateController extends BaseController {

    @Autowired
    private WsxdSmsTemplateService wsxdSmsTemplateService;

    @ModelAttribute
    public WsxdSmsTemplate get(@RequestParam(required = false) String id) {
        WsxdSmsTemplate entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = wsxdSmsTemplateService.get(id);
        }
        if (entity == null) {
            entity = new WsxdSmsTemplate();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(WsxdSmsTemplate wsxdSmsTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<WsxdSmsTemplate> page = wsxdSmsTemplateService.findPage(new Page<WsxdSmsTemplate>(request, response), wsxdSmsTemplate);
        List<WsxdSmsTemplate> wsxdSmsTemplateList = wsxdSmsTemplateService.findList(new WsxdSmsTemplate());
        if (null != wsxdSmsTemplateList) {
            List<String> nameList = wsxdSmsTemplateList.stream().map(WsxdSmsTemplate::getName).collect(Collectors.toList());
            model.addAttribute("nameList", nameList);
        }
        model.addAttribute("page", page);
        return "smsplatform/smstemplatemgt/wsxdSmsTemplateList";
    }

    @RequestMapping(value = "form")
    public String form(WsxdSmsTemplate wsxdSmsTemplate, Model model) {

        model.addAttribute("wsxdSmsTemplate", wsxdSmsTemplate);
        return "smsplatform/smstemplatemgt/wsxdSmsTemplateForm";
    }

    @RequestMapping(value = "save")
    public String save(WsxdSmsTemplate wsxdSmsTemplate, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, wsxdSmsTemplate)) {
            return form(wsxdSmsTemplate, model);
        }
            //判断模板名称是否重复
            List<WsxdSmsTemplate> wsxdSmsTemplateList = wsxdSmsTemplateService.findList(new WsxdSmsTemplate());
        boolean isPresent = wsxdSmsTemplateList.stream().anyMatch(wsxdSmsTemplate1 ->
                wsxdSmsTemplate1.getName().equals(wsxdSmsTemplate.getName()) && !wsxdSmsTemplate1.getId().equals(wsxdSmsTemplate.getId()));
        if (isPresent) {
                model.addAttribute("message", "模板名称重复,请重新输入");
                return form(wsxdSmsTemplate, model);
            }
        wsxdSmsTemplateService.save(wsxdSmsTemplate);
        addMessage(redirectAttributes, "保存短信模板成功");
        return "redirect:" + Global.getAdminPath() + "/smstemplatemgt/wsxdSmsTemplate/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(WsxdSmsTemplate wsxdSmsTemplate, RedirectAttributes redirectAttributes) {
        wsxdSmsTemplateService.delete(wsxdSmsTemplate);
        addMessage(redirectAttributes, "删除短信模板成功");
        return "redirect:" + Global.getAdminPath() + "/smstemplatemgt/wsxdSmsTemplate/?repage";
    }

}