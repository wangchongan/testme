function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

function loading(){
	createDiv();
	var DivH = document.body.scrollHeight;
	
    var v_left=(document.body.clientWidth)/2 + document.body.scrollLeft;
    var v_top=(document.body.clientHeight)/2 + document.body.scrollTop;

   	document.getElementById("divProcessing2").style.display = "";
	document.getElementById('divProcessing2').style.height = DivH;
	document.getElementById("divSearch2").style.display = "";
	document.getElementById("divSearch2").style.top = v_top;
	document.getElementById("divSearch2").style.left = v_left;
}

function createDiv() {
	var divsearchObj = document.createElement("div");
	divsearchObj.setAttribute("id", "divSearch2");
	divsearchObj.style.cssText = "z-index:9998;width:100%;height:100%;position:absolute;left:0px;top:0px;display:none;filter:Alpha(opacity=0);";
	divsearchObj.innerHTML="<table width='100%' height='100%' border='0' cellspacing='0' cellpadding='0'><tr><td>&nbsp;</td></tr></table>";
	document.body.appendChild(divsearchObj);
	
	var divProcessObj = document.createElement("div");
	divProcessObj.setAttribute("id", "divProcessing2");
	divProcessObj.style.cssText= "z-index:9999;width:1200;height:120;position:absolute;left:0px;top:0px;display:none;border:1 #3E85EB solid;background-color:#F5FFEB;";
	divProcessObj.innerHTML=" <div style=\"position:absolute;top:50%;left:40%;font-size:9pt;color:#000000\" id=\"abc\"><img src=\""+ getContextPath()+"/nresources/default/images/loading.gif\"style=\"vertical-align:-10px;\"></div>";

	document.body.appendChild(divProcessObj);
}






//全选
function selectAll(formObject,checkBoxName){
	var obj = formObject.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == checkBoxName){
			obj[i].checked = true;
		}
	}
}

//反选
function unselectAll(formObject,checkBoxName){
	var obj = formObject.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == checkBoxName){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

//获取已选中的行的value值
function getCheckedValue(checkBoxObject){
	var list = "";
	if(checkBoxObject.length){
    	for(var i=0;i<checkBoxObject.length;i++){
        	if(checkBoxObject[i].checked){
    			list+=checkBoxObject[i].value+",";
        	}
    	}
	}else{
		if(checkBoxObject.checked){
    			list+=checkBoxObject.value+",";
        }
	}
	
	return list;
}

//提交表单
function submitFormProcess(formId,actionName){
   document.getElementById(formId).action=actionName;
   document.getElementById(formId).submit();
}

//删除记录
function delProcess(url){	
	if(window.confirm('确认删除么？')){
		location.href= url;
	}
}

//批量删除记录
function batchDelProcess(checkBoxObject,urlName){
	var ids = getCheckedValue(checkBoxObject);
	if(!ids){
		alert("请先选择需要删除的项！");
	}else{
		if(window.confirm('确认删除么？')){	
			location.href= urlName+ids;
		}
	}
}

