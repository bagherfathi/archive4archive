<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/maintainLog"  onsubmit="return validateMaintainLogForm(this);">
<html:hidden property="act" value="edit"/>
<sm:query beanName="maintainManager" methodName="findAllMaintainPlans" var="plans"/>
<webui:panel title="sysadmin.title.edit.maintain.log" width="95%" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.maintain.Log.title" required="true" colspan="3">
				<html:text property="maintainLog.title" size="25"/>
				<html:hidden property="maintainLog.logId"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.maintain.plan">
				<html:select property="maintainLog.planId">
					<html:option value="" key="sysadmin.label.info.search.none"/>
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
	<security:checkPermission resourceKey="SM_EDIT_MAINTAIN_LOG">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(maintainLogForm);" value="sysadmin.button.update"/>
		</security:success>
	</security:checkPermission>
	<security:checkPermission resourceKey="SM_DELETE_MAINTAIN_LOG">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:toDelete();" value="sysadmin.button.delete"/>
		</security:success>
	</security:checkPermission>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="maintainLogForm"/>
<script>
	function toDelete(){
		if(window.confirm("<bean:message key='msg.confirm.delete.maintain.log'/>")){
			document.maintainLogForm.act.value="delete";
			document.maintainLogForm.submit();
		}
	}
</script>