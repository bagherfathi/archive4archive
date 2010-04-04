<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<webui:formTable width="100%">
		<tr>
			<webui:input label="可访问组织" colspan="4">
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr><td align="left">
	<webui:tree 
        root="${orgTree}"
        id="data" 
        type="BaseTreeNode" 
        indent="1" extend="1" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif"
        treeName="orgTree"
        >
        <c:if test="${level==0}">
		        	<img align="absmiddle" src="../images/books_open.gif" />
		        </c:if>
		        <c:if test="${level!=0}">
		            <c:if test="${empty data.children}">  
		                <img align="absmiddle" src="../images/book.gif" />
                    </c:if>
                    <c:if test="${!empty data.children}">
                        <img align="absmiddle" src="../images/books_close.gif" />
                    </c:if>
		        </c:if> 
		        <c:if test="${ data.status ==1 }"><del><c:out value='${data.nodeName}'/></del></c:if><c:if test="${ data.status !=1 }"><c:out value='${data.nodeName}'/></c:if>
    </webui:tree> </td></tr></table>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="继承的可访问组织" colspan="4">
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr><td align="left">
	<webui:tree 
        root="${orgTreeInGroup}"
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="3" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif"
        treeName="orgTreeInGroup"
        >
        <c:if test="${level==0}">
		        	<img align="absmiddle" src="../images/books_open.gif" />
		        </c:if>
		        <c:if test="${level!=0}">
		            <c:if test="${empty data.children}">  
		                <img align="absmiddle" src="../images/book.gif" />
                    </c:if>
                    <c:if test="${!empty data.children}">
                        <img align="absmiddle" src="../images/books_close.gif" />
                    </c:if>
		        </c:if> 
		         <c:if test="${ data.status ==1 }"><del><c:out value='${data.nodeName}'/></del></c:if><c:if test="${ data.status !=1 }"><c:out value='${data.nodeName}'/></c:if>
    </webui:tree> </td></tr></table>
			</webui:input>
		</tr>
	
</webui:formTable>    
     <security:checkPermission resourceKey="SM_CONFIG_OP_ORG">
      <security:success>
        <table width="100%" border="0" cellspacing="2" cellpadding="2"><tr>
       <td align="right">
         <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:configOrg();" value="sysadmin.title.button.opOrgConfig"/>
        </td></tr></table>
      </security:success>
      </security:checkPermission> 
	
	<script>
	   function configOrg(){
	       loadOn();
	       location.href = "<c:url value="/sm/opOrg.do?act=configOrg&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"
	   }
	</script>
	