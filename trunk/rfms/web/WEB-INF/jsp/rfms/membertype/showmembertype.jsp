<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="membertypeForm" action="/membertype.do" method="post"  onsubmit="return validateMemberTypeForm(this);">
<input type="hidden" value="toSearch" name="act"/>
<webui:panel title="会员管理" width="95%"  icon="/images/icon_list.gif">    
<webui:tabContainer id="foo-bar-container">
    <webui:tabPane id="xjInfo" tabTitle="会员管理">
	   <%@include file="/WEB-INF/jsp/rfms/membertype/form.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
 
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
