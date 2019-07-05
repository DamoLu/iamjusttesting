package com.plcs.web.wsxd.sysmgt.allocatecase.entity;

import com.plcs.web.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分案处理组Entity
 * @author tanweijian
 * @version 2019-06-12
 */
public class WsxdAllocateGroup extends DataEntity<WsxdAllocateGroup> {
	
	private static final long serialVersionUID = 1L;
	private String groupName;		// 处理组名
	private String odvs;		// 处理人员组
	private String odvsName;		// 处理人员组（姓名）
	private Integer minOverdueDay;		// 逾期范围下限
	private Integer maxOverdueDay;		// 逾期范围上限
	private String status;		// 当前状态 1-启用 0-禁用

	private List<WsxdAllocateGroupScope> wsxdAllocateGroupScopeList; //处理组范围列表

	public WsxdAllocateGroup() {
		super();
	}

	public WsxdAllocateGroup(String id){
		super(id);
	}

	@Length(min=1, max=64, message="处理组名长度必须介于 1 和 64 之间")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<WsxdAllocateGroupScope> getWsxdAllocateGroupScopeList() {
		return wsxdAllocateGroupScopeList;
	}

	public void setWsxdAllocateGroupScopeList(List<WsxdAllocateGroupScope> wsxdAllocateGroupScopeList) {
		this.wsxdAllocateGroupScopeList = wsxdAllocateGroupScopeList;
	}

	@Length(min=1, max=2000, message="处理人员组长度必须介于 1 和 2000 之间")
	public String getOdvs() {
		return odvs;
	}

	public void setOdvs(String odvs) {
		this.odvs = odvs;
	}
	
	@Length(min=1, max=2000, message="处理人员组（姓名）长度必须介于 1 和 2000 之间")
	public String getOdvsName() {
		return odvsName;
	}

	public void setOdvsName(String odvsName) {
		this.odvsName = odvsName;
	}
	
	@NotNull(message="逾期范围下限不能为空")
	public Integer getMinOverdueDay() {
		return minOverdueDay;
	}

	public void setMinOverdueDay(Integer minOverdueDay) {
		this.minOverdueDay = minOverdueDay;
	}
	
	@NotNull(message="逾期范围上限不能为空")
	public Integer getMaxOverdueDay() {
		return maxOverdueDay;
	}

	public void setMaxOverdueDay(Integer maxOverdueDay) {
		this.maxOverdueDay = maxOverdueDay;
	}
	
	@Length(min=1, max=1, message="当前状态 1-启用 0-禁用长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}