<%@page contentType="text/html;charset=gb2312"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title><bean:message  key="system.exception"/></title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>

</head>
<body>
  <html:errors/>
  <webui:panel title="label.system.error" >
	<textarea style="width:600;height:400"><c:out value='${exceptionStack}'/></textarea>
  </webui:panel>
  <table width="100%">
    <tr>
      <td align="center">
        <input type="button" class="dialogbutton" onclick="history.go(-1)" value="<bean:message  key="sysadmin.button.return"/>"/>
      </td>
    </tr>
</table>
</body>
</html>