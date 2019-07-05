<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件催记管理</title>
	<meta name="decorator" content="default"/>
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
		<li class="active"><a href="${ctx}/businessoperation/wsxdRepayHst/">案件催记列表</a></li>
		<shiro:hasPermission name="businessoperation:wsxdRepayHst:edit"><li><a href="${ctx}/businessoperation/wsxdRepayHst/form">案件催记添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wsxdRepayHst" action="${ctx}/businessoperation/wsxdRepayHst/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="businessoperation:wsxdRepayHst:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsxdRepayHst">
			<tr>
				<shiro:hasPermission name="businessoperation:wsxdRepayHst:edit"><td>
    				<a href="${ctx}/businessoperation/wsxdRepayHst/form?id=${wsxdRepayHst.id}">修改</a>
					<a href="${ctx}/businessoperation/wsxdRepayHst/delete?id=${wsxdRepayHst.id}" onclick="return confirmx('确认要删除该案件催记吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>