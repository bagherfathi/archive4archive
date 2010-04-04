<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/maintainLog"  onsubmit="return validateMaintainLogForm(this);">
<html:hidden property="act" value="create"/>
<sm:query var="plans" beanName="maintainManager" methodName="findAllMaintainPlans"/>
<webui:panel title="sysadmin.title.new.maintain.log" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.maintain.Log.title" required="true" colspan="3">
				   <html:text property="maintainLog.title" size="25"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.maintain.plan">
					<html:select property="maintainLog.planId">
					    <html:option value="" key="sysadmin.label.select"/>
						<html:options collection="plans" property="planId" labelProperty="name"/>
		   			</html:select>
				</webui:input>
				<webui:input label="sysadmin.label.maintain.Log.type">
					<html:select property="maintainLog.type">
			    		<html:optionsCollection name="enumSet" property="element(log_type@SM_MAINTAIN_PLAN)"/>
			    	</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.maintain.Log.content" colspan="3">
				    <html:textarea property="maintainLog.content" styleClass="wid80" rows="20"/>
				</webui:input>
			</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(maintainLogForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="maintainLogForm"/>