<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="操作员组的功能角色" icon="../images/icon_list.gif" width="95%">
<div align="left">
       当前操作对象: <a href="<c:url value="/sm/group.do?act=view&id=${group.groupId}"/>"><c:out value="${group.name}"/></a>
    </div>
	<webui:table 
		items="groupRoles"
		action="${pageContext.request.contextPath}/sm/groupRole.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="groupRole"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column property="roleName" title="角色名称">
			   <c:out value='${groupRole.role.roleName}'/>
			</webui:column>
			<webui:column property="orgName" title="组织机构" >
			   <c:out value='${groupRole.org.name}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	<br/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:roleConfig()" value="设置功能角色"/>
</webui:panel>
<script>
   function roleConfig(){
   		location.href = "<c:url value="/sm/groupRole.do?act=configRole&id=${group.groupId}"/>";
   }
</script>
