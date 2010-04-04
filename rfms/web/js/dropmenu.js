_d=document;
ns4=(_d.layers)?true:false;
ns6=(navigator.userAgent.indexOf("Gecko")!=-1)?true:false;
mac=(navigator.appVersion.indexOf("Mac")!=-1)?true:false;
mac45=(navigator.appVersion.indexOf("MSIE 4.5")!=-1)?true:false;
if (ns6||ns4)
    mac=false;
ns61=(parseInt(navigator.productSub)>=20010726)?true:false;
ie4=(!_d.getElementById&&_d.all)?true:false;


menubarbackground="#C8DAE8";
menubartext="buttontext";
menuitembackground="#C8DAE8";
menuitemtext = "menutext";
menuhighlight="highlight";
menuhighlighttext ="highlighttext";

menuarray = "eosmenu";
maxmenuitem = 100;
menuobjname = "eosmenu";

subarrow1_gif_path="../image/subarrow1.gif";
subarrow2_gif_path="../image/subarrow2.gif";

var mydropmenu = new Array; // ????????????????id????????????????
var dropmenubar_obj;

function getoffset(e) 
{  
 var t=e.offsetTop;  
 var l=e.offsetLeft;  
 while(e=e.offsetParent) 
 {  
  t+=e.offsetTop;  
  l+=e.offsetLeft;  
 }  
 var rec = new Array(1); 
 rec[0]  = t; 
 rec[1] = l; 
 return rec 
}  

function DropMenu ()
{
	this.menuname;
	this.menuitemid;
	this.menuitemiconid;
	this.menuitemimgid;
	this.menuitemtextid;
	this.level;
	this.menubarid;
	this.menudivid;
	this.parentmenudivid;
	this.isvisible = false;
	
	this.showSubMenu = _show_sub_menu;
	this.hiddenSubMenu = _hidden_sub_menu;
	this.getMenuPath = _get_menu_path;
	
	function _show_sub_menu()
	{
		if (this.isvisible)
			return;
		if (this.level > 1) {
			var dropmenu = mydropmenu[this.parentmenudivid];
			dropmenu.showSubMenu();
			oversubmenuitem(gmobj(this.menuitemid), this.menuitemtextid, this.menuitemiconid, this.menuitemimgid);
			var menuid = gmobj(this.menudivid);
			var parapos = gpos(gmobj(this.parentmenudivid));
			var itempos = gpos(gmobj(this.menuitemid));
			menuid.style.position='absolute';
			menuid.style.left=parseInt(parapos[1]) + parseInt(itempos[3]);
			menuid.style.top=parseInt(parapos[0]) + parseInt(itempos[0]) - 2;
			menuid.style.visibility='visible';
		} else {
			var menuid = gmobj(this.menudivid);
			var barid = gmobj(this.menubarid);
			var barpos = gpos(barid);
			onmouseovermenu(barid);

			menuid.style.position='absolute';
			menuid.style.left=getoffset(dropmenubar_obj)[1] + parseInt(barpos[1])-1;
			menuid.style.top=getoffset(dropmenubar_obj)[0] + parseInt(barpos[0]) + parseInt(barpos[2])-1 ;
			menuid.style.visibility='visible';
		}
		this.isvisible = true;
	}
	
	function _hidden_sub_menu()
	{
		if (this.level > 1) {
			var dropmenu = mydropmenu[this.parentmenudivid];
			dropmenu.hiddenSubMenu();
			outsubmenuitem(gmobj(this.menuitemid), this.menuitemtextid, this.menuitemiconid, this.menuitemimgid);
			var menuid = gmobj(this.menudivid);
			menuid.style.visibility='hidden';
		} else {
			var menuid = gmobj(this.menudivid);
			menuid.style.visibility='hidden';
			var barid = gmobj(this.menubarid);
			barid.style.background=menubarbackground;
			barid.style.color=menubartext;
		}
		this.isvisible = false;
	}
	
	function _get_menu_path(menutext)
	{
		var txt = menutext;
		var mymenu = this;
		while (mymenu.level > 1){
			txt = mymenu.menuname + "->" + txt;
			mymenu = mydropmenu[mymenu.parentmenudivid];
		}
		return mymenu.menuname + "->" + txt;
	}
}

