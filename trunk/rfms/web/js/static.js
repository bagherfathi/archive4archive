function realLength(str)
{
  return str.replace(/[^\x00-\xff]/g,"**").length;
}
String.prototype.trim = function(){return this.replace(/^\s*|\s*$/g,"");}
function trimForm(form){
	 for(var i=0;i<form.length;i++){
		 if(form[i].type =="text"){
				form[i].value = form[i].value.trim();
				
		 }
	 }	
}