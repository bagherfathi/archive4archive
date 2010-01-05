<?php
/**
* ��ȡ4�����õı���Ϣ,��֧��php.ini,xml.yaml
*/
class Settings {
	var $_settings = array ();
	/**
	* ��ȡĳЩ���õ�ֵ
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
		// trigger_error ('Not yet implemented', E_USER_ERROR);//����һ������
	}
	function load() {
		trigger_error('Not yet implemented', E_USER_ERROR);
	}
}

//��ȡINI�ļ�������
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

//��ȡPHP�ļ�������
//Class Settings_PHP Extends Settings {
//	function load($file) {
//		if (file_exists($file) == false) {
//			return false;
//		}
//		// Include file
//		include ($file);
//		unset ($file); //����ָ������
//		$vars = get_defined_vars(); //���������Ѷ���������б�,����,������������������ر���,
//		//ͨ��foreach��$file����ı�������ӵ�$_settings�����Ա������ȥ.
//		foreach ($vars as $key => $val) {
//			if ($key == 'this')
//				continue;
//
//			$this->_settings[$key] = $val;
//		}
//	}
//}

//��ȡxml�ļ�����
//Class Settings_XML Extends Settings {
//	function load($file) {
//		if (file_exists($file) == false) {
//			return false;
//		}
//
//		/**xmllib.phpΪPHP XML Library, version 1.2b,�������:http://keithdevens.com/software/phpxml
//		xmllib.php��Ҫ�ص��ǰ�һ������ת����һ��xml���xmlת����һ������
//		XML_unserialize:��һ��xml��ת�� ��һ������
//		XML_serialize:��һ������ת����һ��xml
//		��PHP5��,simpleXML�ͺܲ���,�����ǲ�֧�ֽ�xmlת��������Ĺ���,����xmlLIB���Ǻܲ����. 
//		*/
//		include ('xmllib.php');
//		$xml = file_get_contents($file);
//		$data = XML_unserialize($xml);
//		$this->_settings = $data['settings'];
//	}
//}

/*****************                    ����                       ***************/

//��ȡPHP�����ļ���������������������������������
/**
* ���PHP������,���������ļ�
* $file=
<?php
$db = array();

// Enter your database name here:
$db['name'] = 'test';

// Enter the hostname of your MySQL server:
$db['host'] = 'localhost';

?>


�������:
include ('settings.php'); //ԭʼ��������ÿ����Ϊ������һ������.php�ļ�

// Load settings (PHP)
$settings = new Settings_PHP;
$settings->load('config.php');

echo 'PHP: ' . $settings->get('db.host') . '';

*
*/


//��ȡini�����ļ�������������������������������������
/**
* ini����:
* [db]
name = test
host = localhost
��������:
$settings = new Settings_INI;
$settings->load('config.ini'); 
echo 'INI: ' . $settings->get('db.host') . '';

*
*/


//��ȡxml�����ļ�����������������������������������������
/**
* XML����:
<?xml version="1.0" encoding="UTF-8"?>
<settings>
         <db>
                   <name>test</name>
                   <host>localhost</host>
         </db>
</settings>
��������:
// Load settings (XML)
$settings = New Settings_XML;
$settings->load('config.xml');
echo 'XML: ' . $settings->get('db.host') . '';

*
*/
?>