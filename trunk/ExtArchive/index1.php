<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>�鵵�鿴</title>
<link href="js/css.css" rel="stylesheet" type="text/css" />
<SCRIPT src="js/sx.js" type=text/javascript></SCRIPT>
</head>
<body style="margin-top: 0px;">
<script>
	var isshowdown=false;
	var XMLHttpReq;
	 function createXMLHttpRequest() {
        if (window.XMLHttpRequest) { //Mozilla �����
            XMLHttpReq = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) { // IE�����
            try {
                XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                }
            }
        }
    }
    
	function sendRequest(id) {
        createXMLHttpRequest();
        var rad = Math.round(Math.random() * 10000);
        var url = "archive.php?id=" + id+"&rad="+rad;
        XMLHttpReq.open("GET", url, true);
        XMLHttpReq.onreadystatechange = processResponse;//ָ����Ӧ����
        XMLHttpReq.send(null);  // ��������
  	}
    // ��������Ϣ����
    
    function processResponse() {
        if (XMLHttpReq.readyState == 4) { // �ж϶���״̬
            if (XMLHttpReq.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
//            	alert(XMLHttpReq.responseText);
            	var oldTable=document.getElementById("showdowntable");
//            	alert(oldTable);
				oldTable.innerHTML=XMLHttpReq.responseText;
//				alert(oldTable);
            } else { //ҳ�治����
                //                alert("���������ҳ�����쳣��");
            }
        }
    }
    
	function showdone(){
		showdown=document.getElementById("showdownId");
		if(showdown!=null){
			if(isshowdown==false){
				isshowdown=true;
				showdown.innerHTML="<button onClick='javascript:showdone()'>��ʾδ�鵵</button>";					
				document.getElementById("showdowntable").style.display="block";	
				document.getElementById("notshowdowntable").style.display="none";				
			}else{
				isshowdown=false;
				showdown.innerHTML="<button onClick='javascript:showdone()'>��ʾ�ѹ鵵</button>";	
				document.getElementById("notshowdowntable").style.display="block";	
				document.getElementById("showdowntable").style.display="none";				
			}
		}
	}
	
	function archive(){
		var lines=document.getElementsByName("line");
		for(var i=0; i < lines.length ; i++){
			if(lines[i].checked==true){
//				alert(lines[i].value);
				var child=document.getElementById(lines[i].value);
//				alert(child.innerHTML);
				var parent=child.parentNode;
//				alert(parent.innerHTML);
				parent.removeChild(child);
//				alert(lines[i].value);
				sendRequest(lines[i].value);
			}
		}
	} 
</script>
<?php
$today = date('Y-n-j');
//$yestoday = date("Y-n-j",strtotime("-1 day"));
include ("lib/sourcedb.php");
include ("lib/db.php");
$sql = "select id,���֤��,����,���ܴ���,ҵ����ˮ��,���˱�־,����Ա���,����ʱ��,��������  from SOURCETABLE where ����ʱ�� like '$today%' and ����Ա��� = trim('$OperNo') and ���� like '%��%' order by id";
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
?>
<table width="763" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td background="images/index_01.gif" height="33">&nbsp;</td>
	</tr>
	<tr>
		<td><img src="images/head_03.gif" width="763" height="82" /></td>
	</tr>
	<tr>
		<td>
			<div id="lib_Tab1">
				<div class="lib_Menubox lib_tabborder">
					<ul>
						<li id="one1" onclick="setTab('one',1,2)" class="hover">δ�鵵</li>
						<li id="one2" onclick="setTab('one',2,2)">�ѹ鵵</li>
					</ul>
				</div>
				<div>
					<div id="con_one_1"><!--�����б�ʼ-->
						<form action="index2.html" method="get">
<?php
//echo "<div id='notshowdowntable'>\n";
echo "<table  width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#E0E3EB' onmouseover='changeto()' onmouseout='changeback()'>";
echo "<tr><td height='22' bgcolor='#FFFFFF'>��ѡ��</td><td height='22' bgcolor='#FFFFFF'>ID</td><td height='22' bgcolor='#FFFFFF'>���֤��</td><td height='22' bgcolor='#FFFFFF'>����</td><td height='22' bgcolor='#FFFFFF'>���ܴ���</td><td height='22' bgcolor='#FFFFFF'>ҵ����ˮ��</td><td height='22' bgcolor='#FFFFFF'>���˱�־</td><td bgcolor='#FFFFFF'>����Ա���</td><td height='22' bgcolor='#FFFFFF'>����ʱ��</td><td height='22' bgcolor='#FFFFFF'>��������</td></tr>";
$deletesql = "delete from sourcetable where opertime<'$today 00:00:00'";
$result = mysql_query($deletesql);
while (OCIFetchInto($stmt, $result_array)) {
	$insertsql = "insert ignore into sourcetable (id,idc,name,funno,serial,rollback,operno,opertime,operdate,done) values ('$result_array[0]','$result_array[1]','$result_array[2]','$result_array[3]','$result_array[4]','$result_array[5]','$result_array[6]','$result_array[7]','$result_array[8]','0')";
	$result = mysql_query($insertsql);
}

