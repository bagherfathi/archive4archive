<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="sysadmin.label.dataResource.privilege.list" icon="../images/icon_list.gif" width="95%">
<webui:formTable>
	<tr>
		<webui:input label="sysadmin.label.dataResource.name">
			<c:out value="${queryDataResourcePrivilegeForm.dataResourceEntry.dataResource.privName}"/>
		</webui:input>
		<webui:input label="sysadmin.label.dataResource.entry.title">
			<c:out value="${queryDataResourcePrivilegeForm.dataResourceEntry.title}"/>
		</webui:input>
	</tr>
</webui:formTable>
</br>
<webui:table
		title="操作员列表"
		items="operators"
		tableId="operatorTable"
		action="${pageContext.request.contextPath}/sm/queryDRPrivilege.do?act=queryDataResourcePrivilege&drId=${queryDataResourcePrivilegeForm.dataResource.resourceId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="op"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="id" title="序号" width="10%"/>
			<webui:column  filterable="false" property="contact.name" title="操作员名称" width="30%"/>
			<webui:column  filterable="false" property="loginName" title="登陆名称" width="30%"/>
			<webui:column  filterable="false" property="email" title="电子邮箱" width="30%"/>
		</webui:row>
</webui:table>
</br>
<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<script>
function toReturn(){
		window.location = "<c:url value='/sm/queryDRPrivilege.do'/>";
	}
</script>
