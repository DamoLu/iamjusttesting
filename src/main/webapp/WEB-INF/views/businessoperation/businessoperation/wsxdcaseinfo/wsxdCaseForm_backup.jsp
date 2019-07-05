<%-- 冲突备份 2019-06-25 分页效果调整. 如确定无误后,请删除 --%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>业务操作管理</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/wsxd/wsxdcaseinfo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/businessoperation/wsxdCase/">业务操作列表</a></li>
    <li class="active"><a href="${ctx}/businessoperation/wsxdCase/form?id=${wsxdCase.id}">总进展</a></li>
</ul>
<br/>

<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%-- 0. 总进展 --%>
<ul class="ul-form">
    <button class="btn btn-primary" data-toggle="modal" data-target="#progressModal">添加催记</button>
</ul>
<table id="totalProgressTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>添加时间</th>
        <th>案件状态</th>
        <th>承诺还款时间</th>
        <th>承诺还款金额</th>
        <th>电话号码</th>
        <th>电话状态</th>
        <th>提醒日期</th>
        <th>措施</th>
        <th>录音id</th>
        <th>操作员</th>
        <th>来源</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${totalProgressPage.list}" var="wsxdRemindRecord">
        <tr>
            <td><fmt:formatDate value="${wsxdRemindRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${fns:getDictLabel(wsxdRemindRecord.status, 'case_status', '')}</td>
            <td><fmt:formatDate value="${wsxdRemindRecord.promiseDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${wsxdRemindRecord.promiseAmt}</td>
            <td>${wsxdRemindRecord.phone}</td>
            <td>${fns:getDictLabel(wsxdRemindRecord.phoneStatus, 'phone_status', '')}</td>
            <td><fmt:formatDate value="${wsxdRemindRecord.remindDate}" pattern="yyyy-MM-dd"/></td>
            <td>${wsxdRemindRecord.measure}</td>
            <td>${wsxdRemindRecord.recording}</td>
            <td>${wsxdRemindRecord.odv}</td>
            <td>${fns:getDictLabel(wsxdRemindRecord.source, 'case_source', '')}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination" id="objTotalProgressPage">${totalProgressPage}</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%-- 以下为多个标签页 基本信息、账户信息、电话、地址等 --%>
<ul class="nav nav-tabs" id="changeTab">
    <li class="active"><a class="a-no-decoration">基本信息</a></li>
    <li><a class="a-no-decoration">账户信息</a></li>
    <li><a class="a-no-decoration">电话</a></li>
    <li><a class="a-no-decoration">地址</a></li>
