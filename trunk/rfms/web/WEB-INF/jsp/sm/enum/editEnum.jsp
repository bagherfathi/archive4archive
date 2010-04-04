<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
    function onDisable(){
         if (confirm("<bean:message key='msg.confirm.disable.enumdata' />")) {
            document.enumForm.act.value="disable";
            document.enumForm.submit();
        }
    }
    
    function onEnable(){
         if (confirm("<bean:message key='msg.confirm.enable.enumdata' />")) {
            document.enumForm.act.value="enable";
            document.enumForm.submit();
        }
    }
    
    function onReturn(){
        window.location.href='<c:url value="/sm/enumCategory.do?act=edit&categoryId=${enumCategory.categoryId}"/>';
    }
    
    function onReturn1(){
        window.location.href='<c:url value="/sm/enumCategory.do?act=edit&categoryId=${enumData.categoryId}"/>';
    }
    
    function ParamaterNode(order,label,value,linkValue){
		this.label = label;
		this.value = value;
		this.linkValue = linkValue ;
		this.order = order;
    }
    
   
    function addNodeRow(){
        var order = document.getElementById("entryOrder").value;
		var label =document.getElementById("entryLabel").value;
		var value = document.getElementById("entryValue").value;
		var linkValue = document.getElementById("entryLinkValue").value;
		
		if(order == ''){
	        order = '1';
		}
		
		var regexp=/^([1-9]{1}\d{0,1})$/;
		if(!regexp.test(order)){
		    alert("��������ȷ��˳��ţ�˳���ȡֵ��Χ1-99");
		    return;
		}
				
		if(label == ''){
			alert("����дֵ����");
			return ;
		}
		
		if(value == ''){
			alert("����дֵ");
			return ;
		}
		
		if(realLength(label) > 64){
		    alert("ֵ�������ܳ���64���ַ�");
		    return;
		}
		
		if(realLength(value) > 64){
		    alert("ֵ���ܳ���64���ַ�");
		    return;
		}
		
		if(realLength(linkValue) > 64){
		    alert("����ֵ���ܳ���64���ַ�");
		    return;
		}
		
		 var reg = /^[\w\u4e00-\u9fa5\��\��\(\)\-\s]*$/;
		 if(!reg.test(label)){
		     //alert("ֵ������Ч��ֻ��Ϊ���ģ���Сд��ĸ�����֡��̺����Լ��»���")
		     //return;
		 }
		 
		 if(!reg.test(value)){
		     alert("ֵ��Ч��ֻ��Ϊ���ģ���Сд��ĸ�����֡��̺����Լ��»���")
		     return;
		 }
		 
		 if(!reg.test(linkValue)){
		     alert("����ֵ��Ч��ֻ��Ϊ���ģ���Сд��ĸ�������Լ��»���")
		     return;
		 }
		 
		 var existNode = document.getElementById("entryId-" + label);
		 if(null != existNode){
		     alert("ֵ�����ظ����������ظ���ֵ����");
		     return;
		 }
		 
		 existNode = document.getElementById("entryNode-" + value);
		 
		 if(null != existNode){
		     alert("ֵ�ظ����������ظ���ֵ");
		     return;
		 }
		 
		 
		var aNode = new ParamaterNode(order,label,value,linkValue);
		document.getElementById("entryOrder").value='';
		document.getElementById("entryLabel").value='';
		document.getElementById("entryValue").value='';
		document.getElementById("entryLinkValue").value='';
		addRow('paramTable',aNode);
	 }
	 
	function addRow(name,aNode){
		var aTable = document.getElementById(name);
		var lastRow = aTable.rows.length;
		var iteration = lastRow;
	    var row = aTable.insertRow(lastRow-1);
	    row.id = ("row_"+aNode.label);
	    var column = row.insertCell(0);
	    
	    aInput =  document.createElement('input');
	    aInput.setAttribute("type", "hidden");
	    aInput.setAttribute("id", "entryId-" + aNode.label);
	    column.appendChild(aInput);
	    
	    var textNode = document.createElement('input');
	    textNode.setAttribute("type", "text");
	    textNode.setAttribute("name", "entryNode[" + aNode.label + "#" + aNode.value + "#" + aNode.linkValue + "]");	   
        textNode.setAttribute("value", aNode.order);
	    textNode.setAttribute("id", "entryNode-" + aNode.value );
	    textNode.setAttribute("onblur",Function("validOrder('" +  textNode.getAttribute('id') + "')"));
	    textNode.setAttribute("size", "9");
	    column.appendChild(textNode);
	    column = row.insertCell(1);
	    var bTextNode = document.createTextNode(aNode.label);
	    column.appendChild(bTextNode);
	    column = row.insertCell(2);

	    var bTextNode = document.createTextNode(aNode.value);
	    column.appendChild(bTextNode);
	    column = row.insertCell(3);
	    
	    bTextNode = document.createTextNode(aNode.linkValue);
	    column.appendChild(bTextNode);
	    column = row.insertCell(4);
	    
	    aImg =  document.createElement('img');
	    aImg.setAttribute("src","../images/clearicon.gif");
	    aImg.style.cursor="hand";
	    aImg.setAttribute("title","����ɾ����ֵ");
	    aImg.setAttribute("onclick", Function("removeRow('" + name + "','" + row.id + "')"));
	    column.appendChild(aImg);
	    
	}
	
	 function removeRow(name,rowId){
	    var result=confirm('ȷ��Ҫɾ��������?');
	    if(result){
        	var aTable = document.getElementById(name);
			var aRow = document.getElementById(rowId);
			aTable.deleteRow(aRow.rowIndex);
	    }
    }
    
    function realLength(str)
    {
        return str.replace(/[^\x00-\xff]/g,"**").length;
    }
    
    function openEnumTree(){
  	    flag = window.open ("<c:url value='/sm/dialog.do?act=selLinkValue'/>","tree",
  	       'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=yes') 
  	    flag.focus();
  	}
  	
  	function validOrder(id){
  	    input = document.getElementById(id);
		var order = input.value;
		
		var regexp=/^([1-9]{1}\d{0,1})$/;
		if(!regexp.test(order)){
		    alert("��������ȷ��˳��ţ�˳���ȡֵ��Χ1-99");
		    return;
		}
	}
