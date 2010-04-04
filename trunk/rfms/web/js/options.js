function addOption(source ,label,value){
	if(!hasValue(source,value)){
	  	var length = source.options.length;
		source.options.length = length+ 1;
		source.options[length].value = value;
		source.options[length].text = label;
		source.options[length].selected = true;
	}
}
function selectAll(box){
	for(var i=0 ; i < box.options.length;i++){
		box.options[i].selected = true;
	}
}
function valueIndex(box,value){
	for(var i=0 ; i < box.options.length;i++){
		if(box.options[i].value == value){
			 return i;
		}
	}
	return -1;
}
function deleteOptionByValue(box,value){
	var idx = valueIndex(box,value);
	if(idx==-1) return ;
	for(var i=idx ; i < box.options.length-1;i++){
		box.options[i].value = box.options[i+1].value;
		box.options[i].text = box.options[i+1].text;			
	}
	box.options.length = box.options.length -1;
}
function hasValue(box,value){
	for(var i=0 ; i < box.options.length;i++){
			if(box.options[i].value ==value){
				return true;
			}
	}
	return false;
}

function deleteSelected(box)  {
	if(box.options.length==0){
		return ;
	}
	idx = box.selectedIndex
	
	for(var i=idx ; i < box.options.length - 1;i++){
		box.options[i].value = box.options[i+1].value;
		box.options[i].text = box.options[i+1].text;			
	}
	box.options.length = box.options.length -1;
}

function swapSelected(sourceId,targetId )  {
	source = document.getElementById(sourceId);
	target = document.getElementById(targetId);
   if(source.selectedIndex<0){
      return false;
   }
   for( var i=source.options.length-1 ; i >=0 ;i-- ){
      if(source.options[i].selected){
         var tempOptSource = source.options[source.selectedIndex];
	     //var tempOptTarget = target.options[target.selectedIndex];
	     addOption(target ,tempOptSource.text, tempOptSource.value);
	     deleteSelected(source);
      }
   }
}

function swapAll(source,target )  {
   for(var i=source.options.length-1 ; i >=0 ;i--){
  		addOption(target ,source.options[i].text, source.options[i].value);
		source.options.length=i;
   }
}

function selectedValue(aselect){
   
   return aselect.options[aselect.selectedIndex].value;
}

function selectedText(aselect){
   
   return aselect.options[aselect.selectedIndex].text;
}

function hasChecked(field){
	var isValid = false;
	if(field.length){ 
		for(m=0;m<field.length;m++){
		   
			if(field[m].checked){
				isValid= true;
			}
		} 
	}else{
	    return field.checked;
	}
	return isValid;
}

