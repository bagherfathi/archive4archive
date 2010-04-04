<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="sysadmin.label.resource.query" icon="../images/icon_search.gif">
<html:form action="/queryRPrivilege.do"  method="post">
<html:hidden property="act" value="query"/>
<webui:formTable>
	<tr>
		<webui:input label="��֯����">
			<sm:selAccessOrg inputName="orgId"/>
		</webui:input>
		
		<webui:input label="����Ȩ��">
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
			<webui:column sortable="false" filterable="false" cell="rowCount" property="resourceId" title="���" width="10%"/>
			<webui:column  filterable="false" property="title" title="����Ȩ�ޱ���">
				 <c:out value='${resource.title}'/>
			</webui:column>
			<webui:column  filterable="false" property="parent.resourceTitle" title="�ϼ�����"/>
			<webui:column  filterable="false" property="type" title="����Ȩ������">
				<webui:lookup code="resource_type@SM_RESOURCE" value="${resource.type}"/>
			</webui:column>
			<webui:column  filterable="false" property="action" title="����">
			   <a href="<c:url value='/sm/queryRPrivilege.do?act=queryResourcePrivilege&rId=${resource.resourceId}'/>">�鿴</a>
			</webui:column>
		</webui:row>
</webui:table>
</webui:panel>
