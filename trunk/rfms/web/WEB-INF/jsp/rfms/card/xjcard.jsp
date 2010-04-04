<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="label.rfms.card.card_name" required="true">
			<html:hidden property="baseEntity.type" value="1" />
			<html:hidden property="baseEntity.status" value="1" />
			<html:hidden property="baseEntity.cardId" />
			<html:hidden property="baseEntity.operatorId"/>
			<html:text property="baseEntity.cardName" size="25" />
		</webui:input>
		<webui:input label="label.rfms.card.card_serial" required="false">
			<html:text property="baseEntity.cardSerial" size="25" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.card.type" required="false">
			<html:text property="" size="25" value="ÏÖ½ðÈ¯" disabled="true" />
		</webui:input>
		<webui:input label="label.rfms.card.par_value1" required="true">
			<html:text property="baseEntity.parValue" size="25"
				onkeydown="onlyNum()" />
	    </webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.sfXf" required="true">
			<webui:radioGroup property="baseEntity.sfXf" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="1" />
		</webui:input>
		<webui:input label="label.rfms.card.sfHy" required="true">
			<webui:radioGroup property="baseEntity.sfHy" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="1" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.fyDate" required="true">
			<webui:calendar id="settleStartdate" property="baseEntity.fyDate"
				defaultToday="true" />
		</webui:input>
		<webui:input label="label.rfms.card.yxDate" required="true">
			<webui:calendar id="settleStartdate1" property="baseEntity.yxDate"
				defaultToday="false" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.sfHf" required="true">
			<webui:radioGroup property="baseEntity.sfHf" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="2" />
		</webui:input>
		<webui:input label="label.rfms.card.sfZf" required="true">
			<webui:radioGroup property="baseEntity.sfZf" styleClass="noborder"
				beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)"
				valueProperty="value" labelProperty="label" defaultValue="2" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.card.xfCs" required="true">
			<html:text property="baseEntity.xfCs" size="25" onkeydown="onlyNum()" />
		</webui:input>
		<webui:input label="label.rfms.card.syCs" required="true">
			<html:text property="baseEntity.syCs" size="25" onkeydown="onlyNum()" />
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.card.cfCs" required="true">
			<html:text property="baseEntity.cfCs" size="25" onkeydown="onlyNum()" />
		</webui:input>
		<webui:input label="label.rfms.card.tqTx" required="true">
			<html:text property="baseEntity.tqTx" size="25" onkeydown="onlyNum()" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.mbHy" required="true" colspan="3">
			<html:text property="baseEntity.mbHy" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.card.status">
			<html:select property="baseEntity.status">
				<html:optionsCollection name="enumSet" property="element(STATUS@RFMS_CARD)"/>
			</html:select>
		</webui:input>

		<webui:input label="label.rfms.card.qtxh">
			<html:text property="baseEntity.qtxh" size="25" />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="sysadmin.label.card.sygz" colspan="3">
			<html:textarea property="baseEntity.sygz" styleClass="wid80" rows="3" />
		</webui:input>
	</tr>
</webui:formTable>
