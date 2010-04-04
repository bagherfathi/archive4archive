<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onCreate(){
        document.templateForm.act.value="create";
        document.templateForm.submit();
    }  
     function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();templateForm.submit();
     }
    }
document.body.onkeypress= checkEnter;  
</script>
<html:form action="/template" method="post">
<input type="hidden" value="search" name="act"/>
<webui:panel title="查询模板" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="模板名称">
	       <html:text property="templateName" size="25"/>
	    </webui:input>
		<webui:input label="模板编码">
		   <html:text property="templateCode" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="所属类别" colspan="4">
	       <html:select property="categoryCode">
    		    <html:option value="">请选择</html:option>
	            <html:optionsCollection name="enumSet" property="element(category_code@SM_TEMPLATE)"/>
	        </html:select>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();templateForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="模板列表" icon="../images/icon_list.gif">
<webui:table 
		dataSource="templateDS"
		action="${pageContext.request.contextPath}/sm/template.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="模板列表"
		var="template"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
		    <webui:column sortable="false" filterable="false" property="template.templateName" title="模板名称">
			</webui:column>
			<webui:column sortable="false" filterable="false" property="template.templateCode" title="模板编码" />
			<webui:column sortable="false" filterable="false" property="categoryCode" title="所属类别">
			    <webui:lookup code="category_code@SM_TEMPLATE" value="${template.template.categoryCode}"/>
			</webui:column>
			<webui:column property="操作" title="操作" >
			   <a href="<c:url value='/sm/template.do?act=view&templateId=${template.template.templateId}'/>">查看</a>&nbsp;
			   <a href="<c:url value='/sm/template.do?act=edit&templateId=${template.template.templateId}'/>">修改</a>&nbsp;
			</webui:column>
			<webui:column property="业务操作" title="业务操作" >
			   <a href="<c:url value='/sm/templateFile.do?act=listHistory&templateId=${template.template.templateId}'/>">模板文件</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="sysadmin.button.create"/>
</webui:panel>
