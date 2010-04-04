<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<webui:panel title="所选操作员列表"  icon="../images/icon_list.gif">
         <webui:table 
		items="opList"
		action="${pageContext.request.contextPath}/sm/op.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="op"
		width="95%"
		showPagination="false"
		showStatusBar="false"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row >
			<webui:column property="loginName" title="登录名">
			</webui:column>
			<webui:column property="contact.name" title="姓名"/>
			<webui:column property="email" title="电子邮箱" />
			<webui:column property="status" title="状态" >
			   <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
<br/>

<webui:panel title="批量设置操作员的组"  icon="../images/icon_list.gif">
<html:form action="opGroup" >
<input type="hidden" name="loginName" value="<c:out value='${operatorGroupForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorGroupForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorGroupForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<c:forEach items="${opList}" var="op">
	<input type="hidden" name="opIds" value="<c:out value="${op.operatorId }" />" />
</c:forEach>
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
    <td>
      <webui:tree 
        root="${root}" 
        id="data" 
        type="BaseTreeNode" 
        indent="2" extend="2" 
        saveToCookie="false" 
        level="level" 
        closeFolderImg="../images/tree/jia.gif" 
        openFolderImg="../images/tree/jian.gif" 
        leafImg="../images/tree/space.gif">
       <c:if test="${level != 0}" >
       <input class="noborder" type="checkbox" name="groupIds" value="<c:out value='${data.key}'/>" <c:if test="${data.status == 1}">disabled title="组织已禁止"</c:if> />
       </c:if> 
        <c:if test="${empty data.parent}">
            <img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
        </c:if>
        <c:if test="${!empty data.parent}">
            <c:if test="${empty data.children}">
                <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
            </c:if>
            <c:if test="${!empty data.children}">
                <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
            </c:if>
        </c:if>
      </webui:tree>
    </td>
  </tr>
  <tr>
		<td>
			 <a href="#" onClick="javascript:checkAll(document.all.groupIds)">[全选]</a>
				&nbsp;<a href="#" onClick="javascript:unCheckAll(document.all.groupIds)">[全部取消]</a>
		</td>
  </tr>
  </table>
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="postUrl('batchSaveGroup');" value="新增"/>
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="postUrl('batchDeleteGroup');" value="删除"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="返回"/>
</html:form>
</webui:panel>

<script>
    function check(){
   	    var checked =false ;
   	    var obj = document.getElementsByName('groupIds');
			for(var i = 0;i < obj.length; i++) {
				 var e = obj[i];
				 if (e.checked){
				     checked=true;
				     break;
				 }
			}
		if(!checked) alert("请选择操作员组");
		return checked;
	   }
   function back(){
     location.href = "<c:url value="/sm/op.do?loginName=${operatorGroupForm.loginName}&name=${operatorGroupForm.name}&orgId_s=${operatorGroupForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"; 
   }
   function postUrl(act){
     if(check()){
       loadOn();
       document.forms.operatorGroupForm.setAttribute('action', '<c:url value="/sm/opGroup.do?listOp_p=${currentPage_listOp}&act="/>'+act);
	   document.forms.operatorGroupForm.submit();
	 }  
	}
</script>