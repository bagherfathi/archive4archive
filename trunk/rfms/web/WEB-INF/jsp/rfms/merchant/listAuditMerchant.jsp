<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html:form action="/merchant" method="post">
	<input type="hidden" value="audit" name="act" />
   <html:hidden property="auditStatus"/>
<webui:panel title="title.rfms.merchant.list"
	icon="../images/icon_list.gif">
	<webui:table items="${merchants }"
		action="${pageContext.request.contextPath}/rfms/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="merchant" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm" form="merchantForm">
		<webui:row>
			<webui:column  property="merchantCode" title="label.rfms.merchant.merchant_code" styleClass="td_normal">
			</webui:column>
			<webui:column property="merchantName" title="label.rfms.merchant.merchant_name" styleClass="td_normal"/>
			<webui:column  property="address" title="label.rfms.merchant.address"  styleClass="td_normal">
			</webui:column>
			<webui:column property="status" title="label.rfms.merchant.status"  width="10%">
				<webui:lookup code="status@RFMS_MERCHANT" value="${merchant.status}" />
			</webui:column>
			<webui:column property="auditStatus" title="label.rfms.merchant.audit_status" width="10%">
				<webui:lookup code="audit_status@RFMS_MERCHANT" value="${merchant.auditStatus}" />
			</webui:column>
			<webui:column property="op" title="title.rfms.common.operater" width="5%">
				<a href="<c:url value='/rfms/merchant.do?act=auditView&id=${merchant.id}'/>">…Û≈˙</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
</html:form>