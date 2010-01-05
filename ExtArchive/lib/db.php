<?php
include ("configreader.php");

$settings = new Settings_INI;
$settings->load('../config/extarchive.ini'); 
$Host = ''. $settings->get('DB.Host') .'';
$Name = ''. $settings->get('DB.Name') .'';
$User = ''. $settings->get('DB.User') .'';
$Password = ''. $settings->get('DB.Password') .'';
echo 'INI: '.$Host.'';
echo "\n";
echo 'INI: '.$User.'';
echo "\n";
echo 'INI: '.$Password.'';

$con = mysql_connect($Host, $User, $Password)or die("Could not connect");  
mysql_select_db($Name,$con) or die("Could not select database");
?>
