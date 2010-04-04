<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>


<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<webui:panel title="����Ա��"  icon="../images/icon_list.gif">
    <webui:tabContainer id="foo-bar-container" selectedTabPaneId="${param.selectedPane}">
	<webui:tabPane id="info" tabTitle="��������">
	   <%@include file="/WEB-INF/jsp/sm/group/inc/groupInfo.jsp/" %>
	</webui:tabPane>
	<webui:tabPane id="org" tabTitle="�ɷ�����֯">
	   <%@include file="/WEB-INF/jsp/sm/group/inc/groupOrg.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="role" tabTitle="����Ȩ��">
	   <%@include file="/WEB-INF/jsp/sm/group/inc/groupRole.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="role" tabTitle="����Ա">
	   <%@include file="/WEB-INF/jsp/sm/group/inc/groupOp.jsp" %>
	</webui:tabPane>
    </webui:tabContainer>
    <webui:linkButton  styleClass="clsButtonFace"
			href="javascript:back();" value="����" />
</webui:panel>
<script>
   function goURL(act){
       location.href = "<c:url value="/sm/group.do?id=${group.groupId}&act="/>" + act;
   }
   function listRole(){
      location.href = "<c:url value="/sm/groupRole.do?id=${group.groupId}"/>"
   }
   function listOrg(){
      location.href = "<c:url value="/sm/groupOrg.do?id=${group.groupId}"/>"
   }
   function back(){
      location.href="<c:url value="/sm/group.do"/>"
   }
</script>