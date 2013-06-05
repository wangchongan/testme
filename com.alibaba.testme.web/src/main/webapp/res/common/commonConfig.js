//全选
function selectAll(fom,delid){
	var obj = fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == delid){
			obj[i].checked = true;
		}
	}
}

//反选
function unselectAll(fom,delid){
	var obj = fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == delid){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

//获取已选中的行的value值
function getCheckedValue(fom,delid){
	var list = "";
	if(fom.delid.length){
    	for(var i=0;i<fom.delid.length;i++){
        	if(fom.delid[i].checked){
    			list+=fom.delid[i].value+",";
        	}
    	}
	}else{
		if(fom.delid.checked){
    			list+=fom.delid.value+",";
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
function batchDelProcess(formId,checkBoxName,urlName){
	var ids = getCheckedValue(formId,checkBoxName);
	if(!ids){
		alert("请先选择需要删除的项！");
	}else{
		if(window.confirm('确认删除么？')){	
			location.href= urlName+ids;
		}
	}

}