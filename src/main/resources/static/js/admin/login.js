/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var host = "http://localhost:8080/";

function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    //value check
    var errorMsg = "";
    if (CommonUtils.isEmpty(username)) {
        errorMsg = errorMsg + "请填写用户名</br>";
    }

    if (CommonUtils.isEmpty(password)) {
        errorMsg = errorMsg + "请填写密码</br>";
    }

    if (!CommonUtils.isEmpty(errorMsg)) {
        //show model return
        CommonUtils.showAlert('输入错误', errorMsg);
        return;
    }

    //user model format
    var user = {
        username: username,
        password: password
    };

    CommonUtils.showLoading();

    $.ajax({
        url: host + "user/login", // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        data: JSON.stringify(user),
        contentType: "application/json",
        dataType: "text", // 服务器响应的数据类型
        type: "POST", // 请求方式
        success: function (data, status, xhr) {
            CommonUtils.showToast('登录成功')
            setTimeout("window.location.href = 'order'", 1500)
        },
        complete: function () {
            CommonUtils.hideLoading();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 401) {
                CommonUtils.showToast('用户名或密码错误');
            } else {
                CommonUtils.showToast('请求错误');
            }
        }
    });
}
