<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value='/js/options.js'/>"></script>
<script>

    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.payReportForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
    
    function toReports(){
      document.freport['beginDate'].value=payReportForm['beginDate'].value;
      document.freport['endDate'].value=payReportForm['endDate'].value;
      loadOn();
      document.freport.submit();
    }
    function pay(aform){
       aform.act.value="pay";
       loadOn();
       aform.submit();
    }
</script>
<form name="freport" action="../ReportServer" method="get">
 <input type="hidden" name="reportlet" value="/com/ft/pay.cpt"/>
 <input type="hidden" name="beginDate"/>
 <input type="hidden" name="endDate"/>
</form>
<html:form action="/payReport" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="支付报表"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="统计开始时间">
					<webui:calendar readonly="false" id="beginDate" property="beginDate" defaultToday="true"/>
				</webui:input>
				<webui:input label="统计结束时间">
					<webui:calendar readonly="false" id="endDate" property="endDate" defaultToday="true"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.payReportForm.submit();"
			value="sysadmin.button.search" />
			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:toReports();"
			value="生成报表" />
			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:pay(payReportForm);"
			value="确认付款" />
	</webui:panel>
	
	<br />
<webui:panel title="未付款记录"
	icon="../images/icon_list.gif">
	<webui:table items="${results }"
		action="${pageContext.request.contextPath}/rfms/payReport.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="item" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="payReportForm" form="payReportForm">
		<webui:row>
		   <webui:column style="text-align:center;" sortable="false"
				property="checkbox"
				title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.selectedIds)'/>全选"
				filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="selectedIds"
					value="<c:out value='${item.id}'/>" />
			</webui:column>
			<webui:column  property="merchant_name" title="商户名称" styleClass="td_normal">
			</webui:column>
			<webui:column property="start_date" title="付款起始日" styleClass="td_normal" cell="date" format="yyyy-MM-dd"/>
			<webui:column property="end_date" title="付款终止日" cell="date" format="yyyy-MM-dd">
			</webui:column>
			<webui:column property="amount" title="付款金额（元）">
			<c:out value="${item.amount/100.0 }"></c:out>
			</webui:column>
			<webui:column property="pay_flag" title="付款状态">
			<webui:lookup code="pay_flag@RFMS_TRADE" value="${item.pay_flag }"></webui:lookup>
			</webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>

</html:form>



