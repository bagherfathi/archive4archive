<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.member.name" required="true">
			<html:hidden property="baseEntity.id" />
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${membertypeForm.currentUser.merchantCode}'/>" />
			<html:text property="baseEntity.name" size="25" />
		</webui:input>
		<webui:input label="label.rfms.member.cardSerial" required="true">
			<html:text property="baseEntity.cardSerial" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.mobile" required="true">
			<html:text property="baseEntity.mobile" size="25" />
		</webui:input>
		<webui:input label="label.rfms.member.name" required="true">
			<html:text property="baseEntity.name" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.zjStatus">
			<html:select property="baseEntity.zjStatus">
				<html:option value="-1">«Î—°‘Ò</html:option>
				<html:optionsCollection name="enumSet"
					property="element(ZJ_STATUS@RFMS_CARD)" />
			</html:select>
		</webui:input>
		<webui:input label="label.rfms.member.zjNumber" required="true">
			<html:text property="baseEntity.zjNumber" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.age" required="true">
			<html:text property="baseEntity.age" size="25" />
		</webui:input>
		<webui:input label="label.rfms.card.birthDate" required="true">
			<webui:calendar id="settleStartdate1" property="baseEntity.birthDate"
				defaultToday="false" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.tel" required="true">
			<html:text property="baseEntity.tel" size="25" />
		</webui:input>
		<webui:input label="label.rfms.member.email" required="true">
			<html:text property="baseEntity.email" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.member.address" required="true">
			<html:text property="baseEntity.address" size="25" />
		</webui:input>
		<webui:input label="label.rfms.member.zipcode" required="true">
			<html:text property="baseEntity.zipcode" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.membertype.name" required="true">
			<html:text property="baseEntity.memberTypeId" size="25" />
		</webui:input>
		<webui:input label="label.rfms.member.status">
			<html:select property="baseEntity.status">
				<html:option value="-1">«Î—°‘Ò</html:option>
				<html:optionsCollection name="enumSet"
					property="element(HY_STATUS@RFMS_CARD)" />
			</html:select>
		</webui:input>
	</tr>
</webui:formTable>
