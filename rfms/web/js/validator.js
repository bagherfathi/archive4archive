//Desc����ǰ�����Ŀ�ж�Ҫ�õ�һЩ�ű���Ϊ�˱���ͳһ����ʹ�÷��㣬�ش˽���������
//�Ժ���ʲô�õġ�ͨ�õĽű����Լӽ���

function isInMaxLen(o,nMaxLen){  
  o.value=trim(o.value);
 if(o.value.length>nMaxLen){
   if(o.alt!=null&&o.alt!=""){
    alert(o.alt+"���ܳ���"+nMaxLen+"���ַ�");
   }
   else {
   alert("���ܳ���"+nMaxLen+"���ַ�");
   }
   o.value=o.value.substring(0,nMaxLen);
   o.focus();
   return false;
 }
 else{  
   return true;
 }
}   
//�ж��Ƿ�ѡ���˵�ǰҳ���һ�� checkbox
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

//����obj1��checked��ѡ�л�ѡ�����е�obj2
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
//����obj,flag��ѡ�л�ѡ��ĳһ��ֵselectValue
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
//�ж϶��󼯺��е�ֵΪselectValue�Ƿ�ѡ��
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
//�ı��Ƿ���ʾ
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

//�ı��Ƿ���ʾ
function expandView(obj, changeClassObj)
{
	obj.style.display = "";
	changeClassObj.className = "arrow_up";
}

//�ı��Ƿ���ʾ
function closeView(obj, changeClassObj)
{
	obj.style.display = "none";
	changeClassObj.className = "arrow_down";
}

//�ı�ҳ����ָ���� object ����ʾ
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

//����span��innerhtml
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
 * ��ȡ�ַ����ո���;
 */
function trim(sStr)
{
	return sStr.replace(/(^\s*)|(\s*$)/g,"");
}

//��֤��������ѡ��
function checkSelect(obj, errorMsg)
{
  if(obj && (obj.value =='' || obj.value == '0' || obj.value == '-1'))
  {
    alert("��ѡ��" + errorMsg + "��");
    obj.focus();
    return false;
  }
  
  return true;
}
//��֤������������,������ʾ���
function checkInputReturnAlert(obj, errorMsg)
{
  if(obj && trim(obj.value) == "")
  {
    return (errorMsg);        
  }  
  return "";
}
//��֤������������
function checkInput(obj, errorMsg)
{
  if(obj && trim(obj.value) == "")
  {
    alert("������" + errorMsg + "��");
    obj.focus();
    return false;
  }
  
  return true;
}

//��֤������������
function checkInputColseView(obj, errorMsg, table)
{
  if(obj && trim(obj.value) == "")
  {
    if ( table.style.display=='none' )
        table.style.display='';
    alert("������" + errorMsg + "��");
    obj.focus();
    return false;
  }
  
  return true;
}


//����ʱ�
function checkZipCode(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag;
}

//���绰����
function checkPhone(obj, errorMsg, needFlag) 
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag;
}

//����ֻ�����
function checkMobile(obj, errorMsg, needFlag) 
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag;
}

//���Email��ַ
function checkEmail(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag;
}

// ���IP��ַ
function checkIp(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
      obj.focus();
      return false;
    }
  }
  else
  {
    if(value == "")
      return true;
  }
    
  var error = "��������ȷ��" + errorMsg + "��";
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

//���URL
function checkUrl(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
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
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag;
}