function createmenubar() 
{
	var def_menubar = "";
	def_menubar = "<table id=\"eosmenu_dropmenu_bar\" cellpadding=0 cellspacing=0 border=0 class=outmenubar><tr><td>";
	def_menubar += "<table border=0 cellpadding=0 cellspacing=0 class=inmenubar>";
	def_menubar += "<tr><td height=23px><span class=menubarhand></span></td><td height=23px><span class=menubarhand></span></td>";
	for (i=1; i<=maxmenuitem; i++) {
		try {
			baritem = eval(menuarray+i);
		} catch (ee) {
			break;
		}
		var barname=menuobjname+"bar"+i;
		var menudiv=menuobjname+"div"+i;
		var hassub = true;
		try {
			var submenuvalue = eval(menuarray + i + "_1");
		} catch (ee) {
			hassub = false;
		}
		if (hassub) {
			def_menubar += "<td id="+barname+" class=menubar nowrap onMouseOver=\"showmenu('"+menudiv+"'); window.status='"+baritem[0]+"';\"";
			def_menubar += " onMouseOut=\"hiddenmenu('"+menudiv+"');window.status='';\">"+baritem[0]+"</td>";
		} else {
			def_menubar += "<td id="+barname+" class=menubar nowrap onMouseOver=\"onmouseovermenu(this);window.status='"+baritem[1]+"';\" onClick=\"doMenuBarAction('"+baritem[0]+"', '"+baritem[1]+"', '"+baritem[4]+"')\" ";
			def_menubar += " onMouseOut=\"onmouseoutmenu(this);window.status='';\">"+baritem[0]+"</td>";
		}
		def_menubar += "<td><span class=menubarsep></span></td>";
	}
	def_menubar += "<td></td></tr></table></td></tr></table>\n";
	deftext = def_menubar;
	document.write(def_menubar);
	def_menudiv = "";
	for (j=1; j<i; j++) {
		try {
			var submenuvalue = eval(menuarray + j + "_1");
		} catch (ee) {
			continue;
		}
		createmenudiv(j, 1);
		var dropmenu = new DropMenu();
		baritem = eval(menuarray+j);
		dropmenu.menudivid = menuobjname + "div" + j;
		dropmenu.menubarid = menuobjname + "bar" + j;
		dropmenu.menuname = baritem[0];
		dropmenu.level = 1;
		mydropmenu[menuobjname + "div" + j] = dropmenu;
	}
	document.write(def_menudiv);
	deftext += def_menudiv;
	dropmenubar_obj = gmobj("eosmenu_dropmenu_bar");
	return deftext;
}

function showmenu(mm) {
	mydropmenu[mm].showSubMenu();
}

function hiddenmenu(mm) {
	mydropmenu[mm].hiddenSubMenu();
}

function onmouseovermenu(mm) {
	mm.style.background=menuhighlight;
	mm.style.color=menuhighlighttext;
}

function onmouseoutmenu(mm) {
	mm.style.background=menuitembackground;
	mm.style.color=menuitemtext;
}

function doMenuBarAction(menuname, menuaction, menutarget)
{
	if (menuaction != null && menuaction != "#" && menuaction != ""){
		if (menutarget == null || menutarget == "") {
			//????????html????????????????<font id="clickMenuAddress"></font>????????????????????????????????????????????????????
			var clickMenuText = gmobj("clickMenuAddress");
			if (clickMenuText != null)
				clickMenuText.innerText = menuname;
			if (menuaction.indexOf("?") == -1)
				location.href=menuaction ;//+ "?clickCurrentMenuPath=" + menuname;
			else
				location.href=menuaction ;//+ "&clickCurrentMenuPath=" + menuname;
			return;
		} else {
			//????????html????????????????<font id="clickMenuAddress"></font>????????????????????????????????????????????????????
			var clickMenuText = gmobj("clickMenuAddress");
			if (clickMenuText != null)
				clickMenuText.innerText = menuname;
			window.frames[menutarget].location.href=menuaction;
			return;
		}
	}
	return;
}

function doMenuAction(menutext, menuaction, menutarget, hassub, menudivid)
{
	if (hassub) return;
	if (menuaction != null && menuaction != "#" && menuaction != ""){
		if (menutarget == null || menutarget == "") {
			var menuPath = "";
			if (menudivid != null && menudivid != "") {
				hiddenmenu(menudivid);
				var dropmenu = mydropmenu[menudivid];
				//????????html????????????????<font id="clickMenuAddress"></font>????????????????????????????????????????????????????
				var clickMenuText = gmobj("clickMenuAddress");
				if (clickMenuText != null) {
					menuPath = dropmenu.getMenuPath(menutext);
					clickMenuText.innerText = menuPath;
				}
			}
			if (menuaction.indexOf("?") == -1)
				location.href=menuaction ;//+ "?clickCurrentMenuPath=" + menuPath;
			else
				location.href=menuaction ;//+ "&clickCurrentMenuPath=" + menuPath;
			return;
		} else {
			if (menudivid != null && menudivid != "") {
				hiddenmenu(menudivid);
				var dropmenu = mydropmenu[menudivid];
				//????????html????????????????<font id="clickMenuAddress"></font>????????????????????????????????????????????????????
				var clickMenuText = gmobj("clickMenuAddress");
				if (clickMenuText != null)
					clickMenuText.innerText = dropmenu.getMenuPath(menutext);
			}
			window.frames[menutarget].location.href=menuaction;
			return;
		}
	}
	return;
}

