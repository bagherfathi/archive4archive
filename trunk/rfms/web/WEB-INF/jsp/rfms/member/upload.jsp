<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="memberForm" action="/memberimp.do" method="post" enctype="multipart/form-data" >
	<input type="hidden" value="save" name="act" />
	<html:hidden property="baseEntity.name" value="1"/>
	<html:hidden property="baseEntity.mobile" value="1"/>
	<html:hidden property="baseEntity.pwd" value="1" />
	<webui:panel title="�ϴ���Ա" width="95%" icon="/images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="ģ���ļ�" colspan="3">
		            <html:file property="strFile"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../member.xls">ģ������</a>
				</webui:input>
			</tr>
		</webui:formTable>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:confirmUpload(memberForm);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="memberForm" />
<script>
 function confirmUpload(aform){
     if(document.getElementById('strFile').value == ''){
       		alert("��ѡ���ϴ��ļ�");
            return;
     }
    //aform.act.value="save";
	 //loadOn();
	 aform.submit();
 }
</script>