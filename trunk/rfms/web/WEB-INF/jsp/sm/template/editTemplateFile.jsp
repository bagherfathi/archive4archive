<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="模板文件" width="101%" icon="../images/icon_list.gif">
<html:form action="templateFile" >
<input type="hidden" name="act" value="update"/>
<input type="hidden" name="templateId" value="<c:out value='${templateFile.templateFile.templateId}'/>"/>
<input type="hidden" name="templateFileId" value="<c:out value='${templateFile.templateFile.fileId}'/>"/>
		<webui:formTable width="95%">
			<tr>
				<webui:input label="文件版本号">
				   <c:out value="${templateFile.templateFile.fileVersion}"/>
				</webui:input>
				<webui:input label="是否发布">
				   <select name="publish">
				      <option value="false">否</option>
				      <option value="true">是</option>
				   </select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="文件内容" colspan="4">
				   <FCK:editor instanceName="fileContent" height="425">
				    <jsp:attribute name="value"><%=((com.ft.web.sm.template.TemplateForm)request.getAttribute("templateForm")).getFileContent() %></jsp:attribute>
			       </FCK:editor>
				</webui:input>
			</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="templateForm.submit()" value="保存"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back();" value="返回"/>
</html:form>	
</webui:panel>
<script>
    function back(){
        location.href = "<c:url value='/sm/templateFile.do?act=listHistory&templateId=${templateFile.templateFile.templateId}'/>"
    }
</script>
