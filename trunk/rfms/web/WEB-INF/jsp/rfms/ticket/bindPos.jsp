<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="ticketForm" action="/ticket.do" method="post"  onsubmit="return validateTicketForm(this);">
<input type="hidden" value="tosearchPos" name="act"/>
<webui:panel title="优惠券绑定POS机" width="95%"  icon="/images/icon_list.gif">    
	   <webui:formTable>
	<tr>
		<webui:input label="label.rfms.ticket.ticket_name">
			<html:hidden property="baseEntity.sendCount" />
			<html:hidden property="baseEntity.useCount" />
			<html:hidden property="id"/>
			<input type="hidden" name="bindId"/>
			<html:hidden property="baseEntity.ticketId" />
			<input type="hidden" name="targetMemberTypes" id="targetMemberTypes" />
			<input type="hidden" name="baseEntity.merchantId"
				value="<c:out value='${ticketForm.currentUser.merchantCode}'/>" />
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${ticketForm.currentUser.operatorId}'/>" />
			<c:out value="${ ticketForm.baseEntity.ticketName}"></c:out>
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketSerial" >
			<c:out value="${ticketForm.baseEntity.ticketSerial }"></c:out>
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.ticket.type">
			 <webui:lookup code="TYPE@RFMS_CARD" value="${ticketForm.baseEntity.type}" />
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketCount">
				<c:out value="${ticketForm.baseEntity.parValue }"></c:out>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.parValue">
				<c:out value="${ticketForm.baseEntity.parValue }"></c:out>
		</webui:input>
		<webui:input label="label.rfms.ticket.parZhekou">
				<c:out value="${ticketForm.baseEntity.parZhekou }"></c:out>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.beginDate">
				<c:out value="${ticketForm.baseEntity.beginDate }"></c:out>
		</webui:input>
		<webui:input label="label.rfms.ticket.endDate">
				<c:out value="${ticketForm.baseEntity.endDate }"></c:out>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.status">
			<webui:lookup code="STATUS@RFMS_CARD" value="${ticketForm.baseEntity.status}" />
		</webui:input>
		<webui:input label="label.ticket.ohterInfo" >
			<c:out value="${ ticketForm.baseEntity.ohterInfo}"></c:out>
		</webui:input>

		<tr>
			<webui:input label="label.rfms.ticket.targetMemberType" colspan="3">
					<webui:lookup code="CODE_TYPE@RFMS_CARD" value="${ticketForm.baseEntity.targetMemberType}" />
			</webui:input>
		</tr>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.useRule" colspan="3">
				<c:out value="${ ticketForm.baseEntity.useRule}"></c:out>
		</webui:input>
	</tr>
</webui:formTable>
</webui:panel>
<br />
	<webui:panel title="已经绑定POS机" icon="../images/icon_list.gif">
		<webui:table items="posBindDTOs"
			action="${pageContext.request.contextPath}/rfms/ticket.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="现金券列表" var="item" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketForm" form="ticketForm">
			<webui:row>
				<webui:column property="merchant.merchantName" title="商户名称"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="branch.branchName" title="门店名称"
					styleClass="td_normal" />
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="bind.createDate" title="绑定时间">
				</webui:column>
			<webui:column property="pos.sysPosCode" title="label.rfms.merchant_pos.sys_pos_code" styleClass="td_normal"/>
			<webui:column  property="item.pos.owner" title="label.rfms.merchant_pos.owner">
			<webui:lookup code="owner@RFMS_MERCHANT_POS" value="${item.pos.posType}" />
			</webui:column>
			<webui:column property="pos.posType" title="label.rfms.merchant_pos.pos_type">
			<webui:lookup code="pos_type@RFMS_MERCHANT_POS" value="${item.pos.posType}" />
			</webui:column>
            <webui:column property="posType" title="操作">
			  <a href="javascript:delbind(ticketForm,<c:out value='${item.bind.bindId }'/>);">删除</a>
			</webui:column>
			</webui:row>
		</webui:table>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(ticketForm);" value="绑定POS机" />
    <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(ticketForm);" value="返回" />
	</webui:panel>
	
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="ticketForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  function delbind(aform,bindId){
    aform.act.value="bindDelete";
    aform.bindId.value=bindId;
   	aform.submit();
  }
 function submitForm(aform){
		  aform.act.value="tosearchPos";
		  loadOn();
		  aform.submit();
 }

</script>