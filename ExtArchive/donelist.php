<?php
include ("lib/configreader.php");
$iniconfig->load();
include ("lib/db.php");
$selectsql = "select id,idc,name,funno,serial,rollback,operno,opertime,operdate,done from sourcetable where trim(opertime) like '$today%' and trim(operno)=trim('$OperNo') and trim(done)='1' order by id";
$result1 = mysql_query($selectsql);

echo "<div id='showdowntable' style='display: none'>\n";
echo "<table border='1'>\n";
echo "<tr><td>��ѡ��</td><td>ID</td><td>���֤��</td><td>����</td><td>���ܴ���</td><td>ҵ����ˮ��</td><td>���˱�־</td><td>����Ա���</td><td>����ʱ��</td><td>��������</td></tr> ";

while ($rs = mysql_fetch_array($result1)) {
	echo "	<tr><td>�ѹ鵵</td><td>$rs[0]</td><td>$rs[1]</td><td>$rs[2]</td><td>$rs[3]</td><td>$rs[4]</td><td>$rs[5]</td><td>$rs[6]</td><td>$rs[7]</td><td>$rs[8]</td></tr>\n";
}
echo "</table>";
echo "</div>\n";
?>
