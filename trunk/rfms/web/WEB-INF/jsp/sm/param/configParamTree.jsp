<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bgcolor="#f5f5f5">
<webui:buildTree beanName="configParamTreeBuilder" var="root" />
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <webui:tree 
        root="${root}"
        id="data"
        type="BaseTreeNode"
        indent="3" extend="3"
        saveToCookie="false"
        level="level"
        closeFolderImg="../images/tree/jia.gif"
        openFolderImg="../images/tree/jian.gif"
        leafImg="../images/tree/space.gif">
        <c:if test="${level==0}"><img align="absmiddle" src="../images/books_open.gif" /><a href='<c:url value="/sm/param.do?parentId=${data.key}"/>' target="rightFrame"><c:out value='${data.nodeName}'/></a>
        </c:if>
        <c:if test="${level!=0}">
            <c:if test="${empty data.children}">
                <img align="absmiddle" src="../images/book.gif" /><a href='<c:url value="/sm/param.do?parentId=${data.key}"/>' target="rightFrame"><c:out value='${data.nodeName}'/></a>
            </c:if>
            <c:if test="${!empty data.children}">
                <img align="absmiddle" src="../images/books_close.gif" /><a href='<c:url value="/sm/param.do?parentId=${data.key}"/>' target="rightFrame"><c:out value='${data.nodeName}'/></a>
            </c:if>
        </c:if>
      </webui:tree>
    </td>
  </tr>
</table>
</body>
</html>