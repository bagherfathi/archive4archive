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
	<input type="hidden" name="searchObj.operatorId" value="<c:out value='${ticketForm.currentUser.operatorId }'/>"/>
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
							property="element(STATUS@RFMS_ticket)" />
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
				<webui:column property="sfXf" title="是否主动下发">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${ticket.sfXf}" />
				</webui:column>
				<webui:column property="sfHy" title="限会员使用">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${ticket.sfHy}" />
				</webui:column>
				<webui:column property="sfHf" title="是否需要回复">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${ticket.sfHf}" />
				</webui:column>
				<webui:column property="sfZf" title="是否转发">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${ticket.sfZf}" />
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="fyDate" title="预定下发日期">
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="yxDate" title="飞券有效期">
				</webui:column>
				<webui:column property="status" title="状态">
					<webui:lookup code="STATUS@RFMS_ticket" value="${ticket.status}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/ticket.do?act=edit&id=${ticket.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
			</webui:column>

				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/ticket.do?act=view&id=${ticket.id}'/>">查看</a>&nbsp;
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>