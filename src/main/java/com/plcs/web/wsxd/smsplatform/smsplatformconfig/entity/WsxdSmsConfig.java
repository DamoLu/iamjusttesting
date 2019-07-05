package com.plcs.web.wsxd.smsplatform.smsplatformconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.plcs.web.common.persistence.DataEntity;

/**
 * 短信平台配置Entity
 * @author luqihua
 * @version 2019-06-18
 */
public class WsxdSmsConfig extends DataEntity<WsxdSmsConfig> {
	
	private static final long serialVersionUID = 1L;
	private String returnState;		// 是否开启退订回T,0为是,1为否
	private Integer msgCount;		// 每天允许发送的数量
	
	public WsxdSmsConfig() {
		super();
	}

	public WsxdSmsConfig(String id){
		super(id);
	}

	@Length(min=0, max=1, message="是否开启退订回T,0为是,1为否长度必须介于 0 和 1 之间")
	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}
	
	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}
	
}