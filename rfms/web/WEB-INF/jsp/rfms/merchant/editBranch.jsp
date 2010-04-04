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
<title>编辑门店</title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
</head>
<body>

<html:form styleId="merchantForm" action="/merchant.do" method="post">
<input type="hidden" value="saveMerchantBranch" name="act"/>

<webui:panel title="title.rfms.merchant_branch.edit" width="100%"  icon="/images/icon_list.gif">    
<webui:formTable>
      <tr>		
		<webui:input label="label.rfms.merchant_branch.branch_name" required="true" colspan="3">
		   <html:hidden property="branch.merchantBranchId" />
		   <html:text property="branch.branchName" size="25"/>
		</webui:input>
	  </tr>
      <tr>
	    <webui:input label="label.rfms.merchant_branch.branch_address" required="true" colspan="3">
	          <html:text property="branch.branchAddress" size="25"/>
	    </webui:input>
	
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant_branch.branch_contact_name" required="true">
	       <html:text property="branch.branchContactName" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant_branch.branch_phone" required="true">
		   <html:text property="branch.branchPhone" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant_branch.pos_num" required="true">
		    <html:text property="branch.posNum" size="25" onkeydown="onlyNum()"/>
		</webui:input>
		<webui:input label="label.rfms.merchant_branch.pos_type" required="true">
		   <webui:radioGroup property="branch.posType"styleClass="noborder" beanName="enumSet"
				beanProperty="element(pos_type@RFMS_MERCHANT_BRANCH)" valueProperty="value"
				labelProperty="label"  defaultValue="1"/>
				<html:hidden property="branch.sysMerchantCode"/>
				<c:if test="${merchantForm.branch.sysJionState==null }">
				<html:hidden property="branch.sysJionState" value="0"/>
				</c:if>
				<c:if test="${merchantForm.branch.sysJionState!=null }">
				<html:hidden property="branch.sysJionState" />
				</c:if>
		</webui:input>
	  </tr>

    </webui:formTable>

  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm);" value="sysadmin.button.save"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onClose();" value="sysadmin.button.close"/>
</webui:panel>
</html:form>
<script>
 function submitForm(afrom){
  //loadOn();
  afrom.submit();
 }


</script>
</body>
</html>