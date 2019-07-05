<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信平台配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/smsplatformconfig/wsxdSmsConfig/">短信平台配置</a></li>
		<li class="active"><a href="${ctx}/smsplatformconfig/wsxdSmsConfig/form?id=${wsxdSmsConfig.id}">保存短信平台配置成功<shiro:hasPermission name="smsplatformconfig:wsxdSmsConfig:edit">${not empty wsxdSmsConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="smsplatformconfig:wsxdSmsConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wsxdSmsConfig" action="${ctx}/smsplatformconfig/wsxdSmsConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">是否开启退订回T</label>
			<div class="controls">
					<input type="radio" name="returnState" checked="checked" value="0">是
					<input type="radio" name="returnState" value="1">否
			</div>
			<p><font color="red">退订回T功能暂时不支持</font></p>
		</div>
		<div class="control-group">
			<label class="control-label">每天允许发送的数量：</label>
			<div class="controls">
				<form:input path="msgCount" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
		</div>
	</form:form>
</body>
</html>