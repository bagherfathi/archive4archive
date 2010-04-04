<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<html>
<title>选择区域</title>
<body style="margin:5px;"> 
<webui:panel icon="../images/icon_list.gif" title="选择区域">

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
        <c:if test="${empty data.parent}">
            <img align="absmiddle" src="../images/books_open.gif" />
            <c:if test="${style == 1}">
            	<input type="radio" class="noborder" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" text="<c:out value='${data.nodeName}'/>"/>
            </c:if>
            <c:if test="${style !=1 }">
            	<input type="checkbox" class="noborder" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" text="<c:out value='${data.nodeName}'/>"/>
            </c:if>
            <c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${!empty data.parent}">
            <c:if test="${empty data.children}">
                <img align="absmiddle" src="../images/book.gif" />
                <c:if test="${style == 1}">
            	<input type="radio" class="noborder" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" text="<c:out value='${data.nodeName}'/>"/>
	            </c:if>
	            <c:if test="${style !=1 }">
	            	<input type="checkbox" class="noborder" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" text="<c:out value='${data.nodeName}'/>"/>
	            </c:if>
            	<c:out value='${data.nodeName}'/>
            </c:if>
            <c:if test="${!empty data.children}">
                <img align="absmiddle" src="../images/books_close.gif" />
                <c:if test="${style == 1}">
            	<input type="radio" class="noborder" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" text="<c:out value='${data.nodeName}'/>"/>
	            </c:if>
	            <c:if test="${style !=1 }">
	            	<input type="checkbox" class="noborder" name="regionIds" id="regionIds" value="<c:out value='${data.key}'/>" text="<c:out value='${data.nodeName}'/>"/>
	            </c:if>
                <c:out value='${data.nodeName}'/>
            </c:if>
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
        var obj = document.getElementsByName("regionIds");
        var id;
        var name;
        for(var i=0 ;i<obj.length;i++){
         	if(obj[i].checked){
         	   id = obj[i].value;
         	   name=obj[i].text;
         	   opener.document.all("<c:out value='${inputName}'/>").value=id;
         	   opener.document.all('regionName').value=name;
         	   break;
         	}
        }
    window.close();
    }
  </script>