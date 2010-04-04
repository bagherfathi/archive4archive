function query(){
    document.forms.query.submit();
}

function submitForm(form){
	trimForm(form);
    if(form.onsubmit()){
        loadOn();
    	form.submit();
    }
}


function loadOff(){
   	 document.getElementById("divProcessing").style.display = "none";
	 document.getElementById("divSearch").style.display = "none";
   }
   function loadOn(){
   	flashs();
	str();
   	  document.getElementById("divProcessing").style.display = "block";
	  document.getElementById("divSearch").style.display = "block";
   }

function ls(){
		pimg.innerHTML="";
		for(i=0;i<9;i++){
		pimg.innerHTML+="<input style=\"width:15;height:15;border:0;background:"+"#3E85EB"+";margin:1\">";
		}
	}
	
function rs(){
		pimg.innerHTML="";
		for(i=9;i>-1;i--){
		pimg.innerHTML+="<input style=\"width:15;height:15;border:0;background:"+"#3E85EB"+";margin:1\">";
		}
	}
	

var g=0;sped=0;
function str(){
	if(pimg.style.pixelLeft<350&&g==0){
	if(sped==0){
		ls();
		sped=1;
		}
	pimg.style.pixelLeft+=2;
	setTimeout("str()",1);
	return;
	}
	g=1;
	if(pimg.style.pixelLeft>-200&&g==1){
	if(sped==1){
		rs();
		sped=0;
		}
	pimg.style.pixelLeft-=2;
	setTimeout("str()",1);
	return;
	}
	g=0;
	str();
}

function flashs(){
if(abc.style.color=="#707888"){
	abc.style.color="#000000";
	setTimeout("flashs()",500);
	}
else{
	abc.style.color="#707888";
	setTimeout("flashs()",500);
	}
}

function highLight(obj){
    obj.className = "treeHighlight" ;
    var tags = document.getElementsByTagName("a");
    for(var i=0; i< tags.length ;i++){
        if(tags[i].id != obj.id){
            tags[i].className = "";
        };
    }
}

//默认选择第二个选项，第一个为"请选择"
function setDefalutOpt(obj){
	if(obj && obj.length>1) {
		if(obj.selectedIndex==0) {
			obj.selectedIndex =1;
		}
	}
} 

function openDialog(contextPath,slObjName,dispName)
{
   var  top=((window.screen.availHeight-300)/2);
   var  left=((window.screen.availWidth-430)/2);
   var optDialog = window.open(contextPath+'/fastLocate.jsp?slObjName='+slObjName+'&dispName='+dispName, '', 'top='+top+',left='+left+',height=300px,width=430px,titlebar=no');		
   optDialog.document.focus();
}