#!/bin/bash
#
#       Tingwei 10/14/09-  Creation
#
SERVERIP=127.0.0.1
agv="$1"
One=false
if [ "$agv" = "" ];then
	agv="--help"
fi

if [ "$agv" = "--help" ];then
	echo
	echo	"**********************    Help    ***********************"
	echo
	echo	"	-c [view Number]:" 
	echo	"		To create a view by view id"
	echo	"	-a:"
	echo 	"		To create all views"
	exit;
fi

if [ "$agv" = "-c" ];then
	One=true;
	if [ "$2" = "" ];then
		echo "Error:Need ViewId after -c"
		exit;
	fi
	ViewId="$2";
fi

ORACLE_HOME=/usr/lib/oracle/xe/app/oracle/product/10.2.0/server
export ORACLE_HOME
ORACLE_SID=XE
export ORACLE_SID
NLS_LANG=`./nls_lang.sh`
export NLS_LANG
PATH=$ORACLE_HOME/bin:$PATH
export PATH

echo
echo "Start to create or replace views ...."
sleep 1;

#************************  get infosort ***************************************
#select 911911911,INFO_SORT_ID from STRUCTURE group by INFO_SORT_ID;
Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive@//${SERVERIP}:1521/XE <<EOF
select 911911911,a.INFO_SORT_ID from STRUCTURE a,info_sort b where a.info_sort_id=b.id group by a.INFO_SORT_ID;
exit
EOF`

Result=`echo "${Result}"|grep 911911911|grep -i -v INFO|awk '{print $2}'`
StructureIdList=${Result}

#******************************************* Get structure *********************************************************
for i in ${StructureIdList};do
	
	if [ ${One} = "true" ];then
		if [ "${i}" = "${ViewId}" ];then
			echo "Match";	
			Find=true;
		else
			continue;
		fi
	else	
		Find=true;
	fi
	echo ViewID:${i}
	echo 

	#******************************************* Get Name List *********************************************************
	Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive@//${SERVERIP}:1521/XE <<EOF
	set heading off;
	set pagesize 0;
	set linesize 10240;
	select ZN_NAME from STRUCTURE where info_sort_id=${i} and is_delete=0 order by id;
	exit
	EOF`
	Result=`echo  "${Result}"|grep -v 已|grep -v selected`
	Result=`echo ${Result}`
	number=1
	for k in ${Result};do
		if [ ${number} = 1 ];then
			FieldList=F${number}${k};
			
		else	
			FieldList=${FieldList},F${number}${k};
		fi
		number=`expr ${number} + 1`;
	done
	#Result=`echo  ${Result}|sed 's/ /,F/g'`
	FieldList="ID,INFO_SORT_ID,STATUS,PAR_INFO_SORT_ID,TYPE,TITLE,${FieldList}"
	#echo ${FieldList}

	#**********************************************************  Get Value List **************************************************************************
	Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive@//${SERVERIP}:1521/XE <<EOF
	set heading off;
	set pagesize 0;
	set linesize 10240;
	select SERIAL_NUMBER from STRUCTURE where info_sort_id=${i} and is_delete=0 order by id;
	exit
	EOF`
	Result=`echo  "${Result}"|grep -v 已|grep -v selected`
	Result=`echo  ${Result}|sed 's/ /,/g'`
	ValueList="ID,INFO_SORT_ID,STATUS,PAR_INFO_SORT_ID,TYPE,TITLE,${Result}"
	#echo ${ValueList}

	#select name from info_sort t start with id= ${i} connect by id = prior parent_id;

	Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive@//${SERVERIP}:1521/XE <<EOF
	set heading off;
	set pagesize 0;
	set linesize 10240;
	select name from info_sort where id=${i};
	exit
	EOF`
	Result=`echo  "${Result}"|grep -v 已|grep -v selected`
	ViewName=`echo ${Result}|awk '{for(i=0;i<=NF-1;i++)printf("%s ",$(NF-i));printf("\n");}'|sed 's/ /_/g'|sed 's/\_$//g'`
	ViewName=T${i}_${ViewName}

	echo ViewName:${ViewName}

	Create="Create Or Replace View ${ViewName} ( ${FieldList} ) As Select ${ValueList} From files where	INFO_SORT_ID=${i};"
	echo debug
	Create=`echo "${Create}"|sed 's/\//_/g'`
	echo debug end
	echo 
	echo ${Create}
	echo
	echo "Executing ..."
	echo

	Result=`/usr/lib/oracle/xe/app/oracle/product/10.2.0/server/bin/sqlplus -s archive/archive@//${SERVERIP}:1521/XE <<EOF
	${Create}
	exit
	EOF`
	echo "${Result}"

	echo "******************************************************************************"
	sleep 3;
done
if [ "${Find}" != "true" ];then
	echo "Can't find record."	
fi
