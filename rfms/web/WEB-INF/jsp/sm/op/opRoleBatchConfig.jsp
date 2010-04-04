<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/options.js"/>"></script>
<%@ page import="com.ft.commons.tree.BaseTreeNode"%>

<webui:panel title="��ѡ����Ա�б�"  icon="../images/icon_list.gif">
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
			<webui:column property="loginName" title="��¼��">
			</webui:column>
			<webui:column property="contact.name" title="����"/>
			<webui:column property="email" title="��������" />
			<webui:column property="status" title="״̬" >
			   <webui:lookup code="status@SM_OPERATOR" value="${op.status}"/>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
<br/>
<html:form action="opRole" method="post">
    <input type="hidden" name="loginName" value="<c:out value='${operatorRoleForm.loginName}'/>" />
    <input type="hidden" name="name" value="<c:out value='${operatorRoleForm.name}'/>" />
    <input type="hidden" name="orgId_s" value="<c:out value='${operatorRoleForm.orgId_s}'/>" />
    <input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
	<c:forEach items="${opList}" var="op">
		<input type="hidden" name="opIds"
			value="<c:out value="${op.operatorId }"/>" />
	</c:forEach>
	<webui:panel title="�������ò���Ա�Ĺ��ܽ�ɫ" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="��ǰ��֯" colspan="4">
				    <sm:orgsList inputName="orgId" orgType="-2" selOrgId="${orgId}" onchange="changeOrg(this.value)"></sm:orgsList>
				    <img src="<c:url value="/images/icon_sel.gif"/>"  onclick="openDialog('<c:url value="/" />','orgId','��ǰ��֯');"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="��ѡ��ɫ" colspan="4">
					<table width="95%">
				<tr>
					<td><webui:tree root="${root}" id="data" type="BaseTreeNode"
						indent="2" extend="2" saveToCookie="false" level="level"
						closeFolderImg="../images/tree/jia.gif"
						openFolderImg="../images/tree/jian.gif"
						leafImg="../images/tree/space.gif">
						<c:if test="${level==0}">
							<img align="absmiddle" src="../images/books_open.gif" />
							<c:out value='${data.nodeName}' />
						</c:if>
						<c:if test="${level!=0}">
							<input class="noborder" type="checkbox" name="roleIds"
								value="<c:out value="${data.key}"/>" />
							<c:out value='${data.nodeName}' />
						</c:if>
					</webui:tree></td>
				</tr>
				<tr>
				    <td>
				        <a href="#" onClick="javascript:checkAll(document.all.roleIds)">[ȫѡ]</a>
				        &nbsp;<a href="#" onClick="javascript:unCheckAll(document.all.roleIds)">[ȫ��ȡ��]</a>
				    </td>
				</tr>
			</table>
				</webui:input>
			</tr>
	    </webui:formTable>
		
			<webui:linkButton styleClass="clsButtonFace" href="#"
				onClick="postUrl('batchSaveRole')" value="����" />
			<webui:linkButton styleClass="clsButtonFace" href="#"
				onClick="postUrl('batchDeleteRole')" value="ɾ��" />
			<webui:linkButton styleClass="clsButtonFace" href="#"
				onClick="javascript:back()" value="����" />
	</webui:panel>
</html:form>
<script>
   function back(){
       location.href="<c:url value="/sm/op.do?loginName=${operatorRoleForm.loginName}&name=${operatorRoleForm.name}&orgId_s=${operatorRoleForm.orgId_s}&listOp_p=${currentPage_listOp}"/>";
   }
   function changeOrg(orgId){
       document.forms.operatorRoleForm.setAttribute('action', '<c:url value="/sm/opRole.do?act=batchConfigRole&orgId="/>'+orgId);
	   document.forms.operatorRoleForm.submit();
      // location.href="<c:url value="/sm/opRole.do?act=batchConfigRole&orgId="/>" + orgId;
   }
   
    function check(){
   	    var checked =false ;
   	    var obj = document.getElementsByName('roleIds');
			for(var i = 0;i < obj.length; i++) {
				 var e = obj[i];
				 if (e.checked){
				     checked=true;
				     break;
				 }
			}
		if(!checked) alert("��ѡ���ɫ");
		return checked;
	   }
   
   function postUrl(act){
       if(check()){
         loadOn();
         document.forms.operatorRoleForm.setAttribute('action', '<c:url value="/sm/opRole.do?act="/>'+act);
	     document.forms.operatorRoleForm.submit();
	   }
   }
</script>
