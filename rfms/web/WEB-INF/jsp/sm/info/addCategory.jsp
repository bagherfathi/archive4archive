<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/infoCategory"  onsubmit="return validateInfoCategoryForm(this);">
<html:hidden property="act" value="create"/>
<webui:panel title="sysadmin.title.category.new" width="95%" icon="../images/icon_list.gif">
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
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(infoCategoryForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>

<script>
	function onBack(){
	    window.location="<c:url value='/sm/infoCategory.do'/>";
	}
</script>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="infoCategoryForm" />