<%@ page language="java" %>
<%@ page contentType="application/x-javascript" %>

function delData(data)
{
	data.length=0;
}
function fitProvince(name,parentKey,defaultValue){
    delData(name);
    var option;
	for(var i=0;i<regionArray.length;i++)
	{
		if(regionArray[i][3] == parentKey  ){
            option = new Option(regionArray[i][1],regionArray[i][1]);
            //alert(regionArray[i][1]);
            name.options.add(option);
            break;
		}
	}
    setDefaultValue(name,defaultValue);
}
function fitCity(name,parentKey,defaultValue){
	//alert("city start");
    delData(name);
    var option;
	for(var i=0;i<regionArray.length;i++)
	{
		if(regionArray[i][3] == parentKey  ){
            option = new Option(regionArray[i][1],regionArray[i][1]);
            //alert(regionArray[i][1]);
            name.options.add(option);
		}
	}
	//alert(name.options.length);
    setDefaultValue(name,defaultValue);
}


function fitDistrict(name,city,defaultValue){
	//alert("District start");
    delData(name);
    var parentKey = getKeyByDescription(city);
    var option;
    var districtArray = new Array;
	for(var i=0;i<regionArray.length;i++)
	{
		if(regionArray[i][3] == parentKey){
			//alert(regionArray[i][1]);
			option = new Option(regionArray[i][1],regionArray[i][0]);
            name.options.add(option);
		}
	}
    //alert(name.options.length);
    setDefaultValue(name,defaultValue);
}

function fitRegion(name,parentKey,defaultValue){
    //alert("region start");
    delData(name);
    var option;
   
//    if(parentKey == 0) return;
    for(var i=0;i<regionArray.length;i++)
    {
        if(regionArray[i][3] == parentKey){
        	//alert(regionArray[i][1]);
            option = new Option(regionArray[i][1],regionArray[i][0]);
            name.options.add(option);
        }
    }
    //alert(name.options.length);
    setDefaultValue(name,defaultValue);
}

function setDefaultValue(name,defaultValue){
    if(name.options.length==0){
        var option = new Option("        ","");
        name.options.add(option);
        return;
    }
    if(defaultValue == null || defaultValue == "" || defaultValue == "0"){
//        var option = new Option("        ","");
//        name.options.add(option);
        return;
    }
    for(i=0;i<name.options.length;i++)
    {
        if (name.options[i].value == defaultValue)
            name.options[i].selected = true;
    }
}

function getKeyByDescription(desc){
    if(desc == null || desc == "") return "";
    for(var i=0;i<regionArray.length;i++)
    {
        if(regionArray[i][1] == desc){
            return regionArray[i][0];
        }
    }
    return "";
}