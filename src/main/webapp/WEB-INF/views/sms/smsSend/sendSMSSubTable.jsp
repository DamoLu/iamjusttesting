<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>发送短信</title>
  <style>
  </style>
</head>
<body>
   
  <div class="row-fluid">   <%-- contentTable --%>
    <div class="col-md-12" id="tableContainer">
      <table id="contentTable" class="table table-striped table-bordered .table-condensed">
        <thead>
          <tr>
            <th style="text-align: center" class="">短信模板</th>
            <th style="text-align: center" class="">发送时间</th>
            <th style="text-align: center" class="">发送对象</th>
            <th style="text-align: center" class="">处理人员</th>
            <th style="text-align: center" class="">内容</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="smsContent">
          <tr>
            <td style="text-align: center">
              ${smsContent.templateName}
            </td>
            <td style="text-align: center">
              <fmt:formatDate value="${smsContent.sendTime}" pattern="yyyy-MM-dd"/>
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
        <li><span href="#">&laquo;</span></li>
        <c:forEach items="${pageNumberList}" var="pageNumber" varStatus="loop">
          <li><button class="badge badeg-primary pageButton" pageNo="${loop.index + 1}">${loop.index + 1}</button></li>
        </c:forEach>
        <li><span href="#">&raquo;</span></li>
      </ul>
    </div>
  </div>
<script type="text/javascript">
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

    var customerPhone = getUrlParameter('customerPhone');

    var API = {
      getSmsTplList: '/sms/getTemplates', // 获取信息模板
      getPhoneList: '/sms/getContacts', // 获取联系人
      addNewSms: '/sms/sendSmsByTemplate', // 发送短信接口
      getSentSmsList: '/sms/getSmsContentPage', // 已发短信列表
      getSmsSubTable: '/sms/getSmsSubTable'
    }

    $(".pageButton").click(function () {
      var pageNo = $(this).attr("pageNo");
      $(this).removeClass("badge-primary");
      $(".pageButton").removeClass("badge-success");
      $(this).addClass("badge-success");
      getSmsSubTable(pageNo);
    })

    $("#smsStatusSelector").change(function () {
      var pageNo = 1;
      getSmsSubTable(pageNo);
    })

    function getSmsSubTable(pageNo) {
      var smsStatusSelector = $("#smsStatusSelector").val();

      var jsonOutput = {
        phone:customerPhone,
        status:smsStatusSelector,
        pageNo:pageNo,
      };

      jsonOutput = JSON.stringify(jsonOutput);
      console.log(jsonOutput);
      var url = API.getSmsSubTable;
      $.ajax({
        type : "POST",  
        url : url,  
        data: jsonOutput,
        contentType: "application/json; ; charset=utf-8",
        // dataType: "json",
        timeout:50000,  
        success:function(datas){
          $("#tableOutContainer").html(datas);
          $(".pageButton[pageNo='"+pageNo+"']").removeClass("badge-primary");
          $(".pageButton[pageNo='"+pageNo+"']").addClass("badge-success");
        },  
        error: function(datas) {  
          console.log(datas);
        }
      }); 
    };

    $("#btnSend").click(function () {
      sendSMS();
    })

  });
</script>
</body>
</html>