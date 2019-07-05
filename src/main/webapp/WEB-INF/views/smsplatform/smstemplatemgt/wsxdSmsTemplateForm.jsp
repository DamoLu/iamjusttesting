<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信模板管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/sweetalert/sweetalert.css" rel="stylesheet">
	<script src="${ctxStatic}/js/common.js"></script>
	<script src="${ctxStatic}/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    confirmx('为保证短信平台的正常使用,\n' +
                        '请确认短信模板已进行报备! ', function () {
                        form.submit();
                    });
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

		function submitSmsForm(){
			var minOverdueDay = parseInt($("#inputForm #minOverdueDay").val());
			var maxOverdueDay = parseInt($("#inputForm #maxOverdueDay").val());
			if (maxOverdueDay < minOverdueDay) {
				Common.alert("最小逾期不能大于最大逾期");
			}else {
                $("#inputForm").submit();
			}
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/smstemplatemgt/wsxdSmsTemplate/">短信模板列表</a></li>
	<li class="active"><a href="${ctx}/smstemplatemgt/wsxdSmsTemplate/form?id=${wsxdSmsTemplate.id}">短信模板${not empty wsxdSmsTemplate.id?'修改':'添加'}查看</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="wsxdSmsTemplate" action="${ctx}/smstemplatemgt/wsxdSmsTemplate/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">模版名称：</label>
		<div class="controls">
			<form:input path="name" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">发送类型：</label>
		<div class="controls">
			<form:select path="type" class="input-xlarge required">
				<form:option value="" label=""/>
				<option value="0" <c:if test="${wsxdSmsTemplate.type=='0'}">selected</c:if>>手工</option>
				<option value="1" <c:if test="${wsxdSmsTemplate.type=='1'}">selected</c:if>>批量</option>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">最小逾期天数：</label>
		<div class="controls">
			<form:input path="minOverdueDay" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">最大逾期天数：</label>
		<div class="controls">
			<form:input path="maxOverdueDay" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">模版内容：</label>
		<div class="controls">
			<form:textarea path="content" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
			<p><font color="red">模板中动态字段必须以《客户姓名》《称谓》《业务员号码》代替</font></p>
		</div>
	</div>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" onclick="submitSmsForm()" value="保 存"/>&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>