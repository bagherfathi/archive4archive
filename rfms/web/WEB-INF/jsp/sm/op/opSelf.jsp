<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="opSelf" method="post" onsubmit="return onSave(this);">
	<input type="hidden" name="act" value="save" />
	<webui:panel title="编辑用户" width="100%">
	<webui:formTable>
			<tr>
				<webui:input label="请输入原密码" colspan="4">
                     <input type="password"  name="oldPassword" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="请输入新密码">
                     <input type="password" name="newPassword" size="25" />
				</webui:input>
				<webui:input label="请确认新密码">
                     <input type="password" name="newPassword2" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
	<webui:formTable>
			<tr>
				<webui:input label="登录名">
					<c:out value='${opSelfForm.opDTO.loginName}' />
				</webui:input>
				<webui:input label="移动电话">
					<html:text property="opDTO.contact.mobilePhone" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="姓名" required="true">
					<html:text property="opDTO.contact.name" size="25" />
				</webui:input>
				<webui:input label="联系电话">
					<html:text property="opDTO.contact.telephone" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="电子邮箱" required="true">
					<html:text property="opDTO.email" size="25" />
				</webui:input>
				<webui:input label="MSN">
					<html:text property="opDTO.msn" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="邮政编码" colspan="4">
					<html:text property="opDTO.contact.postCode" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="通信地址" colspan="4">
					<html:text size="64" property="opDTO.contact.address" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="备注" colspan="4">
					<html:textarea property="opDTO.memo" styleClass="wid80" rows="3" />
				</webui:input>
			</tr>
		</webui:formTable>
		<br/>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:submitForm(opSelfForm);" value="保存" />
	</webui:panel>	
</html:form>

<html:javascript dynamicJavascript="true" staticJavascript="false" formName="opSelfForm" />

<script>
    function onSave(opSelfForm){
        if(document.opSelfForm.oldPassword.value.length >0){
            if(document.opSelfForm.newPassword.value == '' || document.opSelfForm.newPassword.value.length < 5){
                alert("请输入新密码，密码长度为5-32个字符");
                return false;
            }
            
            if(document.opSelfForm.newPassword.value != document.opSelfForm.newPassword2.value){
                alert("两次输入的新密码不一致，请重新输入");
                return;
            }
        }
        
        if(!validateOpSelfForm(opSelfForm)) return false;
        
        return true;
    }
</script>
