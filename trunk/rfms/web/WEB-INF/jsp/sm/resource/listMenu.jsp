<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="sysadmin.label.resource.list"
	icon="../images/icon_list.gif" width="95%">
	<webui:table items="resources" tableId="resources"
		action="${pageContext.request.contextPath}/sm/resource.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%" var="res" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" sortable="false">
		<webui:row>
			<webui:column property="title" title="功能权限标题">
				<c:out value='${res.title}' />
			</webui:column>
			<webui:column property="type" title="功能权限类型">
				<webui:lookup code="resource_type@SM_RESOURCE" value="${res.type}" />
			</webui:column>

			<webui:column filterable="false" property="order" title="排序"
				style="align: center;" width="15%">
				<c:if test="${res.type == 1}">
					<input type="text"
						name="orderMenu[<c:out value='${res.resourceId}'/>]"
						value="<c:out value='${res.order}'/>" size="5"
						onBlur="orderCheck(this)" />
				</c:if>
				<c:if test="${res.type ==2 }">/</c:if>
			</webui:column>

			<webui:column property="action" title="操作" width="10%">
				<a href="<c:url value='/sm/resource.do?act=view&resource.resourceId=${res.resourceId}'/>">查看</a>
				<a href="<c:url value='/sm/resource.do?act=toEdit&resource.resourceId=${res.resourceId}'/>">编辑</a>
			    <sm:resource id="${res.resourceId}" var="flag" />
				<c:if test="${!flag }">
				   <a href="#" onclick="del(<c:out value="${res.resourceId}"/>)">删除</a>
				</c:if>
			</webui:column>
			
			<webui:column property="action" title="业务操作" width="10%">
				<a href="<c:url value="/sm/resource.do?act=add&parentResource.resourceId=${res.resourceId}&currRId=0"/>">新增子菜单</a>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_RESOURCE_MENU">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:add();"
				value="新增" />
		</security:success>
	</security:checkPermission>

	<security:checkPermission resourceKey="SM_ORDER_RESOURCE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:toOrder();" value="sysadmin.button.order" />
		</security:success>
	</security:checkPermission>
	
	<c:if test="${!empty parent}">
			<webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="返回"/>
	</c:if>
	
	<%--
	<security:checkPermission resourceKey="SM_IMPORT_RESOURCE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:toImport();" value="sysadmin.button.import"/>
		</security:success>
	</security:checkPermission>
	--%>
</webui:panel>

<script>
    <c:if test="${!empty parent}">
    function back(){
        <c:if test="${parent.parentId != parent.resourceId}">
		window.location = "<c:url value="/sm/resource.do?act=list&resource.resourceId=${parent.parentId}"/>";
		</c:if>
		 <c:if test="${parent.parentId == parent.resourceId}">
		window.location = "<c:url value="/sm/resource.do?act=list"/>";
		</c:if>
	}
	</c:if>
	function del(id){
		if(confirm("<bean:message key='msg.confirm.delete.resource'/>")){
			//loadOn();
			window.location = "<c:url value="/sm/resource.do?act=delete&resource.resourceId="/>" + id;
		}
	}
	
	function add(){
		window.location = "<c:url value='/sm/resource.do?act=add'/>";
	}
	function toOrder(){
	    loadOn();
		document.forms.resources.setAttribute('action', '<c:url value="/sm/resource.do?act=order"/>');
		document.forms.resources.submit();
	}
	function toImport(){
		document.forms.resources.setAttribute('action', '<c:url value="/sm/resource.do?act=toImport"/>');
		document.forms.resources.submit();
	}
	function orderCheck(input){
		var value = input.value;
		var regexp=/^([1-9]\d{1,1}|\d)$/;
		if(!regexp.test(value)){
			alert('<bean:message key="msg.alert.resource.order"/>');
			event.returnValue=false;
			input.focus();
		}
	}
<c:if test="${empty parent }">
  <c:choose>
    <c:when test="${param.flag == 'delete' }">
	   var tree  = parent.leftFrame.tree;
       tree.deleteItem("<c:out value="${param.deletedId}"/>",true);
	</c:when>
	<c:when test="${param.flag == 'add' }">
	   var tree  = parent.leftFrame.tree;
       tree.insertNewItem(-1,<c:out value="${addedResource.resourceId}"/>,"<c:out value='${addedResource.title}'/>",0,"book.gif","books_open.gif","books_close.gif");
	</c:when>
	<c:when test="${empty param.flag }">
	   var tree  = parent.leftFrame.tree;
       tree.selectItem("-1",false);
       tree.openItem("-1");
	</c:when>
  </c:choose>
</c:if>	
	
<c:if test="${!empty parent && parent.type == 1}" >	
<c:choose >
	<c:when test="${param.flag == 'update' }">
	   var tree  = parent.leftFrame.tree;
       tree.setItemText(<c:out value="${resource.resourceId}"/>,"<c:out value="${resource.title}"/>");
	</c:when>
	<c:when test="${param.flag == 'add' }">
	   var tree  = parent.leftFrame.tree;
	   <c:set var="parent_Id" value="${addedResource.parentId}" />
	   <c:if test="${addedResource.parentId == addedResource.resourceId}">
	   <c:set var="parent_Id" value="-1" />
	   </c:if>
       tree.insertNewItem(<c:out value="${parent_Id}"/>,<c:out value="${addedResource.resourceId}"/>,"<c:out value="${addedResource.title}"/>",0,"book.gif","books_open.gif","books_close.gif","SELECT");
	</c:when>
	<c:when test="${param.flag == 'delete' }">
	   var tree  = parent.leftFrame.tree;
       tree.deleteItem("<c:out value="${param.deletedId}"/>",true);
	</c:when>
	<c:when test="${empty param.flag }">
	   var tree  = parent.leftFrame.tree;
       tree.selectItem("<c:out value='${resource.resourceId}'/>",false);
       tree.openItem("<c:out value='${resource.resourceId}'/>");
	</c:when>
</c:choose>
</c:if>
 </script>
