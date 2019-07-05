package com.plcs.web.wsxd.sysmgt.allocatecase.entity;

import org.hibernate.validator.constraints.Length;

import com.plcs.web.common.persistence.DataEntity;

/**
 * 分案处理组案件范围历史Entity
 * @author andyhuang
 * @version 2019-06-24
 */
public class WsxdAllocateGroupScopeHst extends DataEntity<WsxdAllocateGroupScopeHst> {
	
	private static final long serialVersionUID = 1L;
	private String groupName;		// 处理组名
	private String groupId;		// 处理组id
	private String appOrg;		// 机构 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app
	private String appOrgName;		// 机构名
	private String departmentId;		// 事业部
	private String departmentName;		// 事业部名
	private String hasCommonPool;		// 是否含公共池,0:是1:否 2:全部
	
	public WsxdAllocateGroupScopeHst() {
		super();
	}

	public WsxdAllocateGroupScopeHst(String id){
		super(id);
	}

	@Length(min=1, max=64, message="处理组名长度必须介于 1 和 64 之间")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Length(min=1, max=64, message="处理组id长度必须介于 1 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=1, max=16, message="机构 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app长度必须介于 1 和 16 之间")
	public String getAppOrg() {
		return appOrg;
	}

	public void setAppOrg(String appOrg) {
		this.appOrg = appOrg;
	}
	
	@Length(min=1, max=16, message="机构名长度必须介于 1 和 16 之间")
	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}
	
	@Length(min=1, max=64, message="事业部长度必须介于 1 和 64 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=1, max=32, message="事业部名长度必须介于 1 和 32 之间")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Length(min=1, max=1, message="是否含公共池,0:是1:否 2:全部长度必须介于 1 和 1 之间")
	public String getHasCommonPool() {
		return hasCommonPool;
	}

	public void setHasCommonPool(String hasCommonPool) {
		this.hasCommonPool = hasCommonPool;
	}
	
}