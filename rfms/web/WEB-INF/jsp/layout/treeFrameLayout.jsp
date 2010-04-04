<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>

<tiles:useAttribute id="title" name="title" ignore="true"/>
<tiles:useAttribute id="left" name="left" ignore="true"/>
<tiles:useAttribute id="main" name="main" ignore="true"/>

<%
String queryString = request.getQueryString();
if(queryString == null || queryString.length()==0){
   queryString = "";
 }else{
 	queryString = "?"+queryString;
 }
 pageContext.setAttribute("queryString",queryString);
%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>
<frameset rows="*" cols="162,*" frameborder="yes" border="1">
	<frame src="<c:url value="${left}${queryString}"/>" name="leftFrame" style="border-right:1px #C3DEF7 solid;" scrolling="auto" border="1"  bordercolor="0D95E7">
	<frame src="<c:url value="${main}${queryString}"/>" name="rightFrame">
</frameset>  
<noframes><body>
</body></noframes>
</html>
