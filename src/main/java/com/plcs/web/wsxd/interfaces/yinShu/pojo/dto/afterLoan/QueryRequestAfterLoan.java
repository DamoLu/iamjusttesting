package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuMainDTO;

public class QueryRequestAfterLoan extends YinShuMainDTO {

	@JSONField(name = "ABUSER")
	private String abuser;

	@JSONField(name = "DUE_BILL_NO")
	private String dueBillNo;

	@JSONField(name = "PAGE_SIZE")
	private String pageSize = "1";

	@JSONField(name = "PAGE_POSITION")
	private String pagePosition = "1";

	public String getAbuser() {
		return abuser;
	}

	public void setAbuser(String abuser) {
		this.abuser = abuser;
	}

	public String getDueBillNo() {
		return dueBillNo;
	}

	public void setDueBillNo(String dueBillNo) {
		this.dueBillNo = dueBillNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPagePosition() {
		return pagePosition;
	}

	public void setPagePosition(String pagePosition) {
		this.pagePosition = pagePosition;
	}

	@Override
	public String toString() {
		return "QueryAfterLoanRequest [abuser=" + abuser + ", dueBillNo=" + dueBillNo + "]";
	}

}
