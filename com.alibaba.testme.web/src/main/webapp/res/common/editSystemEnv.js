function isExistsConfigName(){
	var configName = document.getElementById("configName").value;
	location.href= "${contextPath}/systemconfig/isExistsConfigName?configName="+configName;
}

//编辑配置名称和所属系统
function editConfigNameProcess(){
	document.getElementById("editConfigNameBtn").style.display="none";
	document.getElementById("saveConfigNameBtn").style.display="";
	document.getElementById("onlyRead_configName").style.display="none";
	document.getElementById("update_configName").style.display="";
	document.getElementById("update_configNameSpan").style.display="";
	document.getElementById("onlyRead_systemName").style.display="none";
	document.getElementById("update_systemName").style.display="";
}
//保存配置名称和所属系统
function saveConfigNameProcess(systemEnvId){
   var configName = encodeURIComponent(document.getElementById("update_configName").value);
   var systemId = document.getElementById("update_systemName").value;
   if(!configName || !systemId){
		alert("配置名称和所属系统不能为空");
   }else{
   		document.getElementById("editConfigNameBtn").style.display="";
		document.getElementById("saveConfigNameBtn").style.display="none";
		document.getElementById("onlyRead_configName").style.display="";
		document.getElementById("update_configName").style.display="none";
		document.getElementById("update_configNameSpan").style.display="none";
		document.getElementById("onlyRead_systemName").style.display="";
		document.getElementById("update_systemName").style.display="none";
  		location.href= "${contextPath}/systemconfig/updateSystemEnv?systemEnvId="+systemEnvId+"&configName="+configName+"&systemId="+systemId;
   }
}

//编辑参数值
function editProcess(systemEnvDetailId){
	var saveBtnName = "saveBtn_"+systemEnvDetailId;
	var textName = "propValueText_"+systemEnvDetailId;
	var labelName = "propValueLabel_"+systemEnvDetailId;
	document.getElementById(textName).style.display="";
	document.getElementById(saveBtnName).style.display="";
	document.getElementById(labelName).style.display="none";
}

//保存参数值
function savePropValueProcess(systemEnvDetailId,systemEnvId){
   var saveBtnName = "saveBtn_"+systemEnvDetailId;
   var textName = "propValueText_"+systemEnvDetailId;
   var labelName = "propValueLabel_"+systemEnvDetailId;
   var propValue = document.getElementById(textName).value;
   if(!propValue){
		alert("参数值不能为空");
   }else{
   		document.getElementById(textName).style.display="none";
		document.getElementById(saveBtnName).style.display="none";
		document.getElementById(labelName).style.display="";
  		location.href= "${contextPath}/systemconfig/editPropValueBySystemEnv?systemEnvDetailId="+systemEnvDetailId+"&propValue="+propValue+"&systemEnvId="+systemEnvId;
   }
}
