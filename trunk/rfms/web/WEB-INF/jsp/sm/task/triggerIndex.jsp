<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="任务列表" icon="../images/icon_list.gif">
	<webui:table 
		dataSource="jobDS"
		action="${pageContext.request.contextPath}/sm/task.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="job"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		>
		<ec:row>
			<ec:column filterable="false" property="name" title="任务名称">
			   <c:out value='${job.name}'/>
			</ec:column>
			<ec:column property="description" title="任务描述"  filterable="false"/>
			 <ec:column filterable="false" property="taskTrigger" title="操作" sortable="false"  style="text-align: center;width: 52px">
                <a href="<c:url value="/sm/trigger.do?act=trigger&jobId=${job.jobId}"/>">计划管理</a>
            </ec:column>
		</ec:row>
	</webui:table>
</webui:panel>


