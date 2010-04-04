<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="功能权限分配列表-${queryResourcePrivilegeForm.resource.title}" icon="../images/icon_list.gif">
<table width="95%">
<tr>
<td align="center">
<webui:table
		title="操作员列表"
		items="operators"
		tableId="operatorTable"
		action="${pageContext.request.contextPath}/sm/queryRPrivilege.do?act=queryResourcePrivilege&rId=${queryResourcePrivilegeForm.resource.resourceId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="100%"
		var="op"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="operatorId" title="序号" width="10%"/>
			<webui:column  filterable="false" property="contact.name" title="操作员名称"/>
			<webui:column  filterable="false" property="loginName" title="登陆名称"/>
			<webui:column  filterable="false" property="contact.telephone" title="联系电话"/>
			<webui:column  filterable="false" property="contact.mobilePhone" title="移动电话"/>
			<webui:column  filterable="false" property="email" title="E-mail"/>
		</webui:row>
</webui:table>
</td>
</tr>
</table>

<webui:table
		title="操作员组列表"
		items="groups"
		tableId="groupTable"
		action="${pageContext.request.contextPath}/sm/queryRPrivilege.do?act=queryResourcePrivilege&rId=${queryResourcePrivilegeForm.resource.resourceId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%" var="group"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="groupId" title="序号" width="10%"/>
			<webui:column  filterable="false" property="name" title="操作员组名称"/>
		</webui:row>
</webui:table>

<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<script>
function toReturn(){
		window.location = "<c:url value='/sm/queryRPrivilege.do'/>";
	}
</script>
