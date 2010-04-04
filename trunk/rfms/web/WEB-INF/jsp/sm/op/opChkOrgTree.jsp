<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>

<html>
<body>
<br/>
<br/>

<webui:buildTree beanName="orgTreeBuilder" var="root" />
<table width="30%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
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
       <input type="radio" name="orgIds" id="orgIds" value="<c:out value='${data.key}'/>" title="<c:out value='${data.nodeName}'/>" <c:if test="${data.key==org.orgId}">checked=true</c:if> />
    <c:out value='${data.nodeName}'/>
      </webui:tree>
    </td>
  </tr>
</table>
<br/>
<button onclick="go()">确定</button>  <button onclick="window.close()">关闭</button>
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
        	 alert("必须选一个");
			 return false;
        }
		opener.document.getElementById('org').value = title;
		opener.document.getElementById('did').value = id;
        window.close();
    }
  </script>
  </body>
  </html>