<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>

<html>
<title>选择组织</title>
<body style="margin:5px;"> 

<sm:buildOrgsTree var="root" orgType="100"> </sm:buildOrgsTree>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style="padding:5px;background:#FFFFFF;height:239px;width:310px;overflow:auto;" >
      <webui:tree 
        root="${root}"
        id="data"
        type="BaseTreeNode" 
        indent="2" extend="2" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif"><c:if test="${level != 0}">
        <c:if test="${empty data.children}">
				<img align="absmiddle" src="../images/book.gif" />
		   </c:if>
		   <c:if test="${!empty data.children}">
				<img align="absmiddle" src="../images/books_close.gif" />
		   </c:if>
        <input class="noborder" type="radio" name="orgIds" id="orgIds" ondblclick="go()" value="<c:out value='${data.key}'/>" <c:if test="${data.key==loginOrg.orgId }">checked=true</c:if> 
        <c:choose>
           <c:when test="${data.status==1}">disabled title="此组织已禁止"</c:when>  
           <c:when test="${data.status==2}">disabled title="此组织不可访问"</c:when>
           <c:otherwise>title="<c:out value="${data.nodeName}"/>" </c:otherwise>
        </c:choose>
            />
           
        </c:if>
        <c:out value='${data.nodeName}'/> 
      </webui:tree></div>
    </td>
  </tr>
  <tr>
    <td align="right" style="padding-top:6px;"><webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:go();" value="确定"/></td>
  </tr>
</table>
  <script>
    function closeDiv(){
       var obj = document.getElementById('popDiv');
       alert(obj.style.display);
    }
  
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
		parent.document.location='<c:url value='/login.do?act=changeOrg&orgId='/>' +id;
    }
  </script>
  </body>
  </html>