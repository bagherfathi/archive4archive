<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/resource.do" enctype="multipart/form-data" >
<html:hidden property="act" value="importMenu"/>
<webui:panel title="sysadmin.lable.resource.import" icon="../images/file.png" width="95%">
	<html:file property="resFile"/>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmit();" value="sysadmin.button.import"/>
</webui:panel>
</html:form>
<script>
	function onSubmit(){
		if(document.forms[0].resFile.value==""){
			alert('<bean:message key="msg.import.file"/>');
		}else{
			var fileName = document.forms[0].resFile.value;
			var len = fileName.length;
			var postfix=fileName.substr(len-4,len);
			if(postfix==".xml"){
				document.forms[0].submit();
			}else{
			alert('<bean:message key="msg.import.error.postfix"/>');
			}
		}
	}
</script>