<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>
<webui:panel width="95%" title="����ԱȨ���б�-${queryOperatorPrivilegeForm.operator.contact.name}" icon="../images/icon_list.gif">
<webui:tabContainer id="foo-bar-container">
	<webui:tabPane id="resourceTree" tabTitle="����Ȩ��">
	   <%@include file="/WEB-INF/jsp/sm/privilege/inc/opResourceTree.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="dataResourceList" tabTitle="ҵ��Ȩ��">
	   <%@include file="/WEB-INF/jsp/sm/privilege/inc/opDataResourceList.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>