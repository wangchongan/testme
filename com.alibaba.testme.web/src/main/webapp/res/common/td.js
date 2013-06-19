$(document).ready(function() {
	if (jQuery.browser.msie) {
		if (navigator.appVersion.match(/MSIE 6./i) == 'MSIE 6.'
			|| navigator.appVersion.match(/MSIE 7./i) == 'MSIE 7.' 
			|| navigator.appVersion.match(/MSIE 7./i) == 'MSIE 8.') {
		    alert('当前浏览器版本无法使用数据工厂全部功能，请升级至IE9及以上版本，或更换其他浏览器。');
		}
		$("form[data-remote]").find("input[type='submit'],button[type='submit'],input[name='commit']").live('click', function(e) {
			$(this).parents("form[data-remote]").callRemote();
			if(e.preventDefault) {
		      	e.preventDefault();
			}
		});
	}
});

$('.form input').live('click', function() {
    var $detail_wrap = $(this).parent().parent().parent().parent().parent().next();
    var $detail = $detail_wrap.children('.detail');
    var $toggle = $detail_wrap.next('.toggle');
    if (!$detail.is(":hidden")) {
		$detail.hide(150);
		$toggle.show();
    }
});

$('.toggle').live('click', function() {
    $(this).css('display', 'none');
    $(this).prev(".detail-wrap").css('display', 'block');
});

function doSreach(e) {
    if (window.event.keyCode == 13) {
		$(e).parents('form').submit();
    }
}

function doInvoke(e) {
    if (window.event.keyCode == 13) {
		$(e).parents('form').submit();
    }
}

function displayWaiting() {
//    unblockUI();
    blockUI({ css: {
        border: 'none',
        padding: '15px',
        backgroundColor: '#000',
        '-webkit-border-radius': '10px',
        '-moz-border-radius': '10px',
        opacity: .5,
        color: '#fff'
    },
        message: '<h1>调用中……</h1>'
    });
}

function checkVersion() {
    var current_version = '1.2';
    if (typeof window.localStorage != 'undefined') {
		var version = window.localStorage.getItem('history_version');
		if(version == null || version != current_version) {
		    window.localStorage.clear();
		    window.localStorage.setItem('history_version', current_version);
		}
    }
}

function saveHistoryify(parameter, result) {
    if(result != null && typeof result != 'undefined'
       && result.body != null && typeof result.body != 'undefined'
       && result.success != null && typeof result.success != 'undefined' && result.success == true) {
		saveHistory(parameter, result.body);
    }
}

function saveHistory(parameter, result) {
    if (typeof window.localStorage != 'undefined') {
		try {
		    var history = {};
		    history.tid = parameter.tid;
		    history.parameter = parameter;
		    history.result = result;
		    var time = new Date();
		    history.time = time.format('yyyy-MM-dd hh:mm:ss');
		    var key = history.tid + '_' + Date.parse(time);
		    history.key = key;
		    var his_str = JSON.stringify(history);
		    saveSizeIndex(key, his_str);
		    window.localStorage.setItem(key, his_str); //save item
		    //update index column
		    var index = JSON.parse(window.localStorage.getItem('index_' + history.tid));
		    if (isArray(index)) {
				var len = index.length;
				if (len > 0) {
				    if (len >= 10) {//max size is 10
						var temp_key = index[len - 1];
						index = removeIndex(temp_key);
						removeSizeIndex(temp_key);
						window.localStorage.removeItem(temp_key); //remove item
				    }
				    index.splice(0, 0, key); //insert key to the head of index
				} else {
				    index[0] = key;
				}
		    } else {
				index = new Array();
				index[0] = key;
		    }
		    window.localStorage.setItem('index_' + history.tid, JSON.stringify(index)); //update index
		} catch(e) {
		    if(clearLocalStorage()) {
				saveHistory(parameter, result);
		    }
		}
    }
}

function clearLocalStorage() {
    var keys = new Array('weights_high', 'weights_mid', 'weights_low');
    return removeHistoryBySizeIndex(keys);
}

