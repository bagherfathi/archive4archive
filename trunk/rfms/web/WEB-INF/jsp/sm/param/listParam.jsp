<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<c:if test="${configParamForm.refresh}">
<script>parent.leftFrame.location.reload();</script>
</c:if>

<sm:query beanName="configParamManager" methodName="findParamsByParentId" var="configParams">
   <sm:param value="${configParamForm.parent.configId}" type="java.lang.Long"/>
</sm:query>


<webui:panel title="�ڵ�'${configParamForm.parent.configCode}'�Ĳ����б�" icon="../images/icon_list.gif">
	<webui:table 
		items="${configParams}"
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
			<webui:column  property="configValue" title="ֵ"/>
			<webui:column  property="configName" title="����"/>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="goCreateNode()"  value="�½��ӽڵ�"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="goEditNode()"  value="�༭�ڵ�"/>
</webui:panel><br/>
<webui:panel title="�½�����" icon="../images/icon_list.gif">
<html:form action="param" onsubmit="return validateConfigParamForm(this);">
      <html:hidden property="act" value="save"/>
	  <html:hidden property="parentId" />
	  <html:hidden property="configParam.type" value="0"/>	
	<table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
			<tr>
			    <webui:input label="����" required="true">
					<html:text property="configParam.configCode" size="32" />
				</webui:input>
				 <webui:input label="ֵ" required="true">
					<html:text property="configParam.configValue" size="32"/>
				</webui:input>
		   </tr>
		   <tr>
				<webui:input label="����" colspan="3">
				   <html:text property="configParam.configName" size="32"/>
				</webui:input>
			</tr>
	</table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(configParamForm)"  value="����"/>
</html:form>	
</webui:panel>
 <script>
      function goCreateNode(){
          location.href = "<c:url value='/sm/param.do?parentId=${configParamForm.parent.configId}&act=createNode'/>";
      }
      function goEditNode(){
          location.href = "<c:url value='/sm/param.do?paramId=${configParamForm.parent.configId}&act=editNode'/>";
      }
 </script>  
 <html:javascript  dynamicJavascript="true" staticJavascript="false" formName="configParamForm" /> 
