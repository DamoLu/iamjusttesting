<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <link href="${ctxStatic}/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctxStatic}/bootstrap-select/css/bootstrap-select.css" rel="stylesheet">
        <link href="${ctxStatic}/bootstrap/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
        <link href="${ctxStatic}/font-awesome/font-awesome.min.css" rel="stylesheet">
        <link href="${ctxStatic}/sweetalert/css/sweetalert.css" rel="stylesheet">
        <link href="${ctxStatic}/chosen/chosen.css" rel="stylesheet">
        <link href="${ctxStatic}/validform/css/style.css" rel="stylesheet">
        <link href="${ctxStatic}/wsxd/sysmgt/allocatecase/css/editAllocateGroup.css" rel="stylesheet">
    </head>
    <body>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/">分案处理组列表</a></li>
        <li class="active"><a href="${ctx}/sysmgt/allocatecase/wsxdAllocateGroup/edit?groupId=${allocateGroup.id}">分案处理组<c:out value="${not empty allocateGroup.id ? '修改' : '添加' }" /></a></li>
    </ul><br/>
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins m0 mt10">
                        <div class="ibox-content">
                            <form method="post" class="form-horizontal registerform" id="mainForm">
                                <input type="hidden" id="allocateGroupId" name="id" value='${allocateGroup.id}'/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="need">*</span>处理组名</label>

                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="groupName" name="groupName" maxlength="100"
                                               datatype="groupName" value='${allocateGroup.groupName}' errormsg="处理组名不能为空"/>
                                        <div class="Validform_checktip">请输入处理组名</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="need">*</span>处理人员</label>

                                    <div class="col-sm-8">
                                        <input id="odvsName" name="odvsName" type="text" class="form-control bg-color-white" maxlength="100"
                                               datatype="odvsName" readonly="true" value="${allocateGroup.odvsName}"/>
                                        <input type="hidden" id="odvs" name="odvs" value="${allocateGroup.odvs}">
                                        <div class="Validform_checktip">请至少选择1个处理人员</div>
                                    </div>
                                    <button type="button" id="addOdvsNameBtn" class="btn btn-warning fn-add">添加</button>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="need">*</span>逾期范围</label>

                                    <div class="col-sm-8">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-5">
                                                <div class="input-group">
                                                    <input type="text" id="minOverdueDay" class="form-control col-sm-4" value="${allocateGroup.minOverdueDay}"
                                                           name="minOverdueDay" maxlength="100" datatype="minOverdueDay"
                                                           errormsg="逾期天数必须为正整数" />
                                                    <span class="input-group-addon">天</span>
                                                </div>
                                                <div class="Validform_checktip">请输入逾期范围!
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-2 text-center">
                                                <label class="control-label sep-label">至</label></div>
                                            <div class="col-xs-12 col-sm-5">
                                                <div class="input-group">
                                                    <input type="text" id="maxOverdueDay" class="form-control col-sm-4" value="${allocateGroup.maxOverdueDay}"
                                                           name="maxOverdueDay" maxlength="100" datatype="maxOverdueDay"
                                                           errormsg="逾期天数必须为正整数" />
                                                    <span class="input-group-addon">天</span>
                                                </div>
                                                <div class="Validform_checktip">请输入逾期范围
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="need">*</span>案件范围</label>
                                    <div id="sp-div" class="col-sm-8">
                                        <input class="form-control bg-color-white" type="text" id="appOrgList" readonly
                                               placeholder="请选择" datatype="appOrgList">
                                        <input id="WsxdAllocateGroupScopeList" type="hidden" value='${WsxdAllocateGroupScopeList}' />
                                        <div class="Validform_checktip">请选择案件范围！</div>
                                    </div>
                                    <button id="addCaseScopeBtn" type="button" class="btn btn-warning">添加</button>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="need">*</span>当前状态</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" errormsg="请选择当前状态" datatype="status" name="status" id="status">
                                            <option value="-1" selected>请选择</option>
                                            <option value="1" <c:if test="${not empty allocateGroup.status and allocateGroup.status eq '1'}">selected</c:if>>启动</option>
                                            <option value="0" <c:if test="${not empty allocateGroup.status and allocateGroup.status eq '0'}">selected</c:if>>禁止</option>
                                        </select>
                                        <div class="Validform_checktip">请选择当前状态</div>
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                    <div class="col-sm-12 text-center">
                                        <button type="button" class="btn btn-add btn-success btn-lg fn-Save"
                                                style="margin-bottom: 15px;"
                                                title="保存">
                                            <i class="fa fn-save" aria-hidden="true"> 保存</i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>

    <script>
        var ctx = '${ctx}';
        var ctxStatic = '${ctxStatic}';
    </script>

    <script type='text/javascript' src="${ctxStatic}/jquery/jquery-2.1.4.min.js"></script>
    <script type='text/javascript' src="${ctxStatic}/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type='text/javascript' src="${ctxStatic}/sweetalert/js/sweetalert.min.js"></script>
    <script type='text/javascript' src="${ctxStatic}/layer/layer.min.js"></script>
    <script type='text/javascript' src="${ctxStatic}/common/js/common.js"></script>

    <script id="addCaseScopeTpl" type="text/template">
        <div class="wrapper wrapper-content animated fadeInRight add-case-scope-model" style="height: 100%;padding-top:15px;">
            <div class="full-height">
                <div class="col-sm-12 col-xs-12 full-height">
                    <div class="ibox float-e-margins m0 mt10 full-height">
                        <div class="ibox-content full-height">
                            <form method="post" class="form-horizontal registerform">
                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need">*</span>合作机构：</label>

                                    <div class="col-xs-10 col-sm-10">
                                        <select id="selCooperativeAgency" class="form-control role-select selectpicker" title="请选择合作机构"
                                                data-multiple-separator="|"
                                                multiple
                                        >
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need">*</span>事业部：</label>

                                    <div id="businessUnit" class="col-xs-10 col-sm-10">
                                        <select id="selBusinessUnit" class="form-control selectpicker" title="请选择事业部："
                                                data-multiple-separator="|"
                                                multiple
                                        >
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need">*</span>客户经理：</label>
                                    <div class="col-xs-10 col-sm-10">
                                        <select id="selCustomerManager" class="form-control selectpicker" title="请选择客户经理" >

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need">*</span>案件范围：                                    </label>

                                    <div class="col-xs-10 col-sm-10 chosen-container chosen-container-multi">
                                        <ul id="boxSelectedCaseScope" class="chosen-choices form-control">

                                        </ul>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need"></span></label>
                                    <div class="col-xs-10 col-sm-10">
                                        <div id="filterdCaseScope" class="filtered-case-scope form-control">

                                            <!-- panel-end -->
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12 text-center">
                                        <button id="btnSaveCaseScope" type="button" class="btn btn-add btn-success btn-lg" title="保存">
                                            <i class="fa" aria-hidden="true"> 保存</i>
                                        </button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </script>

    <script id="addOdvsNameTpl" type="text/template">
        <div class="wrapper wrapper-content animated fadeInRight" style="height: 95%;">
            <div class="row full-height" style="margin:0; padding-top: 50px">
                <div class="col-sm-12 col-xs-12 full-height" >
                    <div class="ibox float-e-margins m0 mt10 full-height">
                        <div class="ibox-content full-height">
                            <form method="post" class="form-horizontal registerform">

                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need">*</span>角色名称</label>

                                    <div class="col-xs-10 col-sm-10">
                                        <select id="role-select" class="form-control role-select selectpicker"
                                                data-live-search="true"
                                        >
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 col-sm-2 control-label"><span class="need">*</span>搜索用户</label>

                                    <div class="col-xs-10 col-sm-10">
                                        <select id="odvs-select" class="form-control selectpicker" title="请选择用户"
                                                data-multiple-separator="|"
                                                multiple
                                                data-live-search="true"
                                        >
                                        </select>
                                    </div>
                                </div>
                                <br>
                                <br>
                                <br>
                                <br>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                    <div class="col-sm-12 text-center">
                                        <button id="saveOdvsBtn" type="button" class="btn btn-add btn-success btn-lg fn-Save"
                                                style="margin-bottom: 15px;"
                                                title="保存">
                                            <i class="fa" aria-hidden="true"> 保存</i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </script>
    <script type='text/javascript' src="${ctxStatic}/validform/js/Validform_v5.3.2_min.js"></script>
    <script type='text/javascript' src="${ctxStatic}/bootstrap-select/js/bootstrap-select.js"></script>
    <script type='text/javascript' src="${ctxStatic}/wsxd/sysmgt/allocatecase/js/editAllocateGroup.js"></script>
</html>