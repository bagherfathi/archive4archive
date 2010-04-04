<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="sysadmin.label.group.privilege.query" icon="../images/icon_search.gif">
<html:form action="/queryGroupPrivilege.do"  method="post">
<html:hidden property="act" value="query"/>
<webui:formTable>
	<tr>
		<webui:input label="sysadmin.label.group.name">
			<html:text property="groupName"/>
		</webui:input>
	</tr>
</webui:formTable>
<webui:linkButton styleClass="clsButtonFace" href="javascript:document.forms.queryGroupPrivilegeForm.submit();" value="sysadmin.button.search"/>
</html:form>
</webui:panel>
</br>
<webui:panel width="95%" title="操作员组列表" icon="../images/icon_list.gif">
<webui:table 
		dataSource="groupDS"
		action="${pageContext.request.contextPath}/sm/queryGroupPrivilege.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="操作员组列表"
		var="group"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="groupId" title="序号" width="10%"/>
			<webui:column  filterable="false" property="name" title="操作员组名称">
				 <c:out value="${group.name}"/><c:out value="${group.name}"/>
			</webui:column>
			<webui:column  filterable="false" property="description" title="说明"/>
			<webui:column  filterable="false" property="action" title="操作" width="10%">
			     <a href="<c:url value='/sm/queryGroupPrivilege.do?act=queryGroupPrivilege&gId=${group.groupId}'/>">查看</a>
			</webui:column>
		</webui:row>
</webui:table>
</webui:panel>
