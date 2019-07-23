package com.plcs.web.wsxd.businessoperation.businessoperation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plcs.web.common.persistence.DataEntity;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.DepartmentVO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 分案历史Entity
 * @author tanweijian
 * @version 2019-06-11
 */
public class WsxdAllocateHst extends DataEntity<WsxdAllocateHst> {
	
	private static final long serialVersionUID = 1L;
	private String loanBillNo;		// 借据号
	private String appOrg;		// 案件范围(机构) 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app
	private String appOrgName;		// 案件范围(机构) 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app
	private String departmentId;		// 事业部
	private String departmentName;		// 事业部名
	private String hasCommonPool;		// 是否含公共池,0:是1:否 2:全部
	private String customerIdNo;		// 客户证件号
	private String odv;		// 催收员编号
	private String odvName;		// 催收员姓名
	private String oldOdv;		// 旧催收员编号
	private String oldOdvName;		// 旧催收员姓名
	private String odvGroup;		// 催收员组
	private String odvGroupName;		// 催收员组名
	private Date startDate;		// 处理开始日期
	private Date endDate;		// 处理结束日期
	private String allocateType;		// 分案类型, 0:自动分案，1手工分案
	private Integer overdueDays; // 逾期天数
	private String oldOdvGroup; // 旧催收组编号
	private String oldOdvGroupName; // 旧催收组名

	private String loanOrgin; // 放款机构
	private String customerName; // 客户姓名
	private String minOverdueDays; // 最小逾期天数
	private String maxOverdueDays; // 最大逾期天数
	private Date startOperateDate; // 开始操作时间
	private Date endOperateDate; // 结束操作时间
	private String createByUser; // 创建者
	private String updateByUser; // 更新者
	private String permissionOdv;

	private List<String> appOrgList; // 合作机构列表
	private List<DepartmentVO> departmentList; // 事业部列表
	private List<String> loanOrginList; // 放款机构列表

	public WsxdAllocateHst() {
		super();
	}

	public WsxdAllocateHst(String id){
		super(id);
	}

	@Length(min=1, max=64, message="借据号长度必须介于 1 和 64 之间")
	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}
	
	@Length(min=1, max=255, message="案件范围(机构) 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app长度必须介于 1 和 255 之间")
	public String getAppOrg() {
		return appOrg;
	}

	public void setAppOrg(String appOrg) {
		this.appOrg = appOrg;
	}
	
	@Length(min=1, max=255, message="案件范围(机构) 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app长度必须介于 1 和 255 之间")
	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}
	
	@Length(min=1, max=255, message="事业部长度必须介于 1 和 255 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=1, max=255, message="事业部名长度必须介于 1 和 255 之间")
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
	
	@Length(min=1, max=50, message="客户证件号长度必须介于 1 和 50 之间")
	public String getCustomerIdNo() {
		return customerIdNo;
	}

	public void setCustomerIdNo(String customerIdNo) {
		this.customerIdNo = customerIdNo;
	}
	
	@Length(min=1, max=64, message="催收员编号长度必须介于 1 和 64 之间")
	public String getOdv() {
		return odv;
	}

	public void setOdv(String odv) {
		this.odv = odv;
	}
	
	@Length(min=1, max=32, message="催收员姓名长度必须介于 1 和 32 之间")
	public String getOdvName() {
		return odvName;
	}

	public void setOdvName(String odvName) {
		this.odvName = odvName;
	}
	
	@Length(min=1, max=64, message="旧催收员编号长度必须介于 1 和 64 之间")
	public String getOldOdv() {
		return oldOdv;
	}

	public void setOldOdv(String oldOdv) {
		this.oldOdv = oldOdv;
	}
	
	@Length(min=1, max=32, message="旧催收员姓名长度必须介于 1 和 32 之间")
	public String getOldOdvName() {
		return oldOdvName;
	}

	public void setOldOdvName(String oldOdvName) {
		this.oldOdvName = oldOdvName;
	}
	
	@Length(min=1, max=255, message="催收员组长度必须介于 1 和 255 之间")
	public String getOdvGroup() {
		return odvGroup;
	}

	public void setOdvGroup(String odvGroup) {
		this.odvGroup = odvGroup;
	}
	
	@Length(min=1, max=255, message="催收员组名长度必须介于 1 和 255 之间")
	public String getOdvGroupName() {
		return odvGroupName;
	}

	public void setOdvGroupName(String odvGroupName) {
		this.odvGroupName = odvGroupName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="处理开始日期不能为空")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=1, max=1, message="分案类型, 0:自动分案，1手工分案长度必须介于 1 和 1 之间")
	public String getAllocateType() {
		return allocateType;
	}

	public void setAllocateType(String allocateType) {
		this.allocateType = allocateType;
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

	public Date getStartOperateDate() {
		return startOperateDate;
	}

	public void setStartOperateDate(Date startOperateDate) {
		this.startOperateDate = startOperateDate;
	}

	public Date getEndOperateDate() {
		return endOperateDate;
	}

	public void setEndOperateDate(Date endOperateDate) {
		this.endOperateDate = endOperateDate;
	}

	public List<String> getAppOrgList() {
		return appOrgList;
	}

	public void setAppOrgList(List<String> appOrgList) {
		this.appOrgList = appOrgList;
	}

	public List<DepartmentVO> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentVO> departmentList) {
		this.departmentList = departmentList;
	}

	public List<String> getLoanOrginList() {
		return loanOrginList;
	}

	public void setLoanOrginList(List<String> loanOrginList) {
		this.loanOrginList = loanOrginList;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public String getOldOdvGroup() {
		return oldOdvGroup;
	}

	public void setOldOdvGroup(String oldOdvGroup) {
		this.oldOdvGroup = oldOdvGroup;
	}

	public String getOldOdvGroupName() {
		return oldOdvGroupName;
	}

	public void setOldOdvGroupName(String oldOdvGroupName) {
		this.oldOdvGroupName = oldOdvGroupName;
	}

	public String getCreateByUser() {
		return createByUser;
	}

	public void setCreateByUser(String createByUser) {
		this.createByUser = createByUser;
	}

	public String getUpdateByUser() {
		return updateByUser;
	}

	public void setUpdateByUser(String updateByUser) {
		this.updateByUser = updateByUser;
	}

	public String getPermissionOdv() {
		return permissionOdv;
	}

	public void setPermissionOdv(String permissionOdv) {
		this.permissionOdv = permissionOdv;
	}
}