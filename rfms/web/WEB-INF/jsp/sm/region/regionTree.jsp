<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html>
<link href="<c:url  value="/css/dhtmlXTree.css"/>" rel="stylesheet" type="text/css" /> 
<script src="<c:url value='/js/dhtmlXCommon.js'/>"></script>
<script src="<c:url value='/js/dhtmlXTree.js'/>"></script>
<body leftmargin="0" topmargin="0" rightmargin="0" bgcolor="#f5f5f5">
<table><tr><td>
<div id="treeboxbox_tree" /></div>
</td></tr></table>
<script>
		tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.loadXML("<c:url value="/sm/treeXml.do?beanName=regionManager"/>");
		tree.setXMLAutoLoading("<c:url value="/sm/treeXml.do?beanName=regionManager"/>");
		tree.setImagePath("<c:url value="/images/"/>");
		tree.setOnClickHandler(doOnSelect);

		function doOnSelect(itemId){
           parent.rightFrame.location.href='<c:url value="/sm/region.do?act=view&regionId="/>'+ itemId;
		}
		
</script>
</body>
</html>