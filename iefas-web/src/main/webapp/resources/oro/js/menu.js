
/* --------------------- tx menu --------------------- */
function _err(_message, _type){
	if(_type == undefined){
		console.log(_message);
	}
}
var _rwd = 480;
function _initTxMenu(){
	var _s = '-';			
	$('.tx-menu').each(function(_x) {		
		$(this).prop('menuType', _menuType($(this)));			
		var _aV = $(this).find('ul').siblings('a'); 
		_aV.attr('href', 'javascript:void(0)'); 
		// block url if it has child (ul)
		$(_aV).each(function(_y) {	
			var _div = $('<div></div>')
				.prop('id', 'div'+_s+_x+_y+_s)
				.addClass('div-cont');				
			var _clickCheckbox = $('<input/>')
				.addClass('click-checkbox')
				.prop('type', 'checkbox')
				.prop('id', _s+_x+_y+_s)
				.prop('checked', ((_ws.get(_s+_x+_y+_s) == 'checked') ? true : false))
				.addClass('hidden-checkbox')
				.click(function(e) { 					
					if($(this).prop('checked')){ _ws.set($(this).prop('id'), 'checked'); }else{ _ws.set($(this).prop('id'), '') }					
				});			
			var _clickLabel = $('<label>&nbsp;</label>')
				.addClass('click-label')
				.prop('for', _s+_x+_y+_s);
			var _arrow = $('<span></span>')
				.addClass('arrow')
				.addClass('fa fa-chevron-right');		
				//ti-control-play , ti-angle-right  
				//fa fa-caret-right
			
			$(this).before(_clickCheckbox).before(_arrow);
			$(this).before(_div);
			_div.append(_clickLabel);			
			_div.append($(this));
			//_clickLabel.css('height', $(this).css('height'));
		});	 	
	});
	$('.tx-menu.rwd').each(function(_x) { 		
		// responsive web design menu 		
		var _id = 'rwd-menu-cbx'+_s+_x+_s;		
		if($(this).attr('data-menu-id') != undefined) {_id = 'rwd-' + $(this).attr('data-menu-id'); }		
		var _trigger = $('<label></label>')
			.addClass('rwd-menu-trigger')
			.prop('id', 'rwd-menu-trigger'+_s+_x+_s)
			.prop('for', _id)
			.html('<i class="ti-menu"></i>');
		var _cbx = $('<input/>')
			.addClass('rwd-menu-cbx')
			.prop('type', 'checkbox')
			.prop('checked', false)
			.prop('id', _id)
			.prop('checked', (_ws.get(_id) == 'checked') ? true : false)
			.addClass('hidden-checkbox')
			.click(function(e) {				
				if($(this).prop('checked')){ _ws.set($(this).prop('id'), 'checked'); }else{ _ws.set($(this).prop('id'), ''); } 
				if($(this).prop('checked')){
					//alert('d1');		
					$('body').addClass('blocked-body').removeClass('default-body'); 
				}else{
					//alert('d2');		
					$('body').addClass('default-body').removeClass('blocked-body');	
				}	
			});
		var _mask = $('<div></div>')
			.addClass('rwd-menu-mask')
			.prop('id', 'rwd-menu-mask'+_id)
			.click(function(e) {
				//console.log('rwd-menu-mask.click');						
				$('#'+_id).click();
			});	
		var _a = $(this).find('a'); 
		$(_a).each(function(_z){
			$(this).click(function(e) { 
				//all a click to collapse rwd menu				
				$('#'+_id).click();
			});
		});
		$(this).before(_trigger).before(_cbx).before(_mask);		
	});	
	$('.lv1').each(function(_x) {
		var _id = 'lv1-menu-cbx'+_s+_x+_s;
		var _thisMenu = $(this);		
		if($(this).attr('data-menu-id') != undefined) { _id = 'lv1-' + $(this).attr('data-menu-id'); }				
		var _trigger = $('<label><i class="ti-menu"></i></label>')
			.addClass('lv1-menu-trigger')
			.prop('id', 'lv1-menu-trigger'+_id)
			.prop('for', _id);			
		var _cbx = $('<input/>')
			.addClass('lv1-menu-cbx')
			.prop('type', 'checkbox')
			.prop('checked', false)
			.prop('id', _id)
			.prop('checked', (_ws.get(_id) == 'checked') ? true : false)
			.prop('menuType', _menuType($(this)))
			.addClass('hidden-checkbox')
			.click(function(e) {
				if($(this).prop('checked')){ _ws.set($(this).prop('id'), 'checked'); }else{ _ws.set($(this).prop('id'), ''); } 
				_changeClass(_cbx, _thisMenu);
			});
		_changeClass(_cbx, _thisMenu);		
		//$('body').prepend(_trigger);
		$(this).prepend(_cbx);			
	});
	$('body').addClass('default-body').removeClass('blocked-body');	
	function _changeClass(_cbx, _thisMenu){			
		_cbx.prop('menuType', _thisMenu.prop('menuType'));
		if(_cbx.prop('checked')){
			if(_thisMenu.hasClass('lv1')){				
				if(_cbx.prop('menuType') != 'vertical'){
					_thisMenu.addClass('vertical').removeClass(_cbx.prop('menuType'));	
				}
			}					
		}else{
			if(_cbx.prop('menuType') != 'vertical'){
				_thisMenu.addClass(_cbx.prop('menuType')).removeClass('vertical');
			}
		}				
	}
}
function _menuType(_menu){
	if(_menu.hasClass('vertical')){ return 'vertical' }
	if(_menu.hasClass('accordion')){ return 'accordion' }
	if(_menu.hasClass('horizontal')){ return 'horizontal' }		
}
function _changeMenuType(_id, _to){
	_stopTransition('body');
	var _tgt = $("div[data-menu-id='"+_id+"']");	
	_tgt.prop('menuType', _to);		
	var _lv1Cbx = _tgt.find('input:checkbox').eq(0);
	var _lv1CbxChecked = _lv1Cbx.prop('checked');		
	if(!_lv1CbxChecked){
		_tgt.removeClass(_menuType(_tgt))
		.addClass(_to);
	}
}
function _changeMenuLayout(_menuId, _menuType){
	var _id = '';
	var _menuLayout = $('.menu-layout');	
	var _menu = _menuLayout.find('.tx-menu').eq(0);					
	if(_menuLayout.attr('data-layout-id') != 'undefined') {_id = 'layout-' + _menuLayout.attr('data-layout-id');}	
	if(_menuType == 'top-menu'){
		$('#'+_id).prop('checked', false).click();
		_menu.removeClass(_menu.prop('menuType')).addClass('horizontal');
		// if(_menuLayout.attr('data-layout-id') != undefined) {_id = 'layout-lv1-' + _menuLayout.attr('data-layout-id');}		
		// $('#'+_id).prop('checked', true).click();
	}else{
		$('#'+_id).prop('checked', true).click();
	}
} 
function _initTxMenuLayout(){
	var _s = '-';	
	$('.menu-layout').each(function(_x){
		var _menuLayout = $(this);		
		var _id = 'menu-layout'+_s+_x+_s;
		if($(this).attr('data-layout-id') != undefined) {_id = 'layout-' + $(this).attr('data-layout-id');}		
		if(_ws.get(_id) == undefined){			
			if($(this).attr('data-layout-type') != undefined) {
				if($(this).attr('data-layout-type') == 'top-menu') { 
					_ws.set(_id, 'checked');
				}else{
					_ws.set(_id, '');
				}
			} 	
		}					
		//is topmenu, sidemenu ?		
		if($('#'+_id).length > 0){  // layout-mainLayout is exist			
			$('#'+_id).click(function(e) { _layoutCbxClick($(this), _menuLayout); });
			//.prop('checked', (_ws.get(_id) == 'checked') ? true : false)			
		}else{
			var _layoutCbx = $('<input/>')
			.addClass('layout-cbx')
			.prop('type', 'checkbox')
			.prop('id', _id)		
			.prop('checked', (_ws.get(_id) == 'checked') ? true : false)
			.click(function(e) { _layoutCbxClick($(this), _menuLayout); })
			.addClass('hidden-checkbox');
			$(this).before(_layoutCbx);		
		}
		_layoutCbxClick($('#'+_id), _menuLayout); 
		
		if($(this).attr('data-layout-id') != undefined) {_id = 'layout-lv1-' + $(this).attr('data-layout-id');}	
		var _menu = _menuLayout.find('.tx-menu').eq(0);
		if(_ws.get(_id) == undefined){			
			if(_menu.attr('data-menu-collasped') != undefined) { 
				if(_menu.attr('data-menu-collasped') == 'true') {				
					_ws.set(_id, 'checked');
				}else{
					_ws.set(_id, '');
				}
			}			
		}
		//sidemenu collapsed ?				
		if($('#'+_id).length > 0){  // layout-lv1-mainLayout is exist			
			$('#'+_id).click(function(e) { _lv1CbxClick($(this), _menuLayout); });
			//.prop('checked', (_ws.get(_id) == 'checked') ? true : false)			
		}else{
			var _lv1Cbx = $('<input />')
				.addClass('layout-lv1-cbx')
				.prop('type', 'checkbox')
				.prop('id', _id)		
				.prop('checked', (_ws.get(_id) == 'checked') ? true : false)
				.click(function(e) { _lv1CbxClick($(this), _menuLayout); })		
				.addClass('hidden-checkbox');
			$(this).before(_lv1Cbx);
		}		
		
		var _layoutLv1Trigger = $('<label><i class="ti-menu icon-01"></i><i class="ti-arrow-left icon-02"></i></label>')
			.addClass('layout-lv1-trigger')
			.prop('for', _id)
		$(this).find('.menu-content .body-header').eq(0).prepend(_layoutLv1Trigger);
		
		_lv1CbxClick($('#'+_id), _menuLayout);
	});
	function _layoutCbxClick(_this, _menuLayout){
		_stopTransition('body');
		var _id = '';		
		if(_this.prop('checked')){ _ws.set(_this.prop('id'), 'checked'); }else{ _ws.set(_this.prop('id'), ''); } 
		var _menu = _menuLayout.find('.tx-menu').eq(0);					
		if(_this.prop('checked')){ 						
			_menu.removeClass(_menu.prop('menuType'))
				.addClass('horizontal');
			if(_menuLayout.attr('data-layout-id') != undefined) { _id = 'layout-lv1-' + _menuLayout.attr('data-layout-id'); }						
			$('#'+_id).prop('checked', true).click();
			//_destroyTxMenuScrollbar();
		}else{ 			
			_menu.removeClass('horizontal')
				.addClass(_menu.prop('menuType'));
			//_initTxMenuScrollbar();
		} 
	}
	function _lv1CbxClick(_this, _menuLayout){	
		//_stopTransition('.tx-menu > ul > li > ul'); 		
		if(_this.prop('checked')){ _ws.set(_this.prop('id'), 'checked'); }else{ _ws.set(_this.prop('id'), ''); } 		
		var _menu = _menuLayout.find('.tx-menu').eq(0);
		var _lv1MenuCbx = _menuLayout.find('.tx-menu .lv1-menu-cbx').eq(0);
		var _originalMenuType = _menu.prop('menuType');			
		_lv1MenuCbx.prop('checked', _this.prop('checked'));	
		if(_this.prop('checked')){ 			
			_stopTransition('.tx-menu > ul'); 
			_menu.removeClass('horizontal').removeClass('accordion').addClass('vertical');
			_destroyTxMenuScrollbar();
		}else{			
			_menu.removeClass('vertical');
			if(_menuType(_menu) != 'horizontal'){
				_menu.addClass(_originalMenuType);	
			}
			_initTxMenuScrollbar();
		} 
	}
}
function _stopTransition(_tgt){
	$(_tgt).addClass("stop-transition");
	setTimeout(function(){ $(_tgt).removeClass("stop-transition"); }, 800);	
}
/* --------------------- tx menu --------------------- */
function _initMenuCheckbox(_name){	
	if($('#layout-'+_name).length == 0){  
		document.write('<input class="layout-cbx hidden-checkbox" type="checkbox" id="layout-'+_name+'" '+_ws.get('layout-'+_name)+' >');	
	}	
	if($('#layout-lv1-'+_name).length == 0){  		
		document.write('<input class="layout-lv1-cbx hidden-checkbox" type="checkbox" id="layout-lv1-'+_name+'" '+_ws.get('layout-lv1-'+_name)+' >');	
	}	
}
var _txMenuPS = null;
function _destroyTxMenuScrollbar(){
	//_txMenuPS.destroy();
}
function _initTxMenuScrollbar(){
	// _txMenuPS = new PerfectScrollbar('.menu-layout .tx-menu.accordion');
}
function _initAllTxMenu(){
//alert('_initTxMenu1');
	_initTxMenu(); // first	
//alert('_initTxMenu2');
	_initTxMenuLayout(); // second 
//alert('_initTxMenu3');
	_stopTransition('body');		
	if($('.menu-layout .tx-menu').length > 0){
		_txMenuPS = new PerfectScrollbar('.menu-layout .tx-menu > ul');
	}
	_initMenuMore();	
}
function _initMenuMore(){
	var _bodyW = $(document.body).width();	
	if($('.tx-menu.horizontal').length > 0){ 
		// for horizontal menu only
		var _w = 0;
		var _dotW = 45;
		var _lis = $('.tx-menu.horizontal > ul > li');
		var _more = $('.tx-menu.horizontal > ul > li.tx-more');
		var _moreUl = '';
		if(_more.length == 0){
			$('.tx-menu.horizontal > ul').append('<li class="tx-more"><a href="javascript:void(0)" ><i class="ti-more-alt"></i></a><ul></ul></li>');	
			_more = $('.tx-menu.horizontal > ul > li.tx-more');
		}else{
			//.html('<li><a href="javascript:void(0)" ><i class="ti-notepad"></i><span>Bank Rec.</span></a></li>');
			//_more.insertAfter(".tx-menu.horizontal > ul > li:last-child()"); 
		}
		_moreUl = _more.find('> ul'); 
		var _at = 0;		
		_more.attr('style','display:inline-block !important');
		_moreUl.html('');
		_lis.removeClass('tx-right');
		_lis.each(function(_i){
			if(!$(this).hasClass('tx-more')){			
				$(this).show();
				//alert((_w + $(this).width()) +'_____'+ _bodyW);
				if((_dotW + _w + $(this).width()) > _bodyW) {
					if(_at == 0) { _at = _i }
					$(this).clone(true).appendTo(_moreUl);
					$(this).hide();
				}			
				_w =  _w + $(this).width();				
			}
		});						
		if(_at == 0) { 
			//_err('no more ...');
			// no more ...
			_more.attr('style','display:none !important');
			//_err(_bodyW - _w );
			if( _bodyW - _w <= 200){
				//_err('has more ... s ' + _more.length);
				if(_more.length == 0){
					//_err('HIDDEN more not exist ...');
					// has more ...
					_lis.eq(_lis.length - 1).addClass('tx-right');	
				}else{
					//_err('HIDDEN more exist ...');
					_lis.eq(_lis.length - 1).addClass('tx-right');	
					_lis.eq(_lis.length - 2).addClass('tx-right');	
				}
			}			
		}else{
			//_err('has more ...');
			// has more ...
			_lis.eq(_at-2).addClass('tx-right');
			_lis.eq(_at-1).addClass('tx-right');
		}
	}	
	if(_bodyW < _rwd ){		
		$('.tx-menu > ul > li').show();
		$('.tx-menu > ul > li.tx-more').attr('style','display:none !important');
		_lis.removeClass('tx-right');
	}else{		
	}		
}
function _menuToggle(){
	if($('.menu-toggle').length > 0){
		var _li = $('.menu-toggle');
		var _i = _li.find('i').eq(0);
		var _layoutId = '#layout-'+_li.attr('data-layout-id');
		_initMenuToggleStatus();		
		_li.click(function(){
			$(_layoutId).click(); 
			_initMenuToggleStatus();
			$('.tx-menu > ul > li').show();
			$('.tx-menu > ul > li.tx-more').hide();
			_initMenuMore();
		});
	}
	function _initMenuToggleStatus(){
		if($(_layoutId).prop('checked') == true){
			_li.prop('title', 'Switch to Side Menu');
			_i.addClass('ti-layout-sidebar-left').removeClass('ti-layout-tab-window');  
		}else{
			_li.prop('title', 'Switch to Top Menu');
			_i.addClass('ti-layout-tab-window').removeClass('ti-layout-sidebar-left'); 
		}	
	}
}
$(document).ready(function() {		
	//_initAllTxMenu();
	//txMenuPS.update();
	/*
	$(this).keyup(function(e) {
		var _num = 48;
		if (e.altKey) {
			if(e.which >= 49 && e.which <= 59 ){ // alt 1-9
				_num = e.which - 48;
				if(_num == 1){
					$('.selectors').toggleClass('ar-hide');
					_ws.set('selectorShow', $('.selectors').hasClass('ar-hide'));
				}else if(_num == 2){
				}
			}
		}
	});
	_initDummyStatus();		
	*/		
	_menuToggle();	
	
	var id;
	$(window).resize(function() {
		//var _bodyW = $(document.body).width();
		//alert(_bodyW);
		//if(_bodyW > _rwd){		
			clearTimeout(id);
			id = setTimeout(_initMenuMore, 500);    
		//}else{
		//	$('.tx-menu > ul > li').show();
//			$('.tx-menu > ul > li.tx-more').hide();
		//}
	});
	 
});
var _ws = {
	set : function(_name, _value){		
		if(typeof(Storage) !== "undefined") {
			try{
				sessionStorage.setItem(_name, _value);
			}catch(err){ 							
				//console.log(err.message);			
			}	
		} else {
			//alert('Sorry! No Web Storage support...');;
			//console.log('Sorry! No Web Storage support...');
		}		
	},
	get : function(_name){
		//console.log(sessionStorage[_name]);
		try{
			return sessionStorage[_name];						
		}catch(err){ 			
			//console.log(err.message);			
		}		
	},
	remove : function(_name){		
		try{
			sessionStorage.removeItem(_name);
		}catch(err){ 			
			//console.log(err.message);			
		}		
	}
}
/* Dummy Status ------------------------------------ */
/* Dummy Status ------------------------------------ */
/* Dummy Status ------------------------------------ */






