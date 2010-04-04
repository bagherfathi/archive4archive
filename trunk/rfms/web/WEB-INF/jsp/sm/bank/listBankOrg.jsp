<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.bankOrgForm.act.value="addRelBankOrg";
        document.bankOrgForm.submit();
    }
    
    function onDelete(){
        if (!isChecked(document.bankOrgForm.selectedIds, "��ѡ����Ҫɾ���İ���֯")) {
            return;
        } else {
            if (confirm("ȷ��Ҫɾ��ѡ���а���֯��Ϣ��")) {
                loadOn();
                document.bankOrgForm.act.value="deleteRelBankOrg";
                document.bankOrgForm.submit();
            }
        }
    }
    
    function onBack(){
        location.href = '<c:url value="/sm/bank.do?listBank_p="/>' + '<c:out value="${currentPage_listBank}"/>';
    }
</script>
<html:form action="/bankOrg" method="post">
<input type="hidden" name="act"/>
<input type="hidden" name="bankId" value="<c:out value='${bank.bankId}'/>"/>
<webui:panel title="sysadmin.title.bank.info" width="95%">    
    <webui:formTable>
      <tr>
	    <webui:input label="sysadmin.label.bank.name">
	       <c:out value='${bank.bankName}'/>
	    </webui:input>
		<webui:input label="sysadmin.label.bank.code">
		   <c:out value='${bank.bankCode}'/>
		</webui:input>
	  </tr>
    </webui:formTable>
</webui:panel>
<br/>
<webui:panel title="sysadmin.title.bank.orgs" icon="../images/icon_list.gif">
<webui:table 
		items="bankOrgs"
		action="${pageContext.request.contextPath}/sm/bankOrg.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.bank.list"
		var="bankOrg"
		width="95%"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		autoIncludeParameters="false"
		sortable="false" 
		>
		<webui:row>
		    <webui:column style="text-align:center;" sortable="false" property="checkbox" title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.selectedIds)'/>ȫѡ" filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="selectedIds" value="<c:out value='${bankOrg.relBankOrg.relId}'/>"/>       
			</webui:column>
			<webui:column sortable="false" filterable="false" property="bankOrg.org.orgName" title="��֯����">
			    <c:out value='${bankOrg.org.orgName}'/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="bankOrg.relBankOrg.onlineStatus" title="�Ƿ�����">
			    <webui:lookup code="onlineStatus@SM_REL_BANK_ORG" value="${bankOrg.relBankOrg.onlineStatus}"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="bankOrg.relBankOrg.onlineType" title="������ʽ">
			    <c:if test="${bankOrg.relBankOrg.onlineStatus==0}">
			        ��
			    </c:if>
			    <c:if test="${bankOrg.relBankOrg.onlineStatus!=0}">
			        <webui:lookup code="onlineType@SM_REL_BANK_ORG" value="${bankOrg.relBankOrg.onlineType}"/>
			    </c:if>
			</webui:column>
			<webui:column property="����" title="����" >
			   <a href="<c:url value='/sm/bankOrg.do?act=edit&bankId=${bank.bankId}&relBankOrgId=${bankOrg.relBankOrg.relId}'/>">�޸�</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="sysadmin.button.create"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onDelete();" value="sysadmin.button.delete"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
