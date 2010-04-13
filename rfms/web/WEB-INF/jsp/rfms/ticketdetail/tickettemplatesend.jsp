<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">

<html:form styleId="ticketdetailForm" action="/ticketsend.do" method="post" enctype="multipart/form-data">
  <input type="hidden" name="type" value="1" />
	<input type="hidden" value="save" name="act" />
	<webui:panel title="优惠券下发" width="95%" icon="/images/icon_list.gif">
		<webui:formTable>
		<tr>
			<webui:input label="label.rfms.ticket.ticket_name" colspan="3">
				<html:text property="rfmsTicket.ticketName" size="25" disabled="true"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="label.rfms.ticket.ticketSerial" colspan="3">
				<html:text property="rfmsTicket.ticketSerial" size="25" disabled="true" />
			</webui:input>
		</tr>
		<tr>
			<webui:input label="label.rfms.ticket.ticketCount" colspan="3">
			<html:text property="rfmsTicket.ticketCount" size="25"disabled="true"/>
		</webui:input>
		</tr>
			<tr>
				<webui:input label="模板文件" colspan="3">
		            <html:file property="strFile"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../member.xls">模板下载</a>
				</webui:input>
			</tr>
		</webui:formTable>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:confirmUpload(ticketdetailForm);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<script>
 function confirmUpload(aform){
     if(document.getElementById('strFile').value == ''){
       		alert("请选择上传文件");
            return;
     }
	 aform.submit();
 }
</script>
