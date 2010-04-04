<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
  <sm:query beanName="groupManager" methodName="findGroupRoleForOrgsByGroupId" var="roles">
     <sm:param value="${group.groupId}" type="java.lang.Long"/>
 </sm:query>
  <webui:buildTree beanName="opResourceTreeBuilder" var="root" >
<webui:buildTreeParam name="roleInGroup" type="java.lang.ArrayList" value="${roles}" /> 
<webui:buildTreeParam name="roleType" type="java.lang.Long" value="1" />
</webui:buildTree>
<webui:formTable width="100%">
		<tr>
			<webui:input label="sysadmin.label.role" colspan="4">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr><td align="left">
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
        <%--
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
		   --%>
		   <c:if test="${level==0}">
		      <img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
		  </c:if>
		  <c:if test="${level!=0}">
		      <c:if test="${empty data.children}">  
		          <img align="absmiddle" src="../images/book.gif" />
              </c:if>
              <c:if test="${!empty data.children}">
                   <img align="absmiddle" src="../images/books_close.gif" />
              </c:if>
              <c:if test="${data.status==100}">
                  <a href="#" onclick="viewRole(<c:out value='${data.key}'/>)"><c:out value='${data.nodeName}'/></a>
              </c:if>
               <c:if test="${data.status!=100}">
                   <c:out value='${data.nodeName}'/>
              </c:if>
		   </c:if>
    </webui:tree> 
    </td>
  </tr></table>
  </webui:input></tr></webui:formTable>
    
<security:checkPermission resourceKey="SM_CONFIG_GROUP_ROLE">
	<security:success>
		<table width="100%" border="0" cellspacing="2" cellpadding="2"> <tr><td align="right">
			<webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:roleConfig();" value="sysadmin.button.setrole"/>
		</td></tr></table>
	</security:success>
</security:checkPermission>
<script>
   function roleConfig(){
   		location.href = "<c:url value="/sm/groupRole.do?act=configRole&id=${group.groupId}"/>";
   }
   
   function viewRole(roleId){
       window.open("<c:out value='${pageContext.request.contextPath}'/>/sm/dialog.do?act=selectRole&roleId=" + roleId,"treeorgId",'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=yes');
   }

</script>