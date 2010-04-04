<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function onBack(){
        location.href = '<c:url value="/sm/bank.do?listBank_p="/>' + '<c:out value="${currentPage_listBank}"/>';
    }
    
    function onDisable(){
        if (confirm("<bean:message key='msg.confirm.disable.bank'/>")) {
            loadOn();
            document.bankForm.act.value="disableBank";
            document.bankForm.submit();
        }
    }
    
    function onEnable(){
        if (confirm("<bean:message key='msg.confirm.enable.bank'/>")) {
            loadOn();
            document.bankForm.act.value="enableBank";
            document.bankForm.submit();
        }
    }
</script>

<html:form styleId="bankForm" action="/bank" method="post" onsubmit="return validateBankForm(this)">
<input type="hidden" value="save" name="act"/>
<input type="hidden" name="bankId" value="<c:out value='${bank.bankId}'/>"/>
<input type="hidden" name="validationKey" value="bankForm"/>

<webui:panel title="sysadmin.title.bank.edit" width="95%">    
    <webui:formTable>
      <tr>
	    <webui:input label="sysadmin.label.bank.name" required="true">
	       <html:text property="bank.bankName" size="25"/>
	    </webui:input>
	    <c:if test="${!empty bank.bankId}">
		<webui:input label="sysadmin.label.bank.code" required="true">
		   <html:text property="bank.bankCode" readonly="true" size="25"/>
		</webui:input>
		</c:if>
		
		<c:if test="${empty bank.bankId}">
		<webui:input label="sysadmin.label.bank.code" required="true">
		   <html:text property="bank.bankCode" size="25"/>
		</webui:input>
		</c:if>
	  </tr>
	  <tr>
	    <webui:input label="银行类型" required="true" colspan="4">
		   <html:select property="bank.bankType">
		       <html:option value="">请选择</html:option>
		       <html:optionsCollection name="enumSet" property="element(bank_type@SM_BANK)"/>
		  </html:select>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="sysadmin.label.desc" colspan="4">
	      <html:textarea property="bank.description" styleClass="wid80" rows="3"/>
    	</webui:input>
      </tr>
    </webui:formTable>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(bankForm);" value="sysadmin.button.save"/>
  <c:if test="${!empty bank.bankId}">
      <c:if test="${bank.status == 0}">
          <webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onDisable();" value="sysadmin.button.disable"/>
      </c:if>
      
      <c:if test="${bank.status == 1}">
          <webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onEnable();" value="sysadmin.button.enable"/>
      </c:if>
  </c:if>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>

</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="bankForm" />