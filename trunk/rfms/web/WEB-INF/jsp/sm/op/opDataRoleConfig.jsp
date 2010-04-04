<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/options.js"/>"></script>
<%@ page import="com.ft.commons.tree.BaseTreeNode"%>
<html:form action="opDataRole" method="post">
	<input type="hidden" name="act" value="saveRole" />
	<input type="hidden" name="opId"
		value="<c:out value='${op.operatorId}'/>" />
	<input type="hidden" name="loginName" value="<c:out value='${operatorRoleForm.loginName}'/>" />
    <input type="hidden" name="name" value="<c:out value='${operatorRoleForm.name}'/>" />
    <input type="hidden" name="orgId_s" value="<c:out value='${operatorRoleForm.orgId_s}'/>" />
	<webui:panel title="操作员'${op.loginName}'的业务角色设置"
		icon="../images/icon_list.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="当前组织" colspan="4">
					<select name="orgId" id="orgId" onchange="changeOrg(this.value)">
						<c:forEach items="${allOrgs}" var="o">
							<option value="<c:out value="${o.orgId}"/>"
								<c:if test="${o.orgId==org.orgId}">selected</c:if>><c:out
								value="${o.name}" /></option>
						</c:forEach>
					</select>
					<img src="<c:url value="/images/icon_sel.gif"/>"  onclick="openDialog('<c:url value="/" />','orgId','当前组织');"/>
					<c:if test="${empty allOrgs}">&nbsp;<font color="red">请先</font><a
							href="<c:url value="/sm/opOrg.do?act=configOrg&opId=${op.operatorId}"/>"><font
							color="red">设置可访问组织</font></a>
					</c:if>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="选择业务角色" colspan="4">
					<table width="95%">
						<tr>
							<td><webui:tree root="${root}" id="data" type="BaseTreeNode"
								indent="2" extend="2" saveToCookie="false" level="level"
								closeFolderImg="../images/tree/jia.gif"
								openFolderImg="../images/tree/jian.gif"
								leafImg="../images/tree/space.gif">
								<c:if test="${level==0}">
									<img align="absmiddle" src="../images/books_open.gif" />
									<c:out value='${data.nodeName}' />
								</c:if>
								<c:if test="${level!=0}">
								    <c:if test="${!empty data.parent}">
										<c:if test="${empty data.children}">
											<img align="absmiddle" src="../images/book.gif" />
										</c:if>
										<c:if test="${!empty data.children}">
											<img align="absmiddle" src="../images/books_close.gif" />
										</c:if>
									</c:if>
									<c:set var="flag" value="false" />
									<c:forEach items="${opRoles}" var="rolettt">
										<c:if test="${rolettt.roleId == data.key}">
											<c:set var="flag" value="true" />
										</c:if>
									</c:forEach>
									<input class="noborder" type="checkbox" name="roleIds"
										value="<c:out value="${data.key}"/>"
										<c:if test="${flag}">checked</c:if> />
									<c:out value='${data.nodeName}' />
								</c:if>
							</webui:tree></td>
						</tr>
						<tr>
						    <td>
						        <a href="#" onClick="javascript:checkAll(document.all.roleIds)">[全选]</a>
						        &nbsp;<a href="#" onClick="javascript:unCheckAll(document.all.roleIds)">[全部取消]</a>
						    </td>
						</tr>
					</table>
				</webui:input>
			</tr>
		</webui:formTable>

		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:operatorRoleForm.submit()" value="保存" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:javascript:back();" value="返回" />
	</webui:panel>
</html:form>

<script>
   function changeOrg(orgId){
     location.href = "<c:url value="/sm/opDataRole.do?act=configRole&opId=${op.operatorId}&loginName=${operatorRoleForm.loginName}&name=${operatorRoleForm.name}&orgId_s=${operatorRoleForm.orgId_s}&listOp_p=${currentPage_listOp}&orgId="/>" + orgId ;
   }
    function back(){
     location.href = "<c:url value="/sm/op.do?act=view&selectedPane=dataRole&opId=${op.operatorId}&loginName=${operatorRoleForm.loginName}&name=${operatorRoleForm.name}&orgId_s=${operatorRoleForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"; 
   }
     function checkSelect(sourceId,targetId){
 	  source = document.getElementById(sourceId);
 	  if(source.selectedIndex<0){
      	alert("请选择");
      	return false;
      }
 	  swapSelected(sourceId,targetId );
    }  
     function moveAll(sourceId, targetId){
      var source = document.getElementById(sourceId);
      var target = document.getElementById(targetId);
      swapAll(source,target);
    } 
</script>
