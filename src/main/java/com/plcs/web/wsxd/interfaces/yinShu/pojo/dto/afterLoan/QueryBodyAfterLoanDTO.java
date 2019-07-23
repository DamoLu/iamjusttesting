package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuServiceBodyDTO;

public class QueryBodyAfterLoanDTO extends YinShuServiceBodyDTO {

	@JSONField(name = "REQUEST")
	private QueryRequestAfterLoan request;

	public QueryRequestAfterLoan getRequest() {
		return request;
	}

	public void setRequest(QueryRequestAfterLoan request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "QueryBodyAfterLoanDTO [request=" + request + "]";
	}

}
