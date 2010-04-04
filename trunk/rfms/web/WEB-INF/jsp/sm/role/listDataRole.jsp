<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html:form action="/dataRole.do" >
<webui:panel title="sysadmin.button.search" icon="../images/icon_search.gif" width="95%">	
    <webui:formTable>
      <tr>
	    <webui:input label="��ɫ����" colspan="4">
	      <html:text property="role.roleName" size="25"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
	 <webui:linkButton styleClass="clsButtonFace" href="#" onClick="loadOn();document.forms.dataRoleForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="sysadmin.label.data.role.title" icon="../images/icon_list.gif">
	<webui:table
		items="dataRoles"
		action="${pageContext.request.contextPath}/sm/dataRole.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="role"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  filterable="false" property="name" title="��ɫ����" width="30%">
			      <c:out value='${role.roleName}'/>
			</webui:column>
			<webui:column property="description" title="��ɫ����" styleClass="td_normal" width="60%"/>
			<webui:column property="action" title="����">
			<a href="<c:url value='/sm/dataRole.do?act=view&id=${role.roleId}'/>">�鿴</a>&nbsp;
			<a href="<c:url value='/sm/dataRole.do?act=toEdit&id=${role.roleId}'/>">�༭</a>
			<sm:role id="${role.roleId}" var="flag"/>
			<c:if test="${!flag}">
				    <a href="#" onclick="goDel(<c:out value="${role.roleId }"/>)">ɾ��</a>
			</c:if>

			</webui:column>
  		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_DATA_ROLE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:goCreate();" value="����"/>
		</security:success>
	</security:checkPermission>
</webui:panel>
<script>
	function goCreate(){
		location.href = "<c:url value='/sm/dataRole.do?act=create'/>"
	}
	function goDel(id){
		if(window.confirm("<bean:message key='msg.confirm.delete.data.role'/>")){
			window.location = "<c:url value='/sm/dataRole.do?act=delete&id='/>" + id;
		}
	}
</script>
