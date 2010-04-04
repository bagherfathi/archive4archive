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
<input type="hidden" value="savePos" name="act"/>
<html:hidden property="posId" />
<html:hidden property="branchId" />
<webui:panel title="title.rfms.merchant_pos.edit" width="100%"  icon="/images/icon_list.gif">    
<webui:formTable>
      <tr>		
		<webui:input label="label.rfms.merchant_pos.sys_pos_code" required="true" colspan="3">
		   <html:hidden property="pos.merchantPosId" />
		   <input type="hidden" name="pos.merchantBranchId" value="<c:out value='${merchantForm.branchId }'/>"/>
		   <html:text property="pos.sysPosCode" size="25" readonly="false"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant_pos.owner" required="true">
	        <webui:radioGroup property="pos.owner"styleClass="noborder" beanName="enumSet"
				beanProperty="element(owner@RFMS_MERCHANT_POS)" valueProperty="value"
				labelProperty="label"  defaultValue="1"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant_pos.pos_type" required="true">
		    <webui:radioGroup property="pos.posType"styleClass="noborder" beanName="enumSet"
				beanProperty="element(pos_type@RFMS_MERCHANT_POS)" valueProperty="value"
				labelProperty="label"  defaultValue="1"/>
		</webui:input>
	  </tr>
</webui:formTable>

  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm);" value="sysadmin.button.add"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onClose();" value="sysadmin.button.close"/>
</webui:panel>
<br/>
<webui:panel title="title.rfms.merchant_pos.list" width="100%"  icon="/images/icon_list.gif"> 
<webui:table items="${merchantPoss }" action="${pageContext.request.contextPath}/sm/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_pos.list" var="pos" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm" form="merchantForm">
		<webui:row>
			<webui:column property="sysPosCode" title="label.rfms.merchant_pos.sys_pos_code" styleClass="td_normal"/>
			<webui:column  property="owner" title="label.rfms.merchant_pos.owner">
			<webui:lookup code="owner@RFMS_MERCHANT_POS" value="${pos.posType}" />
			</webui:column>
			<webui:column property="posType" title="label.rfms.merchant_pos.pos_type">
			<webui:lookup code="pos_type@RFMS_MERCHANT_POS" value="${pos.posType}" />
			</webui:column>
			<webui:column property="dd" title="title.rfms.common.operater">
				<a
					href="javascript:editPos(${pos.merchantBranchId},${pos.id})"><bean:message key="sysadmin.button.edit"/></a>&nbsp;
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
  aform.act.value="savePos"
  aform.submit();
 }
 function editPos(branchId,posId){
  //loadOn();
  document.merchantForm.act.value="editPos"
  //document.merchantForm.branchId.value=branchId;
  document.merchantForm.posId.value=posId;
  document.merchantForm.submit();
 }
</script>
</body>
</html>