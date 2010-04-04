<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="param" onsubmit="return validateConfigParamForm(this)">
	  <input type="hidden" name="act" value="update"/>
	  <input type="hidden" name="paramId" value="<c:out value="${configParam.configId}"/>"/>
      <webui:panel title="编辑参数" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="代码" required="true">
				   <html:text property="configParam.configCode"/>
				</webui:input>
		   </tr>
		   <tr>
			    <webui:input label="值" required="true">
					<html:text property="configParam.configValue"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="描述" >
					<html:text property="configParam.configName"/>
				</webui:input>
			</tr>
		  </webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(document.forms.configParamForm)"  value="保存"/>
        <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="返回"/>
	</webui:panel>
</html:form>

<script>
   function back(){
     location.href="<c:url value="/sm/param.do?parentId=${configParam.parentId}"/>";
   }
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="configParamForm" />	
