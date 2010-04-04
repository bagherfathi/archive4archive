<table width="95%">
	<tr>
	  	<td>
	  		<webui:tree
		        root="${resourceRoot}"
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
		        <c:if test="${level!=0}">
		        	<c:if test="${empty data.children}">  
		                    <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
                        </c:if>
                        <c:if test="${!empty data.children}">
                            <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
                        </c:if>
		        </c:if>
		    </webui:tree>
		</td>
	</tr>
</table>