<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<html>
<link href="<c:url  value="/css/dhtmlXTree.css"/>" rel="stylesheet" type="text/css" /> 
<script  src="<c:url value='/js/dhtmlXCommon.js'/>"></script>
<script  src="<c:url value='/js/dhtmlXTree.js'/>"></script>
<body leftmargin="0" topmargin="0" rightmargin="0" bgcolor="#f5f5f5">
<table><tr><td>
<div id="treeboxbox_tree" />
</td></tr></table>
</body>
</html> 
 <script>
		tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.loadXML("<c:url value="/sm/treeXml.do?beanName=orgManager"/>");
		tree.setXMLAutoLoading("<c:url value="/sm/treeXml.do?beanName=orgManager"/>");
		tree.setImagePath("<c:url value="/images/"/>");
		tree.setOnClickHandler(doOnSelect);
		tree.enableDragAndDrop(false);
		tree.setDragHandler(tondrag);
		
		function doOnSelect(itemId){
           parent.rightFrame.location.href='<c:url value="/sm/org.do?act=view&orgId="/>'+ itemId;
		}
		
		function tondrag(id,id2){
		    if(id!="" && id2!="" ){  
			   if( confirm("确定要将组织\""+tree.getItemText(id)+"\"移动到\""+tree.getItemText(id2)+"\"?")){
			    var obj = parent.rightFrame.document.getElementById('hiddenForm');
			    obj.setAttribute('action', '<c:url value="/sm/org.do?act=changeParent&orgId="/>'+id +"&pid=" + id2);
	            obj.submit();
	            }
			}
		};
		
		dhtmlXTreeObject.prototype.moveItem=function(itemId,targetId) {
		  this.setDragHandler("");
          var sNode=this._globalIdStorageFind(itemId);
          if(!sNode)return(0);
          var tNode=this._globalIdStorageFind(targetId);
          if(!tNode)return(0);
          this._moveNodeTo(sNode,tNode);
          this.setDragHandler(tondrag);
        }
</script>