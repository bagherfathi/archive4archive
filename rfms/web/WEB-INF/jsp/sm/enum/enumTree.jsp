<%@ page language="java" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/tld/sm.tld" prefix="sm" %>
<%@ taglib uri="/WEB-INF/tld/webui.tld" prefix="webui" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<link href="<c:url  value="/css/dhtmlXTree.css"/>" rel="stylesheet" type="text/css" /> 
<script src="<c:url value='/js/dhtmlXCommon.js'/>"></script>
<script src="<c:url value='/js/dhtmlXTree.js'/>"></script>

<body leftmargin="0" topmargin="0" rightmargin="0" bgcolor="#f5f5f5">
<table><tr><td>
<div id="treeboxbox_tree" ></div>
</td></tr></table>
</body>

<script>
		tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.loadXML("<c:url value="/sm/treeXml.do?beanName=enumManager"/>");
		tree.setXMLAutoLoading("<c:url value="/sm/treeXml.do?beanName=enumManager"/>");
		tree.setImagePath("<c:url value="/images/"/>");
		tree.setOnClickHandler(doOnSelect);

		function doOnSelect(itemId){
		  var obj = itemId.split("_");
		  var url = "<c:url value="/sm/enumGroup.do"/>";
		  if(obj[0] == "group"){
		     url =  "<c:url value="/sm/enumGroup.do?act=edit&groupId="/>" + obj[1] ;
		  }
		  if(obj[0] == "category"){
		     url =  "<c:url value="/sm/enumCategory.do?act=edit&categoryId="/>" + obj[1] ;
		  }
		  if(obj[0] == "enum"){
		     url =  "<c:url value="/sm/enum.do?act=edit&enumId="/>" + obj[1];
		  }
		  parent.rightFrame.location.href = url ;
		}
		
</script>