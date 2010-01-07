<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<head>

<title>选择源数据</title>
<!-- selectsourcedb.php -->
<body>
<script>
	var isshowdown=false;
	function showdone(){
		showdown=document.getElementById("showdown");
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
</script>
<?php
$today = date('Y-n-j');
//echo $today;
include ("lib/sourcedb.php");
include ("lib/db.php");
$sql = "select id,身份证号,姓名,功能代码,业务流水号,回退标志,操作员编号,操作时间,操作年月  from SOURCETABLE where 操作时间 like '$today%' and 操作员编号 = trim('$OperNo') order by id";
//echo $sql;
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
?>

<table>
	<tr>
		<td><div id="showdown"><button onClick="javascript:showdone()">显示已归档</button></div></td>
	</tr>
</table>

<?php
echo "<div id='notshowdowntable'>\n";
echo "<table border='1'>";
echo "<tr><td>请选择</td><td>ID</td><td>身份证号</td><td>姓名</td><td>功能代码</td><td>业务流水号</td><td>回退标志</td><td>操作员编号</td><td>操作时间</td><td>操作年月</td></tr> ";
while (OCIFetchInto($stmt, $result_array)) {
	//	echo "<tr><td><input type='checkbox' name='line$result_array[0]' value ='$result_array[0]'/><br></td><td>$result_array[0]</td><td>$result_array[1]</td><td>$result_array[2]</td><td>$result_array[3]</td><td>$result_array[4]</td><td>$result_array[5]</td><td>$result_array[6]</td><td>$result_array[7]</td><td>$result_array[8]</td></tr> ";
	//	echo "<tr>ID:$result_array[0] 身份证号:$result_array[1] 姓名:$result_array[2] 功能代码:$result_array[3] 业务流水号:$result_array[4] 回退标志:$result_array[5] 操作员编号:$result_array[6] 操作时间:$result_array[7] 操作年月:$result_array[8]</tr> ";
	//	echo "\n";
	$insertsql = "insert ignore into sourcetable (id,idc,name,funno,serial,rollback,operno,opertime,operdate,done) values ('$result_array[0]','$result_array[1]','$result_array[2]','$result_array[3]','$result_array[4]','$result_array[5]','$result_array[6]','$result_array[7]','$result_array[8]','0')";
	//	$sql=mb_convert_encoding($insertsql, "GBK", "UTF-8"); 
	//	echo $sql;
	$result = mysql_query($insertsql);
}
//$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='0' order by id";
$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='0' order by id";
//echo $selectsql;
$result1 = mysql_query($selectsql);
while ($rs = mysql_fetch_array($result1)) {
//	if (trim($rs[9]) == '0') {
		echo "	<tr><td><input type='checkbox' name='line$rs[0]' value ='$rs[0]'/></td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
//	} else {
//		echo "	<tr><td>已归档</td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
//	}
}
echo "</table>\n";
echo "</div>\n";

$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='1' order by id";
$result1 = mysql_query($selectsql);

echo "<div id='showdowntable' style='display: none'>\n";
echo "<table border='1'>\n";
echo "<tr><td>请选择</td><td>ID</td><td>身份证号</td><td>姓名</td><td>功能代码</td><td>业务流水号</td><td>回退标志</td><td>操作员编号</td><td>操作时间</td><td>操作年月</td></tr> ";

while ($rs = mysql_fetch_array($result1)) {
//	if (trim($rs[9]) == '0') {
//		echo "	<tr style='display:none'><td><input type='checkbox' name='line$rs[0]' value ='$rs[0]'/></td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
//	} else {
		echo "	<tr><td>已归档</td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
//	}
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
