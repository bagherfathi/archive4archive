<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
   <webui:panel title="任务'${taskTrigger.taskJob.jobName }'的规则'${taskTrigger.description }'运行历史记录" icon="../images/icon_list.gif">
          <webui:table items="triggerHis"
		action="${pageContext.request.contextPath}/sm/trigger.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		var="his"
		sortable="false"
		>
		<webui:row>
			<webui:column property="startTime"  title="运行开始时间">
			  <fmt:formatDate value="${ his.startTime }" type="both"/>
			</webui:column>
			<webui:column property="endTime" title="运行结束时间">
			  <fmt:formatDate value="${ his.endTime }" type="both"/>
			</webui:column>
		</webui:row>
	</webui:table>
      <webui:linkButton styleClass="clsButtonFace" value="返回" href="#" onClick="back()"/>
	</webui:panel>
 
<script>
	 function back(){
	    window.location.href="<c:url value="/sm/trigger.do?act=trigger&jobId=${taskTrigger.jobId}"/>";
	 }
 
</script>

