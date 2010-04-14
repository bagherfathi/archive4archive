<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>" type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>

<html:form styleId="ticketForm" action="/ticket.do" method="post"  onsubmit="return validateTicketForm(this);">
<input type="hidden" value="tosearchPos" name="act"/>
<webui:panel title="�Ż�ȯ��POS��" width="95%"  icon="/images/icon_list.gif">    
	   <webui:formTable>
	<tr>
		<webui:input label="label.rfms.ticket.ticket_name">
			<html:hidden property="baseEntity.sendCount" />
			<html:hidden property="baseEntity.useCount" />
			<html:hidden property="id"/>
			<input type="hidden" name="bindId"/>
			<html:hidden property="baseEntity.ticketId" />
			<input type="hidden" name="targetMemberTypes" id="targetMemberTypes" />
			<input type="hidden" name="baseEntity.merchantId"
				value="<c:out value='${ticketForm.currentUser.merchantCode}'/>" />
			<input type="hidden" name="baseEntity.operatorId"
				value="<c:out value='${ticketForm.currentUser.operatorId}'/>" />
			<c:out value="${ ticketForm.baseEntity.ticketName}"></c:out>
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketSerial" >
			<c:out value="${ticketForm.baseEntity.ticketSerial }"></c:out>
		</webui:input>
	</tr>

	<tr>
		<webui:input label="label.rfms.ticket.type">
			 <webui:lookup code="TYPE@RFMS_CARD" value="${ticketForm.baseEntity.type}" />
		</webui:input>
		<webui:input label="label.rfms.ticket.ticketCount">
				<c:out value="${ticketForm.baseEntity.parValue }"></c:out>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.parValue">
				<c:out value="${ticketForm.baseEntity.parValue }"></c:out>
		</webui:input>
		<webui:input label="label.rfms.ticket.parZhekou">
				<c:out value="${ticketForm.baseEntity.parZhekou }"></c:out>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.beginDate">
				<c:out value="${ticketForm.baseEntity.beginDate }"></c:out>
		</webui:input>
		<webui:input label="label.rfms.ticket.endDate">
				<c:out value="${ticketForm.baseEntity.endDate }"></c:out>
		</webui:input>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.status">
			<webui:lookup code="STATUS@RFMS_CARD" value="${ticketForm.baseEntity.status}" />
		</webui:input>
		<webui:input label="label.ticket.ohterInfo" >
			<c:out value="${ ticketForm.baseEntity.ohterInfo}"></c:out>
		</webui:input>

		<tr>
			<webui:input label="label.rfms.ticket.targetMemberType" colspan="3">
					<webui:lookup code="CODE_TYPE@RFMS_CARD" value="${ticketForm.baseEntity.targetMemberType}" />
			</webui:input>
		</tr>
	</tr>
	<tr>
		<webui:input label="label.rfms.ticket.useRule" colspan="3">
				<c:out value="${ ticketForm.baseEntity.useRule}"></c:out>
		</webui:input>
	</tr>
</webui:formTable>
</webui:panel>
<br />
	<webui:panel title="�Ѿ���POS��" icon="../images/icon_list.gif">
		<%--
		<webui:table items="posBindDTOs"
			action="${pageContext.request.contextPath}/rfms/ticket.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="�ֽ�ȯ�б�" var="item" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketForm" form="ticketForm">
			<webui:row>
				<webui:column property="merchant.merchantName" title="�̻�����"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="branch.branchName" title="�ŵ�����"
					styleClass="td_normal" />
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="bind.createDate" title="��ʱ��">
				</webui:column>
			<webui:column property="pos.sysPosCode" title="label.rfms.merchant_pos.sys_pos_code" styleClass="td_normal"/>
			<webui:column  property="item.pos.owner" title="label.rfms.merchant_pos.owner">
			<webui:lookup code="owner@RFMS_MERCHANT_POS" value="${item.pos.posType}" />
			</webui:column>
			<webui:column property="pos.posType" title="label.rfms.merchant_pos.pos_type">
			<webui:lookup code="pos_type@RFMS_MERCHANT_POS" value="${item.pos.posType}" />
			</webui:column>
            <webui:column property="posType" title="����">
			  <a href="javascript:delbind(ticketForm,<c:out value='${item.bind.bindId }'/>);">ɾ��</a>
			</webui:column>
			</webui:row>
		</webui:table>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(ticketForm);" value="��POS��" />
    <webui:linkButton styleClass="clsButtonFace" href="javascript:submitForm(ticketForm);" value="����" />
    --%>
    
<table>
  <tr>
    <td>
	<%--
	<p><a href="javascript: mytree.openAll();">ȫ��չ��</a> | <a href="javascript: mytree.closeAll();">ȫ���ر�</a></p>
	--%>
	<script type="text/javascript">
		<!--
    //�ڵ�ĺ���node��9��������������Ҫȫ����������ֻ��������Ĭ��Ϊǰ�漸��
		mytree = new dTree('mytree','','ticketForm');
		mytree.config.useCheckbox = true;  //�����и�ѡ��
		mytree.add(0,-1,'���ڵ�');
		
<c:forEach items="${posTree }" var="posnode">
  mytree.add('<c:out value="${posnode.nodeId }"></c:out>','<c:out value="${posnode.parentId }"></c:out>','<c:out value="${posnode.name }"></c:out>');
</c:forEach>
		document.write(mytree);



function onsave(){
        var ids=document.getElementsByName("ids");
        var count=0;
        for(var x=0;x<ids.length;x++){
            if(ids[x].checked==true){
              count++;
            }
        }
        if(count==0){
          alert("��ѡ��POS");
          return;
        }
        document.ticketForm.act.value="bind";
        document.ticketForm.submit();
    }
    
<c:forEach items="${binds }" var="b">
   ids=document.getElementsByName("ids");   
   for(var i=0;i<ids.length;i++){
     if(ids[i].value=="P_<c:out value='${b.posCode}'/>"){
        ids[i].checked=true;
        var tempId=ids[i].id;
        var tarr=tempId.split("-");
        document.getElementById(tarr[0]+"-").checked=true;
        document.getElementById(tarr[0]+"-"+tarr[1]+"-").checked=true;
     }
   }
</c:forEach>

		//-->
	</script>

</td>
  </tr>
</table>
<webui:linkButton styleClass="clsButtonFace" href="javascript:onsave();" value="sysadmin.button.save" />&nbsp;
<webui:linkButton styleClass="clsButtonFace" href="javascript:history.go(-1);" value="sysadmin.button.return" />
	</webui:panel>
	
</html:form>
<html:javascript dynamicJavascript="true" staticJavascript="false"
	formName="ticketForm" />
<script>
  function searchEdit(aform){
   	submitForm(aform);
  }
  function delbind(aform,bindId){
    aform.act.value="bindDelete";
    aform.bindId.value=bindId;
   	aform.submit();
  }
 function submitForm(aform){
		  aform.act.value="tosearchPos";
		  loadOn();
		  aform.submit();
 }

</script>