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
				<webui:input label="优惠券编号">
					<html:text property="searchObj.seqNumber" size="25" />
				</webui:input>
				<webui:input label="验证码">
					<html:text property="searchObj.validatorCode" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="手机" colspan="3">
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
	<webui:panel title="优惠券卡使用详细" icon="../images/icon_list.gif">
		<webui:table dataSource="ticketDetailDS"
			action="${pageContext.request.contextPath}/rfms/ticketdetail.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="优惠券卡列表" var="ticketdetail" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketdetailForm" form="ticketdetailForm">
			<webui:row>
				<webui:column property="seqNumber" title="优惠券编号" styleClass="td_normal"/>
				<webui:column property="validatorCode" title="验证码" styleClass="td_normal"/>
				<webui:column property="ticketId" title="优惠券名称">
				<webui:query property="ticketName" beanName="rfmsTicketService" methodName="getRfmsTicketById">
				<webui:param name="ticketId" type="java.lang.Long" value="${ticketdetail.ticketId }"/>
				</webui:query>
				</webui:column>
				<webui:column property="status" title="优惠券状态">
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