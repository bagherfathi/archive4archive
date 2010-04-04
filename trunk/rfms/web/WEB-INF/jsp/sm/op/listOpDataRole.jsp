<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="操作员业务角色列表"  icon="../images/icon_list.gif">
    <div align="left">
       当前操作对象: 操作员<a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}"/>"><c:out value='${op.name}'/></a>
    </div>
	<webui:table 
		items="opRoles" tableId="opRoles"
		action="${pageContext.request.contextPath}/sm/opDataRole.do"
		var="opRole"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		title="业务角色列表"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column property="roleName" title="角色名称">
			   ${opRole.role.roleName}
			</webui:column>
			<webui:column property="roleDesc" title="角色描述" >
			   ${opRole.role.description}
			</webui:column>
		</webui:row>
	</webui:table>
	<br/>

	<webui:table
		title="继承的业务角色"
		items="roleInGroups" tableId="roleInGroups" 
		action="${pageContext.request.contextPath}/sm/opDataRole.do"
		var="roleInGroup"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column property="roleName" title="角色名称">
			   ${roleInGroup.role.roleName}
			</webui:column>
			<webui:column property="orgName" title="角色描述" styleClass="td_normal">
			   ${roleInGroup.role.description}
			</webui:column>
			<webui:column property="groupName" title="操作员组" >
			   ${roleInGroup.group.name}
			</webui:column>
		</webui:row>
	</webui:table>
	 <security:checkPermission resourceKey="SM_CONFIG_OP_ROLE">
      <security:success>
       <webui:linkButton styleClass="clsButtonFace" href="javascript:dataRoleConfig();" value="设置业务角色"/>
      </security:success>
      </security:checkPermission> 
</webui:panel>
<script>
   function dataRoleConfig(){
   		location.href = "<c:url value="/sm/opDataRole.do?act=configRole&opId=${op.operatorId}"/>";
   }
</script>
