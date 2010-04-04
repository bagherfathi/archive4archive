<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>

<script>
    function go(){
        var obj = document.getElementsByName("regionIds");
        var flag = false;
        var id;
        var title;
        for(var i=0 ;i<obj.length;i++){
         	if(obj[i].checked){
         	   flag=true;
         	   id = obj[i].value;
         	   title = obj[i].title;
         	   break;
         	}
        }
        if(!flag){
        	 alert("����ѡ��һ��С��");
			 return;
        }
        
        var inputNameValue = document.getElementById("inputName").value;
                
		opener.document.getElementById('select_'  + inputNameValue).value = title;
		opener.document.getElementById(inputNameValue).value = id;
        window.close();
    }
    
    function changeSheng(){
        document.selRegionForm.act.value="changePriv";
        document.selRegionForm.submit();
    }
    
    function changeShi(){
        document.selRegionForm.act.value="changeCity";
        document.selRegionForm.submit();
    }
    
    function changeQuxian(){
        document.selRegionForm.act.value="changeDistrict";
        document.selRegionForm.submit();
    }
</script>
<html:form action="/selRegion" method="post">
<input type="hidden" name="act" value="search"/>
<input type="hidden" name="searchType"/>
<input type="hidden" name="inputName" value="<c:out value='${param.inputName}'/>"/>
<webui:panel title="ѡ������" width="95%">
	<webui:formTable>
      <tr>
        <td>
		<html:select property="selShengId" style='width:100' onchange="changeSheng();">
			<option value="0">��ѡ��</option>
			<c:forEach items="${selRegionForm.shengList}" var="region">
			<option value="<c:out value='${region.regionId}'/>" <c:if test='${selRegionForm.selShengId==region.regionId}'>selected</c:if>><c:out value="${region.regionName}"/></option>
			</c:forEach>
		</html:select>&nbsp;ʡ&nbsp;
		<html:select property="selShiId" style='width:100' onchange="changeShi();">
			<option value="0">��ѡ��</option>
			<c:forEach items="${selRegionForm.shiList}" var="region">
			<option value="<c:out value='${region.regionId}'/>" <c:if test='${selRegionForm.selShiId==region.regionId}'>selected</c:if>><c:out value="${region.regionName}"/></option>
			</c:forEach>
		</html:select>&nbsp;��&nbsp;
		<html:select property="selQuxianId" style='width:100' onchange="changeQuxian();">
			<option value="0">��ѡ��</option>
			<c:forEach items="${selRegionForm.quxianList}" var="region">
			<option value="<c:out value='${region.regionId}'/>" <c:if test='${selRegionForm.selQuxianId==region.regionId}'>selected</c:if>><c:out value="${region.regionName}"/></option>
			</c:forEach>
		</html:select>&nbsp;�����أ�&nbsp;
		<html:select property="selQuId" style='width:100'>
			<option value="0">��ѡ��</option>
			<c:forEach items="${selRegionForm.quList}" var="region">
			<option value="<c:out value='${region.regionId}'/>" <c:if test='${selRegionForm.selQuId==region.regionId}'>selected</c:if>><c:out value="${region.regionName}"/></option>
			</c:forEach>
		</html:select>&nbsp;��
		</td>
	  </tr>
	  <tr>
        <td>
        С�����ƣ�&nbsp;
	    <html:text property="regionName" size="25"/>
	    <td>
	  </tr>
    </webui:formTable>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();selRegionForm.searchType.value='2';selRegionForm.submit();" value="ģ����ѯ"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:loadOn();selRegionForm.searchType.value='1';selRegionForm.submit();" value="��ȷ��ѯ"/>
</webui:panel>
</html:form>
<br/>
<webui:panel title="С���б�" icon="../images/icon_list.gif">
<webui:table 
		items="accessRegions"
		action="${pageContext.request.contextPath}/sm/selRegion.do?act=search&inputName=${param.inputName}"
		imagePath="${pageContext.request.contextPath}/images/table/*.gif"
		title="sysadmin.title.bank.list"
		var="region"
		width="95%"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		autoIncludeParameters="false"
		>
		<webui:row>
		    <webui:column style="text-align:center;" sortable="false" property="ѡ��" title="ѡ��" filterable="false" width="8%">
				<input class="noborder" type="radio" name="regionIds" value="<c:out value='${region.regionId}'/>" title="<sm:regionLocation regionId='${region.regionId}'/>" ondblclick="go();"/>       
			</webui:column>
			<webui:column sortable="false" filterable="false" property="regionName" title="��������">
			    <sm:regionLocation regionId="${region.regionId}"/>
			</webui:column>
		</webui:row>
	</webui:table>
	<webui:linkButton styleClass="clsButtonFace" href="javascript:go();" value="ȷ��"/>
    <webui:linkButton styleClass="clsButtonFace" href="javascript:window.close();" value="�رմ���"/>
</webui:panel>