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
	<webui:panel title="����Ա��ѯ" icon="../images/icon_search.gif" width="95%">
		<webui:formTable> 
			<tr>
				<webui:input label="��¼��">
					<html:text property="loginName" size="25" />
				</webui:input>
				<webui:input label="����">
					<html:text property="name" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="������֯" colspan="3">
					<sm:orgsTree inputName="orgId_s" orgType="-1" selOrgId="${operatorForm.orgId_s }"></sm:orgsTree>
				</webui:input>
			</tr>
		</webui:formTable>
		<security:checkPermission resourceKey="SM_CREATE_OP">
      <security:success>
          <webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="����"/>
	  </security:success>
  </security:checkPermission>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.forms.operatorForm.submit();"
			value="��ѯ" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:clear();"
			value="����" />	
	</webui:panel>
</html:form>
<br />
<webui:panel title="����Ա�б�" icon="../images/icon_list.gif">
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
			<webui:column style="text-align:center;" sortable="false" property="checkbox" title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.opIds)'/>ȫѡ" filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="opIds" value="<c:out value='${op.operatorId}'/>" <c:if test="${op.status==1}">disabled title="�ѽ�ֹ"</c:if> />
			</webui:column>
			
			<webui:column property="loginName" title="��¼��">
			    <c:out value='${op.loginName}'/>
			</webui:column>
			<webui:column property="contact.name" title="����"/>
			<webui:column property="email" title="��������" />
			<webui:column property="org.orgName" title="������֯" >
			</webui:column>
			<webui:column property="status" title="״̬" >
			   <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
			</webui:column>
			<webui:column property="action" title="����" >
			   <a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}"/>">��ϸ</a>&nbsp;
			<!--   <a href="<c:url value="/sm/op.do?act=edit&opId=${op.operatorId}"/>">�༭</a>  --> 
			   <a href="<c:url value="/sm/op.do?act=copy&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}"/>">����Ȩ��</a>
			</webui:column> 
		</webui:row>
	</webui:table>

  <security:checkPermission resourceKey="SM_DISABLE_OP">
      <security:success>
       <webui:linkButton styleClass="clsButtonFace" href="javascript:submit_form('disable');" value="��ֹ"/>
      </security:success>
  </security:checkPermission>
 
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opRole.do','batchConfigRole')" value="���ܽ�ɫ" />
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opDataRole.do','batchConfigRole')" value="ҵ���ɫ" />
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opOrg.do','batchConfigOrg')" value="�ɷ�����֯" />
  <webui:linkButton styleClass="clsButtonFace" href="#"
		onClick="goBatch('opGroup.do','batchConfigGroup')" value="����Ա��" />		
  
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
		if(!checked) alert("��ѡ�����Ա");
		return checked;
	   }
	function submit_form(act){
	    	if(check()){
	             var ok=window.confirm("ȷ��Ҫ��ֹ��?");
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