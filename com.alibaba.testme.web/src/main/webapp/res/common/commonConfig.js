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
	var i;
	for(i=0;i<fom.delid.length;i++){
    	if(fom.delid[i].checked){
			list+=fom.delid[i].value+",";
    	}
	}
	return list;
}