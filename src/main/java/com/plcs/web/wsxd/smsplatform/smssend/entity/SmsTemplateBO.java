package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class SmsTemplateBO {

	@ApiModelProperty(value = "模板id")
	private String id;
	@ApiModelProperty(value = "模板内容")
	private String content;
	@ApiModelProperty(value = "模板名称")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SmsTemplateBO [id=" + id + ", content=" + content + ", name=" + name + "]";
	}

}
