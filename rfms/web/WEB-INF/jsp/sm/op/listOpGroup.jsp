<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<webui:panel title="操作员所在组"  icon="../images/icon_list.gif">
<div align="left">
       当前操作对象: 操作员<a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}"/>"><c:out value='${op.name}'/></a>
    </div>
	<webui:table
		title="组列表"
		items="opGroups"
		action="${pageContext.request.contextPath}/sm/opGroup.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="group"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>	
			<webui:column property="name" title="组名称">
			    <c:out value='${group.name}'/>
			</webui:column>
			<webui:column property="description" title="组描述" styleClass="td_normal"/>
		</webui:row>
	</webui:table>
	<br/>
	 <security:checkPermission resourceKey="SM_CONFIG_OP_GROUP">
      <security:success>
      <webui:linkButton styleClass="clsButtonFace" href="javascript:configGroup()" value="设置操作员的组"/>
       </security:success>
      </security:checkPermission>
</webui:panel>
<script>
   function configGroup(){
      location.href = "<c:url value="/sm/opGroup.do?act=configGroup&opId=${op.operatorId}"/>"
   }
</script>
