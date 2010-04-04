<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script src="<c:url value="/js/date.js"/>"></script>
<html:form action="/consign.do" method="post">
<input type="hidden" name="act" value="updateCommission"/>
<html:hidden property="consignPrivilege.consignId"/>
<input type="hidden" name="consigneeId" value="<c:out value='${consignee.operatorId}'/>">

<webui:panel title="编辑委托记录">
	<webui:formTable>
		<tr>
		<webui:input label="sysadmin.label.privilege.name">
		    <c:out value="${consignPrivilege.resourceName}"/>
		</webui:input>
		<webui:input label="sysadmin.label.commission.privilege.name">
		    <c:out value="${consignPrivilege.consigneeName}"/>
		</webui:input>
	  	</tr>
	  	<tr>
		<webui:input label="sysadmin.label.commission.validTime" required="true">
			<sm:consignDate date="${consignPrivilege.validTime}" var="flag1"/>
			<c:if test="${!flag1}">
			<input type="input" readonly="true" name="consignPrivilege.validTime" id="startDate" value='<fmt:formatDate value="${consignPrivilege.validTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />&nbsp;<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(startDate);return false;">
			</c:if>
			<c:if test="${flag1}">
			<input type="input" name="consignPrivilege.validTime" id="startDate" value='<fmt:formatDate value="${consignPrivilege.validTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="true"/>
			</c:if>
        </webui:input>
		<webui:input label="sysadmin.label.commission.expireTime" required="true">
			<sm:consignDate date="${consignPrivilege.expireTime}" var="flag2"/>
			<c:if test="${!flag2}">
			<input type="input" readonly="true" name="consignPrivilege.expireTime" id="endDate" value='<fmt:formatDate value="${consignPrivilege.expireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />&nbsp;<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(endDate);return false;">
			</c:if>
			<c:if test="${flag2}">
			<input type="input" name="consignPrivilege.expireTime" id="endDate" value='<fmt:formatDate value="${consignPrivilege.expireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="true"/>
			</c:if>
        </webui:input>
        </tr>
    </webui:formTable>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmit();" value="sysadmin.button.save"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
function onSubmit() {
    if (!checkCompareDate2(document.commisionForm.startDate, "生效时间", document.commisionForm.endDate, "失效时间", true, -1, 
            "yyyy-MM-dd HH:mm:ss")) {
      return;
    } else {
      document.commisionForm.act.value="updateCommission";
      document.commisionForm.submit();
    }
  }
  
   function onBack(){
        document.commisionForm.act.value="searchCommissions";
        document.commisionForm.submit();
    }
</script>