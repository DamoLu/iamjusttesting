package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import io.swagger.annotations.ApiModelProperty;

public class SysDictBO {

	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "值")
	private String value;
	@ApiModelProperty(value = "标签")
	private String label;
	@ApiModelProperty(value = "类型")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SysDictBO [id=" + id + ", value=" + value + ", label=" + label + ", type=" + type + "]";
	}

}
