<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode"%>
<script src="<c:url value="/js/resource.js"/>"></script>
<html:form action="/role.do" onsubmit="return onSave(this);">
	<html:hidden property="act" value="edit" />
	<input type="hidden" name="id" value="<c:out value='${role.roleId}'/>" />
	<webui:panel title="sysadmin.label.edit.role" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.role.name" required="true" colspan="4">
					<html:text property="role.roleName" size="25"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.role.desc" colspan="4">
					<html:textarea property="role.description" styleClass="wid80"
						rows="3" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="选择功能权限" colspan="4">
					<table width="95%">
						<tr>
							<td><webui:buildTree beanName="resourceTreeBuilder"
								var="root" /> <sm:query var="resources"
								beanName="resourceManager"
								methodName="findAllCheckedResourceByRoleId">
								<sm:param type="java.lang.Long" value="${role.roleId}" />
							</sm:query> <webui:tree root="${root}" id="data"
								type="com.ft.common.security.ResourceTreeNode"
								indent="2" extend="1" saveToCookie="false" level="level"
								closeFolderImg="../images/tree/jia.gif"
								openFolderImg="../images/tree/jian.gif"
								leafImg="../images/tree/space.gif">
								<c:if test="${level==0}">
									<img align="absmiddle" src="../images/books_open.gif" />
									<c:out value='${data.nodeName}' />
								</c:if>
								<c:if test="${level!=0}">
									<c:set var="isChecked" value="false" />
									<c:forEach items="${resources}" var="resource">
										<c:if test="${resource.resourceId == data.key}">
											<c:set var="isChecked" value="true" />
										</c:if>
									</c:forEach>

									<c:if test="${isChecked == true}">
										<input class="noborder" type="checkbox" name="ids"
											value="<c:out value='${data.key}'/>"
											id="a<c:out value='${data.path}'/>" onclick='autoCheck(this)'
											checked="true" />
										<c:if test="${empty data.children}">
											<img align="absmiddle" src="../images/book.gif" />
											<c:out value='${data.nodeName}' />
										</c:if>
										<c:if test="${!empty data.children}">
											<img align="absmiddle" src="../images/books_close.gif" />
											<c:out value='${data.nodeName}' />
										</c:if>
									</c:if>
									<c:if test="${isChecked == false}">
										<input class="noborder" type="checkbox" name="ids"
											value="<c:out value='${data.key}'/>"
											id="a<c:out value='${data.path}'/>" onclick='autoCheck(this)' />
										<c:if test="${empty data.children}">
											<img align="absmiddle" src="../images/book.gif" />
											<c:out value='${data.nodeName}' />
										</c:if>
										<c:if test="${!empty data.children}">
											<img align="absmiddle" src="../images/books_close.gif" />
											<c:out value='${data.nodeName}' />
										</c:if>
									</c:if>
									<script lanuage=javascript>
						checkBoxIds[index++]="a<c:out value='${data.path}'/>";
					</script>
								</c:if>
							</webui:tree></td>
						</tr>
					</table>
				</webui:input>
			</tr>
			<tr>
			<sm:buildOrgsTree var="orgRoot" orgType="200"></sm:buildOrgsTree>
				<webui:input label="选择适用组织" colspan="4">
					<table width="95%">
						<tr>
							<td>
							<webui:tree root="${orgRoot}" id="data" type="BaseTreeNode"
								indent="2" extend="2" saveToCookie="false" level="level"
								closeFolderImg="../images/tree/jia.gif"
								openFolderImg="../images/tree/jian.gif"
								leafImg="../images/tree/space.gif" treeName="org">
								<c:if test="${level==0}">
									<img align="absmiddle" src="../images/books_open.gif" />适用组织
								</c:if>
								<c:if test="${level!=0}">
									<c:set var="flag" value="false" />
									<c:forEach var="org" items="${orgs}">
										<c:if test="${ org.orgId==data.key }">
											<c:set var="flag" value="true" />
										</c:if>
									</c:forEach>
									<input class="noborder" type="checkbox" name="orgIds"
										value="<c:out value='${data.key}'/>"
										<c:if test="${data.status == 1}">disabled title="组织已禁止"</c:if>
										<c:if test="${flag}">checked</c:if> />
								        <c:if test="${empty data.children}">
										    <img align="absmiddle" src="../images/book.gif" />
									    </c:if>
									    <c:if test="${!empty data.children}">
										    <img align="absmiddle" src="../images/books_close.gif" />
									    </c:if>
                                        <c:out value='${data.nodeName}' />
								</c:if>
							</webui:tree></td>
						</tr>
						<tr>
						    <td>
						        <a href="#" onClick="javascript:checkAll(document.all.orgIds)">[全选]</a>
						        &nbsp;<a href="#" onClick="javascript:unCheckAll(document.all.orgIds)">[全部取消]</a>
						    </td>
						</tr>
					</table>
				</webui:input>
			</tr>

		</webui:formTable>
	</webui:panel>
	<table width="95%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="right"><webui:linkButton standOnly="true"
				styleClass="clsButtonFace" href="javascript:submitForm(roleForm);"
				value="sysadmin.button.save" /> <webui:linkButton standOnly="true"
				styleClass="clsButtonFace" href="javascript:toReturn();"
				value="sysadmin.button.return" /></td>
		</tr>
	</table>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="roleForm" />
<script>
	function toReturn(){
		window.location = "<c:url value='/sm/role.do?act=view&id=${role.roleId}'/>";
	}
	
	function onSave(roleForm){
	    var ret = validateRoleForm(roleForm);
	    
	    if(ret){
	        if (!isChecked(document.forms.roleForm.ids, "请选择功能权限")){
	            return false;
	        }else if (!isChecked(document.forms.roleForm.orgIds, "请选择适用组织")){
	            return false;
	        }else{
	            return true
	        }
	    }
	}
</script>
