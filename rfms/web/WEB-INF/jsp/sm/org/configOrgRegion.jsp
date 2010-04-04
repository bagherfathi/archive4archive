<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<webui:buildTree beanName="regionTreeBuilder" var="root">
    <webui:buildTreeParam name="showLevel" type="java.lang.Long" value="3"/>
</webui:buildTree>
<webui:panel title="组织可访问的区域">
<br/>
<script src="<c:url value="/js/resource.js"/>"></script>
<html:form action="org" >
<html:hidden property="act" value="regionSave"/>
<input type="hidden" name="orgId" value="<c:out value='${org.orgId}'/>"/>

 <table width="30%" valign="top" align="center" border="0" cellpadding="0" cellspacing="0">
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
        <sm:regionPath var="flag" regionList="${regions}" regionPath="${data.object.regionPath}"/>
      	<input class="noborder" type="checkbox" name="regionIds" value="<c:out value='${data.key}'/>" <c:if test="${flag}">checked</c:if> 
      	  <c:if test="${data.status ==1 }">disabled title=此区域已禁止</c:if>
      	  onclick="autoCheck(this)" id="id<c:out value='${data.object.regionPath}'/>" />
      	<c:if test="${empty data.parent}">
            <img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${!empty data.parent}">
            <c:if test="${empty data.children}">
                 <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
            </c:if>
            <c:if test="${!empty data.children}">
                 <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
            </c:if>
        </c:if>
        <script lanuage=javascript>
				checkBoxIds[index++]="id<c:out value='${data.object.regionPath}'/>";
        </script>
      </webui:tree>
    </td>
  </tr>
  <tr>
    <td><br/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:orgForm.submit();" value="保存"/>
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="返回" />
    </td>
  </tr>
</table>
</html:form>
</webui:panel>
<script>
    function back(){
        location.href = "<c:url value="/sm/org.do?act=view&orgId=${org.orgId}"/>"
    }
</script>