//������������9λ
function checkNumber(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
	  obj.focus();
  }
  return flag; 
}
//��鸡����
function checkFloat(obj, errorMsg, needFlag)
{
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
		//˵�����ַ����Ϸ�
			alert("�ֶ�'"+errorMsg+"'����������,��������ĸ�������Ƿ����ϣ�")
			obj1.focus();
			return false;
		}
	}
	//˵���Ϸ�
  return true; 
}
/*
 funtion checkdecimal(obj1,obj1Str)
//��������checkdecimal(obj1,obj1Str)
//������������,С�����ֲ��ܳ�����λ
//����˵����Ҫ��������num,����������
//����ֵ��0������ȷ  1����ȷ
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
		       alert(obj1Str+",С����󲻿��Գ���2λ!");
		       obj1.focus();
			return false;
		}
	}
			return true;
}

//��������������9λ
function checkPositiveNumber(obj, errorMsg, needFlag)
{
  var value = obj.value;
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "������������");
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
//��������������9λ,ͬʱ�����Ƿ����أ�������أ��򿪶�Ӧ�����
function checkPositiveNumber2(obj, errorMsg, needFlag, divTable ) {
  var value = obj.value;
  if(needFlag)
  {
    if(value == "")
    {
      if ( divTable.style.display = "none" )
        divTable.style.display = "";
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "������������");
	obj.focus();
  }
  return flag;
}
//�Ƚ�����
function compareNumber(obj, compareValue, errorMsg, needFlag)
{
  if(!checkNumber(obj, errorMsg, needFlag)) {
    alert(errorMsg+"������������");
    obj.focus();
    return false;
  }   
  if(!needFlag && obj.value == "") {   
    return true;
  }
  
  var value = parseInt(obj.value);    
  if(value > compareValue)
  {
    alert(errorMsg + "���ܴ���" + compareValue + "��");
    obj.focus();
    return false;
  }
  
  return true;
}
//�Ƚ�����
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
    alert(errorMsg + "���ܴ���" + compareValue + "��");
    obj.focus();
    return false;
  }
  
  return true;
}
//���MAC��ַ
function checkMacAddr(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag; 
}

//����ļ��������Ļ�������ĸ�»���.exe|zip|rar��
function checkFileName(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��");
    obj.focus();
  }
  return flag; 
}

//����Ƿ�����ĸ��ͷ��ֻ������ĸ�����֡��»������
function checkLoginName(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "������ĸ��ͷ��ֻ�ܰ�����ĸ�����֡��»��ߣݡ�");
    obj.focus();
  }
  return flag; 
}

//����Ƿ�����ĸ��ͷ��ֻ������ĸ�����֡�'-'�����
function checkAppName(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "������ĸ��ͷ��ֻ�ܰ�����ĸ�����֡�'-'�ݡ�");
    obj.focus();
  }
  return flag; 
}

//ֻ�ܰ�����ĸ������
//needFlag �Ƿ����
function checkPassword(obj, errorMsg, needFlag)
{  
  var value = obj.value;
  
  if(needFlag)
  {
    if(value == "")
    {
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg + "��ֻ�ܰ�����ĸ�����֣ݡ�");
    obj.focus();
  }
  return flag; 
}

/*
ֻ�ܰ�����ĸ������
needFlag �Ƿ����
*/
function checkTwoPassword(obj1, errorMsg1, obj2, errorMsg2, needFlag) {

  if (checkPassword(obj1, errorMsg1, needFlag) && checkPassword(obj2, errorMsg2, needFlag)) {
	if (obj1.value!=obj2.value) {
		alert("�����������벻һ�¡�");
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
//	�ж����ڸ�ʽ
//	ע�⣺Ҫʹ������������������date.js
// -------------------------------------------------------------------
function checkDate(obj, errorMsg, format, needFlag) {
	if (needFlag) {
		if (obj.value=="") {
			alert("������" + errorMsg + "��");
			obj.focus();
			return false;
		}
	} else {
		if (obj.value=="") {
			return true;
		}
	}

	if (!isDate(obj.value, format)) {
		alert("��������ȷ��" + errorMsg + "��"+format+"�ݡ�");
		obj.focus();
		return false;
	}
	return true;
}

// -------------------------------------------------------------------
// �Ƚ϶����Ƿ�Ϊ�Ϸ������ڣ��ҷ���flag������
//	1 if obj1 is greater than obj2
//	0 if they are the same
//	-1 if obj2 is greater than obj1
//	ע�⣺Ҫʹ������������������date.js
// -------------------------------------------------------------------

function checkCompareDate(obj1, errorMsg1, obj2, errorMsg2, needFlag,format) {
    if (checkDate(obj1, errorMsg1, format, needFlag) && checkDate(obj2, errorMsg2, format, needFlag)) {
        if (compareDates(obj1.value, format, obj2.value, format) != 0 && needFlag ) {
			alert(errorMsg1 + "���ܴ���" + errorMsg2 + "��");
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
			alert(errorMsg1 + "���ܴ���" + errorMsg2 + "��");
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
// �Ƚ϶����Ƿ�Ϊ�Ϸ������ڣ��ҷ���flag������
//   1 if obj1 is greater than obj2
//   0 if they are the same
//  -1 if obj2 is greater than obj1
//  -2 if either of the objs is in an invalid format
//	ע�⣺Ҫʹ������������������date.js
// -------------------------------------------------------------------
function checkCompareDate2(obj1, errorMsg1, obj2, errorMsg2, needFlag, flag,format) {
    if (checkDate(obj1, errorMsg1, format, needFlag) && checkDate(obj2, errorMsg2, format, needFlag)) {
        if (flag!=compareDates2(obj1.value, format, obj2.value, format)) {
			if (flag==1) {
				alert(errorMsg1 + "�������" + errorMsg2 + "��");
				obj1.focus();
				return false;
			} else if (flag==-1) {
				alert(errorMsg2 + "�������" + errorMsg1 + "��");
				obj1.focus();
				return false;
			} else if (flag==0) {
				alert(errorMsg1 + "�������" + errorMsg2 + "��");
				obj1.focus();
				return false;
			} else {
				alert("flagֻ��Ϊ1��0��-1��");
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
      alert("������" + errorMsg + "��");
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
    alert("��������ȷ��" + errorMsg );
    obj.focus();
  }
  return flag;
}


function onSubmitValidator(input){
	if(input.onsubmit()){
		input.submit();
	}
}
