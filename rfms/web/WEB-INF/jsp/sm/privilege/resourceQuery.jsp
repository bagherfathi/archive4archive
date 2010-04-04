<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onSearch(){
        if(document.resQueryForm.orgId.value==""){
            alert("请选择组织机构");
            return;
        }
        
        if(document.resQueryForm.resourceId.value==""){
            alert("请选择功能权限");
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
<html:form action="/resourceQuery.do"  method="post">
<html:hidden property="act" value="search"/>

<webui:panel width="95%" title="sysadmin.label.resource.query" icon="../images/icon_search.gif">
<webui:formTable>
	<tr>
		<webui:input label="组织机构">
			<sm:orgsTree inputName="orgId" orgType="-1" selOrgId="${param.orgId}"/>
		</webui:input>
		
		<webui:input label="功能权限">
			<sm:selectResource inputName="resourceId" selResourceId="${param.resourceId}"/>
		</webui:input>
	</tr>
</webui:formTable>
<webui:linkButton styleClass="clsButtonFace" href="javascript:onSearch();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>

<c:if test="${!empty opList}">
<br>
<webui:panel width="95%" title="操作员列表" icon="../images/icon_list.gif">
    <webui:table
		items="opList"
		tableId="operatorTable"
		action="${pageContext.request.contextPath}/sm/resourceQuery.do?act=search&orgId=${param.orgId}&resourceId=${param.resourceId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="op"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column  filterable="false" property="contact.name" title="操作员名称"/>
			<webui:column  filterable="false" property="loginName" title="登陆名称"/>
			<webui:column  filterable="false" property="contact.telephone" title="联系电话"/>
			<webui:column  filterable="false" property="contact.mobilePhone" title="移动电话"/>
			<webui:column  filterable="false" property="email" title="E-mail"/>
		</webui:row>
    </webui:table>
</webui:panel>
</c:if>

<c:if test="${!empty groupList}">
<br>
<webui:panel width="95%" title="操作员组列表" icon="../images/icon_list.gif">
    <webui:table
		items="groupList"
		tableId="groupTable"
		action="${pageContext.request.contextPath}/sm/resourceQuery.do?act=search&orgId=${param.orgId}&resourceId=${param.resourceId}"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif" width="95%"
		var="group"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column  filterable="false" property="name" title="操作员组名称"/>
			<webui:column  filterable="false" property="description" title="操作员组描述"/>
		</webui:row>
    </webui:table>
</webui:panel>
</c:if>