package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

public enum PhoneStatusType {
	
	/** 有效 */
	effectvie("有效", 1),
	/** 停机 */
	downtime("停机", 2),
	/** 占线 */
	busy("占线", 3),
	/** 不在服务区 */
	outOfServiceArea("不在服务区", 4),
	/** 拒接 */
	rejecxt("拒接", 5),
	;
	
	private String name;
	private Integer code;
	
	PhoneStatusType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}
	
	public Integer getCode() {
		return code;
	}

	public static PhoneStatusType getType(String typeName) {
		for(PhoneStatusType t : PhoneStatusType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static PhoneStatusType getType(Integer code) {
		for(PhoneStatusType t : PhoneStatusType.values()) {
			if(t.getCode().equals(code)) {
				return t;
			}
		}
		return null;
	}
	
}
