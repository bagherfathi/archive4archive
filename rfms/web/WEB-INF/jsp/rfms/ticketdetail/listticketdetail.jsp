<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.ticketdetailForm.act.value="create";
        document.ticketdetailForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.ticketdetailForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/ticketdetail" method="post">

	
	<br />
	<webui:panel title="飞券卡使用详细" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDetailDS"
			action="${pageContext.request.contextPath}/rfms/ticketdetail.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="飞券卡列表" var="ticketdetail" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketdetailForm" form="ticketdetailForm">
			<webui:row>
				<webui:column property="seqNumber" title="飞券编号" styleClass="td_normal"/>
				<webui:column property="ticketId" title="飞券名称">
				<webui:query property="ticketName" beanName="rfmsTicketService" methodName="getRfmsTicketById">
				<webui:param name="ticketId" type="java.lang.Long" value="${ticketdetail.ticketId }"/>
				</webui:query>
				</webui:column>
				<webui:column property="status" title="飞券状态">
					<webui:lookup code="SEND_STATUS@RFMS_CARD" value="${ticketdetail.status}" />
				</webui:column>
				<webui:column sortable="true" property="mobile" title="手机"
					styleClass="td_normal" />
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="sendDate" title="发送时间">
				</webui:column>
					
				<webui:column property="sendOperatorId" title="发送人">
				<webui:query property="opName" beanName="merchantService" methodName="findOperatorById">
				<webui:param name="sendOperatorId" type="java.lang.Long" value="${ticketdetail.sendOperatorId }"/>
				</webui:query>
				</webui:column>
				
				<webui:column sortable="true" property="useDate" title="使用时间"
					styleClass="td_normal" />
				<webui:column sortable="true" property="userPos" title="POS机"
					styleClass="td_normal" />
			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>