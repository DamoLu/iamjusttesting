<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>业务操作管理</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/sweetalert/sweetalert.css" rel="stylesheet">
    <script src="${ctxStatic}/js/common.js"></script>
    <script src="${ctxStatic}/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript">

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
        $(function () {
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
            $("#operatorList").click(function () {
                $("#searchForm").toggle();
                $("#searchForm1").toggle();
            });
        });

        // 分页
        function page(n, s) {
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
            if ($("#jbox-content").hasClass("jbox-content")) {
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

        $(function () {
            $('#btnExport').click(function () {
                var hasCheck=($("input[type='checkbox']").is(':checked'));
                if(!hasCheck){
                    // alert("请选择导出项");
                    Common.alert("请选择需要导出的案件数据！");
                    return;
                }

                var selectAllFlag=$("#checkAll").is(':checked');
                var checkID = [];//定义一个空数组

                if(!selectAllFlag){
                    $("input[type='checkbox']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                        checkID[i] =$(this).val();
                    });
                    console.log(checkID);
                }

                $("#searchForm").attr("action",'${ctx}/businessoperation/realtimequery/exportFile');
                $("#isSelectAll").attr("value",selectAllFlag);
                $("#checkIDArr").attr("value",checkID);
                $("#searchForm").submit();
                $("#searchForm").attr("action",'${ctx}/businessoperation/realtimequery/');

            });
        });


    </script>

    <style type="text/css">
        .table>tbody>tr:hover {
            background-color: #fff69a!important;
        }
    </style>
</head>
<body>


    <p style="font-size: 20px;">海尔90天以上数据不支持实时查询，宽限期内逾期金额的数据不准确,请悉知！</p>


<!-- Modal 模态框-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">请至少选择一条记录</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                请至少勾选一条记录！！！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
            </div>
        </div>
    </div>
</div>

<form:form id="searchForm" modelAttribute="wsxdCase" action="${ctx}/businessoperation/realtimequery/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="isSelectAll" name="isAll" type="hidden"/>
    <input id="checkIDArr" name="checkIDArr" type="hidden"/>

    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
        <li><label style="width: auto">合作机构：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:select path="appOrg" class="input-medium" id="appOrgSelect">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('appOrg')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
        <li><label style="width: auto">事&nbsp;&nbsp;业&nbsp;&nbsp;部：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:select path="departmentId" class="input-medium" id="departmentIdSelect">
                <form:option value="" label="请选择"/>
                <form:options items="${wsxdCase.departmentList}" itemValue="departmentId" itemLabel="departmentName"
                              htmlEscape="false"/>
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
            <form:input path="loanBillNo" htmlEscape="false" maxlength="100" class="input-medium"
                        placeholder="请输入借据编号"/>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
        <li><label style="width: auto">客户姓名：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:input path="customerName" htmlEscape="false" maxlength="40" class="input-medium"
                        placeholder="请输入客户姓名"/>
        </li>
    </ul>
    <ul class="ul-form">
        <li><label style="width: auto">身份证号：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:input path="customerIdNo" htmlEscape="false" maxlength="50" style="width: 163px;"
                        placeholder="请输入身份证号" size="4"/>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
        <li><label style="width: auto">电话号码：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:input path="telephoneNo" htmlEscape="false" maxlength="50" class="input-medium" placeholder="电话号码"/>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
        <li><label style="width: auto">催收员：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:input path="odvName" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入处理人员"/>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
        <li><label style="width: auto">最小逾期：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:input path="minOverdueDays" htmlEscape="false" maxlength="11" class="input-medium" placeholder="请输入最小逾期"/>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
        <li><label style="width: auto">最大逾期：&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <form:input path="maxOverdueDays" htmlEscape="false" maxlength="11" class="input-medium" placeholder="请输入最大逾期"/>
        </li>
        <li class="clearfix"></li>
    </ul>

    <ul class="ul-form">
        <div style="float:left">
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="showLoading()"
                                    value="查询"/></li>
            <li class="btns"><input id="btnClear" class="btn btn-primary" type="button" value="清空"
                                    onclick="clearAction()"/></li>
        </div>
    </ul>
</form:form>

<form:form id="exportForm" class="breadcrumb form-search">
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
        <th style="text-align: center">合作机构</th>
        <th style="text-align: center">事业部</th>
        <th style="text-align: center">放款机构</th>
        <th style="text-align: center">客户姓名</th>
        <th style="text-align: center">借据编号</th>
        <th style="text-align: center" class="sort-column a.realtime_overdue_days">逾期天数</th>
        <th style="text-align: center" class="sort-column a.realtime_overdue_amt">逾期金额</th>
        <th style="text-align: center" class="sort-column a.loan_balance">贷款余额</th>
        <th style="text-align: center" class="sort-column b.create_date">最新更催时间</th>
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
                    ${wsxdCase.appOrg}
            </td>
            <td style="text-align: center">
                    ${wsxdCase.departmentName}
            </td>
            <td style="text-align: center">
                    ${wsxdCase.loanOrgin}
            </td>

            <td style="text-align: center">
                    ${wsxdCase.customerName}
            </td>
            <td style="text-align: center">
                    ${wsxdCase.loanBillNo}
            </td>
            <td style="text-align: center">
                    ${wsxdCase.realtimeOverdueDays}
            </td>
            <td style="text-align: center">
                <c:if test="${not empty wsxdCase.realtimeOverdueAmt}">
                    ￥
                </c:if>
                    ${wsxdCase.realtimeOverdueAmt}
            </td>
            <td style="text-align: center">
                <c:if test="${not empty wsxdCase.loanBalance}">
                    ￥
                </c:if>
                    ${wsxdCase.loanBalance}
            </td>
            <td style="text-align: center">
                <fmt:formatDate value="${wsxdCase.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination" align="center">${page}</div>
</body>
</html>