//Desc：当前许多项目中都要用到一些脚本，为了便于统一管理、使用方便，特此进行了整理
//以后有什么好的、通用的脚本可以加进来

function isInMaxLen(o,nMaxLen){  
  o.value=trim(o.value);
 if(o.value.length>nMaxLen){
   if(o.alt!=null&&o.alt!=""){
    alert(o.alt+"不能超过"+nMaxLen+"个字符");
   }
   else {
   alert("不能超过"+nMaxLen+"个字符");
   }
   o.value=o.value.substring(0,nMaxLen);
   o.focus();
   return false;
 }
 else{  
   return true;
 }
}   
//判断是否选择了当前页面的一个 checkbox
function isChecked(o, errorMsg)
{
  if(o == null)
    return false;

  if(o.length) 
  {
    for(var i=0;i<o.length;i++) 
    {
      if(o[i].checked) 
      {
        return true;
      }
    }
	alert(errorMsg);
    return false;
  }
  else  
  {
    if(!o.checked) {
      alert(errorMsg);
	} else {
      return true;
	}
  }
}

//根据obj1的checked，选中或不选中所有的obj2
function convertCheck(obj1, obj2) {
  if(obj1.checked) {
    checkAll(obj2);
  } else {
    unCheckAll(obj2);
  }
}

function checkAll(obj) {
  checkObj(obj, true);
}

function unCheckAll(obj) {
  checkObj(obj ,false);
}

function checkObj(obj, flag) {
  if(obj == null) {
    return;
  }
  if(obj.length) {
    for(var i = 0;i < obj.length;i++)
    {
      if(!obj[i].disabled)
          obj[i].checked = flag;
    }
  } else {
    if(!obj.disabled) obj.checked = flag;
  }
}
//根据obj,flag，选中或不选中某一个值selectValue
function checkObjSelected(obj, flag,selectValue) {
  if(obj == null) {
    return;
  }

  if(obj.length) {
    for(var i = 0;i < obj.length;i++)
    {
      if(obj[i].value==selectValue){	
        obj[i].checked = flag;
      }
    }
  } else {
    if(obj.value==selectValue){	
     obj.checked = flag;
     }
  }
}
//判断对象集合中的值为selectValue是否被选中
function isCheckObj(obj, selectValue) {
  if(obj == null) {
    return;
  }

  if(obj.length) {
    for(var i = 0;i < obj.length;i++)
    {
      if(obj[i].value==selectValue&&obj[i].checked==true){	
         return true;
      }
    }
  }
  else {
    if(obj.value==selectValue&&obj.checked==true){	
      return true;
     }
  }
  return false;
}
//改变是否显示
function convertView(obj, changeClassObj)
{
  if(obj.style.display == "") {
	obj.style.display = "none";
	changeClassObj.className = "arrow_down";
  } else {
	obj.style.display = "";
	changeClassObj.className = "arrow_up";
  }
}

//改变是否显示
function expandView(obj, changeClassObj)
{
	obj.style.display = "";
	changeClassObj.className = "arrow_up";
}

//改变是否显示
function closeView(obj, changeClassObj)
{
	obj.style.display = "none";
	changeClassObj.className = "arrow_down";
}

//改变页面上指定的 object 否显示
function changeView(obj, value)
{
  if(obj.length)
  {
    for(var i = 0;i < obj.length;i++)
    {
      obj[i].style.display = value;
    }
  }
  else
  {
    obj.style.display = value;
  }
}

//设置span的innerhtml
function setSpanInnerHtml(obj, value)
{
  if(obj.length)
  {
    for(var i = 0;i < obj.length;i++)
    {
      obj[i].innerHTML = value;
    }
  }
  else
  {
    obj.innerHTML = value;
  }  
}

/**
 * 截取字符串空格函数;
 */
function trim(sStr)
{
	return sStr.replace(/(^\s*)|(\s*$)/g,"");
}

