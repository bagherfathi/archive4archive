function showmsg(type,msg,obj){
	if(msg==""){
		return;
	}
	str="";
	switch(type){
		case true:
			img1="icon_success_1.gif";
			img2="icon_success_2.gif";
			img3="icon_success_3.gif";
			color="#447d00";
		break;
		case false:
			img1="icon_error_1.gif";
			img2="icon_error_2.gif";
			img3="icon_error_3.gif";
			color="#c00000";
		break;
		case 2:
			img1="icon_clew_1.gif";
			img2="icon_clew_2.gif";
			img3="icon_clew_3.gif";
			color="#ff6600";
		break;
	}

	str+="<img height=5 src=/img/shim.gif width=3><br>";
	str+='<table align="center" width="100%" cellpadding="0" cellspacing="0">';
	str+='<tr>';
	str+='<td width="38"><img src="/img/'+img1+'"></td>';
	str+='<td background="/img/'+img2+'"><font color='+ color + ' style="font-size:14px"><b>&nbsp;'+msg+'</b></font></td>';
	str+='<td width="15"><img src="/img/'+img3+'"></td>';
	str+='</tr></table>';
	obj.innerHTML=str;
}
