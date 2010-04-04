<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<c:set var="dataResource" value="${dataResourceForm.dataResource}"/>
<c:if test="${dataResource.dataType == 1}">
<html:form styleId="dataResourceEntrySingleForm" action="/dataResource.do"  method="post" onsubmit="return validateDataResourceEntrySingleForm(this)">
	<html:hidden property="act" value="addEntry"/>
	<input type="hidden" name="id" value="<c:out value='${dataResource.resourceId}'/>" />
	<webui:panel title="sysadmin.label.dataResource.entry.add" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.dataResource.entry.title" required="true">
					<html:text property="entry.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.dataResource.entry.value" required="true">
					<html:text property="maxValue" size="25"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.dataResource.entry.desc" colspan="3">
					<html:textarea property="entry.description" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(dataResourceEntrySingleForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="dataResourceEntrySingleForm" />
</c:if>
<c:if test="${dataResource.dataType == 2}">
<html:form styleId="dataResourceEntryMultiForm" action="/dataResource.do"  method="post" >
	<html:hidden property="act" value="addEntry"/>
	<input type="hidden" name="id" value="<c:out value='${dataResource.resourceId}'/>" />
	<webui:panel title="sysadmin.label.dataResource.entry.add" width="100%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.dataResource.entry.title" required="true" colspan="3">
					<html:text property="entry.title" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.dataResource.entry.min.value" required="true">
					<html:text property="minValue" size="25" />
				</webui:input>
			
				<webui:input label="sysadmin.label.dataResource.entry.max.value" required="true">
					<html:text property="maxValue" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.dataResource.entry.desc" colspan="3">
					<html:textarea property="entry.description" styleClass="wid80" rows="3" />
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmit(dataResourceEntryMultiForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="dataResourceEntryMultiForm" />
<script>
	function onSubmit(input){
		var ss = validateDataResourceEntryMultiForm(input);
		if(ss == true){
			var mx=Number(document.all('maxValue').value);
			var mn=Number(document.all('minValue').value);
			if(mx>mn){
				ss=true;
			}else{
				alert("<bean:message key='msg.alert.dataResource.entry.min.max.error'/>");
				ss=false;
			}
		}
		if(ss==true){
			document.forms.dataResourceEntryMultiForm.submit();
		}
	}
</script>
</c:if>
<script>
	function toReturn(){
		window.location = "<c:url value='/sm/dataResource.do?act=view&id=${dataResource.resourceId}'/>";
	}
</script>