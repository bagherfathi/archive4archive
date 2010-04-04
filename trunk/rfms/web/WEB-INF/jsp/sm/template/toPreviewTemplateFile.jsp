<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
<html>
<title>Ä£°æÔ¤ÀÀ</title>
<body>
<table align="center" width="95%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="95%">
		<td align="center">
		<table border="1" cellpadding="3" cellspacing="0" class="tb-list"
			width="100%" height="100%">
			<tr>
				<td><iframe width="100%" height="100%" frameborder="0"
					src="<c:url value='/sm/templateFile.do?act=preview&&templateFileId=${param.fileId }' />"></iframe></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center"><br/><webui:linkButton styleClass="clsButtonFace"
			href="javascript:window.close();" value="sysadmin.button.close" /></td>
	</tr>
</table>
</body>
</html>




