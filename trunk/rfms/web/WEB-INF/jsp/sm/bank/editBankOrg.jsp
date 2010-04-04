<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
     function onBack(){
        location.href = '<c:url value="/sm/bankOrg.do?bankId="/>' + '<c:out value="${bank.bankId}"/>';
     }
</script>

<html:form styleId="bankOrgForm" action="/bankOrg" method="post" onsubmit="return validateForm(this)">
<input type="hidden" value="save" name="act"/>
<input type="hidden" name="bankId" value="<c:out value='${bank.bankId}'/>"/>
<input type="hidden" name="relBankOrgId" value="<c:out value='${relBankOrg.relBankOrg.relId}'/>"/>
<input type="hidden" name="validationKey" value="bankOrgForm"/>
<input type="hidden" name="relBankOrg.relBankOrg.bankId" value="<c:out value='${bank.bankId}'/>"/>

<webui:panel title="sysadmin.title.bank.org" width="95%">    
    <webui:formTable>
      <tr>
	    <webui:input label="sysadmin.label.bank.name">
	       <c:out value="${bank.bankName}"/>
	    </webui:input>
	    
		<webui:input label="sysadmin.label.bank.code">
		   <c:out value="${bank.bankCode}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <c:if test="${!empty relBankOrg.relBankOrg.relId}">
	      <webui:input label="绑定组织">
	        <c:out value="${relBankOrg.org.orgName}"/>
	        <html:hidden property="relBankOrg.relBankOrg.bankOrgId"/>
		  </webui:input>
		</c:if>
		<c:if test="${empty relBankOrg.relBankOrg.relId}">
		  <webui:input label="选择组织" required="true">
	        <html:select property="relBankOrg.relBankOrg.bankOrgId">
	           <option value="">请选择</option>
		       <html:optionsCollection property="accessOrgs"/>
		    </html:select>
		  </webui:input>
		</c:if>
		<webui:input label="是否联机" required="true">
	        <html:select property="relBankOrg.relBankOrg.onlineStatus" onchange="changeOnlineType(this.value)">
	           <option value="">请选择</option>
		       <html:optionsCollection name="enumSet" property="element(onlineStatus@SM_REL_BANK_ORG)"/>
		    </html:select>
		</webui:input>
	  </tr>
	  <tr id="onlineTypeTR" <c:if test="${ empty relBankOrg.relBankOrg || relBankOrg.relBankOrg.onlineStatus == 0 }">style="display:none"</c:if> >
		<webui:input label="联机类型" colspan="4" required="true">
	        <html:select property="relBankOrg.relBankOrg.onlineType">
	           <option value="">请选择</option>
		       <html:optionsCollection name="enumSet" property="element(onlineType@SM_REL_BANK_ORG)"/>
		    </html:select>
		</webui:input>
	  </tr>
    </webui:formTable>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(bankOrgForm);" value="sysadmin.button.save"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
   function changeOnlineType(obj){
      var tr = document.getElementById('onlineTypeTR');
      if(obj == 1){
          tr.style.display='';
      }else{
          tr.style.display='none';
      }   
   }
   function validateForm(obj){
      var onlineStatus = document.getElementById('relBankOrg.relBankOrg.onlineStatus');
      if(onlineStatus.value == 1){
          var onlineType = document.getElementById('relBankOrg.relBankOrg.onlineType');
          if(onlineType.value ==""){
             alert("请选择联机类型");
             return false;
          }
      }
      return validateBankOrgForm(obj);
   }
</script>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="bankOrgForm" />