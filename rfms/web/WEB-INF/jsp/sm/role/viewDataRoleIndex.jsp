<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>
<webui:panel title="sysadmin.title.view.data.role" width="95%">
<html:form action="/dataRole.do" onsubmit="return validateRoleForm(this);">
	<html:hidden property="act" value="toEdit"/>
	<input type="hidden" name="id" value="<c:out value='${param.roleId}'/>"/>
<webui:tabContainer id="foo-bar-container">
	<webui:tabPane id="roleInfo" tabTitle="业务角色信息">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/dataRoleInfo.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="resourceTree" tabTitle="业务角色功能权限">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/dataRoleResourceTree.jsp" %>
	</webui:tabPane>
	<webui:tabPane id="opList" tabTitle="操作员列表">
	   <%@include file="/WEB-INF/jsp/sm/role/inc/dataRoleOpList.jsp" %>
	</webui:tabPane>
</webui:tabContainer>
</html:form>
<security:checkPermission resourceKey="SM_EDIT_DATA_ROLE">
	<security:success>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:goURL('toEdit','id');" value="sysadmin.button.edit"/>
	</security:success>
</security:checkPermission>
<security:checkPermission resourceKey="SM_DELETE_DATA_ROLE">
	<security:success>
		<sm:role id="${role.roleId}" var="flag"/>
			<c:if test="${!flag}">
				<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:goDel('delete','id');" value="sysadmin.button.delete"/>
			</c:if>
		</security:success>
	</security:checkPermission>
<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="roleForm" />
<script>
	function goURL(act,param){
		window.location = "<c:url value='/sm/dataRole.do?act='/>" + act + "&" + param + "=<c:out value='${role.roleId}'/>";
	}
       
	function goDel(act,param){
		if(window.confirm("<bean:message key='msg.confirm.delete.data.role'/>")){
			window.location = "<c:url value='/sm/dataRole.do?act='/>" + act + "&" + param + "=<c:out value='${role.roleId}'/>";
		}
	}
	function toReturn(){
		window.location = "<c:url value='/sm/dataRole.do'/>";
	}
</script>