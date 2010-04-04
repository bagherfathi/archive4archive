<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="memberForm" action="/member.do"
	method="post" onsubmit="return validateMemberForm(this);">
	<input type="hidden" value="edit" name="act" />
	<input type="hidden" value="0" name="subflag" />
	<webui:panel title="会员管理" width="95%" icon="/images/icon_list.gif">
		<%@include file="/WEB-INF/jsp/rfms/member/form.jsp"%>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:submitForm(memberForm,0);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="memberForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  
 function submitForm(aform){
	 if(validateMemberForm(aform)){
		  aform.act.value="save";
		  loadOn();
		  aform.submit();
	 }
 }

</script>