function createmenudiv(menuno, level)
{
	var menuname = menuobjname + "div" + menuno;

	var menudiv_text = "<div id=" +menuname+ " style=\"visibility:hidden;position:absolute;\" onMouseOver=\"showmenu('"+ menuname +"'); \" onMouseOut=\"hiddenmenu('"+menuname+"'); \">"
	menudiv_text += "<table class=outmenu cellpadding=0 cellspacing=0><tr><td><table class=inmenu cellpadding=0 cellspacing=1>";
	var i;
	var needsep = false;
	for (i=1; i<=maxmenuitem; i++) {
		var menuvalue;
		try {
			menuvalue = eval(menuarray + menuno + "_" + i);
		} catch (ee) {
			break;
		}
		if (needsep) {
			menudiv_text += "<tr><td height=6px class=TDmenusep><div class=menusep><span /></div></td></tr>";
		}

		var menuitemid = "menuitem" + menuno + "_" + i;
		var menuitemiconid = menuitemid + "_icon";
		var menuitemimgid = menuitemid + "_img";
		var menuitemtextid = menuitemid + "_text";

		var menuiconname;
		var menuitemclass = "menuitemwithimg";
		if (menuvalue[3] == null || menuvalue[3] == "") {
			menuiconname = "null";
			menuitemclass = "menuitem";
			menuitemiconid = "";
		} else {
			menuiconname = "'" + menuitemiconid + "'";
		}

		var menustatus = menuvalue[0];
		var hassub = false; // ????????????????????????????????????
		if (menuvalue[2] > 0) {
			hassub = true;
			try {
				var submenuvalue = eval(menuarray + menuno + "_" + i + "_1");
			} catch (ee) {
				hassub = false;
			}
		} else {
			if (menuvalue[1] != null && menuvalue[1] != "" && menuvalue[1] != "#")
				menustatus = menuvalue[1];
		}
		if (menuvalue[2] > 0) {
			if (!needsep && i != 1) {
				menudiv_text += "<tr><td height=6px class=TDmenusep><div class=menusep><span /></div></td></tr>";
			}
			if (!hassub) {
				menudiv_text += "<tr><td id=" + menuitemid + " onMouseOver=\"oversubmenuitem(this, '";
				menudiv_text += menuitemtextid + "', " + menuiconname + ", '"+menuitemimgid+"');window.status='"+menustatus+"';\" ";
				menudiv_text += "onMouseOut=\"outsubmenuitem(this, '";
				menudiv_text += menuitemtextid + "', " + menuiconname + ", '"+menuitemimgid+"');window.status='';\" onClick=\"doMenuAction('"+menuvalue[0]+"', '"+menuvalue[1]+"', '"+menuvalue[4]+"', "+hassub+", '"+menuname+"')\"> ";
				menudiv_text += "<table width=100% cellpadding=0 cellspacing=0 border=0><tr>";
				if (menuiconname != "null") {
					menudiv_text += "<td align=left width=18px id=" + menuitemiconid + " class=menuitemicon>"
					menudiv_text += "<img height=18 width=18 src=" + menuvalue[3] + " /></td>";
				}
				menudiv_text += "<td height=20px width=100% id=" + menuitemtextid + " class=menuitemwithimg nowrap>" +menuvalue[0]+ "</td>";
				menudiv_text += "<td align=right width=10px ><img id=" +menuitemimgid+ " src='"+subarrow1_gif_path+"' /></td>";
				menudiv_text += "</tr></table></td></tr>";
				needsep=false;
			} else {
				var submenudivid = menuobjname + "div" + menuno + "_" + i;
				menudiv_text += "<tr><td id=" + menuitemid + " onMouseOver=\"showmenu('"+ submenudivid +"'); window.status='"+menustatus+"';\" ";
				menudiv_text += "onMouseOut=\"hiddenmenu('"+ submenudivid +"'); window.status='';\" onClick=\"doMenuAction('"+menuvalue[0]+"', '"+menuvalue[1]+"', '"+menuvalue[4]+"', "+hassub+", '"+menuname+"')\" > ";
				menudiv_text += "<table width=100% cellpadding=0 cellspacing=0 border=0><tr>";
				if (menuiconname != "null") {
					menudiv_text += "<td align=left width=18px id=" + menuitemiconid + " class=menuitemicon>"
					menudiv_text += "<img height=18 width=18 src=" + menuvalue[3] + " /></td>";
				}
				menudiv_text += "<td height=20px width=100% id=" + menuitemtextid + " class="+menuitemclass+" nowrap>" +menuvalue[0]+ "</td>";
				menudiv_text += "<td align=right width=10px ><img id=" +menuitemimgid+ " src='"+subarrow1_gif_path+"' /></td>";
				menudiv_text += "</tr></table></td></tr>";
				needsep = true;
				createmenudiv(menuno+"_"+i, level + 1);
				var dropmenu = new DropMenu();
				dropmenu.level = level + 1;
				dropmenu.menudivid = menuobjname + "div" + menuno + "_" + i;
				dropmenu.parentmenudivid = menuname;
				dropmenu.menuitemid = menuitemid;
				dropmenu.menuitemiconid = menuitemiconid;
				dropmenu.menuitemimgid = menuitemimgid;
				dropmenu.menuitemtextid = menuitemtextid;
				dropmenu.menuname = menuvalue[0];
				mydropmenu[menuobjname + "div" + menuno + "_" + i] = dropmenu;
			}
		} else {
			menuitemimgid = "";
			if (menuiconname == "null") {
				menudiv_text += "<tr><td height=20px class=menuitem nowrap  onMouseOver=\"onmouseovermenu(this); window.status='"+menustatus+"';\"";
				menudiv_text += " onMouseOut=\"onmouseoutmenu(this); window.status='';\" onClick=\"doMenuAction('"+menuvalue[0]+"', '"+menuvalue[1]+"', '"+menuvalue[4]+"', "+hassub+", '"+menuname+"')\" >" +menuvalue[0]+ "</td></tr>";
			} else {
				menudiv_text += "<tr><td id=" + menuitemid + " onMouseOver=\"oversubmenuitem(this, '";
				menudiv_text += menuitemtextid + "', " + menuiconname + ", '"+menuitemimgid+"');window.status='"+menustatus+"';\" ";
				menudiv_text += "onMouseOut=\"outsubmenuitem(this, '";
				menudiv_text += menuitemtextid + "', " + menuiconname + ", '"+menuitemimgid+"');window.status='';\" onClick=\"doMenuAction('"+menuvalue[0]+"', '"+menuvalue[1]+"', '"+menuvalue[4]+"', "+hassub+", '"+menuname+"')\"> ";
				menudiv_text += "<table width=100% cellpadding=0 cellspacing=0 border=0><tr>";
				menudiv_text += "<td align=left width=18px id=" + menuitemiconid + " class=menuitemicon>";
				menudiv_text += "<img height=18 width=18 src=" + menuvalue[3] + " /></td>";
				menudiv_text += "<td height=20px width=100% id=" + menuitemtextid + " class=menuitemwithimg nowrap>" +menuvalue[0]+ "</td>";
				menudiv_text += "</tr></table></td></tr>";
			}
			needsep = false;
		}
	}
	menudiv_text += "</table></td></tr></table></div>\n";
	def_menudiv =  menudiv_text + def_menudiv;
}

