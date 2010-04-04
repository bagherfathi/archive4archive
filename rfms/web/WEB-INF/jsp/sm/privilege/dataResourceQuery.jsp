<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ page import="com.ft.commons.tree.BaseTreeNode"%>

<script>
    function onSearch(){
     //   if(document.resQueryForm.resourceId.value==""){
     //       alert("请选择业务权限");
     //       return;
     //   }
     var flag = false;
     var obj = document.getElementsByName("resourceIds");
     for(var i=0 ;i<obj.length;i++){
         	if(obj[i].checked){
         	   flag=true;
         	   break;
         	}
        }
        if(!flag){
        	 alert("请选择业务权限");
			 return;
        }
        
        loadOn();
        document.resQueryForm.submit();
    }
    function checkEnter(){
      if(window.event.keyCode==13){
	    onSearch();
     }
    }
   document.body.onkeypress= checkEnter;
</script>
<webui:buildTree beanName="dataResourceTreeBuilder" var="root" />
<html:form action="/dataResourceQuery.do" method="post">
	<html:hidden property="act" value="search" />

	<webui:panel width="95%" title="sysadmin.label.dataResource.query"
		icon="../images/icon_search.gif">
		<webui:formTable>
			<tr>
				<webui:input label="业务权限" colspan="4">
					<table width="100%" align="center" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><webui:tree root="${root}" id="data" type="BaseTreeNode"
								indent="1" extend="2" saveToCookie="false" level="level"
								closeFolderImg="../images/tree/jia.gif"
								openFolderImg="../images/tree/jian.gif"
								leafImg="../images/tree/space.gif">
								<c:if test="${level == 0}">
									<c:out value='${data.nodeName}' />
								</c:if>

								<c:if test="${level == 1}">
									<img align="absmiddle" src="../images/book.gif" />
									<c:out value='${data.nodeName}' />
								</c:if>
								<c:if test="${level == 2}">
                                    <c:set var="flag" value="false"/>
                                    <c:forEach items="${resQueryForm.resourceIds }" var="resId">
                                       <c:if test="${ ! flag }">
                                           <c:if test="${ resId == data.key }" >
                                           <c:set var="flag" value="true"/>
                                           </c:if>
                                       </c:if>
                                    </c:forEach>								
									<input class="noborder" type="checkbox" name="resourceIds"
										id="resourceIds" value="<c:out value='${data.key}'/>"
										title="<c:out value='${data.nodeName}'/>" <c:if test="${flag }" >checked</c:if> />
									<img align="absmiddle" src="../images/books_close.gif" />
									<c:out value='${data.nodeName}' />
								</c:if>
							</webui:tree></td>
						</tr>
					</table>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onSearch();" value="sysadmin.button.search" />
	</webui:panel>
</html:form>

<c:if test="${!empty opList}">
	<br>
	<webui:panel width="95%" title="操作员列表" icon="../images/icon_list.gif">
		<webui:table items="opList" tableId="operatorTable"
			action="${pageContext.request.contextPath}/sm/dataResourceQuery.do?act=search&resourceId=${param.resourceId}"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			width="95%" var="op" showPagination="true" showStatusBar="true"
			showTitle="true" filterable="false" sortable="false"
			autoIncludeParameters="true">
			<webui:row>
				<webui:column filterable="false" property="contact.name"
					title="操作员名称" />
				<webui:column filterable="false" property="loginName" title="登陆名称" />
				<webui:column filterable="false" property="contact.telephone"
					title="联系电话" />
				<webui:column filterable="false" property="contact.mobilePhone"
					title="移动电话" />
				<webui:column filterable="false" property="email" title="E-mail" />
			</webui:row>
		</webui:table>
	</webui:panel>
</c:if>
