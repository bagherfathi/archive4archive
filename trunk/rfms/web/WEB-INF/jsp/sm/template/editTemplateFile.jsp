<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="ģ���ļ�" width="101%" icon="../images/icon_list.gif">
<html:form action="templateFile" >
<input type="hidden" name="act" value="update"/>
<input type="hidden" name="templateId" value="<c:out value='${templateFile.templateFile.templateId}'/>"/>
<input type="hidden" name="templateFileId" value="<c:out value='${templateFile.templateFile.fileId}'/>"/>
		<webui:formTable width="95%">
			<tr>
				<webui:input label="�ļ��汾��">
				   <c:out value="${templateFile.templateFile.fileVersion}"/>
				</webui:input>
				<webui:input label="�Ƿ񷢲�">
				   <select name="publish">
				      <option value="false">��</option>
				      <option value="true">��</option>
				   </select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="�ļ�����" colspan="4">
				   <FCK:editor instanceName="fileContent" height="425">
				    <jsp:attribute name="value"><%=((com.ft.web.sm.template.TemplateForm)request.getAttribute("templateForm")).getFileContent() %></jsp:attribute>
			       </FCK:editor>
				</webui:input>
			</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="templateForm.submit()" value="����"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back();" value="����"/>
</html:form>	
</webui:panel>
<script>
    function back(){
        location.href = "<c:url value='/sm/templateFile.do?act=listHistory&templateId=${templateFile.templateFile.templateId}'/>"
    }
</script>
