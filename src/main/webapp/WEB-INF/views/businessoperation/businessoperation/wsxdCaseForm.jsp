<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务操作管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					departmentId:{
		                required:true,
		                maxlength:15
		            },
		            departmentName:{
		                required:true,
		                maxlength:15
		            },
		            managerId:{
		                required:true,
		                maxlength:15
		            },
		            managerName:{
		                required:true,
		                maxlength:15
		            }
			    },
			    messages:{
			    	departmentId:{
			          required: "请输入事业部编号",
			          maxlength: "长度不能大于15个字母"
			        },
			        departmentName:{
			        	required: "请输入事业部名称",
				          maxlength: "长度不能大于15个字母"
		            },
		            managerId:{
		            	required: "请输入客户经理编号",
				          maxlength: "长度不能大于15个字母"
		            },
		            managerName:{
		            	required: "请输入客户经理姓名",
				          maxlength: "长度不能大于15个字母"
		            }
			    	
				},
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
	<style type="text/css">
		.modal{
			left: 40%;     /* 距离左边的距离 */
			width: 900px;  /* 模态框的长度 */
		}
		.modal.fade.in{
			top: 30%;      /* 具体上面的距离 */
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/businessoperation/wsxdCase/">业务操作列表</a></li>
		<li class="active"><a href="${ctx}/businessoperation/wsxdCase/form?id=${wsxdCase.id}">业务操作<shiro:hasPermission name="businessoperation:wsxdCase:edit">${not empty wsxdCase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="businessoperation:wsxdCase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<table class="table table-bordered" style="width: 500px;margin: auto;"  >
		   <tbody>
		   		<tr>
					<td bgcolor="#EEEEEE" class="width-12-1"><label class="pull-right">用户ID：</label></td>
					<td class="width-12-3">
						123
					</td>
					<td bgcolor="#EEEEEE" class="width-12-1"><label class="pull-right">用户ID：</label></td>
					<td class="width-12-3">
						123
					</td>
					<td class="width-15 active"><label class="pull-right">用户ID：</label></td>
					<td class="width-35">
						123
					</td>
				</tr>
				<tr>
					<td bgcolor="#EEEEEE" class="width-12-1"><label class="pull-right">用户ID：</label></td>
					<td class="width-12-3">
						123
					</td>
					<td bgcolor="#EEEEEE" class="width-12-1"><label class="pull-right">用户ID：</label></td>
					<td class="width-12-3">
						123
					</td>
					<td class="width-15 active"><label class="pull-right">用户ID：</label></td>
					<td class="width-35">
						123
					</td>
				</tr>
			</tbody>
		</table>
	
	<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">开始演示模态框</button>	
		
	
	<!-- 模态框（Modal） -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-lg" role="document">
	    	 <form:form id="inputForm" modelAttribute="wsxdCase" action="${ctx}/businessoperation/wsxdCase/save" method="post" class="form-horizontal">
		            	<form:hidden path="id"/>
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">新建电话</h4>
	            </div>
	            
	            <div class="modal-body modal-lg">
		           
		            	<div class="control-group">
							<table class="table table-bordered">
						        <tbody>
						        <tr>
							        <td bgcolor="#EEEEEE"><nobr>事业部编号：</nobr></td>
									<td>
										<form:input path="departmentId" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
							        <td bgcolor="#EEEEEE"><nobr>事业部:</nobr></td>
									<td>
										<form:select path="departmentName" class="input-xlarge required">
											<form:option value="" label=""/>
											<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
										</form:select>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
								</tr>
								<tr>
							        <td bgcolor="#EEEEEE"><nobr>客户经理编号：</nobr></td>
									<td>
										<form:input path="managerId" htmlEscape="false" maxlength="36" class="input-xlarge required"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
							        <td bgcolor="#EEEEEE"><nobr>客户经理编号：</nobr></td>
									<td>
										<form:input path="managerName" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
								</tr>
								</tbody>
							</table>
							
						</div>	
					
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
	            </div>
	            </form:form>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	    
</body>
</html>