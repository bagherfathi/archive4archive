<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function onEdit(){
        window.location.href='<c:url value="/sm/enumGroup.do?act=edit&groupId=${enumGroupForm.enumGroup.groupId}"/>';
    }
    
    function onRemove(){
        if (confirm("<bean:message key='msg.confirm.delete.enumgroup' />")) {
            document.enumGroupForm.act.value="delete";
            document.enumGroupForm.submit();
        }
    }
    
    function onAddEnumCategoty(){
        window.location.href='<c:url value="/sm/enumCategory.do?act=create&groupId=${enumGroupForm.enumGroup.groupId}"/>';
    }
    
    function onReturn(){
        window.location.href='<c:url value="/sm/enumGroup.do"/>';
    }
    
    function onExport(){
    	document.enumGroupForm.act.value="export";
        document.enumGroupForm.submit();
    }
</script>

<sm:query var="enumCategories" beanName="enumManager" methodName="findEnumCategoriesOfGroup">
    <sm:param value="${enumGroupForm.enumGroup.groupId}" type="java.lang.Long"/>
</sm:query>

<html:form styleId="enumGroupForm" action="/enumGroup" method="post" onsubmit="return validateEnumGroupForm(this)">
<input type="hidden" value="save" name="act"/>
<input type="hidden" name="validationKey" value="enumGroupForm"/>
<input type="hidden" name="groupId" value="<c:out value='${enumGroup.groupId}'/>"/>
<webui:panel title="sysadmin.title.enumgroup.indo" width="95%" icon="../images/icon_list.gif">
    <table  width="95%" class="table-bg" cellspacing="1" cellpadding="2">
      <tr>
	    <webui:input label="sysadmin.label.enumgroup.name" required="true" colspan="4">
	       <html:text property="enumGroup.groupName" size="25"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="sysadmin.label.desc" colspan="4">
	      <html:textarea property="enumGroup.description" styleClass="wid80" rows="3"/>
    	</webui:input>
      </tr>
    </table>
  <security:checkPermission resourceKey="SM_SAVE_ENUM_DATA_GROUP">
      	<security:success>
      	   <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(enumGroupForm);" value="sysadmin.button.save"/>
  		</security:success>
  </security:checkPermission>
  
  <c:if test="${!empty enumGroup.groupId}">
	<c:if test="${empty enumCategories}">
    	<security:checkPermission resourceKey="SM_DELETE_ENUM_DATA_GROUP">
      		<security:success>
      		   <webui:linkButton styleClass="clsButtonFace" href="javascript:onRemove();" value="删除"/>
      		</security:success>
		</security:checkPermission>
	</c:if>
  </c:if>
  
  <webui:linkButton styleClass="clsButtonFace" href="javascript:onReturn();" value="sysadmin.button.return"/>
<%--
<security:checkPermission resourceKey="SM_EXPORT_ENUM_DATA_GROUP">
	<security:success>
	    <webui:linkButton styleClass="clsButtonFace" href="javascript:onExport();" value="sysadmin.button.export"/>
	</security:success>
</security:checkPermission>
--%>
</webui:panel>
</html:form>

<c:if test="${!empty enumGroup.groupId}">
<br/>
<webui:panel title="类别列表" width="95%" icon="../images/icon_list.gif">
    <webui:table
		items="enumCategories"
		action="${pageContext.request.contextPath}/sm/enumGroup.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.enumgroup.list"
		var="category"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" property="categoryName" title="类别名称"/>
			<webui:column sortable="false" filterable="false" property="categoryCode" title="类别编码">
			    <c:out value="${category.categoryCode}"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="操作" title="操作">
			    <a href='<c:url value="/sm/enumCategory.do?act=edit&categoryId=${category.categoryId}"/>'>修改
			    </a>
			</webui:column>
		</webui:row>
	</webui:table>
	<c:if test="${!empty enumGroup.groupId}">
	<security:checkPermission resourceKey="SM_ADD_ENUM_DATA">
		<security:success>
		     <webui:linkButton styleClass="clsButtonFace" href="javascript:onAddEnumCategoty();" value="sysadmin.button.create"/>
		</security:success>
  	</security:checkPermission>
  	</c:if>
</webui:panel>
</c:if>
<c:choose>
<c:when test="${param.flag == 'add' }">
      <script>
      parent.leftFrame.tree.insertNewItem("root_-1","group_<c:out value="${enumGroup.groupId}"/>","<c:out value="${enumGroup.groupName}"/>",0,"book.gif","books_open.gif","books_close.gif","SELECT");
      </script>
</c:when>
<c:when test="${param.flag == 'update' }">
      <script>
      var tree = parent.leftFrame.tree ;
      tree.setItemText("group_<c:out value="${enumGroup.groupId}"/>","<c:out value="${enumGroup.groupName}"/>");
      </script>
</c:when>
<c:when test="${param.flag == 'delete' }">
      <script>
      parent.leftFrame.tree.deleteItem("category_<c:out value="${param.deletedId}"/>",true);
      </script>
</c:when>
<c:when test="${empty param.flag }">
      <script>
      var tree = parent.leftFrame.tree ;
      tree.selectItem("group_<c:out value="${enumGroup.groupId}"/>",false);
      tree.openItem("group_<c:out value="${enumGroup.groupId}"/>");
      </script>
</c:when>
</c:choose>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="enumGroupForm" />
