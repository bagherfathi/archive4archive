<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<link rel="stylesheet" href="<c:url value="/tab/tabstyle.jsp"/>"
	type="text/css">
<script language="javascript" src="<c:url value="/tab/tabs.js"/>"></script>
<script src="<c:url value='/js/prototype.js'/>"></script>
<script language="JavaScript1.2">
	var deleteConfirmMsg = '确定要删除';
	var enterCorrectEmailAddress = '请输入正确的手机号码！';
	var emailAddressNull = '手机号码不能为空！';
	var EmailAddressExist = '该手机号码已存在！';
	var codeSelectNone = '请选择要操作的项目';

	function CM(m){
	if(m.length > 11){
	return false;
	}else{
	    var t=/^(13\d{9})|(15\d{9})|(18\d{9})|(0\d{10,11})$/;
	    return t.test(m);
	    }
	}

    function checkm(mobile)
    {
        if (mobile.length != 0)
        {
        	if(!CM(mobile)){
		        alert(enterCorrectEmailAddress);
		       return;
		    }
        }
        else
        {
            alert(emailAddressNull);
            return false;
        }
        return true;
    }

    function addlist() {
        if (!checkm($('inputmobile').value)) {
            return;
        }
        for (i = 0; i < $('codeselect').length; i++) {
            var val = $('codeselect').options[i].value;
            if (val == $('inputmobile').value) {
                alert(EmailAddressExist);
                return;
            }
        }
        var v0 = $('inputmobile').value;
        $('codeselect').options[$('codeselect').length] = new Option(v0, v0);
        $('inputmobile').value = "";
    }

    function dellist() {
        if ($('codeselect').options.selectedIndex == -1){
            alert(codeSelectNone);
			return;
		}

        var result = confirm(deleteConfirmMsg+" [" + $('codeselect').options[$('codeselect').options.selectedIndex].value + "]");

        if (result == true) {
            $('codeselect').options[$('codeselect').options.selectedIndex] = null;
        }
    }

    function saveMailGroup() {
        mergelist();
    	var codevalue  = $('codevalue').value;
    	if(codevalue==""){
			alert(emailAddressNull);
			return;
    	}
   
        $('ticketdetailForm').submit();
    }

    function mergelist() {
        $('codevalue').value = "";
        for (i = 0; i < $('codeselect').length; i++) {
            var val = $('codeselect').options[i].value;
            $('codevalue').value += val;
            if (i < $('codeselect').length - 1) {
                $('codevalue').value += ";";
            }
        }
    }

function selectNode(event){
	var obj = $("MenuBox").getElementsByTagName("div");
	var a	= $("MenuBox").getElementsByTagName("a");

	var e = window.event || event;

	var target = $(Event.element(e).id);
	crtselect = target.id;
	crtlevel = target.getAttribute("level");
	
	for(var j=0;j<a.length;j++)
	{
		if(a[j].getAttribute("level")==crtlevel &&a[j].id!=crtselect){
			$(a[j].id + "_subarea").style.display = "none";
			$(a[j].id).parentNode.className = $(a[j].id).parentNode.getAttribute("nclass");
		}
	}
}

var crtselect="root";
var crtlevel = 1;
var maxlevel = 4;
function addnode(){
	if(crtlevel>= maxlevel)
		return;
	if(!crtselect)
		return;
	var arg = new Object();
	arg.id = "node" + inc;
	arg.parent = $(crtselect + "_subarea");
	arg.text = $("txtCategoryName").value;
	arg.remark = $("txtCategoryRemark").value;
	arg.level = parseInt(crtlevel)+1;

	if(arg.type == "branch"){
		arg.item_normal="item_normal";
		arg.item_select="item_select";
	}else{
		arg.item_normal="leaf_item_normal";
		arg.item_select="leaf_item_select";
	}
	var newnode = new TreeNode(arg);
	newnode._itemHandler = function(event){selectNode(event)};
	inc ++;
}

function addCategorys(arr){
	var arr1 = [];
	var arr2 = [];
	var arr3 = [];

	for(var i=0;i<arr.length;i++){
		if(arr[i].type=="root"){
			arr1.push(arr[i]);
		}
		else if(arr[i].type=="branch"){
			arr2.push(arr[i]);
		}else if(arr[i].type=="leaf"){
			arr3.push(arr[i]);
		}
	}
	addByType(arr1,"root");
	addByType(arr2,"branch");
	addByType(arr3,"leaf");
}