//验证下拉必须选择
function checkSelect(obj, errorMsg)
{
  if(obj && (obj.value =='' || obj.value == '0' || obj.value == '-1'))
  {
    alert("请选择" + errorMsg + "。");
    obj.focus();
    return false;
  }
  
  return true;
}
//验证输入框必须输入,返回提示语句
function checkInputReturnAlert(obj, errorMsg)
{
  if(obj && trim(obj.value) == "")
  {
    return (errorMsg);        
  }  
  return "";
}
//验证输入框必须输入
function checkInput(obj, errorMsg)
{
  if(obj && trim(obj.value) == "")
  {
    alert("请输入" + errorMsg + "。");
    obj.focus();
    return false;
  }
  
  return true;
}

//验证输入框必须输入
function checkInputColseView(obj, errorMsg, table)
{
  if(obj && trim(obj.value) == "")
  {
    if ( table.style.display=='none' )
        table.style.display='';
    alert("请输入" + errorMsg + "。");
    obj.focus();
    return false;
  }
  
  return true;
}


//检查邮编
function checkZipCode(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }  
    
  var regexp=/^\d{6}$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag;
}

//检查电话号码
function checkPhone(obj, errorMsg, needFlag) 
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
      
 // var regexp=/^(\d+-{0,1}\d+)$/;
 var regexp=/^\d{0,4}\-?\d{0,4}\-?\d{6}\d*\*?\d{0,6}$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag;
}

//检查手机号码
function checkMobile(obj, errorMsg, needFlag) 
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
      
  var regexp=/^([1-9]\d{10}|[1-9]\d{12})$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag;
}

