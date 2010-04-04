<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form styleId="buttonForm" action="/resource.do"  method="post" onsubmit="return validateButtonForm(this)">
	<html:hidden property="act" value="addButton"/>
	<html:hidden property="validationKey" value="buttonForm"/>
	<html:hidden property="parentResource.resourceId"/>
	<html:hidden property="resource.type" value="2"/>
	<webui:panel title="新增'${parentResource.title }'的按钮" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.resource.button.name" required="true">
					<html:text property="resource.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.resource.resourceKey" required="true">
					<html:text property="resource.resourceKey" size="25"/>
				</webui:input>
			</tr>
			<tr>
			    <webui:input label="sysadmin.label.resource.parent.menu" colspan="3">
			    	<html:text property="parentResource.title" readonly="true" size="25"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(buttonForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="buttonForm" />
<script>
	function toReturn(){
	    <c:if test="${empty param.currRId}">
		window.location = "<c:url value='/sm/resource.do?act=view&resource.resourceId=${parentResource.resourceId}'/>";
		</c:if>
		<c:if test="${!empty param.currRId}">
		window.location = "<c:url value='/sm/resource.do?act=view&resource.resourceId=${param.currRId}'/>";
		</c:if>
	}
</script>