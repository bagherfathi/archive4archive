<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value="/js/resource.js"/>"></script>
<script src="<c:url value="/js/date.js"/>"></script>
<html:form action="/consign.do" onsubmit="return onSubmit();">
	<input type="hidden" name="act" value="saveResourceComission" />
	<input type="hidden" name="type" value="1">
	<input type="hidden" name="consigneeId"
		value="<c:out value='${consignee.operatorId}'/>">
	<html:hidden property="searchName" />
	<html:hidden property="searchLoginName" />
	<html:hidden property="searchOrgId" />
	<webui:panel title="sysadmin.label.commission.resource.privilege">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.commission.privilege.name">
					<c:out value="${commisionForm.consignee.contact.name}" />
				</webui:input>

				<webui:input label="sysadmin.label.visit.org" required="true">
					<html:select property="orgId" onchange="changeOrg();">
						<option value="">请选择</option>
						<c:forEach items="${accessOrgs}" var="org">
							<c:if test="${org.orgId==orgId}">
								<option value="<c:out value='${org.orgId}'/>" selected="true">
								<c:out value="${org.name}" /></option>
							</c:if>
							<c:if test="${org.orgId!=orgId}">
								<option value="<c:out value='${org.orgId}'/>" /><c:out
									value="${org.name}" /></option>
							</c:if>
						</c:forEach>
					</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.commission.validTime"
					required="true">
					<input type="input" readonly="true" name="startDate" id="startDate"
						value='<fmt:formatDate value="${commisionForm.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' />&nbsp;<img
						name="popcal" align="absmiddle"
						src='<c:url value="/images/look_day.gif"/>' class="cur-hand"
						border="0" onClick="calendar(startDate);return false;">
				</webui:input>
				<webui:input label="sysadmin.label.commission.expireTime"
					required="true">
					<input type="input" readonly="true" name="endDate" id="endDate"
						value='<fmt:formatDate value="${commisionForm.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' />&nbsp;<img
						name="popcal" align="absmiddle"
						src='<c:url value="/images/look_day.gif"/>' class="cur-hand"
						border="0" onClick="calendar(endDate);return false;">
				</webui:input>
			</tr>

			<tr>
				<td class="td-left" width="15%">选择委托权限</td>
				<td colspan="3" class="td-right td_normal">
				<table width="90%" align="center" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><webui:tree root="${root}" id="data"
							type="com.ft.common.security.ResourceTreeNode"
							indent="1" extend="1" saveToCookie="false" level="level"
							closeFolderImg="../images/tree/jia.gif"
							openFolderImg="../images/tree/jian.gif"
							leafImg="../images/tree/space.gif">
							<c:if test="${level==0}">
								<img align="absmiddle" src="../images/books_open.gif" />
								<c:out value="${data.nodeName}" />
							</c:if>
							<c:if test="${level!=0}">
								<c:choose>
									<c:when test="${!data.checked}">
										<c:if test="${empty data.children}">
											<img align="absmiddle" src="../images/book.gif" />
											<c:out value="${data.nodeName}" />
										</c:if>
										<c:if test="${!empty data.children}">
											<img align="absmiddle" src="../images/books_close.gif" />
											<c:out value="${data.nodeName}" />
										</c:if>
									</c:when>
									<c:otherwise>
										<%--
                    <c:set var="flag" value="false" />
                    <c:forEach items="${consignedPrivileges}" var="cp">
                      <c:if test="${cp.resourceId == data.key}">
                        <c:set var="flag" value="true" />
                      </c:if>
                    </c:forEach>
                    <c:if test="${flag == true}">
                      <input class="noborder" type="checkbox" name="selectedIds" value="<c:out value='${data.key}'/>" id="a<c:out value='${data.path}'/>" onclick='autoCheck(this)' checked="true" />
                    </c:if>
                    <c:if test="${flag == false}">
                      <input class="noborder" type="checkbox" name="selectedIds" value="<c:out value='${data.key}'/>" id="a<c:out value='${data.path}'/>" onclick='autoCheck(this)' />
                    </c:if>
                    --%>
										<input class="noborder" type="checkbox" name="selectedIds"
											value="<c:out value='${data.key}'/>"
											id="a<c:out value='${data.path}'/>" onclick='autoCheck(this)' />

										<c:if test="${empty data.children}">
											<img align="absmiddle" src="../images/book.gif" />
											<c:out value="${data.nodeName}" />
										</c:if>
										<c:if test="${!empty data.children}">
											<img align="absmiddle" src="../images/books_close.gif" />
											<c:out value="${data.nodeName}" />
										</c:if>

										<script lanuage=javascript>
                      checkBoxIds[index++] = "a<c:out value='${data.path}'/>";
                    </script>
									</c:otherwise>
								</c:choose>
							</c:if>
						</webui:tree></td>
					</tr>
				</table>
				</td>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:submitForm(commisionForm);"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<script>
  function changeOrg() {
    document.commisionForm.act.value = "selectComissionResource";
    document.commisionForm.submit();
  }

  function onSubmit() {
  
   var resids =  document.getElementsByName('selectedIds');
   if(resids.length ==0){
          alert("没有权限可以进行委托");
            return false;
    }
    if(document.commisionForm.orgId.value == ""){
        alert("请选择可访问组织");
        return false;
    }
    
    if (!checkCompareDate2(document.commisionForm.startDate, "生效时间", document.commisionForm.endDate, "失效时间", true, -1, 
            "yyyy-MM-dd HH:mm:ss")) {
      return false;
    }
    
    if(!isChecked(document.getElementsByName('selectedIds'),"请选择委托权限")){
            return false;
    }
    
    return true;
  }
  
  function onBack(){
      document.commisionForm.act.value="searchOperator";
      document.commisionForm.submit();
  }
</script>
