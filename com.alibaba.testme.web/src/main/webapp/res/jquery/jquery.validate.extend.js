var regExp_userNick = "^([\u4e00-\u9fa5]|[A-Za-z]){1}([\u4e00-\u9fa5]|[0-9]|[A-Za-z]|\\_)*([\u4e00-\u9fa5]|[0-9]|[A-Za-z]){1}$";
var regExp_userId = "^([1-9]){1}([0-9]){4,19}$";
var userEmailRegExp = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;

jQuery.validator.addMethod("userNick", function(value, element, params) {
	var exp = new RegExp(regExp_userNick);
	value = $.trim(value);
	var check = exp.test(value);
	if (check && value.indexOf("__") == -1) {
		return f_wordsLength(value, 20, 5);
	}
	return false;
}, "不合法的会员名");

jQuery.validator.addMethod("loginName", function(value, element, params) {
	var userIdExp = new RegExp(regExp_userId);
	value = $.trim(value);
	if (userIdExp.test(value) || f_isUserNick_login(value)
			|| userEmailRegExp.test(value)) {
		return true;
	}
	return false;
}, "不合法的登录帐号");

// 校验两个值是否不一致，如果一致则报错，用于修改密码时候
jQuery.validator.addMethod("notEqualTo", function(value, element, params) {
	value = $.trim(value);
	var oldValue = $(params).val();
	if (value == oldValue) {
		return false;
	} else {
		return true;
	}
}, "两个值一致");

// 校验验证码
jQuery.validator.addMethod("authCode",
		function(value, element, params) {
			value = $.trim(value);
			if (value == undefined || $.trim(value) == ""
					|| $.trim(value).length != 4) {
				return false;
			}
			return true;
		}, "验证码为4个字符");

// 校验密码格式
jQuery.validator.addMethod("password", function(value, element, params) {
	value = $.trim(value);
	if (value == undefined || $.trim(value) == "" || $.trim(value).length > 16
			|| $.trim(value).length < 6) {
		return false;
	}
	return true;
}, "密码6~16个字符");

// 校验电话格式，允许为手机或者固话
jQuery.validator.addMethod("phone", function(value, element, params) {
	if (value == undefined || $.trim(value) == "") {
		return true;
	}
	if ($.trim(value).length > 25 || $.trim(value).length < 10) {
		return false;
	}
	return true;
}, "联系电话为10~25个字符");

// 校验电话格式，允许为手机或者固话
jQuery.validator.addMethod("fax", function(value, element, params) {
	if (value == undefined || $.trim(value) == "") {
		return true;
	}
	if ($.trim(value).length > 25 || $.trim(value).length < 10) {
		return false;
	}
	return true;
}, "传真为10~25个字符");

// 校验真实姓名
jQuery.validator.addMethod("realName", function(value, element, params) {
	value = $.trim(value);
	if (value.length > 20 || value.length < 2) {
		return false;
	}
	return true;
}, "真实姓名2~20个字符");

// 真实姓名必录
jQuery.validator.addMethod("realNameRequired",
		function(value, element, params) {
			if (value == undefined || $.trim(value) == "") {
				return false;
			}
			return true;
		}, "请输入真实姓名");

// 校验公司全称
jQuery.validator.addMethod("companyName", function(value, element, params) {
	value = $.trim(value);
	if (value.length > 50 || value.length < 5) {
		return false;
	}
	return true;
}, "公司名称5~50个字符");

// 如果多选框被选中，则需要指定的某个文本框必录
// 如：requiredIfChecked: "authDTO.accountType,1"
jQuery.validator
		.addMethod("requiredIfChecked",
				function(value, element, params) {
					var name = params.split(",")[0];
					var eqlVal = params.split(",")[1];

					var radioCheckValue = $(
							"input[name='" + name + "']:checked").val();
					if (eqlVal == radioCheckValue) {
						if (value == undefined || $.trim(value) == "") {
							return false;
						}
					}
					return true;
				}, "多选框或单选框已选中，文本框必录");

// 如果多选框未被选中，则需要指定的某个文本框必录
// 如：requiredIfNotChecked: "authDTO.accountType,1"
jQuery.validator.addMethod("requiredIfNotChecked", function(value, element,
		params) {
	var name = params.split(",")[0];
	var eqlVal = params.split(",")[1];

	var radioCheckValue = $("input[name='" + name + "']:checked").val();
	if (radioCheckValue == undefined || eqlVal != radioCheckValue) {
		if (value == undefined || $.trim(value) == "") {
			return false;
		}
	}
	return true;
}, "必需项");

// 允许输入的字符，一个中文计为2个字符
jQuery.validator.addMethod("rangelengthCN",
		function(value, element, param) {
			var length = $.trim(value).length;
			for ( var i = 0; i < value.length; i++) {
				if (value.charCodeAt(i) > 127) {
					length++;
				}
			}
			return this.optional(element)
					|| (length >= param[0] && length <= param[1]);
		}, $.validator.format("允许输入{0}-{1}个字符，1个汉字计两个字符。"));

// 允许输入固定长度的字符
jQuery.validator.addMethod("fixLength", function(value, element, param) {
	var length = $.trim(value).length;
	return this.optional(element) || (length == param);
}, $.validator.format("允许输入固定长度{0}个字符。"));