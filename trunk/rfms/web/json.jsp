
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:useBean id="JSONRPCBridge" scope="session"
	class="com.metaparadigm.jsonrpc.JSONRPCBridge" />
<jsp:useBean id="sHello" scope="session"
	class="com.ft.rfms.web.portal.PortalAction" />
<%
	JSONRPCBridge.registerObject("jHello", sHello);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/json/jsonrpc.js"></script>
<script type="text/javascript" src="js/json/prototype.js"></script>
<script type="text/javascript">
var jsonrpc = new JSONRpcClient("JSON-RPC");

function memberLogin() {
	var mobile=document.getElementById("mobile").value;
	var pwd=document.getElementById("pwd").value;
	var memberLoginDTO=jsonrpc.jHello.memberLogin(mobile,pwd);
	alert(memberLoginDTO.resultMsg.returnMsg);
}
function regMember() {
	var mobile=document.getElementById("mobile1").value;
	var resultMsg=jsonrpc.jHello.regMember(mobile);
	alert(resultMsg.returnMsg);
}

function findTicket() {
	var merchantCode=document.getElementById("merchantCode").value;
	var json=jsonrpc.jHello.findTicket(merchantCode);
	alert(json);
		
}

</script>
<title>JSON 验证测试案例</title>
</head>

<body>

001：注册成功 1002：手机号码已经存在 1003：未知错误
<p>1、(用于pos机注册)，只提供手机号码，密码自动生成，并发送密码短信: 手机 ： <input type="text"
	name="mobile" id="mobile1" value="13899999999" /> <input type="button"
	name="button2" id="button2" value="手机注册" onclick="regMember()" /></p>
<br>

<p>2、登陆验证: 手机 ： <input type="text" name="mobile" id="mobile"
	value="13899999999" /> 密码： <input type="password" name="pwd" id="pwd"
	value="123456" /> <input type="button" name="button2" id="button2"
	value="登陆验证" onclick="memberLogin()" /></p>
<br />

<p>3、: 商户编号： <input type="text" name="merchantCode"
	id="merchantCode" value="1" /> <input type="button" name="button2"
	id="button2" value="获取商户的飞卷信息" onclick="findTicket()" /></p>
<br />

<p>4、: 得到行业信息：<input type="button" name="button2"
	id="button2" value="得到行业信息" onclick="jsonrpc.jHello.getIndustry()" /></p>
<br />

</p>
</body>
</html>
