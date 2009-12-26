viewname=$1
cvsfile=$2
SRC="'"
cat ${cvsfile}|sed 's/^"/insert into '${viewname}' values(HIBERNATE_SEQUENCE.nextval,"/g;s/$/);/g;s/^,/insert into '${viewname}' values(HIBERNATE_SEQUENCE.nextval,/g;s/,,/,"",/g;s/''"'/"'"'/g;'>${cvsfile}.sql
