<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<head>
<title>ѡ��Դ����</title>
<!-- selectsourcedb.php -->
<body>
<?php
$today = date('Y-n-j');
//echo $today;
include ("lib/sourcedb.php");
$sql = "select * from SOURCETABLE where ����ʱ�� like '$today%'";
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
?>
<table border='1'>
<?php
echo "<tr><td>��ѡ��</td><td>ID</td><td>���֤��</td><td>����</td><td>���ܴ���</td><td>ҵ����ˮ��</td><td>���˱�־</td><td>����Ա���</td><td>����ʱ��</td><td>��������</td></tr> ";
while (OCIFetchInto($stmt, $result_array)) {	
	echo "<tr><td><input type='checkbox' name='line$result_array[0]' value ='$result_array[0]'/><br></td><td>$result_array[0]</td><td>$result_array[1]</td><td>$result_array[2]</td><td>$result_array[3]</td><td>$result_array[4]</td><td>$result_array[5]</td><td>$result_array[6]</td><td>$result_array[7]</td><td>$result_array[8]</td></tr> ";
//	echo "<tr>ID:$result_array[0] ���֤��:$result_array[1] ����:$result_array[2] ���ܴ���:$result_array[3] ҵ����ˮ��:$result_array[4] ���˱�־:$result_array[5] ����Ա���:$result_array[6] ����ʱ��:$result_array[7] ��������:$result_array[8]</tr> ";
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
