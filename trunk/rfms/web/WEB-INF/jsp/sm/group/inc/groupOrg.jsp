<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
	
	
	<webui:formTable width="100%">
		<tr>
			<webui:input label="sysadmin.label.visit.org" colspan="4">
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr><td align="left">
	<webui:tree 
        root="${orgTree}"
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="3" 
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
		</webui:formTable>
	
	 <table width="90%" border="0" cellspacing="2" cellpadding="2"><tr>
       <td align="right">
	<security:checkPermission resourceKey="SM_CONFIG_GROUP_ORG">
		<security:success>
			<webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:configOrg();" value="sysadmin.button.edit"/>
		</security:success>
	</security:checkPermission>
    </td></tr></table>
<script>
   function configOrg(){
     location.href="<c:url value="/sm/groupOrg.do?act=configOrg&id=${group.groupId}"/>"
   }
</script> 
