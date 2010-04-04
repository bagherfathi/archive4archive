<%@ page contentType="text/html; charset=GBK" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/tld.inc" %><%--
--%><c:set var="jspBegin"><%=System.currentTimeMillis()%></c:set><%--
--%>
<html>
<head>
<base   target="_self">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); 
%>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>报表</title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
</head>
<body align="center">
 
</body>
</html>