</ul>
<br/>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%-- 1. 基本信息标签页的内容 --%>
<div name="infoTab">
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td class="width-12-1"><b>客户姓名：</b></td>
            <td class="width-12-3">${wsxdCase.customerName}</td>
            <td class="width-12-1"><b>身份证号：</b></td>
            <td class="width-12-3">${wsxdCase.customerIdNo}</td>
            <td class="width-12-1"><b>性别：</b></td>
            <td class="width-12-3">${wsxdCase.gender}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>单位名称：</b></td>
            <td class="width-12-3">${wsxdCase.company}</td>
            <td class="width-12-1"><b>婚姻状况：</b></td>
            <td class="width-12-3">${wsxdCase.maritalStatus}</td>
            <td class="width-12-1"><b>配偶姓名：</b></td>
            <td class="width-12-3">${wsxdCase.mateName}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>邮箱：</b></td>
            <td class="width-12-3">${wsxdCase.mail}</td>
            <td class="width-12-1"><b>事业部：</b></td>
            <td class="width-12-3">${wsxdCase.departmentName}</td>
            <td class="width-12-1"><b>配偶身份证号：</b></td>
            <td class="width-12-3">${wsxdCase.mateIdNo}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>处理人：</b></td>
            <td class="width-12-3">${wsxdCase.odv}</td>
            <td class="width-12-1"><b>客户经理（现）：</b></td>
            <td class="width-12-3">${wsxdCase.managerName}</td>
            <td class="width-12-1"><b>产品名称：</b></td>
            <td class="width-12-3">${wsxdCase.appName}</td>
        </tr>

        <tr><td colspan="6"><br/></td></tr>

        <tr>
            <td class="width-12-1"><b>今日逾期天数：</b></td>
            <td class="width-12-3">${wsxdCase.realtimeOverdueDays}</td>
            <td class="width-12-1"><b>数仓逾期天数：</b></td>
            <td class="width-12-3">${wsxdCase.overdueDays}</td>
            <td class="width-12-1"><b>数仓逾期复利：</b></td>
            <td class="width-12-3">${wsxdCase.overdueCompound}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>今日逾期总额：</b></td>
            <td class="width-12-3">${wsxdCase.realtimeOverdueAmt}</td>
            <td class="width-12-1"><b>数仓逾期总金额：</b></td>
            <td class="width-12-3">${wsxdCase.overdueAmt}</td>
            <td class="width-12-1"><b>数仓逾期违约金：</b></td>
            <td class="width-12-3">${wsxdCase.overdueLateFee}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>是否首次逾期：</b></td>
            <td class="width-12-3">${wsxdCase.isFirstOverdue}</td>
            <td class="width-12-1"><b>数仓逾期本金：</b></td>
            <td class="width-12-3">${wsxdCase.overduePrin}</td>
            <td class="width-12-1"><b>数仓逾期手续费：</b></td>
            <td class="width-12-3">${wsxdCase.overdueFee}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>逾期起始日期：</b></td>
            <td class="width-12-3"><fmt:formatDate value="${wsxdCase.overdueStartDate}" pattern="yyyy-MM-dd"/></td>
            <td class="width-12-1"><b>数仓逾期利息：</b></td>
            <td class="width-12-3">${wsxdCase.overdueInt}</td>
            <td class="width-12-1"><b>数仓贷款余额：</b></td>
            <td class="width-12-3">${wsxdCase.loanBalance}</td>
        </tr>
        <tr>
            <td class="width-12-1"><b>历史逾期次数：</b></td>
            <td class="width-12-3">${wsxdCase.overdueCount}</td>
            <td class="width-12-1"><b>数仓逾期罚息：</b></td>
            <td class="width-12-3">${wsxdCase.overduePenalty}</td>
            <td class="width-12-1"></td>
            <td class="width-12-3"></td>
        </tr>
        <tr>
            <td class="width-12-1"></td>
            <td class="width-12-3"></td>
            <td class="width-12-1"><b>数仓数据更新日期：</b></td>
            <td class="width-12-3"><fmt:formatDate value="${wsxdCase.dataDate}" pattern="yyyy-MM-dd"/></td>
            <td class="width-12-1"></td>
            <td class="width-12-3"></td>
        </tr>
        </tbody>
    </table>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%-- 2. 账户信息标签页的内容 --%>
<div name="infoTab" class="hidden-tab">
    <table id="cardInfoTable" class="table table-striped table-bordered table-condensed table-td-center table-th-center">
        <thead>
        <tr>
            <th>序号</th>
            <th>合同号</th>
            <th>放款机构</th>
            <th>放款金额</th>
            <th>合同余额</th>
            <th>放款日期</th>
            <th>到期日期</th>
            <th>期数</th>
            <th>账单日</th>
            <th>入催时间</th>
            <th>还款卡号</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cardInfoPage.list}" var="varCase" varStatus="caseStatus">
        <tr>
            <td>${caseStatus.index + 1}</td>
            <td>${varCase.appNo}</td>
            <td>${varCase.appOrg}</td>
            <td>${varCase.loanPrin}</td>
            <td>${varCase.loanBalance}</td>
            <td><fmt:formatDate value="${varCase.loanDate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${varCase.dueDate}" pattern="yyyy-MM-dd"/></td>
            <td>${varCase.loanTerms}</td>
            <td>${varCase.billDate}</td>
            <td><fmt:formatDate value="${varCase.overdueStartDate}" pattern="yyyy-MM-dd"/></td>
            <td>${varCase.bankCardNo}</td>
            <td>
                <a onclick="findRepayPlan(${varCase.loanBillNo})">查看分期信息</a>
                <a onclick="pictureUpload(${varCase.loanBillNo})">上传影像信息</a>
                <a href="${ctx}/businessoperation/wsxdRepayPlan/list?loanBillNo=${wsxdCase.loanBillNo}">结清金额查询</a>
                <a href="${ctx}/businessoperation/wsxdRepayPlan/list?loanBillNo=${wsxdCase.loanBillNo}">还款记录查询</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination" id="objCardInfoPage">${cardInfoPage}</div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%-- 3 电话标签页的内容 --%>
