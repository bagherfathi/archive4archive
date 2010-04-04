<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();maintainPlanForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>
<html:form action="/maintainPlan" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="ά���ƻ���ѯ" icon="../images/icon_search.gif"
		width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.maintain.plan.name">
					<html:text property="planName" size="25" />
				</webui:input>
				<webui:input label="sysadmin.label.maintain.plan.cycle">
					<html:select property="cycleUnit">
						<html:option value="">��ѡ��</html:option>
						<html:optionsCollection name="enumSet"
							property="element(cycle_unit@SM_MAINTAIN_PLAN)" />
					</html:select>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();maintainPlanForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>
</html:form>
<br>
<webui:panel title="sysadmin.title.maintain.plan.list" width="95%"
	icon="../images/icon_list.gif">
	<webui:table dataSource="maintainPlanDS"
		action="${pageContext.request.contextPath}/sm/maintainPlan.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.category.list" var="plan" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"
		filterable="false" sortable="false">
		<webui:row>
			<webui:column filterable="false" property="name" title="�ƻ�����"
				width="70%">
			</webui:column>
			<webui:column filterable="false" property="cycle" title="����"
				width="10%" styleClass="td_normal">
				<c:out value='${plan.cycle}' />
			</webui:column>
			<webui:column filterable="false" property="cycleUnit" title="���ڵ�λ"
				width="10%" styleClass="td_normal">
				<webui:lookup code="cycle_unit@SM_MAINTAIN_PLAN"
					value="${plan.cycleUnit}" />
			</webui:column>
			<webui:column property="����" title="����" width="10%">
				<a
					href="<c:url value='/sm/maintainPlan.do?act=toEdit&id=${plan.planId}'/>">�޸�</a>
				<security:checkPermission resourceKey="SM_DELETE_MAINTAIN_PLAN">
					<security:success>
						<sm:maintainPlan id="${plan.planId}" var="flag" />
						<c:if test="${flag == false}">
							<a href="javascript:toDelete(<c:out value='${plan.planId}'/>);" >ɾ��</a>
						</c:if>
					</security:success>
				</security:checkPermission>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_MAINTAIN_PLAN">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace"
				href="javascript:goCreate();" value="sysadmin.button.create" />
		</security:success>
	</security:checkPermission>
</webui:panel>
<script>
	function goCreate(){
		location.href = "<c:url value='/sm/maintainPlan.do?act=create'/>";
	}
	function toDelete(id){
		if(window.confirm("<bean:message key='msg.confirm.delete.maintain.plan'/>")){
			location.href = "<c:url value='/sm/maintainPlan.do?act=delete&id='/>" + id;
		}
	}
</script>
