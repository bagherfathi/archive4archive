<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="template">
<webui:panel title="ģ����Ϣ" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="ģ������">
				   <c:out value="${template.template.templateName}"/>
				</webui:input>
				<webui:input label="ģ�����">
				    <c:out value="${template.template.templateCode}"/>
				</webui:input>
			</tr>
			
			<tr>
				<webui:input label="�����ļ��汾">
				   <c:if test="${template.template.publishVersion==0}">
				   �޷����ļ�
				   </c:if>
				   <c:if test="${template.template.publishVersion!=0}">
				       <c:out value="${template.template.publishVersion}"/>
				   </c:if>
				</webui:input>
				<webui:input label="�����ļ��汾">
				   <c:if test="${template.template.lastVersion==0}">
				   ��ģ���ļ�
				   </c:if>
				   <c:if test="${template.template.lastVersion!=0}">
				       <c:out value="${template.template.lastVersion}"/>
				   </c:if>
				</webui:input>
			</tr>
			
			<tr>
				<webui:input label="ģ�����" colspan="4">
		           <webui:lookup code="category_code@SM_TEMPLATE" value="${template.template.categoryCode}"/>
				</webui:input>			
			</tr>
			
			<tr>
	            <webui:input label="������Ϣ" colspan="4">
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