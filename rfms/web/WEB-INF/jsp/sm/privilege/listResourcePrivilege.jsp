<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="����Ȩ�޷����б�-${queryResourcePrivilegeForm.resource.title}" icon="../images/icon_list.gif">
<table width="95%">
<tr>
<td align="center">
<webui:table
		title="����Ա�б�"
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
			<webui:column sortable="false" filterable="false" cell="rowCount" property="operatorId" title="���" width="10%"/>
			<webui:column  filterable="false" property="contact.name" title="����Ա����"/>
			<webui:column  filterable="false" property="loginName" title="��½����"/>
			<webui:column  filterable="false" property="contact.telephone" title="��ϵ�绰"/>
			<webui:column  filterable="false" property="contact.mobilePhone" title="�ƶ��绰"/>
			<webui:column  filterable="false" property="email" title="E-mail"/>
		</webui:row>
</webui:table>
</td>
</tr>
</table>

<webui:table
		title="����Ա���б�"
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
			<webui:column sortable="false" filterable="false" cell="rowCount" property="groupId" title="���" width="10%"/>
			<webui:column  filterable="false" property="name" title="����Ա������"/>
		</webui:row>
</webui:table>

<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<script>
function toReturn(){
		window.location = "<c:url value='/sm/queryRPrivilege.do'/>";
	}
</script>