function removeHistoryBySizeIndex(keys) {
    if(isArray(keys) && keys.length > 0) {
		var key = getFirstAndRemove(keys);
		var hiss = JSON.parse(window.localStorage.getItem(key));
		if (isArray(hiss) && hiss.length > 0) {
		    for(var i = 0; i < hiss.length; i++) {
				window.localStorage.removeItem(hiss[i]);
				removeIndex(hiss[i]);
		    }
		    window.localStorage.removeItem(key);
		    return true;
		} else {
		    return removeHistoryBySizeIndex(keys);
		}
    } else {
		return false;
    }
}

function getFirstAndRemove(arr) {
    var item = arr[0];
    arr.splice(0, 1);
    return item;
}

function removeIndex(key) {
    var his = JSON.parse(window.localStorage.getItem(key));
    if(his == null) {
		return [];
    }
    var index_k = 'index_' + his.tid;
    var his_index = JSON.parse(window.localStorage.getItem(index_k));
    if(isArray(his_index) && his_index.length > 0) {
		var idx = $.inArray(key, his_index);
		if(idx != -1) {
		    his_index.splice(idx, 1);
		    window.localStorage.setItem(index_k, JSON.stringify(his_index));
		}
		return his_index;
    }
    return [];
}

function removeSizeIndex(key) {
    var his = JSON.parse(window.localStorage.getItem(key));
    if(his != null) {
		var keys = new Array('weights_high', 'weights_mid', 'weights_low');
		for(var i = 0; i < keys.length; i++) {
		    var size_index = JSON.parse(window.localStorage.getItem(keys[i]));
		    if(isArray(size_index) && size_index.length > 0) {
				var idx = $.inArray(key, size_index);
				if(idx != -1) {
				    size_index.splice(idx, 1);
				    window.localStorage.setItem(keys[i], JSON.stringify(size_index));
				}
		    }
		}
    }
}

function saveSizeIndex(key, data) {
    if (typeof window.localStorage != 'undefined') {
		var len = data.length;
		if(len <= 500) {
		    saveToSizeIndex(key, 'weights_low');
		}
		else if(len > 500 && len <= 1000) {
		    saveToSizeIndex(key, 'weights_mid');
		}
		else if(len > 1000) {
		    saveToSizeIndex(key, 'weights_high');
		}
    }
}

function saveToSizeIndex(key, sizeIndex) {
    var idx = JSON.parse(window.localStorage.getItem(sizeIndex));
    if (isArray(idx) && idx.length > 0) {
		idx.splice(0, 0, key);
    } else {
		idx = [];
		idx[0] = key;
    }
    window.localStorage.setItem(sizeIndex, JSON.stringify(idx));
}

function getHistory(tid) {
    if (typeof window.localStorage != 'undefined') {
		var toRemovekey = [];
		var index = JSON.parse(window.localStorage.getItem('index_' + tid)); //get index
		if (isArray(index)) {
		    var len = index.length;
		    if (len > 0) {
				var history = new Array();
				for (var i = 0; i < len; i++) {
				    var his = JSON.parse(window.localStorage.getItem(index[i]));
				    if(his == null || typeof his == 'undefined') {
						toRemovekey[toRemovekey.length] = index[i]; // record index of the history which result is null
				    } 
				    else {
						if(his.result == null || typeof his.result == 'undefined') {
						    window.localStorage.removeItem(index[i]); //remove history from localStorage if result of history is null
						    toRemovekey[toRemovekey.length] = index[i]; // record index of the history which result is null
						} else {
						    history[i] = his;
						}
				    }
				}
				if(toRemovekey.length != 0) {
				    for(j = 0; j < toRemovekey.length; j++) { //remove from index which result of history is null
						removeIndex(toRemovekey[j]);
						removeSizeIndex(toRemovekey[j]);
				    }
				}
				return history;
		    }
		} else {
		    return [];
		}
    } else {
		return [];
    }
}

