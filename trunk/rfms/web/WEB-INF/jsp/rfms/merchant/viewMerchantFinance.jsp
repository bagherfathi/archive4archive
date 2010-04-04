<%@ include file="/WEB-INF/jsp/inc/tld.inc"%> 
<webui:formTable>
      <tr>
	    <webui:input label="label.rfms.merchant.settle_startdate" required="true" colspan="3">
	       <c:out value="${merchantForm.baseEntity.settleStartdate}"/>
	    </webui:input>
	  </tr>
      <tr>
	    <webui:input label="label.rfms.merchant.discount_rate">
	       <c:if test="${merchantForm.baseEntity.auditStatus==2 }">
	       <html:text property="baseEntity.discountRate" size="25"/>
	       </c:if>
	        <c:if test="${merchantForm.baseEntity.auditStatus!=2 }">
	        <c:out value="${merchantForm.baseEntity.discountRate}"/>
	       </c:if>
	    </webui:input>
		<webui:input label="label.rfms.merchant.discount_remark">
		   <c:if test="${merchantForm.baseEntity.auditStatus==2 }">
	       <html:text property="baseEntity.discountRemark" size="25"/>
	       </c:if>
	        <c:if test="${merchantForm.baseEntity.auditStatus!=2 }">
	        <c:out value="${merchantForm.baseEntity.discountRemark}"/>
	       </c:if>
		   
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.commision_charge" required="true">
	       <c:out value="${merchantForm.baseEntity.commisionCharge}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.settle_upperlimit">
		   <c:out value="${merchantForm.baseEntity.settleUpperlimit}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.handling_charge">
		  <c:out value="${merchantForm.baseEntity.handlingCharge}"/>
		</webui:input>
		<webui:input label="label.rfms.merchant.rebates">
		  <c:out value="${merchantForm.baseEntity.rebates}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.pos_fee" colspan="3">
	       <c:out value="${merchantForm.baseEntity.posFee}"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.bank_name" required="true">
	       <c:out value="${merchantForm.baseEntity.bankName}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.bank_account_name"  required="true">
		   <c:out value="${merchantForm.baseEntity.bankAccountName}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.bank_account_code" required="true" colspan="3">
	       <c:out value="${merchantForm.baseEntity.bankAccountCode}"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.settle_period"  required="true">
	       <c:out value="${merchantForm.baseEntity.settlePeriod}"/>
	    </webui:input>
	    <webui:input label="label.rfms.merchant.settle_date" required="true">
	       <c:out value="${merchantForm.baseEntity.settleDate}"/>
	    </webui:input>
	   </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.settle_type" required="true">
		   <c:out value="${merchantForm.baseEntity.settleType}"/>
		</webui:input>
	    <webui:input label="label.rfms.merchant.check_date" required="true">
	       <c:out value="${merchantForm.baseEntity.checkDate}"/>
	    </webui:input>
		
	  </tr>
	  <!-- finace contacter informations -->
	  <tr>
	    <webui:input label="label.rfms.merchant.finance_contact" required="true">
	       <c:out value="${merchantForm.baseEntity.financeContact}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.finance_contact_phone" required="true">
		   <c:out value="${merchantForm.baseEntity.financeContactPhone}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.finance_contact_fax" required="true">
	       <c:out value="${merchantForm.baseEntity.financeContactFax}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.finance_contact_email" required="true">
		   <c:out value="${merchantForm.baseEntity.financeContactEmail}"/>
		</webui:input>
	  </tr>
 
    </webui:formTable>
