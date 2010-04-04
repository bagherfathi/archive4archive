<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html:form action="org" onsubmit="return validateOrgForm(this);">
	  <html:hidden property="act" value="save"/>
	  <input type="hidden" name="pid" value="<c:out value='${parentOrg.orgId}'/>"/>
      <webui:panel title="新增组织" width="95%" icon="../images/icon_list.gif">
			<webui:formTable>
			<tr>
				<webui:input label="组织名称" required="true">
				   <html:text property="org.name" size="25" />
				</webui:input>
				<webui:input label="组织类型" required="true">
				    <sm:orgType orgId="${parentOrg.orgId}" var="types"/>
					<select name="org.type">
					  <option value="">请选择</options>
		              <c:forEach items="${types}" var="type">
		                  <option value="<c:out value="${type}"/>"><webui:lookup code="org_type@SM_ORGANIZATION" value="${type}"/></option>
		              </c:forEach>
		            </select>
				</webui:input>
			</tr>
			<tr>
			    <webui:input label="父组织" required="true">
					<c:out value='${parentOrg.name}'/>
				</webui:input>
			<%--	<webui:input label="独立结算" required="true">
				    <html:select property="org.selfBalance">
				    	<html:optionsCollection name="enumSet" property="element(self_balance@SM_ORGANIZATION)"/>
		            </html:select>
				</webui:input>
			--%>
			    <webui:input label="组织代码" required="true" >
			   	<html:text property="org.code" size="25"/>
			   </webui:input>
			</tr>
			<tr>
				<webui:input label="描述" colspan="4">
					<html:textarea  property="org.desc" styleClass="wid80" rows="3"/>
				</webui:input>
			</tr>
			</webui:formTable>
			<br/>
			<webui:formTable>
			<tr>
				<webui:input label="联系人">
					<html:text property="org.contact.name" size="25"/>
				</webui:input>
				<webui:input label="移动电话">
					<html:text  property="org.contact.mobilePhone" size="25"/>
				</webui:input>	
			</tr>
			<tr>
				<webui:input label="邮政编码">
					<html:text property="org.contact.postCode" size="25"/>
				</webui:input>
				<webui:input label="联系电话" >
					<html:text property="org.contact.telephone" size="25"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="通信地址" colspan="4">
					<html:text property="org.contact.address" size="60"/>
				</webui:input>
			</tr>
			</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(orgForm);" value="保存"/>
        <webui:linkButton styleClass="clsButtonFace" href="javascript:history.back();" value="返回"/>
	</webui:panel>
	</html:form>	
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="orgForm" /> 