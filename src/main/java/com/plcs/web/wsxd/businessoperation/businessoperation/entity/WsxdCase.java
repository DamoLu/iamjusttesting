package com.plcs.web.wsxd.businessoperation.businessoperation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plcs.web.common.persistence.DataEntity;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 业务操作Entity
 * @author tanweijian
 * @version 2019-06-10
 */
public class WsxdCase extends DataEntity<WsxdCase> {
	
	private static final long serialVersionUID = 1L;
	private Date dataDate;		// 数据日期
	private String appOrg;		// 合作机构
	private String appOrgName; // 合作机构编码
	private String departmentId;		// 事业部编号
	private String departmentName;		// 事业部
	private String loanOrgin;		// 放款机构
	private String custLevel;		// 客户级别
	private String appNo;		// 申请编号
	private String loanBillNo;		// 借据编号
	private String customerName;		// 客户姓名
	private String customerIdNo;		// 身份证号
	private String managerId;		// 客户经理编号
	private String managerName;		// 客户经理名称
	private String teamManagerName;		// 团队经理名称
	private String isFirstOverdue;		// 是否首次逾期 0:是 1:否
	private Date loanDate;		// 放款日期
	private Date dueDate;		// 到期日期
	private String billDate;		// 账期
	private String loanTerms;		// 总期数（贷款期限）
	private BigDecimal loanPrin;		// 贷款本金
	private BigDecimal interest;		// 贷款利息
	private String isSettle;		// 是否已还清 0:是 1:否
	private BigDecimal loanTermFee;		// 手续费
	private BigDecimal accountFee;		// 服务费
	private BigDecimal currentDebt;		// 当前欠款
	private BigDecimal loanBalance;		// 贷款余额
	private BigDecimal lastInterest;		// 剩余利息
	private BigDecimal lastLoanBalance;		// 剩余手续费
	private BigDecimal lastAccountFee;		// 剩余服务费
	private String overdueTerm;		// 逾期期数
	private BigDecimal overduePrin;		// 逾期本金
	private BigDecimal overdueInt;		// 逾期利息
	private BigDecimal overdueFee;		// 逾期手续费
	private BigDecimal overdueAccountFee;		// 逾期账户管理费
	private BigDecimal overdueLateFee;		// 逾期违约金
	private BigDecimal overduePenalty;		// 罚息
	private BigDecimal overdueCompound;		// 复利
	private Integer overdueDays;		// 逾期天数
	private BigDecimal overdueAmt;		// 逾期总金额度
	private Integer realtimeOverdueDays;		// 逾期天数（接口）
	private BigDecimal realtimeOverdueAmt;		// 逾期金额（接口）
	private String appType;		// 产品类型
	private String appName;     // 产品名称
	private String caseType;		// 案件类型(0内催案件,1诉讼仲裁案件,2外包案件)
	private String caseStatus;		// 案件状态(0逾期,1（正常）结清当期,2结清本笔)
	private String sourceCore;		//案件来源核心(01:银数、02:安硕、03:创新、C:c金所）
	private String remindStatus; // 催记状态
	private String innerType;		// 内催状态(0非理赔非核销,1理赔,2核销)
	private Date presentDate;		// 当前日期
    private String contractNo; // 合同编号
    private String caseLabel; // 案件标签
    private String overdueStartTerm; // 逾期开始期数
    private Date appointStart; // 委案开始日期
    private Date appointEnd; // 委案结束日期
    private String mark1; // 备注1
    private String mark2; // 备注2

	private String telephoneNo; // 电话号码
	private String odv; // 处理人员Id
	private String minOverdueDays; // 最小逾期天数
	private String maxOverdueDays; // 最大逾期天数
	private String status; // 从案件催记中取出的案件状态
	private String odvGroupName; // 处理组名
	private String odvName; // 处理人名
	private String checkID; // 案件ID列表
	private boolean selectAllFlag; // 是否全选

