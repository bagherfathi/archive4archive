<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.bankForm.act.value="create";
        document.bankForm.submit();
    }
    
    function onDisable(){
        if (!isChecked(document.forms.listBank.selectedIds, "<bean:message key='msg.selected.bank' />")) {
            return;
        } else {
            if (confirm("<bean:message key='msg.confirm.disable.bank' />")) {
                loadOn();
                document.forms.listBank.setAttribute('action', '<c:url value="/sm/bank.do?act=disable"/>');
	            document.forms.listBank.submit();
            }
        } 
    }
    
    function onEnable(){
        if (!isChecked(document.forms.listBank.selectedIds, "<bean:message key='msg.selected.bank' />")) {
            return;
        } else {
            if (confirm("<bean:message key='msg.confirm.enable.bank' />")) {
                loadOn();
                document.forms.listBank.setAttribute('action', '<c:url value="/sm/bank.do?act=enable"/>');
	            document.forms.listBank.submit();
            }
        }
    }
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.bankForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/bank" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="selectBankIds" />
	<webui:panel title="sysadmin.tilte.bank.search"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.bank.name">
					<html:text property="bankName" size="25" />
				</webui:input>
				<webui:input label="sysadmin.label.bank.code">
					<html:text property="bankCode" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();bankForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
</html:form>
<br />
<webui:panel title="sysadmin.title.bank.list"
	icon="../images/icon_list.gif">
	<webui:table dataSource="bankDS"
		action="${pageContext.request.contextPath}/sm/bank.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.bank.list" var="bank" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"
		filterable="false" autoIncludeParameters="false" tableId="listBank">
		<webui:row>
			<webui:column style="text-align:center;" sortable="false"
				property="checkbox"
				title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.selectedIds)'/>全选"
				filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="selectedIds"
					value="<c:out value='${bank.bankId}'/>" />
			</webui:column>
			<webui:column sortable="false" filterable="false" property="bankName"
				title="银行名称" styleClass="td_normal">
			</webui:column>
			<webui:column sortable="false" filterable="false" property="bankCode"
				title="银行代码" styleClass="td_normal"/>
			<webui:column sortable="false" filterable="false" property="bankType"
				title="银行类型">
				<webui:lookup code="bank_type@SM_BANK" value="${bank.bankType}" />
			</webui:column>
			<webui:column property="status" title="状态">
				<webui:lookup code="status@SM_BANK" value="${bank.status}" />
			</webui:column>
			<webui:column property="操作" title="操作">
				<a
					href="<c:url value='/sm/bank.do?act=edit&bankId=${bank.bankId}'/>">修改</a>&nbsp;
			</webui:column>
			<webui:column property="业务操作" title="业务操作">
				<a href="<c:url value='/sm/bankOrg.do?bankId=${bank.bankId}'/>">绑定组织</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_BANK">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:onCreate();" value="sysadmin.button.create" />
		</security:success>
	</security:checkPermission>
	<security:checkPermission resourceKey="SM_DISABLE_BANK">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="#"
				onClick="javascript:onDisable();" value="sysadmin.button.disable" />
		</security:success>
	</security:checkPermission>

	<security:checkPermission resourceKey="SM_ENABLE_BANK">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="#"
				onClick="javascript:onEnable();" value="sysadmin.button.enable" />
		</security:success>
	</security:checkPermission>
</webui:panel>
