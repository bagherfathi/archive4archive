<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="����Ա�Ĺ��ܽ�ɫ"  icon="../images/icon_list.gif">
   <div align="left">
       ��ǰ��������: ����Ա<a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}"/>"><c:out value='${op.name}'/></a>
    </div>
	<webui:table
		title="���ܽ�ɫ�б�"
		items="opRoles" tableId="opRoles"
		action="${pageContext.request.contextPath}/sm/opRole.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="opRole"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column property="roleName" title="��ɫ����">
			   <c:out value='${opRole.role.roleName}'/>
			</webui:column>
			<webui:column property="desc" title="��ɫ����" styleClass="td_normal">
			   <c:out value='${opRole.role.description}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	<br/>

	<webui:table
		title="�̳еĹ��ܽ�ɫ"
		items="roleInGroups" tableId="roleInGroups"
		action="${pageContext.request.contextPath}/sm/opRole.do"
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
			   <c:out value='${roleInGroup.role.roleName}'/>
			</webui:column>
			<webui:column property="desc" title="��ɫ����" styleClass="td_normal">
			   <c:out value='${roleInGroup.role.description}'/>
			</webui:column>
			<webui:column property="groupName" title="����Ա��" >
			   <c:out value='${roleInGroup.group.name}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_CONFIG_OP_ROLE">
	<security:success>
		<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:loadOn();roleConfig();" value="���ù��ܽ�ɫ"/>
	</security:success>
	</security:checkPermission>
</webui:panel>
<script>
   function roleConfig(){
   		location.href = "<c:url value="/sm/opRole.do?act=configRole&opId=${op.operatorId}"/>";
   }
</script>
