<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<sm:query var="categories" beanName="infoManager" methodName="findAllCategories"/>
<html:form action="/affiche">
	<input type="hidden" name="act" value="delete"/>
	<input type="hidden" name="id" value="<c:out value='${affiche.afficheId}'/>"/>
	<webui:panel title="sysadmin.title.affiche.info" width="95%" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="sysadmin.label.affiche.title" >
				   	<c:out value="${affiche.title}"/>
				</webui:input>
				<webui:input label="sysadmin.label.affiche.publisher" >
				    <c:out value="${affiche.operator.opName}"/>
				</webui:input>
			</tr>
			<tr>
			    <webui:input label="sysadmin.label.affiche.category">
				    <c:forEach items="${categories}" var="category">
				      <c:if test="${category.categoryId==affiche.categoryId}">
				      <c:out value="${category.name}"/>
				      </c:if>
			        </c:forEach>
			    </webui:input>
			    <webui:input label="发布时间">
				    <fmt:formatDate value="${affiche.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			    </webui:input>
		    </tr>
			<tr>
				<webui:input label="sysadmin.label.affiche.validTime">
				    <fmt:formatDate value="${affiche.validTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</webui:input>
				<webui:input label="sysadmin.label.affiche.expireTime">
					<fmt:formatDate value="${affiche.expireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.affiche.level" colspan="3">
					<webui:lookup code="affiche_level@SM_AFFICHE" value="${affiche.level}" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="sysadmin.label.affiche.content" colspan="3">
					<html:textarea property="affiche.content" styleClass="wid80" rows="3" readonly="true"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<c:if test="${affiche.publisherId == afficheForm.currentUser.operatorId}">
	        <webui:linkButton styleClass="clsButtonFace" href="javascript:toEdit();" value="sysadmin.button.edit"/>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:toDelete();" value="sysadmin.button.delete"/>
		</c:if>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="sysadmin.button.return"/>
	</webui:panel>
</html:form>
<script>
	function toEdit(){
		window.location = "<c:url value='/sm/affiche.do?act=toEdit&id=${affiche.afficheId}'/>";
	}

	function toDelete(){
		if(window.confirm("<bean:message key='msg.confirm.delete.info.affiche'/>")){
      		document.afficheForm.submit();
      	}
	}
	function back(){
	    window.location.href="<c:url value="/sm/affiche.do"/>";
	}
</script>
