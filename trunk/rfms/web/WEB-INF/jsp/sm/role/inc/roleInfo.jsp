<webui:formTable width="100%">
	<tr>
		<webui:input label="sysadmin.label.role.name" colspan="4">
			<c:out value='${role.roleName}'/>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="sysadmin.label.role.desc" colspan="4">
			<html:textarea property="role.description" styleClass="wid80" rows="3" readonly="true"/>
		</webui:input>
	</tr>
</webui:formTable>