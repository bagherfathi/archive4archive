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
      loadOn();
      document.freport.submit();
    }
</script>
<form name="freport" action="../ReportServer" method="get">
 <input type="hidden" name="reportlet" value="/com/ft/invoice.cpt"/>
 <input type="hidden" name="beginDate"/>
</form>
<html:form action="/invoiceReport" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="应收发票报表"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="统计月份" colspan="3">
					<webui:calendar readonly="false" id="beginDate" property="beginDate" defaultToday="true"/>
				</webui:input>
			</tr>
		</webui:formTable>
			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:toReports();"
			value="生成报表" />
	</webui:panel>
	


</html:form>



