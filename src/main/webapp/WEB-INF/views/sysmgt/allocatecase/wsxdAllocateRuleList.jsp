<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分案规则排序管理</title>
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
        
        function moveUp(index, num) {
            //点击上移
            if (index == 1){
                alertx("首行数据不可上移");
            }else{
                $.ajax({
                    type: "get",
                    url: ${ctx}+ "/sysmgt/allocatecase/wsxdAllocateRule/operation?num="+ num + "&operation=up",
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data)
                    {
                        window.location.reload();
                    },
                    error: function (err)
                    {
                        alertx("操作失败");
                    }
                });
            }
        }

        function moveDown(index, num) {
            //下移
            if (index >= 3) {
                alertx("尾行数据不可下移")
            }else {
                $.ajax({
                    type: "get",
                    url: ${ctx}+ "/sysmgt/allocatecase/wsxdAllocateRule/operation?num="+ num + "&operation=down",
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data)
                    {
                        window.location.reload();
                    },
                    error: function (err)
                    {
                        alertx("操作失败");
                    }
                });
            }
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/">分案处理组列表</a></li>
		<li class="active"><a href="${ctx}/allocatecase/wsxdAllocateRule/">分案排序列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="wsxdAllocateRule" action="${ctx}/allocatecase/wsxdAllocateRule/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>规则描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsxdAllocateRule" varStatus="i">
			<tr>
				<td><a href="${ctx}/allocatecase/wsxdAllocateRule/form?id=${wsxdAllocateRule.id}">
					${wsxdAllocateRule.num}
				</a></td>
				<td>
					${wsxdAllocateRule.ruleDescribe}
				</td>
				<td>
                    <a  title="上移" class="btn btn-primary" onclick="moveUp(${i.count},${wsxdAllocateRule.num})" href = "javascript:void(0);"><i class="fa fa-sort-amount-asc" aria-hidden="true"></i>上移</a>
                    <a  title="下移" class="btn" onclick="moveDown(${i.count},${wsxdAllocateRule.num})" href = "javascript:void(0);"><i class="fa fa-sort-amount-desc" aria-hidden="true"></i>下移</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>