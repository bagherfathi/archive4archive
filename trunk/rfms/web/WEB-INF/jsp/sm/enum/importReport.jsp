<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
<c:if test="${enumGroupForm.refresh}">
	window.parent.frames['leftFrame'].location.href = window.parent.frames['leftFrame'].location.href;
</c:if>
</script>
<webui:panel title="�������ݽ��" icon="../images/icon_list.gif" width="95%">
<table width="95%">
<tr>
	<td align="center">
	<webui:fieldSet title="���������������">
		<webui:table
				items="addList"
				title="����ɹ�������"
				action="${pageContext.request.contextPath}/sm/enumGroup.do"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				width="95%"
				var="enumData"
				showPagination="true"
				showStatusBar="true"
				showTitle="false"
				filterable="false"
				sortable="false"
				autoIncludeParameters="true"
				>
				<webui:row>
				<webui:column sortable="false" filterable="false" cell="rowCount" property="enumId" title="���" width="10%"/>
				<webui:column sortable="false" property="enumName" title="��������" width="45%"/>
				<webui:column sortable="false" property="enumCode" title="���ݴ���" width="45%"/>
				</webui:row>
		</webui:table>
		</br>
	</webui:fieldSet>
	</td>
</tr>
</table>
</br>
<table width="95%">
<tr>
	<td align="center">
	<webui:fieldSet title="�������µ�����">
		<webui:table
			items="updateList"
			title="�������µ�����"
			action="${pageContext.request.contextPath}/sm/enumGroup.do"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			width="95%"
			var="enumData"
			showPagination="true"
			showStatusBar="true"
			showTitle="false"
			filterable="false"
			sortable="false"
			autoIncludeParameters="true"
			>
			<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="enumId" title="���" width="10%"/>
			<webui:column sortable="false" property="enumName" title="��������" width="45%"/>
			<webui:column sortable="false" property="enumCode" title="���ݴ���" width="45%"/>
			</webui:row>
		</webui:table>
		</br>
	</webui:fieldSet>
	</td>
</tr>
</table>
</br>
<table width="95%">
<tr>
	<td align="center">
	<webui:fieldSet title="�޷����������">
		<webui:table
			items="errorList"
			title="�޷����������"
			action="${pageContext.request.contextPath}/sm/enumGroup.do"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			width="95%"
			var="enumData"
			showPagination="true"
			showStatusBar="true"
			showTitle="false"
			filterable="false"
			sortable="false"
			autoIncludeParameters="true"
			>
			<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="enumId" title="���" width="10%"/>
			<webui:column sortable="false" property="enumName" title="��������" width="45%"/>
			<webui:column sortable="false" property="enumCode" title="���ݴ���" width="45%"/>
			</webui:row>
		</webui:table>
		</br>
	</webui:fieldSet>
	</td>
</tr>
</table>
</br>
<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<script>
	function toReturn(){
		window.location = "<c:url value='/sysadmin/enumGroup.do'/>";
	}
</script>