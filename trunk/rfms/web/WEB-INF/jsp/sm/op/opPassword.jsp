<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="op" method="post" styleId="opForm_password" onsubmit="return check(this)">
<input type="hidden" name="act" value="changePassword"/>
<input type="hidden" name="opId" value="<c:out value='${op.operatorId}'/>"/>
<input type="hidden" name="validationKey" value="opForm_password"/>
<input type="hidden" name="loginName" value="<c:out value='${operatorForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<webui:panel title="�޸��û�����" width="95%"  icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
		    <webui:input label="����">
					<c:out value='${op.contact.name}'/>	
			</webui:input>
			<webui:input label="��¼��" colspan="4">
					<c:out value='${op.loginName}'/>	
			</webui:input>
		</tr>
		<tr>
			<webui:input label="������" required="true">
				<input type="password" name="password" id="newPsw"/>
			</webui:input>
			<webui:input label="������ȷ��" required="true">
				<input type="password" id="newPsw2"/>
			</webui:input>
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:javascript:submitForm(operatorForm);" value="����"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:javascript:back();" value="����"/>
</webui:panel>	
</html:form>
<script>
   function back(){
      location.href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}" />" ;
   }
     function check(form){
       if(validateOpForm_password(form)){
          var newPsw = document.getElementById("newPsw");
          var newPsw2 = document.getElementById("newPsw2");
          if(newPsw.value==null ||newPsw.value==''){
              alert('������������');
              return false;
          }
          if(newPsw2.value==null ||newPsw2.value==''){
              alert('��ȷ��������');
              return false;
          }
           if(newPsw.value!=newPsw2.value){
              alert('��������������벻��ͬ');
              return false;
          }
          return true;
        }  
     }
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="opForm_password" />
