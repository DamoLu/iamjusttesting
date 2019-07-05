package com.plcs.web.wsxd.smsplatform.smssend.entity;

public class CustomerBO {

	private String id;
	private String customerIdNo;
	private String phone;
	private String gender;
	private String customerName;
	private String loanBillNo;

	public String getLoanBillNo() {
		return loanBillNo;
	}

	public void setLoanBillNo(String loanBillNo) {
		this.loanBillNo = loanBillNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerIdNo() {
		return customerIdNo;
	}

	public void setCustomerIdNo(String customerIdNo) {
		this.customerIdNo = customerIdNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CustomerBO [id=" + id + ", customerIdNo=" + customerIdNo + ", phone=" + phone + ", gender=" + gender
				+ ", customerName=" + customerName + ", loanBillNo=" + loanBillNo + "]";
	}

}
