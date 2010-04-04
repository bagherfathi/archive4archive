<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>
<webui:panel width="95%" title="操作员权限列表-${queryOperatorPrivilegeForm.operator.contact.name}" icon="../images/icon_list.gif">
<webui:tabContainer id="foo-bar-container">
	<webui:tabPane id="resourceTree" tabTitle="功能权限">
	   <%@include file="/WEB-INF/jsp/sm/privilege/inc/opResourceTree.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="dataResourceList" tabTitle="业务权限">
	   <%@include file="/WEB-INF/jsp/sm/privilege/inc/opDataResourceList.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>