<table >      
	     <tr>
				<td align="left" width="20%" >*<bean:message key="cron.interval.day" />:&nbsp;&nbsp;&nbsp;&nbsp;</td>				
					<td align="left" >
					<input type="text" id="dailydayOrWeekPeriod" name="dailydayOrWeekPeriod" value="<c:out value='${dailydayOrWeekPeriod}'/>"/>
					<bean:message key="task.day" />&nbsp;<bean:message key="crop.day.bound" /></td>
				</tr>
				<tr >
					<td align="left"><sup class="tip">*</sup><bean:message key="cron.start.execute" />:&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="left">
					<input type="text" id="dailyrunDayTime" name="dailyrunDayTime" value="<c:out value='${dailyrunDayTime}'/>" />
				<br>
					<bean:message key="cron.example" /></td>
		 </tr>	
  </table>       	 