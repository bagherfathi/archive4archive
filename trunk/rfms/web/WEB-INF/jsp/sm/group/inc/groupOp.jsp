<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
  <sm:query beanName="operatorManager" methodName="findOperatorsOfGroup" var="ops">
     <sm:param value="${group.groupId}" type="java.lang.Long"/>
 </sm:query>
 
 <webui:table 
		items="ops"
		action="${pageContext.request.contextPath}/sm/group.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="op"
		width="100%"
		rowsDisplayed="20"
		showPagination="false"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true">
		<webui:row >
			<webui:column property="loginName" title="sysadmin.label.loginName">
			    <c:out value='${op.loginName}'/>
			</webui:column>
			<webui:column property="contact.name" title="sysadmin.label.name"/>
			<webui:column property="email" title="sysadmin.label.email" />
			<webui:column property="status" title="sysadmin.title.common.status" >
			   <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
			</webui:column>
		</webui:row>
</webui:table>