<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="sysadmin.label.dataResource.title" icon="../images/icon_list.gif">
	<webui:table
		items="dataResources"
		tableId="drTable"
		action="${pageContext.request.contextPath}/sm/dataResource.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		title="sysadmin.label.dataResource.title"
		width="95%"
		var="data"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column filterable="false" property="title" title="ҵ��Ȩ�ޱ���">
			</webui:column>
			<webui:column filterable="false" property="code" title="ҵ��Ȩ�ޱ���">
			</webui:column>
			<webui:column property="description" title="ҵ��Ȩ������" styleClass="td_normal"/>
			<webui:column property="crud" title="����" width="5%">
			    <a href="<c:url value='/sm/dataResource.do?act=edit&id=${data.resourceId}'/>">�༭</a>
			    <sm:dataResource var="flag" id="${data.resourceId}"/>
			    <c:if test="${!flag}" >
			          <a href="#" onclick="goDel(<c:out value="${data.resourceId }"/>)">ɾ��</a>
			    </c:if>
			</webui:column>
			<webui:column property="action" title="ҵ�����" width="10%">
			    <a href="<c:url value='/sm/dataResource.do?act=view&id=${data.resourceId}'/>">ҵ��Ȩ����Ŀ</a>&nbsp;
			</webui:column>
  		</webui:row>
	</webui:table>
		<security:checkPermission resourceKey="SM_ADD_DATA_RESOURCE">
			<security:success>
				<webui:linkButton styleClass="clsButtonFace" href="javascript:goCreate();" value="sysadmin.button.create"/>
			</security:success>
		</security:checkPermission>
	</webui:panel>
<script>
	function goCreate(){
		location.href = "<c:url value='/sm/dataResource.do?act=add'/>";
	}
	
	function goDel(id){
		if(confirm("<bean:message key='msg.confirm.delete.dataResource'/>")){
		    loadOn();
		    location.href = "<c:url value='/sm/dataResource.do?act=delete&id='/>" + id ;
	    }
	}
</script>
