<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.ticket.ticket_name" required="true">
			<html:hidden property="baseEntity.sendCount" />
			<html:hidden property="baseEntity.useCount" />
			<html:hidden property="baseEntity.ticketId" />
			<input type="hidden" name="targetMemberTypes" id="targetMemberTypes" />
			<input type="hidden" name="baseEntity.merchantId"
				value="<c:out value='${ticketForm.currentUser.merchantCode}'/>" />
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${ticketForm.currentUser.operatorId}'/>" />
			<html:text property="baseEntity.ticketName" size="25" />
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketSerial" required="true">
			<html:text property="baseEntity.ticketSerial" size="25" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.ticket.type" required="true">
			<html:select property="baseEntity.type" onchange="toChanage(this.value);">
				<html:optionsCollection name="enumSet"
					property="element(TYPE@RFMS_CARD)" />
			</html:select>
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketCount" required="true">
			<html:text property="baseEntity.ticketCount" size="25"
				onkeydown="onlyNum()" />
		</webui:input>
	</tr>
	
	<tr  id="parId">
		<webui:input label="label.rfms.ticket.parValue" required="true" colspan="3">
			<html:text property="baseEntity.parValue" size="25"
				onkeydown="onlyNum();" onchange="changeMemo(1);" />
		</webui:input>
	</tr>
	
	<tr id="zkId" style="display:none">
		<webui:input label="label.rfms.ticket.parZhekou" required="true" colspan="3">
			<html:text property="baseEntity.parZhekou" size="25"
				onkeydown="onlyNum();" onchange="changeMemo(2);"/>
		</webui:input>
	</tr>
	
	<tr >
		<webui:input label="label.rfms.ticket.beginDate" required="true">
			<webui:calendar id="settleStartdate" property="baseEntity.beginDate"
				defaultToday="true" />
		</webui:input>
		<webui:input label="label.rfms.ticket.endDate" required="true">
			<webui:calendar id="settleStartdate1" property="baseEntity.endDate"
				defaultToday="false" readonly="true" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.status">
			<html:select property="baseEntity.status">
				<html:optionsCollection name="enumSet"
					property="element(STATUS@RFMS_CARD)" />
			</html:select>
		</webui:input>
		<webui:input label="label.ticket.ohterInfo" >
			<html:text property="baseEntity.ohterInfo" size="25" />
		</webui:input>

		<tr>
			<webui:input label="label.rfms.ticket.targetMemberType" colspan="3">
				<webui:checkGroup property="baseEntity.targetMemberType"
					styleClass="noborder" beanName="enumSet"
					beanProperty="element(CODE_TYPE@RFMS_CARD)" valueProperty="value"
					labelProperty="label"/>
			</webui:input>
		</tr>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.useRule" required="true" colspan="3">
			<html:textarea property="baseEntity.useRule" styleClass="wid80"
				rows="3" />
		</webui:input>
	</tr>
</webui:formTable>
<script>
	var rad1 = document.ticketForm["baseEntity.targetMemberType"].length;
	for(var i=0;i<rad1;i++){
		 var aa1 =document.ticketForm["baseEntity.targetMemberType"][i].value;
		 var str1 ='<c:out value='${ticketForm.baseEntity.targetMemberType}'/>';
		 if(str1.indexOf(aa1)>-1){
			 document.ticketForm["baseEntity.targetMemberType"][i].checked=true;
		 }
	}
	function toChanage(index){
		if(index==1){
			document.getElementById("parId").style.display="";
			document.getElementById("zkId").style.display="none";
		}else if(index==2){
			document.getElementById("parId").style.display="none";
			document.getElementById("zkId").style.display="";
		}
	}

	function changeMemo(index){
		var parValue =  document.ticketForm["baseEntity.parValue"].value;
		var parZhekou =  document.ticketForm["baseEntity.parZhekou"].value;
		if(index==1){
			document.ticketForm["baseEntity.useRule"].value="抵价券面值"+parValue+"元";
		}else if(index==2){
			document.ticketForm["baseEntity.useRule"].value="折扣券"+parZhekou+"折";
		}
	}
</script>