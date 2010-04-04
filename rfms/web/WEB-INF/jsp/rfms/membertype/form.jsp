<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.membertype.name" required="true"
			colspan="3">
			<html:hidden property="baseEntity.id" />
			<html:text property="baseEntity.name" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="sysadmin.label.membertype.memo" colspan="3">
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${membertypeForm.currentUser.operatorId }'/>" />
			<html:textarea property="baseEntity.memo" styleClass="wid80" rows="3" />
		</webui:input>
	</tr>
</webui:formTable>
