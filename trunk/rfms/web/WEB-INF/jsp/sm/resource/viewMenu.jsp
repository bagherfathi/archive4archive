<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel width="95%" title="sysadmin.label.resource.view">
	<html:form action="/resource.do">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.resource.name">
					<c:out value='${resource.title}' />
				</webui:input>
				<webui:input label="sysadmin.label.resource.type">
					<webui:lookup code="resource_type@SM_RESOURCE"
						value="${resource.type}" />
				</webui:input>
			</tr>

			<tr>
				<c:if test="${ resource.type ==1 }">
					<webui:input label="sysadmin.label.resource.url" colspan="3">
						<c:out value='${resource.url}' />
					</webui:input>
				</c:if>
				<c:if test="${ resource.type ==2 }">
					<webui:input label="sysadmin.label.resource.resourceKey"
						colspan="3">
						<c:out value='${resource.resourceKey}' />
					</webui:input>
				</c:if>
			</tr>
		</webui:formTable>
	</html:form>
	
	<sm:query var="childrenResources" beanName="resourceManager" methodName="findChildren">
			<sm:param type="java.lang.Long" value="${resource.resourceId}" />
		</sm:query>
	
	<security:checkPermission resourceKey="SM_EDIT_RESOURCE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:goURL('toEdit','resource.resourceId');"
				value="sysadmin.button.edit" />
		</security:success>
	</security:checkPermission>
	<c:if test="${resource.type == 1}">
		<security:checkPermission resourceKey="SM_ADD_RESOURCE_MENU">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace"
					href="javascript:goURL('add','parentResource.resourceId');"
					value="sysadmin.button.resource.new.menu" />
				<webui:linkButton styleClass="clsButtonFace"
					href="javascript:goURL('addButton','parentResource.resourceId');"
					value="sysadmin.button.resource.new.button" />
			</security:success>
		</security:checkPermission>
		<%--
			<security:checkPermission resourceKey="SM_ADD_RESOURCE_BUTTON">
				<security:success>
					<webui:linkButton styleClass="clsButtonFace" href="javascript:goURL('addButton','parentResource.resourceId');" value="sysadmin.button.resource.new.button"/>
				</security:success>
			</security:checkPermission>
			--%>
	</c:if>
	<sm:resource id="${resource.resourceId}" var="flag" />
	<c:if test="${(empty childrenResources)&&(flag == false)}">
		<security:checkPermission resourceKey="SM_DELETE_RESOURCE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="#"
					onClick="javascript:deleteRes();" value="sysadmin.button.delete" />
			</security:success>
		</security:checkPermission>
	</c:if>
	<c:if test="${resource.resourceId == resource.parent.resourceId}">
		<security:checkPermission resourceKey="SM_EXPORT_RESOURCE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace"
					href="javascript:toExport();" value="sysadmin.button.export" />
			</security:success>
		</security:checkPermission>
	</c:if>
</webui:panel>

<c:if test="${!empty childrenResources}">
    <br/>
	<webui:panel width="95%" title="sysadmin.label.resource.all.children">
		<webui:table
		items="childrenResources"
		title="sysadmin.label.resource.all.children"
		action="${pageContext.request.contextPath}/sm/resource.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="cRes"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		>
		<webui:row>
			<webui:column  filterable="false" property="title" title="功能权限标题" >
			      <c:out value='${cRes.title}'/>
			</webui:column>
			<webui:column  filterable="false" property="url" title="功能权限链接" styleClass="td_normal"/>
			<webui:column  filterable="false" property="type" title="功能权限类型">
				<webui:lookup code="resource_type@SM_RESOURCE" value="${cRes.type}"/>
			</webui:column>
			<webui:column  filterable="false" property="visible" title="是否可见">
			  <c:if test="${cRes.visible}">可见</c:if>
			  <c:if test="${!cRes.visible}">隐藏</c:if>
			</webui:column>  
			<webui:column  filterable="false" property="order" title="排序" style="align: center;">
				<c:if test="${cRes.type == 1}">
			    <input type="text" name="orderMenu[<c:out value='${cRes.resourceId}'/>]" value="<c:out value='${cRes.order}'/>" size="5" onBlur="orderCheck(this)" />
			    </c:if>
			    <c:if test="${cRes.type != 1}">/</c:if>
			</webui:column>
			<webui:column  filterable="false" property="action" title="操作">
				<a href="<c:url value='resource.do?act=view&resource.resourceId=${cRes.resourceId}'/>">查看</a>
				<a href="<c:url value='resource.do?act=toEdit&resource.resourceId=${cRes.resourceId}'/>">编辑</a>
			    <sm:resource id="${cRes.resourceId}" var="cflag" />
				<c:if test="${!cflag }">
				    <a href="#" onclick="del(<c:out value="${cRes.resourceId}"/>)">删除</a>
				</c:if>
			</webui:column>
			<webui:column property="action" title="业务操作" width="10%">
			    <c:if test="${cRes.type==1}">
				<a href="<c:url value="/sm/resource.do?act=add&parentResource.resourceId=${cRes.resourceId}&currRId=${resource.resourceId}"/>">新增子菜单</a>
				<a href="<c:url value="/sm/resource.do?act=addButton&parentResource.resourceId=${cRes.resourceId}&currRId=${resource.resourceId}"/>">新增按钮</a>
				</c:if>
			</webui:column>
		</webui:row>
		</webui:table>
		<security:checkPermission resourceKey="SM_ORDER_RESOURCE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:onOrder();" value="sysadmin.button.order"/>
			</security:success>
		</security:checkPermission>
	</webui:panel>
	</c:if>
<script>
	function goURL(act,param){
	    var url = "<c:url value='/sm/resource.do?act='/>" + act ;
	    if(param){
	        url = url + "&" + param + "=<c:out value='${resource.resourceId}'/>"
	    }
		window.location = url;
	}
	function deleteRes(){
	    del(<c:out value='${resource.resourceId}'/>);
	}
	function del(id){
		var ok=window.confirm("<bean:message key='msg.confirm.delete.resource'/>");
		if(ok==true){
			loadOn();
			document.forms.resourceForm.setAttribute('action', '<c:url value="/sm/resource.do?act=delete&resource.resourceId="/>'+id);
			document.forms.resourceForm.submit();
		}
		else
		return false;
	}
	function onOrder(){
	    loadOn();
		document.forms.ec.setAttribute('action', '<c:url value="/sm/resource.do?act=order&resource.resourceId=${resource.resourceId}"/>');
		document.forms.ec.submit();
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
	function toExport(){
		window.location = "<c:url value='/sm/resource.do?act=export'/>"+"&resource.resourceId="+"<c:out value='${resource.resourceId}'/>";
	}
<c:if test="${resource.type == 1}" >	
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
