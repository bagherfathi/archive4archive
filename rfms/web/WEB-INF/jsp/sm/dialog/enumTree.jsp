<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>


<webui:buildTree beanName="enumEntryTreeBuilder" var="root" />
<html>
<head>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
</head>
<body style="margin:5px;">
<webui:panel icon="../images/icon_list.gif" title="选择关联系统数据值">
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0"  >
  <tr>
    <td>
    <div style="padding:5px;background:#F5F8FB;overflow:auto;height:220px;width:390px;" >
      <webui:tree 
        root="${root}" 
        id="data" 
        type="com.ft.commons.tree.BaseTreeNode" 
        indent="2" extend="1" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif">
        <c:if test="${level==0}"><img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${level==1}"><img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${level==2}"><img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${level==3}"><img align="absmiddle" src="../images/book.gif" />
            <c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${level==4}">
            <input type="radio" name="enumEntriesIds" id="enumEntriesIds" value="<c:out value='${data.key}'/>" 
            <c:if test="${data.parent.status==1}">disabled title="系统数据已被禁止"</c:if>/>
            <c:out value='${data.nodeName}'/>
        </c:if>
      </webui:tree>
    </div>
    </td>
  </tr>
</table>
<webui:linkButton styleClass="clsButtonFace" href="javascript:go();" value="确定"/>
<webui:linkButton styleClass="clsButtonFace" href="javascript:window.close();" value="关闭"/>
</webui:panel>
<script>
    function go(){
        var obj = document.getElementsByName("enumEntriesIds");
        var flag = false;
        var selValue;
        for(var i=0 ;i<obj.length;i++){
         	if(obj[i].checked){
         	   flag=true;
         	   selValue = obj[i].value;
         	   break;
         	}
        }
        
        if(!flag){
        	 alert("必须选择一个系统数据值");
			 return;
        }
        
		opener.document.getElementById('entryLinkValue').value = selValue;
        window.close();
    }
  </script>
</body>
</html>