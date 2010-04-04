<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.membertype.name" required="true" >
			<html:hidden property="baseEntity.id" />
			<html:text property="baseEntity.name" size="25" />
		</webui:input>
		<webui:input label="label.rfms.membertype.name" required="true" >
			<html:hidden property="baseEntity.id" />
			<html:text property="baseEntity.name" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="sysadmin.label.membertype.memo" colspan="3">
			<html:textarea property="baseEntity.memo" styleClass="wid80" rows="3" />
		</webui:input>
	</tr>
</webui:formTable>
