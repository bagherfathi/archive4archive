<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();document.ruleForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>
<sm:query beanName="ruleManager" methodName="findAllRuleCategory" var="categories"/>
<html:form action="/rule" method="post">
<html:hidden property="act" value="search"/>

	<webui:panel title="sysadmin.title.rule.search" width="95%" icon="../images/icon_search.gif">
		<webui:formTable>
			<tr>
		 		<webui:input label="sysadmin.lable.rule.category">				
					<html:select property="categoryId">
						<html:option value="" key="sysadmin.label.select"/>
						<c:forEach items="${categories}" var="category">
						<option value="<c:out value='${category.categoryId}'/>" <c:if test="${ruleForm.categoryId==category.categoryId}">selected</c:if>><c:out value="${category.name}"/></option>
						</c:forEach>
			   		</html:select>
				</webui:input>
		 		<webui:input label="sysadmin.label.rule.name">
		 			<html:text property="ruleName"/>
				</webui:input>
		 	</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();document.ruleForm.submit();" value="sysadmin.button.search"/>	
	</webui:panel>
</html:form>
</br>
<webui:panel title="sysadmin.title.rule.list" icon="../images/icon_list.gif" width="95%">
	<webui:table
		dataSource="ruleDS"
		action="${pageContext.request.contextPath}/sm/rule.do?act=search"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.rule.list"
		var="rule"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column filterable="false" property="name" title="规则名称" width="25%">
			      <a href="<c:url value='rule.do?act=toView&id=${rule.ruleId}'/>"><c:out value='${rule.name}'/></a>
			</webui:column>
			<webui:column filterable="false" property="code" title="规则编码" width="25%">
			</webui:column>
			<webui:column filterable="false" property="type" title="规则类型" width="10%">
			    <webui:lookup code="type@SM_RULE_INFO" value="${rule.type}"/>
			</webui:column>
			
			<webui:column filterable="false" property="发布版本" title="发布版本" width="10%">
				<c:if test="${rule.publishVersion == 0}">
					<bean:message key='sysadmin.label.rule.no.publish'/>
				</c:if>
				<c:if test="${rule.publishVersion != 0}">
			      	Version.<c:out value='${rule.publishVersion}'/>
			   	</c:if>
			</webui:column>
			<webui:column filterable="false" property="操作" title="操作" width="15%">
			    <a href="<c:url value='/sm/rule.do?act=toView&ruleId=${rule.ruleId}'/>">查看</a>
			    <security:checkPermission resourceKey="SM_EDIT_RULE">
			      <security:success>
				    &nbsp;<a href="<c:url value='/sm/rule.do?act=toEdit&ruleId=${rule.ruleId}'/>">修改</a>
			      </security:success>
		        </security:checkPermission>
			</webui:column>
			<webui:column filterable="false" property="业务操作" title="业务操作" width="15%">
			    <a href="<c:url value='/sm/ruleFile.do?act=list&ruleId=${rule.ruleId}'/>">规则文件</a>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_RULE">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:toAdd();" value="sysadmin.button.create"/>
		</security:success>
	</security:checkPermission>
</webui:panel>
<script>
	function toAdd(){
		window.location = "<c:url value='/sm/rule.do?act=toCreate'/>";
	}
	
	function toDelete(ruleId){
		if(window.confirm("<bean:message key='msg.confirm.delete.rule'/>")){
		    document.ruleForm.act.value="delete";
		    document.ruleForm.submit();
		}
	}
</script>