<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
Date.prototype.format = function(format) //author: meizz 日期格式化
{
		var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
		}
		if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		(this.getFullYear()+"").substr(4 - RegExp.$1.length));
		for(var k in o)if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,
		RegExp.$1.length==1 ? o[k] :
		("00"+ o[k]).substr((""+ o[k]).length));
		return format;
}
function check(){
	var endTime = document.getElementById('endTime');
	var beginTime = document.getElementById('beginTime');
	if(beginTime.value!='' && endTime.value==''){
	endTime.value=new Date().format("yyyy-MM-dd hh:mm:ss"); 
	}
}
function checkEnter(){
      if(window.event.keyCode==13){
         check();loadOn();document.maintainLogForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>

<sm:query beanName="maintainManager" methodName="findAllMaintainPlans" var="planList"/>
<html:form action="/maintainLog" method="post" onsubmit="check();">
	<html:hidden property="act" value="toSearch"/>
	<webui:panel title="sysadmin.title.search.maintain.log" width="95%" icon="../images/icon_search.gif">
		<webui:formTable>
		 	<tr>
		 		<webui:input label="sysadmin.label.maintain.Log.title">
				   <html:text property="logTitle" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.maintain.Log.type">
					<html:select property="logType">
						<html:option value="" key="sysadmin.label.select"/>
			    		<html:optionsCollection name="enumSet" property="element(log_type@SM_MAINTAIN_PLAN)"/>
			    	</html:select>
				</webui:input>
		 	</tr>
		 	<tr>
			 	<webui:input label="sysadmin.label.startTime">
					<input size="25" type="text" id="beginTime" readonly="true" name="logTime.beginTime" value="<c:out value='${param["logTime.beginTime"]}'/>"/>
					<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(beginTime,'yyyy-MM-dd');return false;">
				</webui:input>
				<webui:input label="sysadmin.label.endTime">
					<input size="25" type="text" id="endTime" readonly="true" name="logTime.endTime" value="<c:out value='${param["logTime.endTime"]}'/>"/>
					<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(endTime,'yyyy-MM-dd');return false;">
				</webui:input>
			</tr>
			
			<tr>
				<webui:input label="sysadmin.label.maintain.plan" colspan="4">
					<html:select property="planId">
						<html:option value="" key="sysadmin.label.select"/>
						<c:forEach items="${planList}" var="plan">
						<option value="<c:out value='${plan.planId}'/>" <c:if test="${maintainLogForm.planId==plan.planId}">selected</c:if>><c:out value="${plan.name}"/></option>
						</c:forEach>
			    	</html:select>
				</webui:input>
		 	</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:check();loadOn();document.maintainLogForm.submit();" value="sysadmin.button.search"/>	
	</webui:panel>
</html:form>
</br>
<webui:panel title="sysadmin.title.maintain.log.list" icon="../images/icon_list.gif" width="95%">
	<webui:table
		dataSource="maintainLogDS"
		action="${pageContext.request.contextPath}/sm/maintainLog.do?act=toSearch"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.rule.list"
		var="log"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" property="title" title="日志标题" >
			</webui:column>
			<webui:column sortable="false" filterable="false" property="type" title="维护类型" >
				<webui:lookup code="log_type@SM_MAINTAIN_PLAN" value="${log.type}"/>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="planId" title="维护计划" >
			    <c:if test="${! empty log.planId}">
			    <c:forEach items="${planList}" var="plan">
			        <c:if test="${log.planId==plan.planId}"><c:out value="${plan.name}"></c:out></c:if>
			    </c:forEach>
			    </c:if>
			    <c:if test="${empty log.planId}">
			    无
			    </c:if>
			</webui:column>
			<webui:column sortable="false" filterable="false" property="creator" title="作者" />
			<webui:column sortable="false" filterable="false" cell="date" format="yyyy-MM-dd HH:mm:ss" property="logTime" title="时间" />
			<webui:column property="操作" title="操作" width="10%">
			   <a href="<c:url value='/sm/maintainLog.do?act=toEdit&id=${log.logId}'/>">编辑</a>
			   <security:checkPermission resourceKey="SM_DELETE_MAINTAIN_LOG">
		         <security:success>
			       <a href="#" onclick="toDelete(<c:out value='${log.logId}'/>);">删除</a>
		         </security:success>
	           </security:checkPermission>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_MAINTAIN_LOG">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:goCreate();" value="sysadmin.button.create"/>
		</security:success>
	</security:checkPermission>
</webui:panel>
<script>
	function goCreate(){
		location.href = "<c:url value='/sm/maintainLog.do?act=create'/>";
	}
	function toDelete(id){
		if(window.confirm("<bean:message key='msg.confirm.delete.maintain.log'/>")){
			location.href =  "<c:url value="/sm/maintainLog.do?act=delete&id="/>" + id;
		}
	}
</script>
