package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuContainerDTO;

public class QueryAfterLoanContainerDTO extends YinShuContainerDTO {

	@JSONField(name = "SERVICE")
	private QueryAfterLoanMainDTO service;

	public QueryAfterLoanMainDTO getService() {
		return service;
	}

	public void setService(QueryAfterLoanMainDTO service) {
		this.service = service;
	}

}
