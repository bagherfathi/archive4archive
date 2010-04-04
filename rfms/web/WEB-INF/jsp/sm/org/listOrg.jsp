<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="org" method="post">
<webui:panel title="����" icon="../images/icon_search.gif" width="95%">	
    <webui:formTable>
    	<tr>
    		<webui:input label="��֯��">
            	<input type="text" name="name"/>
			</webui:input>	
			<webui:input label="��֯����">
				<input type="text" name="code"/>  
			</webui:input>
		</tr>
		<tr>
		<webui:input label="״̬">
			<select name="status">
			<option value="">����</option>
				<bean:define id="enum" name="enumSet" property="element(SM_ORG_STATUS_ENUM)"/>
			<c:forEach var="status" items="${enum}">
				<option value="<c:out value='${status.value}'/>"><c:out value='${status.label}'/></option>			                         
			</c:forEach>
			</select>
		</webui:input>	
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();document.forms.orgForm.submit();" value="����"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="��֯����" icon="../images/icon_list.gif">
	<webui:table 
		dataSource="orgDS"
		action="${pageContext.request.contextPath}/sm/org.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="org"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
		    <webui:column property="checkbox" title="ѡ��" width="6%">
		         <sm:org id="${org.orgId}" var="flag"/>
				<input class="noborder" type="checkbox" name="orgIds" value="<c:out value='${org.orgId}'/>" 
				  <c:if test="${flag && org.status==0}">disabled title=���ܽ�ֹ</c:if>
				  <c:if test="${org.status==1}">disabled title="�ѽ�ֹ"</c:if>/>       
			</webui:column>
			<webui:column  property="loginName" title="��֯����">
			      <a href="<c:url value="/sm/org.do?act=view&orgId=${org.orgId}"/>">
			       <c:out value='${org.name}'/></a>
			</webui:column>
			<webui:column  property="code" title="��֯����"/>
			<webui:column   property="parent" title="�ϼ���֯">
			   <c:choose>
			      <c:when test="${org.parent.orgId == 0}">
			           <c:out value='${org.parent.orgName}'/>
			      </c:when>
			      <c:otherwise>
			           <a href="<c:url value="/sm/org.do?act=view&orgId=${org.parent.orgId}"/>"><c:out value='${org.parent.orgName}'/></a>
			      </c:otherwise>
			   </c:choose>
			</webui:column>
			<webui:column  property="status" title="״̬">
			  <webui:lookup code="status@SM_ORG" value="${org.status}"/>
			</webui:column>
		</webui:row>
	</webui:table>
	   <br/>
	   <security:checkPermission resourceKey="SM_DISABLE_ORG">
      <security:success>
      <webui:linkButton styleClass="clsButtonFace" href="javascript:submit_form('disable')" value="��ֹ��֯"/>
	   </security:success>
	   </security:checkPermission>
</webui:panel>
<script>
	function newOrg(){
		window.location="<c:url value='/sm/org.do?act=create'/>";
	}
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
		if(!checked) alert("��ѡ����֯");
			return checked;
	}
	function submit_form(act){
		if(check()){
			document.forms.ec.setAttribute('action', '<c:url value="/sm/org.do?act="/>'+act);
			document.forms.ec.submit();
		}
	}
</script>
