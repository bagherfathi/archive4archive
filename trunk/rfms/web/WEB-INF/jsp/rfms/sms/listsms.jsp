<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.smsForm.act.value="create";
        document.smsForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.smsForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/sms" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.operatorId" value="<c:out value='${smsForm.currentUser.operatorId }'/>"/>
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="手机" colspan="3">
					<html:text property="searchObj.mobile" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onCreate();" value="sysadmin.button.create" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.smsForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
	<br />
	<webui:panel title="短信列表" icon="../images/icon_list.gif">
		<webui:table dataSource="smsDS"
			action="${pageContext.request.contextPath}/rfms/sms.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="短信列表" var="sms" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="smsForm" form="smsForm">
			<webui:row>
				<webui:column property="mobile" title="手机"
					styleClass="td_normal">
				</webui:column>

				<webui:column sortable="true" property="message" title="内容"
					styleClass="td_normal" />
					<webui:column property="status" title="短信状态">
					<webui:lookup code="SMS_STATUS@RFMS_CARD" value="${sms.status}" />
				</webui:column>

				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="sendDate" title="发送日期">
				</webui:column>
				
				<webui:column property="operatorId" title="发送人">
				<webui:query property="opName" beanName="merchantService" methodName="findOperatorById">
				<webui:param name="operatorId" type="java.lang.Long" value="${sms.operatorId }"/>
				</webui:query>
				</webui:column>
				
			</webui:row>
		</webui:table>
	</webui:panel>
</html:form>