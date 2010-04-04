<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/rule" onsubmit="return validateRuleForm(this)">
	<html:hidden property="categoryId"/>
	<input type="hidden" name="id" value="${rule.ruleId}"/>
	<html:hidden property="act" value="delete"/>
	<webui:panel title="sysadmin.title.rule.info" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.rule.name">
					<c:out value="${rule.name}"/>
				</webui:input>
				<webui:input label="sysadmin.label.rule.code">
					<c:out value="${rule.code}"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.rule.type">
					<webui:lookup code="type@SM_RULE_INFO" value="${rule.type}"/>
				</webui:input>
				<webui:input label="sysadmin.label.rule.publish.version">
					<c:if test="${rule.publishVersion == 0}">
						<bean:message key='sysadmin.label.rule.no.publish'/>
					</c:if>
					<c:if test="${rule.publishVersion != 0}">
				      	Version.<c:out value='${rule.publishVersion}'/>
				   	</c:if>
				</webui:input>
			</tr>
		</webui:formTable>
		<%--
		<security:checkPermission resourceKey="SM_UPLOAD_RULE_FILE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:toUploadFile();" value="sysadmin.button.upload.rule.file"/>
			</security:success>
		</security:checkPermission>
		
		<security:checkPermission resourceKey="SM_EDIT_RULE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:toEdit();" value="sysadmin.button.edit"/>
			</security:success>
		</security:checkPermission>

		<security:checkPermission resourceKey="SM_DELETE_RULE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:toDelete();" value="sysadmin.button.delete"/>
			</security:success>
		</security:checkPermission>
		--%>
	</webui:panel>
	<br>
	<webui:panel title="规则文件列表" width="95%" icon="../images/icon_list.gif">
		<webui:table
			items="ruleFiles"
			title="sysadmin.label.resource.all.children"
			action="${pageContext.request.contextPath}/sm/rule.do?act=toView&id=${rule.ruleId}"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			width="95%"
			var="ruleFile"
			showPagination="true"
			showStatusBar="true"
			showTitle="false"
			filterable="false"
			sortable="false"
			autoIncludeParameters="true"
			form="ruleForm"
			>
			<webui:row>
			<webui:column filterable="false" property="version" title="版本号" >
			</webui:column>
			<webui:column property="发布状态" title="发布状态">
			    <c:if test="${ruleFile.version==rule.publishVersion}">
			    已发布
			    </c:if>
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    未发布
			    </c:if>
			</webui:column>
			<webui:column filterable="false" property="publisher" title="创建者" >
			</webui:column>
			<webui:column filterable="false" property="uploadTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="创建时间"  >
			</webui:column>
			
			<%--
			<security:checkPermission resourceKey="SM_PUBLISH_RULE">
			<security:success>
				<webui:column filterable="false" property="null" title="发布" >
					<c:if test="${ruleFile.version != rule.publishVersion}">
						<a href="<c:url value='/sm/rule.do?act=publish&rfId=${ruleFile.fileId}'/>">发布</a>
					</c:if>
				</webui:column>
			</security:success>
			</security:checkPermission>
			<security:checkPermission resourceKey="SM_DELETE_RULE_FILE">
			<webui:column filterable="false" property="null" title="删除" >
				<sm:ruleFile id="${ruleFile.fileId}" var="flag"/>
				<c:if test="${flag == false}">
					<a href="javascript:toDeleteFile(${rule.ruleId},${ruleFile.fileId});">删除</a>
				</c:if>
			</webui:column>
			</security:checkPermission>
			--%>
			</webui:row>
		</webui:table>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="ruleForm" />
<script>
    
	function toEdit(){
		window.location = "<c:url value='/sm/rule.do?act=toEdit&id=${rule.ruleId}&cid=${ruleForm.categoryId}'/>";
	}
	function toEditFile(){
		window.location = "<c:url value='/sm/rule.do?act=toEditFile'/>"+ "&id" + "=<c:out value='${rule.ruleId}'/>";
	}
	function toDelete(){
		if(window.confirm("<bean:message key='msg.confirm.delete.rule'/>")){
		document.ruleForm.submit();
		}
	}
	function toReturn(){
		window.location = "<c:url value='/sm/rule.do?act=search'/>";
	}
	
	function toUploadFile(){
		window.location = "<c:url value='/sm/rule.do?act=toUploadFile'/>"+ "&id" + "=<c:out value='${rule.ruleId}'/>";
	}
	function toDeleteFile(ruleId,ruleFileId){
		if(window.confirm("<bean:message key='msg.confirm.delete.rule.file'/>")){
		//document.all('act').value="deleteRuleFile";
		//document.ruleForm.submit();
		window.location = "<c:url value='/sm/rule.do?act=deleteRuleFile'/>"+ "&rfId=" + ruleFileId+"&id="+ruleId;
		}
	}
</script>
