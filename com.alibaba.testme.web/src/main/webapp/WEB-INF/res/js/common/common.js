
//验证框架开关，如果设置为false，则关闭在提交前对数据进行校验
var validateToggle = true;

//刷新验证码
function f_changeCode(path) {
    $('#kaptcha').attr('src', path +'/kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn();  
    $("#authCode").val("").focus();
}

$().ready(function(){
	$("#authCode").val("");
});

/**
 * 校验字符数目
 * 一个汉字算两个字符
 * @param str
 * @param maxLength
 * @param minLength
 * @returns {Boolean}
 */
function f_wordsLength(str, maxLength, minLength){
	var r = /[^\x00-\xff]/g;
	if(minLength == null){
		minLength = 0;
	}
	if((str.replace(r,"mm").length >= minLength) && (str.replace(r,"mm").length <= maxLength)){
		return true;
	}else{
		return false;
	}
}

/**
 * 登录时候会员昵称校验
 * @param value
 * @returns
 */
function f_isUserNick_login(value) {
	var exp = new RegExp(regExp_userNick);
    var check = exp.test(value); 
    if (check && value.indexOf("__") == -1) {
    	return f_wordsLength(value, 20, 4);
    }
    return false;
}

/**
 * 注册时候会员昵称校验
 * @param value
 * @returns
 */
function f_isUserNick_reg(value) {
	var exp = new RegExp(regExp_userNick);
    var check = exp.test(value);
    if (check && value.indexOf("__") == -1) {
    	return f_wordsLength(value, 20, 5);
    }
    return false;
}

function f_isEmail(value) {
	return  /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
}

/**
 * 获取网站根路径
 * @returns
 */
function f_getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
}

/**
 * 判断多选框是否选中
 * 
 * @param obj
 * @returns {Boolean}
 */
function f_isChecked(obj) {
	if($(obj).attr("checked") == "checked"){
		return true
	} else {
		return false;
	}
}

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
}

//获取被选中的radioBox的值
function f_getCheckedRadioValue(radioName)  
{  
  var obj = document.getElementsByName(radioName);
  for(i = 0; i < obj.length; i++)
  {  
	  if (obj[i].checked == true) {
	  	return obj[i].value;
	  }
  }
  return null;
}

/**
 * 删除多余空格，只剩下一个空格
 * @param str
 * @returns
 */
function f_delMoreOneSpace(str){
	while (str.indexOf("  ") != -1) {
		str = str.replace("  "," ");
	}
	return str;
}


/**  
* Make a map like java.  
* You can use this map like this :   
* var myMap = new Map();  
* myMap.put("key","value");  
* var key = myMap.get("key");  
*/  
function Map() {   

  this.elements = new Array();   

  this.size = function() {   
      return this.elements.length;   
  }   

  this.isEmpty = function() {   
      return (this.elements.length < 1);   
  }   

  this.clear = function() {   
      this.elements = new Array();   
  }   

  this.put = function(_key, _value) {   
      this.elements.push({key:_key, value:_value});   
  }   

  this.remove = function(_key) {   
      var bln = false;   

      try {   
          for (i = 0; i < this.elements.length; i++) {   
              if (this.elements[i].key == _key) {   
                  this.elements.splice(i, 1);   
                  return true;   
              }   
          }   
      } catch(e) {   
          bln = false;   
      }   
      return bln;   
  }   

  this.get = function(_key) {   
      try{    
          for (i = 0; i < this.elements.length; i++) {   
              if (this.elements[i].key == _key) {   
                  return this.elements[i].value;   
              }   
          }   
      }catch(e) {   
          return null;   
      }   
  }   

  this.element = function(_index) {   
      if (_index < 0 || _index >= this.elements.length) {   
          return null;   
      }   
      return this.elements[_index];   
  }   

  this.containsKey = function(_key) {   
      var bln = false;   
      try {   
          for (i = 0; i < this.elements.length; i++) {   
              if (this.elements[i].key == _key) {   
                  bln = true;   
              }   
          }   
      }catch(e) {   
          bln = false;   
      }   
      return bln;   
  }   

  this.containsValue = function(_value) {   
      var bln = false;   
      try {   
          for (i = 0; i < this.elements.length; i++) {   
              if (this.elements[i].value == _value){   
                  bln = true;   
              }   
          }   
      } catch(e) {   
          bln = false;   
      }   
      return bln;   
  }   

  this.values = function() {   
      var arr = new Array();   
      for (i = 0; i < this.elements.length; i++) {   
          arr.push(this.elements[i].value);   
      }   
      return arr;   
  }   

  this.keys = function() {   
      var arr = new Array();   
      for (i = 0; i < this.elements.length; i++) {   
          arr.push(this.elements[i].key);   
      }   
      return arr;   
  }   
}   

/**
 * 模拟Java中的ArrayList
 */
function List(){

	this.length=0;
	this.array = new Array();
	this.position = 0;

	//添加一个元素
	this.add = function(obj){
			this.array[this.length]=obj;
			this.length++;
	}
	
	//删除一个元素
	this.remove = function(position){
			if(position < this.length && position >= 0 && this.length>0){
				for(var i=position;i<this.length-1;i++){
					this.array[i]=this.array[i+1];
				}
				this.length--;
			}
	}
	
	//获取一个元素
	this.get = function(position){
		if(position < this.length && position >= 0 && this.length>0){
			return this.array[position];
		}
	}
	
	//删除所有元素
	this.removeAll = function(){
			this.length=0;
	}
	
	//获取元素数组
	this.toArray = function(){
		var arr = new Array();
		for(var i=0;i<this.length;i++){
			arr[i]=this.array[i];
		}
		return arr;
	}
	
	//获取元素个数
	this.size = function(){
		return this.length;
	}
}


function f_msg(content){
	
	var title = "提示信息";
	var btnCode = "<input type=button value='我知道了' onclick='$.unblockUI();'>";
	
	$.blockUI({ 
        message: 
        	'<table>' +
        	'<tr><td>' + title + '</td></tr>' +
        	'<tr><td>' + content + '</td></tr>' + 
        	'<tr><td>' + btnCode + '</td></tr>' +
        	'</table>', 
        fadeIn: 0, 
        fadeOut: 0, 
        css: { 
            top: '20%', 
            border: '7px solid #1067AF',
            padding: '10px', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: 1
        },
        overlayCSS: { 
        	backgroundColor: '#fff',
            opacity: 0.8
        }
	}); 
}

//全选
function f_allChecked(name){
	$("[name=" + name + "]").each(function(i,o){
		$(o).attr("checked","checked");
	});
}

//全反选
function f_reverseCheck(name){
	$("[name=" + name + "]").each(function(i,o){
		if ($(o).attr("checked") == "checked") {
			$(o).removeAttr("checked");
		} else {
			$(o).attr("checked","checked");
		}
	});
}

 