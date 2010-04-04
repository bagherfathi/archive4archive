<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<webui:panel title="�༭����Ա��"  icon="../images/icon_list.gif">
<html:form action="group" onsubmit="return validateGroupForm(this)">
<input type="hidden" name="act" value="update"/>
<html:hidden property="group.groupId"/>
    <webui:formTable>
      <tr>
	    <webui:input label="����Ա������" required="true">
	       <html:text property="group.name" size="25"/>
	    </webui:input>
	    <webui:input label="�����ֹ�˾" required="true">
	        <sm:orgsList inputName="orgId" orgType="1" selOrgId="${groupForm.group.ownerOrgId}"/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="����Ա������" colspan="3">
	        <html:textarea property="group.description" styleClass="wid80" rows="3"/>
	    </webui:input>
	  </tr>
    </webui:formTable>
    <br/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(groupForm);" value="����"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:back();" value="����"/>
</html:form>
</webui:panel>
<script>
     function back(){
        <c:if test="${ empty param.from }">
        location.href = "<c:url value="/sm/group.do?act=view&id=${group.groupId}"/>";
        </c:if>
        <c:if test="${ !empty param.from }">
        location.href = "<c:url value="/sm/group.do"/>";
        </c:if>
     }
</script>
<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="groupForm" />