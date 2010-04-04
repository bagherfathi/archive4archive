<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value='/js/options.js'/>"></script>
<script>

    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.fineReportForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;

   function settlement(reportlet,reportlet1){
       document.freport["reportlet"].value=reportlet1;
       document.freport["beginDate"].value=document.fineReportForm["beginDate"].value;
       document.freport["endDate"].value=document.fineReportForm["endDate"].value;
       loadOn();
       document.freport.submit();
    }
    
    function settlementSingle(reportlet,merchantId,settledate){
       document.freport["reportlet"].value=reportlet;
       document.freport["beginDate"].value=settledate;
       document.freport["endDate"].value=settledate;
       loadOn();
       document.freport.submit();
    }
</script>
<form name="freport" action="../ReportServer" method="get">
 <input type="hidden" name="reportlet" value="/com/ft/settlementByMerchantId.cpt"/>
 <input type="hidden" name="beginDate"/>
 <input type="hidden" name="endDate"/>
 <input type="hidden" name="merchantId" value="<c:out value='${fineReportForm.merchantIds[0] }'/>"/>
 <input type="hidden" name="merchantIds" value="<c:out value='${fineReportForm.merchantIds[0] }'/>"/>
</form>

<html:form action="/fineReport" method="post">
	<input type="hidden" value="searchMerchantRep" name="act" />
	<webui:panel title="商户结算报表"
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
			href="javascript:loadOn();document.fineReportForm.submit();"
			value="sysadmin.button.search" />
			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:settlement('/com/ft/settlement.cpt','/com/ft/settlement1.cpt');"
			value="合并报表" />
	</webui:panel>
	<br/>
	<webui:panel title="商户记录"
	icon="../images/icon_list.gif">
	<webui:table items="${results }"
		action="${pageContext.request.contextPath}/rfms/fineReport.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="item" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="payReportForm" form="payReportForm">
		<webui:row>
			<webui:column  property="merchant_name" title="商户名称" styleClass="td_normal">
			</webui:column>
			<webui:column property="settle_date" title="结算日" styleClass="td_normal" cell="date" format="yyyy-MM-dd"/>
			<webui:column property="amount" title="结算报表"><a href="javascript:settlementSingle('/com/ft/settlement1.cpt',<c:out value='${item.merchant_id }'/>,'<fmt:formatDate value='${item.settle_date }' pattern='yyyy-MM-dd'/>')"/>下载</a></webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>
</html:form>

