<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function onSearch(){
        document.ticketForm.act.value="searchPos";
        document.ticketForm.submit();
    }
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
    function checkEnter(){
      if(window.event.keyCode==13){
	    loadOn();
	    document.forms.ticketForm.submit();
     }
    }
    document.body.onkeypress= checkEnter;
</script>
<html:form action="/ticket" method="post">
	<input type="hidden" value="searchPos" name="act" />
	<html:hidden property="id"/>
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
	<%--
	<webui:panel title="title.rfms.merchant.search" icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="�̻�����">
					<input type="text" name="merchantName" size="25" />
				</webui:input>
				<webui:input label="�ŵ�����">
					<input type="text" name="branchName" size="25" />
				</webui:input>
			</tr>
			<tr>
				<webui:input label="POS���" colspan="3">
					<input type="text" name="posCode" size="25" />
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:onSearch();" value="sysadmin.button.search" />
	</webui:panel>

	<br />

	<webui:panel title="POS�б�" icon="../images/icon_list.gif">
		<webui:table items="poss"
			action="${pageContext.request.contextPath}/rfms/ticket.do"
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			title="�ֽ�ȯ�б�" var="item" width="95%" showExports="true"
			showPagination="true" showStatusBar="true" showTitle="false"
			sortable="false" filterable="false" autoIncludeParameters="false"
			tableId="ticketForm" form="ticketForm">
			<webui:row>
			    <webui:column property="selec" title="��ѡ"
					styleClass="td_normal">
					<input type="checkbox" name="ids" value="<c:out value='${item.pos.sysPosCode }'/>"/>
				</webui:column>
				<webui:column property="merchant.merchantName" title="�̻�����"
					styleClass="td_normal">
				</webui:column>
				<webui:column sortable="true" property="branch.branchName" title="�ŵ�����"
					styleClass="td_normal" />
				<webui:column filterable="false" cell="date" format="yyyy-MM-dd"
					property="bind.createDate" title="��ʱ��">
				</webui:column>
			<webui:column property="pos.sysPosCode" title="label.rfms.merchant_pos.sys_pos_code" styleClass="td_normal"/>
			<webui:column  property="pos.owner" title="label.rfms.merchant_pos.owner">
			<webui:lookup code="owner@RFMS_MERCHANT_POS" value="${item.pos.posType}" />
			</webui:column>
			<webui:column property="pos.posType" title="label.rfms.merchant_pos.pos_type">
			<webui:lookup code="pos_type@RFMS_MERCHANT_POS" value="${item.pos.posType}" />
			</webui:column>
			</webui:row>
		</webui:table>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:onsave();" value="sysadmin.button.save" />
	</webui:panel>
	--%>


<table>
  <tr>
    <td>
	<p><a href="javascript: mytree.openAll();">ȫ��չ��</a> | <a href="javascript: mytree.closeAll();">ȫ���ر�</a></p>
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

		//-->
	</script>

</td>
  </tr>
</table>
 <webui:linkButton styleClass="clsButtonFace" href="javascript:onsave();" value="sysadmin.button.save" />
	</webui:panel>
</html:form>