function addByType(arr,type){
	for(var i=0;i<arr.length;i++){
		if(type=="root"){
			var nd = new TreeNode({id:arr[i].id,parent:$("MenuBox"),text:arr[i].text,href:arr[i].href,remark:arr[i].remark,parentlink:arr[i].parentlink,level:1,indent:0,item_normal:"root_normal",item_select:"root_select"})
			nd._itemHandler = function(event){selectNode(event)};
			nd._itemHandler2 = function(event){setHeight('Main_body','Main_left');}
		}
		else if(type=="branch"){
			var item_n,item_s;
			item_n = arr[i].level>2?"sub_item_normal":"item_normal";
			item_s = arr[i].level>2?"sub_item_select":"item_select";
			var nd = new TreeNode({id:arr[i].id,parent:$(arr[i].parent + "_subarea"),text:arr[i].text,href:arr[i].href,remark:arr[i].remark,parentlink:arr[i].parentlink,level:arr[i].level,item_normal:item_n,item_select:item_s})
			nd._itemHandler = function(event){selectNode(event)};
			nd._itemHandler2 = function(event){setHeight('Main_body','Main_left');}
		}
		else if(type=="leaf") {
			var nd = new TreeNode({id:arr[i].id,parent:$(arr[i].parent + "_subarea"),text:arr[i].text,href:arr[i].href,remark:arr[i].remark,parentlink:arr[i].parentlink,level:arr[i].level,item_normal:"leaf_item_normal",item_select:"leaf_item_select"})
			nd._itemHandler = function(event){selectNode(event)};
			nd._itemHandler2 = function(event){setHeight('Main_body','Main_left');}
		}
	}
}
</script>

<html:form styleId="ticketdetailForm" action="/ticketsend.do" method="post">
  <input type="hidden" name="mobiles" value="<c:out value='${ticketdetailForm.mobiles}'/>" id="codevalue"/>
  <input type="hidden" name="mobiles1" value="<c:out value='${ticketdetailForm.mobiles1}'/>" id="codevalue2"/>
	<input type="hidden" value="save" name="act" />
	<webui:panel title="飞券下发" width="95%" icon="/images/icon_list.gif">
		<webui:formTable>
		<tr>
			<webui:input label="label.rfms.ticket.ticket_name" colspan="3">
				<html:text property="rfmsTicket.ticketName" size="25" disabled="true"/>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="label.rfms.ticket.ticketSerial" colspan="3">
				<html:text property="rfmsTicket.ticketSerial" size="25" disabled="true" />
			</webui:input>
		</tr>
		<tr>
			<webui:input label="label.rfms.ticket.ticketCount" colspan="3">
			<html:text property="rfmsTicket.ticketCount" size="25"disabled="true"/>
		</webui:input>
		</tr>
		<tr>
			<webui:input label="手机" required="true" colspan="3">
				<input type="text" id="inputmobile" size="25"/>
				&nbsp;&nbsp;&nbsp;&nbsp; 
				<span class="clsButtonFace"><a href="javascript:addlist();">添加</a></span>
				<span class="clsButtonFace"><a href="javascript:dellist();">删除</a></span>
			</webui:input>
		</tr>
		<tr>
			<webui:input label="待下发飞券" required="true">
				 <div class="select" id="sel1_1"
                                     style="width:190px;height:210px;">
                                    <select name="aa" size="8"
                                            style="width:190px;height:210px;"
                                            id="codeselect">
                                    </select>
                                </div>
			</webui:input>
			
			<webui:input label="已下发飞券" required="true">
				 <div class="select" id="sel1_2"
                                     style="width:190px;height:210px;">
                                    <select name="aa2" size="8"
                                            style="width:190px;height:210px;"
                                            id="codeselect2">
                                    </select>
                                </div>
			</webui:input>
		</tr>
		</webui:formTable>
		<br />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:saveMailGroup();"
			value="sysadmin.button.save" />
		<webui:linkButton styleClass="clsButtonFace"
			href="javascript:onBack();" value="sysadmin.button.return" />
	</webui:panel>
</html:form>
<script>
var str1 = $('codevalue').value;
if (!str1 == "") {
    var arr = str1.split(";");
    for (i = 0; i < arr.length; i++) {

        $('codeselect').options[$('codeselect').length] = new Option(arr[i], arr[i]);
    }
}

var str2 = $('codevalue2').value;
if (!str2 == "") {
    var arr2 = str2.split(";");
    for (i = 0; i < arr2.length; i++) {

        $('codeselect2').options[$('codeselect2').length] = new Option(arr2[i], arr2[i]);
    }
}
</script>