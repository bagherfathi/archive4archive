<?php
include ("lib/sourcedb.php");
$sql = "select * from SOURCETABLE";
$stmt = OCIParse($conn, $sql);
OCIExecute($stmt);
while (OCIFetchInto($stmt1, $result_array1)) {	
	echo "ID:.$result_array1[0]. 身份证号:.$result_array1[1]. 姓名:.$result_array1[2]. 功能代码:.$result_array1[3]. 业务流水号:.$result_array1[4]. 回退标志:.$result_array1[5]. 操作员编号:.$result_array1[6]. 操作时间:.$result_array1[7]. 操作年月:.$result_array1[8]. ";
	echo "\n";
}
/*
 * Created on 2010-1-6
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
?>
