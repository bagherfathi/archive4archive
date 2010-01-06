 <?
include ("configreader.php");
$iniconfig->load();
$Host = '' . $iniconfig->get('SourceDB.Host') . '';
$Port = '' . $iniconfig->get('SourceDB.Port') . '';
$TnsName = '' . $iniconfig->get('SourceDB.TnsName') . '';
$User = '' . $iniconfig->get('SourceDB.User') . '';
$Password = '' . $iniconfig->get('SourceDB.Password') . '';
$View = '' . $iniconfig->get('SourceDB.View') . '';
//echo 'INI: ' . $Host . '';
//echo "\n";
//echo 'INI: ' . $TnsName . '';
//echo "\n";
//echo 'INI: ' . $User . '';
//echo "\n";
//echo 'INI: ' . $Password . '';
//echo "\n";
$sid = "(DESCRIPTION=(ADDRESS=(PROTOCOL =TCP)(HOST=".$Host.")(PORT = ".$Port."))(CONNECT_DATA =(SID=".$TnsName.")))";
//echo 'INI: ' . $sid . '';
//echo "\n";
if ($conn = OCILogon($User,$Password,"$sid")) {
//	echo "SUCCESS ! Connected to database\n";
} else {
	echo "Failed :-( Could not connect to database\n";
}
//ora_Logoff($conn);
//phpinfo();
?> 