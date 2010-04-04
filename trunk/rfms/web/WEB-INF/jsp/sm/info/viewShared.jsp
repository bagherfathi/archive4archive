<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/infoShared">
	<input type="hidden" name="act" value="delete"/>
	<input type="hidden" name="id" value="<c:out value='${infoShared.infoId}'/>"/>
	<webui:panel title="sysadmin.label.shared.content" width="95%" icon="../images/icon_list.gif">
	<table width="95%">
	<tr>
		<td align="center">
			<table width="95%">
			<tr>
			</tr>
			<tr width="100%">
				<td width="15%" rowspan="2">
					<c:out value="${infoShared.operator.opName}"/>
				</td>
				<td width="85%">
					标题:<c:out value="${infoShared.title}"/>&nbsp;
					时间:<c:out value="${infoShared.publishTime}"/>
				</td>
			<tr>
			<tr>
				<td></td>
				<td class="td_normal">
					<c:out value="${infoShared.content}"/>
					<c:if test="${attach != null}">
					<br></br>
					<img width="16" height="16" src="<c:url value='/images/icon/new.png'/>" border="0"/><a href="<c:url value='/sm/infoShared.do?act=download&aId=${attach.attachId}'/>"><c:out value="${attach.fileName}"/></a>
					</c:if>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<c:if test="${infoShared.authorId == op.operatorId}">
		<security:checkPermission resourceKey="SM_EDIT_INFO_SHARED">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:toEdit();" value="sysadmin.button.edit.shared"/>
			</security:success>
		</security:checkPermission>
	</c:if>
	<c:if test="${infoShared.authorId == op.operatorId}">
		<security:checkPermission resourceKey="SM_DELETE_INFO_SHARED">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:toDelete();" value="sysadmin.button.delete.shared"/>
			</security:success>
		</security:checkPermission>
	</c:if>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
	function toEdit(){
		window.location = "<c:url value='/sm/infoShared.do?act='/>" + "toEdit" + "&" + "id" + "=<c:out value='${infoShared.infoId}'/>";
	}
	function toDelete(){
		if(window.confirm("<bean:message key='msg.confirm.delete.info.shared'/>")){
      		document.infoSharedForm.submit();
      	}
	}
</script>