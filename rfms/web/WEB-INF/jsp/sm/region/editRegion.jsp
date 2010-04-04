<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function backToParent(){
        location.href = '<c:url value="/sm/region.do?act=view&regionId="/>' + '<c:out value="${parentRegion.regionId}"/>';
    }
    
    function backToRegion(){
        location.href = '<c:url value="/sm/region.do?act=view&regionId="/>' + '<c:out value="${region.regionId}"/>';
    }
    
</script>
<html:form styleId="regionForm" action="/region.do" onsubmit="return validateRegionForm(this);">
<html:hidden property="act" value="save"/>
<html:hidden property="validationKey" value="regionForm"/>
<input type="hidden" name="regionId" value="<c:out value='${region.regionId}'/>"/>
<input type="hidden" name="parentId" value="<c:out value='${parentRegion.regionId}'/>"/>
	<webui:panel title="sysadmin.label.region.info" width="95%" icon="../images/icon_list.gif">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.region.name" required="true">
				<html:text property="region.regionName" size="25"/>
			</webui:input>
			<webui:input label="sysadmin.label.region.code" required="true">
				<html:text property="region.regionCode" size="25"/>
			</webui:input>
		</tr>
		<tr>
		    <webui:input label="sysadmin.label.region.type" required="true">
			    <html:select property="region.regionType">
			        <option value="">请选择</option>
			        <%--
			    	<html:optionsCollection name="enumSet" property="element(region_type@SM_REGION)"/>
			    	--%>
			    	<bean:define id="regionTypes" name="enumSet" property="element(region_type@SM_REGION)"/>
			    	<c:forEach items="${regionTypes}" var="regionType">
			    	    <c:if test="${regionType.value > parentRegion.regionType}">
			    	        <c:if test="${region.regionId > 0}">
			    	        <option value="<c:out value='${regionType.value}'/>" <c:if test='${regionType.value==region.regionType}'>selected</c:if>><c:out value="${regionType.label}"/></option>
			    	        </c:if>
			    	        <c:if test="${empty region.regionId}">
			    	        <option value="<c:out value='${regionType.value}'/>" <c:if test='${regionType.value==parentRegion.regionType+1}'>selected</c:if>><c:out value="${regionType.label}"/></option>
			                </c:if>
			    	    </c:if>
			    	</c:forEach>
			    </html:select>
			</webui:input>
			
			<webui:input label="sysadmin.label.region.parent.region">
			    <input type="text" size="25" value="<c:out value='${regionForm.parentRegion.regionName}'/>" readonly="true"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="sysadmin.label.region.ysname" required="true">
				<html:text property="region.ysname" size="25"/>
			</webui:input>
			<webui:input label="sysadmin.label.region.yscode" required="true">
				<html:text property="region.yscode" size="25"/>
			</webui:input>
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(regionForm);" value="保存"/>
	<c:if test="${region.regionId > 0}">
	    <webui:linkButton styleClass="clsButtonFace" href="javascript:backToRegion();" value="返回"/>
	</c:if>
	<c:if test="${empty region.regionId}">
	    <webui:linkButton styleClass="clsButtonFace" href="javascript:backToParent();" value="返回"/>
	</c:if>
	</webui:panel>
</html:form>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="regionForm" />