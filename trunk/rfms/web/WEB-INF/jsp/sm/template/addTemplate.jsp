<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<%--
<sm:buildOrgsTree orgType="-1" var="root"/>
--%>
<html:form action="template" styleId="createTemplateForm" enctype="multipart/form-data" onsubmit="return confirmUpload(this);" >
<input type="hidden" name="act" value="save"/>
<input type="hidden" name="validationKey" value="createTemplateForm"/>

<webui:panel title="新建模板信息" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="模板名称" required="true">
				   <html:text property="template.template.templateName" size="25"/>
				</webui:input>
				<webui:input label="模板编码" required="true">
				    <html:text property="template.template.templateCode" size="25"/>
				</webui:input>
			</tr>
			
			<tr>
				<webui:input label="模板类别" required="true">
		           <html:select property="template.template.categoryCode">
		           <option value="">请选择</option>
		            <html:optionsCollection name="enumSet" property="element(category_code@SM_TEMPLATE)"/>
		            </html:select>
				</webui:input>
				
				<webui:input label="模板文件">
		            <html:file property="file"/>
				</webui:input>
			</tr>
			
			<tr>
	            <webui:input label="描述信息" colspan="4">
	                <html:textarea property="template.template.templateDesc" styleClass="wid80" rows="3"/>
    	        </webui:input>
            </tr>
            <%--
            <tr>
			    <td class="td-left" width="15%">适用组织</td>
			    <td colspan="3" class="td-right td_normal">
			    <table width="90%" align="center" border="0" cellpadding="0" cellspacing="0">
			      <tr><td>
			      <webui:tree 
			        root="${root}" 
			        id="data" 
			        type="com.ft.commons.tree.BaseTreeNode" 
			        indent="2" extend="2" 
			        saveToCookie="false" 
			        level="level" 
			        closeFolderImg="../images/tree/jia.gif" 
			        openFolderImg="../images/tree/jian.gif" 
			        leafImg="../images/tree/space.gif">
			        <c:if test="${level==0}">
			            <c:out value='${data.nodeName}'/>
			        </c:if>
			        <c:if test="${level > 0}">
				        <input class="noborder" type="checkbox" name="orgIds" value="<c:out value='${data.key}'/>" 
				            <c:if test="${data.status == 1 || data.object.type==1 || data.object.type==4 || data.object.type==0}">disabled title="组织已被禁止或不可访问"</c:if> 
				        />
				        <c:if test="${empty data.parent}">
				            <img align="absmiddle" src="../images/books_open.gif" /><c:out value='${data.nodeName}'/>
				        </c:if>
				        <c:if test="${!empty data.parent}">
				            <c:if test="${empty data.children}">
				                <img align="absmiddle" src="../images/book.gif" /><c:out value='${data.nodeName}'/>
				            </c:if>
				            <c:if test="${!empty data.children}">
				                <img align="absmiddle" src="../images/books_close.gif" /><c:out value='${data.nodeName}'/>
				            </c:if>
				        </c:if>
			        </c:if>
			      </webui:tree>
			      </td></tr>
			    </table>
			    </td>
			</tr>
			--%>
			
		</webui:formTable>
		<br/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="submitForm(templateForm)" value="sysadmin.button.save"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back()" value="sysadmin.button.return"/>
</webui:panel>
</html:form>
<script>
    function back(){
        location.href = "<c:url value="/sm/template.do"/>"
    }
    
    function confirmUpload(f){
        if(document.getElementById('file').value != ''){
            if(!confirm("<bean:message key='maxLengthExplanation'/>")){
               return false;
           }
        }
	    return validateCreateTemplateForm(f);
    }
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="createTemplateForm" />