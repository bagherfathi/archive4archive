<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<head>
<title>选择源数据</title>
<!-- selectsourcedb.php -->
<body>
<?php
$today = date('Y-n-j');
//echo $today;
include ("lib/sourcedb.php");
$sql = "select * from SOURCETABLE where 操作时间 like '$today%'";
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
?>
<table border='1'>
<?php
echo "<tr><td>请选择</td><td>ID</td><td>身份证号</td><td>姓名</td><td>功能代码</td><td>业务流水号</td><td>回退标志</td><td>操作员编号</td><td>操作时间</td><td>操作年月</td></tr> ";
while (OCIFetchInto($stmt, $result_array)) {	
	echo "<tr><td><input type='checkbox' name='line$result_array[0]' value ='$result_array[0]'/><br></td><td>$result_array[0]</td><td>$result_array[1]</td><td>$result_array[2]</td><td>$result_array[3]</td><td>$result_array[4]</td><td>$result_array[5]</td><td>$result_array[6]</td><td>$result_array[7]</td><td>$result_array[8]</td></tr> ";
//	echo "<tr>ID:$result_array[0] 身份证号:$result_array[1] 姓名:$result_array[2] 功能代码:$result_array[3] 业务流水号:$result_array[4] 回退标志:$result_array[5] 操作员编号:$result_array[6] 操作时间:$result_array[7] 操作年月:$result_array[8]</tr> ";
	echo "\n";
}
/*
 * Created on 2010-1-6
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
?>
</table>
</body>
