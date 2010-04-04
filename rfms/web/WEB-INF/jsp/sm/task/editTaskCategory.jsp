<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/taskCategory"  onsubmit="return validateTaskCategoryForm(this);">
<html:hidden property="act" value="edit"/>
<input type="hidden" name="id" value="<c:out value='${category.categoryId}'/>"/>
<webui:panel title="编辑任务分类" width="95%" icon="../images/icon_list.gif">
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
	<sm:taskCategory var="flag" id="${category.categoryId}"></sm:taskCategory>
    <c:if test="${flag == false}">
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:toDelete();" value="sysadmin.button.delete"/>
    </c:if>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="taskCategoryForm" />
<script>
	function toDelete(){
		if(window.confirm("确定要删除所选的分类?")){
			document.taskCategoryForm.act.value="delete"
			document.taskCategoryForm.submit();
		}
	}
	function back(){
		location.href = "<c:url value="/sm/taskCategory.do"/>"
	}
</script>