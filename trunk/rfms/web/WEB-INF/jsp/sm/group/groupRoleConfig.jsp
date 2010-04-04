<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/options.js"/>"></script>
<%@ page import="com.ft.commons.tree.BaseTreeNode"%>
<html:form action="groupRole">
	<input type="hidden" name="act" value="saveRole" />
	<input type="hidden" name="id"
		value="<c:out value='${group.groupId}'/>" />
	<webui:panel title="操作员组'${group.name}'的功能角色设置"
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
					<c:if test="${empty allOrgs}">&nbsp;<font color="red">请先</font><a
							href="<c:url value="/sm/groupOrg.do?act=configOrg&id=${group.groupId}"/>"><font
							color="red">设置可访问组织</font></a>
					</c:if>
				</webui:input>
			</tr>

			<tr>
				<webui:input label="设置功能角色" colspan="4">
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
									<c:set var="flag" value="false" />
									<c:forEach items="${groupRoles}" var="rolettt">
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
					</table>
				</webui:input>
			</tr>
		</webui:formTable>

		<webui:linkButton styleClass="clsButtonFace" href="#"
			onClick="if(validateOrgId()){groupRoleForm.submit()}" value="保存" />
		<webui:linkButton styleClass="clsButtonFace" href="javascript:back()"
			value="返回" />
	</webui:panel>
</html:form>

<script>
    function back(){
       location.href="<c:url value="/sm/group.do?act=view&selectedPane=role&id=${group.groupId}"/>"
    }
   function changeOrg(orgId){
     location.href = "<c:url value="/sm/groupRole.do?act=configRole&id=${group.groupId}&orgId="/>" + orgId ;
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
    function validateOrgId(){
       var orgId = document.getElementById("orgId").value;
       if(orgId == ""){
           alert("请先选择组织");
           return false;
       }else{
       return true;
       }
    } 
</script>
