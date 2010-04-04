<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="param" styleId="configParamNodeForm" onsubmit="return validateConfigParamNodeForm(this)">
	  <html:hidden property="act" value="saveNode"/>
	  <html:hidden property="validationKey" value="configParamNodeForm"/>
	  <html:hidden property="parentId"/>
	  <html:hidden property="configParam.type" value="1"/>
      <webui:panel title="新建节点" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="代码" required="true">
				   <html:text property="configParam.configCode"/>
				</webui:input>
		   </tr>
			<tr>
				<webui:input label="描述">
					<html:text property="configParam.configName"/>
				</webui:input>
			</tr>
			</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(configParamForm)"  value="保存"/>
        <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="返回"/>
	</webui:panel>
</html:form>
<script>
 function back(){
     location.href="<c:url value="/sm/param.do?parentId=${configParamForm.parentId}"/>";
   }
 </script>  
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="configParamNodeForm" />	
