<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.merchantForm.act.value="create";
        document.merchantForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.merchantForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/merchant" method="post">
	<input type="hidden" value="searchMerchant" name="act" />
	<input type="hidden" name="selectMerchantIds" />
	<webui:panel title="title.rfms.merchant.search"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="label.rfms.merchant.merchant_name">
					<html:text property="searchObj.merchantName" size="25" />
				</webui:input>
				<webui:input label="label.rfms.merchant.merchant_code">
					<html:text property="searchObj.merchantCode" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.merchant.status">
					<html:select property="searchObj.status">
		       <html:optionsCollection name="enumSet" property="element(status@RFMS_MERCHANT)"/>
		      </html:select>
				</webui:input>
				<webui:input label="label.rfms.merchant.audit_status">
					<html:select property="searchObj.auditStatus">
					<html:option value="-1">ȫ��</html:option>
		       <html:optionsCollection name="enumSet" property="element(audit_status@RFMS_MERCHANT)"/>
		      </html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.merchant.user_id" colspan="3">
					 <html:select property="baseEntity.userId">
					 <html:option value="0">��ѡ��</html:option>
	           <html:optionsCollection name="merchantForm" property="users"/>
	       </html:select>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.merchantForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>

<br />
<webui:panel title="title.rfms.merchant.list"
	icon="../images/icon_list.gif">
	<webui:table dataSource="merchantDS"
		action="${pageContext.request.contextPath}/rfms/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="merchant" width="95%" showExports="true"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm" form="merchantForm">
		<webui:row>
			<webui:column  property="merchantCode" title="label.rfms.merchant.merchant_code" styleClass="td_normal">
			</webui:column>
			<webui:column property="merchantName" title="label.rfms.merchant.merchant_name" styleClass="td_normal"/>
			<webui:column  property="address" title="label.rfms.merchant.address"  styleClass="td_normal">
			</webui:column>
			<webui:column property="status" title="label.rfms.merchant.status">
				<webui:lookup code="status@RFMS_MERCHANT" value="${merchant.status}" />
			</webui:column>
			<webui:column property="auditStatus" title="label.rfms.merchant.audit_status">
				<webui:lookup code="audit_status@RFMS_MERCHANT" value="${merchant.auditStatus}" />
			</webui:column>
			<webui:column property="amount" title="label.rfms.merchant.amount">
				<c:out value="${merchant.amount/100.00}" />
			</webui:column>
			<webui:column property="curUser" title="��ǰ������">
			<webui:query property="opName" beanName="merchantService" methodName="findOperatorByMerchantIdAndAuditStatus">
			<webui:param name="merchantId" type="java.lang.Long" value="${merchant.merchantId }"/>
			<webui:param name="auditStatus" type="java.lang.Long" value="${merchant.auditStatus }"/>
			</webui:query>
			</webui:column>
			<webui:column property="op" title="title.rfms.common.operater" width="3%">
			    <a
					href="<c:url value='/rfms/merchant.do?act=edit&id=${merchant.id}'/>">�༭</a>&nbsp;
				<a
					href="<c:url value='/rfms/merchant.do?act=view&id=${merchant.id}'/>">�鿴</a>&nbsp;<a
					href="<c:url value='/rfms/merchant.do?act=toPayment&merchantId=${merchant.id}'/>">��ֵ</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>
</html:form>