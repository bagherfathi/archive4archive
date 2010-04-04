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
	     loadOn();
	     document.forms.afficheForm.submit();
 }
 
 function checkEnter(){
      if(window.event.keyCode==13){
	    check();
     }
    }
document.body.onkeypress= checkEnter;
</script>

<sm:query var="categories" beanName="infoManager" methodName="findAllCategories"/>

<html:form action="/affiche" method="post">
<input type="hidden" name="act" value="search"/>
<c:if test="${loginOp.loginName=='admin'}">
<input type="hidden" name="publisherId" value=""/>
</c:if>
<c:if test="${loginOp.loginName!='admin'}">
<input type="hidden" name="publisherId" value="<c:out value='${loginOp.operatorId}'/>"/>
</c:if>
<webui:panel title="sysadmin.label.affiche.search" width="95%" icon="../images/icon_search.gif">
	<webui:formTable>
		<tr>
			<webui:input label="sysadmin.label.affiche.title">
				<html:text property="afficheTitle" size="25"/>
			</webui:input>
			<webui:input label="sysadmin.label.affiche.category">
				<html:select property="categoryId">
				    <html:option value="" key="sysadmin.label.select"/>
				    <c:forEach items="${categories}" var="category">
				    <option value="<c:out value='${category.categoryId}'/>"><c:out value="${category.name}"/></option>
				    </c:forEach>
				</html:select>
			</webui:input>
		</tr>
		<tr>
		<webui:input label="发布时间" colspan="4">
			<input size="25" type="text" readonly="true" id="beginTime" name="publishTime.beginTime" value="<c:out value='${param["publishTime.beginTime"]}'/>" />
			<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(beginTime,'yyyy-MM-dd');return false;">
		    至 <input size="25" type="text" readonly="true" id="endTime" name="publishTime.endTime" value="<c:out value='${param["publishTime.endTime"]}'/>"/>
			<img name="popcal" align="absmiddle" src='<c:url value="/images/look_day.gif"/>' class="cur-hand" border="0" onClick="calendar(endTime,'yyyy-MM-dd');return false;">
		</webui:input>
		</tr>
	</webui:formTable>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:check();" value="sysadmin.button.search"/>
</webui:panel>
</html:form>

<br/>
<webui:panel title="sysadmin.label.affiche.list" width="95%" icon="../images/icon_list.gif">
	<webui:table
		dataSource="afficheDS"
		action="${pageContext.request.contextPath}/sm/affiche.do"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.label.affiche.list"
		var="affiche"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"
		autoIncludeParameters="true"
		>
		<webui:row>
			<webui:column  filterable="false" property="title" title="公告标题">
			</webui:column>
			<webui:column sortable="false" filterable="false" property="level"
				title="公告级别">
				<webui:lookup code="affiche_level@SM_AFFICHE" value="${affiche.level}" />
			</webui:column>
			<webui:column  filterable="false" property="category" title="所属类别">
			    <c:forEach items="${categories}" var="category">
				  <c:if test="${category.categoryId==affiche.categoryId}">
				      <c:out value="${category.name}"/>
				  </c:if>
			    </c:forEach>
			</webui:column>
			<webui:column  filterable="false" property="operator.opName" title="发布人">
			</webui:column>
			<webui:column  filterable="false" cell="date" format="yyyy-MM-dd HH:mm:ss" property="publishTime" title="发布时间">
			</webui:column>
			<webui:column  filterable="false" property="desc" title="操作">
			      <a href="<c:url value='/sm/affiche.do?act=view&id=${affiche.afficheId}'/>">查看</a>
			      &nbsp;<a href="<c:url value='/sm/affiche.do?act=toEdit&id=${affiche.afficheId}'/>">修改</a>
			      &nbsp;<a href="#" onclick="javascript:toDelete(<c:out value='${affiche.afficheId}'/>)">删除</a>
			</webui:column>
		</webui:row>
	</webui:table>
	<security:checkPermission resourceKey="SM_ADD_INFO_AFFICHE">
		<security:success>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:toCreate();" value="sysadmin.button.create"/>
		</security:success>
	</security:checkPermission>
</webui:panel>

<script>
	function toCreate(){
 		window.location = "<c:url value='/sm/affiche.do?act=create'/>";
	}
	
	function toDelete(afficheId){
		if(window.confirm("<bean:message key='msg.confirm.delete.info.affiche'/>")){
      		window.location = "<c:url value='/sm/affiche.do?act=delete&id='/>" + afficheId;
      	}
	}
</script>
