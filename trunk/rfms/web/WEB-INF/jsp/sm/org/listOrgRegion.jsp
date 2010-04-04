<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
	<webui:panel title="�ɷ�������" width="95%">
	<div align="left">
       ��ǰ��������: <a href="<c:url value="/sm/org.do?act=view&orgId=${org.orgId}"/>"><c:out value="${org.name}"/></a>
    </div>
	<webui:table 
		items="orgRegions"
		action="${pageContext.request.contextPath}/sm/org.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%" var="region"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  property="regionName" title="��������"/>
			<webui:column  property="regionCode" title="�������"/>    
		</webui:row>
	</webui:table>
	   <webui:linkButton styleClass="clsButtonFace"  value="���ÿɷ�������" href="javascript:configRegion()" />
	</webui:panel>
 
 <script>
     function configRegion(){
        loadOn();
     	location.href = "<c:url value="/sm/org.do?act=regionConfig&orgId=${org.orgId}"/>";
     }
 </script>
