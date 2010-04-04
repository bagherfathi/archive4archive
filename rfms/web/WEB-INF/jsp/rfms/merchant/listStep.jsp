<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<webui:table items="${merchantSteps }" action="${pageContext.request.contextPath}/rfms/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_pos.list" var="step" width="95%"
		showPagination="false" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm1" form="merchantForm1">
		<webui:row>
			<webui:column property="minCharge" title="label.rfms.commision_step.min_charge" styleClass="td_normal"/>
			<webui:column  property="maxCharge" title="label.rfms.commision_step.max_charge">
			</webui:column>
			<webui:column property="commisionCharge" title="label.rfms.commision_step.commision_charge">
			</webui:column>
		</webui:row>
</webui:table>
