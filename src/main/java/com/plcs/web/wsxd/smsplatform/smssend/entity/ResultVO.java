package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

public class ResultVO {

	private boolean success = false;
	@ApiModelProperty(value = "成功:0, 失败:其他负数")
	private Integer code = -1;
	@ApiModelProperty(value = "反馈描述")
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	public ResultVO normal(boolean success, Integer code, String msg) {
		ResultVO v = new ResultVO();
		v.setCode(code);
		v.setSuccess(success);
		v.setMsg(msg);
		return v;
	}
	
	public ResultVO success(String msg) {
		ResultVO v = new ResultVO();
		v.setCode(0);
		v.setSuccess(true);
		v.setMsg(msg);
		return v;
	}
	
	public ResultVO fail(Integer code, String msg) {
		ResultVO v = new ResultVO();
		v.setCode(code);
		v.setSuccess(false);
		v.setMsg(msg);
		return v;
	}
	
}
