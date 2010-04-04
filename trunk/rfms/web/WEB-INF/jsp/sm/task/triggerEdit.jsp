<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script src="<c:url value="/js/date.js"/>"></script>
<fmt:setBundle basename="ActionDefinitionResource"/>
<html:form action="/trigger.do" >
	 <input type="hidden" name="act" value="saveCron" /> 
	 <input type="hidden" value="<c:out value='${taskForm.taskTrigger.triggerId}'/>" name="triggerId"/>
	 <input type="hidden" value="<c:out value='${taskForm.jobDefine.jobId}'/>" name="jobId"/>
   <webui:panel title="编辑规则" icon="../images/icon_list.gif">
	 <div id="mainId">
	   <table width="95%" class="table-bg" cellspacing="1" cellpadding="2"> 
	       <tr>
	           <webui:input label="任务名称">
	               <c:url value="${taskForm.jobDefine.name}"/> 
	           </webui:input>
		  </tr>	
		   <tr>
		       <webui:input label="是否立即启用">
	                <html:select property="taskTrigger.needStartup"  >
					   <html:option value="0">否</html:option>
					   <html:option value="1">是</html:option> 
		            </html:select>	
	           </webui:input>
		  </tr>				  
	   	  <tr>  	  
			<webui:input label="运行的频率">
	            <html:select property="cronType" onchange="changeSelectValue(this.value)">
	                <option vlaue="0">选择运行的频率</option>  
					<html:option value="1">每天运行</html:option>
					<html:option value="2">每周运行</html:option>
					<html:option value="3">每月运行</html:option>
					<html:option value="4">只运行一次</html:option>
					<html:option value="5">每隔几分钟</html:option>
					<html:option value="6">每隔几小时</html:option>
				</html:select>	
				<div id="cron1" style="display:none" > <%@ include file="cronDay.jsp" %></div>
	            <div id="cron2" style="display:none" > <%@ include file="cronWeek.jsp" %></div>
	            <div id="cron3" style="display:none" > <%@ include file="cronMonth.jsp" %></div>
               <div id="cron4" style="display:none" > <%@ include file="cronOnce.jsp" %></div>
	           <div id="cron5" style="display:none" >  <%@ include file="cronFix.jsp" %></div>	
	           <div id="cron6" style="display:none" >  <%@ include file="cronHour.jsp" %></div>	
		  </webui:input>	
		  </tr>
		</table>
	</div>

	

	       <br>
	            <div class="eXtremeTable" >
	             <table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="95%">		
	                 
	                <thead>
	                 <c:if test="${!empty taskForm.jobDefine.paramDefines }">
	                 <tr>	                    
	                    <td align="center" colspan="4" class="titleRow">参数列表</td>
	                 </tr>
	                   <tr>	                    
	                    <td width="15%" class="tableHeader"  > <bean:message key="parameter.name"/>      </td>
			            <td width="15%" class="tableHeader" > <bean:message key="parameter.desc"/>       </td>
			            <td width="15%" class="tableHeader" > <bean:message key="parameter.type"/>       </td>   
			            <td width="15%" class="tableHeader" > <bean:message key="parameter.value"/>      </td>
			          </tr>	
			          </c:if>
			         </thead> 
			         <tbody class="tableBody" >
	                  <c:if test="${taskForm.taskTrigger.triggerId == 0 || empty taskForm.taskTrigger.params }">	                     	                 
		                     <c:forEach items="${taskForm.jobDefine.paramDefines}" var="paramDefine" >
		                        <tr>
			                         <td><c:out value="${paramDefine.paramName}"/></td>
			                         <td><c:out value="${paramDefine.paramDesc}"/></td>
			                         <td><webui:lookup code="param_type@SM_TASK_JOB" value="${paramDefine.paramType}"/></td> 	      			                         
			                         <td><c:if test="${paramDefine.paramType != 4}">
			                         <input type="text"  size="20" id="In_<c:out value="${paramDefine.paramDefineid}"/>" 
			                          name="InstanceValue_<c:out value="${paramDefine.paramDefineid}"/>" value=""  
			                          onblur="check('<c:out value="${paramDefine.paramType}"/>',this.value,this.id);"/>
			                          </c:if>
			                          <c:if test="${paramDefine.paramType == 4}">
			                             <input readonly type="text" id="In_<c:out value="${paramDefine.paramDefineid}"/>" name="InstanceValue_<c:out value="${paramDefine.paramDefineid}"/>" />
				<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(In_<c:out value="${paramDefine.paramDefineid}"/>,'yyyy-MM-dd hh:mm:ss');return false;">
			                          </c:if></td>
		                        </tr>  	                           
	                  		 </c:forEach>      	                    
	                 </c:if>	 
	                                    
	                 <c:if test="${taskForm.taskTrigger.triggerId != 0 && !empty taskForm.taskTrigger.params}" >
	                     <c:forEach items="${taskForm.taskTrigger.params}" var="jobParam" >	                       
	                         <tr>
		                          <td><c:out value="${jobParam.paramDefine.paramName}"/></td>
		                          <td><c:out value="${jobParam.paramDefine.paramDesc}"/></td>
		                          <td><webui:lookup code="param_type@SM_TASK_JOB" value="${jobParam.paramDefine.paramType}"/></td> 	     	                
		                          <td>
		                          <c:if test="${jobParam.paramDefine.paramType != 4}">
		                          <input type="text" size="20" id="In_<c:out value="${jobParam.paramId}"/>"
		                           name="InstanceValue_<c:out value="${jobParam.paramId}"/>"
		                            value="<c:out value="${jobParam.value}"/>"  
		                            onblur="check('<c:out value="${jobParam.paramDefine.paramType}"/>',this.value,this.id);" />
		                          </c:if>
		                          <c:if test="${jobParam.paramDefine.paramType == 4}">
		                               <input readonly type="text" id="In_<c:out value="${jobParam.paramId}"/>" name="InstanceValue_<c:out value="${jobParam.paramId}"/>" value="<c:out value="${jobParam.value}"/>"/>
				<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(In_<c:out value="${jobParam.paramId}"/>,'yyyy-MM-dd hh:mm:ss');return false;">
		                          </c:if> 
		                            
		                            </td>
	                          </tr>  	                           
	                     </c:forEach>   
	                </c:if>	
	                </tbody>               
                 </table> 
                 </div>
	      <br>        
   <webui:linkButton styleClass="clsButtonFace" value="保存" href="#" onClick="if(validCronString()){taskForm.submit()}"/> 
   <webui:linkButton styleClass="clsButtonFace" value="返回" href="#" onClick="back()"/>
