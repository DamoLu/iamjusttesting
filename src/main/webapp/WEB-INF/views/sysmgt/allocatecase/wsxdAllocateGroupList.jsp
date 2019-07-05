<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分案处理组管理</title>

	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/sweetalert/css/sweetalert.css" rel="stylesheet">
    <link href="${ctxStatic}/wsxd/sysmgt/allocatecase/css/wsxdAllocateGroupList.css" rel="stylesheet">
    <script type="text/javascript">
		var layerIndex;
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function groupScopeDetail(obj){
			var id = $(obj).attr("wsxdAllocateGroupId");
			top.$.jBox("iframe:"+${ctx} + '/sysmgt/allocatecase/wsxdAllocateGroup/caseScope?id=' + id,{
				width: 300,
				height: 300,
				title: "案件范围",
				buttons: { '关闭':true }
			});
		}
	</script>
	<script type='text/javascript' src="${ctxStatic}/sweetalert/js/sweetalert.min.js"></script>
	<script type='text/javascript' src="${ctxStatic}/common/js/common.js"></script>
	<script type="text/javascript">
		function stopCase(groupId){
			Common.confirm("禁用", "确认要禁用该处理组吗?", function (isConfirm) {
				if (isConfirm) {
					var group = {
						id : groupId,
						status : '0',
					};
					$.ajax({
						type: "POST",
						url: ctx + '/sysmgt/allocatecase/wsxdAllocateGroup/stopOrRestart',
						contentType: "application/json; charset=utf-8",
						data: JSON.stringify(group),
						success: function (result) {
							if (result.success) {
								Common.alert("禁用成功", function () {
									window.location.href = ctx + "/sysmgt/allocatecase/wsxdAllocateGroup/list";
								});
							} else {
								switch (result.code) {
									case "-6" :
										Common.error('逾期范围已重叠');
										break;
									case "-7" :
										Common.error('一个用户只能出现在一个处理组');
										break;
									default:
										Common.error("禁用失败");
								}
							}
						},
						error: function () {
							Common.error("禁用失败");
						}

					});
				}
			});
		}

		function startCase(groupId){
			Common.confirm("启用", "确认要启用该处理组吗?", function (isConfirm) {
				if (isConfirm) {
					var group = {
						id : groupId,
						status : '1',
					};
					$.ajax({
						type: "POST",
						url: ctx + '/sysmgt/allocatecase/wsxdAllocateGroup/stopOrRestart',
						contentType: "application/json; charset=utf-8",
						data: JSON.stringify(group),
						success: function (result) {
							if (result.success) {
								Common.alert("启用成功", function () {
									window.location.href = ctx + "/sysmgt/allocatecase/wsxdAllocateGroup/list";
								});
							} else {
								switch (result.code) {
									case "-6" :
										Common.error('逾期范围已重叠');
										break;
									case "-7" :
										Common.error('一个用户只能出现在一个处理组');
										break;
									default:
										Common.error("启用失败");
								}
							}
						},
						error: function () {
							Common.error("启用失败");
						}

					});
				}
			});
		}

		function reAllocateCase() {
			$.ajax({
				type: "get",
				url: ${ctx}+ "/sysmgt/allocatecase/wsxdAllocateGroup/reAllocateCase",
				contentType: "application/json",
				dataType: "json",
                beforeSend: pageLoading("正在分案...."),

				success: function (data)
				{
                    pageCloseLoading();
                    if (data.code == 200) {
                        Common.alert("分案成功");
                    }else {
                        Common.alert("分案失败");
                    }
				},
				error: function ()
				{
                    pageCloseLoading();
                    Common.alert("分案失败");
				}
			});
		}

        function pageLoading(mess) {
		    $('.loadingModal').css("display","block");
            loading(mess);

        }

        function pageCloseLoading () {
            closeLoading();
            $('.loadingModal').css("display","none");
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/">分案处理组列表</a></li>
		<li><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateRule/">分案排序列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="wsxdAllocateGroup" action="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><a class="btn btn-primary"  href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/edit">新增</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th>序号</th>
				<th>处理组名</th>
				<th>处理人员</th>
				<th>逾期范围</th>
				<th>案件范围</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsxdAllocateGroup" varStatus="i">
			<tr>
                <td>
                        ${ ( page.pageNo - 1 ) * page.pageSize + i.count }
                </td>
                <td>
                        ${wsxdAllocateGroup.groupName}
                </td>
                <td>
                        ${wsxdAllocateGroup.odvsName}
                </td>
                <td>
                        ${wsxdAllocateGroup.minOverdueDay}- ${wsxdAllocateGroup.maxOverdueDay}
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="groupScopeDetail(this)" wsxdAllocateGroupId = "${wsxdAllocateGroup.id}">查看</a>
                </td>
                <td>
                    <c:if test="${wsxdAllocateGroup.status == '1'}">
                        启用
                    </c:if>
                    <c:if test="${wsxdAllocateGroup.status == '0'}">
                        禁用
                    </c:if>
                </td>
				<td>
    				<a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/detail?id=${wsxdAllocateGroup.id}">查看详情</a>&nbsp;&nbsp;
    				<a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/edit?groupId=${wsxdAllocateGroup.id}">修改信息</a>&nbsp;&nbsp;
					<c:if test="${wsxdAllocateGroup.status == '1'}">
						<a href="#" onclick="stopCase('${wsxdAllocateGroup.id}');" >禁用</a>
					</c:if>
					<c:if test="${wsxdAllocateGroup.status == '0'}">
						<a href="#" onclick="startCase('${wsxdAllocateGroup.id}');" >启用</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div class="row">
		<div style="text-align: center">
			<button style="background: #1c84c6;color:white;border-radius: 5px; height:35px"
					type="button" title="重新分案" onclick="reAllocateCase()">
				重新分案
			</button>
		</div>
	</div>
    <div class="loadingModal"></div>
</body>

</html>