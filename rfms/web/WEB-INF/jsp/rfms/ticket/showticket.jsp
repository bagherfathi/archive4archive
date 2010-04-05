<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="ticketForm" action="/ticket.do" method="post"  onsubmit="return validateticketForm(this);">
<input type="hidden" value="toSearch" name="act"/>
<webui:panel title="·ÉÈ¯ÏêÇé" width="95%"  icon="/images/icon_list.gif">    
	   <%@include file="/WEB-INF/jsp/rfms/ticket/form.jsp" %>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
