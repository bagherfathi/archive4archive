<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
	<webui:table items="${branchs }"
		action="${pageContext.request.contextPath}/rfms/merchant.do?act=view"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_branch.list" var="branch" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" rowsDisplayed="15" autoIncludeParameters="false" tableId="merchantForm" form="merchantForm">
		<webui:row>
			<webui:column property="branchName" title="label.rfms.merchant_branch.branch_name" styleClass="td_normal"/>
			<webui:column  property="branchAddress" title="label.rfms.merchant_branch.branch_address"  styleClass="td_normal">
			</webui:column>
			<webui:column property="branchContactName" title="label.rfms.merchant_branch.branch_contact_name">
			</webui:column>
			<webui:column property="branchPhone" title="label.rfms.merchant_branch.branch_phone">
			</webui:column>
			
			<webui:column property="posNum" title="label.rfms.merchant_branch.pos_num">
			</webui:column>
			<webui:column property="status" title="label.rfms.merchant_branch.pos_type">
				<webui:lookup code="pos_type@RFMS_MERCHANT_BRANCH" value="${branch.posType}" />
			</webui:column>
			<webui:column property="sysMerchantCode" title="label.rfms.merchant_branch.sys_merchant_code">
			</webui:column>
			<webui:column property="sysJionState" title="label.rfms.merchant_branch.sys_jion_state">
			<webui:lookup code="sys_jion_state@RFMS_MERCHANT_BRANCH" value="${branch.sysJionState}" />
			</webui:column>
			<c:if test="${merchantForm.baseEntity.auditStatus==6 }">
			<webui:column property="dd" title="title.rfms.common.operater">
				<a
					href="javascript:viewPos(<c:out value='${branch.id}'/>)"><bean:message key="sysadmin.button.merchant_pos.edit"/></a>&nbsp;
			</webui:column>
			</c:if>
			<c:if test="${merchantForm.baseEntity.auditStatus!=6 }">
			<webui:column property="dd" title="title.rfms.common.operater">
				<a
					href="javascript:showPos(<c:out value='${branch.id}'/>)"><bean:message key="sysadmin.button.merchant_pos.view"/></a>&nbsp;
			</webui:column>
			</c:if>
		</webui:row>
	</webui:table>
