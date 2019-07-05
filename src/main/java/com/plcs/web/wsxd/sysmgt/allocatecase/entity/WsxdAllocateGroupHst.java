package com.plcs.web.wsxd.sysmgt.allocatecase.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.plcs.web.common.persistence.DataEntity;

/**
 * 分案处理组历史Entity
 * @author andyhuang
 * @version 2019-06-17
 */
public class WsxdAllocateGroupHst extends DataEntity<WsxdAllocateGroupHst> {
	
	private static final long serialVersionUID = 1L;
	private String groupId;		// 处理组id
	private String groupName;		// 处理组名
	private String odvs;		// 处理人员组
	private String odvsName;		// 处理人员组（姓名）
	private Integer minOverdueDay;		// 逾期范围下限
	private Integer maxOverdueDay;		// 逾期范围上限
	private String status;		// 当前状态 1-启用 0-禁用
	
	public WsxdAllocateGroupHst() {
		super();
	}

	public WsxdAllocateGroupHst(String id){
		super(id);
	}

	@Length(min=1, max=64, message="处理组id长度必须介于 1 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=1, max=64, message="处理组名长度必须介于 1 和 64 之间")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Length(min=1, max=255, message="处理人员组长度必须介于 1 和 255 之间")
	public String getOdvs() {
		return odvs;
	}

	public void setOdvs(String odvs) {
		this.odvs = odvs;
	}
	
	@Length(min=1, max=255, message="处理人员组（姓名）长度必须介于 1 和 255 之间")
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