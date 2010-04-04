<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        location.href="<c:url value='/sm/group.do?act=create'/>"
    }
    
    function onDelete(){
        var ids = document.getElementById("ids");
        if (!isChecked(document.forms.ec.ids, "��ѡ����")) {
            return;
        } else if (confirm("ȷ��ɾ��ѡ�е���?")) {
                document.forms.ec.setAttribute('action', '<c:url value="/sm/group.do?act=delete"/>');
	            document.forms.ec.submit();
       }
    }
function checkEnter(){
   if(window.event.keyCode==13){
	    loadOn();
	    document.forms.groupForm.submit();
   }
}
document.body.onkeypress= checkEnter;
</script>

<html:form action="group" method="post">
	<webui:panel title="sysadmin.button.search"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="����Ա������">
					<html:text property="groupName" size="25" />
				</webui:input>
				<webui:input label="�����ֹ�˾" colspan="3">
					<sm:orgsList inputName="orgId" selOrgId="${groupForm.orgId}"
						orgType="1" emptyChoice="true" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#"
			onClick="loadOn();document.forms.groupForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
</html:form>
<br />
<webui:panel icon="../images/icon_list.gif" title="����Ա���б�">
	<webui:table items="groups"
		action="${pageContext.request.contextPath}/sm/group.do" var="group"
		width="95%" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" sortable="false">
		<webui:row>
			<webui:column style="text-align:center;" sortable="false" property="checkbox" title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.ids)'/>ȫѡ" filterable="false" width="8%">
				<c:set var="id" value="${group.groupId}" />
				<sm:group id="${id}" var="flag" />
				<input class="noborder" type="checkbox" name="ids"
					value="<c:out value='${group.groupId}'/>"
					<c:if test="${flag}">disabled title="����ɾ��"</c:if> />
			</webui:column>
			<webui:column property="name" title="������"/>
			<webui:column property="ownerOrg.orgName" title="������֯" />
			<webui:column property="description" title="������" styleClass="td_normal"/>
			<webui:column property="action" title="����" width="6%">
				<a href="<c:url value="/sm/group.do?act=view&id=${group.groupId}"/>">�鿴</a>&nbsp;
			   <a
					href="<c:url value="/sm/group.do?act=edit&id=${group.groupId}&from=list"/>">�༭</a>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_CREATE_GROUP">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:onCreate();" value="sysadmin.button.create" />
		</security:success>
	</security:checkPermission>
	<security:checkPermission resourceKey="SM_DELETE_GROUP">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:onDelete();" value="sysadmin.button.delete" />
		</security:success>
	</security:checkPermission>
</webui:panel>
