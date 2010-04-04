<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="sysadmin.label.operator.privilege.query" icon="../images/icon_search.gif">
<html:form action="/queryOPPrivilege.do"  method="post">
<html:hidden property="act" value="query"/>
<webui:formTable>
	<tr>
		<webui:input label="sysadmin.label.operator.name">
			<html:text property="opName"/>
		</webui:input>
		<webui:input label="sysadmin.label.operator.login.name">
			<html:text property="loginName"/>
		</webui:input>
	</tr>
</webui:formTable>
<webui:linkButton styleClass="clsButtonFace" href="javascript:document.forms.queryOperatorPrivilegeForm.submit();" value="sysadmin.button.search"/>
</html:form>
</webui:panel>
</br>
<webui:panel width="95%" title="sysadmin.label.resource.list" icon="../images/icon_list.gif">
<webui:table
	dataSource="operatorDS"
	action="${pageContext.request.contextPath}/sm/queryOPPrivilege.do"
	imagePath="${pageContext.request.contextPath}/images/table/*.gif"
	title="sysadmin.label.resource.list"
	var="op"
	width="95%"
	showPagination="true"
	showStatusBar="true"
	showTitle="false"
	filterable="false"
	sortable="false"
	autoIncludeParameters="true">
	<webui:row>
		<webui:column sortable="false" filterable="false" cell="rowCount" property="operatorId" title="序号" width="10%"/>
		<webui:column  filterable="false" property="contact.name" title="操作员名称">
			 <c:out value='${op.contact.name}'/>
		</webui:column>
		<webui:column  filterable="false" property="loginName" title="登录名称"/>
		<webui:column  filterable="false" property="msn" title="MSN联系方式"/>
		<webui:column  filterable="false" property="email" title="E-Mail"/>
		<webui:column  filterable="false" property="action" title="操作">
		     <a href="<c:url value='/sm/queryOPPrivilege.do?act=queryOperatorPrivilege&opId=${op.operatorId}'/>">查看</a>
		</webui:column>
	</webui:row>
</webui:table>
</webui:panel>