//检查Email地址
function checkEmail(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
        
  var regexp=/^([\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+)$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag;
}

// 检查IP地址
function checkIp(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var error = "请输入正确的" + errorMsg + "。";
  var regexp = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
  var r = value.match(regexp);
  if(r == null) {
    alert(error);
    obj.focus();
    return false;
  }  
  if(value.indexOf("0.0.0.0") > -1 || value.indexOf("255.255.255.255") > -1) {
    alert(error);
    obj.focus();
    return false;
  }
  if(r[1] < 0 || r[1] > 255) {
    alert(error);
    obj.focus();
    return false;
  }
  if(r[2] < 0 || r[2] > 255) {
    alert(error);
    obj.focus();
    return false;
  }
  if(r[3] < 0 || r[3] > 255) {
    alert(error);
    obj.focus();
    return false;
  }
  if(r[4] < 0 || r[4] > 255) {
    alert(error);
    obj.focus();
    return false;
  }  
  
  return true;
}

//检查URL
function checkUrl(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
        
  var regexp=/^(\w+):\/\/([^/:]+)(:\d*)?([^# ]*)$/;
  var flag = regexp.test(value);

  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag;
}

function checkUrlFile(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
        
  var regexp=/^(\w+):\/\/([^/:]+)(:\d*)?([^# ]*\.(exe|zip|rar){1})$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag;
}

//检查整数，最大9位
function checkNumber(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
  
  var regexp=/^([1-9]\d{1,8}|\d)$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
	  obj.focus();
  }
  return flag; 
}
//检查浮点数
function checkFloat(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }  
   var a=obj.value;
	strTemp="0123456789.";
	for (i=0;i<a.length;i++)
	{
		j=strTemp.indexOf(a.charAt(i));
		if (j==-1)
		{
		//说明有字符不合法
			alert("字段'"+errorMsg+"'请输入数字,不能有字母或其他非法符合！")
			obj1.focus();
			return false;
		}
	}
	//说明合法
  return true; 
}
/*
 funtion checkdecimal(obj1,obj1Str)
//函数名：checkdecimal(obj1,obj1Str)
//检查输入的数字,小数部分不能超过两位
//参数说明：要检查的数字num,必须是数字
//返回值：0：不正确  1：正确
*/

function checkdecimal(obj1,obj1Str)
{
	var num=obj1.value;
	if(!checkFloat(obj1,obj1Str))
	return false;
	if(num.indexOf('.')>0){
		num=num.substr(num.indexOf('.')+1,num.length-1);
                if ((num.length)<3){
			return true;
		}
		else{
		       alert(obj1Str+",小数点后不可以超过2位!");
		       obj1.focus();
			return false;
		}
	}
			return true;
}

//检查正整数，最大9位
function checkPositiveNumber(obj, errorMsg, needFlag)
{
  var value = obj.value;
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
  
  var regexp=/^([1-9]{1}\d{0,8})$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "【正整数】。");
	obj.focus();
  }
  return flag; 
}

function checkPositiveNumber3(obj,errorMsg,needFlag){
      var value = obj.value;
  if(needFlag)
  {
    if(value == "")
    {
      alert(errorMsg);
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
  
  var regexp=/^([1-9]{1}\d{0,8})$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert(errorMsg);
	obj.focus();
  }
  return flag; 
}
//检查正整数，最大9位,同时检验是否隐藏，如果隐藏，打开对应的面板
function checkPositiveNumber2(obj, errorMsg, needFlag, divTable ) {
  var value = obj.value;
  if(needFlag)
  {
    if(value == "")
    {
      if ( divTable.style.display = "none" )
        divTable.style.display = "";
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }

  var regexp=/^([1-9]{1}\d{0,8})$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    if ( divTable.style.display = "none" )
        divTable.style.display = "";
    alert("请输入正确的" + errorMsg + "【正整数】。");
	obj.focus();
  }
  return flag;
}
//比较整数
function compareNumber(obj, compareValue, errorMsg, needFlag)
{
  if(!checkNumber(obj, errorMsg, needFlag)) {
    alert(errorMsg+"必须是整数！");
    obj.focus();
    return false;
  }   
  if(!needFlag && obj.value == "") {   
    return true;
  }
  
  var value = parseInt(obj.value);    
  if(value > compareValue)
  {
    alert(errorMsg + "不能大于" + compareValue + "。");
    obj.focus();
    return false;
  }
  
  return true;
}
//比较整数
function compareFloat(obj, compareValue, errorMsg, needFlag)
{
  if(!checkdecimal(obj, errorMsg, needFlag)) {
    obj.focus();
    return false;
  } 
  if(!needFlag && obj.value == "") {   
    return true;
  }
  
  var value = parseFloat(obj.value);    
  if(value > compareValue)
  {
    alert(errorMsg + "不能大于" + compareValue + "。");
    obj.focus();
    return false;
  }
  
  return true;
}
//检查MAC地址
function checkMacAddr(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var regexp = /^[a-zA-Z0-9]{2}\-[a-zA-Z0-9]{2}\-[a-zA-Z0-9]{2}\-[a-zA-Z0-9]{2}\-[a-zA-Z0-9]{2}\-[a-zA-Z0-9]{2}$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag; 
}

//检查文件名（中文或数字字母下划线.exe|zip|rar）
function checkFileName(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var regexp=/^[\u4E00-\u9FA5\w]+\.(exe|zip|rar){1}$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "。");
    obj.focus();
  }
  return flag; 
}

//检查是否以字母开头，只能由字母、数字、下划线组成
function checkLoginName(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var regexp=/^[A-Za-z]+\w*$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "［以字母开头，只能包含字母、数字、下划线］。");
    obj.focus();
  }
  return flag; 
}

//检查是否以字母开头，只能由字母、数字、'-'线组成
function checkAppName(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var regexp=/^[a-zA-Z]{1}([a-zA-Z0-9]|[-]){0,19}$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "［以字母开头，只能包含字母、数字、'-'］。");
    obj.focus();
  }
  return flag; 
}

//只能包含字母、数字
//needFlag 是否必须
function checkPassword(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var regexp=/^[A-Za-z0-9]+$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg + "［只能包含字母、数字］。");
    obj.focus();
  }
  return flag; 
}

/*
只能包含字母、数字
needFlag 是否必须
*/
function checkTwoPassword(obj1, errorMsg1, obj2, errorMsg2, needFlag) {

  if (checkPassword(obj1, errorMsg1, needFlag) && checkPassword(obj2, errorMsg2, needFlag)) {
	if (obj1.value!=obj2.value) {
		alert("两次密码输入不一致。");
		obj1.focus();
		return false;
	} else {
	  return true;
	}
  } else {
	  return false;
  } 
}


