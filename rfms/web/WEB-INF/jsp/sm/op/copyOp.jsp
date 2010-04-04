<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="op.do" >
<input type="hidden" name="act" value="saveCopy"/>
<input type="hidden" name="loginName" value="<c:out value='${operatorForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<webui:panel title="选择复制权限" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
	   <tr>
			<webui:input label="源操作员">
					<c:out value="${op.loginName}"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="复制权限">
			    <%--
			    <html:checkbox property="copyType" styleClass="noborder" value="1" <c:if test='${type==1}'>checked</c:if>/>功能角色</html:checkbox>
			    <html:checkbox property="copyType" styleClass="noborder" value="2">业务角色</html:checkbox>
			    <html:checkbox property="copyType" styleClass="noborder" value="3">可访问组织</html:checkbox>
			    <html:checkbox property="copyType" styleClass="noborder" value="4">组</html:checkbox>
			    --%>
			    <input class="noborder" type="checkbox" name="copyType" value="1" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==1}'>checked</c:if>
			      </c:forEach>/>功能角色
			    <input class="noborder" type="checkbox" name="copyType" value="2" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==2}'>checked</c:if>
			      </c:forEach>/>业务角色
			    <input class="noborder" type="checkbox" name="copyType" value="3" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==3}'>checked</c:if>
			      </c:forEach>/>可访问组织
			    <input class="noborder" type="checkbox" name="copyType" value="4" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==4}'>checked</c:if>
			      </c:forEach>/>组
			</webui:input>
		</tr>
	</webui:formTable>
</webui:panel>
<br/>
<webui:panel title="复制目标操作员查询" icon="../images/icon_search.gif" width="95%">
		<webui:formTable> 
			<tr>
				<webui:input label="登录名">
					<html:text property="targetLoginName" size="25" />
				</webui:input>
				<webui:input label="姓名">
					<html:text property="targetName" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:findTargetOp();"
			value="查询" />
</webui:panel>
<br/>
<webui:panel title="选择目标操作员" icon="../images/icon_list.gif">
	<webui:table 
		items="ops"
		action="${pageContext.request.contextPath}/sm/op.do?act=copy"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="op"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		form="operatorForm"
		tableId="operatorForm"
		>
		<webui:row >
			<webui:column property="checkbox" title="选择">
			   <input class="noborder" type="radio" name="opIds" value="<c:out value='${op.operatorId}'/>" <c:if test="${op.status==1}">disabled title="已禁止"</c:if> />
			</webui:column>
			<webui:column property="loginName" title="登录名">
			    <c:out value='${op.loginName}'/>
			</webui:column>
			<webui:column property="contact.name" title="姓名"/>
			<webui:column property="email" title="电子邮箱" />
			<webui:column property="org.orgName" title="所属组织" >
			</webui:column>
			<webui:column property="status" title="状态" >
			   <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
			</webui:column>
		</webui:row>
	</webui:table>
          <webui:linkButton styleClass="clsButtonFace" href="#" onClick="copyOp()" value="复制"/>
          <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="返回"/>
</webui:panel>
</html:form>
<script>
    function check(ids,msg){
   	    var checked =false ;
   	    var obj = document.getElementsByName(ids);
			for(var i = 0;i < obj.length; i++) {
				 var e = obj[i];
				 if (e.checked){
				     checked=true;
				     break;
				 }
			}
		if(!checked) alert(msg);
		return checked;
	   }
	
	function copyOp(){
        if(check('copyType',"请选择复制权限") && check('opIds',"请选择操作员") ){
            loadOn();
            document.forms.operatorForm.submit();
        }
    }
    function back(){
        location.href="<c:url value='/sm/op.do?loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}'/>"
    }
    
    function findTargetOp(){
        document.forms.operatorForm.setAttribute('action', '<c:url value="/sm/op.do?act=copy"/>');
	    document.forms.operatorForm.submit();
    }
</script>
