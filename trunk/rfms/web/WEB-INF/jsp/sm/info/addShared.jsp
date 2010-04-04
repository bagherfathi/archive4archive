<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value="/js/resource.js"/>"></script>
<script src="<c:url value="/js/date.js"/>"></script>
<html:form action="/infoShared" enctype="multipart/form-data" onsubmit="return onSubmit(this);">
	<input type="hidden" name="act" value="create"/>
	<html:hidden property="category.categoryId"/>
	<webui:panel title="sysadmin.title.info.shared.publish" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.info.shared.title" required="true" colspan="3">
					<html:text property="infoShared.title"/>
				</webui:input>
			</tr>
			<%--
			<tr>
				<webui:input label="sysadmin.label.shared.validTime">
				   	<input type="text" name="infoShared.validTime" id="validTime" value='<fmt:formatDate value="${infoSharedForm.infoShared.validTime}" pattern="yyyy-MM-dd hh:mm:ss"/>' readonly="true"/>
				   	<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(validTime);return false;">
				</webui:input>
				<webui:input label="sysadmin.label.shared.expireTime">
				   	<input type="text" name="infoShared.expireTime" id="expireTime" value='<fmt:formatDate value="${infoSharedForm.infoShared.expireTime}" pattern="yyyy-MM-dd hh:mm:ss"/>' readonly="true"/>
				   	<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(expireTime);return false;">
				</webui:input>
			</tr>
			--%>
			<tr>
				<webui:input label="sysadmin.label.shared.attach" colspan="3">
					<input name="attachFile" size="40" type="file">
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.shared.content" required="true" colspan="3">
					<html:textarea property="infoShared.content" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
		</webui:formTable>
		</br>
		<webui:buildTree beanName="orgTreeBuilder" var="root" />
		<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<bean:message key="sysadmin.label.affiche.orgs"/>
			</tr>
			<tr>
				<td>
					<webui:tree 
						root="${root}" 
						id="data" 
						type="com.ft.commons.tree.BaseTreeNode"
						indent="2" extend="2" 
						saveToCookie="false" 
						level="level" 
						closeFolderImg="../images/tree/jia.gif"
						openFolderImg="../images/tree/jian.gif"
						leafImg="../images/tree/space.gif">
						<input class="noborder" type="checkbox" name="ids" value="<c:out value='${data.key}'/>" id="a<c:out value='${data.object.path}'/>" onclick='autoCheck2(this)'>
						<img align="absmiddle" src="../images/tree/folder.gif" /><c:out value='${data.nodeName}'/>
						<script lanuage=javascript>
				       		checkBoxIds[index++]="a<c:out value='${data.object.path}'/>";
                        </script>
					</webui:tree>
				</td>
			</tr>
		</table>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmit(infoSharedForm);" value="sysadmin.button.publish"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="infoSharedForm" />
<script>	
	function onSubmit(input) {
      if(validateInfoSharedForm(input)){
      	document.forms.infoSharedForm.submit();
      }
  }
</script>