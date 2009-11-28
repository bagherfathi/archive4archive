update b set b.PAR_INFO_SORT_ID='0000' from files a,files b where a.a9=b.a9 and a.status=0 and b.status=1;

update files b set b.PAR_INFO_SORT_ID=(select a.id from files a where a.a9=b.a9 and a.status=0 and b.status=1);  
