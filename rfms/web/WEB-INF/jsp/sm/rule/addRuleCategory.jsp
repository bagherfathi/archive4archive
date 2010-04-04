<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/ruleCategory"  onsubmit="return validateRuleCategoryForm(this);">
<html:hidden property="act" value="create"/>
<webui:panel title="sysadmin.title.new.rule.category" width="95%" icon="../images/icon_list.gif">
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
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(ruleCategoryForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="ruleCategoryForm" />