<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="ticketForm" action="/ticket.do" method="post"  onsubmit="return validateTicketForm(this);">
<input type="hidden" value="edit" name="act"/>
<input type="hidden" value="0" name="subflag"/>
<webui:panel title="飞券管理" width="95%"  icon="/images/icon_list.gif">    
<webui:tabContainer id="foo-bar-container">
    <webui:tabPane id="xjInfo" tabTitle="现金券">
	   <%@include file="/WEB-INF/jsp/rfms/ticket/form.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
<br/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(ticketForm,0);" value="sysadmin.button.save"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="ticketForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  
 function submitForm(aform){
	 if(validateTicketForm(aform)){
		  aform.act.value="save";
		  loadOn();
		  aform.submit();
	 }
 }

</script>