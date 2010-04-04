<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<form name="ruleFileForm" id="ruleFileForm" method="post" action="<c:url value='/sm/ruleFile.do?ruleId=${rule.ruleId}'/>" 
       enctype="multipart/form-data" onsubmit="return confirmUpload(this)">
<input type="hidden" name="act" value="save"/>
<input type="hidden" name="ruleId" value="<c:out value='${rule.ruleId}'/>"/>
<webui:panel title="sysadmin.title.rule.file" width="95%" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="上传文件" required="true">
				<input name="file" type="file">
			</webui:input>
			<webui:input label="sysadmin.label.rule.is.publish" required="true">
				<select name="publish">
					<option value="false"><bean:message key='sysadmin.label.false'/></option>
					<option value="true"><bean:message key='sysadmin.label.true'/></option>
				</select>
			</webui:input>
		</tr>
	</webui:formTable>
	<security:checkPermission resourceKey="SM_UPLOAD_RULE_FILE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(ruleFileForm)" value="sysadmin.button.save"/>
		</security:success>
	</security:checkPermission>
	
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="sysadmin.button.return"/>
</webui:panel>
</form>
<script>
	function back(){
        location.href = "<c:url value='/sm/ruleFile.do?act=list&ruleId=${rule.ruleId}'/>"
    }
    function confirmUpload(f){
        if(document.getElementById('file').value != ''){
            if(!confirm("<bean:message key='maxLengthExplanation'/>")){
               return false;
           }
        }
	    return validateRuleFileForm(f);
    }
</script>

<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="ruleFileForm" />