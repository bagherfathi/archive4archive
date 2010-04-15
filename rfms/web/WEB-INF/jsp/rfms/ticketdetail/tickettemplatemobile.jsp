<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">

<script type="text/javascript">

String.prototype.Trim = function() {  
	  var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);  
	  return (m == null) ? "" : m[1];  
}

String.prototype.isMobile = function() {  
  return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));  
}

function sendTicketMember(aform) {
	var mobile=document.ticketdetailForm["mobile"].value;
	 if (mobile.isMobile())  {  
		 mobile=mobile.Trim();  
	  } else {  
	   	 alert("请输入正确的手机号码!");
	   	 return;
	  }  
	 aform.submit();
}

</script>
<html:form styleId="ticketdetailForm" action="/ticketsend.do"
	method="post" enctype="multipart/form-data">
	<input type="hidden" name="type" value="3" />
	<input type="hidden" value="save" name="act" />
	<webui:panel title="优惠券下发" width="95%" icon="/images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="label.rfms.ticket.ticket_name" colspan="3">
					<html:text property="rfmsTicket.ticketName" size="25"
						disabled="true" />
					<input type="hidden" name="ticketSerial" size="25"
						value="<c:out value='${ticketdetailForm.rfmsTicket.ticketSerial}'/>" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.ticket.ticketSerial" colspan="3">
					<html:text property="rfmsTicket.ticketSerial" size="25"
						disabled="true" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.ticket.ticketCount" colspan="3">
					<html:text property="rfmsTicket.ticketCount" size="25"
						disabled="true" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.member.mobile" required="true">
					<input name="mobile" size="25" />
				</webui:input>
			</tr>

		</webui:formTable>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:sendTicketMember(ticketdetailForm);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>

