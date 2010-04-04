<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<c:choose>
<c:when test="${resource.type == 1}">
<html:form styleId="menuForm" action="/resource.do"  method="post" onsubmit="return validateMenuForm(this)">
<html:hidden property="act" value="edit"/>
<html:hidden property="validationKey" value="menuForm"/>
<html:hidden property="resource.resourceId"/>
	<webui:panel title="sysadmin.label.resource.edit" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.resource.menu.name" required="true">
					<html:text property="resource.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.resource.is.visible">
					<html:radio styleClass="noborder" property="resource.visible" value="true" /><bean:message key="sysadmin.label.true"/>
					<html:radio styleClass="noborder" property="resource.visible" value="false" /><bean:message key="sysadmin.label.false"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.resource.image">
					<html:text property="resource.image" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.resource.url" >
					<html:text property="resource.url" size="25"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(menuForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="menuForm" />
</c:when>
<c:otherwise>
<html:form styleId="buttonForm" action="/resource.do"  method="post" onsubmit="return validateButtonForm(this)">
<html:hidden property="act" value="edit"/>
<html:hidden property="validationKey" value="buttonForm"/>
<html:hidden property="resource.resourceId"/>
	<webui:panel title="sysadmin.label.resource.edit" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.resource.button.name" required="true">
					<html:text property="resource.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.resource.resourceKey" required="true">
					<html:text property="resource.resourceKey" size="25"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(buttonForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="buttonForm" />
</c:otherwise>
</c:choose>
<script>
	function toReturn(){
		window.location = "<c:url value='/sm/resource.do?act=toReturn'/>" +"&resource.resourceId"+"=<c:out value='${resource.resourceId}'/>"+ "&parentResource.resourceId" + "=<c:out value='${parentResource.resourceId}'/>";
	}
	var tree  = parent.leftFrame.tree;
       tree.selectItem("<c:out value='${resource.resourceId}'/>",false);
       tree.openItem("<c:out value='${resource.resourceId}'/>");
</script>