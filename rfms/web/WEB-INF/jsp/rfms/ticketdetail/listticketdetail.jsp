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
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.merchantId" value="<c:out value='${ticketForm.currentUser.merchantCode}'/>"/>
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="�Ż�ȯ���">
					<html:text property="searchObj.seqNumber" size="25" />
				</webui:input>
				<webui:input label="��֤��">
					<html:text property="searchObj.validatorCode" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="�ֻ�" colspan="3">
					<html:text property="searchObj.mobile" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onCreate();" value="sysadmin.button.create" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.ticketForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
	
	<br />
	<webui:panel title="�Ż�ȯ��ʹ����ϸ" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDetailDS"
			action="${pageContext.request.contextPath}/rfms/ticketdetail.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="�Ż�ȯ���б�" var="ticketdetail" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketdetailForm" form="ticketdetailForm">
			<webui:row>
				<webui:column property="seqNumber" title="�Ż�ȯ���" styleClass="td_normal"/>
				<webui:column property="validatorCode" title="��֤��" styleClass="td_normal"/>
				<webui:column property="ticketId" title="�Ż�ȯ����">
				<webui:query property="ticketName" beanName="rfmsTicketService" methodName="getRfmsTicketById">
				<webui:param name="ticketId" type="java.lang.Long" value="${ticketdetail.ticketId }"/>
				</webui:query>
				</webui:column>
				<webui:column property="status" title="�Ż�ȯ״̬">
					<webui:lookup code="SEND_STATUS@RFMS_CARD" value="${ticketdetail.status}" />
				</webui:column>
				<webui:column sortable="true" property="mobile" title="�ֻ�"
					styleClass="td_normal" />
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="sendDate" title="����ʱ��">
				</webui:column>
					
				<webui:column property="sendOperatorId" title="������">
				<webui:query property="opName" beanName="merchantService" methodName="findOperatorById">
				<webui:param name="sendOperatorId" type="java.lang.Long" value="${ticketdetail.sendOperatorId }"/>
				</webui:query>
				</webui:column>
				
				<webui:column sortable="true" property="useDate" title="ʹ��ʱ��"
					styleClass="td_normal" />
				<webui:column sortable="true" property="userPos" title="POS��"
					styleClass="td_normal" />
			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>