</webui:panel>
</html:form>

<script language="javascript">
   function back(){
      location.href="<c:url value="/sm/trigger.do?act=trigger&jobId=${taskForm.jobDefine.jobId}"/>"
   }
   function check(type,value,id) {
      
       var dow = document.getElementById(id);
       value = value.trim();
       if(value =="") return true;
       if (type == '1') {   
		  var re = /^\d+$/g;
			if(!re.test(value)){
				alert("<bean:message key="please.input.again3" />")
				dow.focus();
				return false;	
			}
       }else if (type == '3') {
           
          if ('true' == value || 'false' == value) {              
          } else {
                  alert("<bean:message key="please.input.again1" />");  
                  dow.focus();
			      return false;
          }
           
	
   
       }else if (type == '4' ) {
      
           /* if( value=="0" || value.indexOf('-')==-1 || value.indexOf(':')==-1 || value.indexOf(' ')==-1 ){
					alert("<bean:message key="please.input.again2" />" );	
					dow.focus();			
					return false;
		   }else {	
				    var dddd = dow.value.split(" ");
					if(!isYMD(dddd[0])){
						return false;
				   }  			
					if(!isHMS(dddd[1])) {	
						return false;
				 }
		    }		 */	
       }
       return true;
       
     }


   function init(cronType) {
      for(var i=1;i<=6;i++) {			
			document.getElementById("cron"+i).style.display="none";
	  }
	  if (cronType !=0)
	   document.getElementById("cron"+cronType).style.display="";   
    }

    init('<c:out value="${taskForm.cronType}"/>');
	function changeSelectValue(cronType) {	
   
	    if (cronType == 0){
	        alert("<bean:message key="please.choose.again" />");
	        return;
	   }
	    init(cronType);  
   
	}

   function validCronString() {  
   
        var selectedValues = document.getElementsByName("cronType");
         for (var i=0; i<selectedValues.length; i++) { 
	         var selectedValue = selectedValues[i].value;	
	         if (selectedValue == '0' || selectedValue==""){
	               alert("<bean:message key="please.config.cronString" /> ");
	               return false;
	         }else if(selectedValue=='1'){	         
		       var dow = document.getElementById("dailydayOrWeekPeriod");  	
			   if(dow.value==""){
					alert("<bean:message key="input.day.number" />");
					dow.focus();
					return false;
				}else{
					if(!isNumber(dow.value)){
						alert("<bean:message key="day.must.number" />");
						dow.focus();
						return false;
					}
					
					if(dow.value>=32 || dow.value<0){
						alert("<bean:message key="input.right.day" />");
						dow.focus();
						return false;
					}
			    }  	         
			     var day = document.getElementById("dailyrunDayTime");	    
			     if(!isHMS(day.value)){
					dow.focus(); 
					return false;
			     }
		     }else if (selectedValue=='2'){
		     
		         var dows = document.getElementsByName("weeklydayOrWeekPeriod");
	
				if(!chdsome(dows,"<bean:message key="cron.week" />")) {
					return false;
				}
							
				 var wdts = document.getElementsByName("weeklyrunDayTime");
				 for (var i=0; i<wdts.length; i++) { 
			         var wdt = wdts[i];	         
					if(!isHMS(wdt.value)){
						wdt.focus();
						return false;	
					}
		          }
		         
		     }else if (selectedValue=='3'){
		     
		        var dows1 = document.getElementsByName("monthlydayOrWeekPeriod");
	
				if(!chdsome(dows1,"<bean:message key="cron.month" />")) {
					return false;
				}
				 var dows2 = document.getElementsByName("monthlymonthsEnum");
	   
				if(!chdsome(dows2,"<bean:message key="cron.month" />")) {
					return false;
				}		
		   		var wdt = document.getElementById("monthlyrunDayTime");
		 
			   if(!isHMS(wdt.value)){
					wdt.focus();
					return false;
				}
				
			   if (!validate(dows1,dows2)){
				   return false;
				}	
				
			} else if (selectedValue=='4'){  
			    var dow = document.getElementById("onetimerunDayTime");
			    if(dow.value=="" || dow.value=="0" || dow.value.indexOf('-')==-1 || dow.value.indexOf(':')==-1 || dow.value.indexOf(' ')==-1 ){
					alert("<bean:message key="input.right.format" />" );
					dow.focus();
					return false;
			    }else{	
					var dddd = dow.value.split(" ");
					if(!isYMD(dddd[0])){
						dow.focus();
						return false;
				   }  			
					if(!isHMS(dddd[1])){
						dow.focus();
						return false;
				 }
			   }
			    
			}else if (selectedValue=='5'){
			    var dow = document.getElementById("fixedrateperiod");
			    if(dow.value==""){
					alert("<bean:message key="input.minute.value" />");
					dow.focus();
					return false;
				}else{				
					if(!isNumber(dow.value)){
						dow.focus();
						return false;
					}
					if(dow.value>=60 || dow.value<=0){
						alert("<bean:message key="input.right.minute" />");
						dow.focus();
						return false;
					}
				}		    
			}else if (selectedValue=='6'){
			    var dow = document.getElementById("hourlyRunTime");
			    if(dow.value==""){
					alert("请输入小时数");
					dow.focus();
					return false;
				}else{				
					if(!isNumber(dow.value)){
						dow.focus();
						return false;
					}
					if(dow.value>=24 || dow.value<=0){
						alert("小时的有效范围为1-23,请重新输入");
						dow.focus();
						return false;
					}
				}		    
			}
			
		 }
			return true;		
	}
	
	
	function isHMS(hms) {
		if(hms.indexOf(":")==-1){
			alert("<bean:message key="input.right.time1" />");	
			return false;		
		}
		var ss = hms.split(":");
		if(ss.length>3) {
			alert("<bean:message key="input.right.time1" />");	
			return false;
		}		
		if(vd(ss[0],"0","23","<bean:message key="task.hour" />","<bean:message key="task.hour" />")) {
			if(vd(ss[1],"0","59","<bean:message key="task.minute" />","<bean:message key="task.minute" />")) {
				if(ss.length==3){
					if(!vd(ss[2],"0","59","<bean:message key="task.second" />","<bean:message key="task.second" />")){
						return false;
					}
				}
			}else{
				return false;
			}
		}else {
			return false;
		}
		return true;
}

