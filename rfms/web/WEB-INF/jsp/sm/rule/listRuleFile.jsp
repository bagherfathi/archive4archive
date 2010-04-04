<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<sm:query var="ruleFileList" beanName="ruleManager" methodName="findRuleFileByRuleId">
    <sm:param type="java.lang.Long" value="${rule.ruleId}"/>
</sm:query>

<webui:panel title="规则文件列表" width="95%" icon="../images/icon_list.gif">
		<webui:table 
		items="ruleFileList"
		action="${pageContext.request.contextPath}/sm/ruleFile.do?act=list&ruleId=${rule.ruleId}"
		var="ruleFile"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false">
		<webui:row>
			<webui:column property="version" title="版本号">
			</webui:column>
			<webui:column property="规则名称" title="规则名称">
			    <c:out value="${rule.name}"/>
			</webui:column>
			<webui:column property="publisher" title="创建者">
			</webui:column>
			<webui:column property="发布状态" title="发布状态">
			    <c:if test="${ruleFile.version==rule.publishVersion}">
			    已发布
			    </c:if>
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    未发布
			    </c:if>
			</webui:column>
			<webui:column property="templateFile.createDate" cell="date" title="创建时间" format="yyyy-MM-dd HH:mm:ss">
			</webui:column>
			<webui:column filterable="false" property="uploadTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="创建时间"  >
			</webui:column>
			
			<webui:column property="action" title="操作">
			    <a href="<c:url value='/sm/ruleFile.do?act=edit&fileId=${ruleFile.fileId}'/>">在线编辑</a>&nbsp;
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    <a href="#" onclick="onDelete(<c:out value='${rule.ruleId}'/>,<c:out value='${ruleFile.fileId}'/>);">删除</a>&nbsp;
			    </c:if>
			</webui:column>
			
			<webui:column property="busAction" title="业务操作">
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    <a href="#" onclick="onPublish(<c:out value='${rule.ruleId}'/>,<c:out value='${ruleFile.fileId}'/>);">发布</a>&nbsp;
			    </c:if>
			    <a href="<c:url value='/sm/ruleFile.do?act=download&fileId=${ruleFile.fileId}&ruleId=${rule.ruleId}'/>">下载</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="onAddFile();" value="sysadmin.button.create"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="sysadmin.button.return"/>
</webui:panel>
<script>
    function back(){
        location.href="<c:url value="/sm/rule.do"/>";
    }
    
    function onAddFile(){
        location.href="<c:url value="/sm/ruleFile.do?act=add&ruleId=${rule.ruleId}"/>";
    }
    
    function onPublish(ruleId,ruleFileId){
        if (confirm("确定要发布该版本文件？")) {
            location.href="<c:url value='/sm/ruleFile.do?act=publish&ruleId='/>" + ruleId + "&fileId=" + ruleFileId;
        }
    }
    
    function onDelete(ruleId,ruleFileId){
        if (confirm("确定要删除该版本文件？")) {
            location.href="<c:url value='/sm/ruleFile.do?act=delete&ruleId='/>" + ruleId + "&fileId=" + ruleFileId;
        }
    }
</script>
