<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
</script>
<html:form action="/refreshCache" method="post">
<input type="hidden" value="refresh" name="act"/>
<webui:panel title="更新前台缓存数据" icon="../images/icon_search.gif" width="95%">
	<webui:formTable>
      <tr>
	    <webui:input label="选择数据类型" colspan="4">
	       <html:select property="cacheCode">
		       <html:optionsCollection property="dataType"/>
		  </html:select>
	    </webui:input>
	  </tr>
    </webui:formTable>
   <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();refreshCacheForm.submit();" value="刷新"/>
</webui:panel>
</html:form>
