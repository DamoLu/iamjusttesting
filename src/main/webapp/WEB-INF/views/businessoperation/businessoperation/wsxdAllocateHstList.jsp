<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手工分案日志管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/sweetalert/sweetalert.css" rel="stylesheet">
	<script src="${ctxStatic}/js/common.js"></script>
	<script src="${ctxStatic}/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
            if (n == undefined || n == '' || s == undefined || s == '') {
                loading("正在排序，请稍等...");
                $("#btnSubmit").attr("disabled", "disabled");
                $("#btnClear").attr("disabled", "disabled");
                $("#btnExport").attr("disabled", "disabled");
            }
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        };

        // 清空搜索栏
        function clearAction() {
            $("#appOrgSelect").val("").select2();
            $("#departmentIdSelect").val("").select2();
            $("#loanOrginSelect").val("").select2();
            $(':input,#searchForm')
                .not(':button, :submit, :reset, :hidden')
                .val('');
        };

        // 全选操作
        $(function() {
            /** 获取上一次选中的案件数据 */
            var boxs = $("input[type='checkbox'][id^='box_']");

            /** 给全选按钮绑定点击事件  */
            $("#checkAll").click(function () {
                // this是checkAll  this.checked是true
                // 所有数据行的选中状态与全选的状态一致
                boxs.attr("checked", this.checked);
            });
        });

        // 表头隐藏或显示
        $(function () {
            $("#operatorList").click(function(){
                $("#searchForm").toggle();
                $("#searchForm1").toggle();
            });
        });

        // 提交查询
        function showLoading() {
            if($("#jbox-content").hasClass("jbox-content")){
                return false;
            }

            var maxOverdueDays = $("#maxOverdueDays").val();
            var minOverdueDays = $("#minOverdueDays").val();
            if (maxOverdueDays != '') {
                if (Number(minOverdueDays) > Number(maxOverdueDays)) {
                    Common.alert("最小逾期不能大于最大逾期！");
                    return false;
                }
            }

			var startOperateDate = new Date($("#startOperateDate").val());
            var endOperateDate = new Date($("#endOperateDate").val());
            if ($("#endOperateDate").val() != '') {
                if (startOperateDate > endOperateDate) {
                    Common.alert("开始时间不能大于结束时间！");
                    return false;
				}
			}

            loading("正在查询，请稍等...");
            $("#searchForm").submit();
            $("#btnSubmit").attr("disabled", "disabled");
            $("#btnClear").attr("disabled", "disabled");
            $("#btnExport").attr("disabled", "disabled");
        };

        // 导出Excel
        $(function () {
            $('#btnExport').click(function () {
                var hasCheck=($("input[type='checkbox']").is(':checked'));
                if(!hasCheck){
                    // alert("请选择导出项");
                    $('#exampleModal').modal({
                        show:true
                    });
                    return;
                }

                var selectAllFlag=$("#checkAll").is(':checked');
                var checkID = [];//定义一个空数组

                if(!selectAllFlag){
                    $("input[type='checkbox']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                        checkID[i] =$(this).val();
                    });
                }

                $("#searchForm").attr("action",'${ctx}/businessoperation/wsxdAllocateHst/exportFile');
                $("#isSelectAll").attr("value",selectAllFlag);
                $("#checkIDArr").attr("value",checkID);
                $("#searchForm").submit();
                $("#searchForm").attr("action",'${ctx}/businessoperation/wsxdAllocateHst/form');
            });
        });

	</script>
