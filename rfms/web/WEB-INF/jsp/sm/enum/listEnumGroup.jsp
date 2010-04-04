<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        window.location.href='<c:url value="/sm/enumGroup.do?act=create"/>';
    }
    
    function toImport(){
    	window.location.href='<c:url value="/sm/enumGroup.do?act=toImport"/>';
    }
</script>
<webui:panel title="sysadmin.title.enumgroup.list" icon="../images/icon_list.gif" width="95%">
	<webui:table
		dataSource="enumGroupDS"
		action="${pageContext.request.contextPath}/sm/enumGroup.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.enumgroup.list"
		var="enumGroup"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" property="groupName" title="组名称"/>
			<webui:column sortable="false" filterable="false" property="description" title="描述信息" styleClass="td_normal">
			    <c:out value="${enumGroup.description}"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="操作" title="操作">
			    <a href="<c:url value='/sm/enumGroup.do?act=edit&groupId=${enumGroup.groupId}'/>">修改</a>
			</webui:column>	
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_ENUM_DATA_GROUP">
		<security:success>
		
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="sysadmin.button.create"/>
		
		</security:success>
	</security:checkPermission>
	<%--
	<security:checkPermission resourceKey="SM_IMPORT_ENUM_DATA_GROUP">
		<security:success>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toImport();" value="sysadmin.button.import"/>
		</security:success>
	</security:checkPermission>
	--%>
</webui:panel>

<c:choose>
<c:when test="${param.flag == 'delete' }">
      <script>
      var tree= parent.leftFrame.tree;
      tree.deleteItem("group_<c:out value="${param.deletedId}"/>",true);
      </script>
</c:when>
<c:when test="${empty param.flag }">
      <script>
        if(parent.leftFrame.document.readyState == "complete"){
           parent.leftFrame.tree.selectItem("root_-1",false);
        }
      </script>
</c:when>
</c:choose>
