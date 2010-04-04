<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<webui:panel width="95%" title="����Ա��Ȩ���б�-${queryGroupPrivilegeForm.group.name}" icon="../images/icon_list.gif">
<table width="95%">
<tr>
<td align="center">
<table width="95%">
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
</td>
</tr>
</table>
</webui:panel>
<sm:query var="operators" beanName="operatorManager" methodName="findOperatorsOfGroup">
	<sm:param type="java.lang.Long" value="${queryGroupPrivilegeForm.group.groupId}" />
</sm:query>
</br>
<webui:panel width="95%" title="����Ա�б�" icon="../images/icon_list.gif">
<table width="95%">
<tr>
<td align="center">
<webui:table
		title="����Ա�б�"
		items="operators"
		tableId="operatorTable"
		action="${pageContext.request.contextPath}/sm/queryGroupPrivilege.do?act=queryGroupPrivilege&gId=${queryGroupPrivilegeForm.group.groupId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="100%"
		var="op"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="operatorId" title="���" width="10%"/>
			<webui:column  filterable="false" property="contact.name" title="����Ա����"/>
			<webui:column  filterable="false" property="loginName" title="��½����"/>
			<webui:column  filterable="false" property="contact.telephone" title="��ϵ�绰"/>
			<webui:column  filterable="false" property="contact.mobilePhone" title="�ƶ��绰"/>
			<webui:column  filterable="false" property="email" title="E-mail"/>
		</webui:row>
</webui:table>
</td>
</tr>
</table>
<webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="sysadmin.button.return"/>
</webui:panel>
