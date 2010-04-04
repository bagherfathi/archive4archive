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
	<input type="hidden" value="checkRep" name="act" />
	<webui:panel title="对账报表" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="商户名称" colspan="3">
					<html:text property="merchantName" size="25"></html:text>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="结算周期">
					<html:select property="settlePeriod">
						<html:option value="0">请选择</html:option>
						<webui:selectOfTable labelProperty="text" valueProperty="id"
							tableName="RfmsDictSettleperiodType" />
					</html:select>
				</webui:input>
				<webui:input label="结算类型">
					<html:select property="settleType">
						<html:option value="0">请选择</html:option>
						<webui:selectOfTable labelProperty="text" valueProperty="id"
							tableName="RfmsDictSettleType" />
					</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="结算日区间" colspan="3">
					<html:text property="beginDay" size="10" onkeydown="onlyNum()"/> 至 <html:text
						property="endDay" size="10"  onkeydown="onlyNum()"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="统计开始时间">
					<webui:calendar id="timeSegment.begingTime"
						property="timeSegment.begingTime" defaultToday="true"  readonly="false"/>
				</webui:input>
				<webui:input label="统计结束时间">
					<webui:calendar id="timeSegment.endTime"
						property="timeSegment.endTime" defaultToday="true"  readonly="false"/>
				</webui:input>
			</tr>

		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.fineReportForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
	<br/>
	<webui:panel title="title.rfms.merchant.list"
		icon="../images/icon_list.gif">
		<webui:table dataSource="merchantRepDS"
			action="${pageContext.request.contextPath}/rfms/fineReport.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="title.rfms.merchant.list" var="merchant" width="95%"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="merchantForm">
			<webui:row>
				<webui:column property="merchantCode"
					title="label.rfms.merchant.merchant_code" styleClass="td_normal">
				</webui:column>
				<webui:column property="merchantName"
					title="label.rfms.merchant.merchant_name" styleClass="td_normal" />
				<webui:column property="address" title="label.rfms.merchant.address"
					styleClass="td_normal">
				</webui:column>
				<webui:column property="status" title="label.rfms.merchant.status">
					<webui:lookup code="status@RFMS_MERCHANT"
						value="${merchant.status}" />
				</webui:column>
				<webui:column property="auditStatus"
					title="label.rfms.merchant.audit_status">
					<webui:lookup code="audit_status@RFMS_MERCHANT"
						value="${merchant.auditStatus}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a
						href="<c:url value='../ReportServer?reportlet=/com/ft/checkIndex.cpt&merchantId=${merchant.id}'/>">生成报表</a>&nbsp;
			</webui:column>
			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>

