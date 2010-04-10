<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.member.mobile" required="true">
			<html:hidden property="baseEntity.id" />
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${membertypeForm.currentUser.operatorId}'/>" />
			<html:text property="baseEntity.mobile" size="25" />
		</webui:input>
		<webui:input label="label.rfms.member.name" required="true">
			<html:text property="baseEntity.name" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.password" required="true">
			<html:password property="baseEntity.pwd" size="25" />
		
		</webui:input>
		<webui:input label="label.rfms.member.type" required="true">
			<webui:radioGroup property="baseEntity.status" styleClass="noborder"
				beanName="enumSet" beanProperty="element(CODE_TYPE@RFMS_CARD)"
				valueProperty="value" labelProperty="label" defaultValue="1" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.sex">
			<webui:radioGroup property="baseEntity.sex" styleClass="noborder"
				beanName="enumSet" beanProperty="element(SEX@RFMS_CARD)"
				valueProperty="value" labelProperty="label" />
		</webui:input>

		<webui:input label="label.rfms.member.address">
			<html:text property="baseEntity.address" size="25" />
		</webui:input>
	</tr>
</webui:formTable>
