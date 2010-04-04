<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<select name="operatorId">
  <option value="0">Ыљга</option>
  <c:forEach items="${ops}" var="op">
      <option value="<c:out value="${op.operatorId}"/>"><c:out value="${op.name}"/></option>
  </c:forEach>
</select>