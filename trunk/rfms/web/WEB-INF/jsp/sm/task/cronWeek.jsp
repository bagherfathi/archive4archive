 <table>
			<tr>
			<td  align="left" width="20%"><sup class="tip">*</sup><bean:message key="cron.everyWhere.week.execute" />:&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="left">
			   <c:forTokens items="1,2,3,4,5,6,7" delims="," var="week">
			       <c:set var="exist" value="false"/>
			       
			       <c:forEach items="${weeklydayOrWeekPeriod}" var="selectedWeek">
			          <c:if test="${week == selectedWeek}">
			             <c:set var="exist" value="true"/>
			          </c:if>
			       </c:forEach>
			       
			       <c:if test="${exist == true}">
			           <input type="checkbox" name="weeklydayOrWeekPeriod" value="<c:out value='${week}'/>"  class="checkbox" checked/><fmt:message key="cron.week${week}" /><br>
			       </c:if>
			       <c:if test="${exist == false}">
			           <input type="checkbox" name="weeklydayOrWeekPeriod" value="<c:out value='${week}'/>"  class="checkbox"/><fmt:message key="cron.week${week}" /><br>
			       </c:if>
			   </c:forTokens>
			</td>
		</tr>
		<tr>
			<td align="left" width="20%"><sup class="tip">*</sup><bean:message key="cron.start.execute" />:&nbsp;&nbsp;&nbsp;
			</td>
			<td align="left">
			   <input type="text" name="weeklyrunDayTime" value="<c:out value="${weeklyrunDayTime}"/>" /><bean:message key="cron.example" />
			</td>
		</tr>
	</table>