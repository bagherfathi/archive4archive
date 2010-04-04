<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/enumGroup.do" enctype="multipart/form-data" onsubmit="return onSubmit();">
<input type="hidden" name="act" value="importEnumDataGroup"/>
<webui:panel title="sysadmin.lable.enumgroup.import.title">
<html:file property="enumDataGroupFile"/>
<input type="submit" name="import" value="<bean:message key='sysadmin.button.import'/>" class="dialogbutton"/>
</webui:panel>
</html:form>
<script>
	function onSubmit(){
		if(document.forms[0].enumDataGroupFile.value==""){
			alert('<bean:message key="msg.import.file"/>');
			return false;
		}else{
			var fileName = document.forms[0].enumDataGroupFile.value;
			var len = fileName.length;
			var postfix=fileName.substr(len-4,len);
			if(postfix==".xml"){
				return true;
			}
			alert('<bean:message key="msg.import.error.postfix"/>');
			return false;
		}
	}
</script>