</head>
<body>
	<!-- Modal 模态框-->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">系统警告</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					请至少勾选一条案件记录！！！
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/businessoperation/wsxdAllocateHst/">手工分案日志列表</a></li>
		<shiro:hasPermission name="businessoperation:wsxdAllocateHst:edit"><li><a href="${ctx}/businessoperation/wsxdAllocateHst/form">手工分案日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wsxdAllocateHst" action="${ctx}/businessoperation/wsxdAllocateHst/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="isSelectAll" name="isAll" type="hidden"/>
		<input id="checkIDArr" name="checkIDArr" type="hidden"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label style="width: auto">合作机构：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:select path="appOrg" class="input-medium" id="appOrgSelect">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('appOrg')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</li>
			<li><label style="width: auto">事&nbsp;&nbsp;业&nbsp;&nbsp;部：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:select path="departmentId" class="input-medium" id="departmentIdSelect">
					<form:option value="" label="请选择"/>
					<form:options items="${wsxdAllocateHst.departmentList}" itemValue="departmentId" itemLabel="departmentName" htmlEscape="false"/>
				</form:select>
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">放款机构：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:select path="loanOrgin" class="input-medium" id="loanOrginSelect">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('loan_orgin')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">借据编号：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input path="loanBillNo" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入借据编号"/>
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">客户姓名：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input path="customerName" htmlEscape="false" maxlength="40" class="input-medium" placeholder="请输入客户姓名"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li><label style="width: auto">最小逾期：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input path="minOverdueDays" htmlEscape="false" maxlength="11" class="input-medium" placeholder="请输入最小逾期"/>
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">最大逾期：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input path="maxOverdueDays" htmlEscape="false" maxlength="11" class="input-medium" placeholder="请输入最大逾期"/>
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">原处理员：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input path="oldOdvName" htmlEscape="false" maxlength="20" style="width: 163px;" placeholder="请输入催收员" />
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">现处理员：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input path="odvName" htmlEscape="false" maxlength="20" style="width: 163px;" placeholder="请输入催收员" />
			</li>
			<li class="clearfix"></li>
		</ul>
		<ul class="ul-form">
			<li><label style="width: auto">操作时间范围：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input id="startOperateDate" name="startOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                       value="<fmt:formatDate value="${wsxdAllocateHst.startOperateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" placeholder="请输入操作时间"/>
			</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>
                <input id="endOperateDate" name="endOperateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                       value="<fmt:formatDate value="${wsxdAllocateHst.endOperateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" placeholder="请输入操作时间"/>
			</li>
			<li class="clearfix"></li>
		</ul>
		<ul class="ul-form">
			<div style="float:left">
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="showLoading()" value="查询"/></li>
				<li class="btns"><input id="btnClear" class="btn btn-primary" type="button" value="清空" onclick="clearAction()"/></li>
			</div>
		</ul>
	</form:form>

	<form:form id="searchForm1" modelAttribute="wsxdCase" class="breadcrumb form-search">
		<ul class="ul-form">
			<li style="float: left"><label style="width: auto">导出操作：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th style="text-align: center">
				<input type="checkbox" name="checkAll" id="checkAll">
			</th>
			<th style="text-align: center">序号</th>
			<th style="text-align: center" class="sort-column a.app_org">合作机构</th>
			<th style="text-align: center" class="sort-column a.department_name">事业部</th>
			<th style="text-align: center" class="sort-column d.label">放款机构</th>
			<th style="text-align: center" class="sort-column a.customer_id_no">客户姓名</th>
			<th style="text-align: center" class="sort-column a.loan_bill_no">借据编号</th>
			<th style="text-align: center" class="sort-column a.overdue_days">分案逾期天数</th>
			<th style="text-align: center" class="sort-column a.old_odv_name">原处理人员</th>
			<th style="text-align: center" class="sort-column a.old_odv_group_name">原处理组名</th>
			<th style="text-align: center" class="sort-column a.odv_name">现处理人员</th>
			<th style="text-align: center" class="sort-column a.odv_group_name">现处理组名</th>
			<th style="text-align: center" class="sort-column a.create_by">操作人</th>
			<th style="text-align: center" class="sort-column a.create_date">操作时间</th>
			<shiro:hasPermission name="businessoperation:wsxdCase:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsxdAllocateHst" varStatus="wsxdAllocateHstStatus">
			<tr>
				<td style="text-align: center">
					<input type="checkbox" id="box_${wsxdAllocateHstStatus.index}" value="${wsxdAllocateHst.id}">
				</td>
				<td style="text-align: center">
						${wsxdAllocateHstStatus.index + 1}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.appOrgName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.departmentName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.loanOrgin}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.customerName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.loanBillNo}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.overdueDays}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.oldOdvName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.oldOdvGroupName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.odvName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.odvGroupName}
				</td>
				<td style="text-align: center">
						${wsxdAllocateHst.createByUser}
				</td>
				<td style="text-align: center">
					<fmt:formatDate value="${wsxdAllocateHst.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="businessoperation:wsxdAllocateHst:edit"><td>
    				<a href="${ctx}/businessoperation/wsxdAllocateHst/form?id=${wsxdAllocateHst.id}">修改</a>
					<a href="${ctx}/businessoperation/wsxdAllocateHst/delete?id=${wsxdAllocateHst.id}" onclick="return confirmx('确认要删除该手工分案日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" align="center">${page}</div>
</body>
</html>