    function menu1_scroll(n)
    {
        temp=n;
    var adisplay = document.getElementById("titleDisplay");
       // alert(TopMenu.scrollLeft+"  "+(sumTitle-titleWidth[ttt]))
        if(n==1 && (TopMenu.scrollLeft+TopMenu.offsetWidth>sumTitle )){
             return false;
	     }
		else if ( n==-1 && (TopMenu.scrollLeft<=sumTitle-titleWidth[ttt])) 
			return false;
		TopMenu.scrollLeft=TopMenu.scrollLeft+temp;

        if (temp==0) return;
		else
        setTimeout("menu1_scroll(temp)",1);
        if(TopMenu.scrollLeft==0)
        { 
            document.all("left").style.display="none";
        }
		else{
            document.all("left").style.display="";
        }
        if(TopMenu.scrollLeft+TopMenu.offsetWidth>=TopMenu.scrollWidth)
        {
            document.all("right").style.display="none";
        }
		else{
            document.all("right").style.display="";
        }
	
    }
    
    img1=new Array(2);
    
    img1[0]=new Image; 
	img1[0].src="images/icon_left.gif";
    img1[1]=new Image;
	img1[1].src="images/icon_right.gif";    
    img2=new Array(2);
    img2[0]=new Image; 
	img2[0].src="images/icon_left.gif";
    img2[1]=new Image;
	img2[1].src="images/icon_right.gif";
    
    function show(n)
    {
        if (n==0)
			document.P0.src=img2[0].src;
        if (n==1)
			document.P1.src=img2[1].src;
    }
    
    function hide(n)
    {
        if (n==0) document.P0.src=img1[0].src;
        if (n==1) document.P1.src=img1[1].src;
    }

var ttt;
ttt = 1;
function menu1_clickImage(id) {
	td=eval("document.all.module"+id);
	tdleft=eval("document.all.module"+id+"_1");
	tdright=eval("document.all.module"+id+"_2");	
	tdlink=eval("document.all.module"+id+"_3");
	td.className="menu1_downm";
	tdleft.className="menu1_downl";
	tdright.className="menu1_downr";
	tdlink.className="menu1";

   ttt = id;

	for (var i = 1; i <=20; i++) {
	
	if(i!=id) {
	
		itd=eval("document.all.module"+i);
		itdleft=eval("document.all.module"+i+"_1");
		itdright=eval("document.all.module"+i+"_2");
		itdlink=eval("document.all.module"+i+"_3");
			if(itd && itdleft && itdright && itdlink)
			{
			itd.className="menu1_upm";
			itdleft.className="menu1_upl";
			itdright.className="menu1_upr";
			itdlink.className="menu1";
			}
		  }
	   }
	 sumTitleWidth(id);
	// alert(TopMenu.scrollLeft+"   "+TopMenu.offsetWidth+"  "+sumTitle+"   ===");
	 if(TopMenu.scrollLeft+TopMenu.offsetWidth<sumTitle){
	 menu1_scroll(1)
	 }
	 else if (TopMenu.scrollLeft>sumTitle-titleWidth[id])
	 { 
		  //   alert(TopMenu.scrollLeft+" "+(sumTitle-titleWidth[id])+"  "+sumTitle+"  "+titleWidth[id]);
		 	 sumTitleWidth(id);
	         menu1_scroll(-1)
	 }  


 }


function menu1_changeImage(id) {
	td=eval("document.all.module"+id);
	tdleft=eval("document.all.module"+id+"_1");
	tdright=eval("document.all.module"+id+"_2");	
	tdlink=eval("document.all.module"+id+"_3");
	td.className="menu1_downm";
	tdleft.className="menu1_downl";
	tdright.className="menu1_downr";
	tdlink.className="menu1";
			
	for (var i = 1; i <=20; i++) {
	
	if(i!=id && i!=ttt) {
	
		itd=eval("document.all.module"+i);
		itdleft=eval("document.all.module"+i+"_1");
		itdright=eval("document.all.module"+i+"_2");
		itdlink=eval("document.all.module"+i+"_3");
			if(itd && itdleft && itdright && itdlink)
			{
			itd.className="menu1_upm";
			itdleft.className="menu1_upl";
			itdright.className="menu1_upr";
			itdlink.className="menu1";
			}
		  }
	   }
 }


function menu1_loadImage(id) {
	td=eval("document.all.module"+id);
	tdleft=eval("document.all.module"+id+"_1");
	tdright=eval("document.all.module"+id+"_2");	
	tdlink=eval("document.all.module"+id+"_3");
	td.className="menu1_downm";
	tdleft.className="menu1_downl";
	tdright.className="menu1_downr";
	tdlink.className="menu1";
}


function menu1_init(){
    if(TopMenu.scrollLeft==0)
    { 
        document.all("left").style.display="none";
    }else{
        document.all("left").style.display="";
    }
    if(TopMenu.scrollLeft+TopMenu.offsetWidth>=TopMenu.scrollWidth)
    {
        document.all("right").style.display="none";
    }else{
        document.all("right").style.display="";
    }
}
var titleWidth = new Array() ;
function setTitleWidth(maxId){
	for (var i = 1; i <=maxId; i++) {
	titleWidth[i]=	eval("document.all.titleDisplay"+i).clientWidth;
	}
}
function showTitleWidth(){
    for (var i=1;i<titleWidth.length;i++ )
    {
		//alert(titleWidth[i])
    }
}
 var  sumTitle=0;
function sumTitleWidth(currentId){
	sumTitle=0;
	for (var i=1;i<=currentId;i++ )
    {
		//alert(titleWidth[i]+" =="+i);
		tw=	eval("document.all.titleDisplay"+i).clientWidth;
		sumTitle+=tw;
		
    }
}