function compare(source, to) {
    var diff_array = [];
    doCompare('[ROOT]', source, to, diff_array);
    return diff_array;
}

function doCompare(parentName, source, toCompare, diff_array) {
    var src = jsonify(source);
    var to = jsonify(toCompare);
    if(isJson(src) && isJson(to)) {
		for(var key in src) {
		    if(isJson(src[key]) && isJson(to[key])) {
				doCompare(parentName + '.' + key, src[key], to[key], diff_array);
		    } 
		    else if(isArray(src[key]) && isArray(to[key])) {
				doCompare(parentName + '.' + key, src[key], to[key], diff_array);
		    } 
		    else {
				if(src[key] != to[key]) {
				    diff_array[diff_array.length] = parentName + '.' + key;
				}
		    }
		}
    }
    else if(isArray(src) && isArray(to)) {
		if(src.length != to.length) {
		    if(parentName != null && parentName != '') {
				diff_array[diff_array.length] = parentName;
		    }
		} else {
		    for(var i = 0; i < src.length; i++) {
				if(isJson(src[i]) && isJson(to[i])) {
				    doCompare(parentName + '[' + i + ']', src[i], to[i], diff_array);
				}
				else if(isArray(src[i]) && isArray(to[i])) {
				    doCompare(parentName + '[' + i + ']', src[i], to[i], diff_array);
				}
				else {
				    if(src[i] != to[i] && (parentName != null && parentName != '')) {
						diff_array[diff_array.length] = parentName;
						diff_array[diff_array.length] = parentName + '.value:{' + to[i] + '}';
				    }
				}
		    }
		}
    } else {
		if(src != to && (parentName != null && parentName != '')) {
		    diff_array[diff_array.length] = parentName + '.value:{' + to + '}';
		}
    }
}

function showResultTree(param, data,e) {
//    unblockUI();
    var treeNodes;
    var setting = {
    	callback: {
			onClick: copy2Clipboard
		},
		view: {
		    showLine: function() {
				if(data.success) {
				    return true;
				} else {
				    return false;
				}
		    },
		    showIcon: function() {
				if(data.success) {
				    return true;
				} else {
				    return false;
				}
		    },
		    showTitle: false,
		    fontCss: {
				'max-width': '300px',
				'overflow': 'hidden',
				'text-overflow': 'ellipsis'
		    },
		    addHoverDom: displayDetail,
		    removeHoverDom: removeDetail
		    
		},
		data: {
		    simpleData: {
				enable: true
		    }
		}
    };
    paramTreeNodes = [];
    if(param.tid) { //hide tid
		delete param.tid;
    }
    formatJsonForTree(param, '[ROOT]', "0-1", paramTreeNodes, []);
    paramTreeNodes.splice(0, 0, {id:"0-1", pId:"0", name:'调用参数：', open:true});
    if(!data.success) {
		setting.view.fontCss = {'white-space':' pre'};
		setting.view.nameIsHTML = true;
		setting.view.addHoverDom = null;
		setting.view.removeHoverDom = null;
		treeNodes = [
		    {id:"0-1", pId:"0", name:'TD事务调用失败。'}, 
		    {id:"0-2", pId:"0", name:'错误类型：' + getErrorMessage(data.errorCode)}, 
		    {id:"0-3", pId:"0", name:'错误信息：<font color="red">' + data.errorMsg + '</font>'}
		];
    } else {
		if(data.body == null || data.body === '' || data.body.length == 0) {
		    treeNodes = [{id:"0-1", pId:"0", name:'调用成功。返回值：无'}];
		} else {
		    treeNodes = [];
		    formatJsonForTree(data.body, '[ROOT]', "0-1", treeNodes, []);
		    treeNodes.splice(0, 0, {id:"0-1", pId:"0", name:'调用成功。返回值：', open:true});
		}
    }
    initZtree($("#td_param_tree"), setting, paramTreeNodes);
    initZtree($("#td_result_tree"), setting, treeNodes);
    $('#td_invoke_result').css('display', 'block');
    blockUI({
		message: $("#td_result"),
		css: {
		    width: '80%',
		    height: '73%',
		    top: '10%',
		    left:'10%',
		    overflow:'auto',
		    cursor: 'auto'
		},
		overlayCSS: {
		    cursor: 'auto'
		}
    });
}

