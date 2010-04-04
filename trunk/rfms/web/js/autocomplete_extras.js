var neverModules = window.neverModules || {};

neverModules.browser = neverModules.browser || {
  isMozilla : (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument!='undefined'),
  isIE      : window.ActiveXObject?true:false,
  isOpera   : (navigator.userAgent.toLowerCase().indexOf("opera")!=-1)
}

neverModules.modules = neverModules.modules || {}; 

if (!neverModules.browser.isIE) {
  HTMLElement.prototype.click = function() {
    var evt = this.ownerDocument.createEvent('MouseEvents');
    evt.initMouseEvent('click', true, true, this.ownerDocument.defaultView, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
    this.dispatchEvent(evt);
  }
}

// make sure the String value is not regular expression

String.prototype.rgEncode = function () {
  var val = this; if (val=="undefined") { return ""; }
  val = val.replace(/\\/g,"\\");
  val = val.replace(/\//g,"\/");
  val = val.replace(/\^/g,"\\^");
  val = val.replace(/\*/g,"\\*");
  val = val.replace(/\?/g,"\\?");
  val = val.replace(/\+/g,"\\+");
  val = val.replace(/\./g,"\\.");
  val = val.replace(/\|/g,"\\|");
  val = val.replace(/\[/g,"\\[");
  val = val.replace(/\]/g,"\\]");
  val = val.replace(/\(/g,"\\(");
  val = val.replace(/\)/g,"\\)");
  val = val.replace(/\{/g,"\\{");
  val = val.replace(/\}/g,"\\}");
  return val;
}

function getElCoordinate (e) {
  var t = e.offsetTop;
  var l = e.offsetLeft;
  var w = e.offsetWidth;
  var h = e.offsetHeight;
  while (e=e.offsetParent) {
    t += e.offsetTop;
    l += e.offsetLeft;
  }; return {
    top: t,
    left: l,
    width: w,
    height: h,
    bottom: t+h,
    right: l+w
  }
}

if (typeof addEvent != 'function')
{
 var addEvent = function(o, t, f, l)
 {
  var d = 'addEventListener', n = 'on' + t, rO = o, rT = t, rF = f, rL = l;
  if (o[d] && !l) return o[d](t, f, false);
  if (!o._evts) o._evts = {};
  if (!o._evts[t])
  {
   o._evts[t] = o[n] ? { b: o[n] } : {};
   o[n] = new Function('e',
    'var r = true, o = this, a = o._evts["' + t + '"], i; for (i in a) {' +
     'o._f = a[i]; r = o._f(e||window.event) != false && r; o._f = null;' +
     '} return r');
   if (t != 'unload') addEvent(window, 'unload', function() {
    removeEvent(rO, rT, rF, rL);
   });
  }
  if (!f._i) f._i = addEvent._i++;
  o._evts[t][f._i] = f;
 };
 addEvent._i = 1;
 var removeEvent = function(o, t, f, l)
 {
  var d = 'removeEventListener';
  if (o[d] && !l) return o[d](t, f, false);
  if (o._evts && o._evts[t] && f._i) delete o._evts[t][f._i];
 };
}

// main module of autocomplete

neverModules.modules.autocomplete = function (oConfigs) {

  this.bIgnoreCase  = (oConfigs.ignoreCase===false)?false:true;
  this.bIgnoreWhere = (oConfigs.ignoreWhere===true)?true:false;
  this.name         = oConfigs.instanceName; // need
  this.oTextbox     = oConfigs.textbox;  // need
  this.oRetTextBox  = oConfigs.retTextBox||this.oTextbox; //optional
  this.autoSlice    = (oConfigs.autoSlice===false)?false:true;
  this.nMaxSlice    = isNaN(parseInt(oConfigs.maxSlice))?100:parseInt(oConfigs.maxSlice); 
  this.sliceRange   = {low: 0, high: 200}; 
  this.highlighted  = (oConfigs.highlight===false)?false:true;
    
  this._autoContainer  = null;
  this._iframeForFixed = null;
  this._styleName      = oConfigs.style?oConfigs.style:"auto";
  this._nScrollHeight  = isNaN(parseInt(oConfigs.height))?200:parseInt(oConfigs.height);
  this._nSelectIndex   = 0;
  this._ownInstance    = this;
  this._dataSource     = "autoComplete";

  this.setDataSource(oConfigs.dataSource);
  this._defaultInitializer.apply(this);

}

neverModules.modules.autocomplete.prototype = {

  _defaultInitializer: function() { with(this) {
      oTextbox.setAttribute("autocomplete","off");
      _autoContainer    = document.createElement("DIV");
      document.body.appendChild(_autoContainer);

      _autoContainer.className = this._styleName;
      with (_autoContainer.style) {
        height   = _nScrollHeight+"px";
        overflow = "auto";
        zIndex   = "99";
        position = "absolute";
        display  = "none";
      }

      if (neverModules.browser.isIE) {
        _iframeForFixed = document.createElement("iframe");
        document.body.appendChild(_iframeForFixed);
        with (_iframeForFixed.style) {
          position = "absolute";
          display  = "none";
          zIndex = 1;
          overflow = "hidden";
          frameBorder = "0";
          scrolling = "no";
          marginHeight = "0";
          marginWidth = "0";
        }
      }

      var own = this._ownInstance;
      addEvent(document,'mousedown', function (evt) {
        evt = evt ? evt : event; var e=evt.srcElement?evt.srcElement:evt.target;
        var rg = new RegExp(own.name+"._tg","g");
        if (e!=own.oTextbox && 
            e!=own._autoContainer && 
            !rg.test(e.getAttribute("onmouseover"))) {
          own.handlerClose();
        }
      });
  }},

  _tg: function (oItem, sClassName) {
    this._ownInstance._handlerToggleEvent.apply(this, arguments);
  },
  _handlerToggleEvent: function (oItem, sClassName) {
    var e = this._autoContainer.childNodes[this._nSelectIndex];
    if (e) e.className = "out";
    oItem.className = sClassName;
  },

  _handlerClickEvent: function (oElement) {
    this.oRetTextBox.value = oElement.innerHTML.replace(/<[^<>]*>/g,"");
    this.handlerClose();
  },
  _hc: function (oElement) {
    this._ownInstance._handlerClickEvent.apply(this, arguments);
  },

  _handlerQueryEvent: function (sQueryVal) {

    var sRegOpt = this.bIgnoreCase?"i":"";
    var sRegExp = '<div[^<>]+?>' +(this.bIgnoreWhere?'[^<>]*?':'')+sQueryVal+'[^<>]*?<\/div>';
    if (this.bCustom) sRegExp=sCustomRegExp;

    var oRegExp = new RegExp().compile(sRegExp, sRegOpt+"g");
    var aMatch  = this._dataSource.match(oRegExp); 
    oRegExp     = null; if (aMatch==null) { this.handlerClose(); return; }

    if (this.highlighted===true) {
      oRegExp = new RegExp().compile("(?:>)([^<>]*?)(" +sQueryVal+ ")", sRegOpt+"g");
      this._autoContainer.innerHTML=(this.autoSlice && aMatch.length>this.nMaxSlice?aMatch.slice(this.sliceRange.low, this.sliceRange.high).join(""):aMatch.join("")).replace(oRegExp, ">$1<strong>$2</strong>");
    } else {
      this._autoContainer.innerHTML=this.autoSlice && aMatch.length>this.nMaxSlice?aMatch.slice(this.sliceRange.low, this.sliceRange.high).join(""):aMatch.join("");
    }
  },

  _handlerArrowEvent: function (nKeyCode) { with(this) {

    switch (nKeyCode) {
      case 13: // enter
        if (_autoContainer.childNodes[_nSelectIndex])
        _autoContainer.childNodes[_nSelectIndex].click();
        break;
      case 38: // up arrow
        if (_autoContainer.childNodes[_nSelectIndex]) _autoContainer.childNodes[_nSelectIndex].className = "out";
        if (--_nSelectIndex<=0) _nSelectIndex=0;
        break;
      case 40: // down arrow
        if (_autoContainer.childNodes[_nSelectIndex]) _autoContainer.childNodes[_nSelectIndex].className = "out"; 
        if (++_nSelectIndex>=_autoContainer.childNodes.length) _nSelectIndex=_autoContainer.childNodes.length-1;
        break;
      case 27: // esc
        this.handlerClose();
        break;
      default: // to do
        return;
    }
    if (_autoContainer.childNodes[_nSelectIndex]) {
      var e = _autoContainer.childNodes[_nSelectIndex];
      e.className = "over";
      e.scrollIntoView();
      //e.focus();
    }

  }},
  
  _initCoordinate: function () {
    var p = getElCoordinate(this.oTextbox); 
    var x = this._autoContainer.style; 

    x.left    = p.left+"px";
    x.top     = p.top+p.height+"px";
    x.width   = p.width+"px";
    x.display = "block";

    if (neverModules.browser.isIE) {
      var w = this._iframeForFixed.style;
      w.left    = p.left+"px";
      w.top     = p.top+p.height+"px";
      w.width   = p.width+"px";
      w.height  = this._autoContainer.offsetHeight+"px";
      w.display =  "block";
    }

    this._autoContainer.scrollTop = '0px';
  },

  handlerClose: function () {
    if (neverModules.browser.isIE) this._iframeForFixed.style.display = "none";
    this._autoContainer.style.display = "none";
  },

  clearDataSource: function () {
    this._dataSource = "";
  },

  setDataSource: function (aDataSource) {
    if (aDataSource.constructor==Array) {
      if (aDataSource.length==1) {
        this._dataSource = ''
        + '<div class="out" onclick="' +this.name+'._hc(this)" onmouseover="' +this.name+'._tg(this,\'over\')" onmouseout="' +this.name+'._tg(this,\'out\')">' +aDataSource[0]+ '</div>';
      } else {
        this._dataSource = ''
        + '<div class="out" onclick="' +this.name+'._hc(this)" onmouseover="'
        + this.name+'._tg(this,\'over\')" onmouseout="'
        + this.name+'._tg(this,\'out\')">'+aDataSource.join('</div><div class="out" onclick="'
        + this.name+'._hc(this)" onmouseover="' +this.name+'._tg(this,\'over\')" onmouseout="'
        + this.name+'._tg(this,\'out\')">')+"</div>";		
      }
    } else { 
      /* datasource convert 2 Array if the datasource not a Array */
      /* TO DO */ 
      throw new Error("配置参数 dataSource 不是一个数组"); 
    }
  },

  expandAllItem: function () {
    this._autoContainer.innerHTML = this._dataSource;
    this._initCoordinate();
    return true;
  },

  handlerEvent: function (evt) {
    var sQueryValue = this.oTextbox.value; 
    var nKeyCode    = window.event?event.keyCode:evt.which;

    if (nKeyCode==13 || nKeyCode==38 || nKeyCode==40 || nKeyCode==27) {
      this._handlerArrowEvent(nKeyCode);
      return true;
    } else {    
      this._autoContainer.innerHTML = "";
      this._nSelectIndex = 0;
    }

    if (sQueryValue == "") {
      this.handlerClose();
      return false;
    } else {
      this._handlerQueryEvent(sQueryValue.rgEncode()); 
      this._initCoordinate();
      return true;
    }

  }
}