</script>

<sm:query var="entries" beanName="enumManager" methodName="findEntriesByEnum">
    <sm:param value="${enumForm.enumData.enumId}" type="java.lang.Long"/>
</sm:query>

<html:form styleId="enumForm" action="/enum" method="post" onsubmit="return validateEnumForm(this)">
<input type="hidden" value="save" name="act" />
<input type="hidden" value="<c:out value='${enumData.enumId}'/>" name="enumId"/>
<input type="hidden" name="validationKey" value="enumForm"/>
<c:if test="${!empty enumData.enumId}">
<input type="hidden" value="<c:out value='${enumData.categoryId}'/>" name="categoryId"/>
</c:if>
<c:if test="${empty enumData.enumId}">
<input type="hidden" value="<c:out value='${enumCategory.categoryId}'/>" name="categoryId"/>
</c:if>

<webui:panel title="������Ϣ" icon="../images/icon_list.gif" width="95%">
    <table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
      <tr>
	    <webui:input label="��������" required="true">
	      <html:text property="enumData.enumName" size="25"/>
	    </webui:input>
		<webui:input label="���ݴ���" required="true">
		  <html:text property="enumData.enumCode" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="������Ϣ" colspan="4">
	    	<html:textarea property="enumData.enumDesc" styleClass="wid80" rows="3"/>
	    </webui:input>
	  </tr>
    </table>
