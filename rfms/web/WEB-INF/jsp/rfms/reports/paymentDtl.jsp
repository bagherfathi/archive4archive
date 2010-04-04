<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value='/js/options.js'/>"></script>
<script>
    function checkReportBranch(branchId){
       
    }
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.fineReportForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/fineReport" method="post">
	<input type="hidden" value="searchPayment" name="act" />
	<webui:panel title="对账报表"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
		<tr>
				<webui:input label="POS编号" colspan="3">
					<html:text property="posCode" size="25"></html:text>
				</webui:input>
			</tr>
		  <tr>
				<webui:input label="交易状态">
					<html:text property="payStatus" size="25"></html:text>
				</webui:input>
				<webui:input label="卡号">
					<html:text property="cardNo" size="25"></html:text>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="交易金额区间" colspan="3">
					<html:text property="beginMoney" size="10"/> 至 <html:text property="endMoney" size="10"/>
				</webui:input>
				 
			</tr>
			<tr>
				<webui:input label="交易流水号" colspan="3">
					<html:text property="tradeNo" size="25"></html:text>
				</webui:input>
				 
			</tr>
			 
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.fineReportForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
	<webui:panel title="title.rfms.merchant.list"
	icon="../images/icon_list.gif">
	<webui:table items="${payments }"
		action="${pageContext.request.contextPath}/rfms/fineReport.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="pay" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm">
		<webui:row>
			<webui:column  property="merchantCode" title="受理机构" styleClass="td_normal">
			</webui:column>
			<webui:column property="merchantName" title="金额" styleClass="td_normal"/>
			<webui:column  property="address" title="卡号"  styleClass="td_normal">
			</webui:column>
			<webui:column property="status" title="POS号">
				<webui:lookup code="status@RFMS_MERCHANT" value="${merchant.status}" />
			</webui:column>
			<webui:column property="auditStatus" title="交易状态">
				<webui:lookup code="audit_status@RFMS_MERCHANT" value="${merchant.auditStatus}" />
			</webui:column>
			<webui:column property="auditStatus" title="交易时间">
			</webui:column>
			<webui:column property="op" title="title.rfms.common.operater" width="3%">
				<a
					href="<c:url value='/rfms/merchant.do?act=edit&id=${merchant.id}'/>">查看</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>
</html:form>

