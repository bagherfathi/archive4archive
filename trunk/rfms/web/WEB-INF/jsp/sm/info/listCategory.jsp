<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();infoCategoryForm.submit();
     }
    }
   document.body.onkeypress= checkEnter;
</script>
<html:form action="/infoCategory" method="post">
<input type="hidden" name="act" value="search"/>
<webui:panel title="信息分类查询" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="sysadmin.label.category.name" colspan="4">
	       <html:text property="categoryName" size="25"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();infoCategoryForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>

<br>
<webui:panel title="sysadmin.label.category.list" width="95%" icon="../images/icon_list.gif">
	<webui:table
		dataSource="infoCateforyDS"
		action="${pageContext.request.contextPath}/sm/infoCategory.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.category.list"
		var="category"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  filterable="false" property="name" title="分类名称" >
			</webui:column>
			<webui:column  filterable="false" property="desc" title="分类描述"  styleClass="td_normal">
			      <c:out value='${category.desc}'/>
			</webui:column>
			<webui:column  filterable="false" property="desc" title="操作" width="15%" styleClass="td_normal">
			      <a href="<c:url value='/sm/infoCategory.do?act=toEdit&id=${category.categoryId}'/>">修改</a>
			      <sm:infoCategory id="${category.categoryId}" var="flag"/>
		          <c:if test="${flag == false}">
		          &nbsp;<a href="#" onclick="javascript:onDelete(<c:out value='${category.categoryId}'/>)">删除</a>
		          </c:if>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_INFO_CATEGORY">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:goCreate();" value="sysadmin.button.create"/>
		</security:success>
	</security:checkPermission>
</webui:panel>
<script>
	function goCreate(){
		location.href = "<c:url value='/sm/infoCategory.do?act=create'/>";
	}
	
	function onDelete(categoryId){
	    if(window.confirm("<bean:message key='msg.confirm.delete.info.category'/>")){
			location.href="<c:url value='/sm/infoCategory.do?act=delete&id='/>" + categoryId;
		}
	}
</script>
