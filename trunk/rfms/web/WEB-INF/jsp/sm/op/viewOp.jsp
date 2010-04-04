<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>


<webui:panel title="操作员维护" icon="../images/icon_list.gif">
<webui:tabContainer id="foo-bar-container" selectedTabPaneId="${param.selectedPane}">
	<webui:tabPane id="info" tabTitle="基本资料">
	   <%@include file="/WEB-INF/jsp/sm/op/inc/opView.jsp/" %>
	</webui:tabPane>
	<webui:tabPane id="org" tabTitle="可访问组织">
	   <%@include file="/WEB-INF/jsp/sm/op/inc/opOrg.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="role" tabTitle="功能角色" >
	   <%@include file="/WEB-INF/jsp/sm/op/inc/opRole.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="dataRole" tabTitle="业务角色">
	   <%@include file="/WEB-INF/jsp/sm/op/inc/opDataRole.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="group" tabTitle="组信息">
	   <%@include file="/WEB-INF/jsp/sm/op/inc/opGroup.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="返回"/>
</webui:panel> 
<form name="hiddenForm" method="post"/>    
<script>
   function back(){
        location.href = "<c:url value="/sm/op.do?loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}"/>";
   }
   function goURL(act){
        loadOn();
   		location.href = "<c:url value="/sm/op.do?opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&act="/>" + act ;
   }
  function postURL(act){
       loadOn();
       document.forms.hiddenForm.setAttribute('action', '<c:url value="/sm/op.do?opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&act="/>'+act);
	   document.forms.hiddenForm.submit();
  }
</script> 		
  	