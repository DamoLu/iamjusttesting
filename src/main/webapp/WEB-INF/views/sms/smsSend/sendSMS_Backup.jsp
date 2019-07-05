<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务操作管理</title>
	<meta name="decorator" content="default"/>
	<link href="/static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<link href="/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
	<link href="/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<link href="/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<link href="/static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<link href="/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="/static/jqGrid/4.7/css/ui.jqgrid.css">
	<style>
		.custom .control-label{
			width: 80px;
		}
		.custom .controls {
			margin-left: 90px;
		}
		.custom .controls input{
			width: 100%;
			display: block;
			box-sizing: border-box;
			height: 30px;
		}
		.custom .controls textarea{
			width: 100%;
		}
		.custom .controls .hide{
			display: none;
		}
		.custom .controls .btn-link{
			text-decoration: none;
		}
		.selected-phone {
			height: 30px;
			line-height: 30px;
		}
		.select2-container {
			min-width: 200px;
		}
		.selected-phone input{
			width: inherit!important;
		}
		.red{
			color: red;
		}
		#pagination{
			height: 50px;
		}
		#pg_pagination{
			height: 50px;
		}
		#pg_pagination .ui-pg-input{
			width: 30px;
			height: 30px;
		}
		#pg_pagination .ui-pg-selbox {
			width: auto;
			height: auto;
		}
	</style>
</head>
<body>
	<div>
		<button id="btnGoBack" class="btn">返回</button>
	</div>
	<form id="addForm" modelAttribute="wsxdCase" class="form-horizontal custom" style="margin-top: 24px;">
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="mobiles">发送对象：</label>
					<div class="controls">
						<input type="text" id="mobiles" name="mobiles" style="width: auto">
						<span class="help-inline"></span>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="templateList">短信模板：</label>
					<div class="controls">
						<input type="text" id="templateList" name="templateList" style="width: auto">
						<span class="help-inline"></span>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="templateContent"><i class="red">*</i>短信内容：</label>
					<div class="controls">
						<textarea rows="4" id="templateContent" name="templateContent" readonly></textarea>
						<input type="hidden" id="templateId" name="templateId">
						<span class="help-inline"></span>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="businessPhone"><i class="red">*</i>联系电话：</label>
				<div class="controls">
					<div class="selected-phone">
						<span id="selected-phone"></span>
						<input type="text" id="businessPhone" name="businessPhone" class="hide ">
						<button id="btnChangePhoneNumber" class="btn btn-link">更换</button>
						<button id="btnConfirmChangePhoneNumber" class="btn btn-link hide">确定</button>
						<button id="btnCancelChangePhoneNumber"  class="btn btn-link hide">取消</button>
					</div>
					<span class="help-inline"></span>
					<p class="red">*每个手机号用户支持发送5条信息！</p>
				</div>
			</div>
		</div>
		<div class="form-actions-box">
			<button id="btnSend" class="btn btn-primary">发送短信</button>
		</div>
	</form>
	<form id="searchForm" modelAttribute="wsxdCase" class="form-horizontal custom" style="margin-top: 24px;">
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="lastname">发送状态:</label>
					<div class="controls">
						<input type="text" id="status" name="status">
						<span class="help-inline"></span>
					</div>
				</div>
			</div>
		</div>
	</form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	</table>
	<div id="pagination" class="pagination"></div>
	<script src="/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<script src="/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>	
	<script src="/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="/static/common/mustache.min.js" type="text/javascript"></script>	
	<script src="/static/jqGrid/4.7/js/jquery.jqGrid.min.js"></script>
	<script src="/static/common/jeesite.js" type="text/javascript"></script>
	<script src="/static/jqGrid/4.7/js/jquery.jqGrid.extend.js"></script>
