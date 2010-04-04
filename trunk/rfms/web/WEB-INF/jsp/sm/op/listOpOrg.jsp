<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<webui:panel title="����Ա����֯"  icon="../images/icon_list.gif">
	<div align="left">
       ��ǰ��������: ����Ա<a href="<c:url value="/sm/op.do?act=view&opId=${op.operatorId}"/>"><c:out value='${op.name}'/></a>
    </div>

	<webui:table
		title="��֯�б�"
		items="accessOrgs" tableId="orgs"
		action="${pageContext.request.contextPath}/sm/opOrg.do"
		imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
		width="95%"
		var="org"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  property="loginName" title="��֯����">
			      <c:out value='${org.name}'/>
			</webui:column>
			<webui:column  property="code" title="��֯����"/>
			<webui:column   property="parent" title="�ϼ���֯">
			     <c:out value='${org.parent.name}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	
	<webui:table
		title="�̳е���֯"
		items="orgInGroups" tableId="extendOrgs"
		action="${pageContext.request.contextPath}/sm/opOrg.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		var="orgInGroup"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="true"
		filterable="false"
		sortable="false"
		>
		<webui:row>
			<webui:column  property="name" title="��֯����">
			      <c:out value='${orgInGroup.org.name}'/>
			</webui:column>
			<webui:column  property="orgInGroup.org.code" title="��֯����"/>
			<webui:column   property="parent" title="�ϼ���֯">
			       <c:out value='${orgInGroup.org.parent.name}'/>
			</webui:column>
			<webui:column  property="group" title="����Ա��">
			       <c:out value='${orgInGroup.group.name}'/>
			</webui:column>
		</webui:row>
	</webui:table>
	<br/>
	  <security:checkPermission resourceKey="SM_CONFIG_OP_ORG">
      <security:success>
         <webui:linkButton styleClass="clsButtonFace" href="javascript:configOrg();" value="���ÿɷ�����֯"/>
	   </security:success>
      </security:checkPermission>
	</webui:panel>     
	<script>
	   function configOrg(){
	       location.href = "<c:url value="/sm/opOrg.do?act=configOrg&opId=${op.operatorId}"/>"
	   }
	</script>
