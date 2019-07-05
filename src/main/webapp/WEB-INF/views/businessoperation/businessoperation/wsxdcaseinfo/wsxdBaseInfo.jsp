<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>还款计划列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#btnMenu").remove();
        });
    </script>
    <style>
        .width-12-1 {
            width: 8.33%
        }
        .width-12-3 {
            width: 25%
        }
    </style>
</head>
<body>
<table class="table table-bordered">
    <tbody>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>客户姓名：</b></td>
        <td class="width-12-3">
            ${wsxdCase.customerName}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>身份证号：</b></td>
        <td class="width-12-3">
            ${wsxdCase.customerIdNo}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>性别：</b></td>
        <td class="width-12-3">
            ${wsxdCase.gender}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>单位名称：</b></td>
        <td class="width-12-3">
            ${wsxdCase.company}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>婚姻状况：</b></td>
        <td class="width-12-3">
            ${wsxdCase.maritalStatus}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>配偶姓名：</b></td>
        <td class="width-12-3">
            ${wsxdCase.mateName}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>邮箱：</b></td>
        <td class="width-12-3">
            ${wsxdCase.mail}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>事业部：</b></td>
        <td class="width-12-3">
            ${wsxdCase.departmentName}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>配偶身份证号：</b></td>
        <td class="width-12-3">
            ${wsxdCase.mateIdNo}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>处理人：</b></td>
        <td class="width-12-3">
            ${wsxdCase.odv}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>客户经理（现）：</b></td>
        <td class="width-12-3">
            ${wsxdCase.managerName}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>产品名称：</b></td>
        <td class="width-12-3">
            ${wsxdCase.appName}
        </td>
    </tr>

    <tr>
        <td colspan="6"><br/></td>
    </tr>

    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>今日逾期天数：</b></td>
        <td class="width-12-3">
            ${wsxdCase.realtimeOverdueDays}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期天数：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueDays}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期复利：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueCompound}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>今日逾期总额：</b></td>
        <td class="width-12-3">
            ${wsxdCase.realtimeOverdueAmt}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期总金额：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueAmt}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期违约金：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueLateFee}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>是否首次逾期：</b></td>
        <td class="width-12-3">
            ${wsxdCase.isFirstOverdue}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期本金：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overduePrin}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期手续费：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueFee}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>逾期起始日期：</b></td>
        <td class="width-12-3">
            <fmt:formatDate value="${wsxdCase.overdueStartDate}" pattern="yyyy-MM-dd"/>
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期利息：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueInt}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓贷款余额：</b></td>
        <td class="width-12-3">
            ${wsxdCase.loanBalance}
        </td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>历史逾期次数：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overdueCount}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓逾期罚息：</b></td>
        <td class="width-12-3">
            ${wsxdCase.overduePenalty}
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"></td>
        <td class="width-12-3"></td>
    </tr>
    <tr>
        <td bgcolor="#EEEEEE" class="width-12-1"></td>
        <td class="width-12-3"></td>
        <td bgcolor="#EEEEEE" class="width-12-1"><b>数仓数据更新日期：</b></td>
        <td class="width-12-3">
            <fmt:formatDate value="${wsxdCase.dataDate}" pattern="yyyy-MM-dd"/>
        </td>
        <td bgcolor="#EEEEEE" class="width-12-1"></td>
        <td class="width-12-3"></td>
    </tr>
    </tbody>
</table>

</body>
</html>