// evalute if some number is number and it is between correct range
	function vd(str,min,max,item,unit){		
			if(isNumber(str)){			
				var num = new Number(str);
				//alert(typeof(num));
				if(num<min || num>max){
					alert(item+"   " + "<bean:message key="input.right.format" />"
					+min+unit+"<bean:message key="cron.and" />"
					+max+unit+"<bean:message key="cron.between" />");	
					return false;	
				}
			}else{
				return false;
			}
		return true;
	}
	// evaluate if a str is a num
	function isNumber(str){
		var re = /^\d+$/g;
		if(!re.test(str)){
			alert(str+"<bean:message key="input.right.number" />" );
			return false;	
		}
		return true;
	}
	
	//判断是否为闰年
    function validateYear(year){
        if((year%4==0)||((year%100==0)&&(year%400==0))){
			 return true;
		}else{
		    
		    return false;
		}
    }
    
    //判断所选择的月份和该月份的日期是否有效
	function validate(wes,wes1){
	  
		var date = new Date();
		var year = date.getYear();		
	    if(!validateYear(year)){
			for(var i = 0;i<wes1.length;i++){
			    if (wes1[i].checked){
			      if (wes1[i].value == 2 ){
			         for(var i = 0;i<wes.length;i++){
					  if (wes[i].checked){
					      if (wes[i].value ==31 || wes[i].value ==30 || wes[i].value ==29){
					        alert("<bean:message key="input.right.day1" />");
					        return false;
					      }
					  }
				   }
				 }else if (wes1[i].value == 4 || wes1[i].value == 6 || wes1[i].value == 9 || wes1[i].value == 11 ){
				     alert(wes1[i].value);
				     for(var i = 0;i<wes.length;i++){
					  if (wes[i].checked){
					      if (wes[i].value ==31 ){
					        alert("<bean:message key="input.right.day2" />");
					        return false;
					      }
					  }
				   }
			    }
			 }  
		 } 
		}else {
		    for(var i = 0;i<wes1.length;i++){
			    if (wes1[i].checked){
			      if (wes1[i].value == 2 ){
			         for(var i = 0;i<wes.length;i++){
					  if (wes[i].checked){
					      if (wes[i].value ==31 || wes[i].value ==30 ){
					        alert("<bean:message key="input.right.day3" />");
					        return false;
					      }
					  }
					}
				}else if (wes1[i].value == 4 || wes1[i].value == 6 || wes1[i].value == 9 || wes1[i].value == 11 ){
				
				     for(var i = 0;i<wes.length;i++){
					  if (wes[i].checked){
					      if (wes[i].value ==31 ){
					        alert("<bean:message key="input.right.day2" />");
					        return false;
					      }
					  }
				  }
			   }
			}
		  }
		}
       return true;
	}
	
	//evalute whether checkbox is checked
	function chdsome(chobj,desc){
		var chd  = false;
		for(var i = 0;i<chobj.length;i++){
			if(chobj[i].checked){
				chd = true;
				break;
			}
		}
		if(!chd){
			alert("<bean:message key="please.choose" />"+desc);
			return false;
		}
		return true;
	}
	
	
   function isYMD(ymd){
		if(ymd.indexOf("-")==-1){
			alert("<bean:message key="input.right.time2" />");	
			return false;		
		}
		var ss = ymd.split("-");	
		var date = new Date();
		var year = date.getYear();
			
		if(vd(ss[0],year,year+1000,"<bean:message key="task.year" />","<bean:message key="task.year" />")){
			if(vd(ss[1],"1","12","<bean:message key="task.month" />","<bean:message key="task.month" />")){				
				if(!vd(ss[2],"1","31","<bean:message key="task.daily" />","<bean:message key="task.daily" />")){
					return false;
				}
				if (!vAll(ss[0],ss[1],ss[2])){
				    return false;
				}
				
			}else{
				return false;
			}
		}else {
			return false;
		}
		return true;
	}
	
	//判断所输入的年月日是否有效
	function vAll(year,mon,day){
	     if (validateYear(year)){
	         if (mon == 2){
	            if (day == 31 || day == 30){
	              alert("<bean:message key="input.right.day3" />");
	              return false;
	            }
	         }else if (mon == 4 || mon == 6 || mon == 9 || mon ==11){
	             if (day == 31 ){
	                alert("<bean:message key="input.right.day2" />");
	                return false;
	             
	             }
	         }
	     }else {	     
	          if (mon == 2){
	            if (day == 31 || day == 30 || day ==29){
	              alert("<bean:message key="input.right.day1" />");
	              return false;
	            }
	         }else if (mon == 4 || mon == 6 || mon == 9 || mon ==11){
	             if (day == 31 ){
	                alert("<bean:message key="input.right.day2" />");
	                return false;
	             }
	         }	     
	     }
	     return true;
	}
	
</script>

