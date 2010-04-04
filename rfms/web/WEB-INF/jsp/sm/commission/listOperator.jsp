<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
      if(window.event.keyCode==13){
	    onSearchOperator();
     }
    }
document.body.onkeypress= checkEnter;
function clear(){
    document.commisionForm.searchOrgId.value = "";
    document.commisionForm.select_searchOrgId.value = "";
    document.commisionForm.searchLoginName.value = "";
    document.commisionForm.searchName.value = "";
}
</script>

<html:form styleId ="commisionForm" action="/consign" method="post">
<input type="hidden" value="search" name="act"/>
<input type="hidden" name="consigneeId"/>

<webui:panel title="ί�ж����ѯ" icon="../images/icon_search.gif">
	<webui:formTable>
      <tr>
        <webui:input label="sysadmin.label.operator.login.name">
		   <html:text property="searchLoginName" size="25"/>	
		</webui:input>
	    <webui:input label="sysadmin.label.operator.name">
	       <html:text property="searchName" size="25"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="������֯" colspan="4">
	        <c:if test="${empty commisionForm.searchOrgId || commisionForm.searchOrgId==0}">
	        <sm:orgsTree inputName="searchOrgId" orgType="-1"/>
	        </c:if>
	        <c:if test="${!empty commisionForm.searchOrgId && commisionForm.searchOrgId!=0}">
	        <sm:orgsTree inputName="searchOrgId" orgType="-1" selOrgId="${commisionForm.searchOrgId}"/>
	        </c:if>
	    </webui:input>
	  </tr>
    </webui:formTable>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:onSearchOperator();" value="sysadmin.button.search"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:clear();" value="����" />	
</webui:panel>
<br/>
<c:if test="${!empty opList}">
<webui:panel title="sysadmin.label.commission.list" icon="../images/icon_list.gif">
    <webui:table 
		items="opList"
		tableId="commisionForm"
		action="${pageContext.request.contextPath}/sm/consign.do?act=searchOperator"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.bank.list"
		var="op"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		form="commisionForm"
		autoIncludeParameters="false">
		<webui:row>
		    <webui:column sortable="false" filterable="false" property="contact.name" title="����Ա����"/>
		    <webui:column sortable="false" filterable="false" property="loginName" title="����Ա��¼��"/>
			<webui:column sortable="false" filterable="false" property="org.orgName" title="������֯" />
			<webui:column sortable="false" filterable="false" property="ҵ�����" title="ҵ�����">
			    <a href="<c:url value='/sm/consign.do?act=selectComissionResource&consigneeId=${op.operatorId}&searchName=${commisionForm.searchName}&searchLoginName=${commisionForm.searchLoginName}&searchOrgId=${commisionForm.searchOrgId}'/>">ί��Ȩ��</a>&nbsp;
			    <a href="<c:url value='/sm/consign.do?act=searchCommissions&searchName=${commisionForm.searchName}&searchLoginName=${commisionForm.searchLoginName}&searchOrgId=${commisionForm.searchOrgId}&consigneeId=${op.operatorId }'/>">ί�м�¼</a>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
</c:if>
</html:form>

<script>
    function onSearchOperator(){
        loadOn();
        document.commisionForm.act.value="searchOperator";
        document.commisionForm.submit();
    }
</script>
