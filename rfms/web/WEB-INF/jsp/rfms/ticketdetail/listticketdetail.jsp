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
	<webui:panel title="��ȯ��ʹ����ϸ" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDetailDS"
			action="${pageContext.request.contextPath}/rfms/ticketdetail.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="��ȯ���б�" var="ticketdetail" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketdetailForm" form="ticketdetailForm">
			<webui:row>
				<webui:column property="seqNumber" title="��ȯ���" styleClass="td_normal"/>
				<webui:column sortable="true" property="ticketId" title="����" styleClass="td_normal" />
				<webui:column property="status" title="��ȯ״̬">
					<webui:lookup code="SEND_STATUS@RFMS_CARD" value="${ticketdetail.status}" />
				</webui:column>
				<webui:column sortable="true" property="mobile" title="�ֻ�"
					styleClass="td_normal" />
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd HH:mm:ss"
					property="sendDate" title="����ʱ��">
				</webui:column>
				<webui:column sortable="true" property="operator.opName" title="������"
					styleClass="td_normal" />
				<webui:column sortable="true" property="useDate" title="ʹ��ʱ��"
					styleClass="td_normal" />
				<webui:column sortable="true" property="userPos" title="POS��"
					styleClass="td_normal" />
			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>