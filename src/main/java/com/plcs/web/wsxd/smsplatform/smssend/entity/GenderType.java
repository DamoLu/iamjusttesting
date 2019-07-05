package com.plcs.web.wsxd.smsplatform.smssend.entity;

public enum GenderType {
	
	male("先生", 0),
	female("女士", 1),
	;
	
	private String name;
	private Integer code;
	
	GenderType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public static GenderType getType(String typeName) {
		for(GenderType t : GenderType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static GenderType getType(Integer typeCode) {
		for(GenderType t : GenderType.values()) {
			if(t.getCode().equals(typeCode)) {
				return t;
			}
		}
		return null;
	}
}
