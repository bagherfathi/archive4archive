<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.ticketForm.act.value="create";
        document.ticketForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.ticketForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/ticket" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.merchantId" value="<c:out value='${ticketForm.currentUser.merchantCode}'/>"/>
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="���">
					<html:text property="searchObj.ticketSerial" size="25" />
				</webui:input>
				<webui:input label="����">
					<html:text property="searchObj.ticketName" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.ticket.status" colspan="3">
					<html:select property="searchObj.status">
						<html:option value="-1">��ѡ��</html:option>
						<html:optionsCollection name="enumSet"
							property="element(STATUS@RFMS_CARD)" />
					</html:select>
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

	<webui:panel title="�ֽ�ȯ����" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDS"
			action="${pageContext.request.contextPath}/rfms/ticket.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="�ֽ�ȯ�б�" var="ticket" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketForm" form="ticketForm">
			<webui:row>
				<webui:column property="ticketSerial" title="���"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="ticketName" title="����"
					styleClass="td_normal" />
				<webui:column property="status" title="��ȯ����">
					<webui:lookup code="TYPE@RFMS_CARD" value="${ticket.status}" />
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="beginDate" title="Ԥ���·�����">
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="endDate" title="��ȯ��Ч��">
				</webui:column>
				<webui:column property="status" title="��ȯ״̬">
					<webui:lookup code="STATUS@RFMS_CARD" value="${ticket.status}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/ticket.do?act=edit&id=${ticket.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
					<a href="<c:url value='/rfms/ticketsend.do?id=${ticket.id}'/>">��Ա�·�</a>&nbsp;
					<a href="<c:url value='/rfms/ticketsendtem.do?id=${ticket.id}'/>">ģ���·�</a>&nbsp;
			        <a href="<c:url value='/rfms/ticket.do?act=toBind&id=${ticket.id}'/>">��POS��</a>
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>