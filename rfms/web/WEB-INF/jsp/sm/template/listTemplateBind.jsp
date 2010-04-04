<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html:form action="templateBind">
<input type="hidden" name="act" value="save"/>
<input type="hidden" name="delTemplateId"/>
<input type="hidden" name="orgId" value="<c:out value='${templateBindForm.orgId}'/>"/>
<webui:panel title="ģ���" width="95%" icon="../images/icon_list.gif">
     <webui:formTable>
         <tr>
	        <webui:input label="ģ�����" colspan="4">
	          <html:select property="categoryCode" onchange="changeCategory();">
    		    <html:option value="">��ѡ��</html:option>
	            <html:optionsCollection name="enumSet" property="element(category_code@SM_TEMPLATE)"/>
	          </html:select>
	        </webui:input>
	        <webui:input label="ѡ��ģ��" colspan="4">
	          <html:select property="templateId">
    		    <html:option value="">��ѡ��</html:option>
	            <html:optionsCollection property="notBindList"/>
	          </html:select>
	        </webui:input>
         </tr>
     </webui:formTable>
     <c:if test="${templateBindForm.orgId > 0}">
         <webui:linkButton styleClass="clsButtonFace" href="javascript:onAdd();" value="sysadmin.button.create"/>
     </c:if>
</webui:panel>
</html:form>
</br>
<webui:panel title="��ģ���б�" icon="../images/icon_list.gif">
		<webui:table
		items="templateDtoList"
		action="${pageContext.request.contextPath}/sm/templateBind.do?act=search"
		var="templateDto"
		width="95%"
		rowsDisplayed="20"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false">
		<webui:row>
			<webui:column property="template.templateName" title="ģ����" />
			<webui:column property="template.templateCode" title="ģ�����" />
			<webui:column property="template.categoryCode" title="�������">
			    <webui:lookup code="category_code@SM_TEMPLATE" value="${templateDto.template.categoryCode}"/>
			</webui:column>
			<webui:column property="����" title="����">
			    <a href="#" onclick="onDelete(<c:out value='${templateDto.template.templateId}'/>)">ɾ��</a>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
<script>
    function onAdd(){
        var templateId = document.getElementById("templateId").value;
        if(templateId == ""){
            onSure("��ѡ��ģ�������ӣ�");
            return;
        }
        document.templateBindForm.act.value="addBind";
        document.templateBindForm.submit();
    }
    
    function changeCategory(){
        document.templateBindForm.act.value="changeCategory";
        document.templateBindForm.submit();
    }
    
    function onDelete(templateId){
        if (confirm("ȷ��Ҫɾ���ð󶨹�ϵ��")){
            document.templateBindForm.act.value="deleteBind";
            document.templateBindForm.delTemplateId.value=templateId;
            document.templateBindForm.submit();
        }
    }
</script>
