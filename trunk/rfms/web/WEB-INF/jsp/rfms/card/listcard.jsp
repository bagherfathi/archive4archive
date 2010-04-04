<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.cardForm.act.value="create";
        document.cardForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.cardForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/card" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.operatorId" value="<c:out value='${cardForm.currentUser.operatorId }'/>"/>
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="编号">
					<html:text property="searchObj.cardSerial" size="25" />
				</webui:input>
				<webui:input label="名称">
					<html:text property="searchObj.cardName" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.card.status" colspan="3">
					<html:select property="baseEntity.status">
						<html:option value="-1">请选择</html:option>
						<html:optionsCollection name="enumSet"
							property="element(STATUS@RFMS_CARD)" />
					</html:select>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onCreate();" value="sysadmin.button.create" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.cardForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>

	<br />

	<webui:panel title="现金券管理" icon="../images/icon_list.gif">
		<webui:table dataSource="cardDS"
			action="${pageContext.request.contextPath}/rfms/card.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="现金券列表" var="card" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="cardForm" form="cardForm">
			<webui:row>
				<webui:column property="cardSerial" title="编号"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="cardName" title="名称"
					styleClass="td_normal" />
				<webui:column property="sfXf" title="是否主动下发">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${card.sfXf}" />
				</webui:column>
				<webui:column property="sfHy" title="限会员使用">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${card.sfHy}" />
				</webui:column>
				<webui:column property="sfHf" title="是否需要回复">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${card.sfHf}" />
				</webui:column>
				<webui:column property="sfZf" title="是否转发">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${card.sfZf}" />
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="fyDate" title="预定下发日期">
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="yxDate" title="飞券有效期">
				</webui:column>
				<webui:column property="status" title="label.rfms.card.status">
					<webui:lookup code="status@RFMS_CARD" value="${card.status}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/card.do?act=edit&id=${card.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
			</webui:column>

				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/card.do?act=view&id=${card.id}'/>">查看</a>&nbsp;
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>