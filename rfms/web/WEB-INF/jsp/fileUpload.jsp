<%@ page contentType="text/html; charset=GBK" %><%--
--%><%@ include file="/WEB-INF/jsp/inc/tld.inc" %><%--
--%><c:set var="jspBegin"><%=System.currentTimeMillis()%></c:set><%--
--%>
<html>
<head>
<base   target="_self">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); 
%>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>文件上传</title>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
</head>
<body>
<webui:panel title="文件上传" width="95%" icon="/images/icon_list.gif">
<form name="uploadForm" action="<c:url value='/fileUpload'/>" method="post"
       enctype="multipart/form-data">
<webui:formTable>
	<tr>
		<webui:input label="类型" colspan="3">
		   <select name="uptype">
		   <option value="1">本地上传</option>
		   <option value="2">远程文件</option>
		   </select>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="选择文件" colspan="3">
		   <input type="file" name="fileName" size="25"/>
		</webui:input>
	</tr>
</webui:formTable>
</form>
<webui:linkButton styleClass="clsButtonFace"
				href="javascript:toUpload();" value="上传" />
			<webui:linkButton styleClass="clsButtonFace" href="#"
				onClick="javascript:self.close()" value="关闭" />
</webui:panel>
<script>
  function toUpload(){
   if(document.uploadForm.fileName.value==""){
     alert("请选择文件！");
     return;
   }
   loadOn();
   document.uploadForm.submit();
  }
</script>
<div id=divProcessing
	style="z-index:9998;width:100%;height:100%;position:absolute;left:0px;top:0px;display:none;filter:Alpha(opacity=0);">
<table width="100%" height="100%" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</div>
<div id=divSearch
	style="z-index:9999;width:150;height:40;position:absolute;left:20px;top:10px;display:none;border:1 #3E85EB solid;background-color:#EBF5FF;">
	<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:350px; height:80px; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
<div
	style="position:absolute;width:120;height:19;left:15px;top:15px;border:1 #707888 solid;overflow:hidden">
<div style="position:absolute;top:-1;left:0" id="pimg"></div>
</div>
<div
	style="position:absolute;top:10;left:10;font-size:9pt;color:#000000"
	id="abc">......执行中，请稍候......</div>
</div>
</body>

</html>