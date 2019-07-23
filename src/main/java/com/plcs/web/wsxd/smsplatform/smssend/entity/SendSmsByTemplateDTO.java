package com.plcs.web.wsxd.smsplatform.smssend.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SendSmsByTemplateDTO {

	@ApiModelProperty(value = "短信模板id")
	private String templateId;
	@ApiModelProperty(value = "发送目标手机号码列表")
	private List<String> mobiles;
	@ApiModelProperty(value = "客户手机号")
	private String customerPhone;
	@ApiModelProperty(value = "催收业务员手机号")
	private String businessPhone;
	@ApiModelProperty(value = "客户借据号")
	private String loanBillNo;

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public List<String> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}

	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}

	@Override
	public String toString() {
		return "SendSmsByTemplateDTO [templateId=" + templateId + ", mobiles=" + mobiles + ", customerPhone="
				+ customerPhone + ", businessPhone=" + businessPhone + "]";
	}

}
