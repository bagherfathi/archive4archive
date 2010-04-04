<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
<c:if test="${enumGroupForm.refresh}">
	window.parent.frames['leftFrame'].location.href = window.parent.frames['leftFrame'].location.href;
</c:if>
</script>
<webui:panel title="导入数据结果" icon="../images/icon_list.gif" width="95%">
<table width="95%">
<tr>
	<td align="center">
	<webui:fieldSet title="导入后新增的内容">
		<webui:table
				items="addList"
				title="导入成功的内容"
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
				<webui:column sortable="false" filterable="false" cell="rowCount" property="enumId" title="序号" width="10%"/>
				<webui:column sortable="false" property="enumName" title="数据名称" width="45%"/>
				<webui:column sortable="false" property="enumCode" title="数据代码" width="45%"/>
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
	<webui:fieldSet title="导入后更新的内容">
		<webui:table
			items="updateList"
			title="导入后更新的内容"
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
			<webui:column sortable="false" filterable="false" cell="rowCount" property="enumId" title="序号" width="10%"/>
			<webui:column sortable="false" property="enumName" title="数据名称" width="45%"/>
			<webui:column sortable="false" property="enumCode" title="数据代码" width="45%"/>
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
	<webui:fieldSet title="无法导入的内容">
		<webui:table
			items="errorList"
			title="无法导入的内容"
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
			<webui:column sortable="false" filterable="false" cell="rowCount" property="enumId" title="序号" width="10%"/>
			<webui:column sortable="false" property="enumName" title="数据名称" width="45%"/>
			<webui:column sortable="false" property="enumCode" title="数据代码" width="45%"/>
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