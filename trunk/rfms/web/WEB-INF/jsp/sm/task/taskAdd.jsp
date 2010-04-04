<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script language="javascript">
    function back(){
      location.href = "<c:url value="/sm/task.do"/>";
      
    }
    
    function ParamaterNode(id,name,desc,type){
		this.id = id;
		this.name = name;
		this.desc = desc ;
		this.type = type;
   }
   
    function addNodeRow(){
		var name =document.getElementById("nameId").value;
		var id = document.getElementById("nameId").value;
		if(id == ''){
			alert("请填写参数名");
			return ;
		}
		var desc = document.getElementById("descId").value;
		if(desc == ''){
			alert("请填写参数说明");
			return ;
		}
		
		var type = document.getElementById("typeId").value;		
		if(type == 0){
			alert("请选择参数类型");
			return ;
		}
		
		if(realLength(id)>64){
		   alert("参数名不能超过64个字符");
		   return;
		}
		if(realLength(desc)>256){
		   alert("参数说明不能超过256个字符");
		   return;
		}		
		var aNode = new ParamaterNode(id,name,desc,type);
		addRow('paramTable',aNode);
		document.getElementById("nameId").value="";
		document.getElementById("descId").value="";
		
	 }
	
	function addRow(name, aNode){
	
		var existNode = document.getElementById("paramaterNode-" + aNode.id);
		if (null!=existNode) {
			alert ("此参数已经存在： " + aNode.id +" ， 不允许重复添加！");
			return;
		}
		var aTable = document.getElementById(name);
		var lastRow = aTable.rows.length;
		var iteration = lastRow;
	    var row = aTable.insertRow(lastRow-1);
	    row.id = ("row_"+aNode.id);
	    var column = row.insertCell(0);
	    
	    aInput =  document.createElement('input');
	    aInput.setAttribute("type", "hidden");
	    aInput.setAttribute("name", "paramaterNode[" + aNode.id + "[desc]" + aNode.desc + "]");	   
        aInput.setAttribute("value", aNode.type);
	    aInput.setAttribute("id", "paramaterNode-" + aNode.id );
	    column.appendChild(aInput);

	    var textNode = document.createTextNode(aNode.name);
	    column.appendChild(textNode);	    
	    column = row.insertCell(1);
	    
	    var bTextNode = document.createTextNode(aNode.desc);
	    column.appendChild(bTextNode);
	    column = row.insertCell(2);

        var types = new Array ("", "数值型" , "字符串", "布尔型", "日期型");
        
       
        var cTextNode = document.createTextNode(types[aNode.type]);
	    column.appendChild(cTextNode);	   
	    column = row.insertCell(3);
	    
	    aImg =  document.createElement('img');
	    aImg.setAttribute("src","../images/clearicon.gif");
	    aImg.setAttribute("onclick", Function("removeRow('" + name + "','" + row.id + "')"));
	    column.appendChild(aImg);
	}
	
	 function removeRow(name,rowId){	   	    
		    var aTable = document.getElementById(name);
		    var aRow = document.getElementById(rowId);
	        aTable.deleteRow(aRow.rowIndex);      
    }
    
    function remove(name,rowId,id){
           var choice=confirm('确定要删除此参数的定义吗？');
		   if (true==choice){	
		        removeRow(name,rowId);       
		        window.location.href='task.do?act=deleteParam&paramDefineId=' + id;
           }
    }
    
    function  checkParamterNodes(){
	  var tbl = document.getElementById('paramTable');
 	  var lastRow = tbl.rows.length;
 	  if(lastRow==1){
 	  	  return false;
 	  }else{
 	  	return true;
 	  }
   }
