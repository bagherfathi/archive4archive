<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>

<webui:panel title="操作员组可访问的组织"  icon="../images/icon_list.gif" width="95%">
<div align="left">
       当前操作对象: <a href="<c:url value="/sm/group.do?act=view&id=${group.groupId}"/>"><c:out value="${group.name}"/></a>
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
			<webui:column property="name" title="组织名称"/>
			<webui:column property="code" title="组织代码"/>
		</webui:row>
	</webui:table>
  </br>
  <webui:linkButton styleClass="clsButtonFace" href="javascript:configOrg();" value="设置可访问组织"/>
</webui:panel>
<script>
   function configOrg(){
     location.href="<c:url value="/sm/groupOrg.do?act=configOrg&id=${group.groupId}"/>"
   }
</script> 
