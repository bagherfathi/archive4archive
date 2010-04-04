<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<html>
<title>ѡ��ģ���ļ�</title>
<body style="margin:5px;"> 
<webui:panel icon="../images/icon_list.gif" title="ģ���ļ��б�">
<table width="100%" align="center" valign="top" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style="padding:5px;background:#F5F8FB;overflow:auto;height:220px;width:390px;" >
     <webui:table
		items="fileInfos"
		action="${pageContext.request.contextPath}/sm/dialog.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="fileInfo"
		width="95%"
		showPagination="false"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
		    <webui:column property="choose" title=" " >
		    <c:set var="checked" value="false"/>
		    <c:if test="${param.bindedId != 'null'}">
		       <c:if test="${param.bindedId == fileInfo.fileInfoId}">
		            <c:set var="checked" value="true"/>
		       </c:if>
		    </c:if>
		    <input class="noborder" <c:if test="${checked}">checked</c:if>   name="fileInfoId" value="<c:out value="${fileInfo.fileInfoId}"/>" type="radio"/>
		    </webui:column>
		    <webui:column property="aliasName" title="ģ���ļ���">
		     </webui:column>
			<webui:column property="lastVersion" title="���°汾">
			    Version.<c:out value="${fileInfo.lastVersion}"/>
			</webui:column>
			<webui:column property="publishVersion" title="�����汾">
			   <c:if test="${ fileInfo.publishVersion != 0 }">
			       Version.<c:out value="${fileInfo.publishVersion}"/>
			     </c:if>
			     <c:if test="${fileInfo.publishVersion ==0}">
			       δ����
			     </c:if>
			</webui:column>
		</webui:row>
	</webui:table>
      </div>
    </td>
  </tr>
</table>
<c:if test="${!empty fileInfos}">
   <webui:linkButton styleClass="clsButtonFace" href="#" onClick="submit_form();" value="ȷ��"/>
</c:if>
<c:if test="${empty fileInfos}">
   <webui:linkButton styleClass="clsButtonFace" href="#" onClick="alert('�����ϴ�ģ���ļ�')" value="ȷ��"/>
</c:if>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:window.close();" value="�رմ���"/>
</webui:panel>
<script>      
    function check(){
   	    var checked =false ;
   	    var obj = document.getElementsByName('fileInfoId');
			for(var i = 0;i < obj.length; i++) {
				 var e = obj[i];
				 if (e.checked){
				     checked=true;
				     break;
				 }
			}
		if(!checked) alert("��ѡ��ģ���ļ�");
		return checked;
	   }
	function submit_form(){
	    if(check()){
	        document.forms.ec.setAttribute('action', '<c:url value="/sm/templateBind.do?act=bind&orgId=${param.orgId}"/>');
	        document.forms.ec.submit();	
	    }    
	}
</script>
