<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
 
</script>
<html:form action="/tradeSearch" method="post">
	<input type="hidden" value="search" name="act" />
	<webui:panel title="交易记录查询"
		icon="../images/icon_search.gif" width="95%">
		<webui:formTable>
			<tr>
				<webui:input label="POS编号">
					<html:text property="posCode" size="25" onkeydown="onlyNum()"/>
				</webui:input>
				<webui:input label="交易状态">
					<html:select property="tradeStatus">
		       <html:options collection="tradeStatuses" property="value" labelProperty="label"/>
		      </html:select>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="开始时间">
					<webui:calendar id="beginDate" property="beginDate" defaultToday="false" readonly="false"/>
				</webui:input>
				<webui:input label="结束时间">
					<webui:calendar id="endDate" property="endDate" defaultToday="false"  readonly="false"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="卡号">
					<html:text property="cardCode" size="25" onkeydown="onlyNum()"/>
				</webui:input>
				<webui:input label="交易流水号">
					<html:text property="tradeCode" size="25"  onkeydown="onlyNum()"/>
				</webui:input>
			</tr>
			<tr>
				<webui:input label="金额区间" colspan="3">
					 <html:text property="minMoney" size="10" onkeydown="onlyNum()"/> 至 <html:text
						property="maxMoney" size="10"  onkeydown="onlyNum()"/>
				</webui:input>
			</tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:loadOn();document.tradeSearchForm.submit();"
			value="sysadmin.button.search" />
	</webui:panel>

<br />
<webui:panel title="交易记录"
	icon="../images/icon_list.gif">
	<webui:table items="${results }"
		action="${pageContext.request.contextPath}/rfms/tradeSearch.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="title.rfms.merchant.list" var="item" width="95%"
		showPagination="true" showStatusBar="true" showTitle="false"  sortable="false"
		filterable="false" autoIncludeParameters="false" tableId="tradeSearchForm" form="tradeSearchForm">
		<webui:row>
			<webui:column  property="text" title="交易状态" styleClass="td_normal">
 
			</webui:column>
			<webui:column property="realamount" title="交易金额" styleClass="td_normal">
			<fmt:formatNumber value="${item.realamount/100.0 }" pattern="###.00"></fmt:formatNumber>
			</webui:column>
			<webui:column property="recordtime" title="交易时间">
			</webui:column>
			<webui:column property="name" title="商户">
 
			</webui:column>
			<webui:column property="eftno" title="POS号">
			</webui:column>
			<webui:column property="cardasn" title="卡号">
			</webui:column>
		</webui:row>
	</webui:table>
			
</webui:panel>
</html:form>