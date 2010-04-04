<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<script>
Date.prototype.format = function(format) //author: meizz 日期格式化
{
		var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
		}
		if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		(this.getFullYear()+"").substr(4 - RegExp.$1.length));
		for(var k in o)if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,
		RegExp.$1.length==1 ? o[k] :
		("00"+ o[k]).substr((""+ o[k]).length));
		return format;
}
function check(){
	     var endTime = document.getElementById('endTime');
	      var beginTime = document.getElementById('beginTime');
	     if(beginTime.value!='' && endTime.value==''){
	         endTime.value=new Date().format("yyyy-MM-dd hh:mm:ss");
	     }
	     document.forms.infoSharedForm.submit();
}
</script>
<html:form action="/infoShared" method="post" onsubmit="check()">
<input type="hidden" name="act" value="toSearch">
<webui:panel title="sysadmin.title.info.shared.search" width="95%" icon="../images/icon_search.gif">
<sm:query var="categorys" beanName="infoManager" methodName="findAllCategories"/>
	<webui:formTable>
		<tr>
		 	<webui:input label="sysadmin.label.info.category" colspan="3">
				<html:select property="category.categoryId">
					<html:option value="" key="sysadmin.label.info.search.all"/>
				  <html:options collection="categorys" property="categoryId" labelProperty="name"/>
		   		</html:select>
			</webui:input>
		 </tr>
		 <tr>
		 	<webui:input label="sysadmin.label.info.shared.title">
		 		<input type="text" name="title"/>
			</webui:input>

		 	<webui:input label="sysadmin.label.affiche.publisher">
		 		<input type="text" name="publisher"/>
			</webui:input>
		 </tr>
		 <tr>
		 	<webui:input label="sysadmin.label.shared.validTime">
		 		<input type="text" id="beginTime" name="beginTime" value="<c:out value='${param["publishTime.beginTime"]}'/>" />
	      		<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(beginTime,'yyyy-MM-dd hh:mm:ss');return false;">
	      	</webui:input>
	      	<webui:input label="sysadmin.label.shared.expireTime">
	      		<input type="text" id="endTime" name="endTime" value="<c:out value='${param["publishTime.endTime"]}'/>"/>
	      		<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(endTime,'yyyy-MM-dd hh:mm:ss');return false;">
	      	</webui:input>
	     </tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:check();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="sysadmin.label.info.shared.list" icon="../images/icon_list.gif" width="95%">
	<webui:table
		items="shareds"
		action="${pageContext.request.contextPath}/sm/infoShared.do?act=toSearch"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.info.shared.list"
		var="shared"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
			<webui:column sortable="false" filterable="false" cell="rowCount" property="infoId" title="序号" width="5%"/>
			<webui:column  filterable="false" property="title" title="共享信息名称" width="45%">
			      <a href="<c:url value='infoShared.do?act=view&id=${shared.infoId}'/>"><c:out value='${shared.title}'/></a>
			</webui:column>
			<webui:column  filterable="false" property="operator.contact.name" title="发布人" width="25%">
			      <a href="<c:url value='infoShared.do?act=view&id=${shared.infoId}'/>"><c:out value='${shared.operator.opName}'/></a>
			</webui:column>
			<webui:column  filterable="false" cell="date" format="yyyy-MM-dd HH:mm:ss" property="publishTime" title="发布时间" width="25%">
			      <a href="<c:url value='infoShared.do?act=view&id=${shared.infoId}'/>"><c:out value='${shared.publishTime}'/></a>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_INFO_SHARED">
		<security:success>
			<webui:linkButton styleClass="clsButtonFace" href="javascript:toPublish();" value="新建共享信息"/>
		</security:success>
	</security:checkPermission>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:toReturn();" value="sysadmin.button.return"/>
</webui:panel>
<script>
	function toReturn(){
		document.infoSharedForm.act.disabled=true;
		document.infoSharedForm.submit();
	}
	function toPublish(){
		window.location = "<c:url value='/sm/infoShared.do?act=toPublish'/>"+"&cId"+"=<c:out value='${infoSharedForm.category.categoryId}'/>";
	}
</script>
