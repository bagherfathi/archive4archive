#!/bin/bash
#
#       svaggu 09/28/05 -  Creation
#

ORACLE_HOME=/usr/lib/oracle/xe/app/oracle/product/10.2.0/server
export ORACLE_HOME
ORACLE_SID=XE
export ORACLE_SID
NLS_LANG=`/home/tingwei/nls_lang.sh`
export NLS_LANG
PATH=$ORACLE_HOME/bin:$PATH
export PATH

viewname=中文
field1="分类编号1"
field2=层次编码1
field3=上层分类编码1
field4=类型1
field5=标题1
field6=字段1
field7=字段2
field8=字段3

rfield1=info_sort_id
rfield2=status
rfield3=par_info_sort_id
rfield4=type
rfield5=title
rfield6=a1
rfield7=a2
rfield8=a3

/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF 
Create Or Replace View $viewname ( ${field1},${field2},${field3},${field4},${field5},${field6},${field7},${field8} ) As Select a.${rfield1},a.${rfield2},a.${rfield3},a.${rfield4},a.${rfield5},a.${rfield6},a.${rfield7},a.${rfield8} From files a;
exit
EOF
