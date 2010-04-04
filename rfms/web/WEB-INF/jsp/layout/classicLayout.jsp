<%@ page contentType="text/html; charset=GBK" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/tld.inc" %><%--
--%><c:set var="jspBegin"><%=System.currentTimeMillis()%></c:set><%--
--%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<title><tiles:getAsString name="title" ignore="true"/></title>
	<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
</head>
<body>
<table width="100%">
      <logic:messagesPresent message="false">
		<tr>
		    <td  align="left" valign="top">
		        <font color="red"><html:errors/></font>	
		    </td>
		</tr>
	 </logic:messagesPresent>
	 <logic:messagesPresent message="true">
		<tr>
		    <td align="left" valign="top">	
                  <html:messages id="message" message="true">
                    <span id="success"><font color="red"><c:out value="${message}"/></font></span>
                  </html:messages>
		    </td>
		</tr>
	  </logic:messagesPresent>
<table>
<table width="100%" height="100%" border="0" align="center" cellspacing="0">
		<tr><td width="100%" align="center" valign="top">	
			<tiles:insert attribute="top"/>
			<br>
			<tiles:insert attribute="body"/>
		</td></tr>
</table>
<div id=divProcessing style="z-index:9998;width:100%;height:100%;position:absolute;left:0px;top:0px;display:none;filter:Alpha(opacity=0);"> 
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</div>
<div id=divSearch style="z-index:9999;width:350;height:80;position:absolute;left:250px;top:110px;display:none;border:1 #3E85EB solid;background-color:#EBF5FF;"> 
  <div style="position:absolute;width:320;height:19;left:15px;top:15px;border:1 #707888 solid;overflow:hidden">
	<div style="position:absolute;top:-1;left:0" id="pimg">
	</div>
  </div>
  <div  style="position:absolute;top:50;left:90;font-size:9pt;color:#000000" id="abc">
   ......执行中，请稍候......
  </div>
</div> 
</body>
</html>