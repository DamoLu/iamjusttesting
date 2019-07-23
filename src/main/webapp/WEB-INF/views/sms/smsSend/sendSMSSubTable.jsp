<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <div class="row-fluid">   <%-- contentTable --%>
    <div class="col-md-12" id="tableContainer">
      <table id="contentTable" class="table table-striped table-bordered .table-condensed">
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
          <li class="page-item"><button class="page-link pageButton" pageNo="${loop.index + 1}">${loop.index + 1}</button></li>
        </c:forEach>
        <li class="page-item disabled"><span class="page-link" href="#">&raquo;</span></li>
      </ul>
    </div>
  </div>
