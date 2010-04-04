<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();taskCategoryForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>
<html:form action="taskCategory" method="post">
<input type="hidden" name="act" value="search">
<input type="hidden" name="id">
<webui:panel title="任务分类查询" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="分类名称" colspan="4">
	       <html:text property="categoryName" size="25"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();taskCategoryForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
<br>
<webui:panel title="任务分类列表" width="95%" icon="../images/icon_list.gif">
	<webui:table
		dataSource="taskCategoryDS"
		action="${pageContext.request.contextPath}/sm/taskCategory.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.task.category.list"
		var="category"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column  filterable="false" property="name" title="分类名称" styleClass="td_normal">
			</webui:column>
			<webui:column  filterable="false" property="desc" title="分类描述" styleClass="td_normal">
			      <c:out value='${category.desc}'/>
			</webui:column>
			<webui:column  filterable="false" property="action" title="操作">
			      <a href="<c:url value='/sm/taskCategory.do?act=toEdit&id=${category.categoryId}'/>">修改</a>
			      <sm:taskCategory var="flag" id="${category.categoryId}"></sm:taskCategory>
			      <c:if test="${flag == false}">
			      <a href="#" onClick="javascript:toDelete(<c:out value='${category.categoryId}'/>);">删除</a>
		          </c:if>
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:goCreate();" value="sysadmin.button.create"/>
</webui:panel>
</html:form>

<script>
	function goCreate(){
		location.href = "<c:url value='/sm/taskCategory.do?act=create'/>";
	}
	function toDelete(categoryId){
		if(window.confirm("确定要删除所选的分类?")){
			document.taskCategoryForm.act.value="delete"
			document.taskCategoryForm.id.value=categoryId;
			document.taskCategoryForm.submit();
		}
	}
</script>
