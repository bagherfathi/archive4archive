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
<webui:panel title="��ѯģ��" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="ģ������">
	       <html:text property="templateName" size="25"/>
	    </webui:input>
		<webui:input label="ģ�����">
		   <html:text property="templateCode" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="�������" colspan="4">
	       <html:select property="categoryCode">
    		    <html:option value="">��ѡ��</html:option>
	            <html:optionsCollection name="enumSet" property="element(category_code@SM_TEMPLATE)"/>
	        </html:select>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();templateForm.submit();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="ģ���б�" icon="../images/icon_list.gif">
<webui:table 
		dataSource="templateDS"
		action="${pageContext.request.contextPath}/sm/template.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="ģ���б�"
		var="template"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
		    <webui:column sortable="false" filterable="false" property="template.templateName" title="ģ������">
			</webui:column>
			<webui:column sortable="false" filterable="false" property="template.templateCode" title="ģ�����" />
			<webui:column sortable="false" filterable="false" property="categoryCode" title="�������">
			    <webui:lookup code="category_code@SM_TEMPLATE" value="${template.template.categoryCode}"/>
			</webui:column>
			<webui:column property="����" title="����" >
			   <a href="<c:url value='/sm/template.do?act=view&templateId=${template.template.templateId}'/>">�鿴</a>&nbsp;
			   <a href="<c:url value='/sm/template.do?act=edit&templateId=${template.template.templateId}'/>">�޸�</a>&nbsp;
			</webui:column>
			<webui:column property="ҵ�����" title="ҵ�����" >
			   <a href="<c:url value='/sm/templateFile.do?act=listHistory&templateId=${template.template.templateId}'/>">ģ���ļ�</a>&nbsp;
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="sysadmin.button.create"/>
</webui:panel>
