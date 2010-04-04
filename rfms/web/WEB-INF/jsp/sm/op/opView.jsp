<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
		
<table  width="100%" class="table-bg" cellspacing="1" cellpadding="2">
<tr>
	<webui:input label="登录名" >
		<c:out value='${op.loginName}'/>
	</webui:input>
	<webui:input label="姓名" >
		<c:out value='${op.contact.name}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="联系电话" >
		<c:out value='${op.contact.telephone}'/>
	</webui:input>
	<webui:input label="电子邮箱" >
		<c:out value='${op.email}'/>
	</webui:input>
</tr>
<tr>
     <webui:input label="移动电话" >
		<c:out value='${op.contact.mobilePhone}'/>
	</webui:input>		
	<webui:input label="MSN" >
		<c:out value='${op.msn}'/>
	</webui:input>
</tr>
<tr>
    <webui:input label="邮政编码">
		<c:out value='${op.contact.postCode}'/>
	</webui:input>
	<webui:input label="证件类型">
		 <webui:lookup code="reg_type@SM_OPERATOR" value="${op.regType}"/>
	</webui:input>
</tr>
<tr>
	<webui:input label="所属组织">
         <c:out value='${op.org.orgName}'/>
	</webui:input>
	<webui:input label="证件号码" >
		<c:out value='${op.regNumber}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="状态" >
         <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
	</webui:input>
	<webui:input label="工号"  >
        <c:out value='${op.jobNumber}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="SSO状态" >
         <webui:lookup code="ssoStatus@SM_OPERATOR" value="${op.ssoStatus}"/>
	</webui:input>
	<webui:input label="是否允许访问SSO"  >
        <webui:lookup code="ssoAccessed@SM_OPERATOR" value="${op.ssoAccessed}"/>
	</webui:input>
</tr>
<tr>
<webui:input label="通信地址"  colspan="4">
		<c:out value='${op.contact.address}'/>
	</webui:input>
</tr>
<tr>
	<webui:input label="备注" colspan="4">
		<c:out value='${op.memo}'/>
	</webui:input>
</tr>
</table>
 <table width="100%" border="0" cellspacing="2" cellpadding="2"><tr>
       <td align="right">
	<security:checkPermission resourceKey="SM_EDIT_OP">
      <security:success>
         <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:goURL('edit');" value="编辑操作员"/>
      </security:success>
      </security:checkPermission>
    <security:checkPermission resourceKey="SM_CHANGE_OP_PWD">
      <security:success>
         <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:goURL('changePassword')" value="密码修改"/>
       </security:success>
      </security:checkPermission>
    
    <security:checkPermission resourceKey="SM_DISABLE_OP">
      <security:success>
    <c:if test="${op.status==1}">
       <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:postURL('recover')" value="解禁操作员"/>
    </c:if>
	<c:if test="${op.status==0}" >
	   <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:postURL('disableSingle')" value="禁止操作员"/>
    </c:if>
     </security:success>
      </security:checkPermission>
       </td></tr></table>