<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="组织信息" icon="../images/icon_list.gif" width="95%">
	<webui:formTable>
		<tr>
			<webui:input label="组织名称">
				<c:out value="${org.name}" />
			</webui:input>
			<webui:input label="父组织">
				<c:out value="${org.parent.orgName}" />
			</webui:input>
		</tr>
		<tr>
			<webui:input label="组织代码">
				<c:out value="${org.code}" />
			</webui:input>
			<webui:input label="创建日期">
				<fmt:formatDate value="${org.createTime}" type="both" />
			</webui:input>
		</tr>
		<tr>
			<webui:input label="组织类型">
				<webui:lookup code="org_type@SM_ORGANIZATION" value="${org.type}" />
			</webui:input>
			<%-- 		<webui:input label="独立结算" >
				<webui:lookup code="self_balance@SM_ORGANIZATION" value="${org.selfBalance}"/>
			</webui:input>
    --%>
			<webui:input label="状态">
				<webui:lookup code="status@SM_ORGANIZATION" value="${org.status}" />
			</webui:input>
		</tr>
		<tr>
			<webui:input label="描述" colspan="4">
				<c:out value="${org.desc}" />
			</webui:input>

		</tr>
		<c:if test="${org.orgId != org.parent.orgId }">
			<tr>
				<webui:input label="联系人">
					<c:out value="${org.contact.name}" />
				</webui:input>
				<webui:input label="移动电话">
					<c:out value="${org.contact.mobilePhone}" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="邮政编码">
					<c:out value="${org.contact.postCode}" />
				</webui:input>
				<webui:input label="联系电话">
					<c:out value="${org.contact.telephone}" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="通信地址" colspan="4">
					<c:out value="${org.contact.address}" />
				</webui:input>
			</tr>
		</c:if>
	</webui:formTable>
	<c:if test="${org.orgId != org.parent.orgId }">
		<security:checkPermission resourceKey="SM_EDIT_ORG">
			<security:success>
				<%--	<webui:linkButton styleClass="clsButtonFace" href="javascript:openTree();" value="移动"/> --%>
				<webui:linkButton styleClass="clsButtonFace"
					href="javascript:goURL('edit','orgId');"
					value="sysadmin.button.edit" />
			</security:success>
		</security:checkPermission>
		<security:checkPermission resourceKey="SM_DISABLE_ORG">
			<security:success>
				<sm:org id="${org.orgId}" var="flag" />
				<c:if test="${org.status !=1 && !flag}">
					<webui:linkButton styleClass="clsButtonFace"
						href="javascript:disableorg();" value="禁止" />
				</c:if>
			</security:success>
		</security:checkPermission>
	</c:if>
	<c:if test="${org.status !=1}">
		<security:checkPermission resourceKey="SM_CREATE_ORG">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace"
					href="javascript:goURL('create','pid');" value="新增" />
			</security:success>
		</security:checkPermission>
	</c:if>
	<c:if test="${org.type == 4}">
		<security:checkPermission resourceKey="SM_CONFIG_ORG_REGION">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace"
					href="javascript:goURL('regionConfig','orgId');" value="可访问区域" />
			</security:success>
		</security:checkPermission>
	</c:if>
	<c:if test="${!flag}">
		<c:if test="${org.status ==1}">
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:postURL('recover');" value="解禁" />
		</c:if>
	</c:if>
