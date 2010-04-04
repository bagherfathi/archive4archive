<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.ticket.ticket_name" required="true">
			<html:hidden property="baseEntity.type" value="1" />
			<html:hidden property="baseEntity.ticketId" />
			<html:hidden property="baseEntity.operatorId"/>
			<html:text property="baseEntity.ticketName" size="25" />
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketSerial" required="false">
			<html:text property="baseEntity.ticketSerial" size="25" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.ticket.type" required="false">
			<html:text property="" size="25" value="ÏÖ½ðÈ¯" disabled="true" />
		</webui:input>
		<webui:input label="label.rfms.ticket.parValue" required="true">
			<html:text property="baseEntity.parValue" size="25"
				onkeydown="onlyNum()" />
	    </webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.parZhekou" required="true">
			<html:text property="baseEntity.parZhekou" size="25"
				onkeydown="onlyNum()" />
	    </webui:input>
	</tr>
	
	<tr>
		<webui:input label="label.rfms.ticket.ticketCount" required="true">
			<html:text property="baseEntity.ticketCount" size="25"
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
				<html:optionsCollection name="enumSet" property="element(STATUS@RFMS_ticket)"/>
			</html:select>
		</webui:input>
		<webui:input label="label.ticket.ohterInfo" required="true">
			<!-- html:text property="baseEntity.ohterInfo " size="25"/-->
	    </webui:input>
		
	<tr>
		<webui:input label="label.rfms.ticket.targetMemberType">
			<html:select property="baseEntity.targetMemberType">
				<html:optionsCollection name="enumSet" property="element(STATUS@RFMS_ticket)"/>
			</html:select>
		</webui:input>
	</tr>
	</tr>
	<tr>
		<webui:input label="label.ticket.useRule" colspan="3">
			<html:textarea property="baseEntity.useRule" styleClass="wid80" rows="3" />
		</webui:input>
	</tr>
</webui:formTable>
