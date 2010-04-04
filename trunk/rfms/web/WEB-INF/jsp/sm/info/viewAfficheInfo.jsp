<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<c:if test="${!empty affiche}">
<script>
window.document.title="<c:out value='${affiche.title}'/>";
</script>
<table width="95%" border="0" cellspacing="0" cellpadding="0" >
<tr>
	<td align="center">
		<table border="1" cellpadding="3" cellspacing="0" class="tb-list" width="95%">
			<tr>
				<td class="td-detail"><c:out value="${affiche.title}"/></td>
			</tr>
			<tr>
				<td><c:out value="${affiche.validTime}"/></td>
			</tr>
			<tr>
				<td><textarea class="noborder" cols="75" rows="20" readonly="true" style="overflow-x:hidden;overflow-y:hidden"><c:out value='${affiche.content}'/></textarea></td>
			</tr>
		</table>
	</td>
</tr>
</table>
</c:if>

<c:if test="${empty affiche}">
<table width="95%" border="0" cellspacing="0" cellpadding="0" >
<tr>
	<td align="center">
		<table border="1" cellpadding="3" cellspacing="0" class="tb-list" width="95%">
			<tr>
				<td class="td-detail">该公告已被删除</td>
			</tr>
		</table>
	</td>
</tr>
</table>
</c:if>
</br>
<webui:linkButton styleClass="clsButtonFace" href="javascript:window.close();" value="sysadmin.button.close"/>
