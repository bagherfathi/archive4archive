<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="����Աҵ���ɫ�б�"  icon="../images/icon_list.gif">
    <div align="left">
       ��ǰ��������: ����Ա<a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}"/>"><c:out value='${op.name}'/></a>
    </div>
	<webui:table 
		items="opRoles" tableId="opRoles"
		action="${pageContext.request.contextPath}/sm/opDataRole.do"
		var="opRole"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		title="ҵ���ɫ�б�"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column property="roleName" title="��ɫ����">
			   ${opRole.role.roleName}
			</webui:column>
			<webui:column property="roleDesc" title="��ɫ����" >
			   ${opRole.role.description}
			</webui:column>
		</webui:row>
	</webui:table>
	<br/>

	<webui:table
		title="�̳е�ҵ���ɫ"
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
			<webui:column property="roleName" title="��ɫ����">
			   ${roleInGroup.role.roleName}
			</webui:column>
			<webui:column property="orgName" title="��ɫ����" styleClass="td_normal">
			   ${roleInGroup.role.description}
			</webui:column>
			<webui:column property="groupName" title="����Ա��" >
			   ${roleInGroup.group.name}
			</webui:column>
		</webui:row>
	</webui:table>
	 <security:checkPermission resourceKey="SM_CONFIG_OP_ROLE">
      <security:success>
       <webui:linkButton styleClass="clsButtonFace" href="javascript:dataRoleConfig();" value="����ҵ���ɫ"/>
      </security:success>
      </security:checkPermission> 
</webui:panel>
<script>
   function dataRoleConfig(){
   		location.href = "<c:url value="/sm/opDataRole.do?act=configRole&opId=${op.operatorId}"/>";
   }
</script>
