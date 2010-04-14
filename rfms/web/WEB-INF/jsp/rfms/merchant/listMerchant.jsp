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
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="selectMerchantIds" />
	<webui:panel title="title.rfms.merchant.search"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="label.rfms.merchant.merchant_name">
				    <input type="hidden" name="searchObj.operatorId" value="<c:out value='${merchantForm.currentUser.operatorId }'/>"/>
					<html:text property="searchObj.merchantName" size="25" />
				</webui:input>
				<webui:input label="label.rfms.merchant.merchant_code">
					<html:text property="searchObj.merchantCode" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.merchant.status" >
					<html:select property="searchObj.status">
		       <html:optionsCollection name="enumSet" property="element(status@RFMS_MERCHANT)"/>
		      </html:select>
				</webui:input>
 				<webui:input label="label.rfms.merchant.audit_status">
					<html:select property="searchObj.auditStatus">
					<html:option value="-1">请选择</html:option>
		       <html:optionsCollection name="enumSet" property="element(audit_status@RFMS_MERCHANT)"/>
		      </html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.merchant.user_id" colspan="3">
					 <html:select property="baseEntity.userId">
					 <html:option value="-1">请选择</html:option>
	           <html:optionsCollection name="merchantForm" property="users"/>
	       </html:select>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
				href="javascript:onCreate();" value="sysadmin.button.create" />
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
			<webui:column sortable="true" property="merchantName" title="label.rfms.merchant.merchant_name" styleClass="td_normal"/>
			<webui:column  property="address" title="label.rfms.merchant.address"  styleClass="td_normal">
			</webui:column>
			<webui:column property="status" title="label.rfms.merchant.status">
				<webui:lookup code="status@RFMS_MERCHANT" value="${merchant.status}" />
			</webui:column>
			<webui:column property="amount" title="账户余额">
				<fmt:formatNumber pattern="##0.00" value="${merchant.amount/100.00 }"></fmt:formatNumber>
			</webui:column>
			<c:if test="${merchant.auditStatus==null or merchant.auditStatus==0 }">
			<webui:column property="op" title="title.rfms.common.operater" width="3%">
				<a
					href="<c:url value='/rfms/merchant.do?act=edit&id=${merchant.id}'/>"><bean:message key="sysadmin.button.edit"/></a>&nbsp;
			</webui:column>
			</c:if>
			<c:if test="${merchant.auditStatus>0 }">
			<webui:column property="op" title="title.rfms.common.operater" width="3%">
				<a
					href="<c:url value='/rfms/merchant.do?act=edit&id=${merchant.id}'/>">查看</a>&nbsp;
			</webui:column>
			</c:if>
			<webui:column property="op" title="title.rfms.common.operater" width="3%">
				<a
					href="<c:url value='/rfms/merchant.do?act=toPayment&merchantId=${merchant.id}'/>">充值</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>
</html:form>