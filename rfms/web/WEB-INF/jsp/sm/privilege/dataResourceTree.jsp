<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html>
<head>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bgcolor="#f5f5f5">
<webui:buildTree beanName="dataResourceTreeBuilder" var="root" />
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
		<webui:tree
		        root="${root}"
		        id="data"
		        type="com.ft.common.security.ResourceTreeNode"
		        indent="2" extend="2"
		        saveToCookie="false"
		        level="level"
		        closeFolderImg="../images/tree/jia.gif"
		        openFolderImg="../images/tree/jian.gif"
		        leafImg="../images/tree/space.gif">
		        <c:if test="${level==0}"><img align="absmiddle" src="../images/books_open.gif" />
		        <a href="<c:url value='/sm/queryDRPrivilege.do?}'/>" target="rightFrame">
		        	<c:out value='${data.nodeName}'/>
		        </a>
		        </c:if>
		        <c:if test="${level==1}"><img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
		        </c:if>
		        <c:if test="${level==2}"><img align="absmiddle" src="../images/book.gif" />
		        	<a href="<c:url value='/sm/queryDRPrivilege.do?act=queryDataResourcePrivilege&id=${data.key}'/>" target="rightFrame">
		        		<c:out value='${data.nodeName}'/>
		        	</a>
		        </c:if>
		</webui:tree>
	</td>
   </tr>
</table>
</body>
</html>
