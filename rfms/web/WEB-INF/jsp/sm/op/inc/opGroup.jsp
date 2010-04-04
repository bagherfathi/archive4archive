<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

 <sm:query beanName="groupManager" methodName="findGroupsByOperator" var="opGroups">
     <sm:param value="${op.operatorId}" type="java.lang.Long"/>
 </sm:query>
	<webui:table 
		items="opGroups"
		action="${pageContext.request.contextPath}/sm/op.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="group"
		width="100%"
		showPagination="false"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>	
			<webui:column property="groupName" title="sysadmin.label.group.name">
			    <c:out value='${group.name}'/>
			</webui:column>
			<webui:column property="description" title="sysadmin.label.group.desc" styleClass="td_normal" />
		</webui:row>
	</webui:table>
	 <security:checkPermission resourceKey="SM_CONFIG_OP_GROUP">
      <security:success>
      <table width="100%" border="0" cellspacing="2" cellpadding="2"><tr>
       <td align="right">
            <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:configGroup();" value="sysadmin.title.button.opGroupConfig"/>
       </td></tr></table>
      </security:success>
      </security:checkPermission> 
	
<script>
   function configGroup(){
       loadOn();
      location.href = "<c:url value="/sm/opGroup.do?act=configGroup&opId=${op.operatorId}&loginName=${operatorForm.loginName}&name=${operatorForm.name}&orgId_s=${operatorForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"
   }
</script>