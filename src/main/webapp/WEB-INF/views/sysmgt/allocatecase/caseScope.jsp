<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<head>
    <title>案件范围</title>
</head>

<body>
<p>
    <c:forEach items="${wsxdAllocateGroup.wsxdAllocateGroupScopeList}" var="wsxdAllocateGroupScope">
        <br>&nbsp&nbsp&nbsp${wsxdAllocateGroupScope.appOrgName}--${wsxdAllocateGroupScope.departmentName}--${wsxdAllocateGroupScope.hasCommonPool eq '0'? '仅含公共池':wsxdAllocateGroupScope.hasCommonPool eq '1'? '不含公共池':'全部'}
    </c:forEach>
</p>
</body>

