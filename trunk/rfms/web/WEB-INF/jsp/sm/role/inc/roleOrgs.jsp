<table width="100%">
	<tr>
		<td><webui:tree root="${orgTree}" id="data" type="com.ft.commons.tree.BaseTreeNode"
			indent="3" extend="3" saveToCookie="false" level="level"
			closeFolderImg="../images/tree/jia.gif"
			openFolderImg="../images/tree/jian.gif"
			leafImg="../images/tree/space.gif" treeName="org">
			<c:if test="${level==0}">
				<img align="absmiddle" src="../images/books_open.gif" />
			</c:if>
			<c:if test="${level!=0}">
				<input type="checkbox" class="noborder"
					<c:if test='${data.status !=2}' >checked</c:if>
					<c:if test='${data.status ==2}' >disabled</c:if> />
				<c:if test="${empty data.children}">
					<img align="absmiddle" src="../images/book.gif" />
				</c:if>
				<c:if test="${!empty data.children}">
					<img align="absmiddle" src="../images/books_close.gif" />
				</c:if>
			</c:if>
			<c:out value='${data.nodeName}' />
		</webui:tree></td>
	</tr>
</table>
