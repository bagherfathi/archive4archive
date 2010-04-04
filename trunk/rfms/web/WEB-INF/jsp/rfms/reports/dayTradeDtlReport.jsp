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
    
    function settlementByMerchantId(merchantId,reportlet){
       document.freport["reportlet"].value=reportlet;
       document.freport["beginDate"].value=document.fineReportForm["beginDate"].value;
       document.freport["endDate"].value=document.fineReportForm["endDate"].value;
       document.freport["merchantId"].value=merchantId;
       loadOn();
       document.freport.submit();
    }
   function settlement(reportlet,reportlet1){
       document.freport["reportlet"].value=reportlet;
       document.freport["beginDate"].value=document.fineReportForm["beginDate"].value;
       document.freport["endDate"].value=document.fineReportForm["endDate"].value;
       var options=document.fineReportForm["merchantIds"].options;
       var newvalue="";
       for(var i=0;i<options.length;i++){
         if(newvalue!=""){
          newvalue=newvalue+","
         }
         newvalue=newvalue+options[i].value;
       }
       if(newvalue.length>0){
        document.freport["reportlet"].value=reportlet1;
       }
       document.freport["merchantIds"].value=newvalue;
       loadOn();
       document.freport.submit();
    }
    
    function settlementSingle(reportlet,merchantId,settledate){
       document.freport["reportlet"].value=reportlet;
       document.freport["beginDate"].value=settledate;
       document.freport["endDate"].value=settledate;
       document.freport["merchantIds"].value=merchantId;
       loadOn();
       document.freport.submit();
    }
</script>
<form name="freport" action="../ReportServer" method="get">
 <input type="hidden" name="reportlet" value="/com/ft/dayTrade.cpt"/>
 <input type="hidden" name="beginDate"/>
 <input type="hidden" name="endDate"/>
 <input type="hidden" name="merchantId"/>
 <input type="hidden" name="merchantIds"/>
</form>

<html:form action="/fineReport" method="post">
	<input type="hidden" value="searchDayTradeDtl" name="act" />
	<webui:panel title="日明细报表"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="统计开始时间">
					<webui:calendar id="beginDate" property="beginDate" defaultToday="true" readonly="false"/>
				</webui:input>
				<webui:input label="统计结束时间">
					<webui:calendar id="endDate" property="endDate" defaultToday="true" readonly="false"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="选择商户" colspan="3">
				<table><tr>
				<td>
					<html:select styleId="asource" property="tempMerchantIds" multiple="true" style="height:120px">
		           <webui:selectOfTable labelProperty="merchantName" valueProperty="merchantId" tableName="RfmsMerchant" whereCondition=" and auditStatus=8 "/>
		          </html:select>
		        </td>
		          <td>
		          <input type="button" value="&nbsp;>&nbsp;" onclick="swapSelected('asource','atarget')"/><br/>
		          <input type="button" value=">>" onclick="swapAll(asource,atarget)"/><br/><br/>
		          <input type="button" value="&lt;&lt;" onclick="swapAll(atarget,asource)"/><br/>
		          <input type="button" value="&nbsp;&lt;&nbsp;" onclick="swapSelected('atarget','asource')"/><br/>
                </td>
		         <td>
		          <html:select styleId="atarget" property="merchantIds" multiple="true"  style="height:120px">
		          </html:select></td><tr></table>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();selectAll(document.fineReportForm.merchantIds);document.fineReportForm.submit();"
			value="sysadmin.button.search" />
			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:selectAll(document.fineReportForm.merchantIds);settlement('/com/ft/dayTrade.cpt','/com/ft/dayTrade1.cpt');"
			value="合并日明细" />
	</webui:panel>
	<br/>
	<webui:panel title="商户记录"
	icon="../images/icon_list.gif">
	<webui:table items="${results }"
		action="${pageContext.request.contextPath}/rfms/fineReport.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="item" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="fineReportForm" form="fineReportForm">
		<webui:row>
			<webui:column  property="merchant_name" title="商户名称" styleClass="td_normal">
			</webui:column>
			<webui:column property="recorddate" title="记录时间" styleClass="td_normal" cell="date" format="yyyy-MM-dd"/>
			<webui:column property="amount" title="日明细报表"><a href="javascript:settlementSingle('/com/ft/dayTrade1.cpt',<c:out value='${item.merchant_id }'/>,'<fmt:formatDate value='${item.settle_date }' pattern='yyyy-MM-dd'/>')"/>下载</a></webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>
</html:form>
<script type="text/javascript">
<!--
swapSelected('asource','atarget');
//-->
</script>
