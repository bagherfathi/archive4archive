<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<sm:query var="ruleFileList" beanName="ruleManager" methodName="findRuleFileByRuleId">
    <sm:param type="java.lang.Long" value="${rule.ruleId}"/>
</sm:query>

<webui:panel title="�����ļ��б�" width="95%" icon="../images/icon_list.gif">
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
			<webui:column property="version" title="�汾��">
			</webui:column>
			<webui:column property="��������" title="��������">
			    <c:out value="${rule.name}"/>
			</webui:column>
			<webui:column property="publisher" title="������">
			</webui:column>
			<webui:column property="����״̬" title="����״̬">
			    <c:if test="${ruleFile.version==rule.publishVersion}">
			    �ѷ���
			    </c:if>
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    δ����
			    </c:if>
			</webui:column>
			<webui:column property="templateFile.createDate" cell="date" title="����ʱ��" format="yyyy-MM-dd HH:mm:ss">
			</webui:column>
			<webui:column filterable="false" property="uploadTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="����ʱ��"  >
			</webui:column>
			
			<webui:column property="action" title="����">
			    <a href="<c:url value='/sm/ruleFile.do?act=edit&fileId=${ruleFile.fileId}'/>">���߱༭</a>&nbsp;
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    <a href="#" onclick="onDelete(<c:out value='${rule.ruleId}'/>,<c:out value='${ruleFile.fileId}'/>);">ɾ��</a>&nbsp;
			    </c:if>
			</webui:column>
			
			<webui:column property="busAction" title="ҵ�����">
			    <c:if test="${ruleFile.version!=rule.publishVersion}">
			    <a href="#" onclick="onPublish(<c:out value='${rule.ruleId}'/>,<c:out value='${ruleFile.fileId}'/>);">����</a>&nbsp;
			    </c:if>
			    <a href="<c:url value='/sm/ruleFile.do?act=download&fileId=${ruleFile.fileId}&ruleId=${rule.ruleId}'/>">����</a>&nbsp;
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
        if (confirm("ȷ��Ҫ�����ð汾�ļ���")) {
            location.href="<c:url value='/sm/ruleFile.do?act=publish&ruleId='/>" + ruleId + "&fileId=" + ruleFileId;
        }
    }
    
    function onDelete(ruleId,ruleFileId){
        if (confirm("ȷ��Ҫɾ���ð汾�ļ���")) {
            location.href="<c:url value='/sm/ruleFile.do?act=delete&ruleId='/>" + ruleId + "&fileId=" + ruleFileId;
        }
    }
</script>
