<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.ticket.ticket_name" required="true">
			<html:hidden property="baseEntity.sendCount" />
			<html:hidden property="baseEntity.useCount" />
			<html:hidden property="baseEntity.ticketId" />
			<input type="hidden" name="baseEntity.merchantId"
				value="<c:out value='${ticketForm.currentUser.merchantCode}'/>" />
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${ticketForm.currentUser.operatorId}'/>" />
			<html:text property="baseEntity.ticketName" size="25" />
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketSerial" >
			<html:text property="baseEntity.ticketSerial" size="25" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.ticket.type" required="false">
			<html:select property="baseEntity.type">
				<html:optionsCollection name="enumSet"
					property="element(TYPE@RFMS_CARD)" />
			</html:select>
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketCount" required="true">
			<html:text property="baseEntity.ticketCount" size="25"
				onkeydown="onlyNum()" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.parValue" required="true">
			<html:text property="baseEntity.parValue" size="25"
				onkeydown="onlyNum()" />
		</webui:input>
		<webui:input label="label.rfms.ticket.parZhekou" required="true">
			<html:text property="baseEntity.parZhekou" size="25"
				onkeydown="onlyNum()" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.beginDate" required="true">
			<webui:calendar id="settleStartdate" property="baseEntity.beginDate"
				defaultToday="true" />
		</webui:input>
		<webui:input label="label.rfms.ticket.endDate" required="true">
			<webui:calendar id="settleStartdate1" property="baseEntity.endDate"
				defaultToday="false" />
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
					labelProperty="label" />
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
