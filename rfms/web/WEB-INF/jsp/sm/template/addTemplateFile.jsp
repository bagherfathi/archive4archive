<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<form name="templateForm" id="templateFileForm" method="post" action="<c:url value='/sm/templateFile.do?templateId=${template.templateId}'/>" 
       enctype="multipart/form-data" onsubmit="return confirmUpload(this)">
<input type="hidden" name="act" value="save"/>
<input type="hidden" name="validationKey" value="templateFileForm"/>
<webui:panel title="上传模板文件" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="上传文件" required="true">
				    <input type="file" name="file" />
				</webui:input>
				<webui:input label="是否发布" required="true">
				  <select name="publish">
				      <option value="false">否</option>
				      <option value="true">是</option>
				  </select>
				</webui:input>
			</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(templateForm);" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="sysadmin.button.return"/>
</webui:panel>
</form>
<script>
    function back(){
        location.href = "<c:url value='/sm/templateFile.do?act=listHistory&templateId=${template.templateId}'/>"
    }
    function confirmUpload(f){
        if(document.getElementById('file').value != ''){
           if(!confirm("<bean:message key='maxLengthExplanation'/>")){
               return false;
           }
        }
        return   validateTemplateFileForm(f);
    }
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="templateFileForm" />