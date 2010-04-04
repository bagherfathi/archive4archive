<sm:query var="operatorRoleOrgs" beanName="operatorManager" methodName="findOperatorOfRole">
		<sm:param type="java.lang.Long" value="${role.roleId}" />
</sm:query>
<webui:table
items="operatorRoleOrgs"
action="${pageContext.request.contextPath}/sm/dataRole.do?act=view&id=${role.roleId}"
imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
width="100%"
var="oro"
showPagination="true"
showStatusBar="true"
showTitle="false"
filterable="false"
sortable="false"
tableId="dataRoleForm">
	<webui:row>
		<webui:column filterable="false" property="operator.opName" title="sysadmin.label.operator.name" />
	   	<webui:column filterable="false" property="operator.loginName" title="sysadmin.label.operator.login.name" />
	   	<webui:column filterable="false" property="org.orgName" title="sysadmin.label.group.org"/>
	</webui:row>
</webui:table>
