<webui:table 
		items="entrys"
		title="ҵ��Ȩ����Ŀ�б�"
		tableId="b"
		action="${pageContext.request.contextPath}/sm/queryOPPrivilege.do?act=queryOperatorPrivilege&opId=${queryOperatorPrivilegeForm.operator.operatorId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="entry"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="entry.entryId" title="���" width="10%"/>
			<webui:column filterable="false" property="dataResource.privName" title="ҵ��Ȩ�ޱ���" width="45%"/>
			<webui:column filterable="false" property="title" title="ҵ��Ȩ����Ŀ" width="45%"/>
		</webui:row>
</webui:table>
