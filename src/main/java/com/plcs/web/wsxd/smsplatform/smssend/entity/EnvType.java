package com.plcs.web.wsxd.smsplatform.smssend.entity;

public enum EnvType {
	
	test("test"),
	dev("dev"),
	prod("prod"),
	;
	
	private String name;
	
	EnvType(String name) {
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public static EnvType getType(String typeName) {
		for(EnvType t : EnvType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
}
