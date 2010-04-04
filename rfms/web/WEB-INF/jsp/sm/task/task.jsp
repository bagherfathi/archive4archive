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

<!--���������������-->
<sm:query var="categorys" beanName="taskManager" methodName="findAllCategorys"/>
<html:form action="task.do">
	<webui:panel title="��ѯ" icon="../images/icon_search.gif">
		<webui:formTable>
			<webui:input label="��������">
			<html:text property="jobName" size="25"/>
			</webui:input>
			<webui:input label="�������">
				<html:select property="categoryId">
					<option value="">��ѡ��</option>
					<html:options collection="categorys" labelProperty="name" property="categoryId"/>
				</html:select>
			</webui:input>
		</webui:formTable>

		<webui:linkButton href="#" styleClass="clsButtonFace" value="��ѯ"
			onClick="loadOn();taskForm.submit();" />
	</webui:panel>
</html:form>
<br />
<webui:panel title="�����б�" icon="../images/icon_list.gif">
	<webui:table dataSource="jobDS"
		action="${pageContext.request.contextPath}/sm/task.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="job" width="95%" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" sortable="false">
		<webui:row>
			<webui:column property="name" title="��������">
				<c:out value='${job.name}' />
			</webui:column>
			<webui:column property="description" title="��������"
				styleClass="td_normal" />
			<webui:column property="action" title="����" width="15%">
				<a href="<c:url value="/sm/task.do?act=edit&jobId=${job.jobId}"/>">�༭</a>
				<sm:taskJob var="flag" id="${job.jobId}"></sm:taskJob>
				<c:if test="${!flag}">
					<a href="#" onclick="delete_job(<c:out value='${job.jobId}'/>)">ɾ��</a>
				</c:if>
			</webui:column>
			<webui:column property="action" title="ҵ�����" width="15%">
				<a
					href="<c:url value="/sm/trigger.do?act=trigger&jobId=${job.jobId}"/>">�������</a>
				<a
					href="<c:url value="/sm/trigger.do?act=newTrigger&jobId=${job.jobId}"/>">��������</a>
			</webui:column>

		</webui:row>
	</webui:table>
	<webui:linkButton href="#" styleClass="clsButtonFace" value="����"
		onClick="submit_form('create')" />
</webui:panel>
<script>
     function submit_form(act){
         window.location.href="<c:url value='/sm/task.do?act='/>"+act;
	 }
	 
	 function delete_job(jobId){
		if(confirm('ȷ��Ҫɾ����������')){
	      window.location.href="<c:url value='/sm/task.do?act=delete&jobId='/>"+jobId;
	    }
	 }
 
</script>

