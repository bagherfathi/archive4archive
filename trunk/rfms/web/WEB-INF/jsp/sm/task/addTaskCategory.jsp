<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/taskCategory"  onsubmit="return validateTaskCategoryForm(this);">
<html:hidden property="act" value="create"/>
<webui:panel title="新增任务分类" width="95%" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="分类名称" required="true" colspan="4">
				<html:text property="category.name" size="25"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="分类描述" colspan="4">
				<html:textarea property="category.desc" styleClass="wid80" rows="3"/>
			</webui:input>
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(taskCategoryForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
  function back(){
       location.href="<c:url value='/sm/taskCategory.do'/>";
  }
</script>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="taskCategoryForm" />