<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/role.do" onsubmit="return validateRoleForm(this);">
<html:hidden property="act" value="save"/>
<webui:panel title="sysadmin.label.priview.role" width="95%">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.role.name">
				<html:text property="role.roleName" readonly="true"/>
				<html:hidden property="role.type"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.role.desc" colspan="3">
				<html:textarea property="role.description" styleClass="wid80" rows="3" readonly="true"/>
			</webui:input>
		</tr>
	</webui:formTable>
	<table width="95%">
		<tr>
		    <td>
	  			<webui:tree
		        root="${root}"
		        id="data"
		        type="com.ft.common.security.ResourceTreeNode"
		        indent="2" 
		        extend="2"
		        saveToCookie="false"
		        level="level"
		        closeFolderImg="../images/tree/jia.gif"
		        openFolderImg="../images/tree/jian.gif"
		        leafImg="../images/tree/space.gif">
			        <c:if test="${level==0}">
			        	<img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
			        </c:if>
			        <c:if test="${level!=0}">
			            <c:if test="${empty data.children}">  
		                    <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
                        </c:if>
                        <c:if test="${!empty data.children}">
                            <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
                        </c:if>
			        </c:if>
		        </webui:tree>
			</td>
		</tr>
	</table>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(roleForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="roleForm" />
<script>
	function toReturn(){
		window.location = "<c:url value='/sm/role.do?act=create'/>";
	}
</script>