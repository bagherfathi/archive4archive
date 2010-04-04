<%@ include file="/WEB-INF/jsp/inc/tld.inc" %> 
<webui:formTable>
      <tr>
	    <webui:input label="label.rfms.merchant.settle_startdate" required="true" colspan="3">
	       <webui:calendar id="settleStartdate" property="baseEntity.settleStartdate" defaultToday="true"/>
	    </webui:input>
	  </tr>
      <tr>
	    <webui:input label="label.rfms.merchant.discount_rate">
	       <html:text property="baseEntity.discountRate" size="25" readonly="true"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.discount_remark">
		   <html:text property="baseEntity.discountRemark" size="25" readonly="true"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.commision_step" required="true" colspan="3">
	        <webui:radioGroup property="baseEntity.commisionStep" styleClass="noborder" beanName="enumSet"
				beanProperty="element(commision_step@RFMS_MERCHANT)" valueProperty="value"
				labelProperty="label"  defaultValue="2" onclick="changeCommisionType(this.value)"/>&nbsp;&nbsp;<img src="../images/s.gif" alt="edit/view" style="cursor:hand" onclick="viewStep(<c:out value='${merchantForm.baseEntity.merchantId }'/>)"/>
	    </webui:input>
	  </tr>
	  <c:if test="${merchantForm.baseEntity.commisionStep==1 }">
	    <tr id="commisionCharge" style="display:none">
	    <webui:input label="label.rfms.merchant.commision_charge" required="true" colspan="3">
	       <html:text property="baseEntity.commisionCharge" size="25" onkeydown="onlyNum()"/>
	    </webui:input>
	  </tr>
	  </c:if>
	  <c:if test="${merchantForm.baseEntity.commisionStep==1 }">
	    <tr id="commisionCharge1">
	    <webui:input label="label.rfms.merchant.commision_charge" required="true" colspan="3">
	       <jsp:include flush="true" page="/WEB-INF/jsp/rfms/merchant/listStep.jsp"></jsp:include>
	    </webui:input>
	  </tr>
	  </c:if>
	  
	  <c:if test="${merchantForm.baseEntity.commisionStep!=1 }">
	  <tr id="commisionCharge">
	    <webui:input label="label.rfms.merchant.commision_charge" required="true" colspan="3">
	       <html:text property="baseEntity.commisionCharge" size="25" onkeydown="onlyNum()"/>
	    </webui:input>
	  </tr>
	  </c:if>
	  <tr>
	    <webui:input label="label.rfms.merchant.handling_charge">
		  <html:text property="baseEntity.handlingCharge" size="25" onkeydown="onlyNum()"/>
		</webui:input>
		<webui:input label="label.rfms.merchant.rebates">
		  <html:text property="baseEntity.rebates" size="25" onkeydown="onlyNum()"/>
		</webui:input>
	  </tr>
	
	  <tr>
	    <webui:input label="label.rfms.merchant.pos_fee">
	       <html:text property="baseEntity.posFee" size="25" onkeydown="onlyNum()"/>
	    </webui:input>
		 <webui:input label="label.rfms.merchant.settle_upperlimit">
		   <html:text property="baseEntity.settleUpperlimit" size="25" onkeydown="onlyNum()"/>
		</webui:input>
	  </tr>

	  <tr>
	    <webui:input label="label.rfms.merchant.bank_name" required="true">
	       <html:text property="baseEntity.bankName" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.bank_account_name"  required="true">
		   <html:text property="baseEntity.bankAccountName" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.bank_account_code" required="true" colspan="3">
	       <html:text property="baseEntity.bankAccountCode" size="25"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.settle_period"  required="true">
	       <html:select property="baseEntity.settlePeriod">
		   <webui:selectOfTable labelProperty="text" valueProperty="id" tableName="RfmsDictSettleperiodType"/>
		   </html:select>
	    </webui:input>
	    <webui:input label="label.rfms.merchant.settle_date" required="true">
	       <html:text property="baseEntity.settleDate" size="25"/>
	    </webui:input>
	   </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.settle_type" required="true">
		    <html:select property="baseEntity.settleType">
		   <webui:selectOfTable labelProperty="text" valueProperty="id" tableName="RfmsDictSettleType"/>
		   </html:select>
		</webui:input>
	    <webui:input label="label.rfms.merchant.check_date" required="true">
	       <html:text property="baseEntity.checkDate" size="25"/>
	    </webui:input>
		
	  </tr>
	  <!-- finace contacter informations -->
	  <tr>
	    <webui:input label="label.rfms.merchant.finance_contact" required="true">
	       <html:text property="baseEntity.financeContact" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.finance_contact_phone">
		   <html:text property="baseEntity.financeContactPhone" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.finance_contact_fax">
	       <html:text property="baseEntity.financeContactFax" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.finance_contact_email">
		   <html:text property="baseEntity.financeContactEmail" size="25"/>
		</webui:input>
	  </tr>
 
    </webui:formTable>
