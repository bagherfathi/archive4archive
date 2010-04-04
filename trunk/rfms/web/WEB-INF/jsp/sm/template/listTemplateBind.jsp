<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html:form action="templateBind">
<input type="hidden" name="act" value="save"/>
<input type="hidden" name="delTemplateId"/>
<input type="hidden" name="orgId" value="<c:out value='${templateBindForm.orgId}'/>"/>
<webui:panel title="模板绑定" width="95%" icon="../images/icon_list.gif">
     <webui:formTable>
         <tr>
	        <webui:input label="模板类别" colspan="4">
	          <html:select property="categoryCode" onchange="changeCategory();">
    		    <html:option value="">请选择</html:option>
	            <html:optionsCollection name="enumSet" property="element(category_code@SM_TEMPLATE)"/>
	          </html:select>
	        </webui:input>
	        <webui:input label="选择模板" colspan="4">
	          <html:select property="templateId">
    		    <html:option value="">请选择</html:option>
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
<webui:panel title="绑定模板列表" icon="../images/icon_list.gif">
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
			<webui:column property="template.templateName" title="模板名" />
			<webui:column property="template.templateCode" title="模板代码" />
			<webui:column property="template.categoryCode" title="所属类别">
			    <webui:lookup code="category_code@SM_TEMPLATE" value="${templateDto.template.categoryCode}"/>
			</webui:column>
			<webui:column property="操作" title="操作">
			    <a href="#" onclick="onDelete(<c:out value='${templateDto.template.templateId}'/>)">删除</a>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
<script>
    function onAdd(){
        var templateId = document.getElementById("templateId").value;
        if(templateId == ""){
            onSure("请选择模板进行添加！");
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
        if (confirm("确定要删除该绑定关系？")){
            document.templateBindForm.act.value="deleteBind";
            document.templateBindForm.delTemplateId.value=templateId;
            document.templateBindForm.submit();
        }
    }
</script>
