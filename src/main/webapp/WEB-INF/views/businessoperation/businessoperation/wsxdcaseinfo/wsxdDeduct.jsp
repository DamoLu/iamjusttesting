<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结清金额列表</title>
	<meta name="decorator" content="default"/>
	<%--<link href="${ctxStatic}/sweetalert/sweetalert.css" rel="stylesheet">--%>
	<%--<script src="${ctxStatic}/js/common.js"></script>--%>
	<%--<script src="${ctxStatic}/jquery-validation/1.11.0/grunt.js"></script>--%>
	<%--<script src="${ctxStatic}/sweetalert/sweetalert.min.js"></script>--%>
	<script type="text/javascript">
		$(document).ready(function() {
			var isSupport=${result.supportManualDeduction};
			console.log(isSupport);
			var msg="${result.respMsg}";
			console.log(msg);
			if(!isSupport){
                $("#spanContent").text("贷款详情查询失败,原因："+msg);
            }

			jQuery.validator.addMethod("checkAmount", function (value) {
						var pattern=/^[1-9]+(.[0-9]{1,3})?$/;
						return  pattern.test(value);
					}
			);

			$("#deductForm").validate({
				rules: {
					deductAmount: {
						required:true,
						maxlength: 7,
						checkAmount:true
					}
				},
				messages: {
					deductAmount: {
						required: "请输入金额",
						maxlength: "长度不能大于7个字符",
						checkAmount: "请输入合法的金额,1~3位小数!"
					}
				},
				submitHandler: function () {
					postDeduct();
				}
			});
		});
		function postDeduct() {
		    var deductAmount=$("#deductAmount").val();
		    var maxDeductionAmount=$("#maxDeductionAmount").val();
			// var pattern=/^[1-9]+(.[0-9]{1,3})?$/;
			// if(!pattern.test(deductAmount)){
			// 	alertx("请输入合法的金额,1~3位小数!");
			// 	return;
			// }
		    console.log(deductAmount);
		    console.log(maxDeductionAmount);
		    if(Number(deductAmount) > Number(maxDeductionAmount) ){
		    	alertx("扣款金额不能大于最大可扣金额！！！");
		    	return;
			}
			loading("处理中，请稍等...");
			$.ajax({
				type: "POST",
				url: "${ctx}/businessoperation/wsxdCaseInfo/lanuchDeduct",
				data: {
					"loanBillNo": "${loanBillNo}",
					"deductAmount":deductAmount,
					"maxDeductionAmount":maxDeductionAmount
				},
				contentType:"application/x-www-form-urlencoded",
				dataType: "json",
				success: function (result) {
					closeLoading();
					$("#spanContent").text(result.msg);
				}
			});
		};

		function clearInput() {
			$("#deductAmount").val("");
		}
	</script>
</head>
	<ul class="nav nav-tabs">
	</ul>

	<form:form id="deductForm" modelAttribute="result"  class="breadcrumb form-search">
		<span id="spanContent">
			<p>借据编号
				<input id="loanBillNo"   value="${loanBillNo}" style="float: right" readonly="readonly"/>
			</p>
			<p>最大可扣款金额
				<input id="maxDeductionAmount"   value="${result.deductionAmount}" style="float: right" readonly="readonly"/>
			</p>
			<p>扣款金额
				<input id="deductAmount" name="deductAmount" style="float: right" class="required"/>
			</p>
			<a class="btn btn-clean" onclick="clearInput()">取消</a>
			<%--<a class="btn btn-primary"  onclick="postDeduct()">保存</a>--%>
			<a class="btn btn-primary"  type="submit">保存</a>
		</span>

	</form:form>

</body>
</html>