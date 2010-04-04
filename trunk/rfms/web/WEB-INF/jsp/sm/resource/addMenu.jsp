<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form styleId="menuForm" action="/resource.do"  method="post" onsubmit="return validateMenuForm(this)">
	<html:hidden property="act" value="add"/>
	<html:hidden property="validationKey" value="menuForm"/>
	<html:hidden property="parentResource.resourceId" />
	<html:hidden property="resource.type" value="1"/>
	<webui:panel title="添加'${parentResource.title }'的子菜单" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.resource.menu.name" required="true">
				   <html:text property="resource.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.resource.is.visible" >
					<html:radio styleClass="noborder" property="resource.visible" value="true" /><bean:message key="sysadmin.label.true"/>
					<html:radio styleClass="noborder" property="resource.visible" value="false" /><bean:message key="sysadmin.label.false"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.resource.image" >
					<html:text property="resource.image" size="25" value="images/webui/menu_1.gif"/>
				</webui:input>
				<webui:input label="sysadmin.label.resource.url" >
					<html:text property="resource.url" size="25" value="/"/>
				</webui:input>
			</tr>
			<tr>
			    <webui:input label="sysadmin.label.resource.parent.menu" colspan="3">
			    	<html:text property="parentResource.title" readonly="true" size="25"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(menuForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="menuForm" />
<script>
	function toReturn(){
	    <c:if test="${empty parentResource.resourceId}">
	        window.location = "<c:url value="/sm/resource.do?act=list"/>";
	    </c:if>
	    
	     <c:if test="${!empty parentResource.resourceId}">
	         <c:if test="${empty param.currRId}">
			    window.location = "<c:url value="/sm/resource.do?act=view&resource.resourceId=${parentResource.resourceId}"/>";
			 </c:if>
			 <c:if test="${!empty param.currRId}">
			    <c:if test="${param.currRId == 0}">
			    window.location = "<c:url value="/sm/resource.do?act=list"/>";
			    </c:if>
			    <c:if test="${param.currRId != '0'}">
			    window.location = "<c:url value="/sm/resource.do?act=view&resource.resourceId=${param.currRId}"/>";
			    </c:if>
			 </c:if>
		</c:if>
	}
</script>