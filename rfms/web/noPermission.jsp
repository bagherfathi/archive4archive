<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/tld/c.tld"   prefix="c" %>
<HTML>
<HEAD>
<TITLE>没有权限</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GBK">
<link rel="stylesheet" href="../include/style.css" type="text/css">
</HEAD>
<BODY BGCOLOR=#e9e9e9 LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<br>
<table width=353 border=0 cellpadding=0 cellspacing=0 align="center">
  <tr> 
    <td colspan=3><img src='<c:url value="/images/gray_error_title.gif"/>' width=184 height=36 alt=""></td>
    <td colspan=3 background='<c:url value="/images/error_1.gif"/>'> <img src='<c:url value="/images/error_7.gif"/>' width=130 height=36 alt=""></td>
    <td rowspan="2" valign="top" background='<c:url value="/images/gray_error_3.gif"/>'> <img src='<c:url value="/images/gray_error_2.gif"/>' width=29 height=26 alt=""> 
    </td>
  </tr>
  <tr> 
    <td background='<c:url value="/images/error_4.gif"/>'>&nbsp; </td>
    <td colspan=5 valign="middle" background='<c:url value="/images/error_bg.gif"/>' height="154"  class="errmsg">
			对不起，您没有权限进行这个操作。
    </td>
  </tr>
  <tr> 
    <td colspan=7> <img src='<c:url value="/images/gray_error_5.gif"/>' width=353 height=27 alt="" usemap="#Map" border="0"></td>
  </tr>
  <tr> 
    <td colspan=2>&nbsp; </td>
    <td colspan=2> <img src='<c:url value="/images/gray_error_6.gif"/>' width=89 height=63 alt=""></td>
    <td colspan=3>&nbsp; </td>
  </tr>
  <tr> 
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=26 height=1 alt=""></td>
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=90 height=1 alt=""></td>
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=68 height=1 alt=""></td>
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=21 height=1 alt=""></td>
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=109 height=1 alt=""></td>
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=10 height=1 alt=""></td>
    <td> <img src='<c:url value="/images/spacer.gif"/>' width=29 height=1 alt=""></td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="258,2,320,21" href="javascript:history.go(-1)">
</map>
</BODY>
</HTML>