/*!
 * perfect-scrollbar v1.0.3
 * (c) 2017 Hyunje Jun
 * @license MIT
 */
!function(t,e){"object"==typeof exports&&"undefined"!=typeof module?module.exports=e():"function"==typeof define&&define.amd?define(e):t.PerfectScrollbar=e()}(this,function(){"use strict";function t(t){return getComputedStyle(t)}function e(t,e){for(var i in e){var n=e[i];"number"==typeof n&&(n+="px"),t.style[i]=n}return t}function i(t){var e=document.createElement("div");return e.className=t,e}function n(t,e){if(!b)throw new Error("No element matching method supported");return b.call(t,e)}function r(t){t.remove?t.remove():t.parentNode&&t.parentNode.removeChild(t)}function l(t,e){return Array.prototype.filter.call(t.children,function(t){return n(t,e)})}function o(t,e){var i="ps--scrolling-"+e;t.classList.contains(i)?clearTimeout(w[e]):t.classList.add(i),w[e]=setTimeout(function(){return t.classList.remove(i)},1e3)}function s(t){if("function"==typeof window.CustomEvent)return new CustomEvent(t);var e=document.createEvent("CustomEvent");return e.initCustomEvent(t,!1,!1,void 0),e}function a(t,e,i){var n=i[0],r=i[1],l=i[2],a=i[3],c=i[4],h=i[5],u=t.element,d=0,p=!1;e<=0&&(e=0,d=-1),e>=t[n]-t[r]&&((e=t[n]-t[r])-u[l]<=2&&(p=!0),d=1);var f=u[l]-e;f&&(u.dispatchEvent(s("ps-scroll-"+a)),f>0?u.dispatchEvent(s("ps-scroll-"+c)):u.dispatchEvent(s("ps-scroll-"+h)),p||(u[l]=e),d&&u.dispatchEvent(s("ps-"+a+"-reach-"+(d>0?"end":"start"))),o(u,a))}function c(t){return parseInt(t,10)||0}function h(t){return n(t,"input,[contenteditable]")||n(t,"select,[contenteditable]")||n(t,"textarea,[contenteditable]")||n(t,"button,[contenteditable]")}function u(e){var i=t(e);return c(i.width)+c(i.paddingLeft)+c(i.paddingRight)+c(i.borderLeftWidth)+c(i.borderRightWidth)}function d(t,e){return t.settings.minScrollbarLength&&(e=Math.max(e,t.settings.minScrollbarLength)),t.settings.maxScrollbarLength&&(e=Math.min(e,t.settings.maxScrollbarLength)),e}function p(t,i){var n={width:i.railXWidth};i.isRtl?n.left=i.negativeScrollAdjustment+t.scrollLeft+i.containerWidth-i.contentWidth:n.left=t.scrollLeft,i.isScrollbarXUsingBottom?n.bottom=i.scrollbarXBottom-t.scrollTop:n.top=i.scrollbarXTop+t.scrollTop,e(i.scrollbarXRail,n);var r={top:t.scrollTop,height:i.railYHeight};i.isScrollbarYUsingRight?i.isRtl?r.right=i.contentWidth-(i.negativeScrollAdjustment+t.scrollLeft)-i.scrollbarYRight-i.scrollbarYOuterWidth:r.right=i.scrollbarYRight-t.scrollLeft:i.isRtl?r.left=i.negativeScrollAdjustment+t.scrollLeft+2*i.containerWidth-i.contentWidth-i.scrollbarYLeft-i.scrollbarYOuterWidth:r.left=i.scrollbarYLeft+t.scrollLeft,e(i.scrollbarYRail,r),e(i.scrollbarX,{left:i.scrollbarXLeft,width:i.scrollbarXWidth-i.railBorderXWidth}),e(i.scrollbarY,{top:i.scrollbarYTop,height:i.scrollbarYHeight-i.railBorderYWidth})}function f(t,e){function i(e){Y(t,u,p+b*(e[o]-f)),W(t),e.stopPropagation(),e.preventDefault()}function n(){t.event.unbind(t.ownerDocument,"mousemove",i)}var r=e[0],l=e[1],o=e[2],s=e[3],a=e[4],c=e[5],h=e[6],u=e[7],d=t.element,p=null,f=null,b=null;t.event.bind(t[a],"mousedown",function(e){p=d[h],f=e[o],b=(t[l]-t[r])/(t[s]-t[c]),t.event.bind(t.ownerDocument,"mousemove",i),t.event.once(t.ownerDocument,"mouseup",n),e.stopPropagation(),e.preventDefault()})}var b=Element.prototype.matches||Element.prototype.webkitMatchesSelector||Element.prototype.msMatchesSelector,g=function(t){this.element=t,this.handlers={}},v={isEmpty:{configurable:!0}};g.prototype.bind=function(t,e){void 0===this.handlers[t]&&(this.handlers[t]=[]),this.handlers[t].push(e),this.element.addEventListener(t,e,!1)},g.prototype.unbind=function(t,e){var i=this;this.handlers[t]=this.handlers[t].filter(function(n){return!(!e||n===e)||(i.element.removeEventListener(t,n,!1),!1)})},g.prototype.unbindAll=function(){var t=this;for(var e in t.handlers)t.unbind(e)},v.isEmpty.get=function(){var t=this;return Object.keys(this.handlers).every(function(e){return 0===t.handlers[e].length})},Object.defineProperties(g.prototype,v);var m=function(){this.eventElements=[]};m.prototype.eventElement=function(t){var e=this.eventElements.filter(function(e){return e.element===t})[0];return e||(e=new g(t),this.eventElements.push(e)),e},m.prototype.bind=function(t,e,i){this.eventElement(t).bind(e,i)},m.prototype.unbind=function(t,e,i){var n=this.eventElement(t);n.unbind(e,i),n.isEmpty&&this.eventElements.splice(this.eventElements.indexOf(n),1)},m.prototype.unbindAll=function(){this.eventElements.forEach(function(t){return t.unbindAll()}),this.eventElements=[]},m.prototype.once=function(t,e,i){var n=this.eventElement(t),r=function(t){n.unbind(e,r),i(t)};n.bind(e,r)};var w={x:null,y:null},Y=function(t,e,i){var n;if("top"===e)n=["contentHeight","containerHeight","scrollTop","y","up","down"];else{if("left"!==e)throw new Error("A proper axis should be provided");n=["contentWidth","containerWidth","scrollLeft","x","left","right"]}a(t,i,n)},X={isWebKit:document&&"WebkitAppearance"in document.documentElement.style,supportsTouch:window&&("ontouchstart"in window||window.DocumentTouch&&document instanceof window.DocumentTouch),supportsIePointer:navigator&&navigator.msMaxTouchPoints},W=function(t){var e=t.element;t.containerWidth=e.clientWidth,t.containerHeight=e.clientHeight,t.contentWidth=e.scrollWidth,t.contentHeight=e.scrollHeight,e.contains(t.scrollbarXRail)||(l(e,".ps__rail-x").forEach(function(t){return r(t)}),e.appendChild(t.scrollbarXRail)),e.contains(t.scrollbarYRail)||(l(e,".ps__rail-y").forEach(function(t){return r(t)}),e.appendChild(t.scrollbarYRail)),!t.settings.suppressScrollX&&t.containerWidth+t.settings.scrollXMarginOffset<t.contentWidth?(t.scrollbarXActive=!0,t.railXWidth=t.containerWidth-t.railXMarginWidth,t.railXRatio=t.containerWidth/t.railXWidth,t.scrollbarXWidth=d(t,c(t.railXWidth*t.containerWidth/t.contentWidth)),t.scrollbarXLeft=c((t.negativeScrollAdjustment+e.scrollLeft)*(t.railXWidth-t.scrollbarXWidth)/(t.contentWidth-t.containerWidth))):t.scrollbarXActive=!1,!t.settings.suppressScrollY&&t.containerHeight+t.settings.scrollYMarginOffset<t.contentHeight?(t.scrollbarYActive=!0,t.railYHeight=t.containerHeight-t.railYMarginHeight,t.railYRatio=t.containerHeight/t.railYHeight,t.scrollbarYHeight=d(t,c(t.railYHeight*t.containerHeight/t.contentHeight)),t.scrollbarYTop=c(e.scrollTop*(t.railYHeight-t.scrollbarYHeight)/(t.contentHeight-t.containerHeight))):t.scrollbarYActive=!1,t.scrollbarXLeft>=t.railXWidth-t.scrollbarXWidth&&(t.scrollbarXLeft=t.railXWidth-t.scrollbarXWidth),t.scrollbarYTop>=t.railYHeight-t.scrollbarYHeight&&(t.scrollbarYTop=t.railYHeight-t.scrollbarYHeight),p(e,t),t.scrollbarXActive?e.classList.add("ps--active-x"):(e.classList.remove("ps--active-x"),t.scrollbarXWidth=0,t.scrollbarXLeft=0,Y(t,"left",0)),t.scrollbarYActive?e.classList.add("ps--active-y"):(e.classList.remove("ps--active-y"),t.scrollbarYHeight=0,t.scrollbarYTop=0,Y(t,"top",0))},y="ps__child--consume",L={"click-rail":function(t){var e=t.element;t.event.bind(t.scrollbarY,"mousedown",function(t){return t.stopPropagation()}),t.event.bind(t.scrollbarYRail,"mousedown",function(i){var n=i.pageY-window.pageYOffset-t.scrollbarYRail.getBoundingClientRect().top>t.scrollbarYTop?1:-1;Y(t,"top",e.scrollTop+n*t.containerHeight),W(t),i.stopPropagation()}),t.event.bind(t.scrollbarX,"mousedown",function(t){return t.stopPropagation()}),t.event.bind(t.scrollbarXRail,"mousedown",function(i){var n=i.pageX-window.pageXOffset-t.scrollbarXRail.getBoundingClientRect().left>t.scrollbarXLeft?1:-1;Y(t,"left",e.scrollLeft+n*t.containerWidth),W(t),i.stopPropagation()})},"drag-thumb":function(t){f(t,["containerWidth","contentWidth","pageX","railXWidth","scrollbarX","scrollbarXWidth","scrollLeft","left"]),f(t,["containerHeight","contentHeight","pageY","railYHeight","scrollbarY","scrollbarYHeight","scrollTop","top"])},keyboard:function(t){function e(e,n){var r=i.scrollTop;if(0===e){if(!t.scrollbarYActive)return!1;if(0===r&&n>0||r>=t.contentHeight-t.containerHeight&&n<0)return!t.settings.wheelPropagation}var l=i.scrollLeft;if(0===n){if(!t.scrollbarXActive)return!1;if(0===l&&e<0||l>=t.contentWidth-t.containerWidth&&e>0)return!t.settings.wheelPropagation}return!0}var i=t.element,r=function(){return n(i,":hover")},l=function(){return n(t.scrollbarX,":focus")||n(t.scrollbarY,":focus")};t.event.bind(t.ownerDocument,"keydown",function(n){if(!(n.isDefaultPrevented&&n.isDefaultPrevented()||n.defaultPrevented)&&(r()||l())){var o=document.activeElement?document.activeElement:t.ownerDocument.activeElement;if(o){if("IFRAME"===o.tagName)o=o.contentDocument.activeElement;else for(;o.shadowRoot;)o=o.shadowRoot.activeElement;if(h(o))return}var s=0,a=0;switch(n.which){case 37:s=n.metaKey?-t.contentWidth:n.altKey?-t.containerWidth:-30;break;case 38:a=n.metaKey?t.contentHeight:n.altKey?t.containerHeight:30;break;case 39:s=n.metaKey?t.contentWidth:n.altKey?t.containerWidth:30;break;case 40:a=n.metaKey?-t.contentHeight:n.altKey?-t.containerHeight:-30;break;case 32:a=n.shiftKey?t.containerHeight:-t.containerHeight;break;case 33:a=t.containerHeight;break;case 34:a=-t.containerHeight;break;case 36:a=t.contentHeight;break;case 35:a=-t.contentHeight;break;default:return}t.settings.suppressScrollX&&0!==s||t.settings.suppressScrollY&&0!==a||(Y(t,"top",i.scrollTop-a),Y(t,"left",i.scrollLeft+s),W(t),e(s,a)&&n.preventDefault())}})},wheel:function(e){function i(t,i){var n=o.scrollTop;if(0===t){if(!e.scrollbarYActive)return!1;if(0===n&&i>0||n>=e.contentHeight-e.containerHeight&&i<0)return!e.settings.wheelPropagation}var r=o.scrollLeft;if(0===i){if(!e.scrollbarXActive)return!1;if(0===r&&t<0||r>=e.contentWidth-e.containerWidth&&t>0)return!e.settings.wheelPropagation}return!0}function n(t){var e=t.deltaX,i=-1*t.deltaY;return void 0!==e&&void 0!==i||(e=-1*t.wheelDeltaX/6,i=t.wheelDeltaY/6),t.deltaMode&&1===t.deltaMode&&(e*=10,i*=10),e!==e&&i!==i&&(e=0,i=t.wheelDelta),t.shiftKey?[-i,-e]:[e,i]}function r(e,i,n){if(!X.isWebKit&&o.querySelector("select:focus"))return!0;if(!o.contains(e))return!1;for(var r=e;r&&r!==o;){if(r.classList.contains(y))return!0;var l=t(r);if([l.overflow,l.overflowX,l.overflowY].join("").match(/(scroll|auto)/)){var s=r.scrollHeight-r.clientHeight;if(s>0&&!(0===r.scrollTop&&n>0||r.scrollTop===s&&n<0))return!0;var a=r.scrollLeft-r.clientWidth;if(a>0&&!(0===r.scrollLeft&&i<0||r.scrollLeft===a&&i>0))return!0}r=r.parentNode}return!1}function l(t){var l=n(t),s=l[0],a=l[1];if(!r(t.target,s,a)){var c=!1;e.settings.useBothWheelAxes?e.scrollbarYActive&&!e.scrollbarXActive?(a?Y(e,"top",o.scrollTop-a*e.settings.wheelSpeed):Y(e,"top",o.scrollTop+s*e.settings.wheelSpeed),c=!0):e.scrollbarXActive&&!e.scrollbarYActive&&(s?Y(e,"left",o.scrollLeft+s*e.settings.wheelSpeed):Y(e,"left",o.scrollLeft-a*e.settings.wheelSpeed),c=!0):(Y(e,"top",o.scrollTop-a*e.settings.wheelSpeed),Y(e,"left",o.scrollLeft+s*e.settings.wheelSpeed)),W(e),(c=c||i(s,a))&&(t.stopPropagation(),t.preventDefault())}}var o=e.element;void 0!==window.onwheel?e.event.bind(o,"wheel",l):void 0!==window.onmousewheel&&e.event.bind(o,"mousewheel",l)},touch:function(t){function e(e,i){var n=h.scrollTop,r=h.scrollLeft,l=Math.abs(e),o=Math.abs(i);if(o>l){if(i<0&&n===t.contentHeight-t.containerHeight||i>0&&0===n)return{stop:!t.settings.swipePropagation,prevent:0===window.scrollY}}else if(l>o&&(e<0&&r===t.contentWidth-t.containerWidth||e>0&&0===r))return{stop:!t.settings.swipePropagation,prevent:!0};return{stop:!0,prevent:!0}}function i(e,i){Y(t,"top",h.scrollTop-i),Y(t,"left",h.scrollLeft-e),W(t)}function n(){b=!0}function r(){b=!1}function l(t){return t.targetTouches?t.targetTouches[0]:t}function o(t){return!(t.pointerType&&"pen"===t.pointerType&&0===t.buttons||(!t.targetTouches||1!==t.targetTouches.length)&&(!t.pointerType||"mouse"===t.pointerType||t.pointerType===t.MSPOINTER_TYPE_MOUSE))}function s(t){if(o(t)){g=!0;var e=l(t);u.pageX=e.pageX,u.pageY=e.pageY,d=(new Date).getTime(),null!==f&&clearInterval(f),t.stopPropagation()}}function a(n){if(!g&&t.settings.swipePropagation&&s(n),!b&&g&&o(n)){var r=l(n),a={pageX:r.pageX,pageY:r.pageY},c=a.pageX-u.pageX,h=a.pageY-u.pageY;i(c,h),u=a;var f=(new Date).getTime(),v=f-d;v>0&&(p.x=c/v,p.y=h/v,d=f);var m=e(c,h),w=m.stop,Y=m.prevent;w&&n.stopPropagation(),Y&&n.preventDefault()}}function c(){!b&&g&&(g=!1,t.settings.swipeEasing&&(clearInterval(f),f=setInterval(function(){t.isInitialized?clearInterval(f):p.x||p.y?Math.abs(p.x)<.01&&Math.abs(p.y)<.01?clearInterval(f):(i(30*p.x,30*p.y),p.x*=.8,p.y*=.8):clearInterval(f)},10)))}if(X.supportsTouch||X.supportsIePointer){var h=t.element,u={},d=0,p={},f=null,b=!1,g=!1;X.supportsTouch?(t.event.bind(window,"touchstart",n),t.event.bind(window,"touchend",r),t.event.bind(h,"touchstart",s),t.event.bind(h,"touchmove",a),t.event.bind(h,"touchend",c)):X.supportsIePointer&&(window.PointerEvent?(t.event.bind(window,"pointerdown",n),t.event.bind(window,"pointerup",r),t.event.bind(h,"pointerdown",s),t.event.bind(h,"pointermove",a),t.event.bind(h,"pointerup",c)):window.MSPointerEvent&&(t.event.bind(window,"MSPointerDown",n),t.event.bind(window,"MSPointerUp",r),t.event.bind(h,"MSPointerDown",s),t.event.bind(h,"MSPointerMove",a),t.event.bind(h,"MSPointerUp",c)))}}},R=function(n,r){var l=this;if(void 0===r&&(r={}),"string"==typeof n&&(n=document.querySelector(n)),!n||!n.nodeName)throw new Error("no element is specified to initialize PerfectScrollbar");this.element=n,n.classList.add("ps"),this.settings={handlers:["click-rail","drag-thumb","keyboard","wheel","touch"],maxScrollbarLength:null,minScrollbarLength:null,scrollXMarginOffset:0,scrollYMarginOffset:0,suppressScrollX:!1,suppressScrollY:!1,swipePropagation:!0,swipeEasing:!0,useBothWheelAxes:!1,wheelPropagation:!1,wheelSpeed:1};for(var o in r)l.settings[o]=r[o];this.containerWidth=null,this.containerHeight=null,this.contentWidth=null,this.contentHeight=null;var s=function(){return n.classList.add("ps--focus")},a=function(){return n.classList.remove("ps--focus")};this.isRtl="rtl"===t(n).direction,this.isNegativeScroll=function(){var t=n.scrollLeft,e=null;return n.scrollLeft=-1,e=n.scrollLeft<0,n.scrollLeft=t,e}(),this.negativeScrollAdjustment=this.isNegativeScroll?n.scrollWidth-n.clientWidth:0,this.event=new m,this.ownerDocument=n.ownerDocument||document,this.scrollbarXRail=i("ps__rail-x"),n.appendChild(this.scrollbarXRail),this.scrollbarX=i("ps__thumb-x"),this.scrollbarXRail.appendChild(this.scrollbarX),this.scrollbarX.setAttribute("tabindex",0),this.event.bind(this.scrollbarX,"focus",s),this.event.bind(this.scrollbarX,"blur",a),this.scrollbarXActive=null,this.scrollbarXWidth=null,this.scrollbarXLeft=null;var h=t(this.scrollbarXRail);this.scrollbarXBottom=parseInt(h.bottom,10),isNaN(this.scrollbarXBottom)?(this.isScrollbarXUsingBottom=!1,this.scrollbarXTop=c(h.top)):this.isScrollbarXUsingBottom=!0,this.railBorderXWidth=c(h.borderLeftWidth)+c(h.borderRightWidth),e(this.scrollbarXRail,{display:"block"}),this.railXMarginWidth=c(h.marginLeft)+c(h.marginRight),e(this.scrollbarXRail,{display:""}),this.railXWidth=null,this.railXRatio=null,this.scrollbarYRail=i("ps__rail-y"),n.appendChild(this.scrollbarYRail),this.scrollbarY=i("ps__thumb-y"),this.scrollbarYRail.appendChild(this.scrollbarY),this.scrollbarY.setAttribute("tabindex",0),this.event.bind(this.scrollbarY,"focus",s),this.event.bind(this.scrollbarY,"blur",a),this.scrollbarYActive=null,this.scrollbarYHeight=null,this.scrollbarYTop=null;var d=t(this.scrollbarYRail);this.scrollbarYRight=parseInt(d.right,10),isNaN(this.scrollbarYRight)?(this.isScrollbarYUsingRight=!1,this.scrollbarYLeft=c(d.left)):this.isScrollbarYUsingRight=!0,this.scrollbarYOuterWidth=this.isRtl?u(this.scrollbarY):null,this.railBorderYWidth=c(d.borderTopWidth)+c(d.borderBottomWidth),e(this.scrollbarYRail,{display:"block"}),this.railYMarginHeight=c(d.marginTop)+c(d.marginBottom),e(this.scrollbarYRail,{display:""}),this.railYHeight=null,this.railYRatio=null,this.settings.handlers.forEach(function(t){return L[t](l)}),this.event.bind(this.element,"scroll",function(){return W(l)}),W(this)},H={isInitialized:{configurable:!0}};return H.isInitialized.get=function(){return this.element.classList.contains("ps")},R.prototype.update=function(){this.isInitialized&&(this.negativeScrollAdjustment=this.isNegativeScroll?this.element.scrollWidth-this.element.clientWidth:0,e(this.scrollbarXRail,{display:"block"}),e(this.scrollbarYRail,{display:"block"}),this.railXMarginWidth=c(t(this.scrollbarXRail).marginLeft)+c(t(this.scrollbarXRail).marginRight),this.railYMarginHeight=c(t(this.scrollbarYRail).marginTop)+c(t(this.scrollbarYRail).marginBottom),e(this.scrollbarXRail,{display:"none"}),e(this.scrollbarYRail,{display:"none"}),W(this),Y(this,"top",this.element.scrollTop),Y(this,"left",this.element.scrollLeft),e(this.scrollbarXRail,{display:""}),e(this.scrollbarYRail,{display:""}))},R.prototype.destroy=function(){this.isInitialized&&(this.event.unbindAll(),r(this.scrollbarX),r(this.scrollbarY),r(this.scrollbarXRail),r(this.scrollbarYRail),this.removePsClasses(),this.element=null,this.scrollbarX=null,this.scrollbarY=null,this.scrollbarXRail=null,this.scrollbarYRail=null)},R.prototype.removePsClasses=function(){for(var t=this,e=0;e<this.element.classList.length;e++){var i=t.element.classList[e];"ps"!==i&&0!==i.indexOf("ps-")||t.element.classList.remove(i)}},Object.defineProperties(R.prototype,H),R});
/*!
 * perfect-scrollbar v1.0.3
 * (c) 2017 Hyunje Jun
 * @license MIT
 */
/* ----- end -------*/