$('.blockOverlay').live('click', function() {
    if($(".blockMsg").children("h1").length == 0 ){
        unblockUI();
        $('#td_invoke_result').css('display', 'none');
        $('#td_history').css('display', 'none');
        $('#td_compare').css('display', 'none');
    }
});

function initZtree(obj, zSetting, zNodes) {
    if(typeof $.fn.zTree.init !== 'undefined' && $.fn.zTree.init instanceof Function) {
        $.fn.zTree.init(obj, zSetting, zNodes);
    } else {
        if(typeof importScript != 'undefined' && importScript instanceof Function) {
            importScript('res/jquery//jquery.ztree.all-3.5.js');
            $.fn.zTree.init(obj, zSetting, zNodes);
        }
    }
}

function unblockUI() {
    if(typeof $.unblockUI !== 'undefined' && $.unblockUI instanceof Function) {
       $.unblockUI();
    } else {
        if(typeof importScript != 'undefined' && importScript instanceof Function) {
            importScript('res/jquery//jquery.blockUI.js');
            $.unblockUI();
        }
    }
}

function blockUI(opts) {
    if(typeof $.blockUI !== 'undefined' && $.blockUI instanceof Function) {
    	$('#topFrame').block();
    	$('#leftFrame').block();
    	$('#mainFrame').block();
        $.blockUI(opts);
    } else {
        if(typeof importScript != 'undefined' && importScript instanceof Function) {
            importScript('res/jquery/jquery.blockUI.js');
            $.blockUI(opts);
        }
    }
}

function formatJsonForTree(data, parentName, parentId, treeNodes, diff) {
    var node_object = jsonify(data);
    if(isJson(node_object)) {
    	var subId = 0;
		var _subId = parentId + '-';
		for(var key in node_object) {
		    var node = node_object[key];
		    if(!isJson(node) && !isArray(node)) {
				if(diff != null && diff.length > 0 && $.inArray(parentName + '.' + key, diff) != -1) {
				    treeNodes[treeNodes.length] = {id:_subId + subId, pId:parentId, name:'<font color="red">' + key + '=' + node + '</font>'};
				} else {
				    treeNodes[treeNodes.length] = {id:_subId + subId, pId:parentId, name:key + '=' + node};
				}
		    } else {
				if(diff != null && diff.length > 0 && $.inArray(parentName + '.' + key, diff) != -1) {
				    treeNodes[treeNodes.length] = {id:_subId + subId, pId:parentId, name:'<font color="red">' + key + '</font>', open:true};
				} else {
				    treeNodes[treeNodes.length] = {id:_subId + subId, pId:parentId, name:key, open:true};
				}
				formatJsonForTree(node, parentName + '.' + key, _subId + subId, treeNodes, diff);
		    }
		    subId++;
		}
    } 
    else if(isArray(node_object)) {
		for(var idx = 0; idx < node_object.length; idx++) {
		    var node = node_object[idx];
		    var subId = parentId + '-' + idx;
		    if(node != null && node !== '') {
			    if(!isJson(node) && !isArray(node)) {
				    if(diff != null && diff.length > 0 && $.inArray(parentName + '.value:{' + node + '}', diff) != -1) {
						treeNodes[treeNodes.length] = {id:subId, pId:parentId, name:'<font color="red">' + node + '</font>'};
				    } else {
						treeNodes[treeNodes.length] = {id:subId, pId:parentId, name:node.toString()};
				    }
			    } else {
					var pName = parentName + '[' + idx + ']';
					var pNames = pName.split('.');
					if(diff != null && diff.length > 0 && $.inArray(parentName + '.' + pName, diff) != -1) {
					    treeNodes[treeNodes.length] = {id:subId, pId:parentId, name:'<font color="red">' + pNames[pNames.length - 1] + '</font>', open:true};
					} else {
					    treeNodes[treeNodes.length] = {id:subId, pId:parentId, name:pNames[pNames.length - 1], open:true};
					}
					formatJsonForTree(node, pName, subId, treeNodes, diff);
			    }
		    }
		}
    }
    else {
		if(node_object !== null && node_object !== '') {
		    if(diff != null && diff.length > 0 && $.inArray(parentName + '.value:{' + node_object + '}', diff) != -1) {
				treeNodes[treeNodes.length] = {id:parentId + '-1', pId:parentId, name:'<font color="red">' + node_object + '</font>'};
		    } else {
				treeNodes[treeNodes.length] = {id:parentId + '-1', pId:parentId, name:node_object.toString()};
		    }
		}
    }
}

