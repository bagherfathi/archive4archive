<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/tld/c.tld"   prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/webui.tld"   prefix="webui" %>
<%@ page import="com.ft.common.session.OperatorSessionHelper"%>
<%@ page import="com.ft.sm.dto.OperatorDTO;"%>

<%
    String jobnumber = "";
    String orgName = "";
    String opName = "" ;
    OperatorDTO currentUser = OperatorSessionHelper.getLoginOp(request);
    
    if(currentUser != null){
        jobnumber = currentUser.getJobNumber();
        orgName = currentUser.getDepartment().getOrgName();
        opName = currentUser.getName();
    }
    
    if(jobnumber == null) jobnumber="";
%>
<html>
<head>
<title>BOSS</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" href="css/style.css" type="text/css">
<script language="JavaScript">

function switchMainTop() {
	top.switchHeader();
}
function switchLeftMenu(){
	var workFrame = parent.document.getElementById('workFrame');
	switch(workFrame.cols){
	  case "150,*,18" : workFrame.cols="0,*,18";break;
	  case "0,*,18" : workFrame.cols="150,*,18";break;
	  case "150,*,150" : workFrame.cols="0,*,150";break;
	  case "0,*,150" : workFrame.cols="150,*,150";break;
	}
}

</script>
</head>
<body text="#000000" leftmargin="0" topmargin="0" class="frame_bg_bottom">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="50">&nbsp;</td>
    <td width="24" align="left" id="menuSwitch" style="cursor: hand" onClick='switchMainTop()'>&nbsp;<img src="images/display.gif" align="absmiddle" id="maketop" border="0" title="隐藏顶部菜单"></td>
    <td width="24" id="menuSwitch" style="cursor: hand" onClick='switchLeftMenu()'>&nbsp;<img src="images/arrowleft.gif" align="absmiddle" id="makeleft" border="0" title="隐藏左边菜单"></td>
    <td style="color:#333333 ">&nbsp;<webui:menuNav menuId="${param.menuName}" />&nbsp;</td>
    <td width="50%" align="right" style="color:#333333 ">姓名:&nbsp;<%=opName%>&nbsp;&nbsp;|&nbsp;&nbsp;工号:&nbsp;<%=jobnumber%>&nbsp;&nbsp;|&nbsp;&nbsp;所属组织:&nbsp;<%=orgName%>&nbsp;</td>
  </tr>
</table>
</body>
</html>
