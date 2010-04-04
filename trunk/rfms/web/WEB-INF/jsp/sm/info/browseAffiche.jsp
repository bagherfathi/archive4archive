<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ include file="/WEB-INF/jsp/inc/link.inc"%>
<html:form action="/browse">
<table width="200" border="0" cellspacing="0" cellpadding="0" height="300">
  <tr> 
    <td width="3"></td>
    <td></td>
  </tr>
  <tr>
	  <td valign="top" width="8"><img src="../images/icon_message_left.gif" id="message_img" class="cur-hand" title="显示公告栏" onClick="showMessageLeft();" border="0"></td>
	  <td valign="top">
	  <table width="100%" border="1" cellpadding="3" cellspacing="0" class="tb-list" height="100%">
	  	<tr>
	      <td valign="top">
	        <div id="marquees" style="width:100%;">
	 		</div>
	      </td>
	    </tr>
	  </table>
	  </td>
  </tr>
</table>
</html:form>
<script language="javascript">
<!--

//document.body.onload=show;
Event.observe(window, 'load', show); 

function show(){
timeoo();
setTimeout("show()", 180000);
}

function timeoo(){
	new Ajax.Updater('marquees','<c:url value="${url}"/>',{method:'get',evalScripts:true});
	$('marquees').show();
}

var flag=1;
function showMessageLeft() {
    var workFrame = window.parent.workFrame;
	if(flag==2)
	{
		flag=1;
		if(workFrame.cols == "150,*,150")workFrame.cols="150,*,18";
		if(workFrame.cols == "0,*,150")workFrame.cols="0,*,18";
		document.all("message_img").src="../images/icon_message_left.gif";
		document.all("message_img").title="隐藏公告栏";
	} else {
		flag=2;
		if(workFrame.cols == "150,*,18")workFrame.cols="150,*,150";
		if(workFrame.cols == "0,*,18")workFrame.cols="0,*,150";
		document.all("message_img").src="../images/icon_message_right.gif";
		document.all("message_img").title="显示公告栏";
	}
}
	showDialog=function (name,id, width, height) {
		var url = "<c:url value='/sm/browse.do?act=view&id="+id+"'/>";
		//window.open (url, name, 'height=400px width=500px, top=300, left=200, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no') 
		openDialogWindow(url,400,500);
	}

	openDialogWindow=function(url,height,width) {
	  source = "";
	  defineWindow = "height=" + height + ",width=" + width + ",menubar=no,scrollbars=auto,location=no,resizable=no";
	  if (window.screen) {
	    var ah = screen.availHeight - 30;
	    var aw = screen.availWidth - 10;
	
	    var xc = (aw - width) / 2;
	    var yc = (ah - height) / 2;
	
	    defineWindow += ",left=" + xc + ",screenX=" + xc;
	    defineWindow += ",top=" + yc + ",screenY=" + yc;
	  }
	
	  var newWin = window.open(url, '', defineWindow );
	  newWin.focus();
	  return newWin;
	}
	
	function getCookie( name ) {
		var start = document.cookie.indexOf( name + "=" );
		var len = start + name.length + 1;
		if ( ( !start ) && ( name != document.cookie.substring( 0, name.length ) ) ) {
		return null;
		}
		if ( start == -1 ) return null;
		var end = document.cookie.indexOf( ";", len );
		if ( end == -1 ) end = document.cookie.length;
		return unescape( document.cookie.substring( len, end ) );
	}
	
	function setCookie( name, value) {
		var today = new Date();
		today.setTime( today.getTime() );
		
		var expires = 1000 * 60 * 60 * 24;
		var expires_date = new Date( today.getTime() + (expires) );
		
		document.cookie = name+"="+escape( value ) +
		( ( expires ) ? ";expires="+expires_date.toGMTString() : "" );
	}
-->
</script>
