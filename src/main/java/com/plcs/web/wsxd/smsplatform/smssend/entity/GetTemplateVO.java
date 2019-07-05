package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class GetTemplateVO extends ResultVO {

	@ApiModelProperty(value = "模板")
	private SmsTemplateBO template;

	public SmsTemplateBO getTemplate() {
		return template;
	}

	public void setTemplate(SmsTemplateBO template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "GetTemplateVO [template=" + template + "]";
	}

}
