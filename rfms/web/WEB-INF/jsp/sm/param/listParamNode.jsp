<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="�����ڵ��б�" icon="../images/icon_list.gif">
	<webui:table 
		items="configParams"
		action="${pageContext.request.contextPath}/sm/param.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="configParam"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  property="configCode" title="����">
			    <a href="<c:url value="/sm/param.do?act=edit&paramId=${configParam.configId}"/>"><c:out value="${configParam.configCode}"/></a>
			</webui:column>
			<webui:column  property="configName" title="����"/>
		</webui:row>
	</webui:table>
</webui:panel>
<br/>
<html:form action="param.do" styleId="configParamNodeForm" onsubmit="return validateConfigParamNodeForm(this);">
<webui:panel title="�½������ڵ�" icon="../images/icon_list.gif">
      <html:hidden property="act" value="save"/>
	  <html:hidden property="parentId" />
	  <html:hidden property="validationKey" value="configParamNodeForm"/>
	  <html:hidden property="configParam.type" value="1" />	  	
	<table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
			<tr>
				<webui:input label="����" required="true">
				   <html:text property="configParam.configCode"/>
				</webui:input>
				<webui:input label="����">
					<html:text property="configParam.configName"/>
				</webui:input>
			</tr>
	</table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(configParamForm)"  value="����"/>
</webui:panel>
</html:form>
 <html:javascript  dynamicJavascript="true" staticJavascript="false" formName="configParamNodeForm" /> 
