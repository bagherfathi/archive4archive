<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.card.card_name" required="true">
			<html:hidden property="card.operatorId" value="${cardForm.currentUser.operatorId}" />
			<html:text property="card.cardName" size="25" />
		</webui:input>
		<webui:input label="label.rfms.card.card_serial" required="false">
			<html:text property="card.cardSerial" size="25" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.card.type" required="false">
			<html:text property="card.type" size="25" value="ÏÖ½ðÈ¯" disabled="true" />
		</webui:input>
		<webui:input label="label.rfms.card.par_value1" required="true">
			<html:text property="card.parValue" size="25"
				onkeydown="onlyNum()" />
	    </webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.sfXf" required="true">
			<webui:radioGroup property="card.sfXf" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="1" />
		</webui:input>
		<webui:input label="label.rfms.card.sfHy" required="true">
			<webui:radioGroup property="card.sfHy" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="1" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.fyDate" required="true">
			<webui:calendar id="settleStartdate" property="card.fyDate"
				defaultToday="true" />
		</webui:input>
		<webui:input label="label.rfms.card.yxDate" required="true">
			<webui:calendar id="settleStartdate1" property="card.yxDate"
				defaultToday="false" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.sfHf" required="true">
			<webui:radioGroup property="card.sfHf" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="2" />
		</webui:input>
		<webui:input label="label.rfms.card.sfZf" required="true">
			<webui:radioGroup property="card.sfZf" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="2" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.card.xfCs" required="true">
			<html:text property="card.xfCs" size="25" onkeydown="onlyNum()" />
		</webui:input>
		<webui:input label="label.rfms.card.syCs" required="true">
			<html:text property="card.syCs" size="25" onkeydown="onlyNum()" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.card.cfCs" required="true">
			<html:text property="card.cfCs" size="25" onkeydown="onlyNum()" />
		</webui:input>
		<webui:input label="label.rfms.card.tqTx" required="true">
			<html:text property="card.tqTx" size="25" onkeydown="onlyNum()" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.mbHy" required="true" colspan="3">
			<html:text property="card.mbHy" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.status">
			<html:select property="card.status">
				<html:optionsCollection name="enumSet" property="element(STATUS@RFMS_CARD)"/>
			</html:select>
		</webui:input>

		<webui:input label="label.rfms.card.qtxh">
			<html:text property="card.qtxh" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="sysadmin.label.card.sygz" colspan="3">
			<html:textarea property="card.sygz" styleClass="wid80" rows="3" />
		</webui:input>
	</tr>
</webui:formTable>
