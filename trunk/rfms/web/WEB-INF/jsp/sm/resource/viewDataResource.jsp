<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/dynamic.js"/>"></script>
<html:form action="/dataResource.do">
<c:set var="dataResource" value="${dataResourceForm.dataResource}"/>
	<html:hidden property="act" value="addEntry"/>
	<input type="hidden" name="id" value="<c:out value='${dataResource.resourceId}'/>">
	
	<sm:query var="entryList" beanName="resourceManager" methodName="findEntryByDataResourceId">
		<sm:param type="java.lang.Long" value="${dataResource.resourceId}" />
	</sm:query>
	
	<webui:panel title="sysadmin.label.dataResource.info">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.dataResource.name">
					<c:out value="${dataResource.title}"/>
				</webui:input>
				<webui:input label="sysadmin.label.dataResource.code">
					<c:out value="${dataResource.code}"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.dataResource.type">
					<webui:lookup code="data_type@SM_DATA_RESOURCE" value="${dataResource.dataType}"/>
				</webui:input>
				<webui:input label="sysadmin.label.dataResource.assign.type">
			  		<webui:lookup code="assign_type@SM_DATA_RESOURCE" value="${dataResource.assignType}"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.dataResource.desc" colspan="3">
					<html:textarea property="dataResource.description" styleClass="wid80" rows="3" readonly="true"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<security:checkPermission resourceKey="SM_EDIT_DATA_RESOURCE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:doAction('edit');" value="sysadmin.button.edit"/>
			</security:success>
		</security:checkPermission>
		<security:checkPermission resourceKey="SM_DELETE_DATA_RESOURCE">
			<security:success>
				<c:if test="${empty entryList}">
					<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:doDelete();" value="sysadmin.button.delete"/>
				</c:if>
			</security:success>
		</security:checkPermission>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="sysadmin.button.return"/>
	</webui:panel>
	
	<br/>
	
	<webui:panel title="sysadmin.label.dataResource.entry.list">
		<webui:table
		items="entryList"
		tableId="dataResourceForm"
		action="${pageContext.request.contextPath}/sm/dataResource.do?act=view&id=${dataResource.resourceId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="entry"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column filterable="false" property="title" title="权限条目标题"/>
			<c:if test="${dataResource.dataType == 1}">
				<webui:column filterable="false" property="maxValue" title="条目值"/>
			</c:if>
			<c:if test="${dataResource.dataType == 2}">
				<webui:column filterable="false" property="minValue" title="最小值"/>
				<webui:column filterable="false" property="maxValue" title="最大值"/>
			</c:if>
			<webui:column filterable="false" property="操作" title="操作">
			<security:checkPermission resourceKey="SM_EDIT_DATA_RESOURCE_ENTRY">
				<security:success>
					<a href='<c:url value="/sm/dataResource.do?act=toEditEntry&eId=${entry.entryId}&id=${dataResource.resourceId}"/>'>编辑</a>
				</security:success>
			</security:checkPermission>
			<sm:dataResourceEntry var="flag" id="${entry.entryId}"/>
			<c:if test="${flag == false}">
				<security:checkPermission resourceKey="SM_DELETE_DATA_RESOURCE_ENTRY">
					<security:success>
						<a href='#' onClick="deleteEntry(<c:out value='${entry.entryId}'/>,<c:out value='${dataResource.resourceId})'/>">删除</a>
					</security:success>
				</security:checkPermission>
			</c:if>
			</webui:column>
		</webui:row>
		</webui:table>
		
		<security:checkPermission resourceKey="SM_ADD_DATA_RESOURCE_ENTRY">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:doAction('toAddEntry');" value="sysadmin.button.create"/>
			</security:success>
		</security:checkPermission>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="dataResourceForm" />
<script>
	function deleteEntry(entryId,resourceId){
		if(window.confirm("<bean:message key='msg.confirm.delete.dataResource'/>")){
			window.location.href = "<c:url value='/sm/dataResource.do?act=deleteEntry&eId="+entryId+"&id="+resourceId+"'/>";
		}
	}

	function doAction(act){
		window.location = "<c:url value="/sm/dataResource.do?act="/>" + act + "&id" + "=<c:out value='${dataResource.resourceId}'/>";
	}
	function back(){
		window.location.href = '<c:url value="/sm/dataResource.do?drTable_p="/>' + '<c:out value="${currentPage_drTable}"/>';
	}
	function doDelete(){
		var ok=window.confirm("<bean:message key='msg.confirm.delete.dataResource'/>");
		if(ok==true){
		    loadOn();
			document.forms.dataResourceForm.setAttribute('action', '<c:url value="/sm/dataResource.do?act=delete&id=${dataResource.resourceId}"/>');
	        document.forms.dataResourceForm.submit();
	    }
		else
			return false;
	}
</script>
