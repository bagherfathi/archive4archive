<?php
include ("lib/sourcedb.php");
$sql = "select * from SOURCETABLE";
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
while (OCIFetchInto($stmt1, $result_array1)) {	
	echo "ID:.$result_array1[0]. ���֤��:.$result_array1[1]. ����:.$result_array1[2]. ���ܴ���:.$result_array1[3]. ҵ����ˮ��:.$result_array1[4]. ���˱�־:.$result_array1[5]. ����Ա���:.$result_array1[6]. ����ʱ��:.$result_array1[7]. ��������:.$result_array1[8]. ";
	echo "\n";
}
/*
 * Created on 2010-1-6
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
?>
