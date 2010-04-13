<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:useBean id="JSONRPCBridge" scope="session"
	class="com.metaparadigm.jsonrpc.JSONRPCBridge" />
<jsp:useBean id="sHello" scope="session" class="com.ft.rfms.web.portal.PortalAction" />
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
	var memberLoginDTO=jsonrpc.jHello.memberLogin();
	$("returnCode").value=memberLoginDTO.resultMsg.returnCode;
	$("returnMsg").value=memberLoginDTO.resultMsg.returnMsg;
	//$("sex").value=person.sex;
	//$("age").value=person.age;
	alert($("returnCode"));
//	alert(Object.toJSON(person));
}

function setPerson() {
	var person={name:$F("name"),sex:$F("sex"),age:$F("age")};
	alert(jsonrpc.jHello.setPerson(person));
}

function setParent(){
 var parent={name:$F("name"),sex:$F("sex"),age:$F("age"),son:{name:$F("name"),sex:$F("sex"),age:$F("age")}};
 alert(jsonrpc.jHello.setParent(parent));
}

function getObject(){
var obj=jsonrpc.jHello.getObject($F("textfield2"));
alert(Object.toJSON(obj));
}


//同步调用和异步调用
function synCall(){
	alert(Object.toJSON(jsonrpc.jHello.getPersonSyn()));
	alert("返回结果了，才获得做其他事情！");
}

function callBackCall(){
	jsonrpc.jHello.getPersonSyn(callBack);
	alert("放出请求了，我可以做其他事情，返回结果会自动调回调函数处理。");
}
function callBack(result,exception){
alert(Object.toJSON(result));
}

</script>
<title>Hello World</title>
</head>

<body>

<input type="button" name="button2" id="button2" value="" onclick="alert(jsonrpc.jHello.memberLogin('admin','123456'))"/>


<p>1、测试sayHello:  
  <input type="button" name="button" id="button" value="sayHello" onclick="alert(jsonrpc.jHello.sayHello())" />
</p>
<p>2、带参数的sayHello:
  <input name="textfield" type="text" id="textfield" value="大海" />
  <input type="button" name="button2" id="button2" value="sayHello()" onclick="alert(jsonrpc.jHello.sayHello($F('textfield')))"/>
</p>
<p>3、姓名 ：
  <input type="text" name="name" id="name" />
  性别：
  <input type="text" name="sex" id="sex" />
  年龄：
  <input type="text" name="age" id="age" />
  <input type="button" name="button3" id="button3" value="getPerson" onclick="getPerson()"/>
  <br />
</p>
<p>4、
  <input type="button" name="button4" id="button4" value="setPerson()"  onclick="setPerson()"/>  
  <br />
</p>
<p>5、嵌套对象：
  <input type="button" name="button5" id="button5" value="getParent" onclick="alert(Object.toJSON(jsonrpc.jHello.getParent()))"/>
  <br />
</p>
<p>6、死循环嵌套对象：
  <input type="button" name="button6" id="button6" value="getFriend" onclick="alert(Object.toJSON(jsonrpc.jHello.getFriend()))"/>
<br />
</p>
<p>7、
  <input type="button" name="button7" id="button7" value="setParent()" onclick="setParent()"/>
  <br />
</p>
<p>8、
  <input type="text" name="textfield2" id="textfield2"  value="java.util.Date"/>
  <input type="button" name="button8" id="button8" value="getObject()" onclick="getObject()"/>
  <br />
</p>

<p>9、同步调用：
  <input type="button" name="button9" id="button9" value="同步调用" onclick="synCall()"/>
</p>
<p>10、异步调用 ：
  <input type="button" name="button10" id="button10" value="异步调用" onclick="callBackCall()"/>
  <br />
</p>
</body>
</html>
