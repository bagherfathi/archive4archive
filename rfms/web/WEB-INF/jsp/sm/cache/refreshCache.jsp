<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
</script>
<html:form action="/refreshCache" method="post">
<input type="hidden" value="refresh" name="act"/>
<webui:panel title="����ǰ̨��������" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="ѡ����������" colspan="4">
	       <html:select property="cacheCode">
		       <html:optionsCollection property="dataType"/>
		  </html:select>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();refreshCacheForm.submit();" value="ˢ��"/>
</webui:panel>
</html:form>