	private List<String> appOrgList; // 合作机构列表
	private List<String> loanOrginList; // 放款机构列表
	private List<String> caseStatusList; // 案件状态列表
	private List<String> remindStatusList;// 催记状态列表
	private List<ProductNameVO> productNameList; // 产品名称列表
	private List<DepartmentVO> departmentList; // 事业部列表
	private List<OdvOV> odvList; // 催收人员列表

	private List<OdvOV> csyList; // 处理人员组列表
	private List<OdvGroupVO> odvGroupList; // 处理组列表
	private List<ManagerVO> managerList; // 客户经理列表
	private String odvGroup; // 处理组Id
	private String csy; // 催收员
	private String permissionOdv;
	private String search; // 是否进行搜索
	private String find; // 查询功能

	// 业务查看详情-基本信息
	private String gender; // 性别
	private String company; // 单位名称
	private String maritalStatus; // 婚姻状况
	private String mateName; // 配偶姓名
	private String mail; // 邮箱
	private String mateIdNo; // 配偶身份证号
	private String overdueCount; // 历史逾期次数
	private Date overdueStartDate; // 逾期开始日期
	private String bankCardNo;	// 银行卡号

	private int pageNo;		// 当前页
	private int pageSize;	// 一页显示条数

    private String dictType; // 字典类型
	private String dictValue; // 字典编码

	private List<String> appNameList;

	public List<String> getAppNameList() {
		return appNameList;
	}

	public void setAppNameList(List<String> appNameList) {
		this.appNameList = appNameList;
	}

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getPermissionOdv() {
		return permissionOdv;
	}

