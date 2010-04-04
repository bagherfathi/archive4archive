<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();document.taskForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>

<!--查找所有任务分类-->
<sm:query var="categorys" beanName="taskManager" methodName="findAllCategorys"/>
<html:form action="task.do">
	<webui:panel title="查询" icon="../images/icon_search.gif">
		<webui:formTable>
			<webui:input label="任务名称">
			<html:text property="jobName" size="25"/>
			</webui:input>
			<webui:input label="任务分类">
				<html:select property="categoryId">
					<option value="">请选择</option>
					<html:options collection="categorys" labelProperty="name" property="categoryId"/>
				</html:select>
			</webui:input>
		</webui:formTable>

		<webui:linkButton href="#" styleClass="clsButtonFace" value="查询"
			onClick="loadOn();taskForm.submit();" />
	</webui:panel>
</html:form>
<br />
<webui:panel title="任务列表" icon="../images/icon_list.gif">
	<webui:table dataSource="jobDS"
		action="${pageContext.request.contextPath}/sm/task.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="job" width="95%" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" sortable="false">
		<webui:row>
			<webui:column property="name" title="任务名称">
				<c:out value='${job.name}' />
			</webui:column>
			<webui:column property="description" title="任务描述"
				styleClass="td_normal" />
			<webui:column property="action" title="操作" width="15%">
				<a href="<c:url value="/sm/task.do?act=edit&jobId=${job.jobId}"/>">编辑</a>
				<sm:taskJob var="flag" id="${job.jobId}"></sm:taskJob>
				<c:if test="${!flag}">
					<a href="#" onclick="delete_job(<c:out value='${job.jobId}'/>)">删除</a>
				</c:if>
			</webui:column>
			<webui:column property="action" title="业务操作" width="15%">
				<a
					href="<c:url value="/sm/trigger.do?act=trigger&jobId=${job.jobId}"/>">规则管理</a>
				<a
					href="<c:url value="/sm/trigger.do?act=newTrigger&jobId=${job.jobId}"/>">新增规则</a>
			</webui:column>

		</webui:row>
	</webui:table>
	<webui:linkButton href="#" styleClass="clsButtonFace" value="新增"
		onClick="submit_form('create')" />
</webui:panel>
<script>
     function submit_form(act){
         window.location.href="<c:url value='/sm/task.do?act='/>"+act;
	 }
	 
	 function delete_job(jobId){
		if(confirm('确定要删除此任务吗？')){
	      window.location.href="<c:url value='/sm/task.do?act=delete&jobId='/>"+jobId;
	    }
	 }
 
</script>

