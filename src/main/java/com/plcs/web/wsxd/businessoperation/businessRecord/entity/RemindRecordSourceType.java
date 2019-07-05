package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

public enum RemindRecordSourceType {
	
	/** 客户经理pad */
	accountManagerPAD("客户经理pad", 1),
	/** 贷后经理pad */
	postLoanManagerPAD("贷后经理pad", 2),
	/** 催收系统 */
	remindSystem("催收系统", 3),
	;
	
	private String name;
	private Integer code;
	
	RemindRecordSourceType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}
	
	public Integer getCode() {
		return code;
	}

	public static RemindRecordSourceType getType(String typeName) {
		for(RemindRecordSourceType t : RemindRecordSourceType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static RemindRecordSourceType getType(Integer code) {
		for(RemindRecordSourceType t : RemindRecordSourceType.values()) {
			if(t.getCode().equals(code)) {
				return t;
			}
		}
		return null;
	}
	
}
