<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="template">
<webui:panel title="模板信息" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="模板名称">
				   <c:out value="${template.template.templateName}"/>
				</webui:input>
				<webui:input label="模板编码">
				    <c:out value="${template.template.templateCode}"/>
				</webui:input>
			</tr>
			
			<tr>
				<webui:input label="发布文件版本">
				   <c:if test="${template.template.publishVersion==0}">
				   无发布文件
				   </c:if>
				   <c:if test="${template.template.publishVersion!=0}">
				       <c:out value="${template.template.publishVersion}"/>
				   </c:if>
				</webui:input>
				<webui:input label="最新文件版本">
				   <c:if test="${template.template.lastVersion==0}">
				   无模板文件
				   </c:if>
				   <c:if test="${template.template.lastVersion!=0}">
				       <c:out value="${template.template.lastVersion}"/>
				   </c:if>
				</webui:input>
			</tr>
			
			<tr>
				<webui:input label="模板类别" colspan="4">
		           <webui:lookup code="category_code@SM_TEMPLATE" value="${template.template.categoryCode}"/>
				</webui:input>			
			</tr>
			
			<tr>
	            <webui:input label="描述信息" colspan="4">
	                <html:textarea property="template.template.templateDesc" readonly="true" styleClass="wid80" rows="3"/>
    	        </webui:input>
            </tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
    function back(){
      location.href="<c:url value="/sm/template.do"/>";
    }
</script>