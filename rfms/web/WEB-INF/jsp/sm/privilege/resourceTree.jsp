<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.common.security.ResourceTreeNode" %>

<html>
<head>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bgcolor="#f5f5f5">
<webui:buildTree beanName="resourceTreeBuilder" var="root" />
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <webui:tree 
        root="${root}"
        id="data"
        type="ResourceTreeNode"
        indent="2" extend="1"
        saveToCookie="false"
        level="level"
        closeFolderImg="../images/tree/jia.gif"
        openFolderImg="../images/tree/jian.gif"
        leafImg="../images/tree/space.gif">
        <c:if test="${level==0}"><img align="absmiddle" src="../images/books_open.gif" /><a href="<c:url value='/sm/queryRPrivilege.do'/>" target="rightFrame"><c:out value='${data.nodeName}'/></a>
        </c:if>
        <c:if test="${level!=0}">
            <c:if test="${empty data.children}">
                <img align="absmiddle" src="../images/book.gif" /><a id="treeRow<c:out value='${data.key}${level}'/>" href="<c:url value='/sm/queryRPrivilege.do?act=queryResourcePrivilege&rId=${data.key}'/>" target="rightFrame" onclick="highLight(this)"><c:out value='${data.nodeName}'/></a>
            </c:if>
            <c:if test="${!empty data.children}">
                <img align="absmiddle" src="../images/books_close.gif" /><a id="treeRow<c:out value='${data.key}${level}'/>" href="<c:url value='/sm/queryRPrivilege.do?act=queryResourcePrivilege&rId=${data.key}'/>" target="rightFrame" onclick="highLight(this)"><c:out value='${data.nodeName}'/></a>
            </c:if>
        </c:if>
      </webui:tree>
    </td>
  </tr>
</table>
</body>
</html>
