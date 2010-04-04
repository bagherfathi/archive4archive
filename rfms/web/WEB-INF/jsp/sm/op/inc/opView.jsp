<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
		
<table  width="100%" class="table-bg" cellspacing="1" cellpadding="2">
<tr>
	<webui:input label="sysadmin.label.loginName" >
		<c:out value='${op.loginName}'/>
	</webui:input>
	<webui:input label="sysadmin.label.name" >
		<c:out value='${op.contact.name}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="sysadmin.label.phone" >
		<c:out value='${op.contact.telephone}'/>
	</webui:input>
	<webui:input label="sysadmin.label.email" >
		<c:out value='${op.email}'/>
	</webui:input>
</tr>
<tr>
     <webui:input label="sysadmin.label.mobile" >
		<c:out value='${op.contact.mobilePhone}'/>
	</webui:input>		
	<webui:input label="MSN" >
		<c:out value='${op.msn}'/>
	</webui:input>
</tr>
<tr>
    <webui:input label="sysadmin.label.postCode">
		<c:out value='${op.contact.postCode}'/>
	</webui:input>
	<webui:input label="sysadmin.label.regType">
		 <webui:lookup code="reg_type@SM_OPERATOR" value="${op.regType}"/>
	</webui:input>
</tr>
<tr>
	<webui:input label="sysadmin.label.op.org">
         <c:out value='${op.org.orgName}'/>
	</webui:input>
	<webui:input label="sysadmin.label.regNumber" >
		<c:out value='${op.regNumber}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="sysadmin.title.common.status" >
         <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
	</webui:input>
	<webui:input label="sysadmin.label.op.jobNumber"  >
        <c:out value='${op.jobNumber}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="sysadmin.title.common.SSOstatus" >
         <webui:lookup code="ssoStatus@SM_OPERATOR" value="${op.ssoStatus}"/>
	</webui:input>
	<webui:input label="sysadmin.title.common.ssoAccessed"  >
        <webui:lookup code="ssoAccessed@SM_OPERATOR" value="${op.ssoAccessed}"/>
	</webui:input>
</tr>
<tr>
<webui:input label="sysadmin.label.address"  colspan="4">
		<c:out value='${op.contact.address}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="sysadmin.label.op.memo" colspan="4">
		<c:out value='${op.memo}'/>
	</webui:input>
</tr>
</table>
 <table width="100%" border="0" cellspacing="2" cellpadding="2"><tr>
       <td align="right">
	<security:checkPermission resourceKey="SM_EDIT_OP">
      <security:success>
         <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:goURL('edit');" value="sysadmin.button.edit"/>
      </security:success>
      </security:checkPermission>
    <security:checkPermission resourceKey="SM_CHANGE_OP_PWD">
      <security:success>
         <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:goURL('changePassword')" value="sysadmin.button.changePassword"/>
       </security:success>
      </security:checkPermission>
    
    <security:checkPermission resourceKey="SM_DISABLE_OP">
      <security:success>
    <c:if test="${op.status==1}">
       <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:postURL('recover')" value="sysadmin.button.recover"/>
    </c:if>
	<c:if test="${op.status==0}" >
	   <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:postURL('disableSingle')" value="sysadmin.button.disableSingle"/>
    </c:if>
     </security:success>
      </security:checkPermission>
       </td></tr></table>