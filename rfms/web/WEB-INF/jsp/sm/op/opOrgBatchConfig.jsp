<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
<%@ page import="com.ft.commons.tree.BaseTreeNode" %>
<script src="<c:url value="/js/resource.js"/>"></script>

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
<webui:panel title="批量设置操作员可访问的组织"  icon="../images/icon_list.gif">
<html:form action="opOrg" >
<input type="hidden" name="loginName" value="<c:out value='${operatorOrgForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorOrgForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorOrgForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<c:forEach items="${opList}" var="op">
	<input type="hidden" name="opIds" value="<c:out value="${op.operatorId }" />" />
</c:forEach>

<sm:buildOrgsTree var="root" orgType="-1"></sm:buildOrgsTree>			
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
       <c:if test="${data.key != 'root'}">
        <input class="noborder" type="checkbox" name="orgIds" id="id<c:out value='${data.object.path}'/>" value="<c:out value='${data.key}'/>" <c:if test="${data.key==org.orgId }">checked=true</c:if> 
        <c:choose>
           <c:when test="${data.status==1}">disabled title="此组织已禁止"</c:when>  
           <c:when test="${data.status==2}">disabled title="此组织不可访问"</c:when>
           <c:otherwise>title="<c:out value="${data.nodeName}"/>" </c:otherwise>
        </c:choose> onclick="autoCheck(this)" 
            />
            <script lanuage=javascript>
				checkBoxIds[index++]="id<c:out value='${data.object.path}'/>";
        </script>
        </c:if>
        <c:out value='${data.nodeName}'/> 
      </webui:tree>
    </td>
  </tr>
  <tr>
    <td><br/>
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="postUrl('batchSaveOrg');" value="新增"/>
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="postUrl('batchDeleteOrg');" value="删除"/>
    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back();" value="返回"/>
    </td>
  </tr>
</table>
</html:form>
</webui:panel>
<script>
 function check(){
   	    var checked =false ;
   	    var obj = document.getElementsByName('orgIds');
			for(var i = 0;i < obj.length; i++) {
				 var e = obj[i];
				 if (e.checked){
				     checked=true;
				     break;
				 }
			}
		if(!checked) alert("请选择组织");
		return checked;
	   }
   function back(){
     location.href = "<c:url value="/sm/op.do?loginName=${operatorOrgForm.loginName}&name=${operatorOrgForm.name}&orgId_s=${operatorOrgForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"; 
   }
   function postUrl(act){
     if(check()){
       loadOn();
       document.forms.operatorOrgForm.setAttribute('action', '<c:url value="/sm/opOrg.do?act="/>'+act);
	   document.forms.operatorOrgForm.submit();
	 }
	}
</script>