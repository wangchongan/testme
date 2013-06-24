function submitForm(formId,actionName,tableObject,configTableRownumberListId){
	var validateResult = true;
	var name=document.getElementById("name").value;
	var code=document.getElementById("code").value;
	var systemId = document.getElementById("systemId").value;
	var tag = document.getElementById("tag").value;
	var workSpaceId = document.getElementById("workSpaceId").value;
	var customWorkSpace = document.getElementById("customWorkSpace").value;
	var classQualifiedName = document.getElementById("classQualifiedName").value;
	var bundleFile = document.getElementById("bundleFile").value;
	if(!name){
		document.getElementById("nameNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("nameNotNull").style.display="none";
	}
	if(!code){
		document.getElementById("codeNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("codeNotNull").style.display="none";
	}
	
	if(!systemId){
		document.getElementById("systemIdNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("systemIdNotNull").style.display="none";
	}
	
	if(!tag){
		document.getElementById("tagNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("tagNotNull").style.display="none";
	}
	
	if((!workSpaceId||workSpaceId=="0") && !customWorkSpace){
		document.getElementById("workSpaceIdNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("workSpaceIdNotNull").style.display="none";
	}
	
	if(!classQualifiedName){
		document.getElementById("classQualifiedNameNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("classQualifiedNameNotNull").style.display="none";
	}
	
	if(!bundleFile){
		document.getElementById("bundleFileNotNull").style.display="";
		validateResult=false;
	}else{
		document.getElementById("bundleFileNotNull").style.display="none";
	}
	
	var jsonParamList=[];
	var rowLength = tableObject.rows.length;
	var rankList = [];
	rankList.push("#");
	if(rowLength>2){ //表示有数据，数据从第三行开始
		//获取存在的数据行号
		var configTableRownumberListValue = document.getElementById(configTableRownumberListId).value;
		var rownums="";
		if(configTableRownumberListValue){
			rownums = configTableRownumberListValue.split("#");
		}
		jsonParamList.push("[");
		for(var i=0;i<rownums.length;i++){
			var k = rownums[i];
			if(!k.trim() || isNaN(k)){
			   continue;
			}
			var testunitParamId="testunitParamId_"+k;
			var labelName="labelName_"+k;
   			var paramName="paramName_"+k;
            var formCtrlType="formCtrlType_"+k;
			var defaultValue = "defaultValue_"+k;
			var isRequiredYes="isRequiredYes_"+k;
			var rank="rank_"+k;
			var help="help_"+k;
            var paramExt = "paramExt_"+k;
			
			var testunitParamIdValue= document.getElementById(testunitParamId).value;
			var labelNameValue = document.getElementById(labelName).value;
			var paramNameValue = document.getElementById(paramName).value;
			var formCtrlTypeValue = document.getElementById(formCtrlType).value;
			var defaultValueOfValue = document.getElementById(defaultValue).value;
			var isRequiredValue="";
			if(document.getElementById(isRequiredYes).checked){
				isRequiredValue="Y";
			}else{
				isRequiredValue="N";
			}
			var helpValue = document.getElementById(help).value;
			var rankValue = document.getElementById(rank).value;
			
			var paramExtValue = document.getElementById(paramExt).value;
			document.getElementById("testunitConfigNotNull").style.display="none";
			document.getElementById("selectTypeConfigNotNull").style.display="none";
			document.getElementById("rankValueNotRepeat").style.display="none";
			
			if(!labelNameValue || !paramNameValue || !formCtrlTypeValue || !rankValue){
				document.getElementById("testunitConfigNotNull").style.display="";
				document.getElementById("rankValueNotRepeat").style.display="none";
				document.getElementById("selectTypeConfigNotNull").style.display="none";
				validateResult =false;
			}
			if(formCtrlTypeValue == "selectType" && !paramExtValue){
				document.getElementById("selectTypeConfigNotNull").value=" 参数名称为"+paramNameValue+"的表单控件类型为下拉菜单，请先配置该下拉菜单包含的值";
				document.getElementById("selectTypeConfigNotNull").style.display="";
				document.getElementById("rankValueNotRepeat").style.display="none";
				document.getElementById("testunitConfigNotNull").style.display="none";
				validateResult=false;
			}
			var rankStr = rankList.join("");
			if(rankStr.indexOf(rankValue) != -1){
				document.getElementById("testunitConfigNotNull").style.display="none";
				document.getElementById("selectTypeConfigNotNull").style.display="none";
				document.getElementById("rankValueNotRepeat").style.display="";
				validateResult =false;
			}else{
				rankList.push(rankValue);
				rankList.push("#");
			}
			//拼接json
			jsonParamList.push("{");
			jsonParamList.push("\"testunitParamId\":");
			jsonParamList.push("\"");
			jsonParamList.push(testunitParamIdValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"labelName\":");
			jsonParamList.push("\"");
			jsonParamList.push(labelNameValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"paramName\":");
			jsonParamList.push("\"");
			jsonParamList.push(paramNameValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"formCtrlType\":");
			jsonParamList.push("\"");
			jsonParamList.push(formCtrlTypeValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"defaultValue\":");
			jsonParamList.push("\"");
			jsonParamList.push(defaultValueOfValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"rank\":");
			jsonParamList.push("\"");
			jsonParamList.push(rankValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"isRequired\":");
			jsonParamList.push("\"");
			jsonParamList.push(isRequiredValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"help\":");
			jsonParamList.push("\"");
			jsonParamList.push(helpValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"testunitParamExt\":");
			jsonParamList.push("\"");
			jsonParamList.push(paramExtValue);
			jsonParamList.push("\",");
			
			jsonParamList.push("\"rownumber\":");
			jsonParamList.push("\"");
			jsonParamList.push(k);
			jsonParamList.push("\"}");
			if(i!=rownums.length-1 && rownums[i+1].trim()){
				jsonParamList.push(",");
			}
		}
		jsonParamList.push("]");
	}
	
	if(validateResult==true){
		document.getElementById("testunitParamVOStr").value=jsonParamList.join("");
		submitFormProcess(formId,actionName);
	}

}

function getWorkSpaceList(){
	var systemId = document.getElementById("systemId").value;
	if(systemId){
	   location.href= "${contextPath}/testunitmanage/getWorkSpaceListBySystemId?systemId="+systemId;
	}
}

function doShowConfig(rownumber){
	var formCtrlType = "formCtrlType_"+rownumber;
	var configSpanId = "configSpanId_"+rownumber;
	var value = document.getElementById(formCtrlType).value;
	if(value=="selectType"){
		document.getElementById(configSpanId).style.display="";
	}else{
		document.getElementById(configSpanId).style.display="none";
	}

}

function deleteRows(rownumber,marknumber,configTableRownumberList){
	addTestunitConfigTable.deleteRow(rownumber);
	var configTableRownumberListValue = document.getElementById(configTableRownumberList).value;
	if(configTableRownumberListValue){
		var str = marknumber+"#";
   		document.getElementById(configTableRownumberList).value = configTableRownumberListValue.replace(str,"");
	}
}

function addPropKey(marknumber){
    var paramId = "paramExt_"+marknumber;
	var paramValue = document.getElementById(paramId).value;
	var keys="",values="";
	if(paramValue){
		var keyAndValues = paramValue.split(";");
		if(keyAndValues){
			for(var k=0;k<keyAndValues.length;k++){
			    var keyvalue = keyAndValues[k].split("=");
				keys+=(keyvalue[0]+"\n");
				values+=(keyvalue[1]+"\n");
			}	
		}
	}
	document.getElementById("paramKey").value=keys;
	document.getElementById("rowmarknumber").value=marknumber;
	document.getElementById("paramValue").value=values;
	document.getElementById("drag").style.display="";
}

function doConfirmProcess(){
	var keyStr = document.getElementById("paramKey").value;
	var valueStr = document.getElementById("paramValue").value;
	var keys="",values="",keyAndValues="";
	if(keyStr){
		keys = keyStr.trim().split("\n");
	}
	if(valueStr){
		values=valueStr.trim().split("\n");
	}
	if(!keys || !values || keys.length!=values.length){
		alert("配置项设置有误！控件名称和控件值个数不对应！");
		return;
	}
	for(var k=0;k<keys.length;k++){
		keyAndValues+=(keys[k]+"="+values[k]);
		if(k!=keys.length-1){
			keyAndValues+=";";
		}
	}
	var rowmarknumber = document.getElementById("rowmarknumber").value;
	var paramId = "paramExt_"+rowmarknumber;
	document.getElementById(paramId).value=keyAndValues;
	document.getElementById("drag").style.display="none";
}