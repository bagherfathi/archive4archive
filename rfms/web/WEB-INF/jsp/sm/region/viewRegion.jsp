<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="/region" method="post">
<input type="hidden" name="regionId" value="<c:out value='${region.regionId}'/>"/>
<input type="hidden" name="parentId" value="<c:out value='${region.parent.regionId}'/>"/>

  <%--查询子区域--%>
  <sm:query var="childrenRegions" beanName="regionManager" methodName="findRegionByParentId">
  	<sm:param value="${regionForm.region.regionId}" type="java.lang.Long"/>
  </sm:query>

  <webui:panel width="95%" title="sysadmin.label.region.preview" icon="../images/icon_list.gif">
		<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.region.name" >
				<c:out value='${regionForm.region.regionName}'/>
			</webui:input>
			<webui:input label="sysadmin.label.region.code" >
				<c:out value='${regionForm.region.regionCode}'/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.region.type" >
				<webui:lookup code="region_type@SM_REGION" value="${regionForm.region.regionType}"/>
			</webui:input>
			<webui:input label="sysadmin.label.region.parent.region">
			    <c:out value='${regionForm.region.parent.regionName}'/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.region.ysname" >
				<c:out value='${regionForm.region.ysname}'/>
			</webui:input>
			<webui:input label="sysadmin.label.region.yscode" >
				<c:out value='${regionForm.region.yscode}'/>
			</webui:input>
		</tr>
		</webui:formTable>
	<security:checkPermission resourceKey="SM_EDIT_REGION">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:onEdit();" value="sysadmin.button.edit"/>
		</security:success>
	</security:checkPermission>
	
	<c:if test="${empty childrenRegions}">
	    <c:if test="${regionForm.region.status==0}">
		<security:checkPermission resourceKey="SM_DISABLE_REGION">
			<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onDisbale();" value="sysadmin.button.disable"/>
			</security:success>
		</security:checkPermission>
		</c:if>
	</c:if>
	
	<c:if test="${regionForm.region.status==1}">
		<security:checkPermission resourceKey="SM_ENABLE_REGION">
			<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onEnabel();" value="sysadmin.button.enable"/>
			</security:success>
		</security:checkPermission>
	</c:if>
	
	<c:if test="${region.regionId!=region.parent.regionId}">
        <webui:linkButton styleClass="clsButtonFace" href="#" onClick="javascript:onBack();" value="返回上级"/>
    </c:if>
</webui:panel>
</html:form>

<c:set var="canCreateChild" value="false"/>
<bean:define id="regionTypes" name="enumSet" property="element(region_type@SM_REGION)"/>

<c:forEach items="${regionTypes}" var="regionType">
    <c:if test="${regionForm.region.regionType < regionType.value}">
        <c:set var="canCreateChild" value="true"/>
    </c:if>
</c:forEach>

<c:if test="${canCreateChild}">
<br/>
<webui:panel width="95%" title="sysadmin.label.region.children.list" icon="../images/icon_list.gif">
	<webui:table
	items="childrenRegions"
	action="${pageContext.request.contextPath}/sm/region.do?"
	imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
	width="95%"
	var="cRegion"
	showPagination="true"
	showStatusBar="true"
	showTitle="false"
	filterable="false"
	sortable="false"
	>
	<webui:row>
		<webui:column filterable="false" property="regionName" title="区域名称">
		</webui:column>
		<webui:column filterable="false" property="regionCode" title="区域编码"/>
		<webui:column property="regionType" title="区域类型" >
			<webui:lookup code="region_type@SM_REGION" value="${cRegion.regionType}"/>
		</webui:column>
		<webui:column property="status" title="状态" >
			<webui:lookup code="status@SM_REGION" value="${cRegion.status}"/>
		</webui:column>
		<webui:column filterable="false" property="region.ysname" title="银商名称">
		</webui:column>
		<webui:column filterable="false" property="region.yscode" title="银商编码"/>
		<webui:column property="操作" title="操作" >
			<a href="<c:url value='/sm/region.do?act=edit&regionId=${cRegion.regionId}&parentId=${cRegion.parentId}'/>">修改</a>&nbsp;
		</webui:column>
		
	</webui:row>
	</webui:table>
	<c:if test="${regionForm.region.status==0}">
    <security:checkPermission resourceKey="SM_ADD_REGION">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:onCreate();" value="sysadmin.button.create"/>
		</security:success>
	</security:checkPermission>
	</c:if>
</webui:panel>
</c:if>
<form name="hiddenForm" method="post" />

<script>
    function onCreate(){
        window.location = "<c:url value='/sm/region.do?act=create&parentId=${regionForm.region.regionId}'/>";
    }

    function onEdit(){
        window.location = "<c:url value='/sm/region.do?act=edit&regionId=${regionForm.region.regionId}&parentId=${regionForm.region.parent.regionId}'/>";
    }

    function onEnabel(){
        if (confirm("<bean:message key='msg.confirm.enable.region'/>")) {
            loadOn();
            document.forms.hiddenForm.setAttribute('action', '<c:url value="/sm/region.do?act=enable&regionId=${region.regionId}"/>');
	        document.forms.hiddenForm.submit();
        }
    }
    
    function onDisbale(){
        if (confirm("<bean:message key='msg.confirm.disable.region'/>")) {
            loadOn();
            document.forms.hiddenForm.setAttribute('action', '<c:url value="/sm/region.do?act=disable&regionId=${region.regionId}"/>');
	        document.forms.hiddenForm.submit();
        }
    }
    
    function onBack(){
        window.location ="<c:url value='/sm/region.do?act=view&regionId=${region.parent.regionId}'/>";
    }
    <c:choose>
    <c:when  test="${empty param.flag}">
    if(parent.leftFrame.document.readyState == "complete"){
      var tree  = parent.leftFrame.tree;
      tree.selectItem("<c:out value="${regionForm.region.regionId}"/>",false);
      tree.openItem("<c:out value="${regionForm.region.regionId}"/>");
    }
    </c:when>
    <c:when test="${param.flag == 'add' }">
	  parent.leftFrame.tree.insertNewItem(<c:out value="${regionForm.region.parentId}"/>,<c:out value="${regionForm.region.regionId}"/>,"<c:out value="${regionForm.region.regionName}"/>",0,"book.gif","books_open.gif","books_close.gif","SELECT");
    </c:when>
    <c:when test="${param.flag == 'update' }">
      parent.leftFrame.tree.setItemText(<c:out value="${regionForm.region.regionId}"/>,"<c:out value="${regionForm.region.regionName}"/><c:if test="${regionForm.region.status == 1}">(禁用)</c:if>");
    </c:when>
    </c:choose>
</script>

