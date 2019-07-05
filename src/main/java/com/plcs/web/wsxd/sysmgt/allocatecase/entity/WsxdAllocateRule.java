package com.plcs.web.wsxd.sysmgt.allocatecase.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.plcs.web.common.persistence.DataEntity;

/**
 * 分案规则排序Entity
 * @author luqihua
 * @version 2019-06-25
 */
public class WsxdAllocateRule extends DataEntity<WsxdAllocateRule> {
	
	private static final long serialVersionUID = 1L;
	private Integer num;		// 序号
	private String ruleDescribe;		// 规则描述
	private String sortKey;		// 分案组合排序
	
	public WsxdAllocateRule() {
		super();
	}

	public WsxdAllocateRule(String id){
		super(id);
	}

	@NotNull(message="序号不能为空")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Length(min=1, max=255, message="规则描述长度必须介于 1 和 255 之间")
	public String getRuleDescribe() {
		return ruleDescribe;
	}

	public void setRuleDescribe(String ruleDescribe) {
		this.ruleDescribe = ruleDescribe;
	}
	
	@Length(min=1, max=1, message="分案组合排序长度必须介于 1 和 1 之间")
	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}
	
}