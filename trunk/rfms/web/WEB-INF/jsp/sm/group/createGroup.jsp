<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<webui:panel title="�½�����Ա��"  icon="../images/icon_list.gif">
<html:form action="group" onsubmit="return validateGroupForm(this)">
<input type="hidden" name="act" value="save"/>
    <webui:formTable>
      <tr>
	    <webui:input label="����Ա������" required="true">
	       <html:text property="group.name"/>
	    </webui:input>
	    <webui:input label="�����ֹ�˾" required="true">
	        <sm:orgsList inputName="orgId" orgType="1" selOrgId="${loginOrgId}" emptyChoice="true"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="����Ա������" colspan="3">
	       <html:textarea property="group.description" styleClass="wid80" rows="3"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
 <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(groupForm);" value="����"/>
 <webui:linkButton styleClass="clsButtonFace"  value="����" href="javascript:history.back()" />
</html:form>
</webui:panel>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="groupForm" />