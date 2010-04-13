<%@ page contentType="text/html; charset=GBK" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/tld.inc" %><%--
--%><c:set var="jspBegin"><%=System.currentTimeMillis()%></c:set><%--
--%>
<html>
<head>
<base   target="_self">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); 
%>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>分配POS数据</title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
</head>
<body align="center">
<table width="100%">
<tr><td align="center">
<html:form styleId="merchantForm" action="/merchant.do" method="post">
<input type="hidden" value="savePos" name="act"/>
<html:hidden property="posId" />
<html:hidden property="branchId" />
<webui:panel title="title.rfms.merchant_pos.list" width="100%"  icon="/images/icon_list.gif"> 
<webui:table items="${merchantPoss }" action="${pageContext.request.contextPath}/sm/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_pos.list" var="pos" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm" form="merchantForm">
		<webui:row>
			<webui:column property="sysPosCode" title="label.rfms.merchant_pos.sys_pos_code" styleClass="td_normal"/>
			<webui:column  property="owner" title="label.rfms.merchant_pos.owner">
			<webui:lookup code="owner@RFMS_MERCHANT_POS" value="${pos.posType}" />
			</webui:column>
			<webui:column property="posType" title="label.rfms.merchant_pos.pos_type">
			<webui:lookup code="pos_type@RFMS_MERCHANT_POS" value="${pos.posType}" />
			</webui:column>
			<webui:column property="mainkey" title="POS密钥" styleClass="td_normal"/>
		</webui:row>
	</webui:table>
	 <webui:linkButton styleClass="clsButtonFace" href="javascript:onClose();" value="sysadmin.button.close"/>
</webui:panel>
</html:form>
</td>
</tr></table>
 
</body>
</html>