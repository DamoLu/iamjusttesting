package com.plcs.web.wsxd.businessoperation.businessoperation.dto;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * 业务操作Entity
 * @author tanweijian
 * @version 2019-06-10
 */
public class WsxdCaseExportDTO {
	private String appOrg;		// 合作机构
	private String departmentName;		// 事业部
	private String loanOrgin;		// 放款机构
	private String appName;     // 产品名称
	private String customerName;		// 客户姓名
	private String loanBillNo;		// 借据编号
	private Integer overdueDays;		// 逾期天数
	private BigDecimal overdueAmt;		// 逾期金额
	private BigDecimal loanBalance;		// 贷款余额
	private String odvName; // 处理人名
	private String odvGroupName; // 处理组名
	private String createDate;	// 最近更催时间
	private String status; // 从案件催记中取出的案件状态

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public BigDecimal getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}

	public WsxdCaseExportDTO() {
		super();
	}

	@Length(min=1, max=20, message="合作机构长度必须介于 1 和 20 之间")
	public String getAppOrg() {
		return appOrg;
	}

	public void setAppOrg(String appOrg) {
		this.appOrg = appOrg;
	}

	
	@Length(min=1, max=20, message="事业部长度必须介于 1 和 20 之间")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Length(min=1, max=20, message="放款机构长度必须介于 1 和 20 之间")
	public String getLoanOrgin() {
		return loanOrgin;
	}

	public void setLoanOrgin(String loanOrgin) {
		this.loanOrgin = loanOrgin;
	}

	
	@Length(min=1, max=100, message="借据编号长度必须介于 1 和 100 之间")
	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}
	
	@Length(min=1, max=40, message="客户姓名长度必须介于 1 和 40 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOdvGroupName() {
		return odvGroupName;
	}

	public void setOdvGroupName(String odvGroupName) {
		this.odvGroupName = odvGroupName;
	}

	public String getOdvName() {
		return odvName;
	}

	public void setOdvName(String odvName) {
		this.odvName = odvName;
	}


	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String  createDate) {
		this.createDate = createDate;
	}
}