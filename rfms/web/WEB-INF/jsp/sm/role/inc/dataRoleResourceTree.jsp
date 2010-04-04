<table width="100%">
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
	  	<c:if test="${level==0}">
	  		<img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
	  	</c:if>
	  	<c:if test="${level==1}">
	  		<img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
	  	</c:if>
	  	<c:if test="${level==2}">
	  		<img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
	  	</c:if>
	  	</webui:tree>
	  	</td>
	</tr>
</table>