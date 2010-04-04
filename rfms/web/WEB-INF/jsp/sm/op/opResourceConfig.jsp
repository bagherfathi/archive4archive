<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.common.security.ResourceTreeNode" %>
<script language="javascript" src="<c:url value="/js/options.js"/>"></script>
<script src="<c:url value="/js/resource.js"/>"></script>

<html:form action="opResource" method="post">
<input type="hidden" name="act" value="saveConfig" />
<input type="hidden" name="opId" value="<c:out value='${op.operatorId}'/>" />
<input type="hidden" name="loginName" value="<c:out value='${opResourceForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${opResourceForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${opResourceForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />


<webui:panel title="设置操作员访问权限-选择可访问组织" width="95%" icon="../images/icon_list.gif">
    <webui:formTable>
      <tr>
	    <webui:input label="操作员名称">
	        <c:out value="${op.name}"/>
	    </webui:input>
		<webui:input label="可访问组织" required="true">
		    <select name="orgId" id="orgId" onchange="changeOrg(this.value)">
	              <c:forEach items="${accessOrgs}" var="o">
	              <option value="<c:out value="${o.orgId}"/>" <c:if test="${o.orgId==org.orgId}">selected</c:if>><c:out value="${o.name}"/></option>
	              </c:forEach>
	        </select>
	        <img src="<c:url value="/images/icon_sel.gif"/>"  onclick="openDialog('<c:url value="/" />','orgId','可访问组织');"/>
	        <c:if test="${empty accessOrgs}">&nbsp;<font color="red">请先</font><a
							href="<c:url value="/sm/opOrg.do?act=configOrg&opId=${op.operatorId}"/>"><font
							color="red">设置可访问组织</font></a>
			</c:if>
		</webui:input>
	  </tr>
    </webui:formTable>
</webui:panel>
<br/>

<webui:panel title="设置操作员访问权限-选择可访问权限" width="95%" icon="../images/icon_list.gif">
<webui:buildTree beanName="resourceTreeBuilder" var="root" />
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
      <webui:tree 
        root="${root}"
        id="data"
        type="ResourceTreeNode"
        indent="2" extend="2"
        saveToCookie="false"
        level="level"
        closeFolderImg="../images/tree/jia.gif"
        openFolderImg="../images/tree/jian.gif"
        leafImg="../images/tree/space.gif">
        <c:if test="${level==0}"><img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
        </c:if>
        
        <c:if test="${level!=0}">
        	<c:set var="checkedFlag" value="false"/>
        	<c:set var="parentCheckedFlag" value="false"/>
        	<c:forEach items="${resourcesOfOp}" var="resource">
        	  <c:if test="${resource.resourceId == data.key}">
	        	<c:set var="checkedFlag" value="true"/>
              </c:if>
              
              <c:forTokens items="${resource.resourcePath}" delims="#" var="rId">
                  <c:if test="${data.key == rId && !checkedFlag}">
                      <c:set var="parentCheckedFlag" value="true"/>
                  </c:if>
              </c:forTokens>
        	</c:forEach>
        	
            <input class="noborder" type="checkbox" name="ids" value="<c:out value='${data.key}'/>" 
                   id="a<c:out value='${data.path}'/>" onclick='autoCheck(this)' <c:if test="${checkedFlag}">checked</c:if>/>
            
            <c:if test="${empty data.children}">
                <img align="absmiddle" src="../images/book.gif" />
                <c:choose>
			    <c:when test="${!parentCheckedFlag}">
			    <c:out value='${data.nodeName}'/>
			    </c:when>
			    <c:otherwise>
			        <font color="gray"><c:out value='${data.nodeName}'/></font>
			    </c:otherwise>
			    </c:choose>
            </c:if>
            <c:if test="${!empty data.children}">
                <img align="absmiddle" src="../images/books_close.gif" />
                <c:choose>
			    <c:when test="${!parentCheckedFlag}">
			    <c:out value='${data.nodeName}'/>
			    </c:when>
			    <c:otherwise>
			        <font color="gray"><c:out value='${data.nodeName}'/></font>
			    </c:otherwise>
			    </c:choose>
            </c:if>
            <script lanuage=javascript>
			    checkBoxIds[index++]="a<c:out value='${data.path}'/>";
		    </script>
        </c:if>
      </webui:tree>
    </td>
  </tr>
</table>
<webui:linkButton styleClass="clsButtonFace" href="#" onClick="if(validateOrgId()){loadOn();opResourceForm.submit()}" value="保存"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:back()" value="返回"/>
</webui:panel>
</html:form>

<script>
   function changeOrg(orgId){
     location.href = "<c:url value="/sm/opResource.do?act=configResource&opId=${op.operatorId}&loginName=${opResourceForm.loginName}&name=${opResourceForm.name}&orgId_s=${opResourceForm.orgId_s}&listOp_p=${currentPage_listOp}&orgId="/>" + orgId ;
   }
   function back(){
     location.href = "<c:url value="/sm/op.do?act=view&selectedPane=role&opId=${op.operatorId}&loginName=${opResourceForm.loginName}&name=${opResourceForm.name}&orgId_s=${opResourceForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"; 
   }
   
   function validateOrgId(){
       var orgId = document.getElementById("orgId").value;
       if(orgId == ""){
           alert("请选择可访问组织");
           return false;
       }else{
           return true;
       }
    } 
</script>
