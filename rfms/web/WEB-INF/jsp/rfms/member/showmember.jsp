<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="memberForm" action="/member.do" method="post"  onsubmit="return validateCardForm(this);">
<input type="hidden" value="toSearch" name="act"/>
<webui:panel title="�û�����" width="95%"  icon="/images/icon_list.gif">    
<webui:tabContainer id="foo-bar-container">
    <webui:tabPane id="xjInfo" tabTitle="�û�����">
	   <%@include file="/WEB-INF/jsp/rfms/member/form.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
 
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