	public void setPermissionOdv(String permissionOdv) {
		this.permissionOdv = permissionOdv;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public WsxdCase() {
		super();
	}

	public WsxdCase(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="数据日期不能为空")
	public Date getDataDate() {
		return dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	
	@Length(min=1, max=20, message="合作机构长度必须介于 1 和 20 之间")
	public String getAppOrg() {
		return appOrg;
	}

	public void setAppOrg(String appOrg) {
		this.appOrg = appOrg;
	}
	
	@Length(min=1, max=20, message="事业部编号长度必须介于 1 和 20 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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
	
	@Length(min=0, max=20, message="客户级别长度必须介于 0 和 20 之间")
	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	
	@Length(min=1, max=100, message="合同编号长度必须介于 1 和 100 之间")
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
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
	
	@Length(min=1, max=50, message="身份证号长度必须介于 1 和 50 之间")
	public String getCustomerIdNo() {
		return customerIdNo;
	}

	public void setCustomerIdNo(String customerIdNo) {
		this.customerIdNo = customerIdNo;
	}
	
	@Length(min=0, max=36, message="客户经理编号长度必须介于 0 和 36 之间")
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	@Length(min=0, max=32, message="客户经理名称长度必须介于 0 和 32 之间")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	@Length(min=0, max=32, message="团队经理名称长度必须介于 0 和 32 之间")
	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	
	@Length(min=0, max=1, message="是否首次逾期 0:是 1:否长度必须介于 0 和 1 之间")
	public String getIsFirstOverdue() {
		return isFirstOverdue;
	}

	public void setIsFirstOverdue(String isFirstOverdue) {
		this.isFirstOverdue = isFirstOverdue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Length(min=0, max=16, message="账期长度必须介于 0 和 16 之间")
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
	@Length(min=0, max=20, message="总期数（贷款期限）长度必须介于 0 和 20 之间")
	public String getLoanTerms() {
		return loanTerms;
	}

	public void setLoanTerms(String loanTerms) {
		this.loanTerms = loanTerms;
	}
	
	public BigDecimal getLoanPrin() {
		return loanPrin;
	}

	public void setLoanPrin(BigDecimal loanPrin) {
		this.loanPrin = loanPrin;
	}
	
	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	
	@Length(min=0, max=1, message="是否已还清 0:是 1:否长度必须介于 0 和 1 之间")
	public String getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(String isSettle) {
		this.isSettle = isSettle;
	}
	
	public BigDecimal getLoanTermFee() {
		return loanTermFee;
	}

	public void setLoanTermFee(BigDecimal loanTermFee) {
		this.loanTermFee = loanTermFee;
	}
	
	public BigDecimal getAccountFee() {
		return accountFee;
	}

	public void setAccountFee(BigDecimal accountFee) {
		this.accountFee = accountFee;
	}
	
	public BigDecimal getCurrentDebt() {
		return currentDebt;
	}

	public void setCurrentDebt(BigDecimal currentDebt) {
		this.currentDebt = currentDebt;
	}
	
	public BigDecimal getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}
	
	public BigDecimal getLastInterest() {
		return lastInterest;
	}

	public void setLastInterest(BigDecimal lastInterest) {
		this.lastInterest = lastInterest;
	}
	
	public BigDecimal getLastLoanBalance() {
		return lastLoanBalance;
	}

	public void setLastLoanBalance(BigDecimal lastLoanBalance) {
		this.lastLoanBalance = lastLoanBalance;
	}
	
	public BigDecimal getLastAccountFee() {
		return lastAccountFee;
	}

	public void setLastAccountFee(BigDecimal lastAccountFee) {
		this.lastAccountFee = lastAccountFee;
	}
	
	@Length(min=0, max=20, message="逾期期数长度必须介于 0 和 20 之间")
	public String getOverdueTerm() {
		return overdueTerm;
	}

	public void setOverdueTerm(String overdueTerm) {
		this.overdueTerm = overdueTerm;
	}
	
	public BigDecimal getOverduePrin() {
		return overduePrin;
	}

	public void setOverduePrin(BigDecimal overduePrin) {
		this.overduePrin = overduePrin;
	}
	
	public BigDecimal getOverdueInt() {
		return overdueInt;
	}

	public void setOverdueInt(BigDecimal overdueInt) {
		this.overdueInt = overdueInt;
	}
	
	public BigDecimal getOverdueFee() {
		return overdueFee;
	}

	public void setOverdueFee(BigDecimal overdueFee) {
		this.overdueFee = overdueFee;
	}
	
	public BigDecimal getOverdueAccountFee() {
		return overdueAccountFee;
	}

	public void setOverdueAccountFee(BigDecimal overdueAccountFee) {
		this.overdueAccountFee = overdueAccountFee;
	}
	
	public BigDecimal getOverdueLateFee() {
		return overdueLateFee;
	}

	public void setOverdueLateFee(BigDecimal overdueLateFee) {
		this.overdueLateFee = overdueLateFee;
	}
	
	public BigDecimal getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(BigDecimal overduePenalty) {
		this.overduePenalty = overduePenalty;
	}
	
	public BigDecimal getOverdueCompound() {
		return overdueCompound;
	}

	public void setOverdueCompound(BigDecimal overdueCompound) {
		this.overdueCompound = overdueCompound;
	}
	
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
	
	@Length(min=0, max=20, message="产品类型长度必须介于 0 和 20 之间")
	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
	
	@Length(min=0, max=1, message="案件类型(0内催案件,1诉讼仲裁案件,2外包案件)长度必须介于 0 和 1 之间")
	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	
	@Length(min=0, max=1, message="案件状态(0逾期,1正常（结清当期）,2结清本笔 ) 长度必须介于 0 和 1 之间")
	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	@Length(min=0, max=1, message="内催状态(0非理赔非核销,1理赔,2核销)长度必须介于 0 和 1 之间")
	public String getInnerType() {
		return innerType;
	}

	public void setInnerType(String innerType) {
		this.innerType = innerType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPresentDate() {
		return presentDate;
	}

	public void setPresentDate(Date presentDate) {
		this.presentDate = presentDate;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public String getSourceCore() {
		return sourceCore;
	}

	public void setSourceCore(String sourceCore) {
		this.sourceCore = sourceCore;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getOdv() {
		return odv;
	}

	public void setOdv(String odv) {
		this.odv = odv;
	}

	public String getMinOverdueDays() {
		return minOverdueDays;
	}

	public void setMinOverdueDays(String minOverdueDays) {
		this.minOverdueDays = minOverdueDays;
	}

	public String getMaxOverdueDays() {
		return maxOverdueDays;
	}

	public void setMaxOverdueDays(String maxOverdueDays) {
		this.maxOverdueDays = maxOverdueDays;
	}

	public List<String> getAppOrgList() {
		return appOrgList;
	}

	public void setAppOrgList(List<String> appOrgList) {
		this.appOrgList = appOrgList;
	}

	public List<String> getLoanOrginList() {
		return loanOrginList;
	}

	public void setLoanOrginList(List<String> loanOrginList) {
		this.loanOrginList = loanOrginList;
	}

	public List<String> getCaseStatusList() {
		return caseStatusList;
	}

	public void setCaseStatusList(List<String> caseStatusList) {
		this.caseStatusList = caseStatusList;
	}

	public List<DepartmentVO> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentVO> departmentList) {
		this.departmentList = departmentList;
	}

	public List<OdvOV> getOdvList() {
		return odvList;
	}

	public void setOdvList(List<OdvOV> odvList) {
		this.odvList = odvList;
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

	public List<OdvOV> getCsyList() {
		return csyList;
	}

	public void setCsyList(List<OdvOV> csyList) {
		this.csyList = csyList;
	}

	public List<OdvGroupVO> getOdvGroupList() {
		return odvGroupList;
	}

	public void setOdvGroupList(List<OdvGroupVO> odvGroupList) {
		this.odvGroupList = odvGroupList;
	}

	public String getOdvGroup() {
		return odvGroup;
	}

	public void setOdvGroup(String odvGroup) {
		this.odvGroup = odvGroup;
	}

	public String getCsy() {
		return csy;
	}

	public void setCsy(String csy) {
		this.csy = csy;
	}

	public List<ManagerVO> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<ManagerVO> managerList) {
		this.managerList = managerList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<ProductNameVO> getProductNameList() {
		return productNameList;
	}

	public void setProductNameList(List<ProductNameVO> productNameList) {
		this.productNameList = productNameList;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMateIdNo() {
		return mateIdNo;
	}

	public void setMateIdNo(String mateIdNo) {
		this.mateIdNo = mateIdNo;
	}

	public String getOverdueCount() {
		return overdueCount;
	}

	public void setOverdueCount(String overdueCount) {
		this.overdueCount = overdueCount;
	}

	public Date getOverdueStartDate() {
		return overdueStartDate;
	}

	public void setOverdueStartDate(Date overdueStartDate) {
		this.overdueStartDate = overdueStartDate;
	}

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCaseLabel() {
        return caseLabel;
    }

    public void setCaseLabel(String caseLabel) {
        this.caseLabel = caseLabel;
    }

    public String getOverdueStartTerm() {
        return overdueStartTerm;
    }

    public void setOverdueStartTerm(String overdueStartTerm) {
        this.overdueStartTerm = overdueStartTerm;
    }

    public Date getAppointStart() {
        return appointStart;
    }

    public void setAppointStart(Date appointStart) {
        this.appointStart = appointStart;
    }

    public Date getAppointEnd() {
        return appointEnd;
    }

    public void setAppointEnd(Date appointEnd) {
        this.appointEnd = appointEnd;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}

	public String getCheckID() {
		return checkID;
	}

	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}

	public boolean getSelectAllFlag() {
		return selectAllFlag;
	}

	public void setSelectAllFlag(boolean selectAllFlag) {
		this.selectAllFlag = selectAllFlag;
	}

	public String getRemindStatus() {
		return remindStatus;
	}

	public void setRemindStatus(String remindStatus) {
		this.remindStatus = remindStatus;
	}

	public List<String> getRemindStatusList() {
		return remindStatusList;
	}

	public void setRemindStatusList(List<String> remindStatusList) {
		this.remindStatusList = remindStatusList;
	}
}