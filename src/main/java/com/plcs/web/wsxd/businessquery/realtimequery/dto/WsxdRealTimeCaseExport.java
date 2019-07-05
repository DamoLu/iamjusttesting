package com.plcs.web.wsxd.businessquery.realtimequery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plcs.web.common.persistence.DataEntity;
import com.plcs.web.common.utils.excel.annotation.ExcelField;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.ManagerVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvGroupVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 业务操作Entity
 * @author zhengjiangbo
 * @version 2019-06-19
 */
public class WsxdRealTimeCaseExport {

	private static final long serialVersionUID = 1L;
	@ExcelField(title="合作机构", value="appOrg", sort=10)
	private String appOrg;		// 合作机构
	@ExcelField(title="事业部", value="departmentName", sort=20)
	private String departmentName;		// 事业部
	@ExcelField(title="放款机构", value="loanOrgin", sort=30)
	private String loanOrgin;		// 放款机构
	@ExcelField(title="客户姓名", value="customerName", sort=40)
	private String customerName;		// 客户姓名
	@ExcelField(title="借据编号", value="loanBillNo", sort=50)
	private String loanBillNo;		// 借据编号

	@ExcelField(title="逾期天数", value="realtimeOverdueDays", sort=60)
	private Integer realtimeOverdueDays;		// 逾期天数（接口）
	@ExcelField(title="逾期总金额", value="realtimeOverdueAmt", sort=70)
	private BigDecimal realtimeOverdueAmt;		// 逾期金额（接口）
	@ExcelField(title="贷款余额", value="loanBalance", sort=80)
	private BigDecimal loanBalance;		// 贷款余额


	@ExcelField(title="处理人名", value="odvName", sort=90)
	private String odvName; // 处理人名
	@ExcelField(title="处理组名", value="odvGroupName", sort=100)
	private String odvGroupName; // 处理组名
	@ExcelField(title="最近更催时间", value="createDate", sort=110)
	private Date createDate;	// 最近更催时间





	public String getAppOrg() {
		return appOrg;
	}

	public void setAppOrg(String appOrg) {
		this.appOrg = appOrg;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public String getLoanOrgin() {
		return loanOrgin;
	}

	public void setLoanOrgin(String loanOrgin) {
		this.loanOrgin = loanOrgin;
	}


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

    public String getLoanBillNo() {
        return loanBillNo;
    }

    public void setLoanBillNo(String loanBillNo) {
        this.loanBillNo = loanBillNo;
    }

    public Integer getRealtimeOverdueDays() {
		return realtimeOverdueDays;
	}

	public void setRealtimeOverdueDays(Integer realtimeOverdueDays) {
		this.realtimeOverdueDays = realtimeOverdueDays;
	}

	public BigDecimal getRealtimeOverdueAmt() {
		return realtimeOverdueAmt;
	}

	public void setRealtimeOverdueAmt(BigDecimal realtimeOverdueAmt) {
		this.realtimeOverdueAmt = realtimeOverdueAmt;
	}


	public BigDecimal getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}

	public String getOdvName() {
		return odvName;
	}

	public void setOdvName(String odvName) {
		this.odvName = odvName;
	}

	public String getOdvGroupName() {
		return odvGroupName;
	}

	public void setOdvGroupName(String odvGroupName) {
		this.odvGroupName = odvGroupName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}