/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var host = "http://localhost:8080/";

function loadBlog(type) {

    $.ajax({
        url: host + "blog/getBlogsPage?isActive=true&blogType=" + type + "&pageNum=1", // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        contentType: "application/json",
        dataType: "json", // 服务器响应的数据类型
        type: "GET", // 请求方式
        success: function (data) {
            if (data.resultCode == 100) {
                buildBlogElements(type);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function buildBlogElements(type) {
    if (type == 0) {
        buildNewsElements();
    }

    if (type == 1) {
        buildCasesElements();
    }
}

function buildNewsElements() {

}

function buildCasesElements() {

}

/**
 * Load news list and case list
 */
$(document).ready(function (e) {
    var isLogin = sessionStorage.getItem("isLogin");
    if (!isLogin) {
        CommonUtils.loginDefaultUser();
    }
    loadBlog(0);
    loadBlog(1);
});

