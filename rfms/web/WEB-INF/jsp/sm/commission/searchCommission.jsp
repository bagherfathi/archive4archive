<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<c:set var="showbutton" value="0"/>
<html:form action="/consign.do">
<input type="hidden" name="act"/>
<html:hidden property="searchName"/>
<html:hidden property="searchLoginName"/>
<html:hidden property="searchOrgId"/>
</html:form>

<webui:panel title="ί��Ȩ���б�" icon="../images/icon_list.gif">
	<webui:table 
		items="commissionList"
		action="${pageContext.request.contextPath}/sm/consign.do?act=searchCommissions"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.bank.list"
		var="commission"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		autoIncludeParameters="true"
		>
		<webui:row>
		    <webui:column style="text-align:center;" sortable="false" property="checkbox" title="<input class='noborder' type='checkbox' onClick='javascript:convertCheck(this, document.all.selectedIds)'/>ȫѡ" filterable="false" width="8%">
				<input class="noborder" type="checkbox" name="selectedIds" value="<c:out value='${commission.consignId}'/>"/>       
				<c:set var="showbutton" value="1"/> 
			</webui:column>
			<webui:column sortable="false" filterable="false" property="resourceName" title="Ȩ������" styleClass="td_normal">
			    <sm:consignDate date="${commission.validTime}" var="flag1"/>
			    <sm:consignDate date="${commission.expireTime}" var="flag2"/>
			    <c:choose>
			    <c:when test="${flag1==false or flag2==false}">
			    <sm:resourcePath resourcePath="${commission.resourcePath}"/>
			    </c:when>
			    <c:otherwise>
			        <font color="gray"><sm:resourcePath resourcePath="${commission.resourcePath}"/></font>
			    </c:otherwise>
			    </c:choose>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="consigneeName" title="ί�ж�������"/>
			<webui:column sortable="false" filterable="false" property="orgName" title="��֯����"/>
			<webui:column sortable="false" filterable="false" property="consignTime" title="ί��ʱ��">
			    <fmt:formatDate value="${commission.consignTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="validTime" title="��Чʱ��">
			    <fmt:formatDate value="${commission.validTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="expireTime" title="ʧЧʱ��">
			    <fmt:formatDate value="${commission.expireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</webui:column>
			<webui:column sortable="false" property="����" title="����">
			    <a href="<c:url value='/sm/consign.do?act=editCommission&cpId=${commission.consignId}&consigneeId=${consignee.operatorId}'/>">�޸�</a>
			</webui:column>
		</webui:row>
	</webui:table>
	<c:if test="${showbutton==1}">
	    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:reclaim();" value="����"/>
	    <webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:recoverAll();" value="ȫ������"/>
	</c:if>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="����"/>
</webui:panel>

<script>
	function reclaim(){
	     if (!isChecked(document.forms.ec.selectedIds, "��ѡ��Ҫ���յ�ί��Ȩ��")) {
            return;
        } else {
            if (confirm("<bean:message key='msg.confirm.reclaim' />")) {
               document.forms.ec.setAttribute('action', '<c:url value="/sm/consign.do?act=reclaim"/>');
	           document.forms.ec.submit();
            }
        }
	}
	
	function recoverAll(){
	    if (confirm("ȷ��Ҫ��������ί�е�Ȩ��?")) {
               location.href = "<c:url value="/sm/consign.do?act=recoverAll&consigneeId=${param.consigneeId}"/>";
            }
	}
	
	function onBack(){
        document.commisionForm.act.value="searchOperator";
        document.commisionForm.submit();
    }
</script>
