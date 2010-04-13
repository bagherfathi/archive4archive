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
				<webui:input label="编号">
					<html:text property="searchObj.ticketSerial" size="25" />
				</webui:input>
				<webui:input label="名称">
					<html:text property="searchObj.ticketName" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.ticket.status" colspan="3">
					<html:select property="searchObj.status">
						<html:option value="-1">请选择</html:option>
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

	<webui:panel title="现金券管理" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDS"
			action="${pageContext.request.contextPath}/rfms/ticket.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="现金券列表" var="ticket" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketForm" form="ticketForm">
			<webui:row>
				<webui:column property="ticketSerial" title="编号"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="ticketName" title="名称"
					styleClass="td_normal" />
				<webui:column property="status" title="飞券类型">
					<webui:lookup code="TYPE@RFMS_CARD" value="${ticket.status}" />
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="beginDate" title="预定下发日期">
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="endDate" title="飞券有效期">
				</webui:column>
				<webui:column property="status" title="飞券状态">
					<webui:lookup code="STATUS@RFMS_CARD" value="${ticket.status}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/ticket.do?act=edit&id=${ticket.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
					<a href="<c:url value='/rfms/ticketsend.do?id=${ticket.id}'/>">会员下发</a>&nbsp;
					<a href="<c:url value='/rfms/ticketsendtem.do?id=${ticket.id}'/>">模板下发</a>&nbsp;
			        <a href="<c:url value='/rfms/ticket.do?act=toBind&id=${ticket.id}'/>">绑定POS机</a>
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>