<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
 <sm:query beanName="operatorManager" methodName="findOperatorRoleForOrgsOfOperator" var="opRoles">
     <sm:param value="${op.operatorId}" type="java.lang.Long"/>
     <sm:param value="1" type="java.lang.Long"/>
     <sm:param value="true" type="java.lang.Boolean"/>
 </sm:query>
  <sm:query beanName="groupManager" methodName="findGroupRoleForOrgsOfOperator" var="roleInGroups">
     <sm:param value="${op.operatorId}" type="java.lang.Long"/>
     <sm:param value="1" type="java.lang.Long"/>
 </sm:query>
  <webui:buildTree beanName="opResourceTreeBuilder" var="root" >
<webui:buildTreeParam name="roleList" type="java.lang.ArrayList" value="opRoles" /> 
<webui:buildTreeParam name="roleInGroup" type="java.lang.ArrayList" value="roleInGroups" />
<webui:buildTreeParam name="roleType" type="java.lang.Long" value="1" />
</webui:buildTree>

<webui:formTable width="100%">
	<tr>
		<webui:input label="sysadmin.label.role" colspan="4">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr><td align="left">
	<webui:tree 
        root="root"
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="3" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif">
        <%--
        <c:if test="${level==0}">
		        	<img align="absmiddle" src="../images/books_open.gif" /><a href="#" onclick="viewRole(<c:out value='${data.key}'/>)"><c:out value='${data.nodeName}'/></a>
		        </c:if>
		        <c:if test="${level!=0}">
		            <c:if test="${empty data.children}">  
		                <img align="absmiddle" src="../images/book.gif" /><a href="#" onclick="viewRole(<c:out value='${data.key}'/>)"><c:out value='${data.nodeName}'/></a>
                    </c:if>
                    <c:if test="${!empty data.children}">
                        <img align="absmiddle" src="../images/books_close.gif" /><a href="#" onclick="viewRole(<c:out value='${data.key}'/>)"><c:out value='${data.nodeName}'/></a>
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
  </tr>
 </table>
 </webui:input>
 </tr>
 </webui:formTable>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr><td align="right">
	 <security:checkPermission resourceKey="SM_CONFIG_OP_ROLE">
      <security:success>
	<webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:roleConfig();" value="sysadmin.button.setrole"/>
       </security:success>
      </security:checkPermission>
      <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:permissionConfig();" value="sysadmin.button.setpermission"/>
   </td></tr>
      </table>   
<script>
   function roleConfig(){
        loadOn();
   		location.href = "<c:url value="/sm/opRole.do?act=configRole&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}"/>";
   }
   
   function permissionConfig(){
       loadOn();
       location.href = "<c:url value="/sm/opResource.do?act=configResource&opId=${op.operatorId}&&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}"/>";
   }
   
   function viewRole(roleId){
       window.open("/boss/sm/dialog.do?act=selectRole&roleId=" + roleId,"treeorgId",'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=yes');
   }
</script>