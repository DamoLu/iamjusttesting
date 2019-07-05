<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>发送短信</title>
  <meta name="decorator" content="default"/>
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
  <sys:message content="${message}"/>
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
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
<script type="text/javascript">
</script>
</body>
</html>