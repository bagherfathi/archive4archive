<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<sm:query beanName="ruleManager" methodName="findAllRuleCategory" var="categories"/>
<html:form action="/rule" enctype="multipart/form-data" onsubmit="return confirmUpload(this);">
<html:hidden property="act" value="create"/>
	<webui:panel title="sysadmin.title.new.rule" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.rule.name" required="true">
					<html:text property="rule.name"/>
				</webui:input>
				<webui:input label="sysadmin.label.rule.code" required="true">
					<html:text property="rule.code"/>
				</webui:input>
			</tr>
			<tr>
			    <webui:input label="sysadmin.lable.rule.category" required="true">				
					<html:select property="categoryId">
						<html:option value="" key="sysadmin.label.select"/>
						<c:forEach items="${categories}" var="category">
						<option value="<c:out value='${category.categoryId}'/>" <c:if test="${ruleForm.categoryId==category.categoryId}">selected</c:if>><c:out value="${category.name}"/></option>
						</c:forEach>
			   		</html:select>
				</webui:input>
				<webui:input label="sysadmin.label.rule.type">
					<html:select property="rule.type">
			    		<html:optionsCollection name="enumSet" property="element(type@SM_RULE_INFO)"/>
			    	</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.rule.file" colspan="3">
					<input name="file" size="40" type="file">
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.desc" colspan="3">
					<html:textarea property="rule.desc" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(ruleForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="ruleForm" />
<script>
	function toReturn(){
		window.location = "<c:url value='/sm/rule.do'/>";
	}
	function confirmUpload(f){
        if(document.getElementById('file').value != ''){
            if(!confirm("<bean:message key='maxLengthExplanation'/>")){
               return false;
           }
        }
	    return validateRuleForm(f);
    }
</script>