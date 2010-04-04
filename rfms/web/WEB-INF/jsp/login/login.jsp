<HTML>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%@ page contentType="text/html; charset=GBK"%>


<HEAD>
<TITLE>瑞富通银--商户管理系统--登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
 
<STYLE type=text/css>* {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px
}
BODY {
	FONT-WEIGHT: normal; FONT-SIZE: 12px; BACKGROUND: url(images/login/03.jpg) #0cf repeat-x 0px 0px; COLOR: #333333; LINE-HEIGHT: 210%; FONT-FAMILY: Arial, Helvetica, sans-serif,"宋体"; TEXT-DECORATION: none
}
LI {
	LIST-STYLE-TYPE: none
}
INPUT {
	BORDER-RIGHT: #dcdcdc 1px solid; BORDER-TOP: #dcdcdc 1px solid; FONT-SIZE: 12px; BORDER-LEFT: #dcdcdc 1px solid; BORDER-BOTTOM: #dcdcdc 1px solid; FONT-FAMILY: "宋体"; HEIGHT: 18px
}
#main {
	MIN-HEIGHT: 600px; BACKGROUND: url(images/login/01.jpg) no-repeat 100px 0px; MARGIN: 0px auto; OVERFLOW: hidden; WIDTH: 1000px; POSITION: relative; HEIGHT: 560px
}
#login_box {
	PADDING-LEFT: 50px; BACKGROUND: url(images/login/02.jpg) no-repeat 0px 0px; LEFT: 380px; WIDTH: 310px; PADDING-TOP: 50px; POSITION: absolute; TOP: 180px; HEIGHT: 182px
}
#login_box LI {
	LINE-HEIGHT: 210%; HEIGHT: 30px
}
.btn {
	BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BACKGROUND: url(images/login/05.gif) no-repeat 0px 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 58px; CURSOR: pointer; COLOR: #fff; LINE-HEIGHT: 20px; HEIGHT: 20px; BORDER-RIGHT-WIDTH: 0px
}
</STYLE>

</HEAD>
<BODY onload=javascript:document.loginForm.loginName.focus();>
<DIV id="main">
<FORM name="loginForm" onSubmit="return loginCheck(this);"action="<c:url value='/login.do'/>" method="post">
 <input type="hidden" name="act" value="login"/>
<UL id="login_box">
 <html:messages id="message" message="true">
		      <LI><font color="red"><c:out value="${message}"/></font></LI>
	        </html:messages>
  <LI>用户名：<INPUT maxLength="20" name="loginName" id="loginName"> </LI>
  <LI>密　码：<INPUT type="password" maxLength="32" name="password" ></LI>
  <LI style="PADDING-LEFT: 48px"><INPUT type="hidden" value="0" name=cookietime/>
  <INPUT class="btn" type="submit" value=" 登录 " name="dosubmit"> <INPUT class="btn" type="reset" value=" 清除 " name="reset"> 
</LI>
</UL>
</FORM>
</DIV>
</BODY>

<script>
function loginCheck(form)
{
	var username = form.loginName;
	var password = form.password;
	//var cookietime = form.cookietime;
	if(username.value == ''){alert("请输入用户名");username.focus();return false;}
	if(password.value == ''){alert("请输入密码");password.focus();return false;}
	//var days = cookietime.value == 0 ? 1 : cookietime.value/86400;
	//setcookie('username', username.value, days);
	return true;
}
</script>
</HTML>