</script>
<!--查找所有任务分类-->
<sm:query var="categorys" beanName="taskManager" methodName="findAllCategorys"/>
<html:form action="/task.do"  styleId="taskForm" method="post" onsubmit="return validateTaskForm(this)" >
	<input type="hidden" name="act" value="save" />
	<input type="hidden" name="validationKey" value="taskForm"/>
	<input type="hidden" value="<c:out value="${taskForm.jobDefine.jobId}"/>" name="jobId"/>
   <webui:panel title="编辑任务" icon="../images/icon_list.gif">
			<table  width="95%"  class="table-bg" cellspacing="1" cellpadding="2" >
			     <tr>
			        <webui:input label="任务名称:" required="true">
			            <c:if test="${taskForm.jobDefine.jobId == 0}">
					         <html:text property="jobDefine.name" size="25" />
					    </c:if >
					    <c:if test="${taskForm.jobDefine.jobId != 0}">					        
					        <html:text property="jobDefine.name" readonly="true" size="25"/>
					    </c:if>
			        </webui:input>
				   <webui:input label="任务说明:" required="true">
			             <html:text property="jobDefine.description" size="25"/>
			        </webui:input>		
				 </tr>					 		
				 <tr >	
				    <webui:input label="执行的类名" required="true" >
					     <html:text property="jobDefine.className" size="60"/> 
					</webui:input>
					 <webui:input label="任务类别" required="true" >
					     <html:select property="jobDefine.categoryId">
					     <option value="">请选择</option>
					     <html:options collection="categorys" labelProperty="name" property="categoryId"/>
					     </html:select>
					</webui:input>
				 </tr> 
			</table><br/>	

			<table width="95%" class="table-bg" cellspacing="1" cellpadding="2">
			     <tr>
			            <webui:input label="参数名">
			                <input type="text" id="nameId" name="names" size="25" value=""/>
			            </webui:input>
			           <webui:input label="参数说明"> 
			                <input type="text" id="descId" name="desc"  size="25"  value="" />	
			           </webui:input>
			     </tr>
			     <tr>
			           <webui:input label="参数类型" colspan="4">
			                <select name="type" id="typeId"   >
			                     <option value="">请选择</option>
			                     <c:forEach var="labelbean" items="${enum}" >
			                         <option value="<c:out value="${labelbean.value}"/>" > <c:out value="${labelbean.label}"/> </option>			                         
			                     </c:forEach>
					         </select>	
			           </webui:input>
			           			               
			      </tr>			
			</table>
			<table width="95%" border="0" cellspacing="2" cellpadding="2"><tr>
			<td align="right"><span class="clsButtonFace"><a href="#" title="单击添加此参数" onclick="addNodeRow()">添加</a></span>
                </td>
			 </tr></table>
			 <div class="eXtremeTable" >
			<table id="paramTable" border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="95%" >				  
			     <thead>
			     <tr> 			        
			            <td width="30%" class="tableHeader" >参数名</td>
			            <td width="40%" class="tableHeader">参数说明</td>
			            <td width="20%" class="tableHeader">参数类型</td>   
			            <td width="10%" class="tableHeader">操作</td>			          
			     </tr>	
			     </thead>
			     <tbody class="tableBody" >
	              <tr> 			        
			            <td colspan="4"></td>
			     </tr>	
			     </tbody>			             	                
			</table>
			</div>	 
			<c:if test="${taskForm.jobDefine.jobId != null}">	
	                  <c:forEach items="${taskForm.jobDefine.paramDefines}" var="jobParamDefines" >		                                               			    
			            
			             
			             <script>
            var id = "<c:out value='${jobParamDefines.paramName}'/>";
            var name = "<c:out value='${jobParamDefines.paramName}'/>";
	        var desc = "<c:out value='${jobParamDefines.paramDesc}'/>";
	        var type = "<c:out value='${jobParamDefines.paramType}'/>";
            var aNode = new ParamaterNode(id,name,desc,type);
	        addRow('paramTable',aNode);
        </script>	  
		              </c:forEach>
	              </c:if>   
	       <br>
	          <webui:linkButton value="sysadmin.button.save" href="#" onClick="submitForm(taskForm)" styleClass="clsButtonFace"/>
			  <webui:linkButton value="sysadmin.button.return" href="#" onClick="back()" styleClass="clsButtonFace"/>
	</webui:panel>
</html:form>

<html:javascript  dynamicJavascript="true" staticJavascript="false" formName="taskForm" />
