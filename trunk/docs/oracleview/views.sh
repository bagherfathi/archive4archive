#!/bin/bash
#
#       svaggu 09/28/05 -  Creation
#

ORACLE_HOME=/usr/lib/oracle/xe/app/oracle/product/10.2.0/server
export ORACLE_HOME
ORACLE_SID=XE
export ORACLE_SID
NLS_LANG=`./nls_lang.sh`
export NLS_LANG
PATH=$ORACLE_HOME/bin:$PATH
export PATH

#************************  get infosort ***************************************
Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF
select 911911911,INFO_SORT_ID from STRUCTURE group by INFO_SORT_ID;
exit
EOF`

Result=`echo "${Result}"|grep 911911911|grep -i -v INFO|awk '{print $2}'`
StructureIdList=${Result}

#************************ get structure ***************************************
for i in ${StructureIdList};do
echo index is:${i}
echo 
Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF
set heading off;
set pagesize 0;
set linesize 10240;
select ZN_NAME from STRUCTURE where info_sort_id=${i} ;
exit
EOF`
Result=`echo  "${Result}"|grep -v 已|grep -v selected`
Result=`echo  ${Result}|sed 's/ /,/g'|sed 's/,/,F/g'`
FieldList=F${Result}
echo ${FieldList}

Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF
set heading off;
set pagesize 0;
set linesize 10240;
select SERIAL_NUMBER from STRUCTURE where info_sort_id=${i} ;
exit
EOF`
Result=`echo  "${Result}"|grep -v 已|grep -v selected`
Result=`echo  ${Result}|sed 's/ /,/g'`
ValueList=${Result}
echo ${ValueList}

Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF
set heading off;
set pagesize 0;
set linesize 10240;
select name from info_sort t start with id= ${i} connect by id = prior parent_id;
exit
EOF`
Result=`echo  "${Result}"|grep -v 已|grep -v selected`
ViewName=`echo ${Result}|awk '{for(i=0;i<=NF-1;i++)printf("%s ",$(NF-i));printf("\n");}'|sed 's/ /_/g'|sed 's/\_$//g'`
echo ${ViewName}

#Create="Create Or Replace View ${ViewName} ( ${FieldList} ) As Select ${ValueList} From files;"
Create="Create Or Replace View View${i} ( ${FieldList} ) As Select ${ValueList} From files;"
echo ${Create}

/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF
${Create}
exit
EOF


sleep 2

done
exit

#echo  "${Result}"
Result=`echo  "${Result}"|grep 911911911|grep -i -v INFO|awk '{print $2}'`
echo ${Result}
viewname=中文
field1=分类编号
field2=层次编码
field3=上层分类编码
field4=类型
field5=标题
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

#/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive <<EOF 
#Create Or Replace View $viewname ( ${field1},${field2},${field3},${field4},${field5},${field6},${field7},${field8} ) As Select a.${rfield1},a.${rfield2},a.${rfield3},a.${rfield4},a.${rfield5},a.${rfield6},a.${rfield7},a.${rfield8} From files a;
#exit
#EOF
