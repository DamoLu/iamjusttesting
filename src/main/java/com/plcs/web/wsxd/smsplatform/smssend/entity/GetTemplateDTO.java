package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class GetTemplateDTO extends ResultVO {

	@ApiModelProperty(value = "短信模板id")
	private String templateId;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Override
	public String toString() {
		return "GetTemplateDTO [templateId=" + templateId + "]";
	}

}
