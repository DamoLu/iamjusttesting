<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>查看详情表</title>
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
        .width-6-1 {
            height: 20px;
        }
        .t-width {
            max-width: 40%;
        }
        .table th, .table td {
            line-height: 20px;
            text-align: left;
            vertical-align: top;
            padding: 5px;
            border-top: 1px solid rgb(221, 221, 221);
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/">分案处理组详情</a></li>
    <li class="active"><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/form?id=${wsxdAllocateGroup.id}">分案处理组<shiro:hasPermission name="sysmgt:allocatecase:wsxdAllocateGroup:edit">${not empty wsxdAllocateGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sysmgt:allocatecase:wsxdAllocateGroup:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<table class="table table-bordered t-width">
    <tbody>
    <tr>
        <td class="width-6-1"><b>处理组名：</b></td>
        <td class="width-6-3">
            ${wsxdAllocateGroup.groupName}
        </td>
    </tr>
    <tr>
        <td class="width-6-1"><b>处理人员：</b></td>
        <td class="width-6-3">
            ${wsxdAllocateGroup.odvsName}
        </td>
    </tr>
    <tr>
        <td class="width-6-1"><b>逾期范围：</b></td>
        <td class="width-6-3">
            ${wsxdAllocateGroup.minOverdueDay}-${wsxdAllocateGroup.maxOverdueDay} 天
        </td>
    </tr>
    <tr>
        <td class="width-6-1"><b>当前状态：</b></td>
        <c:if test="${wsxdAllocateGroup.status == '1'}">
            <td class="width-6-3">
                启用中
            </td>
        </c:if>
        <c:if test="${wsxdAllocateGroup.status == '0'}">
            <td class="width-6-3">
                禁用中
            </td>
        </c:if>
    </tr>
    <tr>
        <td class="width-6-1"><b>案件范围：</b></td>
        <td class="width-6-3">
            <c:forEach items="${wsxdAllocateGroup.wsxdAllocateGroupScopeList}" var="wsxdAllocateGroupScope">
                ${wsxdAllocateGroupScope.appOrgName}--${wsxdAllocateGroupScope.departmentName}--${wsxdAllocateGroupScope.hasCommonPool eq '0'? '仅含公共池':wsxdAllocateGroupScope.hasCommonPool eq '1'? '不含公共池':'全部'}<br>
            </c:forEach>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>