<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="merchantForm" action="/merchant.do" method="post"  onsubmit="return validateMerchantForm(this);">
<input type="hidden" value="saveAudit" name="act"/>

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
<c:if test="${merchantForm.baseEntity.auditStatus!=7 }">
<br/>
<input type="hidden" name="auditResult" value="1"/>
<input type="hidden" name="auditRemark" value="nothing"/>
<input type="hidden" name="netxtOperatorIds" value="1"/>

<%--
<webui:fieldSet title="审批" width="90%">
<webui:formTable>
<c:if test="${merchantForm.baseEntity.auditStatus==1 or merchantForm.baseEntity.auditStatus==2 or merchantForm.baseEntity.auditStatus==3 }">
      <tr>
	    <webui:input label="审核结果" required="true">
	      <INPUT TYPE="checkbox" value="1" name="auditResult" class="noborder" onclick="checkAuditResult()">审核通过
	    </webui:input>
	    <webui:input label="审核不通过，回退到">
		      <html:select property="returnTo">
		       <html:option value="">请选择</html:option>
		       <html:optionsCollection name="enumSet" property="element(audit_status@RFMS_MERCHANT)"/>
		      </html:select>
		</webui:input>
	  </tr>
      <tr>
	    <webui:input label="审核备注" colspan="3">
	       <textarea name="auditRemark" rows="5" cols="80"></textarea>
	    </webui:input>
	  </tr>
	  </c:if>
	  <c:if test="${merchantForm.baseEntity.auditStatus!=7 }">
	   <tr>
	    <webui:input label="label.rfms.merchant_audit.next_operator_id" required="true" colspan="3">
	       <webui:radioGroup property="netxtOperatorIds" beanName="merchantForm" beanProperty="nextAuditOperators" labelProperty="label" valueProperty="value" styleClass="noborder"/>
	    </webui:input>
	  </tr>
	  </c:if>
</webui:formTable>
</webui:fieldSet>
--%>
</c:if>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(merchantForm);" value="sysadmin.button.submit"/>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script><!--

function viewPos(branchId){
     var url='merchant.do?act=viewPos&branchId='+branchId+"&posId=0";
     window.showModalDialog(url,'','dialogHeight:400px;dialogWidth:600px;dialogLeft:200;dialogTop:200;center:yes')
}
function showPos(branchId){
     var url='merchant.do?act=showPos&branchId='+branchId+"&posId=0";
     window.showModalDialog(url,'','dialogHeight:400px;dialogWidth:600px;dialogLeft:200;dialogTop:200;center:yes')
}
function checkAuditResult()   
{   
	var c = document.getElementsByName("auditResult"); 
	var v = false;  
	for(i=0;i<c.length;i++){   
		if(c[i].checked == true   ){   
	    	v = true;   
	        break;   
	    }   
	}
	var objSelect = document.getElementById("returnTo");
	if(v){
		objSelect.options[0].selected = true;
	    objSelect.disabled = true;
	}else{
		objSelect.disabled = false;
	}
}   
 function submitForm(aform){
  aform.act.value="saveAudit";
  <%--
  <c:if test="${merchantForm.baseEntity.auditStatus!=7 }">
  var nx=GetRadioValue("netxtOperatorIds");
  if(nx==null || nx==""){
   alert("请选择下一审核人");
   return;
  }
  </c:if>
  --%>
  loadOn();
  aform.submit();
 }


--></script>