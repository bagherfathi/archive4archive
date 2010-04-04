<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<sm:query var="templateFileList" beanName="templateManager" methodName="findTemplateFiles">
    <sm:param type="java.lang.Long" value="${template.template.templateId}"/>
</sm:query>

<webui:panel title="ģ���ļ��б�" width="95%" icon="../images/icon_list.gif">
		<webui:table 
		items="templateFileList"
		action="${pageContext.request.contextPath}/sm/templateFile.do?act=listHistory&templateId=${template.template.templateId}"
		var="fileInfo"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false">
		<webui:row>
			<webui:column property="templateFile.fileVersion" title="�汾��">
			</webui:column>
			<webui:column property="ģ������" title="ģ������">
			    <c:out value="${template.template.templateName}"/>
			</webui:column>
			<webui:column property="operatorName" title="������">
			</webui:column>
			<webui:column property="operatorName" title="����״̬">
			    <c:if test="${fileInfo.templateFile.fileVersion==template.template.publishVersion}">
			    �ѷ���
			    </c:if>
			    <c:if test="${fileInfo.templateFile.fileVersion!=template.template.publishVersion}">
			    δ����
			    </c:if>
			</webui:column>
			<webui:column property="templateFile.createDate" cell="date" title="����ʱ��" format="yyyy-MM-dd HH:mm:ss">
			</webui:column>
			<webui:column property="action" title="����">
			    <a href="#" onclick="showDialog(<c:out value='${fileInfo.templateFile.fileId}'/>)">Ԥ��</a>&nbsp;
			    <a href="<c:url value='/sm/templateFile.do?act=edit&templateFileId=${fileInfo.templateFile.fileId}&templateId=${template.template.templateId}'/>">���߱༭</a>&nbsp;
			    <c:if test="${fileInfo.templateFile.fileVersion!=template.template.publishVersion}">
			    <a href="#" onclick="onDelete(<c:out value='${template.template.templateId}'/>,<c:out value='${fileInfo.templateFile.fileId}'/>);">ɾ��</a>&nbsp;
			    </c:if>
			</webui:column>
			<webui:column property="busaction" title="ҵ�����">
			    <c:if test="${fileInfo.templateFile.fileVersion!=template.template.publishVersion}">
			    <a href="#" onclick="onPublish(<c:out value='${template.template.templateId}'/>,<c:out value='${fileInfo.templateFile.fileId}'/>);">����</a>&nbsp;
			    </c:if>
			    <a href="<c:url value='/sm/templateFile.do?act=download&&templateFileId=${fileInfo.templateFile.fileId}&templateId=${template.template.templateId}'/>">����</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="onAddFile();" value="sysadmin.button.create"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="sysadmin.button.return"/>
</webui:panel>
<script>
    function back(){
        location.href="<c:url value="/sm/template.do"/>";
    }
    
    function onAddFile(){
        location.href="<c:url value="/sm/templateFile.do?act=add&templateId=${template.template.templateId}"/>";
    }
    
    function onPublish(templateId,templateFileId){
        if (confirm("ȷ��Ҫ�����ð汾�ļ���")) {
            location.href="<c:url value='/sm/templateFile.do?act=publish&templateId='/>" + templateId + "&templateFileId=" + templateFileId;
        }
    }
    
    function onDelete(templateId,templateFileId){
        if (confirm("ȷ��Ҫɾ���ð汾�ļ���")) {
            location.href="<c:url value='/sm/templateFile.do?act=delete&templateId='/>" + templateId + "&templateFileId=" + templateFileId;
        }
    }
    
    showDialog=function (id) {
		var url = "<c:url value='/sm/templateFile.do?act=toPreview&&fileId='/>" +id;
		//window.open (url, name, 'height=400px width=500px, top=300, left=200, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no') 
		openDialogWindow(url,400,500);
	}

	openDialogWindow=function(url,height,width) {
	  source = "";
	  defineWindow = "height=" + height + ",width=" + width + ",menubar=no,scrollbars=auto,location=no,resizable=yes";
	  if (window.screen) {
	    var ah = screen.availHeight - 30;
	    var aw = screen.availWidth - 10;
	
	    var xc = (aw - width) / 2;
	    var yc = (ah - height) / 2;
	
	    defineWindow += ",left=" + xc + ",screenX=" + xc;
	    defineWindow += ",top=" + yc + ",screenY=" + yc;
	  }
	
	  var newWin = window.open(url, '', defineWindow );
	  newWin.focus();
	  return newWin;
	}
</script>
