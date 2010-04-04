<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
    <webui:formTable width="100%">
      <tr>
	    <webui:input label="sysadmin.label.group.name">
	       <c:out value='${group.name}'/>
	    </webui:input>
	    <webui:input label="sysadmin.label.group.org">
	       <c:out value='${group.ownerOrg.orgName}'/>
	    </webui:input>
	  </tr>
	  <tr>
	    <webui:input label="sysadmin.label.group.desc" colspan="4">
	       <c:out value='${group.description}'/>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <table width="100%" border="0" cellspacing="2" cellpadding="2"><tr>
       <td align="right">
   <security:checkPermission resourceKey="SM_EDIT_GROUP">
      <security:success>
      <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:goURL('edit');" value="sysadmin.button.edit"/>
     </security:success>
    </security:checkPermission>
    <webui:linkButton standOnly="true" styleClass="clsButtonFace" href="javascript:back();" value="sysadmin.button.return"/>    
   </td></tr></table>