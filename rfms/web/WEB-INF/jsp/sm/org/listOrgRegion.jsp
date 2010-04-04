<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>
	<webui:panel title="可访问区域" width="95%">
	<div align="left">
       当前操作对象: <a href="<c:url value="/sm/org.do?act=view&orgId=${org.orgId}"/>"><c:out value="${org.name}"/></a>
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
			<webui:column  property="regionName" title="区域名称"/>
			<webui:column  property="regionCode" title="区域代码"/>    
		</webui:row>
	</webui:table>
	   <webui:linkButton styleClass="clsButtonFace"  value="设置可访问区域" href="javascript:configRegion()" />
	</webui:panel>
 
 <script>
     function configRegion(){
        loadOn();
     	location.href = "<c:url value="/sm/org.do?act=regionConfig&orgId=${org.orgId}"/>";
     }
 </script>
