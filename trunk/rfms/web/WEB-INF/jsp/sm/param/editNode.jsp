<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<c:if test="${configParamForm.refresh}">
<script>parent.leftFrame.location.reload();</script>
</c:if>
<html:form action="param" styleId="configParamNodeForm" onsubmit="return validateConfigParamNodeForm(this)">
	  <input type="hidden" name="act" value="updateNode"/>
	  <input type="hidden" name="validationKey" value="configParamNodeForm"/>
	  <input type="hidden" name="paramId" value="<c:out value="${configParam.configId}"/>"/>
      <webui:panel title="�༭�ڵ�" icon="../images/icon_list.gif">
          <webui:formTable>
			<tr>
				<webui:input label="����" required="true">
				   <html:text property="configParam.configCode"/>
				</webui:input>
		   </tr>
			<tr>
				<webui:input label="����">
					<html:text property="configParam.configName"/>
				</webui:input>
			</tr>
	</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(configParamForm)"  value="����"/>
        <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="����"/>
	</webui:panel>
</html:form>
<script>
 function back(){
     location.href="<c:url value="/sm/param.do?parentId=${configParam.configId}"/>";
   }
 </script>  
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="configParamNodeForm" />	
