<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/ruleCategory"  onsubmit="return validateRuleCategoryForm(this);">
<html:hidden property="act" value="edit"/>
<input type="hidden" name="id" value="<c:out value='${category.categoryId}'/>"/>
<webui:panel title="sysadmin.title.edit.rule.category" width="95%" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.rule.category.name" required="true" colspan="4">
			   <html:text property="category.name"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.rule.category.desc" colspan="4">
			    <html:textarea property="category.desc" styleClass="wid80" rows="3"/>
			</webui:input>
		</tr>
	</webui:formTable>
	<security:checkPermission resourceKey="SM_EDIT_RULE_CATEGORY">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(ruleCategoryForm);" value="sysadmin.button.save"/>
		</security:success>
	</security:checkPermission>
	
	<security:checkPermission resourceKey="SM_DELETE_RULE_CATEGORY">
		<security:success>
		<sm:ruleCategory id="${category.categoryId}" var="flag"/>
		<c:if test="${flag == false}">
			<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:toDelete();" value="sysadmin.button.delete"/>
		</c:if>
		</security:success>
	</security:checkPermission>
	
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="ruleCategoryForm" />
<script>
	function toDelete(){
		if(window.confirm("<bean:message key='msg.confirm.delete.rule.category'/>")){
			document.ruleCategoryForm.act.value="delete"
			document.ruleCategoryForm.submit();
		}
	}
</script>