function showHistoryTree(tid) {
    var his = getHistory(tid);
    $('#td_history_list_body').empty();
    if(isArray(his) && his.length > 0) {
		$('#td_history').css('display', 'block');
		for(var i = 0; i < his.length; i++) {
		    if(i == 0) {
				$('#td_history_list_body').append('<tr><td><div><a class="td_record" herf="#" id=' + his[i].key + ' onclick="showComparedTree(this)">最新</a></div></td></tr>');
		    } else {
				$('#td_history_list_body').append('<tr><td><div><a class="td_record" herf="#" id=' + his[i].key + ' onclick="showComparedTree(this)">' + his[i].time + '</a></div></td></tr>');
		    }
		}
    }
}

function showComparedTree(history) {
    $('.history_highlight').removeClass('history_highlight');
    $(history).addClass('history_highlight');
    if (typeof window.localStorage != 'undefined') {
		var to = JSON.parse(window.localStorage.getItem($(history).attr('id')));
		var index = JSON.parse(window.localStorage.getItem('index_' + to.tid));
		var source = JSON.parse(window.localStorage.getItem(index[0]));
		var diff = compare(source.result, to.result);
		var param_diff = compare(source.parameter, to.parameter);
		var setting = {
			callback: {
				onClick: copy2Clipboard
			},
		    view: {
				showLine: true,
				showIcon: true,
				nameIsHTML: true,
				showTitle: false,
				fontCss: {
				    'max-width': '300px',
				    'overflow': 'hidden',
				    'text-overflow': 'ellipsis'
				},
				addHoverDom: displayDetail,
				removeHoverDom: removeDetail
		    },
		    data: {
				simpleData: {
				    enable: true
				}
		    }
		};
		var paramTreeNodes = [];
		if(to.parameter.tid) { //hide tid
		    delete to.parameter.tid;
		}
		formatJsonForTree(to.parameter, '[ROOT]', "0-1", paramTreeNodes, param_diff);
		paramTreeNodes.splice(0, 0, {id:"0-1", pId:"0", name:'调用参数：', open:true});
		var treeNodes = [];
		formatJsonForTree(to.result, '[ROOT]', "0-1", treeNodes, diff);
		treeNodes.splice(0, 0, {id:"0-1", pId:"0", name:'比较成功。返回值：', open:true});
        initZtree($("#td_compare_param_tree"), setting, paramTreeNodes);
        initZtree($("#td_compare_tree"), setting, treeNodes);
		$('#td_compare').css('display', 'block');
    }
}

function displayDetail(treeId, treeNode) {
    $('#' + treeNode.tId + '_detail').remove();
    if(!treeNode.isParent && $('#' + treeNode.tId).children('#clippy').length == 0) {
		$('#' + treeNode.tId).append('<span class="node_detail" id="' + treeNode.tId + '_detail">' + $('#' + treeNode.tId).text() + '</span>');
    }
}

function removeDetail(treeId, treeNode) {
    $('#' + treeNode.tId + '_detail').remove();
}

