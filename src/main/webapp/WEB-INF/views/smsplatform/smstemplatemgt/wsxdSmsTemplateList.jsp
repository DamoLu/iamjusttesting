<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信模板管理</title>
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

		// 清空搜索栏
		function clearAction() {
			$("#nameSelect").val("").select2();
			$(':input,#searchForm')
					.not(':button, :submit, :reset, :hidden')
					.val('');
		}

		function checkQuery(){
			var minOverdueDay = parseInt($("#searchForm #minOverdueDay").val());
			var maxOverdueDay = parseInt($("#searchForm #maxOverdueDay").val());
			if (maxOverdueDay < minOverdueDay) {
				Common.alert("最小逾期不能大于最大逾期");
			}else {
				$("#searchForm").submit();
			}
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/smstemplatemgt/wsxdSmsTemplate/">短信模板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="wsxdSmsTemplate" action="${ctx}/smstemplatemgt/wsxdSmsTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label class="control-label">模版名称：</label>
				<form:select style="width: 177px" id="nameSelect" path="name" class="input-xlarge required">
					<form:option value="" label=""/>
					<option value="" label=""></option>
					<c:forEach items="${nameList}" var="wsxdSmsTemplateName">
						<option value="${wsxdSmsTemplateName}" <c:if test="${wsxdSmsTemplate.name== wsxdSmsTemplateName}">selected</c:if>>${wsxdSmsTemplateName}</option>
					</c:forEach>
				</form:select>
			</li>
			<li><label style="display: inline">最小逾期天数：</label>
				<form:input path="minOverdueDay" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label style="display: inline">最大逾期天数：</label>
				<form:input path="maxOverdueDay" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" onclick="checkQuery()">查询</a></li>
			<li class="btns"><input id="btnClear" class="btn btn-primary" type="button" value="清空" onclick="clearAction()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
    <div class="row"></div>
    <div class="row">
        <li class="btns"><a class="btn btn-primary" href="${ctx}/smstemplatemgt/wsxdSmsTemplate/form">&nbsp;&nbsp;新增</a></li>
    </div>
    <div class="class"></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-cell">
		<thead>
			<tr>
                <th>序号</th>
				<th>模版名称</th>
				<th>模版内容</th>
				<th>逾期范围</th>
				<th>发送类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsxdSmsTemplate" varStatus="i">
			<tr>
                <td>
						${ ( page.pageNo - 1 ) * page.pageSize + i.count }
                </td>
				<td>
					${wsxdSmsTemplate.name}
				</a></td>
				<td>
					${wsxdSmsTemplate.content}
				</td>
				<td>
						${wsxdSmsTemplate.minOverdueDay}-${wsxdSmsTemplate.maxOverdueDay}
				</td>
				<td>
					<c:if test="${wsxdSmsTemplate.type == '0'}">
						手工
					</c:if>
					<c:if test="${wsxdSmsTemplate.type == '1'}">
						批量
					</c:if>
				</td>
				<td>
    				<a href="${ctx}/smstemplatemgt/wsxdSmsTemplate/form?id=${wsxdSmsTemplate.id}">修改</a>&nbsp;
					<a href="${ctx}/smstemplatemgt/wsxdSmsTemplate/delete?id=${wsxdSmsTemplate.id}" onclick="return confirmx('确认要删除该短信模板吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>