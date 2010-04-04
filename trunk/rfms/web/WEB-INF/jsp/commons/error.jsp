<%@page contentType="text/html;charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<fmt:setBundle basename="GlobalExceptionResouece"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title><bean:message  key="system.exception"/></title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>

</head>
<body>
  <webui:panel title="系统运行中发生错误" width="95%">
      错误信息:
      <fmt:message key="${errorCode}">
          <c:forEach items="${exceptionParams}" var="paramValue">
              <fmt:param value="${paramValue}"/>
          </c:forEach>
      </fmt:message>
  </webui:panel>
  <br>
  <webui:panel title="异常堆栈" width="95%">
     <textarea style="width:600;height:400"><c:out value='${exceptionStack}'/></textarea>
  </webui:panel>
  <br>
  <table width="95%">
    <tr>
      <td align="center">
        <span class="clsButtonFace"><a href="javascript:history.go(-1);">返回</a></span>
      </td>
    </tr>
</table>
</body>
</html>