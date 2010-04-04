<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript" src="<c:url value="/js/options.js"/>"></script>
<html:form action="opGroup"  >
<input type="hidden" value="saveGroup" name="act" />
<input type="hidden" value="<c:out value='${op.operatorId}'/>" name="opId" />
<input type="hidden" name="loginName" value="<c:out value='${operatorGroupForm.loginName}'/>" />
<input type="hidden" name="name" value="<c:out value='${operatorGroupForm.name}'/>" />
<input type="hidden" name="orgId_s" value="<c:out value='${operatorGroupForm.orgId_s}'/>" />
<input type="hidden" name="listOp_p" value="<c:out value='${currentPage_listOp}'/>" />
<webui:panel title="设置操作员组" icon="../images/icon_list.gif" width="95%">
<table width="95%">
		<tr>
			<td width="40%" align="right">
				<table>
					<tr><td>所属操作员组</td></tr>
					<tr><td>
					  <select name="groupIds"  multiple="true" id="target" style="width:150;height:200" ondblclick="checkSelect('target','source');">
				             <c:forEach items="${opGroups}" var="group">
				               <option value="<c:out value='${group.groupId}'/>"><c:out value='${group.name}'/></option>
				             </c:forEach>
					  </select>
					 </td></tr>
				</table>		
			</td>
			<td width="20%" align="center">
				  <span class=clsButtonFace><a href="#" onclick="javascript:moveAll('source','target');">全部左移</a></span><br>
				  <span class=clsButtonFace><a href="#" onclick="javascript:checkSelect('source','target');">＜＜左移</a></span><br>
				  <span class=clsButtonFace><a href="#" onclick="javascript:checkSelect('target','source');">右移＞＞</a></span><br>
                  <span class=clsButtonFace><a href="#" onclick="javascript:moveAll('target','source');">全部右移</a></span>
			
			</td>
			<td width="40%">
			    <table>
					<tr><td>可选择的操作员组</td></tr>
					<tr><td>
					  <select multiple="true" id="source" style="width:150;height:200" ondblclick="checkSelect('source','target');">
					       <c:forEach items="${availableGroups}" var="group">
					          <option value="<c:out value='${group.groupId}'/>"><c:out value='${group.name}'/></option>
					       </c:forEach>       		
					  </select>
					</td></tr>
				</table>		
			</td>
		</tr>
</table>
	 <webui:linkButton styleClass="clsButtonFace" href="javascript:selectAll(operatorGroupForm.groupIds);operatorGroupForm.submit()" value="sysadmin.button.save"/>
	 <webui:linkButton styleClass="clsButtonFace" href="javascript:back()" value="返回"/>
</webui:panel>
</html:form>
<script>
    function back(){
     location.href = "<c:url value="/sm/op.do?act=view&selectedPane=group&opId=${op.operatorId}&&loginName=${operatorGroupForm.loginName}&name=${operatorGroupForm.name}&orgId_s=${operatorGroupForm.orgId_s}&listOp_p=${currentPage_listOp}"/>"; 
   }
    function checkSelect(sourceId,targetId){
 	  source = document.getElementById(sourceId);
 	  if(source.selectedIndex<0){
      	alert("请选择");
      	return false;
      }
 	  swapSelected(sourceId,targetId );
    }   
      function moveAll(sourceId, targetId){
      var source = document.getElementById(sourceId);
      var target = document.getElementById(targetId);
      swapAll(source,target);
    } 
</script>
