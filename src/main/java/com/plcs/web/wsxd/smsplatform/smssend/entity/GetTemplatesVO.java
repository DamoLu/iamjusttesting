package com.plcs.web.wsxd.smsplatform.smssend.entity;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class GetTemplatesVO extends ResultVO {

	@ApiModelProperty(value = "模板列表")
	private List<SmsTemplateBO> templates;

	public List<SmsTemplateBO> getTemplates() {
		return templates;
	}

	public void setTemplates(List<SmsTemplateBO> templates) {
		this.templates = templates;
	}

	@Override
	public String toString() {
		return "GetTemplatesVO [templates=" + templates + "]";
	}

}
