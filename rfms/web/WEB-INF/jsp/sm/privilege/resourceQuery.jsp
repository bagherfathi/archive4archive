<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onSearch(){
        if(document.resQueryForm.orgId.value==""){
            alert("��ѡ����֯����");
            return;
        }
        
        if(document.resQueryForm.resourceId.value==""){
            alert("��ѡ����Ȩ��");
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
		<webui:input label="��֯����">
			<sm:orgsTree inputName="orgId" orgType="-1" selOrgId="${param.orgId}"/>
		</webui:input>
		
		<webui:input label="����Ȩ��">
			<sm:selectResource inputName="resourceId" selResourceId="${param.resourceId}"/>
		</webui:input>
	</tr>
</webui:formTable>
<webui:linkButton styleClass="clsButtonFace" href="javascript:onSearch();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>

<c:if test="${!empty opList}">
<br>
<webui:panel width="95%" title="����Ա�б�" icon="../images/icon_list.gif">
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
			<webui:column  filterable="false" property="contact.name" title="����Ա����"/>
			<webui:column  filterable="false" property="loginName" title="��½����"/>
			<webui:column  filterable="false" property="contact.telephone" title="��ϵ�绰"/>
			<webui:column  filterable="false" property="contact.mobilePhone" title="�ƶ��绰"/>
			<webui:column  filterable="false" property="email" title="E-mail"/>
		</webui:row>
    </webui:table>
</webui:panel>
</c:if>

<c:if test="${!empty groupList}">
<br>
<webui:panel width="95%" title="����Ա���б�" icon="../images/icon_list.gif">
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
			<webui:column  filterable="false" property="name" title="����Ա������"/>
			<webui:column  filterable="false" property="description" title="����Ա������"/>
		</webui:row>
    </webui:table>
</webui:panel>
</c:if>