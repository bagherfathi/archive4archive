<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value="/js/resource.js"/>"></script>
<script src="<c:url value="/js/date.js"/>"></script>
<html:form action="/infoShared" enctype="multipart/form-data" onsubmit="return onSubmit(this);">
	<input type="hidden" name="act" value="edit"/>
	<c:set var="infoShared" value="${infoSharedForm.infoShared}"/>
	<html:hidden property="infoShared.infoId"/>
	<input type="hidden" name="categoryId" value="<c:out value='${infoShared.categoryId}'/>"/>
	<c:if test="${!empty infoShared.inceptOrgs && infoShared.inceptOrgs !='#' }">
	<sm:query var="orgs" beanName="orgManager" methodName="findOrgsByPath">
		<sm:param type="java.lang.String" value="${infoShared.inceptOrgs}" />
	</sm:query>
	</c:if>
	<webui:panel title="sysadmin.title.info.shared.edit" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.info.shared.title" required="true" colspan="3">
				   	<html:text property="infoShared.title"/>
				</webui:input>
			</tr>
			<%--
			<tr>
				<webui:input label="sysadmin.label.shared.validTime">
					<sm:consignDate date="${infoShared.validTime}" var="flag1"/>
					<c:if test="${!flag1}">
				   		<input type="text" name="infoShared.validTime" id="validTime" value='<fmt:formatDate value="${infoSharedForm.infoShared.validTime}" pattern="yyyy-MM-dd hh:mm:ss"/>' readonly="true"/>
				   		<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(validTime);return false;">
					</c:if>
					<c:if test="${flag1}">
						<input type="text" name="infoShared.validTime" id="validTime" value='<fmt:formatDate value="${infoSharedForm.infoShared.validTime}" pattern="yyyy-MM-dd hh:mm:ss"/>' readonly="true"/>
					</c:if>
				</webui:input>
				<webui:input label="sysadmin.label.shared.expireTime">
					<sm:consignDate date="${infoShared.expireTime}" var="flag1"/>
					<c:if test="${!flag1}">
				   		<input type="text" name="infoShared.expireTime" id="expireTime" value='<fmt:formatDate value="${infoSharedForm.infoShared.expireTime}" pattern="yyyy-MM-dd hh:mm:ss"/>' readonly="true"/>
				   		<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(expireTime);return false;">
					</c:if>
					<c:if test="${flag1}">
						<input type="text" name="infoShared.expireTime" id="expireTime" value='<fmt:formatDate value="${infoSharedForm.infoShared.expireTime}" pattern="yyyy-MM-dd hh:mm:ss"/>' readonly="true"/>
				   	</c:if>
				</webui:input>
			</tr>
			--%>
			<tr>
				<webui:input label="sysadmin.label.info.shared.content" required="true" colspan="3">
					<html:textarea property="infoShared.content" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
			<c:if test="${attach != null}">
			<tr>
					<webui:input label="sysadmin.label.shared.attach">
						<c:out value="${attach.fileName}"/>&nbsp;&nbsp;&nbsp;
						<webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:delAttach();" value="sysadmin.label.shared.delete.attach"/>
					</webui:input>
			</tr>
			</c:if>
				<c:if test="${attach == null}">
				<tr>
					<webui:input label="sysadmin.label.shared.attach">
					<input name="attachFile" size="40" type="file">
					</webui:input>
				</tr>
			</c:if>
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
						<c:set var="flag" value="false"/>
						<c:forEach items="${orgs}" var="org">
						<c:if test="${org.orgId == data.key}">
							<input class="noborder" type="checkbox" name="ids" value="<c:out value='${data.key}'/>" checked="true" id="a<c:out value='${data.object.path}'/>" onclick='autoCheck2(this)'>
							<img align="absmiddle" src="../images/tree/folder.gif" /><c:out value='${data.nodeName}'/>
						<c:set var="flag" value="true"/>
						</c:if>
						</c:forEach>
						<c:if test="${flag == false}">
							<input class="noborder" type="checkbox" name="ids" value="<c:out value='${data.key}'/>" id="a<c:out value='${data.object.path}'/>" onclick='autoCheck2(this)'>
							<img align="absmiddle" src="../images/tree/folder.gif" /><c:out value='${data.nodeName}'/>
						</c:if>
						<script lanuage=javascript>
				       		checkBoxIds[index++]="a<c:out value='${data.object.path}'/>";
                        </script>
					</webui:tree>
				</td>
			</tr>
		</table>
		
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(infoSharedForm);" value="sysadmin.button.update"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="infoSharedForm" />
<script>
	
	function onSubmit(input) {
	    return validateInfoSharedForm(input);
  	}
  	
  	function delAttach(){
  		if(window.confirm("<bean:message key='msg.confirm.delete.info.attach'/>")){
  			window.location = "<c:url value='/sm/infoShared.do?act=deleteAttach&aId=${attach.attachId}'/>";
  		}
  	}
</script>
