<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>发送短信</title>
  <link href="${ctxStatic}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${ctxStatic}/css/bootstrap-select.min.css" rel="stylesheet">
  <link href="${ctxStatic}/sweetalert/css/sweetalert.css" rel="stylesheet">

  <script type="text/javascript" src="${ctxStatic}/jquery/jquery3.1.1.js"></script>
  <script type="text/javascript" src="${ctxStatic}/js/popper.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/js/tether.js"></script>
  <script type="text/javascript" src="${ctxStatic}/js/bootstrap.js"></script>
  <script type="text/javascript" src="${ctxStatic}/js/bootstrap-select.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/js/defaults-zh_CN.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/js/bootstrap.min.js"></script>
  <script type='text/javascript' src="${ctxStatic}/sweetalert/js/sweetalert.min.js"></script>
  <script type='text/javascript' src="${ctxStatic}/common/js/common.js"></script>



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

  </style>
</head>
<body>
  <div class="container-fluid">
    <c:if test="${not empty errMsg}">
      <div class="row-fluid">
        <div class="col-md-12">
          <div class="alert alert-danger" role="alert">
            ${errMsg}
          </div>
        </div>
      </div>
    </c:if>
  	<div class="row-fluid">
      <div class="col-md-12">
        <a id="close" class="btn btn-outline-secondary">返回</a>
      </div>
    </div>
    <div class="row-fluid">
      <div class="col-md-12">
        <div class="form-inline">
          <div class="form-group">
            <h3><label class="badge badge-default" for="mobiles">发送对象：</label></h3>
            <select id="mobiles" name="mobiles" class="selectpicker" multiple data-style="btn-light">
              <c:forEach items="${mobiles}" var="mobile" >
                <option value="${mobile.phone}">${mobile.name}, ${mobile.relation}, ${mobile.phone}</option>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="col-md-12">
        <div class="form-inline">
          <div class="form-group">
            <h3><label class="badge badge-default" for="templateList">短信模板：</label></h3>
            <select id="templateList" name="template" class="selectpicker">
              <option value="" content="">请选择</option>
              <c:forEach items="${smsTemplateList}" var="smsTemplate" >
                <option value="${smsTemplate.id}" content="${smsTemplate.content}">${smsTemplate.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="col-md-12">
        <div class="form-inline">
          <div class="form-group">
            <h3><label class="badge badge-default" for="templateContent"><i class="red">*</i>短信内容：</label></h3>
            <textarea class="md-textarea form-control" rows="4" id="templateContent" name="templateContent" readonly cols="180"></textarea>
            <input type="hidden" id="templateId" name="templateId">
            <span class="help-inline"></span>
          </div>
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="col-md-12">
        <div class="form-inline">
          <div class="form-group">
            <h3><label class="badge badge-default" for="businessPhone"><i class="red">*</i>联系电话：</label></h3>
            <div class="selected-phone">
              <span id="selected-phone"></span>
              <input type="text" id="businessPhone" name="businessPhone" class="hide" disabled="true">
              <span id="btnChangePhoneNumber" class="btn btn-outline-primary">更换</span>
              <span id="btnConfirmChangePhoneNumber" class="btn btn-outline-primary hide">确定</span>
              <span id="btnCancelChangePhoneNumber"  class="btn btn-outline-warning hide">取消</span>
            </div>
            <span class="help-inline"></span>
            <p class="red"><c:if test="${not empty sendLimit}">*每个手机号用户支持发送${sendLimit}条信息！</c:if></p>
          </div>
        </div>
      </div>
    </div>

    <div class="row-fluid">  
      <div class="col-md-12">
        <span id="btnSend" class="btn btn-outline-primary">发送短信</span>
      </div>
    </div>


    <div class="row-fluid">  <%-- 发送状态 --%>
      <div class="col-md-12">
        <form id="searchForm" modelAttribute="smsContent" class="form-horizontal custom" style="margin-top: 24px;">
          <div class="row-fluid">
            <div class="col-md-12">
              <div class="control-group">
                <label class="control-label" >发送状态:</label>
                <select class="selectpicker" id="smsStatusSelector">
                  <option value="">全部</option>
                  <option value="0">发送成功</option>
                  <option value="-1">发送失败</option>
                  <option value="-2">退订</option>
                  <option value="-3">条数限制</option>
                </select>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="container-fluid" id="tableOutContainer">
  
      <div class="row-fluid">   <%-- contentTable --%>
        <div class="col-md-12" id="tableContainer">
          <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <thead class="table-primary">
              <tr>
                <th style="text-align: center" class="">短信模板</th>
                <th style="text-align: center" class="">发送时间</th>
                <th style="text-align: center" class="">发送状态</th>
                <th style="text-align: center" class="">发送对象</th>
                <th style="text-align: center" class="">处理人员</th>
                <th style="text-align: center" class="">内容</th>
              </tr>
            </thead>
            <tbody id="contentBody">
            <c:forEach items="${page.list}" var="smsContent">
              <tr>
                <td style="text-align: center">
                  ${smsContent.templateName}
                </td>
                <td style="text-align: center">
                  <fmt:formatDate value="${smsContent.sendTime}" pattern="yyyy-MM-dd"/>
                </td>
                <td style="text-align: center">
                  ${smsContent.status}
                </td>
                <td style="text-align: center">
                  ${smsContent.phone}
                </td>
                <td style="text-align: center">
                  ${smsContent.senderName}
                </td>
                <td style="text-align: center">
                  ${smsContent.content}
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
  
      <div class="row-fluid">  
        <div class="col-md-12">
          <ul id="pagination" class="pagination" pageNo="" pageMax="${page.count / page.pageSize}">
            <li class="page-item disabled"><span class="page-link" href="#">&laquo;</span></li>
            <c:forEach items="${pageNumberList}" var="pageNumber" varStatus="loop">
              <li class="page-item <c:if test='${pageNumber == 1}'>active</c:if>"><button class="page-link pageButton" pageNo="${loop.index + 1}">${loop.index + 1}</button></li>
            </c:forEach>
            <li class="page-item disabled"><span class="page-link" href="#">&raquo;</span></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="row-fluid">  
      <div class="col-md-12">
      </div>
    </div>
  </div>


<script type="text/javascript">

      $("#btnConfirmChangePhoneNumber").hide();
      $("#btnCancelChangePhoneNumber").hide();

      $(document).ready(function() {

          var getUrlParameter = function getUrlParameter(sParam) {
              var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                  sURLVariables = sPageURL.split('&'),
                  sParameterName,
                  i;
              for (i = 0; i < sURLVariables.length; i++) {
                  sParameterName = sURLVariables[i].split('=');
                  if (sParameterName[0] === sParam) {
                      return sParameterName[1] === undefined ? true : sParameterName[1];
                  }
              }
          };

          var customerPhone = "${customerPhone}";
          var loanBillNo = getUrlParameter("loanBillNo");
          // console.log(loanBillNo);

          // var API = {
          //   getSmsTplList: '${ctx}/sms/getTemplates', // 获取信息模板
          //   getPhoneList: '${ctx}/sms/getContacts', // 获取联系人
          //   addNewSms: '${ctx}/sms/sendSmsByTemplate', // 发送短信接口
          //   getSentSmsList: '${ctx}/sms/getSmsContentPage', // 已发短信列表
          //   getSmsSubTable: '${ctx}/sms/getSmsSubTable'
          // }

          $("#templateList").change(function () {
              var content = $('option:selected', this).attr('content');
              $("#templateContent").val(content);
          });

          $("#btnChangePhoneNumber").click(function () {
              showChangeBusinessPhone();
          });
          $("#btnConfirmChangePhoneNumber").click(function () {
              hideChangeBusinessPhone();
          });
          $("#btnCancelChangePhoneNumber").click(function () {
              hideChangeBusinessPhone();
          });

          function showChangeBusinessPhone() {
              console.log("showChangeBusinessPhone");
              $("#btnChangePhoneNumber").hide();
              $("#businessPhone").prop('disabled', false);
              $("#btnConfirmChangePhoneNumber").show();
              $("#btnCancelChangePhoneNumber").show();
              $("#businessPhone").show();
          }

          function hideChangeBusinessPhone() {
              console.log("hideChangeBusinessPhone");
              $("#btnChangePhoneNumber").show();
              $("#businessPhone").prop('disabled', true);
              $("#btnConfirmChangePhoneNumber").hide();
              $("#btnCancelChangePhoneNumber").hide();
              $("#templateContent").val($("#templateContent").val().replace("《业务员号码》", $("#businessPhone").val()));
          }

          function sendSMS() {
              var mobiles = $("#mobiles").val();
              if(mobiles.length < 1) {
                Common.alert("请选择发送对象");
                  return;
              }

              if($("#templateList").val().length < 1) {
                Common.alert("请选择短信模板");
                  return;
              }

              if($("#businessPhone").val().length < 1) {
                Common.alert("请填写联系电话");
                  return;
              }

            Common.confirm("发送短信", "确认要发送短信吗？", function (isConfirm) {
              if (isConfirm) {
                var jsonOutput = {
                    templateId:$("#templateList").val(),
                    mobiles:mobiles,
                    customerPhone:customerPhone,
                    businessPhone:$("#businessPhone").val(),
                    loanBillNo : loanBillNo
                };
                jsonOutput = JSON.stringify(jsonOutput);
                console.log(jsonOutput);
                var protocol = location.protocol;
                var slashes = protocol.concat("//");
                var host = slashes.concat(window.location.hostname)+(location.port ? ':'+location.port: '');
                var url = host + "${ctx}" + "/sms/sendSmsByTemplate";
                $.ajax({
                    type : "POST",
                    url : url,
                    data: jsonOutput,
                    contentType: "application/json; ; charset=utf-8",
                    dataType: "json",
                    timeout:50000,
                    success:function(datas){
                        console.log(datas);
                        if(datas.success == true) {
                            Common.alert("发送成功");
                        } else {
                            Common.alert(datas.msg);
                        }
                        getSmsSubTable(1);
                    },
                    error: function(datas) {
                        console.log(datas);
                    }
                });
              }
            });
          }

          $(".pageButton").click(function () {
              var pageNo = $(this).attr("pageNo");
              // $(this).removeClass("badge-primary");
              $(".pageButton").parent(".page-item").removeClass("active");
              $(this).parent(".page-item").addClass("active");
              getSmsSubTable(pageNo);
          });

          $("#smsStatusSelector").change(function () {
              var pageNo = 1;
              getSmsSubTable(pageNo);
          });

          function getSmsSubTable(pageNo) {
              var smsStatusSelector = $("#smsStatusSelector").val();

              var jsonOutput = {
                  phone:customerPhone,
                  loanBillNo : loanBillNo,
                  status:smsStatusSelector,
                  pageNo:pageNo
              };

              jsonOutput = JSON.stringify(jsonOutput);
              console.log("getSmsSubTable jsonOutput: " + jsonOutput);
              var url = "${ctx}" + "/sms/getSmsSubTable";
              $.ajax({
                  type : "POST",
                  url : url,
                  data: jsonOutput,
                  contentType: "application/json; ; charset=utf-8",
                  // dataType: "json",
                  timeout:50000,
                  success:function(datas){
                      $("#tableOutContainer").html(datas);
                      // $(".pageButton[pageNo='"+pageNo+"']").removeClass("badge-primary");
                      $(".pageButton[pageNo='"+pageNo+"']").parent(".page-item").addClass("active");
                      $(".pageButton").click(function () {
                        var pageNo = $(this).attr("pageNo");
                        // $(this).removeClass("badge-primary");
                        $(".pageButton").parent(".page-item").removeClass("active");
                        $(this).parent(".page-item").addClass("active");
                        getSmsSubTable(pageNo);
                      });
                  },
                  error: function(datas) {
                      console.log(datas);
                  }
              });
          }

          $("#btnSend").click(function () {
              sendSMS();
          });

          $("#close").click(function () {
              console.log("close");
              window.location.href="about:blank";
              window.close();
          });

      });


  </script>
</body>
</html>

