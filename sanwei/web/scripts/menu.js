
function $E(id){ return document.getElementById(id);};
isUndef  = function(a){ return typeof a == "undefined";};
isNull 	 = function(a){ return typeof a == "object" && !a; };
var MAX=10000;
String.prototype.trim = function()
{
	return this.replace(new RegExp("(^[\\s]*)|([\\s]*$)", "g"), "");	
};

$P = function(parameter, url)
{
	url = isUndef(url) ? location.href : url;
	var result = url.match(new RegExp("[\#|\?]([^#]*)[\#|\?]?"));
	url = "&" + (isNull(result) ? "" : result[1]);	
	result = url.match(new RegExp("&"+parameter+"=", "i"));
	return isNull(result) ? undefined : url.substr(result.index+1).split("&")[0].split("=")[1];
};


function showdesc(items)
{
	var desc = "desc" + items.toString();
	var pic = document.getElementById("pic" + items);
	if( items == -1 )
	 desc = "ad_desc";	
	if( $E(desc).style.display=="none" ){
		$E(desc).style.display=""; 
		if(pic !=null){
			pic.src = "/images/collapse.gif";	
		}
	}else{
		$E(desc).style.display="none";
		if(pic !=null){
			pic.src = "/images/expand.gif";
		}
	}
}

function add_favorite(title,url)
{ 
	if (document.all)  
	window.external.AddFavorite(url, title);  
	else if (window.sidebar)  
	window.sidebar.addPanel(title, url, "");
} 
var g_key = $P("search");
var g_sortby = $P("sortby");
var g_suffix = $P("suffix"); 
var g_page = $P("page") ; 
var g_restype = $P("restype");
var g_entryid = $P("id");
if( (g_entryid < 9999))
	g_entryid = parseInt(MAX) + parseInt(g_entryid);//-->
