<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/dataRole.do" onsubmit="return validateDataRoleForm(this);">
<html:hidden property="act" value="edit"/>
<html:hidden property="role.roleId"/>
<input type="hidden" name="id" value="<c:out value='${role.roleId}'/>"/>
    <webui:panel title="sysadmin.label.edit.role" width="95%">
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
	  		<sm:query var="entrys" beanName="resourceManager" methodName="findDataResourcesOfRole">
		  		<sm:param type="java.lang.Long" value="${role.roleId}" />
			</sm:query>
	  		<webui:buildTree beanName="dataResourceTreeBuilder" var="root" />
		    	<webui:tree
		        root="${root}"
		        id="data"
		        type="com.ft.commons.tree.BaseTreeNode"
		        indent="2" extend="2"
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
		        		<c:set var="entryChecked" value="false"/>

		        		<c:forEach items="${entrys}" var="entry">
		        			<c:if test="${entry.entryId == data.key}">
		        				<c:set var="entryChecked" value="true"/>
		        			</c:if>
		        		</c:forEach>

		        		<c:if test="${entryChecked == true}">
		        			<c:if test="${data.single == 2}">
	                			<input class="noborder" type="checkbox" id="ids" name="<c:out value='${data.group}'/>" value="<c:out value='${data.key}'/>" checked="true"/>
	                		</c:if>
	                		<c:if test="${data.single == 1}">
	                			<input class="noborder" type="radio" id="ids" name="<c:out value='${data.group}'/>" value="<c:out value='${data.key}'/>" checked="true" onMouseDown="isChecked(this);" onclick="return false;" />
	                		</c:if>
	                	</c:if>

		        		<c:if test="${entryChecked != true}">
	                		<c:if test="${data.single == 2}">
	                			<input class="noborder" type="checkbox" id="ids" name="<c:out value='${data.group}'/>" value="<c:out value='${data.key}'/>" />
	                		</c:if>
	                		<c:if test="${data.single == 1}">
	                			<input class="noborder" type="radio" id="ids" name="<c:out value='${data.group}'/>" value="<c:out value='${data.key}'/>" onMouseDown="isChecked(this);" onclick="return false;" />
	                		</c:if>
	                	</c:if>
	                	<img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>		        		
		        	</c:if>
		        </c:if>
		      </webui:tree>
	  		</td></tr></table>
				</webui:input>
			</tr>
		</webui:formTable>
		
	<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(dataRoleForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
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
		window.location = "<c:url value='/sm/dataRole.do?act=view&id=${role.roleId}'/>";
	}
</script>