<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<sm:query beanName="ruleManager" methodName="findAllRuleCategory" var="categories"/>

<html:form action="/rule" onsubmit="return validateRuleForm(this)">
<html:hidden property="act" value="edit"/>
<input type="hidden" name="ruleId" value="<c:out value='${rule.ruleId}'/>"/>
<html:hidden property="rule.type"/>
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
						<option value="<c:out value='${category.categoryId}'/>" <c:if test="${rule.categoryId==category.categoryId}">selected</c:if>><c:out value="${category.name}"/></option>
						</c:forEach>
			   		</html:select>
				</webui:input>
				<webui:input label="sysadmin.label.rule.type">
				    <webui:lookup code="type@SM_RULE_INFO" value="${rule.type}"/>
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
		window.location = "<c:url value='/sm/rule.do?act=search'/>";
	}
</script>