<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="org" method="post">
<webui:panel title="搜索" icon="../images/icon_search.gif" width="95%">	
    <webui:formTable>
    	<tr>
    		<webui:input label="组织名">
            	<input type="text" name="name"/>
			</webui:input>	
			<webui:input label="组织代码">
				<input type="text" name="code"/>  
			</webui:input>
		</tr>
		<tr>
		<webui:input label="状态">
			<select name="status">
			<option value="">所有</option>
				<bean:define id="enum" name="enumSet" property="element(SM_ORG_STATUS_ENUM)"/>
			<c:forEach var="status" items="${enum}">
				<option value="<c:out value='${status.value}'/>"><c:out value='${status.label}'/></option>			                         
			</c:forEach>
			</select>
		</webui:input>	
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();document.forms.orgForm.submit();" value="搜索"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="组织操作" icon="../images/icon_list.gif">
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
		    <webui:column property="checkbox" title="选择" width="6%">
		         <sm:org id="${org.orgId}" var="flag"/>
				<input class="noborder" type="checkbox" name="orgIds" value="<c:out value='${org.orgId}'/>" 
				  <c:if test="${flag && org.status==0}">disabled title=不能禁止</c:if>
				  <c:if test="${org.status==1}">disabled title="已禁止"</c:if>/>       
			</webui:column>
			<webui:column  property="loginName" title="组织名称">
			      <a href="<c:url value="/sm/org.do?act=view&orgId=${org.orgId}"/>">
			       <c:out value='${org.name}'/></a>
			</webui:column>
			<webui:column  property="code" title="组织代码"/>
			<webui:column   property="parent" title="上级组织">
			   <c:choose>
			      <c:when test="${org.parent.orgId == 0}">
			           <c:out value='${org.parent.orgName}'/>
			      </c:when>
			      <c:otherwise>
			           <a href="<c:url value="/sm/org.do?act=view&orgId=${org.parent.orgId}"/>"><c:out value='${org.parent.orgName}'/></a>
			      </c:otherwise>
			   </c:choose>
			</webui:column>
			<webui:column  property="status" title="状态">
			  <webui:lookup code="status@SM_ORG" value="${org.status}"/>
			</webui:column>
		</webui:row>
	</webui:table>
	   <br/>
	   <security:checkPermission resourceKey="SM_DISABLE_ORG">
      <security:success>
      <webui:linkButton styleClass="clsButtonFace" href="javascript:submit_form('disable')" value="禁止组织"/>
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
		if(!checked) alert("请选择组织");
			return checked;
	}
	function submit_form(act){
		if(check()){
			document.forms.ec.setAttribute('action', '<c:url value="/sm/org.do?act="/>'+act);
			document.forms.ec.submit();
		}
	}
</script>
