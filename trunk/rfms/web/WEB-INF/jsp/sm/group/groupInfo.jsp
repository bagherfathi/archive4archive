<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
	<tr>
		<webui:input label="组名称">
			<c:out value='${group.name}' />
		</webui:input>
		<webui:input label="所属分公司">
			<c:out value='${group.ownerOrg.orgName}' />
		</webui:input>
	</tr>
	<tr>
		<webui:input label="组描述" colspan="4">
			<c:out value='${group.description}' />
		</webui:input>
	</tr>
</webui:formTable>
<table width="90%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td align="right"><security:checkPermission
			resourceKey="SM_EDIT_GROUP">
			<security:success>
				<webui:linkButton standOnly="true" styleClass="clsButtonFace"
					href="javascript:goURL('edit');" value="编辑" />
			</security:success>
		</security:checkPermission>
		</td>
	</tr>
</table>
