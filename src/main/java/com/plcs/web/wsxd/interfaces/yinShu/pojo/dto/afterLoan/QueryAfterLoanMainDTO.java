package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.afterLoan;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuContainerDTO;

public class QueryAfterLoanMainDTO extends YinShuContainerDTO {

	@JSONField(name = "SERVICE_HEADER")
	private QueryHeaderAfterLoan header;

	@JSONField(name = "SERVICE_BODY")
	private QueryBodyAfterLoanDTO body;

	public QueryHeaderAfterLoan getHeader() {
		return header;
	}

	public void setHeader(QueryHeaderAfterLoan header) {
		this.header = header;
	}

	public QueryBodyAfterLoanDTO getBody() {
		return body;
	}

	public void setBody(QueryBodyAfterLoanDTO body) {
		this.body = body;
	}

}
