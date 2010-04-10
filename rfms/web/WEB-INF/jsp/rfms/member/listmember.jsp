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
	<webui:panel title="会员管理" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="会员姓名">
					<html:text property="searchObj.name" size="25" />
				</webui:input>
				<webui:input label="手机号码">
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

	<webui:panel title="会员类别管理" icon="../images/icon_list.gif">
		<webui:table dataSource="memberDS"
			action="${pageContext.request.contextPath}/rfms/member.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="会员类别列表" var="member" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="memberForm" form="memberForm">
			<webui:row>
				<webui:column property="name" title="会员名称" styleClass="td_normal"></webui:column>
				<webui:column property="mobile" title="会员手机" styleClass="td_normal"></webui:column>
				<webui:column property="address" title="地址" styleClass="td_normal"></webui:column>
				<webui:column property="sex" title="性别">
					<webui:lookup code="SEX@RFMS_CARD" value="${member.sex}" />
				</webui:column>	
				<webui:column property="status" title="会员类别">
					<webui:lookup code="CODE_TYPE@RFMS_CARD" value="${member.status}" />
				</webui:column>	
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/member.do?act=edit&id=${member.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
				</webui:column>

				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/member.do?act=view&id=${member.id}'/>">查看</a>&nbsp;
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>