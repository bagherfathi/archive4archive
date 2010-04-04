<%@ page language="java" contentType="text/html;charset=GBK"%>


<%
	String logoutUrl = request.getContextPath() + "/login.do";

    session.invalidate();
%>

<script language = "javascript">
    top.location.href="<%=logoutUrl%>";
</script>