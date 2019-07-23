<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信平台配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[id='btnSubmit']").hide();
			$("#cancel").hide();
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

		function edit() {
			$("input[id='msgCount']").attr("disabled", false);
			$("#edit").hide();
			$("#cancel").show();
			$("input[id='btnSubmit']").show();
			$(this).parent().parent().parent().find("input[id='btnSubmit']").show();
		}

		function cancle(msgCount) {
			$("input[id='msgCount']").attr("disabled", true);
			$("input[id='msgCount']").val(msgCount);
			$("#edit").show();
			$("#cancel").hide();
			$("input[id='btnSubmit']").hide();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/smsplatformconfig/wsxdSmsConfig/form?id=${wsxdSmsConfig.id}">短信平台配置</a></li>
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
				<form:input id="msgCount" path="msgCount" htmlEscape="false" maxlength="11" class="input-xlarge required digits" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<a id="edit" class="btn btn-primary" href="javascript:void(0)" onclick="edit()">编辑</a>
				<a id="cancel" class="btn btn-primary" href="javascript:void(0)" onclick="cancle(${wsxdSmsConfig.msgCount})">取消</a>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
		</div>
	</form:form>
</body>
</html>