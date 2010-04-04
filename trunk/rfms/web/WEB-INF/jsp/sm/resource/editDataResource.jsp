<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/dynamic.js"/>"></script>
<html:form action="/dataResource.do" onsubmit="return validateDataResourceForm(this);">
	<html:hidden property="act" value="edit"/>
	<input type="hidden" name="id" value="<c:out value='${dataResource.resourceId}'/>"/>
	<webui:panel title="sysadmin.label.dataResource.edit" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.dataResource.name" required="true">
					<html:text property="dataResource.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.dataResource.code" required="true">
					<c:out value="${dataResource.code}" />
					<html:hidden property="dataResource.code"/>
				</webui:input>
			</tr>
			<tr>
			    <%--
				<webui:input label="sysadmin.label.dataResource.type" required="true">
				<html:select property="dataResource.dataType" disabled="true">
			    	<html:optionsCollection name="enumSet" property="element(data_type@SM_PRIV_DATA_RESOURCE)"/>
			  	</html:select>
				</webui:input>
				<webui:input label="sysadmin.label.dataResource.assign.type" required="true">
				<html:select property="dataResource.assignType" disabled="true">
			    	<html:optionsCollection name="enumSet" property="element(assign_type@SM_PRIV_DATA_RESOURCE)"/>
			  	</html:select>
				</webui:input>
				--%>
				<webui:input label="sysadmin.label.dataResource.type">
			  	    <webui:lookup code="data_type@SM_DATA_RESOURCE" value="${dataResource.dataType}"/>
			  	    <html:hidden property="dataResource.dataType"/>
				</webui:input>
				<webui:input label="sysadmin.label.dataResource.assign.type" required="true">
			  	    <webui:lookup code="assign_type@SM_DATA_RESOURCE" value="${dataResource.assignType}"/>
			  	    <html:hidden property="dataResource.assignType"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.dataResource.desc" colspan="3">
					<html:textarea property="dataResource.description" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(dataResourceForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="dataResourceForm" />
<script>
	function toReturn(){
		location.href="<c:url value="/sm/dataResource.do"/>"
	}
</script>