<div name="infoTab" class="hidden-tab">
    <table id="phoneInfoTable" class="table table-striped table-bordered table-condensed table-td-center table-th-center">
        <thead>
        <tr>
            <th>序号</th>
            <th>关系</th>
            <th>姓名</th>
            <th>电话类型</th>
            <th>电话号码</th>
            <th>备注</th>
            <th>拨打时间</th>
            <th>处理人员</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${wsxdContactPhones}" var="varPhone" varStatus="phoneStatus">
            <tr>
                <td>${phoneStatus.index + 1}</td>
                <td>${varPhone.relation}</td>
                <td>${varPhone.name}</td>
                <td>${varPhone.type}</td>
                <td>${varPhone.phone}</td>
                <td>${varPhone.remark}</td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="btn-create">
        <button type="button" class="btn btn-primary btn-create" data-toggle="modal" data-target="#phoneModal">新建电话</button>
    </div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%-- 4. 地址标签页的内容 --%>
<div name="infoTab" class="hidden-tab">
    <table id="addressInfoTable" class="table table-striped table-bordered table-condensed table-td-center table-th-center">
        <thead>
        <tr>
            <th>序号</th>
            <th>关系</th>
            <th>姓名</th>
            <th>地址类型</th>
            <th>地址</th>
            <th>信函状态</th>
            <th>外访状态</th>
            <th>外访结果</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${wsxdContactAddrs}" var="varAddr" varStatus="addrStatus">
            <tr>
                <td>${addrStatus.index + 1}</td>
                <td>${varAddr.relation}</td>
                <td>${varAddr.name}</td>
                <td>${varAddr.type}</td>
                <td>${varAddr.detail}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="btn-create">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addressModal">新建地址</button>
    </div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<!-- 0. 添加催记模态框 -->
<div class="modal fade bs-example-modal-lg" id="progressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none">
    <div class="modal-dialog modal-lg" role="document">
        <form:form modelAttribute="wsxdRemindRecord" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title" id="myModalLabel">添加催记</h4></div>
                <div class="modal-body modal-lg">
                    <div class="control-group">
                        <table class="table table-bordered">
                            <tbody>
                            <input name="loanBillNo" value = "${loanBillNo}" type="hidden"/>
                            <input name="source" value = "1" type="hidden"/>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>案件状态：</nobr></td>
                                <td>
                                    <form:select path="status" class="input-xlarge required">
                                        <form:options items="${fns:getDictList('case_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-inline"><font color="red">*</font> </span>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>承诺还款时间：</nobr></td>
                                <td>
                                    <input name="promiseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                           value="<fmt:formatDate value="${wsxdRemindRecord.promiseDate}" pattern="yyyy-MM-dd"/>"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>承诺还款金额：</nobr></td>
                                <td>
                                    <form:input path="promiseAmt" htmlEscape="false" class="input-xlarge "/>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>电话号码：</nobr></td>
                                <td>
                                    <form:input path="phone" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
                                    <span class="help-inline"><font color="red">*</font> </span>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>电话状态：</nobr></td>
                                <td>
                                    <form:select path="phoneStatus" class="input-xlarge required">
                                        <form:options items="${fns:getDictList('phone_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-inline"><font color="red">*</font> </span>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>提醒日期：</nobr></td>
                                <td>
                                    <input name="remindDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                           value="<fmt:formatDate value="${wsxdRemindRecord.remindDate}" pattern="yyyy-MM-dd"/>"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>措施：</nobr></td>
                                <td>
                                    <form:textarea path="measure" htmlEscape="false" rows="4" class="input-xxlarge "/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" data-dismiss="modal" onclick="addProgress()"/>保存</div>
            </div>
        </form:form>
    </div>
