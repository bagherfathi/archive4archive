<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>

<webui:panel title="����Ա��ɷ��ʵ���֯"  icon="../images/icon_list.gif" width="95%">
<div align="left">
       ��ǰ��������: <a href="<c:url value="/sm/group.do?act=view&id=${group.groupId}"/>"><c:out value="${group.name}"/></a>
    </div>	
     <webui:table 
		items="accessOrg"
		action="${pageContext.request.contextPath}/sm/groupOrg.do"
		var="org"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false">
		<webui:row>
			<webui:column property="name" title="��֯����"/>
			<webui:column property="code" title="��֯����"/>
		</webui:row>
	</webui:table>
  </br>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:configOrg();" value="���ÿɷ�����֯"/>
</webui:panel>
<script>
   function configOrg(){
     location.href="<c:url value="/sm/groupOrg.do?act=configOrg&id=${group.groupId}"/>"
   }
</script> 
