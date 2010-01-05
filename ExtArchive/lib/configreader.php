<?php
/**
* 读取4中配置的表信息,现支持php.ini,xml.yaml
*/
class Settings {
	var $_settings = array ();
	/**
	* 获取某些设置的值
	*
	* @param unknown_type $var
	* @return unknown
	*/
	function get($var) {
		$var = explode('.', $var);
		$result = $this->_settings;
		foreach ($var as $key) {
			if (!isset ($result[$key])) {
				return false;
			}
			$result = $result[$key];
		}
		return $result;
		// trigger_error ('Not yet implemented', E_USER_ERROR);//引发一个错误
	}
	function load() {
		trigger_error('Not yet implemented', E_USER_ERROR);
	}
}

//读取INI文件的子类
Class Settings_INI Extends Settings {
//	function load($file) {
//		if (file_exists($file) == false) {
//			return false;
//		}
//		$this->_settings = parse_ini_file($file, true);
//	}
	private $file;
	function load() {
		$file="../config/extarchive.ini";
		if (file_exists($file) == false) {
			return false;
		}
		$this->_settings = parse_ini_file($file, true);
	}
}

$iniconfig = new Settings_INI;
$iniconfig->load();

//读取PHP文件的子类
//Class Settings_PHP Extends Settings {
//	function load($file) {
//		if (file_exists($file) == false) {
//			return false;
//		}
//		// Include file
//		include ($file);
//		unset ($file); //销毁指定变量
//		$vars = get_defined_vars(); //返回所有已定义变量的列表,数组,变量包括服务器等相关变量,
//		//通过foreach吧$file引入的变量给添加到$_settings这个成员数组中去.
//		foreach ($vars as $key => $val) {
//			if ($key == 'this')
//				continue;
//
//			$this->_settings[$key] = $val;
//		}
//	}
//}

//读取xml文件子类
//Class Settings_XML Extends Settings {
//	function load($file) {
//		if (file_exists($file) == false) {
//			return false;
//		}
//
//		/**xmllib.php为PHP XML Library, version 1.2b,相关连接:http://keithdevens.com/software/phpxml
//		xmllib.php主要特点是把一个数组转换成一个xml或吧xml转换成一个数组
//		XML_unserialize:把一个xml给转换 成一个数组
//		XML_serialize:把一个数组转换成一个xml
//		自PHP5起,simpleXML就很不错,但还是不支持将xml转换成数组的功能,所以xmlLIB还是很不错的. 
//		*/
//		include ('xmllib.php');
//		$xml = file_get_contents($file);
//		$data = XML_unserialize($xml);
//		$this->_settings = $data['settings'];
//	}
//}

/*****************                    例程                       ***************/

//读取PHP配置文件××××××××××××××××
/**
* 针对PHP的配置,如有配置文件
* $file=
<?php
$db = array();

// Enter your database name here:
$db['name'] = 'test';

// Enter the hostname of your MySQL server:
$db['host'] = 'localhost';

?>


具体调用:
include ('settings.php'); //原始环境假设每个类为单独的一个类名.php文件

// Load settings (PHP)
$settings = new Settings_PHP;
$settings->load('config.php');

echo 'PHP: ' . $settings->get('db.host') . '';

*
*/


//读取ini配置文件××××××××××××××××××
/**
* ini例子:
* [db]
name = test
host = localhost
调用例子:
$settings = new Settings_INI;
$settings->load('config.ini'); 
echo 'INI: ' . $settings->get('db.host') . '';

*
*/


//读取xml配置文件××××××××××××××××××××
/**
* XML例子:
<?xml version="1.0" encoding="UTF-8"?>
<settings>
         <db>
                   <name>test</name>
                   <host>localhost</host>
         </db>
</settings>
调用例子:
// Load settings (XML)
$settings = New Settings_XML;
$settings->load('config.xml');
echo 'XML: ' . $settings->get('db.host') . '';

*
*/
?>