<%@ page contentType="text/html; charset=GBK" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/tld.inc" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/appThemeCssLink.inc" %><%--
--%> 
<html>
<head>

<title>
	×ó±ß²Ëµ¥
</title>


</head>
<body onkeydown="keylock(event)">
          
<% Object sess = session.getAttribute("system.menu.Session"); %>
<%if (sess!=null){%>
<c:set var="menuName" value="${param.menuname}"/>
<menu:useMenuDisplayer name="Velocity" config="/templates/menu/listMenu_new.vm" >
    <c:if test="${!empty menuName}">
    <webui:displayMenu name="${menuName}" attribute="system.menu.Session" scope="session"/>
    </c:if>
</menu:useMenuDisplayer>
<%}else{%>
    <script>
       top.location.href="<c:url value='/login.do'/>";
    </script>
<%}%>         
</body>


<script>
var currentLeftSrc = "";
var currentRightSrc = "";

function OpenLink(FileName_Left,FileName_Right)
{
    if(currentLeftSrc != FileName_Left && FileName_Left != "")
    {
        currentLeftSrc = FileName_Left;
        try
        {
            parent.currentLeftSrc = FileName_Left;
            parent.document.getElementById("left").src = FileName_Left + GetUrlParm(FileName_Left);
        }
        catch(err)
        {}
    }
    if(FileName_Right != "")
    {
        parent.document.getElementById("main_right").src = FileName_Right + GetUrlParm(FileName_Right);
    }
}

function GetUrlParm(url)
{
    var urlparm = "?";
    if(url.indexOf('?')>=0)
    {
        urlparm = "&";
    }
    urlparm = urlparm + "t=" + GetRandomNum();
    return urlparm;
}


function GetRandomNum()
{
        var Range = 1000;
        var Rand = Math.random();
        return (Math.round(Rand * Range));
}
</script>
</html>