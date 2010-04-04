<table> 
		<tr valign="top">
			<td  align="left" width="20%"><sup class="tip">*</sup><bean:message key="cron.what.day" />:&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td  align="left" >
			 <c:forTokens items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31"  delims=","  var="day">	
			     <c:set var="exist" value="false" />		    
			     <c:forEach  items="${monthlydayOrWeekPeriod}" var="selectedDay"> 
				       <c:if test="${day == selectedDay}">
				           <c:set var="exist" value="true" />
				       </c:if>			           	       
			     </c:forEach>
			     
			      <c:if test="${exist == true}">
				      <input type="checkbox" name="monthlydayOrWeekPeriod" value='<c:out value="${day}"/>'  class="checkbox" checked/>
				      <c:out value="${day}"/><bean:message key="task.daily" />	&nbsp;&nbsp;&nbsp;
				  </c:if>   
			      <c:if test="${exist == false}">
			          <input type="checkbox" name="monthlydayOrWeekPeriod" value='<c:out value="${day}"/>'  class="checkbox" />
                       <c:out value="${day}"/><bean:message key="task.daily" />	&nbsp;&nbsp;&nbsp;
			      </c:if>
			       <c:if test="${day % 3 == 0}" >
			          	 <br/> 
			      </c:if>
			   </c:forTokens>	      			 
			</td>
		</tr>
		<tr valign="top" >
			<td  align="left" width="20%"><sup class="tip">*</sup><bean:message key="cron.what.month" />:&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="left">
			
			 <c:forTokens items="1,2,3,4,5,6,7,8,9,10,11,12"  delims=","  var="month">	
			     <c:set var="exist" value="false" />		    
			     <c:forEach  items="${monthlymonthsEnum}" var="selectedMonth"> 
				       <c:if test="${month == selectedMonth}">
				           <c:set var="exist" value="true" />
				       </c:if>			           	       
			     </c:forEach>
			     
			      <c:if test="${exist == true}">
				     &nbsp;&nbsp; <input type="checkbox" name="monthlymonthsEnum" value='<c:out value="${month}"/>' class="checkbox" checked/>
				 <fmt:message key="cron.month${month}" />
				  </c:if>   
			      <c:if test="${exist == false}">
			          &nbsp;&nbsp;<input type="checkbox" name="monthlymonthsEnum" value='<c:out value="${month}"/>'  class="checkbox" />
				           <fmt:message key="cron.month${month}" />
			      </c:if>
			       <c:if test="${month % 2 == 0}" >
			          	 <br/> 
			      </c:if>
			   </c:forTokens>
			   
			</td>
		</tr>
		<tr>
			<td align="left" width="20%"><sup class="tip">*</sup><bean:message key="cron.start.execute" />:&nbsp;&nbsp;&nbsp;</td>
			<td align="left"><input  type="text" id="monthlyrunDayTime" name="monthlyrunDayTime" value="<c:out value="${monthlyrunDayTime}"/>" /><bean:message key="cron.example" /></td>
		</tr>
	</table>
       