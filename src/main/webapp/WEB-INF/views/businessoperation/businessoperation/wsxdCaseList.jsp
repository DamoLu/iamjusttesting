<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务操作管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/sweetalert/sweetalert.css" rel="stylesheet">
	<script src="${ctxStatic}/js/common.js"></script>
	<script src="${ctxStatic}/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		var odvMap = {};

        // list转map
        function dictListToMap(list) {
            var map = {};
            for(var index in list){
                map[list[index].odvGroup] = list[index].odvOVList;
            }
            return map;
        }

		$(document).ready(function() {
			// 加载页面完毕，初始化处理组、处理人员组列表
            getOdvGroupList();
            // 选择处理组时联动处理人员组列表
            $("#odvGroupNameSelect").change(function () {
				var $odvGroupSelect= $('#odvGroupNameSelect option:selected'); // 被选中的事业部|组
				var groupId = $odvGroupSelect.val();
                getOdvList(groupId);
            });
		});

        // 获得获取所有事业部
        function getOdvGroupList() {
            $.ajax({
                type: "POST",
                url: "${ctx}/businessoperation/wsxdCase/getOdvGroupList.json",
                data: JSON.stringify({"": ""}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    odvMap = dictListToMap(result);
                    var odv = $("#odvGroupNameSelect");
                    odv.empty();
                    odv.append('<option value=""></option>');
                    $.each(result, function (idx, obj) {
                        var selected = "";
                        var option = '<option value="' + obj.odvGroup + '"' + selected + '>' + obj.odvGroupName + '</option>';
                        odv.append(option);
                    });
                }
            });
        };

        // 清空处理人员列表
        function clearListAction() {
            var form = $(this).parents("searchForm1");
            form.find("input[type='text']").val("");
            form.find("select[class='input-medium]").val("");
            if($("select.select2").length > 0){
                $("select.select2").each(function(i,e){$(e).select2("val","")});
            }
        };

        // 获得获取所有催收员
        function getOdvList(odvGroupId) {
            var odvMapElement = odvMap[odvGroupId];
            console.log(odvMapElement)
			var odv = $("#odvNameSelect");
			odv.empty();
			odv.append('<option value=""></option>');
			for(var i = 0; i <odvMapElement.length; i++) {
                var selected = "";
                var option = '<option value="' + odvMapElement[i].odv + '"' + selected + '>' + odvMapElement[i].odv + "-" + odvMapElement[i].odvName + '</option>';
                odv.append(option);
			}
			clearListAction(); // 清空组件选择效果
        };

        // 清空搜索栏
		function clearAction() {
			$("#appOrgSelect").val("").select2();
            $("#departmentIdSelect").val("").select2();
            $("#loanOrginSelect").val("").select2();
            $("#odvSelect").val("").select2();
            $("#statusSelect").val("").select2();
            $("#managerSelect").val("").select2();
            $("#productSelect").val("").select2();
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

        // 分页
		function page(n,s){
		    if (n == undefined || n == '' || s == undefined || s == '') {
                $("#pageNo").val();
                $("#pageSize").val();
                loading("正在排序，请稍等...");
                $("#btnSubmit").attr("disabled", "disabled");
                $("#btnClear").attr("disabled", "disabled");
                $("#btnExport").attr("disabled", "disabled");
                $("#btnDistribute").attr("disabled", "disabled");
                $("#searchForm").submit();
			} else {
                $("#pageNo").val(n);
                $("#pageSize").val(s);
                $("#searchForm").submit();
			}
        	return false;
        };

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

            loading("正在查询，请稍等...");
            $("#searchForm").submit();
            $("#btnSubmit").attr("disabled", "disabled");
            $("#btnClear").attr("disabled", "disabled");
            $("#btnExport").attr("disabled", "disabled");
            $("#btnDistribute").attr("disabled", "disabled");
        };

        // 导出Excel
        $(function () {
            $('#btnExport').click(function () {
                var hasCheck=($("input[type='checkbox']").is(':checked'));
                if(!hasCheck){
                    // alert("请选择导出项");
                    Common.alert("请选择要需要导出的逾期案件！");
                    return;
                }

                var selectAllFlag=$("#checkAll").is(':checked');
                var checkID = [];//定义一个空数组

                if(!selectAllFlag){
                    $("input[type='checkbox']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                        checkID[i] =$(this).val();
                    });
                }

                $("#searchForm").attr("action",'${ctx}/businessoperation/wsxdCase/exportFile');
                $("#isSelectAll").attr("value",selectAllFlag);
                $("#checkIDArr").attr("value",checkID);
                $("#searchForm").submit();
                $("#searchForm").attr("action",'${ctx}/businessoperation/wsxdCase/');
            });
        });

        // 分配后执行
        function searchAction() {
            $("#searchForm").attr("action",'${ctx}/businessoperation/wsxdCase/');
            $("#searchForm").submit();
        };

        // 分配到人
        $(function () {
            $('#btnDistribute').click(function () {
                var enableToSend = true;
                var param = {};
                // 催收组Id
                param.odvGroup = $("#odvGroupNameSelect").val();
                // 催收组名
                param.odvGroupName = $("#odvGroupNameSelect").find("option:selected").text();
                // 催收员编号
                param.odv = $("#odvNameSelect").find("option:selected").text().split("-")[0];
                // 催收员姓名
                param.odvName = $("#odvNameSelect").find("option:selected").text().split("-")[1];

                // 合作机构
                param.appOrg = $("#appOrgSelect").val();
				// 事业部
				param.departmentId = $("#departmentIdSelect").val();
				// 放款机构
                param.loanOrgin = $("#loanOrginSelect").val();
				// 借据编号
				param.loanBillNo = $("#loanBillNo").val();
                // 客户姓名
				param.customerName = $("#customerName").val();
				// 身份证号
                param.customerIdNo = $("#customerIdNo").val();
				// 电话号码
                param.telephoneNo = $("#telephoneNo").val();
				// 处理人员
                param.csy = $("#odvName").val();
				// 最小逾期
				param.minOverdueDays = $("#minOverdueDays").val();
				// 最大逾期
                param.maxOverdueDays = $("#maxOverdueDays").val();
				// 案件状态
				param.status = $("#statusSelect").val();
				// 客户经理
				param.managerId = $("#managerSelect").val();
				// 产品名称
				param.appName = $("#productSelect").val();

                // 选择处理人员
                var odvSelect= $("#odvNameSelect").val();
                if(odvSelect == "") {
                    Common.alert("请选择分配到的人！");
                    enableToSend = false;
                }

                // 选择案件
                var hasCheck=($("input[type='checkbox']").is(':checked'));
                if(!hasCheck){
                    Common.alert("请选择要分配的逾期案件！");
                    enableToSend = false;
                }

                var selectAllFlag=$("#checkAll").is(':checked');
                param.selectAllFlag = selectAllFlag;

                var checkID = [];//定义一个空数组
                if(!selectAllFlag){
                    $("input[type='checkbox']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                        checkID[i] =$(this).val();
                    });
                }

                if(!enableToSend) {
                    console.log("前端验证失败，不发送！")
                    return;
                }

                if(hasCheck){
                    param.checkID = checkID.toString();
                }

                loading("正在分配，请稍等...");
                $("#btnSubmit").attr("disabled", "disabled");
                $("#btnClear").attr("disabled", "disabled");
                $("#btnExport").attr("disabled", "disabled");
                $("#btnDistribute").attr("disabled", "disabled");

                $.ajax({
                    type: 'post',
                    url: "${ctx}/businessoperation/wsxdCase/distributeCSY.json",
                    data: param,
                    success: function (result) {
                        if (result.success) {
                            closeTip();
                            Common.alert("分配成功！", function () {
                                Common.clearAction();
                                searchAction();
                            });
                        } else {
                            closeTip();
                            Common.alert(result.message, function () {
                                searchAction();
                            });
                        }
                        // $("#countAndMoney").html('');
                    },
                    dataType: "json"
                });
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
					<%--<button type="button" class="btn btn-primary">Save changes</button>--%>
				</div>
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a id="operatorList">业务操作列表</a></li>
		<shiro:hasPermission name="businessoperation:wsxdCase:edit"><li><a href="${ctx}/businessoperation/wsxdCase/form">业务操作添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wsxdCase" action="${ctx}/businessoperation/wsxdCase/" method="post" class="breadcrumb form-search">
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
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">事&nbsp;&nbsp;业&nbsp;&nbsp;部：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:select path="departmentId" class="input-medium" id="departmentIdSelect">
					<form:option value="" label="请选择"/>
					<form:options items="${wsxdCase.departmentList}" itemValue="departmentId" itemLabel="departmentName" htmlEscape="false"/>
				</form:select>
			</li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">放款机构：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:select path="loanOrgin" class="input-medium" id="loanOrginSelect">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('loan_orgin')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">借据编号：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input id="loanBillNo" path="loanBillNo" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入借据编号"/>
			</li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li><label style="width: auto">客户姓名：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<form:input id="customerName" path="customerName" htmlEscape="false" maxlength="40" class="input-medium" placeholder="请输入客户姓名"/>
			</li>
		</ul>
        <ul class="ul-form">
            <li><label style="width: auto">身份证号：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:input id="customerIdNo" path="customerIdNo" htmlEscape="false" maxlength="50" style="width: 163px;" placeholder="请输入身份证号" size="4"/>
            </li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li><label style="width: auto">电话号码：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:input id="telephoneNo" path="telephoneNo" htmlEscape="false" maxlength="50" class="input-medium" placeholder="电话号码"/>
            </li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li><label style="width: auto">处理人员：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:input id="odvName" path="odvName" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入处理人员"/>
            </li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li><label style="width: auto">最小逾期：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:input id="minOverdueDays" path="minOverdueDays" htmlEscape="false" maxlength="11" class="input-medium" placeholder="请输入最小逾期"/>
            </li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li><label style="width: auto">最大逾期：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:input id="maxOverdueDays" path="maxOverdueDays" htmlEscape="false" maxlength="11" class="input-medium" placeholder="请输入最大逾期"/>
            </li>
            <li class="clearfix"></li>
        </ul>
        <ul class="ul-form">
            <li><label style="width: auto">案件状态：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:select path="status" class="input-medium" id="statusSelect">
                    <form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('remind_status')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
			</form:select>
            </li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li><label style="width: auto">客户经理：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:select path="managerId" class="input-medium" id="managerSelect">
                    <form:option value="" label="请选择"/>
                    <form:options items="${wsxdCase.managerList}" itemValue="managerId" itemLabel="managerName" htmlEscape="false"/>
                </form:select>
            </li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li><label style="width: auto">产品名称：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <form:select path="appName" class="input-medium" id="productSelect">
                    <form:option value="" label="请选择"/>
                    <form:options items="${wsxdCase.appNameList}"  htmlEscape="false"/>
                </form:select>
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
			<li><label style="width: auto">手动分案：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<%--<form:select path="odvGroup" class="input-medium" id="odvGroupnNameSelect">--%>
					<%--<form:option value="" label="请选择"/>--%>
					<%--<form:options items="${wsxdCase.odvGroupList}" itemLabel="odvGroupName" itemValue="odvGroup" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<select class="input-medium" id="odvGroupNameSelect">
					<option value="" >请选择</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%--<form:select path="csy" class="input-medium" id="odvNameSelect">--%>
					<%--<form:option value="" label="请选择"/>--%>
					<%--<form:options items="${wsxdCase.csyList}" itemLabel="odvName" itemValue="odv" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<select class="input-medium" id="odvNameSelect">
					<option value="" >请选择</option>
				</select>
				<input id="btnDistribute" class="btn btn-primary" type="button" value="分配到人"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li style="float: left"><label style="width: auto">导出操作：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input id="btnExport" class="btn btn-primary" type="button" value="催记导出"/>
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
				<th style="text-align: center">操作</th>
				<th style="text-align: center" class="sort-column a.app_org">合作机构</th>
				<th style="text-align: center" class="sort-column a.department_name">事业部</th>
				<th style="text-align: center" class="sort-column sd1.label">放款机构</th>
				<th style="text-align: center" class="sort-column a.app_name">产品名称</th>
				<th style="text-align: center" class="sort-column a.customer_id_no">客户姓名</th>
				<th style="text-align: center" class="sort-column a.loan_bill_no">借据编号</th>
				<th style="text-align: center" class="sort-column a.overdue_days">逾期天数</th>
				<th style="text-align: center" class="sort-column a.overdue_amt">逾期金额</th>
				<th style="text-align: center" class="sort-column a.loan_balance">贷款余额</th>
				<th style="text-align: center" class="sort-column c.odv_name">处理人员</th>
				<th style="text-align: center" class="sort-column c.odv_group_name">处理组名</th>
				<th style="text-align: center" class="sort-column b.create_date">最新更催时间</th>
				<th style="text-align: center" class="sort-column b.remind_status">案件状态</th>
				<shiro:hasPermission name="businessoperation:wsxdCase:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsxdCase" varStatus="wsxdCaseStatus">
			<tr>
				<td style="text-align: center">
					<input type="checkbox" id="box_${wsxdCaseStatus.index}" value="${wsxdCase.id}">
				</td>
				<td style="text-align: center">
					${wsxdCaseStatus.index + 1}
				</td>
				<td style="text-align: center">
					<a href="${ctx}/businessoperation/wsxdCaseInfo/list?loanBillNo=${wsxdCase.loanBillNo}">查看</a>
				</td>
				<td style="text-align: center">
					${wsxdCase.appOrg}
				</td>
				<td style="text-align: center">
					${wsxdCase.departmentName}
				</td>
				<td style="text-align: center">
					${wsxdCase.loanOrgin}
				</td>
				<td style="text-align: center">
					${wsxdCase.appName}
				</td>
				<td style="text-align: center">
					${wsxdCase.customerName}
				</td>
				<td style="text-align: center">
					${wsxdCase.loanBillNo}
				</td>
				<td style="text-align: center">
					${wsxdCase.overdueDays}
				</td>
				<td style="text-align: center">
					<c:if test="${not empty wsxdCase.overdueAmt}">
						￥
					</c:if>
					<fmt:formatNumber value="${wsxdCase.overdueAmt}" pattern="#,#00.00#" />
				</td>
				<td style="text-align: center">
					<c:if test="${not empty wsxdCase.loanBalance}">
						￥
					</c:if>
					<fmt:formatNumber value="${wsxdCase.loanBalance}" pattern="#,#00.00#" />
				</td>
				<td style="text-align: center">
					${wsxdCase.odvName}
				</td>
				<td style="text-align: center">
					${wsxdCase.odvGroupName}
				</td>
				<td style="text-align: center">
					<fmt:formatDate value="${wsxdCase.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align: center">
					${wsxdCase.status}
				</td>
				<shiro:hasPermission name="businessoperation:wsxdCase:edit"><td style="text-align: center">
    				<a href="${ctx}/businessoperation/wsxdCase/form?id=${wsxdCase.id}">修改</a>
					<a href="${ctx}/businessoperation/wsxdCase/delete?id=${wsxdCase.id}" onclick="return confirmx('确认要删除该业务操作吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" align="center">${page}</div>
</body>
</html>