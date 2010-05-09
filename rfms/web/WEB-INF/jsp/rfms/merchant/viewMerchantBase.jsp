<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:formTable>
      <tr>
	    <webui:input label="label.rfms.merchant.merchant_name" required="true">
	       <html:hidden property="baseEntity.id" />
	       <html:hidden property="baseEntity.merchantId" />
	       <html:hidden property="baseEntity.auditStatus" />
	       <c:out value="${merchantForm.baseEntity.merchantName}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.merchant_code" required="true">
		   <c:out value="${merchantForm.baseEntity.merchantCode}"/>
		</webui:input>
	  </tr>
	   <tr>
 
		<webui:input label="label.rfms.merchant.sys_merchant_code" required="true" colspan="3">
		   <c:out value="${merchantForm.baseEntity.sysMerchantCode}"/>
		</webui:input>
	  </tr>
      <tr>
	    <webui:input label="label.rfms.merchant.address" required="true">
	          <c:out value="${merchantForm.baseEntity.address}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.phone" required="true">
		   <c:out value="${merchantForm.baseEntity.phone}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.branch_num" required="true">
	       <c:out value="${merchantForm.baseEntity.branchNum}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.fax" required="true">
		   <c:out value="${merchantForm.baseEntity.fax}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.contact_name" required="true">
		    <c:out value="${merchantForm.baseEntity.contactName}"/>
		</webui:input>
		<webui:input label="label.rfms.merchant.contact_phone" required="true">
		   <c:out value="${merchantForm.baseEntity.contactPhone}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.industry" required="true">
		    <webui:lookup code="industry@RFMS_MERCHANT" value="${merchantForm.baseEntity.industry}" />
		</webui:input>
		<webui:input label="label.rfms.merchant.mlevel" required="true">
		    <webui:lookup code="mlevel@RFMS_MERCHANT" value="${merchantForm.baseEntity.mlevel}" />
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.special_contact_name" required="true">
	       <c:out value="${merchantForm.baseEntity.specialContactName}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.special_contact_phone" required="true">
		   <c:out value="${merchantForm.baseEntity.specialContactPhone}"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="label.rfms.merchant.create_time" required="true">
	       <c:out value="${merchantForm.baseEntity.createTime}"/>
	    </webui:input>
		<webui:input label="label.rfms.merchant.org">
		   <webui:query property="regionName" beanName="baseService" methodName="getObjectById">
	        <webui:param name="entityClass" type="java.lang.String" value="com.ft.sm.entity.Region"/>
	        <webui:param name="id" type="java.lang.Long" value="${merchantForm.baseEntity.regionId }"/>
	       </webui:query>
		</webui:input>
	  </tr>
 
     <tr>
	    <webui:input label="label.rfms.merchant.amount" required="true" colspan="3">
	       <c:out value="${merchantForm.baseEntity.amount/100.00}"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
