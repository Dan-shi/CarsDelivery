/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

function submitOrder() {
	//get value from page
	var carName = document.getElementById("carName").value;
	var carType = document.getElementById("carType").value;
	var fromLoc = document.getElementById("fromLoc").value;
	var toLoc = document.getElementById("toLoc").value;
	var cusName = document.getElementById("cusName").value;
	var cusPhone = document.getElementById("cusPhone").value;
	var description = document.getElementById("description").value;

	//value check
	var errorMsg = "";
	if (isEmpty(carName)) {
		errorMsg = errorMsg + "请填写车型";
	}

	if (isEmpty(carType)) {
		errorMsg = errorMsg + "请填写出发城市";
	}

	if (isEmpty(fromLoc)) {
		errorMsg = errorMsg + "请填写到达城市";
	}

	if (isEmpty(toLoc)) {
		errorMsg = errorMsg + "请填写车型";
	}

	if (isEmpty(cusPhone)) {
		errorMsg = errorMsg + "请填写您的电话";
	}

	if (!isEmpty(cusPhone) && !isPhoneNum(cusPhone)) {
		errorMsg = errorMsg + "请填写正确的电话格式";
	}

	if (!isEmpty(errorMsg)) {
		//show model return
		return;
	}

	//Build order model
	$.ajax({
		url: API_URL + "user/getUserByOpenId", // 目标资源
		cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
		async: false, //默认是true，即为异步方式
		data: params,
		dataType: "json", // 服务器响应的数据类型
		type: "POST", // 请求方式
		success: function(data) {
			console.log(data)
		}
	});
}

function isPhoneNum(phone) {
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	if (!myreg.test(phone)) {
		return false;
	}
	return true;
}


//判断字符是否为空的方法
function isEmpty(obj) {
	if (typeof obj == "undefined" || obj == null || obj == "") {
		return true;
	} else {
		return false;
	}
}