<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/dataRole.do" onsubmit="return validateDataRoleForm(this);">
<html:hidden property="act" value="save"/>
<html:hidden property="role.type" value="2"/>
<webui:panel title="sysadmin.label.new.role" width="95%">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.role.name" required="true" colspan="4">
				<html:text property="role.roleName" size="25"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.role.desc" colspan="4">
				<html:textarea property="role.description" styleClass="wid80" rows="3"/>
			</webui:input>
		</tr>
	    <tr>
			<webui:input label="选择业务权限" colspan="4">
				<table width="95%"><tr><td>
			<webui:buildTree beanName="dataResourceTreeBuilder" var="root" />
		      	<webui:tree
		        root="${root}"
		        id="data"
		        type="com.ft.common.security.ResourceTreeNode"
		        indent="1" extend="1"
		        saveToCookie="false"
		        level="level"
		        closeFolderImg="../images/tree/jia.gif"
		        openFolderImg="../images/tree/jian.gif"
		        leafImg="../images/tree/space.gif">
		        <c:if test="${level==0}">
		        	<img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
		        </c:if>
		        <c:if test="${level!=0}">
		        	<c:if test="${level == 1}">
				        <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
					</c:if>
		        	<c:if test="${level == 2}">
		        		<c:if test="${data.single == 2}">
			        		<input class="noborder" type="checkbox" id="ids" name="<c:out value='${data.group}'/>" value="<c:out value='${data.key}'/>"/>
					        <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
				        </c:if>
				        <c:if test="${data.single == 1}">
			        		<input class="noborder" type="radio" id="ids" name="<c:out value='${data.group}'/>" value="<c:out value='${data.key}'/>" onMouseDown="isChecked(this);" onclick="return false;"/>
					        <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
				        </c:if>
		        	</c:if>
		        </c:if>
		    	</webui:tree></td></tr></table>
			</webui:input>
		</tr>
		</webui:formTable>
		
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(dataRoleForm);" value="保存"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="dataRoleForm" />
<script>
	function isChecked(input){
		if(input.checked == true){
			input.checked=false;
		}else{
			input.checked= true;
		}
	}
	function toReturn(){
		window.location = "<c:url value='/sm/dataRole.do'/>";
	}
</script>