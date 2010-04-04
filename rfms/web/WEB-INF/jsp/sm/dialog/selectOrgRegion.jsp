<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>

<html>
<title>选择区域</title>
<body style="margin:5px;">
<webui:panel icon="../images/icon_list.gif" title="选择区域">
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style="padding:5px;background:#F5F8FB;height:220px;width:390px;overflow:auto;" >
      <webui:tree 
        root="${root}"
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="2" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif">
        <c:if test="${data.key != 'root'}">
        <input class="noborder" type="radio" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" <c:if test="${data.key==regionId}">checked=true</c:if> 
        <c:choose>
           <c:when test="${data.status==1}">disabled title="此区域已禁止"</c:when>
           <c:otherwise>title="<c:out value="${data.nodeName}"/>" </c:otherwise>
        </c:choose>
            />
        </c:if>
        <c:out value='${data.nodeName}'/> 
      </webui:tree></div>
    </td>
  </tr>
</table>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:go();" value="确定"/>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:window.close();" value="关闭窗口"/>
</webui:panel>
  <script>
    function go(){
        var obj = document.getElementsByName("regionIds");
        var flag = false;
        var id;
        var title;
        for(var i=0 ;i<obj.length;i++){
         	if(obj[i].checked){
         	   flag=true;
         	   id = obj[i].value;
         	   title = obj[i].title;
         	   break;
         	}
        }
        if(!flag){
        	 alert("必须选择一个区域");
			 return;
        }
		opener.document.getElementById('select_<c:out value="${param.inputName}"/>').value = title;
		opener.document.getElementById('<c:out value="${param.inputName}"/>').value = id;
        window.close();
    }
  </script>
  </body>
  </html>