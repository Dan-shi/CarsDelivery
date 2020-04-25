/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var host = "http://localhost:8080/";
function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

}

//show alert
function showAlert(title, message) {
    Eject.Ealert({
        title: title,
        message: message
    })
}

//show toast
function showToast(message) {
    Eject.Etoast(message, 3) //默认三秒
}


//判断字符是否为空的方法
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}