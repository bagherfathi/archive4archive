
<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ include file="/WEB-INF/jsp/inc/link.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>

<html>
<title>权限列表</title>
<body style="margin:5px;"> 
<webui:panel icon="../images/icon_list.gif" title="权限列表">
<webui:buildTree beanName="orgTreeBuilder" var="root" />
<table width="100%" align="center" valign="top" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style="padding:5px;background:#F5F8FB;overflow:auto;height:220px;width:390px;" >
      <webui:tree
		        root="${root}"
		        id="data"
		        type="com.ft.commons.tree.BaseTreeNode"
		        indent="2" extend="2"
		        saveToCookie="false"
		        level="level"
		        closeFolderImg="../images/tree/jia.gif"
		        openFolderImg="../images/tree/jian.gif"
		        leafImg="../images/tree/space.gif">
		        <c:if test="${level==0}">
		        	<img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
		        </c:if>
		        <c:if test="${level!=0}">
		            <c:if test="${empty data.children}">  
		                <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
                    </c:if>
                    <c:if test="${!empty data.children}">
                        <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
                    </c:if>
		        </c:if>
		    </webui:tree>
		</div>
    </td>
  </tr>
</table>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:window.close();" value="关闭窗口"/>
</webui:panel>