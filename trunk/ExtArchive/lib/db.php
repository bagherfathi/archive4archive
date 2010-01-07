<?php
//include ("configreader.php");
//$iniconfig->load();
$Host = ''. $iniconfig->get('DB.Host') .'';
$Name = ''. $iniconfig->get('DB.Name') .'';
$User = ''. $iniconfig->get('DB.User') .'';
$Password = ''. $iniconfig->get('DB.Password') .'';
//echo 'INI: '.$Host.'';
//echo "\n";
//echo 'INI: '.$Name.'';
//echo "\n";
//echo 'INI: '.$User.'';
//echo "\n";
//echo 'INI: '.$Password.'';

$con = mysql_connect($Host, $User, $Password)or die("Could not connect");  
mysql_select_db($Name,$con) or die("Could not select database");
mysql_query("SET NAMES 'GBK'");
//$charset = mysql_client_encoding($con);
//echo $charset;
?>
