<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html>
<head>
<base   target="_self">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); 
%>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>商户充值</title>
</head>
<body>

<html:form styleId="merchantForm" action="/merchant.do" method="post">
<input type="hidden" value="payment" name="act"/>

<webui:panel title="商户充值" width="100%"  icon="/images/icon_list.gif">    
<webui:formTable>
      <tr>		
		<webui:input label="充值金额" required="true" colspan="3">
		   <html:hidden property="merchantId" />
		   <html:text property="strAmount" size="25" onkeydown="onlyNum()"/>
		</webui:input>
	  </tr>
      <tr>
	    <webui:input label="备注" colspan="3">
	          <input name="notes" size="45"/>
	    </webui:input>
	  </tr>

    </webui:formTable>

  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm);" value="sysadmin.button.save"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:history.go(-1);" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
 function submitForm(afrom){
  afrom.submit();
 }
</script>
</body>
</html>