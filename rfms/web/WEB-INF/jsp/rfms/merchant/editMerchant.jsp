<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="merchantForm" action="/merchant.do" method="post"  onsubmit="return validateMerchantForm(this);">
<input type="hidden" value="edit" name="act"/>
<input type="hidden" value="0" name="subflag"/>
<webui:panel title="title.rfms.merchant.edit" width="95%"  icon="/images/icon_list.gif">    
 <webui:tabContainer id="foo-bar-container">
    <webui:tabPane id="baseInfo" tabTitle="商户基本信息">
	   <%@include file="/WEB-INF/jsp/rfms/merchant/merchantBase.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="contactInfo" tabTitle="财务信息">
	   <%@include file="/WEB-INF/jsp/rfms/merchant/merchantFinance.jsp" %>
	</webui:tabPane>

</webui:tabContainer>
<br/>
<webui:fieldSet title="门店信息" width="90%">
<webui:table items="branchs"
		action="${pageContext.request.contextPath}/rfms/merchant.do?act=edit"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant_branch.list" var="branch" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" rowsDisplayed="2" tableId="merchantForm" form="merchantForm">
		<webui:row>
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
			<webui:lookup code="sys_jion_state@RFMS_MERCHANT_BRANCH" value="${branch.sysJionState}" />
			</webui:column>
			<c:if test="${merchantForm.baseEntity.auditStatus==null or merchantForm.baseEntity.auditStatus==0 }">
			<webui:column property="dd" title="title.rfms.common.operater"><c:if test="${branch.id>0 }">
				<a
					href="javascript:editBranch(<c:out value='${branch.id}'/>)"><bean:message key="sysadmin.button.edit"/></a>&nbsp;
			</c:if>
			</webui:column>
			</c:if>
			<security:checkPermission resourceKey="SEARCH_EDIT">
			  <security:success>
			  <webui:column property="dd" title="title.rfms.common.operater"><c:if test="${branch.id>0 }">
				<a
					href="javascript:editBranch(<c:out value='${branch.id}'/>)"><bean:message key="sysadmin.button.edit"/></a>&nbsp;
			</c:if>
			</webui:column>
			  </security:success>
			</security:checkPermission>
		</webui:row>
	</webui:table>
	
</webui:fieldSet>
 <c:if test="${merchantForm.baseEntity.auditStatus==null or merchantForm.baseEntity.auditStatus==0 }">
  <webui:linkButton styleClass="clsButtonFace" href="javascript:addBranch(merchantForm);" value="sysadmin.button.addBranch"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm,0);" value="sysadmin.button.save"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm,1);" value="sysadmin.button.submit"/>
 </c:if>
 <c:if test="${merchantForm.baseEntity.auditStatus==8 }">
	 <security:checkPermission resourceKey="SEARCH_EDIT">
			  <security:success><input type="hidden" name="isSearchEdit" value="1"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:searchEdit(merchantForm);" value="sysadmin.button.save"/>
	</security:success>
  </security:checkPermission>
 </c:if>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
function addBranch(){
     var returnValue=window.showModalDialog('merchant.do?act=editMerchantBranch','','dialogHeight:250px;dialogWidth:750px;dialogLeft:100;dialogTop:200;center:yes')
     //alert(returnValue);
     if(returnValue=="true"){
       document.merchantForm.act.value="refreshEdit";
       document.merchantForm.submit();
     }
}

function editBranch(branchId){
     var url='merchant.do?act=editMerchantBranch&branchId='+branchId;
     var returnValue=window.showModalDialog(url,'','dialogHeight:250px;dialogWidth:750px;dialogLeft:100;dialogTop:200;center:yes')
     if(returnValue=="true"){
       document.merchantForm.act.value="refreshEdit";
       document.merchantForm.submit();
     }
}
 
function viewStep(merchantId){
     var url='merchant.do?act=viewStep&stepId=0&id='+merchantId;
     var returnValue=window.showModalDialog(url,'','dialogHeight:400px;dialogWidth:600px;dialogLeft:200;dialogTop:200;center:yes');
       document.merchantForm.act.value="refreshEdit";
       document.merchantForm.submit();
    
}
  
  function searchEdit(aform){
//   var auditStatus=<c:out value='${merchantForm.baseEntity.auditStatus}'/>;
   var auditStatus=8;
   submitForm(aform,auditStatus);
  }
 function submitForm(aform,auditStatus){
  var id=aform["baseEntity.merchantId"].value;
  aform["subflag"].value=auditStatus;
  if(id=="" || id=="0"){
     aform["baseEntity.status"].value=1;
     //aform["baseEntity.auditStatus"].value=auditStatus;
  }
  if(aform["baseEntity.auditStatus"].value==0){
    //aform["baseEntity.auditStatus"].value=auditStatus;
  }
  aform.act.value="save";
  loadOn();
  aform.submit();
 }

function changeCommisionType(avalue){
 var merchantId ="<c:out value='${baseEntity.merchantId }'/>";
  if(avalue==1){
   viewStep(merchantId);
   document.getElementById("commisionCharge").style.display="none";
  }else{
   document.getElementById("commisionCharge").style.display="";
   document.getElementById("commisionCharge1").style.display="none";
  }
}

</script>