<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
 function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();operateLogForm.submit();
     }
    }
document.body.onkeypress= checkEnter;
</script>
<fmt:setBundle basename="ActionDefinitionResource"/>
<html:form action="operateLog" method="post" >
	<webui:panel title="��ѯ" icon="../images/icon_search.gif" width="95%">	
	<webui:formTable>
	    <tr>
	    	<webui:input label="����Ա��½��">
	    	    <html:text property="operLoginName" size="25"/>
	    	    <%--
	    		<input type="text" name="operLoginName" value="<c:out value='${param.operatorLoginName}'/>"/>&nbsp;
	    		--%>
	    	</webui:input>
	    	<webui:input label="��ϵͳ">
	    		<html:select property="subSysCode">
	    		    <html:option value="">��ѡ��</html:option>
		            <html:optionsCollection name="enumSet" property="element(sub_sys_code@SM_OPERATOR_LOG)"/>
 		        </html:select>
	    	</webui:input> 	
	    </tr>
	    <tr>
			<webui:input label="��ʼʱ��">
				<input size="25" type="text" readonly="true" id="beginTime" name="insertTime.beginTime" value="<c:out value='${param["insertTime.beginTime"]}'/>"/>
				<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(beginTime,'yyyy-MM-dd');return false;">
			</webui:input>
			<webui:input label="����ʱ��">
				<input size="25" type="text" readonly="true" id="endTime" name="insertTime.endTime"  value="<c:out value='${param["insertTime.endTime"]}'/>"/>
				<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(endTime,'yyyy-MM-dd');return false;">
			</webui:input>
	    </tr>
	    <tr>
	        <webui:input label="�������" colspan="4">
	    		<html:select property="resultCode">
	    		    <html:option value="">��ѡ��</html:option>
		            <html:optionsCollection name="enumSet" property="element(resultCode@SM_OPERATOR_LOG)"/>
 		        </html:select>
	    	</webui:input>
	    </tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();operateLogForm.submit()" value="��ѯ"/>	
</webui:panel>
</html:form>
<br/>
<webui:panel title="������־�б�" icon="../images/icon_list.gif" width="95%">
	<webui:table 
		dataSource="operateLogDS"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		action="${pageContext.request.contextPath}/sm/operateLog.do"
		var="log"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="true"
		showExports="true"	
		> <ec:exportXls
           fileName="operateLog.xls" 
           text="XLS"
           view="com.ft.web.sm.log.LogXlsView"
           />
		<webui:row>
		    <webui:column property="recordTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="ʱ��"/>
		    <webui:column property="operatorLoginName" title="����Ա"/>
		    <webui:column property="remoteHost" title="IP��ַ"/>
		    <webui:column property="systemCode" title="��ϵͳ">
		        <fmt:message key="${log.systemCode}"/>
		    </webui:column>
		    <webui:column property="modelName" title="ģ������">
		        <fmt:message key="${log.modelName}"/>
		    </webui:column>
            <webui:column property="actionCode" title="��������">
                <fmt:message key="${log.actionCode}"/>
            </webui:column>
			<webui:column property="resultCode" title="�������">
			    <fmt:message key="${log.resultCode}"/>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
