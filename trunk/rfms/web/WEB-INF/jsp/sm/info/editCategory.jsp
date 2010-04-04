<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/infoCategory"  onsubmit="return validateInfoCategoryForm(this);">
<html:hidden property="act" value="edit"/>
<input type="hidden" name="id" value="<c:out value='${category.categoryId}'/>"/>
<webui:panel title="sysadmin.title.category.edit" width="95%" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.category.name" required="true" colspan="4">
				<html:text property="category.name" size="25"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.category.desc" colspan="4">
				<html:textarea property="category.desc" styleClass="wid80" rows="3"/>
			</webui:input>
		</tr>
	</webui:formTable>

	<security:checkPermission resourceKey="SM_EDIT_INFO_CATEGORY">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(infoCategoryForm);" value="sysadmin.button.save"/>
		</security:success>
	</security:checkPermission>
	
	<security:checkPermission resourceKey="SM_DELETE_INFO_CATEGORY">
		<security:success>
		<sm:infoCategory id="${category.categoryId}" var="flag"/>
		<c:if test="${flag == false}">
			<webui:linkButton styleClass="clsButtonFace" href="javascript:toDelete();" value="sysadmin.button.delete"/>
		</c:if>
		</security:success>
	</security:checkPermission>
	
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="infoCategoryForm" />
<script>
	function toDelete(){
		if(window.confirm("<bean:message key='msg.confirm.delete.info.category'/>")){
			document.infoCategoryForm.act.value="delete"
			document.infoCategoryForm.submit();
		}
	}
	
	function onBack(){
	    window.location="<c:url value='/sm/infoCategory.do'/>";
	}
</script>