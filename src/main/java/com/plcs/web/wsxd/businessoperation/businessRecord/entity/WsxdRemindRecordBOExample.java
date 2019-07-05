package com.plcs.web.wsxd.businessoperation.businessRecord.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WsxdRemindRecordBOExample {
    protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public WsxdRemindRecordBOExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoIsNull() {
			addCriterion("loan_bill_no is null");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoIsNotNull() {
			addCriterion("loan_bill_no is not null");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoEqualTo(String value) {
			addCriterion("loan_bill_no =", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoNotEqualTo(String value) {
			addCriterion("loan_bill_no <>", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoGreaterThan(String value) {
			addCriterion("loan_bill_no >", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoGreaterThanOrEqualTo(String value) {
			addCriterion("loan_bill_no >=", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoLessThan(String value) {
			addCriterion("loan_bill_no <", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoLessThanOrEqualTo(String value) {
			addCriterion("loan_bill_no <=", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoLike(String value) {
			addCriterion("loan_bill_no like", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoNotLike(String value) {
			addCriterion("loan_bill_no not like", value, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoIn(List<String> values) {
			addCriterion("loan_bill_no in", values, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoNotIn(List<String> values) {
			addCriterion("loan_bill_no not in", values, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoBetween(String value1, String value2) {
			addCriterion("loan_bill_no between", value1, value2, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andLoanBillNoNotBetween(String value1, String value2) {
			addCriterion("loan_bill_no not between", value1, value2, "loanBillNo");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<String> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<String> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andPromiseDateIsNull() {
			addCriterion("promise_date is null");
			return (Criteria) this;
		}

		public Criteria andPromiseDateIsNotNull() {
			addCriterion("promise_date is not null");
			return (Criteria) this;
		}

		public Criteria andPromiseDateEqualTo(Date value) {
			addCriterionForJDBCDate("promise_date =", value, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateNotEqualTo(Date value) {
			addCriterionForJDBCDate("promise_date <>", value, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateGreaterThan(Date value) {
			addCriterionForJDBCDate("promise_date >", value, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("promise_date >=", value, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateLessThan(Date value) {
			addCriterionForJDBCDate("promise_date <", value, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("promise_date <=", value, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateIn(List<Date> values) {
			addCriterionForJDBCDate("promise_date in", values, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateNotIn(List<Date> values) {
			addCriterionForJDBCDate("promise_date not in", values, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("promise_date between", value1, value2, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseDateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("promise_date not between", value1, value2, "promiseDate");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtIsNull() {
			addCriterion("promise_amt is null");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtIsNotNull() {
			addCriterion("promise_amt is not null");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtEqualTo(BigDecimal value) {
			addCriterion("promise_amt =", value, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtNotEqualTo(BigDecimal value) {
			addCriterion("promise_amt <>", value, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtGreaterThan(BigDecimal value) {
			addCriterion("promise_amt >", value, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("promise_amt >=", value, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtLessThan(BigDecimal value) {
			addCriterion("promise_amt <", value, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtLessThanOrEqualTo(BigDecimal value) {
			addCriterion("promise_amt <=", value, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtIn(List<BigDecimal> values) {
			addCriterion("promise_amt in", values, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtNotIn(List<BigDecimal> values) {
			addCriterion("promise_amt not in", values, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("promise_amt between", value1, value2, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPromiseAmtNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("promise_amt not between", value1, value2, "promiseAmt");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNull() {
			addCriterion("phone is null");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNotNull() {
			addCriterion("phone is not null");
			return (Criteria) this;
		}

		public Criteria andPhoneEqualTo(String value) {
			addCriterion("phone =", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotEqualTo(String value) {
			addCriterion("phone <>", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThan(String value) {
			addCriterion("phone >", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("phone >=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThan(String value) {
			addCriterion("phone <", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThanOrEqualTo(String value) {
			addCriterion("phone <=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLike(String value) {
			addCriterion("phone like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotLike(String value) {
			addCriterion("phone not like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneIn(List<String> values) {
			addCriterion("phone in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotIn(List<String> values) {
			addCriterion("phone not in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneBetween(String value1, String value2) {
			addCriterion("phone between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotBetween(String value1, String value2) {
			addCriterion("phone not between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusIsNull() {
			addCriterion("phone_status is null");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusIsNotNull() {
			addCriterion("phone_status is not null");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusEqualTo(String value) {
			addCriterion("phone_status =", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusNotEqualTo(String value) {
			addCriterion("phone_status <>", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusGreaterThan(String value) {
			addCriterion("phone_status >", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusGreaterThanOrEqualTo(String value) {
			addCriterion("phone_status >=", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusLessThan(String value) {
			addCriterion("phone_status <", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusLessThanOrEqualTo(String value) {
			addCriterion("phone_status <=", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusLike(String value) {
			addCriterion("phone_status like", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusNotLike(String value) {
			addCriterion("phone_status not like", value, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusIn(List<String> values) {
			addCriterion("phone_status in", values, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusNotIn(List<String> values) {
			addCriterion("phone_status not in", values, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusBetween(String value1, String value2) {
			addCriterion("phone_status between", value1, value2, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andPhoneStatusNotBetween(String value1, String value2) {
			addCriterion("phone_status not between", value1, value2, "phoneStatus");
			return (Criteria) this;
		}

		public Criteria andRemindDateIsNull() {
			addCriterion("remind_date is null");
			return (Criteria) this;
		}

		public Criteria andRemindDateIsNotNull() {
			addCriterion("remind_date is not null");
			return (Criteria) this;
		}

		public Criteria andRemindDateEqualTo(Date value) {
			addCriterion("remind_date =", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateNotEqualTo(Date value) {
			addCriterion("remind_date <>", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateGreaterThan(Date value) {
			addCriterion("remind_date >", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateGreaterThanOrEqualTo(Date value) {
			addCriterion("remind_date >=", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateLessThan(Date value) {
			addCriterion("remind_date <", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateLessThanOrEqualTo(Date value) {
			addCriterion("remind_date <=", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateIn(List<Date> values) {
			addCriterion("remind_date in", values, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateNotIn(List<Date> values) {
			addCriterion("remind_date not in", values, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateBetween(Date value1, Date value2) {
			addCriterion("remind_date between", value1, value2, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateNotBetween(Date value1, Date value2) {
			addCriterion("remind_date not between", value1, value2, "remindDate");
			return (Criteria) this;
		}

		public Criteria andIsRemindIsNull() {
			addCriterion("is_remind is null");
			return (Criteria) this;
		}

		public Criteria andIsRemindIsNotNull() {
			addCriterion("is_remind is not null");
			return (Criteria) this;
		}

		public Criteria andIsRemindEqualTo(String value) {
			addCriterion("is_remind =", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindNotEqualTo(String value) {
			addCriterion("is_remind <>", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindGreaterThan(String value) {
			addCriterion("is_remind >", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindGreaterThanOrEqualTo(String value) {
			addCriterion("is_remind >=", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindLessThan(String value) {
			addCriterion("is_remind <", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindLessThanOrEqualTo(String value) {
			addCriterion("is_remind <=", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindLike(String value) {
			addCriterion("is_remind like", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindNotLike(String value) {
			addCriterion("is_remind not like", value, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindIn(List<String> values) {
			addCriterion("is_remind in", values, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindNotIn(List<String> values) {
			addCriterion("is_remind not in", values, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindBetween(String value1, String value2) {
			addCriterion("is_remind between", value1, value2, "isRemind");
			return (Criteria) this;
		}

		public Criteria andIsRemindNotBetween(String value1, String value2) {
			addCriterion("is_remind not between", value1, value2, "isRemind");
			return (Criteria) this;
		}

		public Criteria andRecordingIsNull() {
			addCriterion("recording is null");
			return (Criteria) this;
		}

		public Criteria andRecordingIsNotNull() {
			addCriterion("recording is not null");
			return (Criteria) this;
		}

		public Criteria andRecordingEqualTo(String value) {
			addCriterion("recording =", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingNotEqualTo(String value) {
			addCriterion("recording <>", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingGreaterThan(String value) {
			addCriterion("recording >", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingGreaterThanOrEqualTo(String value) {
			addCriterion("recording >=", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingLessThan(String value) {
			addCriterion("recording <", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingLessThanOrEqualTo(String value) {
			addCriterion("recording <=", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingLike(String value) {
			addCriterion("recording like", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingNotLike(String value) {
			addCriterion("recording not like", value, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingIn(List<String> values) {
			addCriterion("recording in", values, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingNotIn(List<String> values) {
			addCriterion("recording not in", values, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingBetween(String value1, String value2) {
			addCriterion("recording between", value1, value2, "recording");
			return (Criteria) this;
		}

		public Criteria andRecordingNotBetween(String value1, String value2) {
			addCriterion("recording not between", value1, value2, "recording");
			return (Criteria) this;
		}

		public Criteria andOdvIsNull() {
			addCriterion("odv is null");
			return (Criteria) this;
		}

		public Criteria andOdvIsNotNull() {
			addCriterion("odv is not null");
			return (Criteria) this;
		}

		public Criteria andOdvEqualTo(String value) {
			addCriterion("odv =", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvNotEqualTo(String value) {
			addCriterion("odv <>", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvGreaterThan(String value) {
			addCriterion("odv >", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvGreaterThanOrEqualTo(String value) {
			addCriterion("odv >=", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvLessThan(String value) {
			addCriterion("odv <", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvLessThanOrEqualTo(String value) {
			addCriterion("odv <=", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvLike(String value) {
			addCriterion("odv like", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvNotLike(String value) {
			addCriterion("odv not like", value, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvIn(List<String> values) {
			addCriterion("odv in", values, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvNotIn(List<String> values) {
			addCriterion("odv not in", values, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvBetween(String value1, String value2) {
			addCriterion("odv between", value1, value2, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvNotBetween(String value1, String value2) {
			addCriterion("odv not between", value1, value2, "odv");
			return (Criteria) this;
		}

		public Criteria andOdvGroupIsNull() {
			addCriterion("odv_group is null");
			return (Criteria) this;
		}

		public Criteria andOdvGroupIsNotNull() {
			addCriterion("odv_group is not null");
			return (Criteria) this;
		}

		public Criteria andOdvGroupEqualTo(String value) {
			addCriterion("odv_group =", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupNotEqualTo(String value) {
			addCriterion("odv_group <>", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupGreaterThan(String value) {
			addCriterion("odv_group >", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupGreaterThanOrEqualTo(String value) {
			addCriterion("odv_group >=", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupLessThan(String value) {
			addCriterion("odv_group <", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupLessThanOrEqualTo(String value) {
			addCriterion("odv_group <=", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupLike(String value) {
			addCriterion("odv_group like", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupNotLike(String value) {
			addCriterion("odv_group not like", value, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupIn(List<String> values) {
			addCriterion("odv_group in", values, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupNotIn(List<String> values) {
			addCriterion("odv_group not in", values, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupBetween(String value1, String value2) {
			addCriterion("odv_group between", value1, value2, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andOdvGroupNotBetween(String value1, String value2) {
			addCriterion("odv_group not between", value1, value2, "odvGroup");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateByIsNull() {
			addCriterion("create_by is null");
			return (Criteria) this;
		}

		public Criteria andCreateByIsNotNull() {
			addCriterion("create_by is not null");
			return (Criteria) this;
		}

		public Criteria andCreateByEqualTo(String value) {
			addCriterion("create_by =", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByNotEqualTo(String value) {
			addCriterion("create_by <>", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByGreaterThan(String value) {
			addCriterion("create_by >", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByGreaterThanOrEqualTo(String value) {
			addCriterion("create_by >=", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByLessThan(String value) {
			addCriterion("create_by <", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByLessThanOrEqualTo(String value) {
			addCriterion("create_by <=", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByLike(String value) {
			addCriterion("create_by like", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByNotLike(String value) {
			addCriterion("create_by not like", value, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByIn(List<String> values) {
			addCriterion("create_by in", values, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByNotIn(List<String> values) {
			addCriterion("create_by not in", values, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByBetween(String value1, String value2) {
			addCriterion("create_by between", value1, value2, "createBy");
			return (Criteria) this;
		}

		public Criteria andCreateByNotBetween(String value1, String value2) {
			addCriterion("create_by not between", value1, value2, "createBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByIsNull() {
			addCriterion("update_by is null");
			return (Criteria) this;
		}

		public Criteria andUpdateByIsNotNull() {
			addCriterion("update_by is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateByEqualTo(String value) {
			addCriterion("update_by =", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByNotEqualTo(String value) {
			addCriterion("update_by <>", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByGreaterThan(String value) {
			addCriterion("update_by >", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
			addCriterion("update_by >=", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByLessThan(String value) {
			addCriterion("update_by <", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByLessThanOrEqualTo(String value) {
			addCriterion("update_by <=", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByLike(String value) {
			addCriterion("update_by like", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByNotLike(String value) {
			addCriterion("update_by not like", value, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByIn(List<String> values) {
			addCriterion("update_by in", values, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByNotIn(List<String> values) {
			addCriterion("update_by not in", values, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByBetween(String value1, String value2) {
			addCriterion("update_by between", value1, value2, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateByNotBetween(String value1, String value2) {
			addCriterion("update_by not between", value1, value2, "updateBy");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNull() {
			addCriterion("update_date is null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNotNull() {
			addCriterion("update_date is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateEqualTo(Date value) {
			addCriterion("update_date =", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotEqualTo(Date value) {
			addCriterion("update_date <>", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThan(Date value) {
			addCriterion("update_date >", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("update_date >=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThan(Date value) {
			addCriterion("update_date <", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
			addCriterion("update_date <=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIn(List<Date> values) {
			addCriterion("update_date in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotIn(List<Date> values) {
			addCriterion("update_date not in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateBetween(Date value1, Date value2) {
			addCriterion("update_date between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
			addCriterion("update_date not between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andRemarksIsNull() {
			addCriterion("remarks is null");
			return (Criteria) this;
		}

		public Criteria andRemarksIsNotNull() {
			addCriterion("remarks is not null");
			return (Criteria) this;
		}

		public Criteria andRemarksEqualTo(String value) {
			addCriterion("remarks =", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksNotEqualTo(String value) {
			addCriterion("remarks <>", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksGreaterThan(String value) {
			addCriterion("remarks >", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksGreaterThanOrEqualTo(String value) {
			addCriterion("remarks >=", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksLessThan(String value) {
			addCriterion("remarks <", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksLessThanOrEqualTo(String value) {
			addCriterion("remarks <=", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksLike(String value) {
			addCriterion("remarks like", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksNotLike(String value) {
			addCriterion("remarks not like", value, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksIn(List<String> values) {
			addCriterion("remarks in", values, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksNotIn(List<String> values) {
			addCriterion("remarks not in", values, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksBetween(String value1, String value2) {
			addCriterion("remarks between", value1, value2, "remarks");
			return (Criteria) this;
		}

		public Criteria andRemarksNotBetween(String value1, String value2) {
			addCriterion("remarks not between", value1, value2, "remarks");
			return (Criteria) this;
		}

		public Criteria andDelFlagIsNull() {
			addCriterion("del_flag is null");
			return (Criteria) this;
		}

		public Criteria andDelFlagIsNotNull() {
			addCriterion("del_flag is not null");
			return (Criteria) this;
		}

		public Criteria andDelFlagEqualTo(String value) {
			addCriterion("del_flag =", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotEqualTo(String value) {
			addCriterion("del_flag <>", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagGreaterThan(String value) {
			addCriterion("del_flag >", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
			addCriterion("del_flag >=", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagLessThan(String value) {
			addCriterion("del_flag <", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagLessThanOrEqualTo(String value) {
			addCriterion("del_flag <=", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagLike(String value) {
			addCriterion("del_flag like", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotLike(String value) {
			addCriterion("del_flag not like", value, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagIn(List<String> values) {
			addCriterion("del_flag in", values, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotIn(List<String> values) {
			addCriterion("del_flag not in", values, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagBetween(String value1, String value2) {
			addCriterion("del_flag between", value1, value2, "delFlag");
			return (Criteria) this;
		}

		public Criteria andDelFlagNotBetween(String value1, String value2) {
			addCriterion("del_flag not between", value1, value2, "delFlag");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {
		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

}