<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="sysadmin.label.category.list" width="95%" icon="../images/icon_list.gif">
	<webui:table
		dataSource="infoCateforyDS"
		action="${pageContext.request.contextPath}/sm/infoShared.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="category"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="categoryInfoId" title="序号" width="5%"/>
			<webui:column filterable="false" property="categoryName" title="分类" width="18%">
				<a href="<c:url value='/sm/infoShared.do?act=toSearch&cId=${category.categoryId}'/>"><c:out value="${category.name}"/></a>
			</webui:column>
			<sm:query var="infoNumber" beanName="infoManager" methodName="findInfoNumberOfCategory">
			    <sm:param type="java.lang.Long" value="${category.categoryId}"/>
			</sm:query>
			
			<webui:column filterable="false" property="sum" title="信息总数" width="10%">
			    <c:out value="${infoNumber}"/>
			</webui:column>
			
			<sm:query var="infoShared" beanName="infoManager" methodName="findLastPublishedInfo">
			    <sm:param type="java.lang.Long" value="${category.categoryId}"/>
			</sm:query>

			<webui:column filterable="false" property="title" title="最后发布的信息" width="25%">
			    <c:if test="${!empty infoShared}">
				    <a href="<c:url value='/sm/infoShared.do?act=view&id=${infoShared.infoId}'/>"><c:out value="${infoShared.title}"/></a>
				</c:if>
				<c:if test="${empty infoShared}">
				    <c:out value=""/>
				</c:if>
			</webui:column>
			<webui:column filterable="false" property="publisher" title="发布人" width="20%">
			    <c:out value="${infoShared.operator.opName}"/>
			</webui:column>
			<webui:column filterable="false" property="lastPublishTime" title="发布时间" width="22%">
			    <c:out value="${infoShared.publishTime}"/>
			</webui:column>
			
			<c:remove var="infoShared"/>
		</webui:row>
	</webui:table>
</webui:panel>
