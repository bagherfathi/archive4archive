<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
   <webui:panel title="任务'${jobDefine.name}'的运行规则" icon="../images/icon_list.gif">
          <webui:table items="triggers" tableId="triggers" 
		action="${pageContext.request.contextPath}/sm/trigger.do?act=trigger"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		var="trigger"
		sortable="false"
		>
		<webui:row>
			<webui:column property="description" title="运行规则">
			     <c:out value="${trigger.description}"/>
			</webui:column>
			<webui:column property="runCount" title="运行次数">
			  <c:choose>
			     <c:when test="${empty trigger.runCount}">未运行</c:when>
			     <c:otherwise><c:out value="${trigger.runCount}"/></c:otherwise>
			  </c:choose>
			</webui:column>
			<webui:column property="runCount" title="最后运行时间">
			    <c:choose>
			     <c:when test="${empty trigger.lastRunTime}">未运行</c:when>
			     <c:otherwise><fmt:formatDate type="both" value="${trigger.lastRunTime}" /></c:otherwise>
			  </c:choose>
			</webui:column>
			<webui:column property="status" title="状态"><font color="red">
			     <c:choose>
			        <c:when test="${trigger.needStartup==0}">禁用</c:when>
					<c:when test="${trigger.needStartup==1}">启用</c:when>
					<c:when test="${trigger.needStartup==2}">过期</c:when>
				</c:choose></font>	
			</webui:column>
            <webui:column property="action" title="操作"  width="10%">
                <c:if test="${ trigger.needStartup!=2}">
                <a href="<c:url value='/sm/trigger.do?act=editTrigger&triggerId=${trigger.triggerId}'/>">编辑</a>
                </c:if>
                <a href="#" onclick="delete_trigger(<c:out value="${trigger.triggerId}"/>)">删除</a>
            </webui:column>
             <webui:column property="action" title="业务操作"  width="10%">
                <c:choose>
                    <c:when test="${trigger.needStartup==1}"><a href="<c:url value="/sm/scheduler.do?act=stop&triggerId=${trigger.triggerId }"/>">禁用</a></c:when>
				    <c:when test="${trigger.needStartup==0}"><a href="<c:url value="/sm/scheduler.do?act=start&triggerId=${trigger.triggerId }"/>">启用</a></c:when>
				</c:choose>
				<a href="<c:url value="/sm/trigger.do?act=history&triggerId=${trigger.triggerId }"/>">规则历史</a>
            </webui:column>
		</webui:row>
	</webui:table>
      <webui:linkButton styleClass="clsButtonFace" value="新增" href="#" onClick="submit_form('newTrigger')"/> 
      <webui:linkButton styleClass="clsButtonFace" value="返回" href="#" onClick="back()"/>
	</webui:panel>
<script>
     function submit_form(act){
	        window.location.href= '<c:url value="/sm/trigger.do?jobId=${taskForm.jobDefine.jobId}&act="/>' +act;
	 }
	 function delete_trigger(id){
	    var choice=confirm('确定要删除此运行规则吗？');
		if(true==choice){
	      window.location.href='<c:url value="/sm/trigger.do?act=delTrigger&triggerId="/>'+id;
	    }
	 }
	 function back(){
	    window.location.href="<c:url value="/sm/task.do"/>";
	 }
</script>

