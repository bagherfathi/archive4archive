<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/maintainPlan"  onsubmit="return validateMaintainPlanForm(this);">
<html:hidden property="act" value="create"/>
<webui:panel title="sysadmin.title.new.maintain.plan" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.maintain.plan.name" required="true" colspan="3">
				   <html:text property="maintainPlan.name" size="25"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.maintain.plan.cycle">
					 <html:text property="maintainPlan.cycle" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.maintain.plan.cycle.unit">
					<html:select property="maintainPlan.cycleUnit">
			    		<html:optionsCollection name="enumSet" property="element(cycle_unit@SM_MAINTAIN_PLAN)"/>
			    	</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.maintain.plan.desc" colspan="3">
				    <html:textarea property="maintainPlan.desc" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(maintainPlanForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="maintainPlanForm" />