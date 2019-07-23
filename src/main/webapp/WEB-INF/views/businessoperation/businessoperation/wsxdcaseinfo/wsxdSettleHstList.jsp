<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结清金额列表</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/sweetalert/sweetalert.css" rel="stylesheet">
	<script src="${ctxStatic}/js/common.js"></script>
	<script src="${ctxStatic}/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/businessoperation/wsxdCaseInfo/deductHstList">结清金额列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="page" action="${ctx}/businessoperation/wsxdCaseInfo/settleHstQuery" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
    <div class="row"></div>
    <div class="class"></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-cell">
		<thead>
			<tr>
                <th>序号</th>
                <th>还款日期</th>
				<th>总金额</th>
				<th>保费</th>
				<th>手续费</th>
				<th>利息</th>
				<th>本金</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settleHst" varStatus="i">
			<tr>
                <td>
						${ ( page.pageNo - 1 ) * page.pageSize + i.count }
                </td>
                <td>
                        ${settleHst.date}
                </td>
				<td>
						￥${settleHst.amount}
				</td>
				<td>
						￥${settleHst.premium}
				</td>
				<td>
						￥${settleHst.poundage}
				</td>
				<td>
						￥${settleHst.interest}
				</td>
				<td>
						￥${settleHst.principal}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>