$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='0' order by id";
$result1 = mysql_query($selectsql);
while ($rs = mysql_fetch_array($result1)) {
	echo "	<tr id='$rs[0]'><td bgcolor='#FFFFFF'><input type='checkbox' name='line' value ='$rs[0]'/></td><td bgcolor='#FFFFFF'>$rs[0]</td><td bgcolor='#FFFFFF'>$rs[1]</td><td bgcolor='#FFFFFF'>$rs[2]</td><td bgcolor='#FFFFFF'>$rs[3]</td><td bgcolor='#FFFFFF'>$rs[4]</td><td bgcolor='#FFFFFF'>$rs[5]</td><td bgcolor='#FFFFFF'>$rs[6]</td><td bgcolor='#FFFFFF'>$rs[7]</td><td bgcolor='#FFFFFF'>$rs[8]</td></tr>\n";
}
echo "</table>\n";
//echo "<table ><tr><td><button onClick='javascript:archive()'/>�鵵</button></td></tr></table>";
//echo "</div>\n";
?>
<!--�б����-->
							<table width="100%">
								<tr>
									<td>
									<div align="right" style="margin-right: 100px;"><input
										type=checkbox onclick=sel('line')>ȫѡ&nbsp;<input
										type="image" name="submit1" src="images/index_06.gif">&nbsp;<input
										type="image" name="submit2" src="images/index_08.gif">&nbsp;<input
										type="image" name="submit3" src="images/index_10.gif"></div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="con_one_2" style="display: none">
<?
$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='1' order by id";
$result1 = mysql_query($selectsql);

//echo "<div id='showdowntable' style='display: none'>\n";
echo "<table  width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#E0E3EB' onmouseover='changeto()' onmouseout='changeback()'>\n";
echo "<tr><td height='22' bgcolor='#FFFFFF'>��ѡ��</td><td height='22' bgcolor='#FFFFFF'>ID</td><td height='22' bgcolor='#FFFFFF'>���֤��</td><td height='22' bgcolor='#FFFFFF'>����</td><td height='22' bgcolor='#FFFFFF'>���ܴ���</td><td height='22' bgcolor='#FFFFFF'>ҵ����ˮ��</td><td height='22' bgcolor='#FFFFFF'>���˱�־</td><td height='22' bgcolor='#FFFFFF'>����Ա���</td><td height='22' bgcolor='#FFFFFF'>����ʱ��</td><td height='22' bgcolor='#FFFFFF'>��������</td></tr>\n";
while ($rs = mysql_fetch_array($result1)) {
	echo "	<tr><td height='20' bgcolor='#FFFFFF'>�ѹ鵵</td><td height='20' bgcolor='#FFFFFF'>$rs[0]</td><td height='20' bgcolor='#FFFFFF'>$rs[1]</td><td height='20' bgcolor='#FFFFFF'>$rs[2]</td><td height='20' bgcolor='#FFFFFF'>$rs[3]</td><td height='20' bgcolor='#FFFFFF'>$rs[4]</td><td height='20' bgcolor='#FFFFFF'>$rs[5]</td><td height='20' bgcolor='#FFFFFF'>$rs[6]</td><td height='20' bgcolor='#FFFFFF'>$rs[7]</td><td height='20' bgcolor='#FFFFFF'>$rs[8]</td></tr>\n";
}
echo "</table>";
//echo "</div>\n";
/*
 * Created on 2010-1-6
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
mysql_close($con);
?>
					</div>
				</div>
			</div>
		</td>
	</tr>
	<tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td height="1" bgcolor="#CCCCCC">
		</td>
	</tr>
	<tr>
		<td>
		<div align="center" style="margin-top: 5px;">CopyRight?2010 ���ݸ�����Ϣ�������޹�˾��ʡ�����ݼ�����������</div>
		</td>
	</tr>
</table>
</body>
</html>
