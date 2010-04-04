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
      document.freport['cardType'].value=payReportForm['cardType'].value;
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
 <input type="hidden" name="reportlet" value="/com/ft/cardTypeReport.cpt"/>
 <input type="hidden" name="beginDate"/>
 <input type="hidden" name="endDate"/>
 <input type="hidden" name="cardType"/>
</form>
<html:form action="/payReport" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="消费汇总报表"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="统计开始时间">
					<webui:calendar id="beginDate" property="beginDate" defaultToday="true" readonly="false"/>
				</webui:input>
				<webui:input label="统计结束时间">
					<webui:calendar id="endDate" property="endDate" defaultToday="true"  readonly="false"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="卡类型" colspan="3">
					 <html:select property="cardType">
					 <html:option value="0">全部</html:option>
					  <html:options collection="cardTypes" property="value" labelProperty="label"/>
					 </html:select>
				</webui:input>
			</tr>
		</webui:formTable>

			<webui:linkButton styleClass="clsButtonFace"
			href="javascript:toReports();"
			value="生成报表" />
	</webui:panel>
	
</html:form>



