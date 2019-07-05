package com.plcs.web.wsxd.smsplatform.smssend.entity;

public enum SmsSendStatusType {
	
	success("发送成功", 0),
	fail("发送失败", -1),
	td("退订", -2),
	limit("条数限制", -3),
	;
	
	private String name;
	private Integer code;
	
	SmsSendStatusType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public static SmsSendStatusType getType(String typeName) {
		for(SmsSendStatusType t : SmsSendStatusType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static SmsSendStatusType getType(Integer typeCode) {
		for(SmsSendStatusType t : SmsSendStatusType.values()) {
			if(t.getCode().equals(typeCode)) {
				return t;
			}
		}
		return null;
	}
}
