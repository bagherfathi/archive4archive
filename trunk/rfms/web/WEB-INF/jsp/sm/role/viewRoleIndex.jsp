<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>
<webui:panel title="功能角色查看">
<html:form action="/role.do" onsubmit="return validateRoleForm(this);">
<html:hidden property="act" value="toEdit"/>
<input type="hidden" name="id" value="<c:out value='${param.roleId}'/>"/>
<webui:tabContainer id="foo-bar-container">
	<webui:tabPane id="roleInfo" tabTitle="功能角色信息">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/roleInfo.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="resourceTree" tabTitle="角色功能权限">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/roleResourceTree.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="opList" tabTitle="操作员列表">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/roleOpList.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="orgs" tabTitle="适用组织">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/roleOrgs.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
</html:form>
<security:checkPermission resourceKey="SM_EDIT_ROLE">
	<security:success>
		<c:if test="${role.roleId != 1}">
			<webui:linkButton styleClass="clsButtonFace" href="javascript:goURL('toEdit','id');" value="sysadmin.button.edit"/>
		</c:if>
	</security:success>
	</security:checkPermission>
	<security:checkPermission resourceKey="SM_DELETE_ROLE">
	<security:success>
	<c:if test="${role.roleId != 1}">
	<sm:role id="${role.roleId}" var="flag"/>
		<c:if test="${!flag}">
				<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:goDel('delete','id');" value="sysadmin.button.delete"/>
		</c:if>
	</c:if>
	</security:success>
	</security:checkPermission>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<script>
	function goURL(act,param){
		window.location = "<c:url value='/sm/role.do?act='/>" + act + "&" + param + "=<c:out value='${role.roleId}'/>";
    }
	function goDel(act,param){
		if(window.confirm("<bean:message key='msg.confirm.delete.role'/>")){
			window.location = "<c:url value='/sm/role.do?act='/>" + act + "&" + param + "=<c:out value='${role.roleId}'/>";
		}
	}
	function toReturn(){
		window.location = "<c:url value='/sm/role.do'/>";
	}
</script>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="roleForm" />