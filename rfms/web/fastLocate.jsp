<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc" %>

<html>
<head>
<title>快速定位</title>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link rel="stylesheet" href="<c:url value="/css/autocomplete.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
<script type="text/javascript" src="<c:url value="/js/autocomplete_extras.js"/>"></script>
<%
	String str=request.getParameter("slObjName");
	if (str==null)
		str="";
    String dispName=request.getParameter("dispName");
	if (dispName==null)
		dispName="";
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
	var selectObj=null;
	var slObjName="<%=str%>";
	onload=function setSelectObj()
	{
		selectObj=eval("this.opener.document.getElementById('"+slObjName+"')");
		var dataSource = [];		
		for(var i=0; i<selectObj.length; i++) 
		{
			if ((selectObj[i].value!=null)&&(selectObj[i].value!=''))
				dataSource[dataSource.length] =selectObj[i].text;
		}	
		var configs = {
			instanceName: "auto",
			textbox: document.getElementById("slObj"),
			dataSource: dataSource,
			height: 200,
			ignoreWhere: true
			}
			window.auto = new neverModules.modules.autocomplete(configs);			
	}
	
	function selectOpt()
	{
		var tempValue=document.all('slObj').value;
		if ((tempValue!=null)&&(tempValue!=''))
		{
			for(var i=0;i<selectObj.length;i++)
			{
				if(selectObj[i].text==tempValue)
				{
					selectObj[i].selected=true;
					try {
						selectObj[i].parentElement.onchange();
					} catch(e) {
					}
					
					break;
				}
			}
			window.close();
		}
	}
		   
//-->
</SCRIPT>
</head>
<body >
<webui:panel title="快速定位" width="100%" icon="/images/icon_search.gif">
		<webui:formTable>
		 <tr>
			<td class="td-left" width="25%"><%=dispName%></td>
			<td class="td-right" width="75%">
			<input id="slObj" onkeyup="auto.handlerEvent(event)"       ondblclick="auto.expandAllItem()" class="wid90"/>
			</td>
	 	 </tr>
		 <tr>
			<td class="td-right" colspan="2">注:双击可以下拉列表显示</td>			
	 	 </tr>
		</webui:formTable>
		<webui:linkButton styleClass="clsButtonFace" href="javascript:selectOpt();" value="关闭"/>
</webui:panel>
</body>
</html>