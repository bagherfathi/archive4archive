<?php
header('Content-type: text/html; charset=gbk');
include ("lib/configreader.php");
$iniconfig->load();
include ("lib/db.php");
$selectsql = "update sourcetable set done='1' where trim(id)='".$_GET["id"]."'";
$result1 = mysql_query($selectsql);
$selectsql1 = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='1' order by id";
$result11 = mysql_query($selectsql1);
//echo $selectsql1;
echo "<table border='1'>\n";
echo "<tr><td>请选择</td><td>ID</td><td>身份证号</td><td>姓名</td><td>功能代码</td><td>业务流水号</td><td>回退标志</td><td>操作员编号</td><td>操作时间</td><td>操作年月</td></tr>\n";
while ($rs = mysql_fetch_array($result11)) {
	echo "	<tr><td>已归档</td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
}
echo "</table>";
mysql_close($con);
?>