</div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<!-- 2.1 查看分期信息模态框 -->
<div class="modal fade bs-example-modal-lg" id="repayplanModal" tabindex="-1" role="dialog" aria-labelledby="repayplanModalLabel" aria-hidden="true" style="display:none">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="repayplanModalLabel">还款计划</h4>
            </div>
            <div class="modal-body modal-lg">
                <table id="repayplanTable" class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>到期日</th>
                        <th>期数</th>
                        <th>本金</th>
                        <th>利息</th>
                        <th>手续费</th>
                        <th>服务费</th>
                        <th>违约金</th>
                        <th>罚息</th>
                        <th>复利</th>
                        <th>已还本金</th>
                        <th>已还利息</th>
                        <th>已还手续费</th>
                        <th>已还服务费</th>
                        <th>已还滞纳金</th>
                        <th>已还罚息</th>
                        <th>已还复利</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
            <div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">关闭</button></div>
        </div>
    </div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<!-- 3.1 电话模态框 -->
<div class="modal fade bs-example-modal-lg" id="phoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px" style="display:none">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="phoneModalLabel">新建电话</h4>
            </div>
            <div class="modal-body modal-lg">
                <form:form id="phoneForm" modelAttribute="wsxdContactPhone"
                           action="${ctx}/businessoperation/wsxdCase/save" method="post" class="form-horizontal">
                    <div class="control-group">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>姓名：</nobr></td>
                                <td><form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font></span></td>
                                <td bgcolor="#EEEEEE"><nobr>关系:</nobr></td>
                                <td>
                                    <form:select path="relation" class="input-xlarge required">
                                        <form:option value="" label=""/>
                                        <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>电话：</nobr></td>
                                <td>
                                    <form:input path="phone" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                                <td bgcolor="#EEEEEE"><nobr>电话类型:</nobr></td>
                                <td>
                                    <form:select path="type" class="input-xlarge required">
                                        <form:option value="" label=""/>
                                        <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value"
                                                      htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label">备注：</label>
                                    <form:textarea path="remark" htmlEscape="false" maxlength="20"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input id="phoneBtn" class="btn btn-primary" type="submit" value="保 存"/>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>
<!-- 4.1 地址模态框 -->
<div class="modal fade bs-example-modal-lg" id="addressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px" style="display:none">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addressModalLabel">新建地址</h4>
            </div>

            <div class="modal-body modal-lg">
                <form:form id="addressForm" modelAttribute="wsxdContactAddr"
                           action="${ctx}/businessoperation/wsxdCase/save" method="post" class="form-horizontal">
                    <div class="control-group">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>姓名：</nobr></td>
                                <td>
                                    <form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                                <td bgcolor="#EEEEEE"><nobr>关系:</nobr></td>
                                <td>
                                    <form:select path="relation" class="input-xlarge required">
                                        <form:option value="" label=""/>
                                        <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#EEEEEE"><nobr>地址：</nobr></td>
                                <td>
                                    <form:input path="detail" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                                <td bgcolor="#EEEEEE"><nobr>地址类型:</nobr></td>
                                <td>
                                    <form:select path="type" class="input-xlarge required">
                                        <form:option value="" label=""/>
                                        <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-inline"><font color="red">*</font></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label">备注：</label>
                                    <form:textarea path="remark" htmlEscape="false" maxlength="20"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%--------------------------------------------------------------------------------------------------------------------------%>
<%--------------------------------------------------------------------------------------------------------------------------%>

