<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<table width="95%" height="100%" border="0" align="center" cellspacing="0">
<tr>
<td align="center" valign="top" bgcolor="#F5F8FB">
	<webui:panel title="sysadmin.label.info.shared.list" width="95%" icon="../images/icon_list.gif">
	<entity:query var="shareds" queryName="findSharedByCategoryId">
			<entity:param type="java.lang.Long" value="${infoSharedForm.category.categoryId}" />
	</entity:query>

	<webui:table
		items="shareds"
		action="${pageContext.request.contextPath}/sm/infoShared.do?act=listShared&cId=${infoSharedForm.category.categoryId}"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.info.shared.list"
		var="shared"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false">
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="infoId" title="序号" width="5%"/>
			<webui:column filterable="false" property="title" title="标题" width="50%">
			<a href="<c:url value='/sm/infoShared.do?act=view&id=${shared.infoId}'/>"><c:out value="${shared.title}"/></a>
			</webui:column>
			<webui:column filterable="false" property="operator.contact.name" title="发布人" width="20%"/>
			<webui:column filterable="false" property="publishTime" title="发布时间" width="25%"/>
		</webui:row>
	</webui:table>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toPublish();" value="sysadmin.button.publish"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toSearch();" value="sysadmin.button.search"/>
	</webui:panel>
</td>
</tr>
</table>
<script>
	function toPublish(){
		window.location = "<c:url value='/sm/infoShared.do?act=toPublish'/>"+"&cId"+"=<c:out value='${infoSharedForm.category.categoryId}'/>";
	}
	
	function toSearch(){
		window.location = "<c:url value='/sm/infoShared.do?act=toSearch'/>"+"&cId"+"=<c:out value='${infoSharedForm.category.categoryId}'/>";
	}
</script>