function copy2Clipboard(event, treeId, treeNode) {
	if(!treeNode.isParent) {
		var text  = treeNode.name;
		var start = text.indexOf(">");
		if(start != -1) {
    		var end = text.indexOf("<", start + 1);
    		if(end != -1) {
    			text = text.substring(start + 1, end);
    		}
    	}
		if (jQuery.browser.msie) {
			if(window.clipboardData) {
				window.clipboardData.clearData();   
             	window.clipboardData.setData("Text", text);
			}
		} else {
			$('#clippy').remove();
			$('#' + treeNode.tId + '_detail').remove();
			$('#' + treeNode.tId).append(
				'<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"' +
				' width="110"' +
				' height="14"' +
				' id="clippy" >' +
				'<param name="movie" value="/flash/clippy.swf"/>' +
				'<param name="allowScriptAccess" value="always" />' +
				'<param name="quality" value="high" />' +
				'<param name="scale" value="noscale" />' +
				'<param NAME="FlashVars" value="text=' + text + '">' +
				'<param name="bgcolor" value="#FFFFFF">' +
				'<embed src="/images/testdata_service/clippy.swf"' +
				' width="110"' +
				' height="14"' +
				' name="clippy"' +
				' quality="high"' +
				' allowScriptAccess="always"' +
				' type="application/x-shockwave-flash"' +
				' pluginspage="http://www.macromedia.com/go/getflashplayer"' +
				' FlashVars="text=' + text + '"' +
				' bgcolor="#FFFFFF"/>' +
				'</object>');
		}
	}
}

function jsonify(str) {
    if(isString(str) && !isJson(str)) {
		if(!str.toString().startsWith('{') && !str.toString().startsWith('[')) {
			return str;
		}
		try {
		    return JSON.parse(str);
		} catch(ex) {
		    return str;
		}
    } else {
		return str;
    }
}

if ( typeof String.prototype.startsWith != 'function' ) {
  String.prototype.startsWith = function( str ) {
    return str.length > 0 && this.substring( 0, str.length ) === str;
  }
};

if ( typeof String.prototype.endsWith != 'function' ) {
  String.prototype.endsWith = function( str ) {
    return str.length > 0 && this.substring( this.length - str.length, this.length ) === str;
  }
};

function isArray(arr) {
    return null != arr && typeof arr != 'undefined' && arr instanceof Array;
}

function isString(str) {
    return null != str && typeof str == 'string' && str.constructor == String;
}

function isJson(obj) {
    return obj != null && typeof obj == 'object' && Object.prototype.toString.call(obj).toLowerCase() == '[object object]';
}

function canJson(str) {
    if(typeof str == 'string' && str.constructor == String) {
	try {
	    JSON.parse(str);
	    return true;
	} catch(ex) {
	    return false;
	}
    }
}

Date.prototype.format = function(format) {
    var o = {
	"M+" : this.getMonth() + 1, //month
	"d+" : this.getDate(), //day
	"h+" : this.getHours(), //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
	"S" : this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
		    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
    }
    return format;
}
$(".favorite_img").live("click", function() {
  var status = $(this).attr('name');
  if(status == 'enable') {
      $(this).attr('src','images/unfavorite.png')
      $(this).attr('name', 'disable');
  } else {
      $(this).attr('src','images/favorite.png')
      $(this).attr('name', 'enable');
  }
  var tid = $(this).parents('div.td').attr("id").split('_')[1];
  
  var pathName = document.location.pathname;
  var index = pathName.substr(1).indexOf("/");
  var ctxPath = pathName.substr(0,index+1);
  
  var url = ctxPath + "/favorite_tds/update/" + tid
    $.ajax({
          url:url,
          success:function(e) {
              if(e.type == "add") {
                  if(!e.success) {
                      $('.favorite_' + e.tid).attr('src','images/unfavorite.png')
                  }
              }
              if(e.type == "remove") {
                  if(!e.success) {
                      $('.favorite_' + e.tid).attr('src','images/favorite.png')
                  }
              }
          }
      })
})