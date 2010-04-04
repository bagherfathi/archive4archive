<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="sysadmin.label.dataResource.query" icon="../images/icon_search.gif" width="95%">
<html:form action="/queryDRPrivilege.do"  method="post">
<html:hidden property="act" value="query"/>
<webui:formTable>
	<tr>
		<webui:input label="sysadmin.label.dataResource.entry.title">
			<html:text property="title"/>
		</webui:input>
	</tr>
</webui:formTable>
<webui:linkButton styleClass="clsButtonFace" href="javascript:document.forms.queryDataResourcePrivilegeForm.submit();" value="sysadmin.button.search"/>
</html:form>
</webui:panel>
</br>
<webui:panel title="ҵ��Ȩ���б�" icon="../images/icon_list.gif" width="95%">
<webui:table 
		dataSource="dataResourceEntryDS"
		action="${pageContext.request.contextPath}/sm/queryDRPrivilege.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.dataResource.list"
		var="entry"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="entryId" title="���" width="10%"/>
			<webui:column  filterable="false" property="title" title="ҵ��Ȩ����Ŀ����">
                  <c:out value='${entry.title}'/>
			</webui:column>
			<webui:column  filterable="false" property="dataResource.privName" title="ҵ��Ȩ�ޱ���"/>
			<webui:column  filterable="false" property="action" title="����">
			      <a href="<c:url value='/sm/queryDRPrivilege.do?act=queryDataResourcePrivilege&id=${entry.entryId}'/>">�鿴</a>
			</webui:column>
		</webui:row>
</webui:table>
</webui:panel>
