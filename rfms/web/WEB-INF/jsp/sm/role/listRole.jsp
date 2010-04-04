<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
   if(window.event.keyCode==13){
	    loadOn();
	    document.forms.roleForm.submit();
   }
}
document.body.onkeypress= checkEnter;
</script>
<html:form action="/role.do">
	<webui:panel title="sysadmin.button.search"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="��ɫ����" colspan="4">
					<html:text property="roleName" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#"
			onClick="loadOn();document.forms.roleForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
</html:form>
<br />
<webui:panel title="sysadmin.label.role.title"
	icon="../images/icon_list.gif" width="95%">

	<webui:table items="roles"
		action="${pageContext.request.contextPath}/sm/role.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%" var="role" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" sortable="false"
		autoIncludeParameters="true">
		<webui:row>
			<webui:column filterable="false" property="name" title="��ɫ����"
				width="30%">
				<c:out value='${role.roleName}' />
			</webui:column>
			<webui:column property="description" title="��ɫ����" width="60%"
				styleClass="td_normal">
			</webui:column>
			<webui:column property="action" title="����">
				<a href="<c:url value='/sm/role.do?act=view&id=${role.roleId}'/>">�鿴</a>&nbsp;
		<%--	<c:if test="${role.roleId != 1 && roleForm.currentUser.orgId == role.orgId}" > --%>
				<a href="<c:url value='/sm/role.do?act=toEdit&id=${role.roleId}'/>">�༭</a>
				<%--	</c:if>   --%>
				<sm:role id="${role.roleId}" var="flag" />
				<c:if test="${!flag}">
				    <a href="#" onclick="goDel(<c:out value="${role.roleId }"/>)">ɾ��</a>
				</c:if>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_ROLE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:goCreate();" value="����" />
		</security:success>
	</security:checkPermission>
</webui:panel>
<script>
        function goCreate(){
            location.href = "<c:url value='/sm/role.do?act=create'/>"
        }
        function goDel(id){
		    if(window.confirm("<bean:message key='msg.confirm.delete.role'/>")){
			window.location = "<c:url value='/sm/role.do?act=delete&id='/>" + id;
		}
	}
</script>
