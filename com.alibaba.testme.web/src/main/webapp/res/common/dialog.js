function addPropKey(){
	document.getElementById("drag").style.display="";
}

function doCancelProcess(){
	document.getElementById("drag").style.display="none";
}

var get = {
	byId: function(id) {
		return typeof id === "string" ? document.getElementById(id) : id;
	},
	byClass: function(sClass, oParent) {
		var aClass = [];
		var reClass = new RegExp("(^| )" + sClass + "( |$)");
		var aElem = this.byTagName("*", oParent);
		for (var i = 0; i < aElem.length; i++) reClass.test(aElem[i].className) && aClass.push(aElem[i]);
		return aClass;
	},
	byTagName: function(elem, obj) {
		return (obj || document).getElementsByTagName(elem);
	}
};
//窗口拖拽

function drag(oDrag,oTitle){
	var posX=posY=0;
	oTitle.onmousedown=function(event){
		oTitle.style.cursor = "move";
		var event1 = event || window.event;
		var disX=event1.clientX-oDrag.offsetLeft;
		var disY=event1.clientY-oDrag.offsetTop;
		document.onmousemove=function(event){
			var event2 = event || window.event;
			maxW=document.documentElement.clientWidth-oDrag.offsetWidth;
			maxH=document.documentElement.clientHeight-oDrag.offsetHeight;
			posX=event2.clientX-disX;
			posY=event2.clientY-disY;
			if(posX<0){
				posX=0;
			}else if(posX>maxW){
				posX=maxW;
			}
			if(posY<0){
				posY=0;
			}else if(posY>maxH){
				posY=maxH;
			}
			oDrag.style.left=posX+'px';
			oDrag.style.top=posY+'px';
		};
		//鼠标松开，窗口将不再移动
		document.onmouseup=function(){
			document.onmousemove=null;
			document.onmouseup=null;
		};
	};
}
window.onload  = function (){
	var oDrag = document.getElementById("drag");
	var oTitle = get.byClass("title", oDrag)[0];
	drag(oDrag,oTitle);
};