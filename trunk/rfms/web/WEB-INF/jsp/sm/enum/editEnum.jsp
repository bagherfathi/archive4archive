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
		    alert("请输入正确的顺序号，顺序号取值范围1-99");
		    return;
		}
				
		if(label == ''){
			alert("请填写值描述");
			return ;
		}
		
		if(value == ''){
			alert("请填写值");
			return ;
		}
		
		if(realLength(label) > 64){
		    alert("值描述不能超过64个字符");
		    return;
		}
		
		if(realLength(value) > 64){
		    alert("值不能超过64个字符");
		    return;
		}
		
		if(realLength(linkValue) > 64){
		    alert("关联值不能超过64个字符");
		    return;
		}
		
		 var reg = /^[\w\u4e00-\u9fa5\（\）\(\)\-\s]*$/;
		 if(!reg.test(label)){
		     //alert("值描述无效，只能为中文，大小写字母，数字、短横线以及下划线")
		     //return;
		 }
		 
		 if(!reg.test(value)){
		     alert("值无效，只能为中文，大小写字母，数字、短横线以及下划线")
		     return;
		 }
		 
		 if(!reg.test(linkValue)){
		     alert("关联值无效，只能为中文，大小写字母，数字以及下划线")
		     return;
		 }
		 
		 var existNode = document.getElementById("entryId-" + label);
		 if(null != existNode){
		     alert("值描述重复，不允许重复的值描述");
		     return;
		 }
		 
		 existNode = document.getElementById("entryNode-" + value);
		 
		 if(null != existNode){
		     alert("值重复，不允许重复的值");
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
	    aImg.setAttribute("title","单击删除此值");
	    aImg.setAttribute("onclick", Function("removeRow('" + name + "','" + row.id + "')"));
	    column.appendChild(aImg);
	    
	}
	
	 function removeRow(name,rowId){
	    var result=confirm('确定要删除此项吗?');
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
		    alert("请输入正确的顺序号，顺序号取值范围1-99");
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

<webui:panel title="基本信息" icon="../images/icon_list.gif" width="95%">
    <table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
      <tr>
	    <webui:input label="数据名称" required="true">
	      <html:text property="enumData.enumName" size="25"/>
	    </webui:input>
		<webui:input label="数据代码" required="true">
		  <html:text property="enumData.enumCode" size="25"/>
		</webui:input>
	  </tr>
	  <tr>
	    <webui:input label="描述信息" colspan="4">
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
          <img src="../images/icon_search.gif" border="0" align="absmiddle"/> 数据条目
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
         <webui:input label="值描述"> 
		    <input type="text" id="entryLabel" name="entryLabel" value="" size="25"/>
		 </webui:input> 
		 <webui:input label="值">   				   
		   <input type="text" id="entryValue" name="entryValue" value="" size="25"/>
		 </webui:input> 
	  </tr>
	  <tr>
	     <webui:input label="顺序号">   				   
		   <input type="text" id="entryValue" name="entryOrder" value="" size="25"/>
		 </webui:input>
		  
		 <webui:input label="关联值">   				   
		   <input type="text" id="entryLinkValue" name="entryLinkValue" value="" size="25"/>
		   <img src="<c:url value='/images/icon_sel.gif'/>" title="点击选择关联的系统数据" onclick="openEnumTree();"/>&nbsp;&nbsp;&nbsp;&nbsp;
		 </webui:input>
	  </tr>
    </table>
    
    <table width="95%" border="0" cellspacing="2" cellpadding="2">
    <tr>
        <td align="right">
            <span class="clsButtonFace"><a href="#" title="单击添加此值" onclick="addNodeRow()">添加</a></span>
        </td>
    </tr>
    </table>
    </br>
    <div class="eXtremeTable" >
    <table id="paramTable" border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="95%" >	
    	<thead>
	    <tr>
	        <td width="10%" class="tableHeader">顺序</td>
		    <td width="30%" class="tableHeader">描述</td>
		    <td width="20%" class="tableHeader">值</td>
		    <td width="20%" class="tableHeader">关联值</td>
		    <td width="20%" class="tableHeader">操作</td>
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
                <span class="clsButtonFace"><a href="javascript:submitForm(enumForm);">保存</a></span>
            </security:success>
            </security:checkPermission>
            
            <c:if test="${!empty enumData.enumId}">
              <c:if test="${enumData.status == 0}">
              <security:checkPermission resourceKey="SM_DISABLE_ENUM_DATA">
              <security:success>
                <span class="clsButtonFace"><a href="#" onClick="javascript:onDisable();">禁用</a></span>
	  	      </security:success>
	          </security:checkPermission>
	        </c:if>
            <c:if test="${enumData.status == 1}">
            <security:checkPermission resourceKey="SM_ENABLE_ENUM_DATA">
            <security:success>
              <span class="clsButtonFace"><a href="#" onClick="javascript:onEnable();">启用</a></span>
	  	    </security:success>
	        </security:checkPermission>
            </c:if>
            <span class="clsButtonFace"><a href="#" onClick="javascript:onReturn1();">返回</a></span>
          </c:if>
          <c:if test="${empty enumData.enumId}">
            <span class="clsButtonFace"><a href="#" onClick="javascript:onReturn();">返回</a></span>
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
      tree.setItemText("enum_<c:out value="${enumData.enumId}"/>","<c:out value="${enumData.enumName}"/><c:if test="${enumData.status == 1}">(禁用)</c:if>");
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