<script type="text/javascript">
// var testRoot = 'https://www.easy-mock.com/mock/5d0b5c4bfe374154e6ed7663/wsxd'
var testRoot = ''
var customerPhone = "18922315552"
var API = {
	getSmsTplList: '/sms/getTemplates', // 获取信息模板
	getPhoneList: '/sms/getContacts', // 获取联系人
	addNewSms: '/sms/sendSmsByTemplate', // 发送短信接口
	getSentSmsList: '/sms/getSmsContentPage' // 已发短信列表
}

var Events = {
	initEvents: function () {
		$('#btnChangePhoneNumber').on('click', function (e) {
			e.preventDefault()
			$('#businessPhone').show().focus()
			$('#selected-phone').hide();
			$(this).hide()
			$('#btnConfirmChangePhoneNumber').show()
			$('#btnCancelChangePhoneNumber').show()
			$('#businessPhone').val($('#selected-phone').text())
		})
		$('#btnConfirmChangePhoneNumber').on('click', function (e) {
			e.preventDefault()
			$('#selected-phone').show()
			$('#businessPhone').hide();
			$(this).hide()
			$('#btnCancelChangePhoneNumber').hide();
			$('#btnChangePhoneNumber').show();
			var prePhone = $('#selected-phone').text()
			var curPhone = $('#businessPhone').val()
			$('#templateContent').val($('#templateContent').val().replace(new RegExp(prePhone), curPhone));
			$('#selected-phone').text(curPhone)

		})
		$('#btnCancelChangePhoneNumber').on('click', function (e) {
			e.preventDefault()
			$('#selected-phone').show()
			$('#businessPhone').hide();
			$(this).hide()
			$('#btnConfirmChangePhoneNumber').hide()
			$('#btnChangePhoneNumber').show();
			$('#businessPhone').val($('#selected-phone').text())
		})
		$('#templateList').on('change', function () {
			var templates = $(this).data('templates');
			var tplId = $(this).val();
			$('#templateContent').val(templates[tplId]);
			$('#templateId').val(tplId);
			var exactedPhone = '';
			if (templates[tplId] !== undefined) {
				exactedPhone = Array.isArray(templates[tplId].match(/\d{11}/)) ? templates[tplId].match(/\d{11}/)[0] : ''
			}
			$('#selected-phone').text(exactedPhone)
		})
		$('#status').on('change', function () {
			Page.grid && Page.grid.trigger('reloadGrid')
		})
		$('#btnGoBack').on('click', function () {
			// TODO
		})
		$('#btnSend').on('click', function (e) {
			e.preventDefault()
			if ($("#addForm").valid() !== true) {
				return
			}
			var ret = $("#searchForm").serialize();
			var params = {
				businessPhone: $('#selected-phone').text(),
				customerPhone: customerPhone,
				mobiles: $('#mobiles').val().split(','),
				templateId: $('#templateList').val()
			}
			loading("正在发送，请稍等...");
			$.ajax({
				type: "post",
				url: API.addNewSms,
				data: params,
				// contentType: "application/json; charset=utf-8",
				dataType: "json",
			}).done(function(result){
				closeLoading()
				Page.grid && Page.grid.trigger('reloadGrid')
			}).fail(function (err) {
				closeLoading()
				$.jBox.tip(err.statusText, 'error');
				console.log('err', err)
			})
		})
	}
}
var Page = {
	initApi: function (apiMap, root) {
		Object.keys(apiMap).forEach(function (key) {
			apiMap[key] = root + apiMap[key]
		})
	},
	updateTabe: function () {
		Page.grid =	$("#contentTable").jqGrid({
			url : API.getSentSmsList, //组件创建完成之后请求数据的url
			datatype : "json",//请求数据返回的类型。可选json,xml,txt
			autoGridWidth: true,
			colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				{ label: '序号', name: 'id', width: 75, sortable : false },
				{ label: '短信模板', name: 'templateName', width: 150 , sortable : false},
				{ label: '发送时间', name: 'sendTime', width: 150 , sortable : false},
				{ label: '发送状态', name: 'status', width: 150 , sortable : false},
				{ label:'发送对象', name: 'senderName', width: 150 , sortable : false},
				{ label:'处理人员', name: 'createBy' , sortable : false},
				{ label:'内容', name: 'content', sortable : false}
			],
			jsonReader: {
				root: 'smsContentList',
				total: 'resultCount',
				page: 'pageNo'
			},
			beforeRequest: function (data) {
				var params = $('#searchForm').serializeArray();
				var gridParam = $(this).jqGrid('getGridParam')
				params.push({ name: 'pageNo', value: gridParam.page })
				params.push({ name: 'pageSize', value: gridParam.rowNum })
				// params.push({ name: 'sortname', value: gridParam.sortname })
				// params.push({ name: 'sortorder', value: gridParam.sortorder })
				$(this).jqGrid('setGridParam', {
					postData: params
				})
				// dataGrid.jqGrid('setGridParam', params);
				console.log('beforeRequest', params)
			},
			gridComplete : function() {
				$(this).jqGrid('setGridWidth', $(document.body).width())
			},
			rowNum : 10,//一页显示多少条
			rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
			pager : '#pagination',//表格页脚的占位符(一般是div)的id
			// sortname : 'id',//初始化的时候排序的字段
			// sortorder : "desc",//排序方式,可选desc,asc
			mtype : "post",//向后台请求数据的ajax的类型。可选post,get
			viewrecords : true,
			// caption : "JSON Example"//表格的标题名字
		});
	},
	initValidator: function () {
		Page.validatedFrom = $("#addForm").validate({
			errorPlacement: function(error, element) {
				$(element)
					.closest(".controls")
					.find(".help-inline")
					.append(error);
			},
			rules: {
				businessPhone: "required",
				templateContent: "required"
			},
			messages: {
				businessPhone: "电话不可为空",
				templateContent: "短信内容不可为空"
			}	
		})
	},
	initSelect: function () {
		$.ajax({
			type: "POST",
			url: API.getPhoneList,
			data: {
				"customerPhone": customerPhone
			},	
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(result){
			if (result.success === true && Array.isArray(result.contacts)) {
				$('#mobiles').select2("destroy");
				$('#mobiles').select2({
					data : result.contacts.map(function (contact) {
						return {
							id: contact.phone,
							text: contact.phone + ' ' + contact.relation + ' ' + contact.name
						}
					}),
					multiple: true,
					placeholder: '请选择发送对象',
				});
			}
			console.log('result', result)
		}).fail(function (err) {	
			console.log('getPhoneList', err)
		})
		$.ajax({
			type: "POST",
			url: API.getSmsTplList,	
			dataType: "json",
		}).done(function(result){
			if (result.success === true && Array.isArray(result.templates)) {
				var templateMap = {}
				result.templates.forEach(function (tpl) {
					templateMap[tpl.id] = tpl.content
				})
				$('#templateList').data('templates', templateMap)
				$('#templateList').select2("destroy");
				$('#templateList').select2({
					data : result.templates.map(function (tpl) {
						return {
							id: tpl.id,
							text: tpl.name
						}
					}),
					placeholder: '请选择短信模板',
					allowClear: true
				});
			}
			console.log('result', result)
		}).fail(function (err) {	
			console.log('getSmsTplList', err)
		})
		$('#status').select2("destroy");
		$('#status').select2({
			data : [
				{ id: '0', text: '发送成功' },
				{ id: '-1', text: '发送失败' },
				{ id: '-2', text: '退订' },
				{ id: '-3', text: '条数限制' }
			],
			allowClear: true,
			placeholder: '请选择发送状态',
		});
	},
	init:function () {
		Page.initApi(API, testRoot)
		Events.initEvents();
		Page.initValidator()
		Page.initSelect();
		Page.updateTabe()
	}
}
Page.init();
</script>
</body>
</html>