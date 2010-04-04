<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="param" onsubmit="return validateConfigParamForm(this)">
	  <input type="hidden" name="act" value="update"/>
	  <input type="hidden" name="paramId" value="<c:out value="${configParam.configId}"/>"/>
      <webui:panel title="�༭����" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="����" required="true">
				   <html:text property="configParam.configCode"/>
				</webui:input>
		   </tr>
		   <tr>
			    <webui:input label="ֵ" required="true">
					<html:text property="configParam.configValue"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="����" >
					<html:text property="configParam.configName"/>
				</webui:input>
			</tr>
		  </webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(document.forms.configParamForm)"  value="����"/>
        <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="����"/>
	</webui:panel>
</html:form>

<script>
   function back(){
     location.href="<c:url value="/sm/param.do?parentId=${configParam.parentId}"/>";
   }
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="configParamForm" />	
