<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>

<html>
<title>选择上级组织</title>
<body style="margin:5px;"> 
<webui:panel icon="../images/icon_list.gif" title="选择上级组织">
<webui:buildTree beanName="orgTreeBuilder" var="root" />
<table width="100%" align="center" valign="top" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style="padding:5px;background:#F5F8FB;overflow:auto;height:220px;width:390px;" >
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
       <sm:orgPath parentPath="${org.orgPath}" path="${data.object.path}" var="flag"/>
        <c:if test="${data.parent.key!=org.orgId && data.key != org.orgId && !flag}">
       <input type="radio" name="orgIds" id="orgIds" value="<c:out value='${data.key}'/>" <c:if test="${data.key==org.parentId}">checked=true</c:if> <c:if test="${data.status==1}">disabled title=此组织已禁止</c:if>  />
    <c:out value='${data.nodeName}'/>
       </c:if>
       <c:if test="${data.parent.key==org.orgId || data.key == org.orgId || flag}">
       <input type="radio" name="orgIds" id="orgIds" value="<c:out value='${data.key}'/>" disabled />
    <c:out value='${data.nodeName}'/>
       </c:if>
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
        var id;
        for(var i=0 ;i<obj.length;i++){
         	if(obj[i].checked){
         	   id = obj[i].value;
         	   break;
         	}
        }
        
        if(id==<c:out value='${org.parentId}'/>){
           window.close();
           return;
        }
        
        opener.document.forms.hiddenForm.setAttribute('action', '<c:url value="/sm/org.do?act=changeParent&orgId=${org.orgId}&pid="/>'+id);
	    opener.loadOn();
	    opener.document.forms.hiddenForm.submit();
        window.close();
    }
  </script>
  </body>
  </html>