<input type="hidden" value="${picturesUploadURL}" id="picturesUploadURL"/>
<input id="pageNo" name="pageNo" type="hidden"/>
<input id="pageSize" name="pageSize" type="hidden"/>
<script type="text/javascript">
    var picturesUploadURL = $("#picturesUploadURL").val();
    var spage = '';
    // 联系电话字典map
    var phoneStatusMap = dictListToMap(${phoneStatusList});
    var caseStatusMap = dictListToMap(${caseStatusList});
    var caseSourceMap = dictListToMap(${caseSourceList});

    function dictListToMap(list) {
        var map = {};
        for(var index in list){
            map[list[index].value] = list[index].label;
        }
        return map;
    }

    // 上传影像
    function pictureUpload(loanBillNo) {
        window.open(picturesUploadURL + '/pic-app/picture/upload_uploadPictures?sysName=aps&apsName=1&apsPassword=1&appNo='+ loanBillNo +'&isAuth=Y&ifPatchBolt=N');
    }

    // 获得默认值，如果value为空，返回''
    function getDefaultValue(value) {
        return getDefaultValueByDefault(value, '');
    }

    // 获得默认值，如果value为空，返回defaultValue
    function getDefaultValueByDefault(value, defaultValue) {
        return value != null? value: defaultValue;
    }

    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        searchPage($(event.target).parents("div[class='pagination']").attr("id"))
        return false;
    }

    // ajax发送请求分页查询列表
    function searchPage(selectPage) {
        loading("正在查询，请稍等...");
        if(selectPage == 'objTotalProgressPage') {
            $.ajax({
                type: "POST",
                url: "${ctx}/businessoperation/wsxdCaseInfo/findProgressPage.json",
                data: JSON.stringify({
                    "pageNo": $("#pageNo").val(),
                    "pageSize": $("#pageSize").val(),
                    "loanBillNo": ${wsxdCase.loanBillNo}
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    closeLoading();
                    var page = $("#"+selectPage);
                    page.html(result.html);
                    var tbody = $("#totalProgressTable tbody");
                    tbody.empty();
                    $.each(result.list, function (idx, obj) {
                        var tr = '<tr>' +
                            '<td>'+getDefaultValue(obj.createTime)+'</td>' +
                            '<td>'+getDefaultValue(caseStatusMap[obj.status])+'</td>' +
                            '<td>'+getDefaultValue(obj.promiseDate)+'</td>' +
                            '<td>'+getDefaultValue(obj.promiseAmt)+'</td>' +
                            '<td>'+getDefaultValue(obj.phone)+'</td>' +
                            '<td>'+getDefaultValue(phoneStatusMap[obj.phoneStatus])+'</td>' +
                            '<td>'+getDefaultValue(obj.remindDate)+'</td>' +
                            '<td>'+getDefaultValue(obj.measure)+'</td>' +
                            '<td>'+getDefaultValue(obj.recording)+'</td>' +
                            '<td>'+getDefaultValue(obj.odv)+'</td>' +
                            '<td>'+getDefaultValue(caseSourceMap[obj.source])+'</td>' +
                            '</tr>';
                        tbody.append(tr);
                    });
                }
            });
        } else if(selectPage == 'objCardInfoPage') {
            $.ajax({
                type: "POST",
                url: "${ctx}/businessoperation/wsxdCaseInfo/findCardInfoPage.json",
                data: JSON.stringify({
                    "pageNo": $("#pageNo").val(),
                    "pageSize": $("#pageSize").val(),
                    "loanBillNo": ${wsxdCase.loanBillNo}
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    closeLoading();
                    var page = $("#"+selectPage);
                    page.html(result.html);
                    var tbody = $("#cardInfoTable tbody");
                    tbody.empty();
                    $.each(result.list, function (idx, obj) {
                        var tr = '<tr>' +
                            '<td>'+(idx+1)+'</td>' +
                            '<td>'+getDefaultValue(obj.appNo)+'</td>' +
                            '<td>'+getDefaultValue(obj.appOrg)+'</td>' +
                            '<td>'+getDefaultValue(obj.loanPrin)+'</td>' +
                            '<td>'+getDefaultValue(obj.loanBalance)+'</td>' +
                            '<td>'+getDefaultValue(obj.loanDate)+'</td>' +
                            '<td>'+getDefaultValue(obj.dueDate)+'</td>' +
                            '<td>'+getDefaultValue(obj.loanTerms)+'</td>' +
                            '<td>'+getDefaultValue(obj.billDate)+'</td>' +
                            '<td>'+getDefaultValue(obj.overdueStartDate)+'</td>' +
                            '<td>'+getDefaultValue(obj.bankCardNo)+'</td>' +
                            '<td>' +
                            '   <a onclick="findRepayPlan('+obj.loanBillNo+')">查看分期信息</a>' +
                            '   <a onclick="pictureUpload('+obj.loanBillNo+')">上传影像信息</a>' +
                            '   <a>结清金额查询</a>' +
                            '   <a>还款记录查询</a>' +
                            '</td>' +
                            '</tr>';
                        tbody.append(tr);
                    });
                }
            });
        }
    }

    // ajax发送请求保存催记
    function addProgress() {
        loading("正在查询，请稍等...");
        $.ajax({
            type: "POST",
            url: "${ctx}/businessoperation/wsxdCaseInfo/addProgress.json",
            data: JSON.stringify({
                "loanBillNo": ${wsxdCase.loanBillNo},
                "source": $("#progressModal input[name='source']").val(),
                "status": $("#progressModal select[name='status']").val(),
                "promiseDate": $("#progressModal input[name='promiseDate']").val(),
                "promiseAmt": $("#progressModal input[name='promiseAmt']").val(),
                "phone": $("#progressModal input[name='phone']").val(),
                "phoneStatus": $("#progressModal select[name='phoneStatus']").val(),
                "remindDate": $("#progressModal input[name='remindDate']").val(),
                "measure": $("#progressModal textarea[name='measure']").val()
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                closeLoading();

                if(result.code != 200) {
                    $("#progressModal").modal("show");
                    alertx(result.msg)
                } else {
                    searchPage("objTotalProgressPage");
                    $("#progressModal input[name='source']").val(null);
                    $("#progressModal select[name='status']").val(null);
                    $("#progressModal input[name='promiseDate']").val(null);
                    $("#progressModal input[name='promiseAmt']").val(null);
                    $("#progressModal input[name='phone']").val(null);
                    $("#progressModal select[name='phoneStatus']").val(null);
                    $("#progressModal input[name='remindDate']").val(null);
                    $("#progressModal textarea[name='measure']").val(null);
                    $("#progressModal select[name='status']").trigger("change");
                    $("#progressModal select[name='phoneStatus']").trigger("change");
                }
            }
        });
    }

    // ajax发送请求查看还款计划（分期信息）
    function findRepayPlan(loanBillNo) {
        loading("正在查询，请稍等...");

        var tbody = $("#repayplanTable tbody");
        tbody.empty();
        $.ajax({
            type: "POST",
            url: "${ctx}/businessoperation/wsxdCaseInfo/findRepayPlans.json",
            data: JSON.stringify({"loanBillNo": loanBillNo}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                closeLoading();
                $.each(result, function (idx, obj) {
                    var tr = '<tr>' +
                        '<td>'+getDefaultValue(obj.loanPmtDueDate)+'</td>' +
                        '<td>'+getDefaultValue(obj.currentTerm)+'</td>' +
                        '<td>'+getDefaultValue(obj.loanTermPrin)+'</td>' +
                        '<td>'+getDefaultValue(obj.loanTermInt)+'</td>' +
                        '<td>'+getDefaultValue(obj.loanTermFee)+'</td>' +
                        '<td>'+getDefaultValue(obj.accountFee)+'</td>' +
                        '<td>'+getDefaultValue(obj.overdueLateFee)+'</td>' +
                        '<td>'+getDefaultValue(obj.overduePenalty)+'</td>' +
                        '<td>'+getDefaultValue(obj.overdueCompound)+'</td>' +
                        '<td>'+getDefaultValue(obj.principal)+'</td>' +
                        '<td>'+getDefaultValue(obj.interest)+'</td>' +
                        '<td>'+getDefaultValue(obj.fee)+'</td>' +
                        '<td>'+getDefaultValue(obj.payAccountFee)+'</td>' +
                        '<td>'+getDefaultValue(obj.lateFee)+'</td>' +
                        '<td>'+getDefaultValue(obj.penalty)+'</td>' +
                        '<td>'+getDefaultValue(obj.compound)+'</td>' +
                        '</tr>';
                    tbody.append(tr);
                });
                $("#repayplanModal").modal("toggle");
            }
        });
    }

    // ajax发送请求更新联系人电话列表
    function findContactPhones(loanBillNo) {
        $.ajax({
            type: "POST",
            url: "${ctx}/businessoperation/wsxdCaseInfo/findContactPhones.json",
            data: JSON.stringify({"loanBillNo": loanBillNo}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                var tbody = $("#phoneInfoTable tbody");
                tbody.empty();
                $.each(result, function (idx, obj) {
                    var tr = '<tr>' +
                                '<td>'+(idx+1)+'</td>' +
                                '<td>'+getDefaultValue(obj.relation)+'</td>' +
                                '<td>'+getDefaultValue(obj.name)+'</td>' +
                                '<td>'+getDefaultValue(obj.type)+'</td>' +
                                '<td>'+getDefaultValue(obj.phone)+'</td>' +
                                '<td>'+getDefaultValue(obj.remark)+'</td>' +
                                '<td></td>' +
                                '<td></td>' +
                              '</tr>';
                    tbody.append(tr);
                });
            }
        });
    };

    // ajax发送请求更新联系人地址列表
    function findContactAddrs(loanBillNo) {
        $.ajax({
            type: "POST",
            url: "${ctx}/businessoperation/wsxdCaseInfo/findContactAddrs.json",
            data: JSON.stringify({"loanBillNo": loanBillNo}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                var tbody = $("#addressInfoTable tbody");
                tbody.empty();
                $.each(result, function (idx, obj) {
                    var tr = '<tr>' +
                        '<td>'+(idx+1)+'</td>' +
                        '<td>'+getDefaultValue(obj.relation)+'</td>' +
                        '<td>'+getDefaultValue(obj.name)+'</td>' +
                        '<td>'+getDefaultValue(obj.type)+'</td>' +
                        '<td>'+getDefaultValue(obj.detail)+'</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '</tr>';
                    tbody.append(tr);
                });
            }
        });
    };

    $(document).ready(function () {
        $("#btnMenu").remove();
        $('#changeTab li').click(function () {
            $("#changeTab li").each(function () {
                $(this).removeClass("active");
            });
            $(this).addClass("active");

            var index = $("#changeTab li").index(this);
            $("div[name='infoTab']").each(function () {
                $(this).addClass("hidden-tab");
            });
            $("div[name='infoTab']").eq(index).removeClass("hidden-tab");
        });


        $("#phoneForm").validate({
            rules: {
                name: {
                    required: true,
                    maxlength: 15
                },
                relation: {
                    required: true
                },
                phone: {
                    required: true,
                    maxlength: 11
                },
                type: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入姓名",
                    maxlength: "长度不能大于15个字母"
                },
                relation: {
                    required: "请选择关系"
                },
                phone: {
                    required: "请输入电话号码",
                    maxlength: "长度不能大于11"
                },
                type: {
                    required: "请选择类型"
                }
            },
            submitHandler: function (form) {
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function (error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });

        $("#addressForm").validate({
            rules: {
                name: {
                    required: true,
                    maxlength: 20
                },
                relation: {
                    required: true
                },
                detail: {
                    required: true,
                    maxlength: 50
                },
                type: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入姓名",
                    maxlength: "长度不能大于20"
                },
                relation: {
                    required: "请选择关系"
                },
                detail: {
                    required: "请输入地址",
                    maxlength: "长度不能大于50"
                },
                type: {
                    required: "请选择类型"
                }

            },
            submitHandler: function (form) {
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function (error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });

    });
</script>
</body>
</html>



