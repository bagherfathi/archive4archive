<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function onBack(){
        location.href = '<c:url value="/sm/bankError.do"/>';
    }
    
    function onDisable(){
        if (confirm("<bean:message key='msg.confirm.disable.bank.error'/>")) {
            loadOn();
            document.bankErrorForm.act.value="disableBankError"
	        document.bankErrorForm.submit();
        }
    }
    
    function onEnable(){
        if (confirm("<bean:message key='msg.confirm.disable.bank.error'/>")) {
            loadOn();
            document.bankErrorForm.act.value="enableBankError"
	        document.bankErrorForm.submit();
        }
    }
</script>

<html:form styleId="bankErrorForm" action="/bankError" onsubmit="return validateBankErrorForm(this);">
<input type="hidden" value="save" name="act"/>
<input type="hidden" name="validationKey" value="bankErrorForm"/>
<input type="hidden" name="bankErrorId" value="<c:out value='${bankError.errorId}'/>"/>
<webui:panel title="sysadmin.label.bank.error.info" icon="../images/icon_list.gif" width="95%">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.bank.error.code" required="true">
			   <html:text property="bankError.errorCode" size="25"/>
			</webui:input>
			<webui:input label="sysadmin.label.bank.name" required="true">
			   <c:if test="${!empty bankError.errorId}">
			   
			   <!--查找所有银行信息-->
			   <sm:query var="banks" beanName="bankManager" methodName="findAllBanks"/>
			   <html:select property="bankError.bankId" disabled="true">
			       <c:forEach items="${banks}" var="bank">
			       <option value="<c:out value='${bank.bankId}'/>" <c:if test="${bank.bankId==bankError.bankId}">selected</c:if>><c:out value='${bank.bankName}'/></option>
			       </c:forEach>
			   </html:select>
			   <html:hidden property="bankError.bankId"/>
			   </c:if>
			   <c:if test="${empty bankError.errorId}">
			   <!--查找所有正常状态的银行信息-->
			   <sm:query var="banks" beanName="bankManager" methodName="findBanksByStatus">
			       <sm:param type="java.lang.Long" value="0"/>
			   </sm:query>
			   <html:select property="bankError.bankId">
			       <option value="">请选择</option>
			       <c:forEach items="${banks}" var="bank">
			       <option value="<c:out value='${bank.bankId}'/>" <c:if test="${bank.bankId==bankError.bankId}">selected</c:if>><c:out value='${bank.bankName}'/></option>
			       </c:forEach>
			   </html:select>
			   </c:if>
			</webui:input>
		</tr>
		<tr>
	        <webui:input label="sysadmin.label.bank.error.desc" required="true" colspan="3">
	            <html:textarea property="bankError.errorDesc" styleClass="wid80" rows="3"/>
			</webui:input>
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(bankErrorForm);" value="sysadmin.button.save"/>
	
	<c:if test="${!empty bankError.errorId}">
		<c:if test="${bankError.status == 0}">
			<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onDisable();" value="sysadmin.button.disable"/>
		</c:if>
		<c:if test="${bankError.status == 1}">
			<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onEnable();" value="sysadmin.button.enable"/>
		</c:if>
	</c:if>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="bankErrorForm" />