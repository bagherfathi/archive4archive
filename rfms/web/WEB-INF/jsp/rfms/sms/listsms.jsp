<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.smsForm.act.value="create";
        document.smsForm.submit();
    }
    
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.smsForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/sms" method="post">
	<input type="hidden" value="search" name="act" />
	<input type="hidden" name="searchObj.operatorId" value="<c:out value='${smsForm.currentUser.operatorId }'/>"/>
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="���">
					<html:text property="searchObj.smsSerial" size="25" />
				</webui:input>
				<webui:input label="����">
					<html:text property="searchObj.smsName" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="label.rfms.sms.status" colspan="3">
					<html:select property="baseEntity.status">
						<html:option value="-1">��ѡ��</html:option>
						<html:optionsCollection name="enumSet"
							property="element(STATUS@RFMS_CARD)" />
					</html:select>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onCreate();" value="sysadmin.button.create" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.smsForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>

	<br />

	<webui:panel title="�ֽ�ȯ����" icon="../images/icon_list.gif">
		<webui:table dataSource="smsDS"
			action="${pageContext.request.contextPath}/rfms/sms.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="�ֽ�ȯ�б�" var="sms" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="smsForm" form="smsForm">
			<webui:row>
				<webui:column property="smsSerial" title="���"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="smsName" title="����"
					styleClass="td_normal" />
				<webui:column property="sfXf" title="�Ƿ������·�">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${sms.sfXf}" />
				</webui:column>
				<webui:column property="sfHy" title="�޻�Աʹ��">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${sms.sfHy}" />
				</webui:column>
				<webui:column property="sfHf" title="�Ƿ���Ҫ�ظ�">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${sms.sfHf}" />
				</webui:column>
				<webui:column property="sfZf" title="�Ƿ�ת��">
					<webui:lookup code="commision_step@RFMS_MERCHANT"
						value="${sms.sfZf}" />
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="fyDate" title="Ԥ���·�����">
				</webui:column>
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="yxDate" title="��ȯ��Ч��">
				</webui:column>
				<webui:column property="status" title="label.rfms.sms.status">
					<webui:lookup code="status@RFMS_CARD" value="${sms.status}" />
				</webui:column>
				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/sms.do?act=edit&id=${sms.id}'/>"><bean:message
						key="sysadmin.button.edit" /></a>&nbsp;
			</webui:column>

				<webui:column property="op" title="title.rfms.common.operater"
					width="3%">
					<a href="<c:url value='/rfms/sms.do?act=view&id=${sms.id}'/>">�鿴</a>&nbsp;
			</webui:column>

			</webui:row>
		</webui:table>

	</webui:panel>
</html:form>