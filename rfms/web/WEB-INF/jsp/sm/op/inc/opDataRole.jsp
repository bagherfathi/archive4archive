<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<sm:query beanName="operatorManager" methodName="findOperatorRoleForOrgsOfOperator" var="opRoles">
     <sm:param value="${op.operatorId}" type="java.lang.Long"/>
     <sm:param value="2" type="java.lang.Long"/>
     <sm:param value="true" type="java.lang.Boolean"/>
 </sm:query>

  <webui:buildTree beanName="opResourceTreeBuilder" var="root" >
<webui:buildTreeParam name="roleList" type="java.lang.ArrayList" value="opRoles" /> 
<webui:buildTreeParam name="roleType" type="java.lang.Long" value="2" />
</webui:buildTree>

<webui:formTable width="100%">
	<tr>
		<webui:input label="sysadmin.label.data.role" colspan="4">
<table width="100%" border="0" cellspacing="2" cellpadding="2"><tr><td align="left">
	<webui:tree 
        root="root"
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="3" 
        saveToCookie="false" 
        level="level" 
        treeName="opDateRole"
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
	</td></tr></table>
	</webui:input></tr></webui:formTable>
	<security:checkPermission resourceKey="SM_CONFIG_OP_ROLE">
      <security:success>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr><td align="right">
         <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:dataRoleConfig();" value="sysadmin.button.setrole"/>
	   </td></tr> </table>
      </security:success>
      </security:checkPermission> 
<script>
   function dataRoleConfig(){
        loadOn();
   		location.href = "<c:url value="/sm/opDataRole.do?act=configRole&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}"/>";
   }
</script>
