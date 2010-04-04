<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="sysadmin.label.resource.query" icon="../images/icon_search.gif">
<html:form action="/queryRPrivilege.do"  method="post">
<html:hidden property="act" value="query"/>
<webui:formTable>
	<tr>
		<webui:input label="组织机构">
			<sm:selAccessOrg inputName="orgId"/>
		</webui:input>
		
		<webui:input label="功能权限">
			<sm:selAccessOrg inputName="orgId"/>
		</webui:input>
	</tr>
</webui:formTable>
<webui:linkButton styleClass="clsButtonFace" href="javascript:document.forms.queryResourcePrivilegeForm.submit();" value="sysadmin.button.search"/>
</html:form>
</webui:panel>
</br>
<webui:panel width="95%" title="sysadmin.label.resource.list" icon="../images/icon_list.gif">
<webui:table 
		dataSource="resourceDS"
		action="${pageContext.request.contextPath}/sm/queryRPrivilege.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.resource.list"
		var="resource"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="resourceId" title="序号" width="10%"/>
			<webui:column  filterable="false" property="title" title="功能权限标题">
				 <c:out value='${resource.title}'/>
			</webui:column>
			<webui:column  filterable="false" property="parent.resourceTitle" title="上级功能"/>
			<webui:column  filterable="false" property="type" title="功能权限类型">
				<webui:lookup code="resource_type@SM_RESOURCE" value="${resource.type}"/>
			</webui:column>
			<webui:column  filterable="false" property="action" title="操作">
			   <a href="<c:url value='/sm/queryRPrivilege.do?act=queryResourcePrivilege&rId=${resource.resourceId}'/>">查看</a>
			</webui:column>
		</webui:row>
</webui:table>
</webui:panel>
