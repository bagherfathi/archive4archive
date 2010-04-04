<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
   <webui:panel title="����'${jobDefine.name}'�����й���" icon="../images/icon_list.gif">
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
			<webui:column property="description" title="���й���">
			     <c:out value="${trigger.description}"/>
			</webui:column>
			<webui:column property="runCount" title="���д���">
			  <c:choose>
			     <c:when test="${empty trigger.runCount}">δ����</c:when>
			     <c:otherwise><c:out value="${trigger.runCount}"/></c:otherwise>
			  </c:choose>
			</webui:column>
			<webui:column property="runCount" title="�������ʱ��">
			    <c:choose>
			     <c:when test="${empty trigger.lastRunTime}">δ����</c:when>
			     <c:otherwise><fmt:formatDate type="both" value="${trigger.lastRunTime}" /></c:otherwise>
			  </c:choose>
			</webui:column>
			<webui:column property="status" title="״̬"><font color="red">
			     <c:choose>
			        <c:when test="${trigger.needStartup==0}">����</c:when>
					<c:when test="${trigger.needStartup==1}">����</c:when>
					<c:when test="${trigger.needStartup==2}">����</c:when>
				</c:choose></font>	
			</webui:column>
            <webui:column property="action" title="����"  width="10%">
                <c:if test="${ trigger.needStartup!=2}">
                <a href="<c:url value='/sm/trigger.do?act=editTrigger&triggerId=${trigger.triggerId}'/>">�༭</a>
                </c:if>
                <a href="#" onclick="delete_trigger(<c:out value="${trigger.triggerId}"/>)">ɾ��</a>
            </webui:column>
             <webui:column property="action" title="ҵ�����"  width="10%">
                <c:choose>
                    <c:when test="${trigger.needStartup==1}"><a href="<c:url value="/sm/scheduler.do?act=stop&triggerId=${trigger.triggerId }"/>">����</a></c:when>
				    <c:when test="${trigger.needStartup==0}"><a href="<c:url value="/sm/scheduler.do?act=start&triggerId=${trigger.triggerId }"/>">����</a></c:when>
				</c:choose>
				<a href="<c:url value="/sm/trigger.do?act=history&triggerId=${trigger.triggerId }"/>">������ʷ</a>
            </webui:column>
		</webui:row>
	</webui:table>
      <webui:linkButton styleClass="clsButtonFace" value="����" href="#" onClick="submit_form('newTrigger')"/> 
      <webui:linkButton styleClass="clsButtonFace" value="����" href="#" onClick="back()"/>
	</webui:panel>
<script>
     function submit_form(act){
	        window.location.href= '<c:url value="/sm/trigger.do?jobId=${taskForm.jobDefine.jobId}&act="/>' +act;
	 }
	 function delete_trigger(id){
	    var choice=confirm('ȷ��Ҫɾ�������й�����');
		if(true==choice){
	      window.location.href='<c:url value="/sm/trigger.do?act=delTrigger&triggerId="/>'+id;
	    }
	 }
	 function back(){
	    window.location.href="<c:url value="/sm/task.do"/>";
	 }
</script>

