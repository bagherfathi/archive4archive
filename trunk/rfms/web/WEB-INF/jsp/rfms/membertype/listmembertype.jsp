<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.membertypeForm.act.value="create";
        document.membertypeForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.membertypeForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/membertype" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.operatorId" value="<c:out value='${membertypeForm.currentUser.operatorId }'/>"/>
	<webui:panel title="��Ա����ѯ" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="��Ա���" colspan="3">
					<html:text property="searchObj.name" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onCreate();" value="sysadmin.button.create" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.membertypeForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>

	<br />

	<webui:panel title="��Ա������" icon="../images/icon_list.gif">
		<webui:table dataSource="memberTypeDS"
			action="${pageContext.request.contextPath}/rfms/membertype.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="��Ա����б�" var="membertype" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="membertypeForm" form="membertypeForm">
			<webui:row>
				<webui:column property="name" title="��Ա���"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="memo" title="����"
					styleClass="td_normal" />
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/membertype.do?act=edit&id=${membertype.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
				</webui:column>

				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/membertype.do?act=view&id=${membertype.id}'/>">�鿴</a>&nbsp;
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>