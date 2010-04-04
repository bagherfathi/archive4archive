<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/appThemeCssLink.inc" %>

<html>
<head>
<title>瑞富通银--商户管理系统</title>

</head>
<body leftmargin="0" topmargin="0" id="Indexbody" height="100%">
<TABLE cellSpacing="0" cellPadding="0" border="0" height="100%">
  <TBODY>
  <TR>
    <TD colSpan=3>
<menu:useMenuDisplayer name="Velocity" config="/templates/menu/tabMenu_new.vm" >
    <webui:displayMenu name="SystemMenu" attribute="system.menu.Session" scope="session"/>
</menu:useMenuDisplayer>
	  </TD>
	 </TR>
    </TBODY>
</TABLE>
 
</BODY></HTML>