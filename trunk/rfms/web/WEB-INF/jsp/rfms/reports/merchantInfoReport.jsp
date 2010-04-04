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
      document.freport['beginDate'].value=merchantReportForm['beginDate'].value;
      document.freport['endDate'].value=merchantReportForm['endDate'].value;
      loadOn();
      document.freport.submit();
    }

</script>
<form name="freport" action="../ReportServer" method="get">
 <input type="hidden" name="reportlet" value="/com/ft/merchantReport.cpt"/>
 <input type="hidden" name="beginDate"/>
 <input type="hidden" name="endDate"/>
</form>
<html:form action="/merchantReport" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="商户信息统计报表"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="统计开始时间">
					<webui:calendar readonly="false" id="beginDate" property="beginDate" defaultToday="false"/>
				</webui:input>
				<webui:input label="统计结束时间">
					<webui:calendar readonly="false" id="endDate" property="endDate" defaultToday="true"/>
				</webui:input>
			</tr>
		</webui:formTable>
			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:toReports();"
			value="生成报表" />

	</webui:panel>
	


</html:form>



