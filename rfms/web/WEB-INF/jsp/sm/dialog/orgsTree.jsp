<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>

<html>
<title>选择组织</title>
<body style="margin:5px;">
<webui:panel icon="../images/icon_list.gif" title="选择组织">

<sm:buildOrgsTree var="root" orgType="${param.orgType }"> </sm:buildOrgsTree>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style="padding:5px;background:#F5F8FB;height:220px;width:390px;overflow:auto;" >
      <webui:tree 
        root="${root}"
        id="data" 
        type="BaseTreeNode" 
        indent="1" extend="1" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif">
        <c:if test="${empty data.children}">
				<img align="absmiddle" src="../images/book.gif" />
		   </c:if>
		   <c:if test="${!empty data.children}">
				<img align="absmiddle" src="../images/books_close.gif" />
		   </c:if>
        <c:if test="${data.key != 'root'}">
        <c:choose>
           <c:when test="${data.status==1}"><c:set var="tempStr" value="disabled title=此组织已禁止"/></c:when>  
           <c:when test="${data.status==2}"><c:set var="tempStr" value="disabled title=此组织不可访问"/></c:when>
           <c:otherwise><c:set var="tempStr" value="title=${data.nodeName}"/></c:otherwise>
        </c:choose>
        <input class="noborder" type="radio" name="orgIds" id="orgIds" value="<c:out value='${data.key}'/>" <c:if test="${data.key==param.selOrgId }">checked=true</c:if> <c:out value="${tempStr }"/>>
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
        var obj = document.getElementsByName("orgIds");
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
        	 alert("必须选择一个组织");
			 return;
        }
		opener.document.getElementById('select_<c:out value="${param.inputName}"/>').value = title;
		opener.document.getElementById('<c:out value="${param.inputName}"/>').value = id;
		<c:if test="${!empty param.method}">
		  opener.<c:out value="${param.method}"/>(id);
		</c:if>
		
        window.close();
    }
  </script>
  </body>
  </html>