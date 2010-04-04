<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/dynamic.js"/>"></script>
<script src="<c:url value="/js/date.js"/>"></script>
<script src="<c:url value="/js/resource.js"/>"></script>

<sm:query var="categories" beanName="infoManager" methodName="findAllCategories"/>
<sm:buildOrgsTree orgType="-1" var="root"/>
<html:form action="/affiche" onsubmit="return onSubmit(this);">
	<input type="hidden" name="act" value="edit"/>
	<input type="hidden" name="createTime" value="<c:out value='${affiche.publishTime}'/>"/>
	<html:hidden property="affiche.publisherId"/>
	<html:hidden property="affiche.afficheId"/>
	<webui:panel title="sysadmin.title.affiche.edit" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.affiche.title" required="true">
				   	<html:text property="affiche.title" size="25"/>
				</webui:input>
				<webui:input label="sysadmin.label.affiche.level" required="true">
					<html:select property="affiche.level">
			    	<html:optionsCollection name="enumSet" property="element(affiche_level@SM_AFFICHE)"/>
			  		</html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.affiche.category" required="true" colspan="4">
				  <html:select property="affiche.categoryId">
				    <c:forEach items="${categories}" var="category">
				    <option value="<c:out value='${category.categoryId}'/>"><c:out value="${category.name}"/></option>
				    </c:forEach>
				  </html:select>
			    </webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.affiche.validTime" required="true">
				   	<input size="25" type="text" name="affiche.validTime" id="validTime" value='<fmt:formatDate value="${afficheForm.affiche.validTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="true"/>
				   	<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(validTime);return false;">
				</webui:input>
				<webui:input label="sysadmin.label.affiche.expireTime" required="true">
				   	<input size="25" type="text" name="affiche.expireTime" id="expireTime" value='<fmt:formatDate value="${afficheForm.affiche.expireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="true"/>
				   	<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(expireTime);return false;">
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.affiche.content" required="true" colspan="3">
					<html:textarea property="affiche.content" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
			<tr>
			    <td class="td-left" width="15%">发布组织</td>
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
			            发布组织
			        </c:if>
			        
			        <c:if test="${level > 0}">
				        <c:set var="flag" value="false"/>
				        <c:forEach items="${affiche.relAfficheOrgs}" var="relAfficheOrg">
				            <c:if test="${relAfficheOrg.orgId==data.key}">
				               <c:set var="flag" value="true"/>
				            </c:if>
				        </c:forEach>
				        <input class="noborder" type="checkbox" name="orgIds" value="<c:out value='${data.key}'/>" 
				            <c:if test="${data.status == 1}">disabled title="组织已被禁止或不可访问"</c:if> 
				            <c:if test="${flag}">checked</c:if> 
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
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSubmitValidator(afficheForm);" value="sysadmin.button.save"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onBack();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="afficheForm" />
<script>
	function onSubmit(input){
	    if(!validateAfficheForm(input)){
	        return false;
	    }
	    
	    if (!checkCompareDate2(document.afficheForm.validTime, "<bean:message key='sysadmin.label.affiche.validTime'/>", document.afficheForm.expireTime, "<bean:message key='sysadmin.label.affiche.expireTime'/>", true, -1, 
            "yyyy-MM-dd HH:mm:ss" )){
            return false;
        }
        
        if(!isChecked(document.getElementsByName('orgIds'),"请选择组织")){
            return false;
        }
        
        return true;
    }
    
    function onBack(){
       window.location = "<c:url value='/sm/affiche.do'/>";
    }
</script>