</webui:panel>
<br />
<webui:panel title="子组织列表" icon="../images/icon_list.gif">
	<sm:query var="childrenOrgs" beanName="orgManager"
		methodName="findChildrenOrgs">
		<sm:param value="${org.orgId}" type="java.lang.Long" />
	</sm:query>
	<webui:table items="childrenOrgs"
		action="${pageContext.request.contextPath}/sm/org.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%" var="cOrg" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" sortable="false"
		autoIncludeParameters="true">
		<webui:row>
			<webui:column property="name" title="组织名称" />
			<webui:column property="code" title="组织代码" />
			<webui:column property="parent" title="上级组织">
				<c:out value="${cOrg.parent.orgName}" />
			</webui:column>
			<webui:column property="status" title="状态">
				<webui:lookup code="status@SM_ORGANIZATION" value="${cOrg.status}" />
			</webui:column>
			<webui:column property="action" title="操作">
				<a href="<c:url value="/sm/org.do?act=view&orgId=${cOrg.orgId}"/>">查看</a>
				<a href="<c:url value="/sm/org.do?act=edit&orgId=${cOrg.orgId}"/>">编辑</a>
				<sm:org id="${cOrg.orgId}" var="cflag" />
				<c:if test="${cOrg.status !=1 && !cflag}">
					<a href="#" onclick="disable(<c:out value="${cOrg.orgId}"/>)">禁止</a>
				</c:if>
				<c:if test="${!cflag && cOrg.status ==1}">
			        <a href="#" onclick="recoverSingle(<c:out value="${cOrg.orgId}"/>)">解禁</a>
	          </c:if>
			</webui:column>
			<webui:column property="busAction" title="业务操作">
				<a href="<c:url value="/sm/org.do?act=create&pid=${cOrg.orgId}"/>">新增子组织</a>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
<form name="hiddenForm" method="post" /><script>
	function openTree(){
		flag = window.open ("<c:url value='/sm/dialog.do?act=parentOrg&orgId=${org.orgId}'/>","tree","height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=yes")
		flag.focus();
	}
	function disable(id){
		if(confirm("确定要禁止吗?")) {
		document.forms.ec.setAttribute("action", "<c:url value='/sm/org.do?act=disableSingle&orgId='/>" + id);
		loadOn();
		document.forms.ec.submit();
		}
	}
	
	function recoverSingle(id){
	    document.forms.ec.setAttribute("action", "<c:url value='/sm/org.do?act=recover&orgId='/>" + id);
		loadOn();
		document.forms.ec.submit();
	}
	function disableorg(){
		if(confirm("确定要禁止吗?")) {
		postURL('disableSingle');
		}
	}
	function goURL(act,param){
		loadOn();
		window.location = "<c:url value='/sm/org.do?act='/>" + act + "&" + param + "=<c:out value='${org.orgId}'/>";
	}
	function postURL(act){
		document.forms.ec.setAttribute("action", "<c:url value='/sm/org.do?orgId=${org.orgId}&act='/>" + act);
		loadOn();
		document.forms.ec.submit();
	}
	function check(){
		var checked =false ;
		var obj = document.getElementsByName("ids");
			for(var i = 0;i < obj.length; i++) {
				var e = obj[i];
					if (e.checked){
						checked=true;
						break;
					}
			}
		if(!checked) alert("请选择组织");
			return checked;
	}
	function submit_form(act){
		if(check()){
			document.forms.ec.setAttribute("action", "<c:url value='/sm/org.do?act='/>"+act);
			loadOn();
			document.forms.ec.submit();
		}
	}
<c:choose>
<c:when test="${param.flag == 'add'}">
   parent.leftFrame.tree.insertNewItem(<c:out value="${org.parent.orgId}"/>,<c:out value="${org.orgId}"/>,"<c:out value="${org.name}"/>",0,"book.gif","books_open.gif","books_close.gif","SELECT");
</c:when>
<c:when test="${param.flag == 'update'}">
   parent.leftFrame.tree.setItemText(<c:out value="${org.orgId}"/>,"<c:out value="${org.name}"/><c:if test="${org.status == 1}">(禁用)</c:if>");
</c:when>
<c:when test="${param.flag == 'move'}">
   parent.leftFrame.tree.moveItem(<c:out value="${org.orgId}"/>,<c:out value="${org.parentOrgId}"/>);
</c:when>
<c:when test="${empty param.flag}">
   if(parent.leftFrame.document.readyState == "complete"){
     var tree  = parent.leftFrame.tree;
     tree.selectItem("<c:out value="${org.orgId}"/>",false);
     tree.openItem("<c:out value="${org.orgId}"/>");
   }
</c:when>
</c:choose>
</script>