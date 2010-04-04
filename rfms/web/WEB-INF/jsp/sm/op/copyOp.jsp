<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="op.do" >
<input type="hidden" name="act" value="saveCopy"/>
<input type="hidden" name="loginName" value="<c:out value='${operatorForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<webui:panel title="ѡ����Ȩ��" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
	   <tr>
			<webui:input label="Դ����Ա">
					<c:out value="${op.loginName}"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="����Ȩ��">
			    <%--
			    <html:checkbox property="copyType" styleClass="noborder" value="1" <c:if test='${type==1}'>checked</c:if>/>���ܽ�ɫ</html:checkbox>
			    <html:checkbox property="copyType" styleClass="noborder" value="2">ҵ���ɫ</html:checkbox>
			    <html:checkbox property="copyType" styleClass="noborder" value="3">�ɷ�����֯</html:checkbox>
			    <html:checkbox property="copyType" styleClass="noborder" value="4">��</html:checkbox>
			    --%>
			    <input class="noborder" type="checkbox" name="copyType" value="1" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==1}'>checked</c:if>
			      </c:forEach>/>���ܽ�ɫ
			    <input class="noborder" type="checkbox" name="copyType" value="2" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==2}'>checked</c:if>
			      </c:forEach>/>ҵ���ɫ
			    <input class="noborder" type="checkbox" name="copyType" value="3" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==3}'>checked</c:if>
			      </c:forEach>/>�ɷ�����֯
			    <input class="noborder" type="checkbox" name="copyType" value="4" 
			      <c:forEach items="${operatorForm.copyType}" var="type">
			        <c:if test='${type==4}'>checked</c:if>
			      </c:forEach>/>��
			</webui:input>
		</tr>
	</webui:formTable>
</webui:panel>
<br/>
<webui:panel title="����Ŀ�����Ա��ѯ" icon="../images/icon_search.gif" width="95%">
		<webui:formTable> 
			<tr>
				<webui:input label="��¼��">
					<html:text property="targetLoginName" size="25" />
				</webui:input>
				<webui:input label="����">
					<html:text property="targetName" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:findTargetOp();"
			value="��ѯ" />
</webui:panel>
<br/>
<webui:panel title="ѡ��Ŀ�����Ա" icon="../images/icon_list.gif">
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
			<webui:column property="checkbox" title="ѡ��">
			   <input class="noborder" type="radio" name="opIds" value="<c:out value='${op.operatorId}'/>" <c:if test="${op.status==1}">disabled title="�ѽ�ֹ"</c:if> />
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
		</webui:row>
	</webui:table>
          <webui:linkButton styleClass="clsButtonFace" href="#" onClick="copyOp()" value="����"/>
          <webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="����"/>
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
        if(check('copyType',"��ѡ����Ȩ��") && check('opIds',"��ѡ�����Ա") ){
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
