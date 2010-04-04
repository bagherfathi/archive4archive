<%@ page contentType="text/html; charset=GBK" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/tld.inc" %><%--
--%><c:set var="jspBegin"><%=System.currentTimeMillis()%></c:set><%--
--%>
<html>
<head>
<base   target="_self">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); 
%>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>分配数据</title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
</head>
<body align="center">
<table width="100%">
<tr><td align="center">
<html:form styleId="merchantForm" action="/merchant.do" method="post">
<input type="hidden" value="saveStep" name="act"/>
<html:hidden property="stepId" />
<webui:panel title="title.rfms.merchant_step.edit" width="100%"  icon="/images/icon_list.gif">    
<webui:formTable>
      <tr>		
		<webui:input label="label.rfms.commision_step.min_charge" required="true">
		   <html:hidden property="step.commisionStepId"/>
		   <input type="hidden" name="step.merchantId" value="<c:out value='${merchantForm.id }'/>"/>
		   <html:text property="step.minCharge" size="25" onkeydown="onlyNum()"/>
		</webui:input>
		<webui:input label="label.rfms.commision_step.max_charge" required="true">
		   <html:text property="step.maxCharge" size="25" onkeydown="onlyNum()"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.commision_step.commision_charge" required="true" colspan="3">
	        <html:text property="step.commisionCharge" size="25" onkeydown="onlyNum()"/>
	    </webui:input> 
	  </tr>
</webui:formTable>

  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm);" value="sysadmin.button.add"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onClose();" value="sysadmin.button.close"/>
</webui:panel>
<br/>
<webui:panel title="title.rfms.merchant_step.list" width="100%"  icon="/images/icon_list.gif"> 
<webui:table items="${merchantSteps }" action="${pageContext.request.contextPath}/sm/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_pos.list" var="step" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm" form="merchantForm">
		<webui:row>
			<webui:column property="minCharge" title="label.rfms.commision_step.min_charge" styleClass="td_normal"/>
			<webui:column  property="maxCharge" title="label.rfms.commision_step.max_charge">
			</webui:column>
			<webui:column property="commisionCharge" title="label.rfms.commision_step.commision_charge">
			</webui:column>
			<webui:column property="dd" title="title.rfms.common.operater">
				<a
					href="javascript:editStep(${step.commisionStepId})"><bean:message key="sysadmin.button.edit"/></a>&nbsp;
			</webui:column>
			
		</webui:row>
	</webui:table>
	</webui:panel>
</html:form>
</td>
</tr></table>
<script>
 function submitForm(aform){
  //loadOn();
  aform.act.value="saveStep"
  aform.submit();
 }
 function editStep(stepId){
  //loadOn();
  document.merchantForm.act.value="editStep"
  document.merchantForm.stepId.value=stepId;
  document.merchantForm.submit();
 }
</script>
</body>
</html>