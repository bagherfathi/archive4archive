<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
function checkEnter(){
   if(window.event.keyCode==13){
	    loadOn();
	    document.forms.operatorForm.submit();
   }
}
document.body.onkeypress= checkEnter;

function clear(){
    document.operatorForm.orgId_s.value = "";
    document.operatorForm.select_orgId_s.value = "";
    document.operatorForm.loginName.value = "";
    document.operatorForm.name.value = "";
}
</script>
<html:form action="op" method="post">
	<webui:panel title="操作员查询" icon="../images/icon_search.gif" width="95%">
		<webui:formTable> 
			<tr>
				<webui:input label="登录名">
					<html:text property="loginName" size="25" />
				</webui:input>
				<webui:input label="姓名">
					<html:text property="name" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="所属组织" colspan="3">
					<sm:orgsTree inputName="orgId_s" orgType="-1" selOrgId="${operatorForm.orgId_s }"></sm:orgsTree>
				</webui:input>
			</tr>
		</webui:formTable>
		<security:checkPermission resourceKey="SM_CREATE_OP">
      <security:success>
          <webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="新增"/>
	  </security:success>
  </security:checkPermission>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.forms.operatorForm.submit();"
			value="查询" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:clear();"
			value="重置" />	
	</webui:panel>
</html:form>
<br />
<webui:panel title="操作员列表" icon="../images/icon_list.gif">
	<webui:table 
		items="ops" tableId="listOp"
		action="${pageContext.request.contextPath}/sm/op.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="op"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		>
		<webui:row >
			<webui:column style="text-align:center;" sortable="false" property="checkbox" title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.opIds)'/>全选" filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="opIds" value="<c:out value='${op.operatorId}'/>" <c:if test="${op.status==1}">disabled title="已禁止"</c:if> />
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
			<webui:column property="action" title="操作" >
			   <a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}"/>">详细</a>&nbsp;
			<!--   <a href="<c:url value="/sm/op.do?act=edit&opId=${op.operatorId}"/>">编辑</a>  --> 
			   <a href="<c:url value="/sm/op.do?act=copy&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}"/>">复制权限</a>
			</webui:column> 
		</webui:row>
	</webui:table>

  <security:checkPermission resourceKey="SM_DISABLE_OP">
      <security:success>
       <webui:linkButton styleClass="clsButtonFace" href="javascript:submit_form('disable');" value="禁止"/>
      </security:success>
  </security:checkPermission>
 
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opRole.do','batchConfigRole')" value="功能角色" />
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opDataRole.do','batchConfigRole')" value="业务角色" />
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opOrg.do','batchConfigOrg')" value="可访问组织" />
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opGroup.do','batchConfigGroup')" value="操作员组" />		
  
</webui:panel>
<script>
    function check(){
   	    var checked =false ;
   	    var obj = document.getElementsByName('opIds');
			for(var i = 0;i < obj.length; i++) {
				 var e = obj[i];
				 if (e.checked){
				     checked=true;
				     break;
				 }
			}
		if(!checked) alert("请选择操作员");
		return checked;
	   }
	function submit_form(act){
	    	if(check()){
	             var ok=window.confirm("确定要禁止吗?");
				 if(ok==true){
				    loadOn();
			   		document.forms.listOp.setAttribute('action', '<c:url value="/sm/op.do?act="/>'+act);
	            	document.forms.listOp.submit();
	        	}
	       }
	 }
	function goBatch(action,act){
	    if(check()){
			loadOn();
			document.forms.listOp.setAttribute('action', '<c:url value="/sm/"/>' + action + "<c:url value='?loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&act='/>" + act);
	        document.forms.listOp.submit();
	    }
		else return false;
	}
	function onCreate(){
        loadOn();
        location.href="<c:url value="/sm/op.do?act=create&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}"/>"
    }
</script>