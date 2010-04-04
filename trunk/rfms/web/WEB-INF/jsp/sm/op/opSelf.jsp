<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="opSelf" method="post" onsubmit="return onSave(this);">
	<input type="hidden" name="act" value="save" />
	<webui:panel title="�༭�û�" width="100%">
	<webui:formTable>
			<tr>
				<webui:input label="������ԭ����" colspan="4">
                     <input type="password"  name="oldPassword" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="������������">
                     <input type="password" name="newPassword" size="25" />
				</webui:input>
				<webui:input label="��ȷ��������">
                     <input type="password" name="newPassword2" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
	<webui:formTable>
			<tr>
				<webui:input label="��¼��">
					<c:out value='${opSelfForm.opDTO.loginName}' />
				</webui:input>
				<webui:input label="�ƶ��绰">
					<html:text property="opDTO.contact.mobilePhone" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="����" required="true">
					<html:text property="opDTO.contact.name" size="25" />
				</webui:input>
				<webui:input label="��ϵ�绰">
					<html:text property="opDTO.contact.telephone" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="��������" required="true">
					<html:text property="opDTO.email" size="25" />
				</webui:input>
				<webui:input label="MSN">
					<html:text property="opDTO.msn" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="��������" colspan="4">
					<html:text property="opDTO.contact.postCode" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="ͨ�ŵ�ַ" colspan="4">
					<html:text size="64" property="opDTO.contact.address" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="��ע" colspan="4">
					<html:textarea property="opDTO.memo" styleClass="wid80" rows="3" />
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:submitForm(opSelfForm);" value="����" />
	</webui:panel>	
</html:form>

<html:javascript dynamicJavascript="true" staticJavascript="false" formName="opSelfForm" />

<script>
    function onSave(opSelfForm){
        if(document.opSelfForm.oldPassword.value.length >0){
            if(document.opSelfForm.newPassword.value == '' || document.opSelfForm.newPassword.value.length < 5){
                alert("�����������룬���볤��Ϊ5-32���ַ�");
                return false;
            }
            
            if(document.opSelfForm.newPassword.value != document.opSelfForm.newPassword2.value){
                alert("��������������벻һ�£�����������");
                return;
            }
        }
        
        if(!validateOpSelfForm(opSelfForm)) return false;
        
        return true;
    }
</script>
