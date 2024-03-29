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
	<input type="hidden" name="searchObj.merchantId"
		value="<c:out value='${ticketForm.currentUser.merchantCode}'/>" />
	<webui:panel title="title.rfms.merchant.search"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="label.rfms.ticket.ticketSerial">
					<html:text property="searchObj.ticketSerial" size="25" />
				</webui:input>
				<webui:input label="label.rfms.ticket.ticket_name">
					<html:text property="searchObj.ticketName" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.ticket.status">
					<html:select property="searchObj.status">
						<html:option value="-1">请选择</html:option>
						<html:optionsCollection name="enumSet"
							property="element(STATUS@RFMS_CARD)" />
					</html:select>
				</webui:input>
				<webui:input label="优惠券有效期">
					<input size="15" type="text" id="beginTime" readonly="true"
						name="endDate.beginTime"
						value="<c:out value='${param["endDate.beginTime"]}'/>" />
					<img name="popcal" align="absmiddle"
						src='<c:url value="/images/look_day.gif"/>' class="cur-hand"
						border="0"
						onClick="calendar(beginTime,'yyyy-MM-dd');return false;">
			至
				<input size="15" type="text" id="endTime" readonly="true"
						name="endDate.endTime"
						value="<c:out value='${param["endDate.endTime"]}'/>" />
					<img name="popcal" align="absmiddle"
						src='<c:url value="/images/look_day.gif"/>' class="cur-hand"
						border="0" onClick="calendar(endTime,'yyyy-MM-dd');return false;">
				</webui:input>

			</tr>
			<tr>
				<webui:input label="商户名称" colspan="3">
					 <html:select property="baseEntity.merchantId">
						 <html:option value="-1">请选择</html:option>
			           <html:optionsCollection name="ticketForm" property="merchants"/>
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

	<webui:panel title="优惠券管理" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDS"
			action="${pageContext.request.contextPath}/rfms/ticket.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="优惠列表" var="ticket" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketForm" form="ticketForm">
			<webui:row>
				<webui:column property="merchantId" title="商户名称">
					<webui:query property="merchantName" beanName="merchantService"
						methodName="findMerchantByBranchId">
						<webui:param name="merchantId" type="java.lang.Long"
							value="${ticket.merchantId}" />
					</webui:query>
				</webui:column>
				<webui:column property="ticketSerial"
					title="label.rfms.ticket.ticketSerial" styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="ticketName"
					title="label.rfms.ticket.ticket_name" styleClass="td_normal" />
				<webui:column property="status" title="优惠券类型">
					<webui:lookup code="TYPE@RFMS_CARD" value="${ticket.status}" />
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="beginDate" title="预定下发日期">
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="endDate" title="优惠券有效期">
				</webui:column>
				<webui:column sortable="true" property="ticketCount"
					title="label.rfms.ticket.ticketCount" styleClass="td_normal" />
				<webui:column sortable="true" property="sendCount" title="发送数量"
					styleClass="td_normal" />
				<webui:column sortable="true" property="useCount" title="使用数量"
					styleClass="td_normal" />

				<webui:column property="status" title="优惠券状态">
					<webui:lookup code="STATUS@RFMS_CARD" value="${ticket.status}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/ticket.do?act=view&id=${ticket.id}'/>">查看</a>&nbsp;
					<a href="<c:url value='/rfms/ticket.do?act=edit&id=${ticket.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
			        <a
						href="<c:url value='/rfms/ticket.do?act=toBind&id=${ticket.id}'/>">绑定POS机</a>
				</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>