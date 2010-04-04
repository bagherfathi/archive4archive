<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<fmt:setBundle basename="ActionDefinitionResource"/>

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
</script>

<html:form action="performanceLog" method="post" onsubmit="check()">
	<webui:panel title="查询" icon="../images/icon_search.gif" width="95%">	
	<webui:formTable>
	    <tr>
	    	<webui:input label="操作员登陆名">
	    	    <html:text property="operLoginName" size="25"/>
	    	    <%--
	    		<input type="text" name="operLoginName" value="<c:out value='${param.operatorLoginName}'/>"/>&nbsp;
	    		--%>
	    	</webui:input> 
	    	<webui:input label="子系统">
	    		<html:select property="subSysCode">
	    		    <html:option value="">请选择</html:option>
		            <html:optionsCollection name="enumSet" property="element(sub_sys_code@SM_OPERATOR_LOG)"/>
 		        </html:select>
	    	</webui:input> 	
	    </tr>
	    <tr>
			<webui:input label="开始时间">
				<input size="25" type="text" id="beginTime" name="insertTime.beginTime" value="<c:out value='${param["insertTime.beginTime"]}'/>"/>
				<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(beginTime,'yyyy-MM-dd');return false;">
			</webui:input>
			<webui:input label="结束时间">
				<input size="25" type="text" id="endTime" name="insertTime.endTime"  value="<c:out value='${param["insertTime.endTime"]}'/>"/>
				<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(endTime,'yyyy-MM-dd');return false;">
			</webui:input>
	    </tr>
	    <tr>
	        <webui:input label="操作结果" colspan="4">
	    		<html:select property="resultCode">
	    		    <html:option value="">请选择</html:option>
		            <html:optionsCollection name="enumSet" property="element(resultCode@SM_OPERATOR_LOG)"/>
 		        </html:select>
	    	</webui:input>
	    </tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:check();loadOn();operateLogForm.submit()" value="查询"/>	
</webui:panel>
</html:form>
<br/>
	<webui:panel title="性能日志维护" width="95%" icon="../images/icon_list.gif">
	<webui:table 
		dataSource="performanceLogDS"
		action="${pageContext.request.contextPath}/sm/performanceLog.do"
		width="95%"
		var="log"
		showPagination="true"
		showStatusBar="true"
		showExports="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"		
		>
		
		<ec:exportXls
           fileName="performanceLog.xls" 
           text="XLS"
           view="com.ft.web.sm.log.LogXlsView"
           />
		<webui:row>
		    <webui:column property="recordTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="时间" />
		    <webui:column property="operatorLoginName" title="操作员登陆名" />
		    <webui:column property="remoteHost" title="IP地址"/>
		    <webui:column property="systemCode" title="子系统">
		        <fmt:message key="${log.systemCode}"/>
		    </webui:column>
		    <webui:column property="modelName" title="模块名称">
		        <fmt:message key="${log.modelName}"/>
		    </webui:column>
            <webui:column property="actionCode" title="操作名称">
                <fmt:message key="${log.actionCode}"/>
            </webui:column>
			<webui:column property="resultCode" title="操作结果">
			    <fmt:message key="${log.resultCode}"/>
			</webui:column>
			<webui:column property="startTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="执行开始时间"/>
		    <webui:column property="endTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="执行结束时间"/>
		</webui:row>
	</webui:table>
	</webui:panel>
