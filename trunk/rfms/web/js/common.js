function onlyNum() 
{ //alert(event.keyCode);
if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39)&&!(event.keyCode==190)) 
if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 
event.returnValue=false; 
} 

function onClose(){
  self.close();
}
function onBack(){
 history.go(-1);
}

function submitForm(afrom){
  loadOn();
  afrom.submit();
}
//--------------------------------------
// ��ȫ������
function redirectWin(url) {
	var scrWidth  = screen.availWidth;
	var scrHeight = screen.availHeight;
	var zpatuCSMWin = window.open(url, "zpatuCSMWin", "resizable=1");
	zpatuCSMWin.moveTo(0, 0);
	zpatuCSMWin.resizeTo(scrWidth, scrHeight);
	window.opener = null;
	window.close(); 
}
//--------------------------------------
// ��ʾ���������б�
function showContentList(n) {
	var ContentListStyle = document.all("divContentList"+n).style;
	if (ContentListStyle.display == "none") {
		ContentListStyle.display = "";
		document.images("imgContentList"+n).src = "../images/minus.gif";
	} else {
		ContentListStyle.display = "none";
		document.images("imgContentList"+n).src = "../images/plus.gif";
	}
}
//--------------------------------------
//--------------------------------------
// ��ť���
function disableButtonById(n) {
	var spaDivs = document.body.all.tags("SPAN");
	var btnDivs = new Array();
	var i = 0;
	var j = 0;
	for(i=0; i<spaDivs.length; i++) {
		if (spaDivs[i].className == "clsButtonFace") {
			btnDivs[j] = spaDivs[i];
			j++;
		}
	}
	if (n==0) {
		for (i=0; i<j; i++) {
			btnDivs[i].className = "clsButtonDisabled"
			btnDivs[i].innerHTML = btnDivs[i].innerText;
		}
	} else {
		btnDivs[n-1].className = "clsButtonDisabled"
		btnDivs[n-1].innerHTML = btnDivs[n-1].innerText;
	}
	return true;
}
//--------------------------------------
// �򿪶Ի���
function openwin(urlstr, width, height) {
	window.open(urlstr,"","width="+width+"px,height="+height+"px,top="+(screen.availHeight-300)/2+",left="+(screen.availWidth-500)/2)
}

function openwinCenter(urlstr, width, height) {
	window.open(urlstr,"","width="+width+"px,height="+height+"px,top="+(screen.availHeight-height)/2+",left="+(screen.availWidth-width)/2)
}

//--------------------------------------
// �򿪶Ի���
function showDialog(urlstr, width, height) {
	var xml=showModalDialog(urlstr, window, "dialogWidth:"+width+"px; dialogHeight:"+height+"px; status:0;scroll:0;help:0");
	if (xml == undefined)
		return false;
	alert(xml);
}

//--------------------------------------
// ����ʾ�Ի���
 
function onSure(message){ 
  alert(message);
} 

//--------------------------------------
// ��ѡ��Ի���

function onSubmit(message){
  return confirm(message);
}


//--------------------------------------
// ����������ֱ��
var keylist="123456789"
var temp=""

function generatepass(plength){
	temp=""
	for (i=0;i<plength;i++)
		temp+=keylist.charAt(Math.floor(Math.random()*keylist.length))
		return temp
}

function populateform(enterlength){
	document.all("textfieldnum").value=generatepass(enterlength)
}
//--------------------------------------
// ������ʱִ��
function checkSearch(){
		// ��ʾ������
		divProcessing.style.display="";
		divSearch.style.display="";
		gourl();
}

//TabЧ��
function switchTab(tabpage,tabid){
    var oItem = document.getElementById(tabpage);   
	for(var i=0;i<oItem.children.length;i++){
		var x = oItem.children(i);
		x.className = "";
		var y = x.getElementsByTagName("a");
		y[0].style.color="#000000";
	}
	document.getElementById(tabid).className = "Selected";
	var dvs=document.getElementById("cnt").getElementsByTagName("div");
	for (var i=0;i<dvs.length;i++){
	  if (dvs[i].id==("d"+tabid))
		dvs[i].style.display="block";
	  else
		dvs[i].style.display="none";
	}
}
// �ļ��˵�
function menu4_Tab(tabpage,tabid){
        var oItem = document.getElementById(tabpage);   
	for(var i=0;i<oItem.children.length;i++){
		var x = oItem.children(i);	
		x.className = "";
		var y = x.getElementsByTagName("a");
		y[0].style.color="#333333";
	}	
	document.getElementById(tabid).className = "Selected";
}

//--------------------------------------
// ����JS���д�����ʾ

function killErrors() {
	return true;
}


function doKeyDown(obj)
{
    switch(event.keyCode)
    {
    case 39:
    case 40:
        event.keyCode = 9;
    break;
    case 37:
    case 38:
        event.keyCode = 9;
    break;
    }
}

//���radio�� ֵ
//����cName���ؼ�����

function GetRadioValue(cName){
    var obj;    
    obj=document.getElementsByName(cName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}


function isDateById(aId){
  var bbtt=document.getElementById(aId);
  if(chkdate(bbtt.value)==0)	
   {    alert(bbtt.name+"��������,��ȷ��!");
   	bbtt.focus();
   	return false;
   	}	
}	
//��������chkdate
//���ܽ��ܣ�����Ƿ�Ϊ����
//����˵����Ҫ�����ַ���datestr,��ʽ�硰2001��12��21��
//����ֵ��0����������  1��������
function chkdate(datestr)
{
	var lthdatestr
	if (datestr != "")
		lthdatestr= datestr.length ;
	else
		lthdatestr=0;

	var tmpy="";
	var tmpm="";
	var tmpd="";
	//var datestr;
	var status;
	status=0;
	if ( lthdatestr== 0)
		return false

	for (i=0;i<lthdatestr;i++)
	{
		if ((datestr.charAt(i)<'0'||datestr.charAt(i)>'9')&&datestr.charAt(i)!='-')
		{
			return 0
		}
	}

	for (i=0;i<lthdatestr;i++)
	{	if (datestr.charAt(i)== '-')
		{
			status++;
		}
		if (status>2)
		{
			//alert("Invalid format of date!");
			return 0;
		}
		if ((status==0) && (datestr.charAt(i)!='-'))
		{
			tmpy=tmpy+datestr.charAt(i)
		}
		if ((status==1) && (datestr.charAt(i)!='-'))
		{
			tmpm=tmpm+datestr.charAt(i)
		}
		if ((status==2) && (datestr.charAt(i)!='-'))
		{
			tmpd=tmpd+datestr.charAt(i)
		}

	}
	year=new String (tmpy);
	month=new String (tmpm);
	day=new String (tmpd)
	//tempdate= new String (year+month+day);
	//alert(tempdate);
	if ((tmpy.length!=4) || (tmpm.length>2) || (tmpd.length>2))
	{
		//alert("Invalid format of date!");
		return 0;
	}
	if (!((1<=month) && (12>=month) && (31>=day) && (1<=day)) )
	{
		//alert ("Invalid month or day!");
		return 0;
	}
	if (!((year % 4)==0) && (month==2) && (day==29))
	{
		//alert ("This is not a leap year!");
		return 0;
	}
	if ((month<=7) && ((month % 2)==0) && (day>=31))
	{
		//alert ("This month is a small month!");
		return 0;

	}
	if ((month>=8) && ((month % 2)==1) && (day>=31))
	{
		//alert ("This month is a small month!");
		return 0;
	}
	if ((month==2) && (day==30))
	{
		//alert("The Febryary never has this day!");
		return 0;
	}

	return 1;
}
