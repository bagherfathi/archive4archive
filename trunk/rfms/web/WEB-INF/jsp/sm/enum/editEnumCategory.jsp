<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function onEdit(){
        window.location.href='<c:url value="/sm/enumCategory.do?act=edit&categoryId=${enumCategoryForm.enumCategory.categoryId}"/>';
    }
    
    function onRemove(){
        if (confirm("确定要删除系统数据类别？")) {
            document.enumCategoryForm.act.value="delete";
            document.enumCategoryForm.submit();
        }
    }
    
    function onAddEnum(){
        window.location.href='<c:url value="/sm/enum.do?act=create&categoryId=${enumCategoryForm.enumCategory.categoryId}"/>';
    }
    
    function onReturn(){
        window.location.href='<c:url value="/sm/enumGroup.do?act=edit&groupId=${enumCategoryForm.groupId}"/>';
    }
    
    function onReturn1(){
        window.location.href='<c:url value="/sm/enumGroup.do?act=edit&groupId=${enumCategory.groupId}"/>';
    }
</script>

<sm:query var="enumDatas" beanName="enumManager" methodName="findEnumDatasByCategory">
    <sm:param value="${enumCategoryForm.enumCategory.categoryId}" type="java.lang.Long"/>
</sm:query>

<html:form styleId="enumCategoryForm" action="/enumCategory" method="post" onsubmit="return validateEnumCategoryForm(this)">
<input type="hidden" value="save" name="act"/>
<input type="hidden" name="validationKey" value="enumCategoryForm"/>
<html:hidden property="groupId"/>
<input type="hidden" name="categoryId" value="<c:out value='${enumCategory.categoryId}'/>"/>

<webui:panel title="系统数据类别信息" icon="../images/icon_list.gif">
    <table  width="95%" class="table-bg" cellspacing="1" cellpadding="2">
      <tr>
	    <webui:input label="sysadmin.label.enumcategory.name" required="true">
	       <html:text property="enumCategory.categoryName" size="25"/>
	    </webui:input>
	    <webui:input label="sysadmin.label.enumcategory.code" required="true">
	      <c:if test="${!empty enumCategory.categoryId}">
	          <html:text property="enumCategory.categoryCode" size="25" readonly="true"/>
	      </c:if>
	      <c:if test="${empty enumCategory.categoryId}">
	          <html:text property="enumCategory.categoryCode" size="25"/>
	      </c:if>
    	</webui:input>
      </tr>
    </table>
  <security:checkPermission resourceKey="SM_SAVE_ENUM_DATA_GROUP">
      	<security:success>
      	   <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(enumCategoryForm);" value="sysadmin.button.save"/>
  		</security:success>
  </security:checkPermission>
  
<c:if test="${!empty enumCategory.categoryId}">
	<c:if test="${empty enumDatas}">
    	<security:checkPermission resourceKey="SM_DELETE_ENUM_DATA_GROUP">
      		<security:success>
      		   <webui:linkButton styleClass="clsButtonFace" href="javascript:onRemove();" value="删除"/>
      		</security:success>
		</security:checkPermission>
	</c:if>
</c:if>

<c:if test="${!empty enumCategory.categoryId}">
      <webui:linkButton styleClass="clsButtonFace" href="javascript:onReturn1();" value="sysadmin.button.return"/>
  </c:if>
  <c:if test="${empty enumCategory.categoryId}">
      <webui:linkButton styleClass="clsButtonFace" href="javascript:onReturn();" value="sysadmin.button.return"/>
  </c:if>
</webui:panel>
</html:form>

<c:if test="${!empty enumCategory.categoryId}">
<br/>
<webui:panel title="系统数据列表" width="95%" icon="../images/icon_list.gif">
    <webui:table
		items="enumDatas"
		action="${pageContext.request.contextPath}/sm/enumGroup.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.enumgroup.list"
		var="enumData"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" property="enumName" title="数据名称">
			</webui:column>
			<webui:column sortable="false" filterable="false" property="description" title="数据代码">
			    <c:out value="${enumData.enumCode}"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="status" title="状态">
			    <webui:lookup code="status@SM_ENUM" value="${enumData.status}"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="操作" title="操作">
			    <a href='<c:url value="/sm/enum.do?act=edit&enumId=${enumData.enumId}"/>'>修改
			    </a>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_ENUM_DATA">
		<security:success>
		     <webui:linkButton styleClass="clsButtonFace" href="javascript:onAddEnum();" value="sysadmin.button.create"/>
		</security:success>
  	</security:checkPermission>
</webui:panel>
</c:if>
<c:if test="${param.flag == 'add' }">
      <script>
      parent.leftFrame.tree.insertNewItem("group_<c:out value="${enumCategoryForm.enumCategory.groupId}"/>","category_<c:out value="${enumCategoryForm.enumCategory.categoryId}"/>","<c:out value="${enumCategoryForm.enumCategory.categoryName}"/>",0,"book.gif","books_open.gif","books_close.gif","SELECT");
      </script>
</c:if>
<c:if test="${param.flag == 'update' }">
      <script>
      parent.leftFrame.tree.setItemText("category_<c:out value="${enumCategoryForm.enumCategory.categoryId}"/>","<c:out value="${enumCategoryForm.enumCategory.categoryName}"/>");
      </script>
</c:if>
<c:if test="${empty param.flag }">
      <script>
      var tree = parent.leftFrame.tree ;
      tree.selectItem("category_<c:out value="${enumCategoryForm.enumCategory.categoryId}"/>",false);
      tree.openItem("category_<c:out value="${enumCategoryForm.enumCategory.categoryId}"/>");
      </script>
</c:if>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="enumCategoryForm" />
