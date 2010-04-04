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
<html:form action="/scheduler.do" method="post">
	<webui:panel title="计划查询" icon="../images/icon_search.gif">
		<input type="hidden" name="act" value="search" />
		<table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
			<tr>
				<webui:input label="任务名称">
					<html:text property="jobName" size="25" />
				</webui:input>
				<webui:input label="运行频率">
					<html:select property="cronType">
						<html:option value="0">
							<bean:message key="cron.startup.frequency" />
						</html:option>
						<html:option value="1">
							<bean:message key="cron.every.day" />
						</html:option>
						<html:option value="2">
							<bean:message key="cron.erveryweek" />
						</html:option>
						<html:option value="3">
							<bean:message key="cron.every.month" />
						</html:option>
						<html:option value="4">
							<bean:message key="cron.run.oneTime" />
						</html:option>
						<html:option value="5">
							<bean:message key="cron.fix.split.time" />
						</html:option>
						<html:option value="6">每隔几小时</html:option>
					</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="规则状态" colspan="4">
					<html:select property="triggerStatus">
						<html:option value="0">请选择</html:option>
						<html:option value="2">启动</html:option>
						<html:option value="1">暂停</html:option>
					</html:select>
				</webui:input>
			</tr>
		</table>
		<webui:linkButton href="#" styleClass="clsButtonFace" value="查询"
			onClick="loadOn();taskForm.submit();" />
	</webui:panel>
</html:form>
<br />
<webui:panel title="计划监控" icon="../images/icon_list.gif">
	<webui:table items="allTriggers" tableId="allTriggers"
		action="${pageContext.request.contextPath}/sm/scheduler.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%" showPagination="true" showStatusBar="true"
		showTitle="false" filterable="false" var="trigger" sortable="false">
		<webui:row>

			<webui:column property="jobDefine.name" title="任务名称" width="15%">
				<c:out value="${trigger.taskJob.jobName}" />
			</webui:column>
			<webui:column property="description" title="运行规则" width="25%">
				<c:out value="${trigger.description}" />
			</webui:column>
			<webui:column property="runCount" title="运行次数">
				<c:choose>
					<c:when test="${empty trigger.runCount}">未运行</c:when>
					<c:otherwise>
						<c:out value="${trigger.runCount}" />
					</c:otherwise>
				</c:choose>
			</webui:column>
			<webui:column property="runCount" title="最后运行时间">
				<c:choose>
					<c:when test="${empty trigger.lastRunTime}">未运行</c:when>
					<c:otherwise>
						<fmt:formatDate type="both" value="${trigger.lastRunTime}" />
					</c:otherwise>
				</c:choose>
			</webui:column>
			<webui:column property="status" title="状态" width="10%">
				<c:choose>
					<c:when test="${trigger.status==2}">
						<font color="red">启动</font>
					</c:when>
					<c:otherwise>暂停</c:otherwise>
				</c:choose>
			</webui:column>
			<webui:column property="operate" title="操作" width="10%"
				style="text-align: center;width: 26px">
				<c:choose>
					<c:when test="${trigger.status != 2}">
						<a
							href="<c:url value="/sm/scheduler.do?act=run&triggerId=${trigger.triggerId}"/>">启动</a>
					</c:when>
					<c:otherwise>
						<a
							href="<c:url value="/sm/scheduler.do?act=pause&triggerId=${trigger.triggerId}"/>">暂停</a>
					</c:otherwise>
				</c:choose>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
<script>
function refresh(){
  window.location.reload();
}
setTimeout("refresh()", 1000*60*5);
</script>

