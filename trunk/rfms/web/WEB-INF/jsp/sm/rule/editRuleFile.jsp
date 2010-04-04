<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="规则文件" width="95%" icon="../images/icon_list.gif">
<html:form action="ruleFile" >
<input type="hidden" name="act" value="update"/>
<input type="hidden" name="ruleId" value="<c:out value='${ruleFile.ruleId}'/>"/>
<input type="hidden" name="fileId" value="<c:out value='${ruleFile.fileId}'/>"/>
		<webui:formTable>
			<tr>
				<webui:input label="文件版本号">
				   <c:out value="${ruleFile.version}"/>
				</webui:input>
				<webui:input label="是否发布">
				   <select name="publish">
				      <option value="false">否</option>
				      <option value="true">是</option>
				   </select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="文件内容" colspan="4">
				   <html:textarea property="fileContent" styleClass="wid80" rows="25"/>
				</webui:input>
			</tr>
		</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="loadOn();ruleFileForm.submit()" value="保存"/>
	<webui:linkButton styleClass="clsButtonFace" href="#" onClick="back();" value="返回"/>
</html:form>	
</webui:panel>
<script>
    function back(){
        location.href = "<c:url value='/sm/ruleFile.do?act=list&ruleId=${ruleFile.ruleId}'/>"
    }
</script>
