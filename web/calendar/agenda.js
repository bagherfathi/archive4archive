//////////////////// Agenda file for CalendarXP 8.0 /////////////////
// This file is totally configurable. You may remove all the comments in this file to minimize the download size.
/////////////////////////////////////////////////////////////////////

//////////////////// Define agenda events ///////////////////////////
// Usage -- fAddEvent(year, month, day, message, action, bgcolor, fgcolor, bgimg, boxit, html);
// Notice:
// 1. The (year,month,day) identifies the date of the agenda.
// 2. In the action part you can use any javascript statement, or use " " for doing nothing.
// 3. Assign "null" value to action will result in a line-through effect(can't be selected).
// 4. html is the HTML string to be shown inside the agenda cell, usually an <img> tag.
// 5. fgcolor is the font color for the specific date. Setting it to ""(empty string) will make the fonts invisible and the date unselectable.
// 6. bgimg is the url of the background image file for the specific date.
// 7. boxit is a boolean that enables the box effect using the bgcolor when set to true.
// ** REMEMBER to enable respective flags of the gAgendaMask option in the theme, or it won't work.
/////////////////////////////////////////////////////////////////////
//fAddEvent(2003,3,28," March 28, 2003 \n Click to detect calendar size. ","alert('Here is the current size of the calendar - \"width='+gfSelf.offsetWidth+' height='+gfSelf.offsetHeight+'\"');","#87ceeb","dodgerblue");
//fAddEvent(2002,12,2," Your comments're of vital importance. ","popup('mailto:pop@calendarxp.net?subject=Comments on PopCalendarXP')","#87ceeb","dodgerblue",null,true);

///////////// Dynamic holiday calculations /////////////////////////
// This function provides you a flexible way to make holidays of your own.
// It will be called whenever the calendar engine needs the agenda info of a specific date, and the date is passed in as (y,m,d);
// With the date in hand, just do whatever you want to validate whether it is a desired holiday;
// Finally you should return an agenda array like [message, action, bgcolor, fgcolor, bgimg, boxit, html] to tell the engine how to render it.
// ** REMEMBER to enable respective flags of the gAgendaMask option in the theme, or it won't work.
////////////////////////////////////////////////////////////////////
function fHoliday(y,m,d) {
	var r=fGetEvent(y,m,d); // get agenda event.
	if (r) return r;	// ignore the following holiday checking if the date has already been set by the above addEvent functions. Of course you can write your own code to merge them instead of just ignoring.

	// you may have sophisticated holiday calculation set here, following are only simple examples.
	if (m==1&&d==1)
		r=[" "+y+"��1��1��, \n �������! ","","#87ceeb","red"];
  else if (m==5&&d==1)
    r=[" "+y+"��5��1��, \n �Ͷ��ڿ���! ","","#87ceeb","red"];
  else if (m==6&&d==1)
		r=[" "+y+"��6��1��, \n ��ͯ�ڿ���! ","","#87ceeb","red"];
  else if (m==10&&d==1)
    r=[" "+y+"��10��1��, \n ����ڿ���! ","","#87ceeb","red"];
  else if (m==10&&d==9)
    r=[" "+y+"��10��9��, \n ��ʦ�ڿ���! ","","#87ceeb","red"];
  else if (m==12&&d==25)
		r=[" "+y+"��12��25��, \n ʥ���ڿ���! ","","#87ceeb","red"];

	return r;	// if r is null, the engine will just render it as a normal day.
}


