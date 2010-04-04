  var checkBoxIds = new Array();
  var index = 0;
  
  function findAllChildren(id){
    var children = new Array();
    var j=0;
    for(var i=0;i<checkBoxIds.length;i++){
            var nowId = checkBoxIds[i];
            if(nowId.indexOf(id) == 0){
                children[j++] = nowId;
            }
     }
    return children;
  }
  
  function findParent(id){
    if(id == ""){
        return "";
    }
    var parent = "";
    for(var i=0;i<checkBoxIds.length;i++){
        var nowId = checkBoxIds[i];
        if(id != nowId && id.indexOf(nowId) == 0){
            parent = nowId;
        }
     }
    return parent;
  }
  
  function unCheckParent(id){
    var parent = findParent(id);
    if(parent != ""){
        document.all[parent].checked = false;
        unCheckParent(parent);
    }
  }
  
  function autoCheck(curChkBox){
   var children = findAllChildren(curChkBox.id);
    for(var i=0;i<children.length;i++){
       var obj =  document.all[children[i]];
        if(!obj.disabled){
            obj.checked = curChkBox.checked;
        }
    }
    if(!curChkBox.checked){
        unCheckParent(curChkBox.id);
    }
  }
  
  function unCheckParent2(id){
    var parent = findParent(id);
    if(parent != ""){
        unCheckParent2(parent);
    }
  }
  
  function autoCheck2(curChkBox){

   var children = findAllChildren(curChkBox.id);
    for(var i=0;i<children.length;i++){
    var obj =  document.all[children[i]];
        obj.checked = curChkBox.checked;
    }
    if(!curChkBox.checked){
        unCheckParent2(curChkBox.id);
    }
  }
  