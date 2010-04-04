    function ParamaterNode(id,name,lvalue){
		this.id = id;
		this.name = name;
		this.lvalue = lvalue ;
   }
   
    function addNodeRow(){
		var id =document.getElementById("entryLabel").value;
		var label = document.getElementById("entryLabel").value;
		if(label == ''){
			alert("����д����");
			return ;
		}
		var value = document.getElementById("entryValue").value;
		if(value == ''){
			alert("����д����ֵ");
			return ;
		}
		var aNode = new ParamaterNode(id,label,value);
		document.getElementById("entryLabel").value='';
		document.getElementById("entryValue").value=''
		addRow('paramTable',aNode);
	 }
	 
	 function addNodeRowOnlyName(){
		var id =document.getElementById("entryLabel").value;
		var label = document.getElementById("entryLabel").value;
		if(label == ''){
			alert("����д����");
			return ;
		}
		var value = document.getElementById("entryValue").value;
		if(value == ''){
			alert("����д����ֵ");
			return ;
		}
		var aNode = new ParamaterNode(id,label,value);
		document.getElementById("entryLabel").value='';
		document.getElementById("entryValue").value=''
		addRowOnlyName('paramTable',aNode);
	 }
	
	function addRowOnlyName(name,aNode){
		var existNode = document.getElementById("entryNode-" + aNode.id);
		if (null!=existNode) {
			alert ("�˲����Ѿ����ڣ� " + aNode.id +" �� �������ظ���ӣ�");
			return;
		}
		var aTable = document.getElementById(name);
		var lastRow = aTable.rows.length;
		var iteration = lastRow;
	    var row = aTable.insertRow(lastRow-1);
	    row.id = ("row_"+aNode.id);
	    var column = row.insertCell(0);
	    
	    aInput =  document.createElement('input');
	    aInput.setAttribute("type", "hidden");
	    aInput.setAttribute("name", "entryNode[" + aNode.id + "]");	   
        aInput.setAttribute("value", aNode.lvalue);
	    aInput.setAttribute("id", "entryNode-" + aNode.id );
	    column.appendChild(aInput);
	    
	    var textNode = document.createTextNode(aNode.name);
	    column.appendChild(textNode);
	    column = row.insertCell(1);
	    
	    aImg =  document.createElement('img');
	    aImg.setAttribute("src","../images/clearicon.gif");
	    aImg.setAttribute("onclick", Function("removeRow('" + name + "','" + row.id + "')"));
	    column.appendChild(aImg);
	    
	}
	
	
	function addRow(name,aNode){
	
		var existNode = document.getElementById("entryNode-" + aNode.id);
		if (null!=existNode) {
			alert ("�˲����Ѿ����ڣ� " + aNode.id +" �� �������ظ���ӣ�");
			return;
		}
		var aTable = document.getElementById(name);
		var lastRow = aTable.rows.length;
		var iteration = lastRow;
	    var row = aTable.insertRow(lastRow-1);
	    row.id = ("row_"+aNode.id);
	    var column = row.insertCell(0);
	    
	    aInput =  document.createElement('input');
	    aInput.setAttribute("type", "hidden");
	    aInput.setAttribute("name", "entryNode[" + aNode.id + "]");	   
        aInput.setAttribute("value", aNode.lvalue);
	    aInput.setAttribute("id", "entryNode-" + aNode.id );
	    column.appendChild(aInput);
	    
	    var textNode = document.createTextNode(aNode.name);
	    column.appendChild(textNode);
	    column = row.insertCell(1);

	    var bTextNode = document.createTextNode(aNode.lvalue);
	    column.appendChild(bTextNode);
	    column = row.insertCell(2);
	    
	    aImg =  document.createElement('img');
	    aImg.setAttribute("src","../images/clearicon.gif");
	    aImg.setAttribute("onclick", Function("removeRow('" + name + "','" + row.id + "')"));
	    column.appendChild(aImg);
	    
	}
	
	 function removeRow(name,rowId){
	    var result=confirm('ȷ��Ҫɾ��������?');
	    if(result){
        	var aTable = document.getElementById(name);
			var aRow = document.getElementById(rowId);
	    	aTable.deleteRow(aRow.rowIndex);
	    }
    }
    
    function remove(name,rowId,id){
        var choice=confirm('ȷ��Ҫɾ���˲����Ķ�����');
		if (true==choice){	
			removeRow(name,rowId);       
		    window.location.href='enum.do' + id;
        }
    }
    
    function  checkParamterNodes(){
	  var tbl = document.getElementById('paramTable');
 	  var lastRow = tbl.rows.length;
 	  if(lastRow==1){
 	  	  return false;
 	  }else{
 	  	return true;
 	  }
   }