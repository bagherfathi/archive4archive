<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.bankErrorForm.act.value="create";
        document.bankErrorForm.submit();
    }
    
    function onDisable(){
        if (!isChecked(document.forms.ec.selectedIds, "<bean:message key='msg.confirm.select.bank.error' />")) {
            return;
        } else {
            if (confirm("<bean:message key='msg.confirm.disable.bank.error'/>")) {
                loadOn();
                document.forms.ec.setAttribute('action', '<c:url value="/sm/bankError.do?act=disable"/>');
		        document.forms.ec.submit();
            }
        } 
    }
    
    function onEnable(){
        if (!isChecked(document.forms.ec.selectedIds, "<bean:message key='msg.confirm.select.bank.error' />")) {
            return;
        } else {
            if (confirm("<bean:message key='msg.confirm.enable.bank.error'/>")) {
                loadOn();
                document.forms.ec.setAttribute('action', '<c:url value="/sm/bankError.do?act=enable"/>');
		        document.forms.ec.submit();
            }
        } 
    }
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.bankErrorForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>

<html:form action="/bankError" method="post">
<html:hidden property="act" value="search"/>
<sm:query var="banks" beanName="bankManager" methodName="findAllBanks"/>
<webui:panel title="sysadmin.tilte.bank.error.search" icon="../images/icon_list.gif" width="95%">
    <webui:formTable>
      <tr>
      	<webui:input label="sysadmin.label.bank.name">
		<html:select property="bankId" >
			<option value="">所有银行</option>
			<html:options collection="banks" labelProperty="bankName" property="bankId"/>
		</html:select>
		</webui:input>
		<webui:input label="sysadmin.label.bank.error.code">
			<html:text property="bankErrorCode" size="25"/>
		</webui:input>
	  </tr>
    </webui:formTable>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();bankErrorForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="sysadmin.title.bank.error.list" icon="../images/icon_list.gif" width="95%">
	<webui:table 
		dataSource="bankErrorDS"
		action="${pageContext.request.contextPath}/sm/bankError.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.bank.error.list"
		var="bankError"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
		    <webui:column style="text-align:center;" sortable="false" property="checkbox" title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.selectedIds)'/>全选" filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="selectedIds" value="<c:out value='${bankError.errorId}'/>"/>       
			</webui:column>
			<webui:column sortable="false" filterable="false" property="bankCode" title="银行名称">
				<c:forEach items="${banks}" var="bank">
					<c:if test="${bank.bankId == bankError.bankId}">
						<c:out value='${bank.bankName}'/>
					</c:if>
				</c:forEach>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="errorCode" title="错误代码" >
				<a href="<c:url value='/sm/bankError.do?act=edit&bankErrorId=${bankError.errorId}'/>"><c:out value="${bankError.errorCode}"/></a>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="errorDesc" title="错误描述" styleClass="td_normal"/>
			<webui:column property="status" title="状态" >
			   <webui:lookup code="status@SM_BANK_ERROR" value="${bankError.status}"/>
			</webui:column>
			<webui:column property="操作" title="操作" >
			   <a href="<c:url value='/sm/bankError.do?act=edit&bankErrorId=${bankError.errorId}'/>">修改</a>
			</webui:column>
		</webui:row>
	</webui:table>
<security:checkPermission resourceKey="SM_ADD_BANK_ERROR">
	<security:success>
	 <webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="sysadmin.button.create"/>
	</security:success>
</security:checkPermission>
<security:checkPermission resourceKey="SM_DISABLE_BANK_ERROR">
	<security:success>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onDisable();" value="sysadmin.button.disable"/>
	</security:success>
</security:checkPermission>
<security:checkPermission resourceKey="SM_ENABLE_BANK_ERROR">
	<security:success>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onEnable();" value="sysadmin.button.enable"/>
	</security:success>
</security:checkPermission>
</webui:panel>
