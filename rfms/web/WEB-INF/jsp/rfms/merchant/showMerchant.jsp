<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="merchantForm" action="/merchant.do" method="post"  onsubmit="return validateMerchantForm(this);">
<input type="hidden" value="toSearch" name="act"/>

<webui:panel title="title.rfms.merchant.edit" width="95%"  icon="/images/icon_list.gif">    
 <webui:tabContainer id="foo-bar-container">
    <webui:tabPane id="baseInfo" tabTitle="商户基本信息">
	   <%@include file="/WEB-INF/jsp/rfms/merchant/viewMerchantBase.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="contactInfo" tabTitle="财务信息">
	   <%@include file="/WEB-INF/jsp/rfms/merchant/viewMerchantFinance.jsp" %>
	</webui:tabPane>
    <webui:tabPane id="brachInfo" tabTitle="商户门店">
	   <%@include file="/WEB-INF/jsp/rfms/merchant/listBranch.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
 
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>

function viewPos(branchId){
     var url='merchant.do?act=viewPos&branchId='+branchId+"&posId=0";
     window.showModalDialog(url,'','dialogHeight:400px;dialogWidth:600px;dialogLeft:200;dialogTop:200;center:yes')
}
function showPos(branchId){
     var url='merchant.do?act=showPos&branchId='+branchId+"&posId=0";
     window.showModalDialog(url,'','dialogHeight:400px;dialogWidth:600px;dialogLeft:200;dialogTop:200;center:yes')
}
   
 function submitForm(aform){
  aform.act.value="saveAudit";
  <c:if test="${merchantForm.baseEntity.auditStatus!=7 }">
  var nx=aform["netxtOperatorIds"].value;
  if(nx==null || nx==""){
   alert("请选择下一审核人");
   return;
  }
  </c:if>
  loadOn();
  aform.submit();
 }


</script>