</webui:panel>
</br>
<table width="95%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td class="table_top_1"></td>
          <td class="table_top_2">
          <img src="../images/icon_search.gif" border="0" align="absmiddle"/> ������Ŀ
          </td>
          <td class="table_top_3"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td  align="center" width="100%" class="table_middle">
    <!-- panel body begin-->
	<!--form table begin -->
    <table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
    <tr>
         <webui:input label="ֵ����"> 
		    <input type="text" id="entryLabel" name="entryLabel" value="" size="25"/>
		 </webui:input> 
		 <webui:input label="ֵ">   				   
		   <input type="text" id="entryValue" name="entryValue" value="" size="25"/>
		 </webui:input> 
	  </tr>
	  <tr>
	     <webui:input label="˳���">   				   
		   <input type="text" id="entryValue" name="entryOrder" value="" size="25"/>
		 </webui:input>
		  
		 <webui:input label="����ֵ">   				   
		   <input type="text" id="entryLinkValue" name="entryLinkValue" value="" size="25"/>
		   <img src="<c:url value='/images/icon_sel.gif'/>" title="���ѡ�������ϵͳ����" onclick="openEnumTree();"/>&nbsp;&nbsp;&nbsp;&nbsp;
		 </webui:input>
	  </tr>
    </table>
    
    <table width="95%" border="0" cellspacing="2" cellpadding="2">
    <tr>
        <td align="right">
            <span class="clsButtonFace"><a href="#" title="������Ӵ�ֵ" onclick="addNodeRow()">���</a></span>
        </td>
    </tr>
    </table>
    </br>
    <div class="eXtremeTable" >
    <table id="paramTable" border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="95%" >	
    	<thead>
	    <tr>
	        <td width="10%" class="tableHeader">˳��</td>
		    <td width="30%" class="tableHeader">����</td>
		    <td width="20%" class="tableHeader">ֵ</td>
		    <td width="20%" class="tableHeader">����ֵ</td>
		    <td width="20%" class="tableHeader">����</td>
		</tr>	
		</thead>
		<tbody class="tableBody" >
	    <tr>
	      <td colspan="3"></td>
	    </tr> 
	    </tbody>
	</table>
	</div>
	<c:if test="${!empty entries}">	
        <c:forEach items="${entries}" var="enumEntry" >	
        <script>
            var order = "<c:out value='${enumEntry.order}'/>";
            var label = "<c:out value='${enumEntry.label}'/>";
	        var value = "<c:out value='${enumEntry.value}'/>";
	        var linkValue = "<c:out value='${enumEntry.linkValue}'/>";
            var aNode = new ParamaterNode(order,label,value,linkValue);
	        addRow('paramTable',aNode);
        </script>	  
	    </c:forEach>
	</c:if>	
	
	</br>
	<table width="95%" border="0" cellspacing="2" cellpadding="2">
    <tr>
        <td align="right">
            <security:checkPermission resourceKey="SM_SAVE_ENUM_DATA">
            <security:success>
                <span class="clsButtonFace"><a href="javascript:submitForm(enumForm);">����</a></span>
            </security:success>
            </security:checkPermission>
            
            <c:if test="${!empty enumData.enumId}">
              <c:if test="${enumData.status == 0}">
              <security:checkPermission resourceKey="SM_DISABLE_ENUM_DATA">
              <security:success>
                <span class="clsButtonFace"><a href="#" onClick="javascript:onDisable();">����</a></span>
	  	      </security:success>
	          </security:checkPermission>
	        </c:if>
            <c:if test="${enumData.status == 1}">
            <security:checkPermission resourceKey="SM_ENABLE_ENUM_DATA">
            <security:success>
              <span class="clsButtonFace"><a href="#" onClick="javascript:onEnable();">����</a></span>
	  	    </security:success>
	        </security:checkPermission>
            </c:if>
            <span class="clsButtonFace"><a href="#" onClick="javascript:onReturn1();">����</a></span>
          </c:if>
          <c:if test="${empty enumData.enumId}">
            <span class="clsButtonFace"><a href="#" onClick="javascript:onReturn();">����</a></span>
          </c:if>
        </td>
    </tr>
    </table>
    
    </td>
  </tr>
  <tr> 
    <td colspan="3"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td class="table_bottom_1"></td>
          <td class="table_bottom_2"><img src="javascript:false" width="1" height="1"></td>
          <td class="table_bottom_3"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>
<c:choose>
<c:when test="${param.flag == 'add' }">
      <script>
      parent.leftFrame.tree.insertNewItem("category_<c:out value="${enumData.categoryId}"/>","enum_<c:out value="${enumData.enumId}"/>","<c:out value="${enumData.enumName}"/>",0,"book.gif","books_open.gif","books_close.gif","SELECT");
      </script>
</c:when>
<c:when test="${param.flag == 'update' }">
      <script>
      var tree = parent.leftFrame.tree ;
      tree.setItemText("enum_<c:out value="${enumData.enumId}"/>","<c:out value="${enumData.enumName}"/><c:if test="${enumData.status == 1}">(����)</c:if>");
      </script>
</c:when>
<c:when test="${empty param.flag }">
      <script>
      var tree = parent.leftFrame.tree ;
      tree.selectItem("enum_<c:out value="${enumData.enumId}"/>",false);
      tree.openItem("enum_<c:out value="${enumData.enumId}"/>");
      </script>
</c:when>  
</c:choose>    
<html:javascript dynamicJavascript="true" staticJavascript="false" formName="enumForm" />