function oversubmenuitem(menuitem, itemtext, itemicon, itemimg){
	onmouseovermenu(menuitem);
	
	if (itemicon != null && itemicon != "" && itemicon != "null") {
		gmobj(itemtext).className='overmenuitemwithimg';
		gmobj(itemicon).className='overmenuitemicon';
	} else {
		gmobj(itemtext).className='overmenuitem';
	}
	if (itemimg != null && itemimg != "" && itemimg != "null")
		gmobj(itemimg).src=subarrow2_gif_path;
}

function outsubmenuitem(menuitem, itemtext, itemicon, itemimg){
	onmouseoutmenu(menuitem);
	if (itemicon != null && itemicon != "" && itemicon != "null") {
		gmobj(itemtext).className='menuitemwithimg';
		gmobj(itemicon).className='menuitemicon';
	} else {
		gmobj(itemtext).className='menuitem';
	}
	if (itemimg != null && itemimg != ""  && itemimg != "null")
		gmobj(itemimg).src=subarrow1_gif_path;
}

function gpos(gm) /* Get document object position */
{
  if (ns4) {
      t_=gm.top;
      l_=gm.left;
      h_=gm.clip.height;
      w_=gm.clip.width;
  } else if (ns6) {
      t_=gm.offsetTop;
      l_=gm.offsetLeft;
      h_=gm.offsetHeight;
      w_=gm.offsetWidth
  } else {
      t_=gm.offsetTop;
      l_=gm.offsetLeft;
      h_=gm.offsetHeight;
      w_=gm.offsetWidth;
  }
  var gmpos=new Array();
  gmpos[0]=t_;
  gmpos[1]=l_;
  gmpos[2]=h_;
  gmpos[3]=w_;
  return(gmpos);
}

function gmobj(mtxt)  /* Get object by object name */
{
  if (document.getElementById) {
      m=document.getElementById(mtxt);
  } else if (document.all) {
      m=document.all[mtxt];
  } else if (document.layers) {
      m=document.layers[mtxt];
  }
  return m;
}
