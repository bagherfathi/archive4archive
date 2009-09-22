var offsetYInsertDiv = -3; // Y offset for the little arrow indicating where the node should be inserted.
if (!document.all)offsetYInsertDiv = offsetYInsertDiv - 7; 	// No IE

var arrParent = false;
var arrMoveCont = false;
var arrMoveCounter = -1;
var arrTarget = false;
var arrNextSibling = false;
var arrPreviousSibling = false;
var leftPosArrangableNodes = false;
var widthArrangableNodes = false;
var nodePositionsY = new Array();
var nodeHeights = new Array();
var arrInsertDiv = false;
var insertAsFirstNode = false;
var arrNodesDestination = false;
var howfarfrommouse = 8;
function cancelEvent()
{
    return false;
}
function getTopPos(inputObj)
{

    var returnValue = inputObj.offsetTop;
    while ((inputObj = inputObj.offsetParent) != null) {
        returnValue += inputObj.offsetTop;
    }
    return returnValue;
}

function getLeftPos(inputObj)
{
    var returnValue = inputObj.offsetLeft;
    while ((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetLeft;
    return returnValue;
}

function clearMovableDiv()
{
    if (arrMoveCont.getElementsByTagName('TR').length > 0) {

        if (arrNextSibling) {
            arrParent.insertBefore(arrTarget, arrNextSibling);

        }
        else {
            arrParent.appendChild(arrTarget);
        }

    }
}

function initMoveNode(e)
{
    clearMovableDiv();
    if (document.all)e = event;
    arrMoveCounter = 0;
    arrTarget = this;
    if (this.nextSibling)arrNextSibling = this.nextSibling; else arrNextSibling = false;
    timerMoveNode();
    arrMoveCont.parentNode.style.left = (e.clientX + howfarfrommouse) + 'px';

    arrMoveCont.parentNode.style.top = (e.clientY + howfarfrommouse) + 'px';

    arrMoveCont.parentNode.style.position = "absolute";
    arrMoveCont.parentNode.style.cursor = "pointer";
    

    return false;

}
function timerMoveNode()
{
    if (arrMoveCounter >= 0 && arrMoveCounter < 10) {
        arrMoveCounter = arrMoveCounter + 1;
        setTimeout('timerMoveNode()', 20);
    }
    if (arrMoveCounter >= 10) {
        arrMoveCont.appendChild(arrTarget);
    }
}

function arrangeNodeMove(e)
{
//	arrTarget.style.backgroundColor = "#E0E0F8";
//    arrMoveCont.parentNode.style.backgroundColor = "#E0E0F8";
    if (document.all)e = event;
    if (arrMoveCounter < 10)return;
    if (document.all && arrMoveCounter >= 10 && e.button != 1 && navigator.userAgent.indexOf('Opera') == -1) {
        arrangeNodeStopMove();
    }
    arrMoveCont.parentNode.style.left = (e.clientX + howfarfrommouse) + 'px';
    arrMoveCont.parentNode.style.top = (e.clientY + howfarfrommouse) + 'px';

    var tmpY = e.clientY;
    arrInsertDiv.style.display = 'none';
    arrNodesDestination = false;


    if (e.clientX < leftPosArrangableNodes || e.clientX > leftPosArrangableNodes + widthArrangableNodes)return;

    var subs = arrParent.getElementsByTagName('TR');
    for (var no = 0; no < subs.length; no++) {
        subs[no].style.backgroundColor = "#FFFFFF";

        var topPos = getTopPos(subs[no]);
        var tmpHeight = subs[no].offsetHeight;
        if (no == 0) {
            if (tmpY <= topPos && tmpY >= topPos - 5) {
                arrInsertDiv.style.top = (topPos + offsetYInsertDiv) + 'px';
                arrInsertDiv.style.display = 'block';
                arrNodesDestination = subs[no];
                subs[no].style.backgroundColor = "#F2F5A9";
                insertAsFirstNode = true;
                //                return;
            }
        }

        if (tmpY >= topPos && tmpY <= (topPos + tmpHeight)) {
            arrInsertDiv.style.top = (topPos + tmpHeight + offsetYInsertDiv) + 'px';
            arrInsertDiv.style.display = 'block';
            arrNodesDestination = subs[no];
            subs[no].style.backgroundColor = "#F2F5A9";
            insertAsFirstNode = false;
            //            return;
        }
    }
}

function arrangeNodeStopMove()
{
    arrMoveCounter = -1;
    arrInsertDiv.style.display = 'none';

    if (arrNodesDestination) {
        var subs = arrParent.getElementsByTagName('TR');
        if (arrNodesDestination == subs[0] && insertAsFirstNode) {
            arrParent.insertBefore(arrTarget, arrNodesDestination);
        } else {
            if (arrNodesDestination.nextSibling) {
                arrParent.insertBefore(arrTarget, arrNodesDestination.nextSibling);
            } else {
                arrParent.appendChild(arrTarget);
            }
        }
    } else {

    }
    arrNodesDestination = false;
    clearMovableDiv();
    var subs = arrParent.getElementsByTagName('TR');
    for (var no = 0; no < subs.length; no++) {
        subs[no].style.backgroundColor = "#FFFFFF";
    }
}

function saveArrangableNodes(formName)
{
//    alert("aaaaaaaaaaaaaaaaaaa");
    var nodes = arrParent.getElementsByTagName('TR');
    var string = "";
    for (var no = 0; no < nodes.length; no++) {
     nodes[no].innerHTML="<td><input name='listseq' type=hidden value="+nodes[no].id.substring(4,5)+" /></td>";
//        if (string.length > 0)string = string + ',';
//        string = string + nodes[no].innerHTML;
    }
//    for (var no = 0; no < nodes.length; no++) {
//        if (string.length > 0)string = string + ',';
//        string = string + nodes[no].id;
//    }
//    alert("String"+string);
//    document.forms[0].hiddenNodeIds.value = string;

    // Just for testing
//    document.getElementById('arrDebug').innerHTML = '<Ready to save these nodes:<br>' + string.replace(/,/g, ',<BR>');
//    alert(string);
//    alert(formName);
//    alert(document.getElementsByName(formName)[0].innerHTML);
    document.getElementsByName(formName)[0].submit();
}

function initArrangableNodes()
{
    if(null==document.getElementById('arrangableNodes') ){
        return;
    }
    if(null==document.getElementById('movableNode')){
        return;
    }
    arrParent = document.getElementById('arrangableNodes');
    arrMoveCont = document.getElementById('movableNode').getElementsByTagName('table')[0].getElementsByTagName('tbody')[0];

    arrInsertDiv = document.getElementById('arrDestInditcator');
    leftPosArrangableNodes = getLeftPos(arrParent);
    arrInsertDiv.style.left = leftPosArrangableNodes - 5 + 'px';
    widthArrangableNodes = arrParent.offsetWidth;

    var subs = arrParent.getElementsByTagName('TR');
    for (var no = 0; no < subs.length; no++) {
        subs[no].onmousedown = initMoveNode;
        subs[no].onselectstart = cancelEvent;
        subs[no].style.cursor = "pointer";
    }

    document.documentElement.onmouseup = arrangeNodeStopMove;
    document.documentElement.onmousemove = arrangeNodeMove;
    document.documentElement.onselectstart = cancelEvent;
    arrInsertDiv.style.display = 'none';
}
//window.onload = initArrangableNodes;