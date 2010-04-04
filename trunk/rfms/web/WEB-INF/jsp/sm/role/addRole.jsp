<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode"%>
<script src="<c:url value="/js/resource.js"/>"></script>
<html:form action="/role.do" onsubmit="return onNext(this);">
	<html:hidden property="act" value="save" />
	<webui:panel title="sysadmin.label.new.role" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.role.name" required="true" colspan="4">
					<html:text property="role.roleName" size="25"/>
					<html:hidden property="role.type" value="1" />
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
					<webui:buildTree beanName="resourceTreeBuilder" var="root" />
					<table width="95%">
						<tr>
							<td><webui:tree root="${root}" id="data"
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
							<td><webui:tree root="${orgRoot}" id="data"
								type="BaseTreeNode" indent="2" extend="2" saveToCookie="false"
								level="level" closeFolderImg="../images/tree/jia.gif"
								openFolderImg="../images/tree/jian.gif"
								leafImg="../images/tree/space.gif" treeName="org">
								<c:if test="${level==0}">
									<img align="absmiddle" src="../images/books_open.gif" />适用组织
								</c:if>
								<c:if test="${level !=0}">
									<input class="noborder" type="checkbox" name="orgIds"
										id="orgIds" value="<c:out value='${data.key}'/>"
										<c:if test="${data.key==org.orgId }">checked=true</c:if>
										<c:choose>
                                         <c:when test="${data.status==1}">disabled title="此组织已禁止"</c:when>  
                                         <c:otherwise>title="<c:out value="${data.nodeName}"/>" </c:otherwise>
                                         </c:choose> 
                                        />
                                        <c:if test="${empty data.children}">
										    <img align="absmiddle" src="../images/book.gif" />
									    </c:if>
									    <c:if test="${!empty data.children}">
										    <img align="absmiddle" src="../images/books_close.gif" />
									    </c:if>
                                        <c:out value='${data.nodeName}' />
								</c:if> 
							</webui:tree>
							</td>
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
				styleClass="clsButtonFace"
				href="javascript:onSubmitValidator(roleForm);" value="保存" /> <webui:linkButton
				standOnly="true" styleClass="clsButtonFace"
				href="javascript:toReturn();" value="sysadmin.button.return" /></td>
		</tr>
	</table>

</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="roleForm" />
<script>
	function toReturn(){
		window.location = "<c:url value='/sm/role.do'/>";
	}
	
	function onNext(roleForm){
	    var ret = validateRoleForm(roleForm);
	    
	    if(ret){
	        if (!isChecked(document.forms.roleForm.ids, "请选择功能权限")){
	            return false;
	        }else if (!isChecked(document.forms.roleForm.orgIds, "请选择适用组织")){
	            return false;
	        }else{
	            return true
	        }
	    }else{
	        return false;
	    }
	}
</script>
