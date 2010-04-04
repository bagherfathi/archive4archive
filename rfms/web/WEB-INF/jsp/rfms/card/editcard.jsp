<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="cardForm" action="/card.do" method="post"  onsubmit="return validateCardForm(this);">
<input type="hidden" value="edit" name="act"/>
<input type="hidden" value="0" name="subflag"/>
<webui:panel title="·ÉÈ¯¹ÜÀí" width="95%"  icon="/images/icon_list.gif">    
<webui:tabContainer id="foo-bar-container">
    <webui:tabPane id="xjInfo" tabTitle="ÏÖ½ðÈ¯">
	   <%@include file="/WEB-INF/jsp/rfms/card/xjcard.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="vipInfo" tabTitle="VIP¿¨">
	</webui:tabPane>
	<webui:tabPane id="zkInfo" tabTitle="ÕÛ¿ÛÈ¯">
	  
	</webui:tabPane>
</webui:tabContainer>
<br/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(cardForm,0);" value="sysadmin.button.save"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="cardForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  
 function submitForm(aform){
	 if(validateCardForm(aform)){
		  aform.act.value="save";
		  loadOn();
		  aform.submit();
	 }
 }

</script>