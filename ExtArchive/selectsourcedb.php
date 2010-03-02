<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<head>

<title>选择源数据</title>
<!-- selectsourcedb.php -->
<body>
<script>
	var isshowdown=false;
	var XMLHttpReq;
	 function createXMLHttpRequest() {
        if (window.XMLHttpRequest) { //Mozilla 浏览器
            XMLHttpReq = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) { // IE浏览器
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
        XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
        XMLHttpReq.send(null);  // 发送请求
  	}
    // 处理返回信息函数
    
    function processResponse() {
        if (XMLHttpReq.readyState == 4) { // 判断对象状态
            if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
//            	alert(XMLHttpReq.responseText);
            	var oldTable=document.getElementById("showdowntable");
//            	alert(oldTable);
				oldTable.innerHTML=XMLHttpReq.responseText;
//				alert(oldTable);
            } else { //页面不正常
                //                alert("您所请求的页面有异常。");
            }
        }
    }
    
	function showdone(){
		showdown=document.getElementById("showdownId");
		if(showdown!=null){
			if(isshowdown==false){
				isshowdown=true;
				showdown.innerHTML="<button onClick='javascript:showdone()'>显示未归档</button>";					
				document.getElementById("showdowntable").style.display="block";	
				document.getElementById("notshowdowntable").style.display="none";				
			}else{
				isshowdown=false;
				showdown.innerHTML="<button onClick='javascript:showdone()'>显示已归档</button>";	
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
$sql = "select id,身份证号,姓名,功能代码,业务流水号,回退标志,操作员编号,操作时间,操作年月  from SOURCETABLE where 操作时间 like '$today%' and 操作员编号 = trim('$OperNo') and 姓名 like '%秀%' order by id";
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
?>

<table>
	<tr>
		<td><div id="showdownId"><button onClick="javascript:showdone()">显示已归档</button></div></td>
	</tr>
</table>

<?php
echo "<div id='notshowdowntable'>\n";
echo "<table border='1'>";
echo "<tr><td>请选择</td><td>ID</td><td>身份证号</td><td>姓名</td><td>功能代码</td><td>业务流水号</td><td>回退标志</td><td>操作员编号</td><td>操作时间</td><td>操作年月</td></tr> ";
$deletesql =  "delete from sourcetable where opertime<'$today 00:00:00'";
$result = mysql_query($deletesql);
while (OCIFetchInto($stmt, $result_array)) {
	$insertsql = "insert ignore into sourcetable (id,idc,name,funno,serial,rollback,operno,opertime,operdate,done) values ('$result_array[0]','$result_array[1]','$result_array[2]','$result_array[3]','$result_array[4]','$result_array[5]','$result_array[6]','$result_array[7]','$result_array[8]','0')";
	$result = mysql_query($insertsql);
}

$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='0' order by id";
$result1 = mysql_query($selectsql);
while ($rs = mysql_fetch_array($result1)) {
		echo "	<tr id='$rs[0]'><td><input type='checkbox' name='line' value ='$rs[0]'/></td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
}
echo "</table>\n";
echo "<table ><tr><td><button onClick='javascript:archive()'/>归档</button></td></tr></table>";
echo "</div>\n";

$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='1' order by id";
$result1 = mysql_query($selectsql);

echo "<div id='showdowntable' style='display: none'>\n";
echo "<table border='1'>\n";
echo "<tr><td>请选择</td><td>ID</td><td>身份证号</td><td>姓名</td><td>功能代码</td><td>业务流水号</td><td>回退标志</td><td>操作员编号</td><td>操作时间</td><td>操作年月</td></tr>\n";
while ($rs = mysql_fetch_array($result1)) {
	echo "	<tr><td>已归档</td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
}
echo "</table>";
echo "</div>\n";
/*
 * Created on 2010-1-6
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
mysql_close($con);
?>
</body>
