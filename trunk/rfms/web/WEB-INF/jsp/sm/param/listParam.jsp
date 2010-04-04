<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<c:if test="${configParamForm.refresh}">
<script>parent.leftFrame.location.reload();</script>
</c:if>

<sm:query beanName="configParamManager" methodName="findParamsByParentId" var="configParams">
   <sm:param value="${configParamForm.parent.configId}" type="java.lang.Long"/>
</sm:query>


<webui:panel title="节点'${configParamForm.parent.configCode}'的参数列表" icon="../images/icon_list.gif">
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
			<webui:column  property="configCode" title="代码">
			    <a href="<c:url value="/sm/param.do?act=edit&paramId=${configParam.configId}"/>"><c:out value="${configParam.configCode}"/></a>
			</webui:column>
			<webui:column  property="configValue" title="值"/>
			<webui:column  property="configName" title="描述"/>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="goCreateNode()"  value="新建子节点"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="goEditNode()"  value="编辑节点"/>
</webui:panel><br/>
<webui:panel title="新建参数" icon="../images/icon_list.gif">
<html:form action="param" onsubmit="return validateConfigParamForm(this);">
      <html:hidden property="act" value="save"/>
	  <html:hidden property="parentId" />
	  <html:hidden property="configParam.type" value="0"/>	
	<table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
			<tr>
			    <webui:input label="代码" required="true">
					<html:text property="configParam.configCode" size="32" />
				</webui:input>
				 <webui:input label="值" required="true">
					<html:text property="configParam.configValue" size="32"/>
				</webui:input>
		   </tr>
		   <tr>
				<webui:input label="描述" colspan="3">
				   <html:text property="configParam.configName" size="32"/>
				</webui:input>
			</tr>
	</table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(configParamForm)"  value="保存"/>
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
