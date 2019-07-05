package com.plcs.web.wsxd.smsplatform.smstemplatemgt.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.plcs.web.common.persistence.DataEntity;

/**
 * 短信模板Entity
 * @author luqihua
 * @version 2019-06-11
 */
public class WsxdSmsTemplate extends DataEntity<WsxdSmsTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 模版名称
	private String type;		// 发送类型 0:手工 1:批量
	private Integer minOverdueDay;		// 最小逾期天数
	private Integer maxOverdueDay;		// 最大逾期天数
	private String content;		// 模版内容
	
	public WsxdSmsTemplate() {
		super();
	}

	public WsxdSmsTemplate(String id){
		super(id);
	}

	@Length(min=1, max=40, message="模版名称长度必须介于 1 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1, message="发送类型 0:手工 1:批量长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@NotNull(message="最小逾期天数不能为空")
	public Integer getMinOverdueDay() {
		return minOverdueDay;
	}

	public void setMinOverdueDay(Integer minOverdueDay) {
		this.minOverdueDay = minOverdueDay;
	}
	
	@NotNull(message="最大逾期天数不能为空")
	public Integer getMaxOverdueDay() {
		return maxOverdueDay;
	}

	public void setMaxOverdueDay(Integer maxOverdueDay) {
		this.maxOverdueDay = maxOverdueDay;
	}
	
	@Length(min=1, max=2000, message="模版内容长度必须介于 1 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}