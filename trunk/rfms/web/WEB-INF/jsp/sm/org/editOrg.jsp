<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<html:form action="org.do" method="post" styleId="orgBaseForm" onsubmit="return validateOrgBaseForm(this)">
<input type="hidden" name="act" value="update"/>
<input type="hidden" name="orgId" value="<c:out value='${org.orgId}'/>"/>
<input type="hidden" name="validationKey" value="orgBaseForm"/>
<input type="hidden" name="pid" value="<c:out value='${org.parent.orgId}'/>"/>
	<webui:panel title="��֯����" icon="../images/icon_list.gif">
		<webui:formTable>
			<tr>
				<webui:input label="��֯����" required="true">
					<html:text property='org.name' size="25"/>
				</webui:input>
				<webui:input label="��֯����" >
				    <webui:lookup code="org_type@SM_ORGANIZATION" value="${org.type}"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="����֯" >
					<c:out value='${org.parent.orgName}'/>
				</webui:input>
			<%-- 	<webui:input label="��������" required="true">
					<html:select property="org.selfBalance">
						<html:optionsCollection name="enumSet" property="element(self_balance@SM_ORGANIZATION)"/>
					</html:select>
				</webui:input>
		--%>		
				<webui:input label="��֯����" >
					<c:out value='${org.code}'/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="����" colspan="4">
					<html:textarea property="org.desc" styleClass="wid80" rows="4" />
				</webui:input>
			</tr>
	      </webui:formTable>
	      <br/>
	     <webui:formTable>
			<tr>
				<webui:input label="��ϵ��">
					<html:text property="org.contact.name" size="25"/>
				</webui:input>
				<webui:input label="�ƶ��绰">
					<html:text  property="org.contact.mobilePhone" size="25"/>
				</webui:input>	
			</tr>
			<tr>
				<webui:input label="��������">
					<html:text property="org.contact.postCode" size="25"/>
				</webui:input>
				<webui:input label="��ϵ�绰" >
					<html:text property="org.contact.telephone" size="25"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="ͨ�ŵ�ַ" colspan="4">
					<html:text property="org.contact.address" size="60"/>
				</webui:input>
			</tr>
			</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(orgForm);" value="����"/>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="����"/>
	</webui:panel>	
</html:form>
<script> 
   function back(){
     location.href="<c:url value="/sm/org.do?act=view&orgId=${org.orgId}"/>";
   }
   <c:if test="${empty param.flag}">
   if(parent.leftFrame.document.readyState == "complete"){
     var tree  = parent.leftFrame.tree;
     tree.selectItem("<c:out value="${org.orgId}"/>",false);
     tree.openItem("<c:out value="${org.orgId}"/>");
   }
   </c:if>
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="orgBaseForm" />