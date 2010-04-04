<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.merchantForm.act.value="editMerchantBranch";
        document.merchantForm.submit();
    }
    /**
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.merchantForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
    */
</script>
<html:form action="/merchant" method="post">
	<input type="hidden" value="editMerchantBranch" name="act" />
	<html:hidden property="merchantId"/>
</html:form>
<webui:panel title="title.rfms.merchant_branch.list"
	icon="../images/icon_list.gif">
	<webui:table items="branchs"
		action="${pageContext.request.contextPath}/sm/merchant.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_branch.list" var="branch" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="merchantForm">
		<webui:row>
			<webui:column style="text-align:center;" sortable="false"
				property="checkbox"
				title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.selectedIds)'/>全选"
				filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="selectedIds"
					value="<c:out value='${branch.id}'/>" />
			</webui:column>
			<webui:column  property="merchantId" title="label.rfms.merchant_branch.merchant_id" styleClass="td_normal">
			</webui:column>
			<webui:column property="branchName" title="label.rfms.merchant_branch.branch_name" styleClass="td_normal"/>
			<webui:column  property="branchAddress" title="label.rfms.merchant_branch.branch_address"  styleClass="td_normal">
			</webui:column>
			<webui:column property="branchContactName" title="label.rfms.merchant_branch.branch_contact_name">
			</webui:column>
			<webui:column property="branchPhone" title="label.rfms.merchant_branch.branch_phone">
			</webui:column>
			
			<webui:column property="posNum" title="label.rfms.merchant_branch.pos_num">
			</webui:column>
			<webui:column property="status" title="label.rfms.merchant_branch.pos_type">
				<webui:lookup code="pos_type@RFMS_MERCHANT_BRANCH" value="${branch.posType}" />
			</webui:column>
			<webui:column property="sysMerchantCode" title="label.rfms.merchant_branch.sys_merchant_code">
			</webui:column>
			<webui:column property="sysJionState" title="label.rfms.merchant_branch.sys_jion_state">
			</webui:column>
			
			<webui:column property="操作" title="操作">
				<a
					href="<c:url value='/rfms/merchant.do?act=edit&merchantId=${merchant.id}'/>">编辑</a>&nbsp;
			</webui:column>
			
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace"
				href="javascript:onBack();" value="sysadmin.button.return" />
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:onCreate();" value="sysadmin.button.create" />
</webui:panel>
