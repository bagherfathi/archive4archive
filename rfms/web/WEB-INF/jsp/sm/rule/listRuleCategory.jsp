<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();ruleCategoryForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>
<html:form action="ruleCategory" method="post">
<input type="hidden" name="act" value="search">
<input type="hidden" name="id">
<webui:panel title="规则分类查询" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="分类名称" colspan="4">
	       <html:text property="categoryName" size="25"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();ruleCategoryForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
<br>
<webui:panel title="sysadmin.title.rule.category.list" width="95%" icon="../images/icon_list.gif">
	<webui:table
		dataSource="ruleCategoryDS"
		action="${pageContext.request.contextPath}/sm/ruleCategory.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.rule.category.list"
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
			<webui:column  filterable="false" property="name" title="分类名称">
			</webui:column>
			<webui:column  filterable="false" property="desc" title="分类描述">
			      <c:out value='${category.desc}'/>
			</webui:column>
			<webui:column  filterable="false" property="操作" title="操作">
			      <a href="<c:url value='/sm/ruleCategory.do?act=toEdit&id=${category.categoryId}'/>">修改</a>
			      <sm:ruleCategory id="${category.categoryId}" var="flag"/>
			      <c:if test="${flag == false}">
			      <a href="#" onClick="javascript:toDelete(<c:out value='${category.categoryId}'/>);">删除</a>
		          </c:if>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_RULE_CATEGORY">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:goCreate();" value="sysadmin.button.create"/>
		</security:success>
	</security:checkPermission>
</webui:panel>
</html:form>

<script>
	function goCreate(){
		location.href = "<c:url value='/sm/ruleCategory.do?act=create'/>";
	}
	function toDelete(categoryId){
		if(window.confirm("<bean:message key='msg.confirm.delete.rule.category'/>")){
			document.ruleCategoryForm.act.value="delete"
			document.ruleCategoryForm.id.value=categoryId;
			document.ruleCategoryForm.submit();
		}
	}
</script>
