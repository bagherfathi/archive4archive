<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.memberForm.act.value="create";
        document.memberForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.memberForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/member" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.operatorId"
		value="<c:out value='${memberForm.currentUser.operatorId }'/>" />
	<webui:panel title="��Ա����" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="��Ա����">
					<html:text property="searchObj.name" size="25" />
				</webui:input>
				<webui:input label="�ֻ�����">
					<html:text property="searchObj.mobile" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onCreate();" value="sysadmin.button.create" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.memberForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>

	<br />

	<webui:panel title="��Ա������" icon="../images/icon_list.gif">
		<webui:table dataSource="memberDS"
			action="${pageContext.request.contextPath}/rfms/member.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="��Ա����б�" var="member" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="memberForm" form="memberForm">
			<webui:row>
				<webui:column property="name" title="��Ա����" styleClass="td_normal"></webui:column>
				<webui:column property="mobile" title="��Ա�ֻ�" styleClass="td_normal"></webui:column>
				<webui:column property="address" title="��ַ" styleClass="td_normal"></webui:column>
				<webui:column property="sex" title="�Ա�">
					<webui:lookup code="SEX@RFMS_CARD" value="${member.sex}" />
				</webui:column>	
				<webui:column property="status" title="��Ա���">
					<webui:lookup code="CODE_TYPE@RFMS_CARD" value="${member.status}" />
				</webui:column>	
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/member.do?act=edit&id=${member.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
				</webui:column>

				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/member.do?act=view&id=${member.id}'/>">�鿴</a>&nbsp;
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>