// -------------------------------------------------------------------
//	判断日期格式
//	注意：要使用这个函数，请包含进date.js
// -------------------------------------------------------------------
function checkDate(obj, errorMsg, format, needFlag) {
	if (needFlag) {
		if (obj.value=="") {
			alert("请输入" + errorMsg + "。");
			obj.focus();
			return false;
		}
	} else {
		if (obj.value=="") {
			return true;
		}
	}

	if (!isDate(obj.value, format)) {
		alert("请输入正确的" + errorMsg + "［"+format+"］。");
		obj.focus();
		return false;
	}
	return true;
}

// -------------------------------------------------------------------
// 比较对象，是否为合法的日期，且符合flag条件：
//	1 if obj1 is greater than obj2
//	0 if they are the same
//	-1 if obj2 is greater than obj1
//	注意：要使用这个函数，请包含进date.js
// -------------------------------------------------------------------

function checkCompareDate(obj1, errorMsg1, obj2, errorMsg2, needFlag,format) {
    if (checkDate(obj1, errorMsg1, format, needFlag) && checkDate(obj2, errorMsg2, format, needFlag)) {
        if (compareDates(obj1.value, format, obj2.value, format) != 0 && needFlag ) {
			alert(errorMsg1 + "不能大于" + errorMsg2 + "。");
			obj1.focus();
			return false;
		} else {
			return true;
		}
    } else {
        return false;
    }
}

/*
function checkCompareDate(obj1, errorMsg1, format1, obj2, errorMsg2, format2, needFlag) {
    if (checkDate(obj1, errorMsg1, format1, needFlag) && checkDate(obj2, errorMsg2, format2, needFlag)) {
        if (compareDates(obj1.value, format1, obj2.value, format2) != 0 && needFlag ) {
			alert(errorMsg1 + "不能大于" + errorMsg2 + "。");
			obj1.focus();
			return false;
		} else {
			return true;
		}
    } else {
        return false;
    }
}
*/

// -------------------------------------------------------------------
// 比较对象，是否为合法的日期，且符合flag条件：
//   1 if obj1 is greater than obj2
//   0 if they are the same
//  -1 if obj2 is greater than obj1
//  -2 if either of the objs is in an invalid format
//	注意：要使用这个函数，请包含进date.js
// -------------------------------------------------------------------
function checkCompareDate2(obj1, errorMsg1, obj2, errorMsg2, needFlag, flag,format) {
    if (checkDate(obj1, errorMsg1, format, needFlag) && checkDate(obj2, errorMsg2, format, needFlag)) {
        if (flag!=compareDates2(obj1.value, format, obj2.value, format)) {
			if (flag==1) {
				alert(errorMsg1 + "必须大于" + errorMsg2 + "。");
				obj1.focus();
				return false;
			} else if (flag==-1) {
				alert(errorMsg2 + "必须大于" + errorMsg1 + "。");
				obj1.focus();
				return false;
			} else if (flag==0) {
				alert(errorMsg1 + "必须等于" + errorMsg2 + "。");
				obj1.focus();
				return false;
			} else {
				alert("flag只能为1，0，-1。");
				return false;
			}
		} else {
			return true;
		}
    } else {
        return false;
    }
}


function checkFtp(obj,errorMsg,needFlag){
  var value = obj.value;

  if(needFlag)
  {
    if(value == "")
    {
      alert("请输入" + errorMsg + "。");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }

  var regexp=/^(((\w)+[.]){1,}(net|com|cn|org|cc|tv|[0-9]{1,3})(((\/[\~]*|\\[\~]*)(\w)+)|[.](\w)+)*(((([?](\w)+){1}[=]*))*((\w)+){1}([\&](\w)+[\=](\w)+)*)*)$/;
  var flag = regexp.test(value);
  if(!flag)
  {
    alert("请输入正确的" + errorMsg );
    obj.focus();
  }
  return flag;
}


function onSubmitValidator(input){
	if(input.onsubmit()){
		input.submit();
	}
}
