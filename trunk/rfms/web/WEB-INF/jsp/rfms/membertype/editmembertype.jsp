<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="membertypeForm" action="/membertype.do"
	method="post" onsubmit="return validateMembertypeForm(this);">
	<input type="hidden" value="edit" name="act" />
	<input type="hidden" value="0" name="subflag" />
	<webui:panel title="会员类型管理" width="95%" icon="/images/icon_list.gif">
		<%@include file="/WEB-INF/jsp/rfms/membertype/form.jsp"%>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:submitForm(membertypeForm,0);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="membertypeForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  
 function submitForm(aform){
	 if(validateMembertypeForm(aform)){
		  aform.act.value="save";
		  loadOn();
		  aform.submit();
	 }
 }

</script>