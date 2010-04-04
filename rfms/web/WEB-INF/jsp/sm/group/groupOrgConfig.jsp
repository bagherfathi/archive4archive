<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<script src="<c:url value="/js/resource.js"/>"></script>
<webui:panel title="操作员组'${group.name}'可访问的组织"  icon="../images/icon_list.gif">
<br/>
<html:form action="groupOrg" >
<input type="hidden" name="act" value="saveOrg"/>
<input type="hidden" name="id" value="<c:out value='${group.groupId}'/>"/>
<sm:buildOrgsTree var="root" orgType="10"></sm:buildOrgsTree>
<table width="30%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <webui:tree 
        root="${root}" 
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="2" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif">
        <c:set var="flag" value="false"/>
        <c:if test="${empty data.parent}">
            <img align="absmiddle" src="../images/books_open.gif" />
        </c:if>
        <c:if test="${!empty data.parent}">
            <c:if test="${empty data.children}">
                <img align="absmiddle" src="../images/book.gif" />
            </c:if>
            <c:if test="${!empty data.children}">
                <img align="absmiddle" src="../images/books_close.gif" />
            </c:if>
        </c:if>
      <c:if test="${level !=0}">
        <c:set var="flag" value="false"/>
        
        <sm:orgPath var="flag" path="${data.object.path}" orgList="${accessOrgs}"></sm:orgPath>
        
         <input class="noborder" type="checkbox" name="orgIds" value="<c:out value='${data.key}'/>" 
         <c:if test="${data.status == 1}">disabled title="组织已禁止"</c:if> 
         <c:if test="${data.status == 2}">disabled title="组织不可访问"</c:if> 
         <c:if test="${flag}">checked</c:if> onclick="autoCheck(this)" id="id<c:out value='${data.object.path}'/>" />
         <script lanuage=javascript>
				checkBoxIds[index++]="id<c:out value='${data.object.path}'/>";
        </script>
      </c:if>
        <c:out value='${data.nodeName}'/>
      </webui:tree>
    </td>
  </tr>
  <tr>
    <td><br/>
     <webui:linkButton styleClass="clsButtonFace" href="javascript:groupOrgForm.submit();" value="保存"/>
     <webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="返回"/>
    </td>
  </tr>
</table>
</html:form>
</webui:panel>
<script>
    function back(){
       location.href="<c:url value="/sm/group.do?act=view&selectedPane=org&id=${group.groupId}"/>"
    }
</script>