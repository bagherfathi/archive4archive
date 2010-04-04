<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
   <webui:panel title="任务'${jobDefine.name }'的运行历史记录" icon="../images/icon_list.gif">
          <webui:table items="taskHis"
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
			<webui:column property="trigger" title="规则描述">
			<c:out value="${his[2] }"/>
			</webui:column>
			<webui:column property="count" title="运行次数">
			<c:out value="${his[0] }"/>
			</webui:column>
			<webui:column property="lastTime" title="最后运行时间">
			
			<fmt:formatDate type="both" value="${his[1] }"/>
			</webui:column>
		</webui:row>
	</webui:table>
      <webui:linkButton styleClass="clsButtonFace" value="返回" href="#" onClick="back()"/>
	</webui:panel>
 
<script>
	 function back(){
	    window.location.href="<c:url value="/sm/task.do"/>";
	 }
</script>

