<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="操作员的组织"  icon="../images/icon_list.gif">
	<div align="left">
       当前操作对象: 操作员<a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}"/>"><c:out value='${op.name}'/></a>
    </div>

	<webui:table
		title="组织列表"
		items="accessOrgs" tableId="orgs"
		action="${pageContext.request.contextPath}/sm/opOrg.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="org"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  property="loginName" title="组织名称">
			      <c:out value='${org.name}'/>
			</webui:column>
			<webui:column  property="code" title="组织代码"/>
			<webui:column   property="parent" title="上级组织">
			     <c:out value='${org.parent.name}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	
	<webui:table
		title="继承的组织"
		items="orgInGroups" tableId="extendOrgs"
		action="${pageContext.request.contextPath}/sm/opOrg.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="orgInGroup"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  property="name" title="组织名称">
			      <c:out value='${orgInGroup.org.name}'/>
			</webui:column>
			<webui:column  property="orgInGroup.org.code" title="组织代码"/>
			<webui:column   property="parent" title="上级组织">
			       <c:out value='${orgInGroup.org.parent.name}'/>
			</webui:column>
			<webui:column  property="group" title="操作员组">
			       <c:out value='${orgInGroup.group.name}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	<br/>
	  <security:checkPermission resourceKey="SM_CONFIG_OP_ORG">
      <security:success>
         <webui:linkButton styleClass="clsButtonFace" href="javascript:configOrg();" value="设置可访问组织"/>
	   </security:success>
      </security:checkPermission>
	</webui:panel>     
	<script>
	   function configOrg(){
	       location.href = "<c:url value="/sm/opOrg.do?act=configOrg&opId=${op.operatorId}"/>"
	   }
	</script>
