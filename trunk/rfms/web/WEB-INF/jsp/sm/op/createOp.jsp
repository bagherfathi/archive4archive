<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="op" onsubmit="return validate(this)">
<input type="hidden" name="act" value="save"/>
<input type="hidden" name="loginName" value="<c:out value='${operatorForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<webui:panel title="新增操作员" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="登录名" required="true">
				<html:text  property="op.loginName" styleId='name' size="25"/>	
			</webui:input>
			<webui:input label="密码" required="true">
				<input type="password" name="op.password" id="password" size="25"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="所属组织" required="true">
				<sm:orgsTree inputName="orgId" selOrgId="${loginOrgId}"  orgType="-1"/>
			</webui:input>
			<webui:input label="密码确认" required="true">
				<input type="password" id="password2" size="25"/>
			</webui:input>
		</tr>
	</webui:formTable>
<br/>
<webui:formTable>
<tr>
	<webui:input label="姓名" required="true">
		<html:text  property="op.contact.name" size="25"/>
	</webui:input>

	<webui:input label="电子邮箱" required="true">
		<html:text  property="op.email" size="25"/>
	</webui:input>
</tr>
<tr>
    <webui:input label="联系电话" >
		<html:text  property="op.contact.telephone" size="25"/>
	</webui:input>	
	<webui:input label="MSN" >
		<html:text  property="op.msn" size="25"/>
	</webui:input>	
</tr>
<tr>
    <webui:input label="移动电话" >
		<html:text  property="op.contact.mobilePhone" size="25"/>
	</webui:input>	
	<webui:input label="证件类型" >
		<html:select property="op.regType" styleId="regType">
				<option value="0">请选择</option>
				<html:optionsCollection name="enumSet" property="element(reg_type@SM_OPERATOR)"/>
		</html:select>
	</webui:input>
</tr>
<tr>
	<webui:input label="工号"  >
		<html:text  property="op.jobNumber" size="25"/>
	</webui:input>
	<webui:input label="证件号码" >
		<html:text  property="op.regNumber" styleId="regNum" size="25"/>
	</webui:input>
</tr>
<tr>
	<webui:input label="邮政编码">
		<html:text  property="op.contact.postCode" size="25"/>
		
	</webui:input>
	<webui:input label="商户编号">
				<html:text property="op.merchantCode" size="25"/>
	</webui:input>
</tr>
<tr>
	<webui:input label="通信地址" colspan="4">
		<html:text  property="op.contact.address" styleClass="wid80" />
	</webui:input>
</tr>
<tr>
   <webui:input label="备注" colspan="4">
		 <html:textarea property="op.memo" styleClass="wid80" rows="3"/>
	</webui:input>
</tr>
</webui:formTable>
<security:checkPermission resourceKey="SM_CREATE_OP">
	<security:success>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(operatorForm);" value="保存"/>
	</security:success>
</security:checkPermission>
	<webui:linkButton styleClass="clsButtonFace"  value="返回" href="javascript:back()" />
</webui:panel>	
</html:form>
<script>
    function back(){
      location.href="<c:url value="/sm/op.do?loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}" />" ;
    }
  	function validate(formName){
  	     var orgId = document.getElementById('orgId').value;
  	       if(orgId==null||orgId==""){
  	         alert("组织不能为空");
  	         return false;
  	       }
  	      var pwd = document.getElementById("password");
          var pwd2 = document.getElementById("password2");
          if(pwd.value!=pwd2.value){
              alert('两次输入的密码不相同');
              return false;
           }
           
           var regNum = document.getElementById('regNum');
  	     var regType = document.getElementById('regType');
  	     if (regType.value==0) regNum.value="";
  	     if(regType.value==1 ){
  	         var regexp=/^\d{15}(\d{2}[0-9Xx])?$/;
             var flag = regexp.test(regNum.value);
  	         if(!flag){
  	           alert("身份证号码无效");
  	           return false;
  	         }
  	     }  	  
  	       return validateOperatorForm(formName);
  	   }
  	   
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="operatorForm" />