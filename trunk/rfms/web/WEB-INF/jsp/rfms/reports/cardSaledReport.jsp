<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value='/js/options.js'/>"></script>
<script>

    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.cardReportForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
    
    function toReports(){
      document.freport['beginDate'].value=cardReportForm['beginDate'].value;
      document.freport['endDate'].value=cardReportForm['endDate'].value;
      loadOn();
      document.freport.submit();
    }
</script>
<form name="freport" action="../ReportServer" method="get">
 <input type="hidden" name="reportlet" value="/com/ft/cardSaledReport.cpt"/>
 <input type="hidden" name="beginDate"/>
 <input type="hidden" name="endDate"/>
</form>
<html:form action="/cardReport" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="�����۱���"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="ͳ�ƿ�ʼʱ��">
					<webui:calendar id="beginDate" property="beginDate" defaultToday="true" readonly="false"/>
				</webui:input>
				<webui:input label="ͳ�ƽ���ʱ��">
					<webui:calendar id="endDate" property="endDate" defaultToday="true"  readonly="false"/>
				</webui:input>
			</tr>
		</webui:formTable>

			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:toReports();"
			value="���ɱ���" />
	</webui:panel>
	
</html:form>



