<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="ticketForm" action="/ticket.do" method="post"
	onsubmit="return validateTicketForm(this);">
	<input type="hidden" value="edit" name="act" />
	<input type="hidden" value="0" name="subflag" />
	<webui:panel title="优惠券管理" width="95%" icon="/images/icon_list.gif">
		<%@include file="/WEB-INF/jsp/rfms/ticket/form.jsp"%>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:submitForm(ticketForm,0);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="ticketForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  
 function submitForm(aform){
	 var type =  document.ticketForm["baseEntity.type"].value;
	 var parValue =  document.ticketForm["baseEntity.parValue"].value;
	 var parZhekou =  document.ticketForm["baseEntity.parZhekou"].value;
	 if(type==1){
		if(parValue==""){
			alert("抵价券面值不能为空");
			return;
		}
	 }else if(type==2){
		 if(parZhekou==""){
			alert("折扣券折扣不能为空");
			return;
		}
	 }
			 
	 if(validateTicketForm(aform)){
	 		var rad = document.ticketForm["baseEntity.targetMemberType"].length;
			var str="";
			for(var i=0;i<rad;i++){
				 var aa =document.ticketForm["baseEntity.targetMemberType"][i].checked;
				if(aa==true){
					str +=document.ticketForm["baseEntity.targetMemberType"][i].value+";";
				}	
			}
			document.getElementById("targetMemberTypes").value=str;

		  aform.act.value="save";
		  loadOn();
		  aform.submit();
	 }
 }

</script>