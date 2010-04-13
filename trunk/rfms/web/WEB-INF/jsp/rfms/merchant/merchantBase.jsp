<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<webui:formTable>
      <tr>
	    <webui:input label="label.rfms.merchant.merchant_name" required="true">
	       <html:hidden property="baseEntity.merchantId" />
	       <html:hidden property="baseEntity.status" />
	       <html:hidden property="baseEntity.auditStatus" />
	       <html:hidden property="netxtOperatorIds" />
	       <html:text property="baseEntity.merchantName" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.merchant_code" required="true">
		   <html:text property="baseEntity.merchantCode" size="25"/>
		   <html:hidden property="baseEntity.sysMerchantCode"/>
		</webui:input>
	  </tr>
		   
      <tr>
	    <webui:input label="label.rfms.merchant.address" required="true">
	          <html:text property="baseEntity.address" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.phone">
		   <html:text property="baseEntity.phone" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.branch_num" required="true">
	       <html:text property="baseEntity.branchNum" size="25" onkeydown="onlyNum()"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.fax">
		   <html:text property="baseEntity.fax" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.contact_name" required="true">
		    <html:text property="baseEntity.contactName" size="25"/>
		</webui:input>
		<webui:input label="label.rfms.merchant.contact_phone" required="true">
		   <html:text property="baseEntity.contactPhone" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.industry"  required="false">
			<html:select property="baseEntity.industry">
				<html:optionsCollection name="enumSet" property="element(industry@RFMS_MERCHANT)"/>
		    </html:select>
	    </webui:input>
	    <webui:input label="label.rfms.merchant.mlevel"  required="false">
			<html:select property="baseEntity.mlevel">
				<html:optionsCollection name="enumSet" property="element(mlevel@RFMS_MERCHANT)"/>
		    </html:select>
	    </webui:input>
	   </tr>
	
	  <tr>
	    <webui:input label="label.rfms.merchant.user_id" required="true" colspan="3">
	       <html:select property="baseEntity.userId">
	           <html:optionsCollection name="merchantForm" property="users"/>
	       </html:select>
	    </webui:input>
	    <%--
		<webui:input label="label.rfms.merchant.user_id" required="true">
		    <html:text property="baseEntity.userId" size="25"/>
		</webui:input>
		--%>
	  </tr>

	  <tr>
	    <webui:input label="label.rfms.merchant.special_contact_name" required="true">
	       <html:text property="baseEntity.specialContactName" size="25"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.special_contact_phone">
		   <html:text property="baseEntity.specialContactPhone" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.create_time" required="true">
	       <webui:calendar id="createTime" property="baseEntity.createTime" defaultToday="true"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.org">
		   <html:select property="baseEntity.regionId">
		   <webui:selectOfTable labelProperty="regionName" valueProperty="regionId" tableName="Region" whereCondition=" and regionType=1"/>
		   </html:select>
		</webui:input>
	  </tr>
    <%--
    <tr>
	    <webui:input label="label.rfms.merchant_audit.next_operator_id" required="true" colspan="3">
	       <webui:radioGroup property="netxtOperatorIds" beanName="merchantForm" beanProperty="nextAuditOperators" labelProperty="label" valueProperty="value" styleClass="noborder"/>
	    </webui:input>
		
	  </tr>
	  --%>
    </webui:formTable>
